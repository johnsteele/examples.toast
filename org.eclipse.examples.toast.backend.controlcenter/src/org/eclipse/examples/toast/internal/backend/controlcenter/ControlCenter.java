/*******************************************************************************
 * Copyright (c) 2009 Paul VanderLei, Simon Archer, Jeff McAffer and others. All 
 * rights reserved. This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 and Eclipse Distribution License
 * v1.0 which accompanies this distribution. The Eclipse Public License is available at 
 * http://www.eclipse.org/legal/epl-v10.html and the Eclipse Distribution License 
 * is available at http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors: 
 *     Paul VanderLei, Simon Archer, Jeff McAffer - initial API and implementation
 *******************************************************************************/
package org.eclipse.examples.toast.internal.backend.controlcenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.examples.toast.backend.controlcenter.IControlCenter;
import org.eclipse.examples.toast.backend.controlcenter.IData;
import org.eclipse.examples.toast.backend.data.IToastBackEndDataFactory;
import org.eclipse.examples.toast.backend.data.IVehicle;
import org.eclipse.examples.toast.backend.data.internal.TrackedLocation;
import org.eclipse.examples.toast.backend.data.internal.Vehicle;
import org.eclipse.examples.toast.backend.provisioning.IProvisioner;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.discovery.IDiscovery;
import org.eclipse.examples.toast.core.discovery.IDiscoveryListener;

public class ControlCenter implements IControlCenter, IDiscoveryListener {
	private Map vehiclesById = new HashMap();
	private IProvisioner provisioner;
	private IDiscovery discovery;
	private IData data;

	public ControlCenter() {
		super();
	}

	private void loadData() {
		Collection vehicles = data.getVehicles();
		for (Iterator i = vehicles.iterator(); i.hasNext();) {
			IVehicle vehicle = (IVehicle) i.next();
			vehiclesById.put(vehicle.getName(), vehicle);
			Map properties = new HashMap();
			properties.put("osgi.os", System.getProperty("osgi.os"));
			properties.put("osgi.ws", System.getProperty("osgi.ws"));
			properties.put("osgi.arch", System.getProperty("osgi.arch"));
			provisioner.addProfile(vehicle.getName(), properties);
		}
	}

	public void bind(IDiscovery value) {
		discovery = value;
	}

	public void setData(IData value) {
		data = value;
	}

	public void bind(IProvisioner value) {
		provisioner = value;
	}

	public void activate() {
		Runnable work = new Runnable() {
			public void run() {
				loadData();
				discovery.addListener(ControlCenter.this);
				Collection profiles = provisioner.getProfiles();
				for (Iterator i = profiles.iterator(); i.hasNext();)
					addVehicle((String) i.next(), null);
			}
		};
		new Thread(work).run();
	}

	public void unbind(IDiscovery value) {
		discovery = null;
	}

	public void unbind(IProvisioner value) {
		provisioner = null;
	}

	public void deactivate() {
		discovery.removeListener(this);
	}

	public boolean emergency(String id, int latitude, int longitude, int heading, int speed) {
		IVehicle vehicle = addVehicle(id, null);
		TrackedLocation location = createLocation(latitude, longitude, heading, speed);
		System.out.println("Emergency reported!");
		System.out.println(printLocation(location));
		((Vehicle) vehicle).setEmergencyLocation(location);
		((Vehicle) vehicle).setCurrentLocation(location);
		return true;
	}

	private String printLocation(TrackedLocation location) {
		StringBuffer result = new StringBuffer();
		result.append(" heading: ");
		result.append(location.getHeading());
		result.append(", latitude: ");
		result.append(location.getLatitude());
		result.append(", longitude: ");
		result.append(location.getLongitude());
		result.append(", speed: ");
		result.append(location.getSpeed());
		result.append(", time: ");
		result.append(location.getTime());
		return result.toString();
	}

	public void postLocation(String id, int latitude, int longitude, int heading, int speed) {
		IVehicle vehicle = addVehicle(id, null);
		TrackedLocation location = createLocation(latitude, longitude, heading, speed);
		System.out.println("Location reported");
		System.out.println(printLocation(location));
		((Vehicle) vehicle).setCurrentLocation(location);
	}

	private TrackedLocation createLocation(int latitude, int longitude, int heading, int speed) {
		TrackedLocation location = (TrackedLocation) IToastBackEndDataFactory.eINSTANCE.createTrackedLocation();
		location.setHeading(heading);
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		location.setSpeed(speed);
		location.setTime(System.currentTimeMillis());
		return location;
	}

	public Collection getKnownIds() {
		return new ArrayList(vehiclesById.keySet());
	}

	public Collection getVehicles() {
		return new ArrayList(vehiclesById.values());
	}

	public IVehicle getVehicle(String id) {
		return (IVehicle) vehiclesById.get(id);
	}

	public IVehicle addVehicle(String id, Map properties) {
		IVehicle result = (IVehicle) vehiclesById.get(id);
		if (result != null)
			return result;
		Vehicle vehicle = (Vehicle) IToastBackEndDataFactory.eINSTANCE.createVehicle();
		vehicle.setName(id);
		vehiclesById.put(id, vehicle);
		provisioner.addProfile(id, properties);
		return vehicle;
	}

	public void removeVehicle(String id) {
		vehiclesById.remove(id);
		provisioner.removeProfile(id);
	}

	public void registered(String id, Map properties) {
		LogUtility.logDebug(this, "Vehicle registered: " + id);
		addVehicle(id, properties);
	}

	public void unregistered(String id) {
		LogUtility.logDebug(this, "Vehicle unregistered: " + id);
		vehiclesById.remove(id);
	}
}

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
package org.eclipse.examples.toast.internal.backend.tracking;

import org.eclipse.examples.toast.backend.controlcenter.IData;
import org.eclipse.examples.toast.backend.data.IToastBackEndDataFactory;
import org.eclipse.examples.toast.backend.data.ITrackedLocation;
import org.eclipse.examples.toast.backend.data.IVehicle;
import org.eclipse.examples.toast.backend.data.internal.TrackedLocation;
import org.eclipse.examples.toast.backend.data.internal.Vehicle;
import org.eclipse.examples.toast.core.tracking.ITrackingCenter;

public class TrackingCenter implements ITrackingCenter {

	private IData data;

	public TrackingCenter() {
		super();
	}

	public void setData(IData value) {
		data = value;
	}

	public void startup() {
	}

	public void shutdown() {
	}

	public void postTrackingLocation(String id, int latitude, int longitude, int heading, int speed) {
		IVehicle vehicle = data.getVehicle(id);
		if (vehicle == null)
			return;
		TrackedLocation location = createLocation(latitude, longitude, heading, speed);
		System.out.println("Location reported");
		System.out.println(location);
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

	public ITrackedLocation[] getTrackedLocations(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}

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
package org.eclipse.examples.toast.backend.data.loader;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.eclipse.examples.toast.backend.controlcenter.IData;
import org.eclipse.examples.toast.backend.data.IVehicle;

public class SimpleDataStore implements IData {

	private Map vehicles;

	public void startup() {
		vehicles = new HashMap();
		DataLoader loader = new DataLoader();
		loader.perZone = 50;
		loader.waybills = 10;
		loader.mode = DataLoader.CITY_MODE;
		loader.cities = Arrays.asList(new String[] {"san francisco"});
		loader.locationSource = DataLoader.class.getResource("sanfran.txt");
		List values = loader.run();
		for (Iterator i = values.iterator(); i.hasNext();) {
			IVehicle element = (IVehicle) i.next();
			vehicles.put(element.getName(), element);
		}
	}

	public Collection getVehicles() {
		return vehicles.values();
	}

	public Collection getVehicleNames() {
		return vehicles.keySet();
	}

	public IVehicle getVehicle(String name) {
		return (IVehicle) vehicles.get(name);
	}

	public void persist(IVehicle object) {
		vehicles.put(object.getName(), object);
	}

	public void removeVehicle(String name) {
		vehicles.remove(name);
	}

	public void update(IVehicle object) {
		vehicles.put(object.getName(), object);
	}
}

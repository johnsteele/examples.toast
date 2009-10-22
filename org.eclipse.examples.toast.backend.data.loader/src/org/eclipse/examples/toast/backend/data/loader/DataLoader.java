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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.eclipse.examples.toast.backend.data.IToastBackEndDataFactory;
import org.eclipse.examples.toast.backend.data.IVehicle;
import org.eclipse.examples.toast.backend.data.internal.Address;
import org.eclipse.examples.toast.backend.data.internal.Driver;
import org.eclipse.examples.toast.backend.data.internal.Package;
import org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataFactory;
import org.eclipse.examples.toast.backend.data.internal.TrackedLocation;
import org.eclipse.examples.toast.backend.data.internal.Vehicle;
import org.eclipse.examples.toast.backend.data.internal.Waybill;

public class DataLoader {
	protected static final int STREET = 0;
	protected static final int ZIP = 1;
	protected static final int STATE = 2;
	protected static final int CITY = 3;
	protected static final int LONGITUDE = 4;
	protected static final int LATITUDE = 5;
	protected static final int ZIP_MODE = 1;
	protected static final int STATE_MODE = 2;
	protected static final int CITY_MODE = 3;
	protected static final int ALL_MODE = 0;

	protected int perZone = 5;
	protected List states;
	protected List zips;
	protected List ranges;
	protected List cities;
	protected URL locationSource;
	protected ArrayList locations = new ArrayList();
	protected Location nextLocation;
	protected int mode = ALL_MODE;
	protected int waybills = 10;
	protected double factor = 60.0;

	String[] drivers = readDrivers();

	public List run() {
		ArrayList result = new ArrayList();
		try {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(locationSource.openStream()));
				in.readLine();
				while (stream(in) != null) {
					result.addAll(buildObjects());
				}
			} finally {
				if (in != null)
					in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private Collection stream(BufferedReader in) throws IOException {
		locations.clear();
		String currentFilter = null;
		if (nextLocation == null)
			nextLocation = readLocation(in);
		while (nextLocation != null) {
			if (mode == ALL_MODE)
				currentFilter = null;
			String filter = null;
			if (mode == STATE_MODE)
				filter = nextLocation.getState();
			if (mode == ZIP_MODE)
				filter = nextLocation.getZip();
			if (mode == CITY_MODE)
				filter = nextLocation.getCity();
			if (currentFilter == null || currentFilter.equalsIgnoreCase(filter)) {
				accept(nextLocation);
				currentFilter = filter;
				nextLocation = readLocation(in);
			}
		}
		return locations.isEmpty() ? null : locations;
	}

	protected void accept(Location location) {
		locations.add(location);
	}

	private boolean validateLocation(Location location) {
		if (cities != null && !cities.isEmpty() && cities.contains(location.getCity().toLowerCase()))
			return true;
		if (states != null && !states.isEmpty() && states.contains(location.getState().toLowerCase()))
			return true;
		if (zips != null && !zips.isEmpty() && zips.contains(location.getZip()))
			return true;
		return false;
	}

	private Location readLocation(BufferedReader in) throws IOException {
		String line = in.readLine();
		while (line != null) {
			String[] segments = line.split(";");
			Location result = new Location();
			result.setStreet(segments[STREET]);
			result.setZip(segments[ZIP]);
			result.setState(segments[STATE]);
			result.setCity(segments[CITY]);
			result.setLongitude(randomize(Double.parseDouble(segments[LONGITUDE]) * -1));
			result.setLatitude(randomize(Double.parseDouble(segments[LATITUDE])));
			if (validateLocation(result))
				return result;
			line = in.readLine();
		}
		return null;
	}

	protected Collection buildObjects() {
		ArrayList result = new ArrayList();
		int count = locations.size() / perZone;
		for (int i = 0; i < count; i++)
			result.add(createVehicle(i));
		return result;
	}

	private IVehicle createVehicle(int n) {
		IToastBackEndDataFactory factory = ToastBackEndDataFactory.eINSTANCE;
		Address driverAddress = createAddress(getRandomLocation());

		Driver driver = createDriver(n);
		driver.setAddress(driverAddress);

		TrackedLocation currentLocation = createTrackedLocation(null);
		TrackedLocation emergencyLocation = createTrackedLocation(null);

		Vehicle vehicle = (Vehicle) factory.createVehicle();
		vehicle.setName(driverAddress.getArea() + "-" + driverAddress.getCity() + "-" + n);
		vehicle.setDriver(driver);
		vehicle.setCurrentLocation(currentLocation);
		vehicle.setEmergencyLocation(emergencyLocation);
		int count = getRandom(waybills);
		for (int i = 0; i < count; i++) {
			Package package1 = (Package) factory.createPackage();
			Waybill waybill = (Waybill) factory.createWaybill();
			package1.setWaybill(waybill);
			package1.setWeight(getRandom(100));

			waybill.setItem(package1);
			waybill.setDestination(createAddress(getRandomLocation()));
			waybill.setOrigin(createAddress(getRandomLocation()));
			waybill.setSent(new Date());
			waybill.setReceived(null);

			vehicle.getPackages().add(waybill);
		}

		return vehicle;
	}

	private Driver createDriver(int n) {
		IToastBackEndDataFactory factory = ToastBackEndDataFactory.eINSTANCE;
		Driver driver = (Driver) factory.createDriver();
		if (n >= 0 && n < drivers.length) {
			String line = drivers[n];
			String[] parts = line.split("\t");
			String name = parts[0];
			int idx = name.lastIndexOf(' ');
			if (idx != -1) {
				driver.setFirstName(name.substring(0, idx));
				driver.setLastName(name.substring(idx + 1));
			} else {
				driver.setFirstName("");
				driver.setLastName(name);
			}
			if (parts.length > 1) {
				String imageId = parts[1];
				try {
					driver.setImage(new URI("https://www.eclipsecon.org/submissions/2009/callbacks/show_image.php?PersonID=" + imageId));
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			driver.setFirstName("Driver");
			driver.setLastName(String.valueOf(n));
		}
		return driver;
	}

	private static String[] readDrivers() {
		List list = new ArrayList();
		InputStream in = DataLoader.class.getResourceAsStream("presenters.tsv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String[] result = new String[list.size()];
		list.toArray(result);
		return result;
	}

	private TrackedLocation createTrackedLocation(Location location) {
		if (location == null)
			location = getRandomLocation();
		IToastBackEndDataFactory factory = ToastBackEndDataFactory.eINSTANCE;
		TrackedLocation currentLocation = (TrackedLocation) factory.createTrackedLocation();
		currentLocation.setHeading(getRandom(360));
		currentLocation.setLatitude(location.getLatitude());
		currentLocation.setLongitude(location.getLongitude());
		currentLocation.setSpeed(getRandom(100));
		currentLocation.setTime(System.currentTimeMillis());
		return currentLocation;
	}

	private int getRandom(int i) {
		return (int) Math.floor(Math.random() * i);
	}

	private Location getRandomLocation() {
		return (Location) locations.get(getRandom(locations.size()));
	}

	private double randomize(double value) {
		double random = Math.random();
		// scale the random down by factor and then make it +/- 
		// and tweak the value
		random = random / factor;
		random = (1.0 / factor / 2.0) - random;
		return value + random;
	}

	private Address createAddress(Location location) {
		IToastBackEndDataFactory factory = ToastBackEndDataFactory.eINSTANCE;
		Address result = (Address) factory.createAddress();
		result.setStreet(location.getStreet());
		result.setCity(location.getCity());
		result.setArea(location.getState());
		result.setCountry("USA");
		result.setPostcode(location.getZip());
		return result;
	}
}

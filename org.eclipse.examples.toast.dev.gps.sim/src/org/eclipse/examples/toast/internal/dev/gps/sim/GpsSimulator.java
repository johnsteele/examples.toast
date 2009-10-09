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
package org.eclipse.examples.toast.internal.dev.gps.sim;

import org.eclipse.examples.toast.dev.gps.IGps;
import org.eclipse.examples.toast.dev.gps.sim.IGpsSimulator;
import org.eclipse.examples.toast.devsim.IDeviceSimulator;

public class GpsSimulator implements IGps, IGpsSimulator {
	private static final String COMPASS_NAME = "compass";
	private static final String DEVICE_NAME = "gps";
	private static final String DEVICE_LABEL = "GPS";
	private static final String SPEED_NAME = "speed";
	private IDeviceSimulator simulator;
	private GpsCompassSensor compass;
	private GpsSpeedSensor speed;

	public void setDevSim(IDeviceSimulator value) {
		simulator = value;
	}

	public void startup() {
		simulator.registerDevice(DEVICE_NAME, DEVICE_LABEL);
		compass = new GpsCompassSensor(COMPASS_NAME, "Compass");
		simulator.addParameter(compass, DEVICE_NAME);
		speed = new GpsSpeedSensor(SPEED_NAME, "Speed", 0, 250, 0, 5, 50, "kph");
		simulator.addParameter(speed, DEVICE_NAME);
	}

	public void shutdown() {
		simulator.unregisterDevice(DEVICE_NAME);
	}

	// IGps implementation
	public int getSpeed() {
		return simulator.getIntegerValue(DEVICE_NAME, SPEED_NAME);
	}

	public int getHeading() {
		return compass.getHeading();
	}

	public int getLatitude() {
		return compass.getLatitude();
	}

	public int getLongitude() {
		return compass.getLongitude();
	}

	// IGpsSimulator implementation
	public void clearMaster() {
		compass.clearMaster();
		speed.clearMaster();
	}

	public void setMaster(IGps master) {
		compass.setMaster(master);
		speed.setMaster(master);
	}
}

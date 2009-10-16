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
package org.eclipse.examples.toast.internal.dev.google;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.examples.toast.crust.shell.DisplayBlock;
import org.eclipse.examples.toast.dev.google.IGoogleEarth;
import org.eclipse.examples.toast.dev.gps.IGps;
import org.eclipse.examples.toast.devsim.IDeviceSimulator;
import org.eclipse.examples.toast.devsim.IDeviceSimulatorListener;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

public class Simulator implements IDeviceSimulatorListener, IGps, IGoogleEarth {
	private static final String DEVICE_NAME = "routing";
	private static final String DEVICE_LABEL = "Routing";
	private static final String PARK_AT_ORIGIN = "parkOrigin";
	private static final String DRIVE_ROUTE_50_KPH = "drive50";
	private static final String DRIVE_ROUTE_250_KPH = "drive250";
	private static final String STOP_DRIVING = "stop";
	private static final String PARK_AT_DESTINATION = "parkDestination";
	private static final int NORMAL_FACTOR = 1;
	private static final int FAST_FACTOR = 5;
	private IDeviceSimulator simulator;
	public Browser browser;
	public Composite browserParent;
	private boolean driving = false;
	private List callbacks = new ArrayList();

	static class PositionCallback extends BrowserFunction {
		public PositionCallback(Browser browser, String name) {
			super(browser, name);
		}

		public Object function(Object[] arguments) {
			return null;
		}
	}

	static class SegmentCallback extends BrowserFunction {
		public SegmentCallback(Browser browser, String name) {
			super(browser, name);
		}

		public Object function(Object[] arguments) {
			return null;
		}
	}

	public void setBrowser(Browser value) {
		browser = value;
	}

	public void setSimulator(IDeviceSimulator value) {
		simulator = value;
	}

	public void startup() {
		startupJSCallbacks();
		startupSimulator();
	}

	public void startupSimulator() {
		simulator.registerDevice(DEVICE_NAME, DEVICE_LABEL, this);
		simulator.addNonRepeatableActionSensor(DEVICE_NAME, PARK_AT_ORIGIN, "Park at origin", PARK_AT_ORIGIN);
		simulator.addNonRepeatableActionSensor(DEVICE_NAME, DRIVE_ROUTE_50_KPH, "Drive route 50 kph", DRIVE_ROUTE_50_KPH);
		simulator.addNonRepeatableActionSensor(DEVICE_NAME, DRIVE_ROUTE_250_KPH, "Drive route 250 kph", DRIVE_ROUTE_250_KPH);
		simulator.addNonRepeatableActionSensor(DEVICE_NAME, STOP_DRIVING, "Stop driving", STOP_DRIVING);
		simulator.addNonRepeatableActionSensor(DEVICE_NAME, PARK_AT_DESTINATION, "Park at destination", PARK_AT_DESTINATION);
	}

	private void startupJSCallbacks() {
		// System.out.println("registering JS callbacks");
		// registerJSCallback(new SegmentCallback(browser,
		// "toast_segment_callback"));
		// registerJSCallback(new PositionCallback(browser,
		// "toast_position_callback"));
	}

	private void disposeJSCallbacks() {
		System.out.println("disposing JS callbacks");
		for (Iterator i = callbacks.iterator(); i.hasNext();) {
			BrowserFunction function = (BrowserFunction) i.next();
			if (!function.isDisposed())
				function.dispose();
		}
		callbacks.clear();
	}

	public void registerJSCallback(BrowserFunction function) {
		callbacks.add(function);
	}

	public void shutdown() {
		simulator.unregisterDevice(DEVICE_NAME);
		disposeJSCallbacks();
	}

	public void setVisible(Composite parent, Rectangle bounds) {
		browserParent = browser.getParent();
		browser.setParent(parent);
		browser.setVisible(true);
		browser.setBounds(bounds);
	}

	public void hide() {
		if (browser == null)
			return;
		browser.setVisible(false);
		if (browserParent != null)
			browser.setParent(browserParent);
	}

	// IDeviceSimulatorListener implementation
	public void performAction(String parameterName, String actionName) {
		if (PARK_AT_ORIGIN.equals(parameterName) && PARK_AT_ORIGIN.equals(actionName))
			parkAtOrigin();
		else if (DRIVE_ROUTE_50_KPH.equals(parameterName) && DRIVE_ROUTE_50_KPH.equals(actionName))
			driveRoute(NORMAL_FACTOR);
		else if (DRIVE_ROUTE_250_KPH.equals(parameterName) && DRIVE_ROUTE_250_KPH.equals(actionName))
			driveRoute(FAST_FACTOR);
		else if (STOP_DRIVING.equals(parameterName) && STOP_DRIVING.equals(actionName))
			stopDriving();
		else if (PARK_AT_DESTINATION.equals(parameterName) && PARK_AT_DESTINATION.equals(actionName))
			parkAtDestination();
	}

	public void valueChanged(String name, int newValue) {
	}

	public void valueChanged(String name, boolean newValue) {
	}

	// Private
	private Object run(final String script) {
		final Object[] result = new Object[1];
		final Browser b = browser;
		new DisplayBlock() {
			public void run() {
				result[0] = b.evaluate(script);
			}
		}.sync();
		return result[0];
	}

	private boolean isDriving() {
		return driving;
	}

	private void parkAtOrigin() {
		if (isDriving())
			return;
	}

	private void driveRoute(int speed) {
		if (isDriving())
			return;
		driving = true;
		controlSimulator(COMMAND_START);
	}

	private void stopDriving() {
		if (!isDriving())
			return;
		controlSimulator(COMMAND_PAUSE);
		driving = false;
	}

	private void parkAtDestination() {
		if (isDriving())
			return;
	}

	public int getHeading() {
		String script = "if (DS_simulator) return DS_simulator.ge.getView().copyAsLookAt(DS_simulator.ge.ALTITUDE_RELATIVE_TO_GROUND).getHeading(); return 0";
		Object result = run(script);
		return (int) Math.round(((Double) result).doubleValue());
	}

	private int convertToTud(Double value) {
		return (int) Math.round(value.doubleValue() * 100000.0);
	}

	public int getLatitude() {
		//		String script = "if (DS_simulator && DS_simulator.currentLoc) return DS_simulator.currentLoc.lat(); return 37.38947;";
		//		String script = "alert(getLat());return getLat();";
		String script = "return getLat();";
		Object result = run(script);
		if (result == null)
			return 3738947;
		return convertToTud((Double) result);
	}

	public int getLongitude() {
		//		String script = "if (DS_simulator && DS_simulator.currentLoc) return DS_simulator.currentLoc.lng(); return -122.08169;";
		String script = "return getLng();";
		Object result = run(script);
		if (result == null)
			return -12208169;
		return convertToTud((Double) result);
	}

	public int getSpeed() {
		String script = "if (DS_simulator) return DS_simulator.currentSpeed;  return 0;";
		Object result = run(script);
		if (result == null)
			return 0;
		return (int) Math.round(((Double) result).doubleValue());
	}

	public void flyTo(int latitude, int longitude, int heading, int zoom) {
		if (isDriving())
			return;
		String lats = "" + (latitude / 100000.0);
		String longs = "" + (longitude / 100000.0);
		String script = "DS_flyToLatLng(new google.maps.LatLng(" + lats + "," + longs + "," + heading + "," + zoom + ")); return 0;";
		run(script);
	}

	public void flyTo(IGps gps, int zoom) {
		if (isDriving())
			return;
		int gpsLat = gps.getLatitude();
		int gpsLng = gps.getLongitude();
		int vehicleLat = getLatitude();
		int vehicleLng = getLongitude();
		if (gpsLat == vehicleLat && gpsLng == vehicleLng)
			return;
		String lat = Double.toString(gpsLat / 100000.0);
		String lng = Double.toString(gpsLng / 100000.0);
		String heading = Integer.toString(gps.getHeading());
		String zooms = Integer.toString(zoom);
		String script = "DS_flyToLatLng(" + lat + "," + lng + "," + heading + "," + zooms + ");";
		run(script);
	}

	public void loadDirections(int originLatitude, int originLongitude, int destLatitude, int destLongitude) {
		String from = "(" + originLatitude / 100000.0 + "," + originLongitude / 100000.0 + ")";
		String to = "(" + destLatitude / 100000.0 + "," + destLongitude / 100000.0 + ")";
		//		from = "Mountain View, CA";
		//		to = "San Francisco, CA";

		String script = "DS_goDirections(\"" + from + "\",\"" + to + "\"); return 0;";
		run(script);
	}

	public void controlSimulator(String command) {
		String script = "DS_controlSimulator('" + command + "'); return 0;";
		run(script);
	}
}

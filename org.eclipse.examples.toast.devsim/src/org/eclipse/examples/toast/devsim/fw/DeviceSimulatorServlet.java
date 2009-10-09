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
package org.eclipse.examples.toast.devsim.fw;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.examples.toast.devsim.IDeviceSimulator;
import org.eclipse.examples.toast.devsim.IDeviceSimulatorListener;

public class DeviceSimulatorServlet extends HttpServlet implements IDeviceSimulator {
	private static final String DEVICE_PARAMETER = "device"; //$NON-NLS-1$
	private static final String UNKNOWN_DEVICE = "Unknown device: "; //$NON-NLS-1$
	private static final String CONTENT_TYPE_HTML = "text/html"; //$NON-NLS-1$
	private Map devices;
	private String id;
	private boolean evenOdd;
	private HtmlGenerator gen;
	private IDeviceSimulatorListener listener;

	public DeviceSimulatorServlet(String id) {
		super();
		devices = new HashMap(7);
		this.id = id;
	}

	// IDeviceSimulator implementation
	public void registerDevice(String name, String label) {
		SimulatedDevice device = new SimulatedDevice(name, label);
		devices.put(name, device);
	}

	public void registerDevice(String name, String label, IDeviceSimulatorListener listener) {
		registerDevice(name, label);
		this.listener = listener;
	}

	public void unregisterDevice(String name) {
		devices.remove(name);
		listener = null;
	}

	public void addIntegerSensor(String deviceName, String sensorName, String label, int minValue, int maxValue, int defaultValue, int smallInc, int largeInc, String units) {
		IntegerSensor parameter = new IntegerSensor(sensorName, label, minValue, maxValue, defaultValue, smallInc, largeInc, units, listener);
		addParameter(parameter, deviceName);
	}

	public void addIntegerSensor(String deviceName, String sensorName, String label, int scaleFactor, int minValue, int maxValue, int defaultValue, int smallInc, int largeInc, String units) {
		IntegerSensor parameter = new IntegerSensor(sensorName, label, scaleFactor, minValue, maxValue, defaultValue, smallInc, largeInc, units, listener);
		addParameter(parameter, deviceName);
	}

	public void addIntegerActuator(String deviceName, String actuatorName, String label, int scaleFactor, String units) {
		IntegerActuator parameter = new IntegerActuator(actuatorName, label, scaleFactor, units);
		addParameter(parameter, deviceName);
	}

	public void addIntegerActuator(String deviceName, String actuatorName, String label, String units) {
		IntegerActuator parameter = new IntegerActuator(actuatorName, label, units);
		addParameter(parameter, deviceName);
	}

	public void addBooleanSensor(String deviceName, String sensorName, String label, boolean defaultValue) {
		BooleanSensor parameter = new BooleanSensor(sensorName, label, defaultValue, listener);
		addParameter(parameter, deviceName);
	}

	public void addRepeatableActionSensor(String deviceName, String sensorName, String label, String actionName) {
		RepeatableActionSensor parameter = new RepeatableActionSensor(sensorName, label, actionName, listener);
		addParameter(parameter, deviceName);
	}

	public void addNonRepeatableActionSensor(String deviceName, String sensorName, String label, String actionName) {
		NonRepeatableActionSensor parameter = new NonRepeatableActionSensor(sensorName, label, actionName, listener);
		addParameter(parameter, deviceName);
	}

	public void addParameter(SimulatedParameter parameter, String deviceName) {
		SimulatedDevice device = getDevice(deviceName);
		device.addParameter(parameter);
	}

	public void addBooleanActuator(String deviceName, String actuatorName, String label) {
		BooleanActuator parameter = new BooleanActuator(actuatorName, label);
		addParameter(parameter, deviceName);
	}

	public boolean getBooleanValue(String deviceName, String parameterName) {
		SimulatedDevice device = getDevice(deviceName);
		return device.getBooleanValue(parameterName);
	}

	public void setBooleanValue(String deviceName, String parameterName, boolean value) {
		SimulatedDevice device = getDevice(deviceName);
		device.setBooleanValue(parameterName, value);
	}

	public void setIntegerValue(String deviceName, String parameterName, int value) {
		SimulatedDevice device = getDevice(deviceName);
		device.setIntegerValue(parameterName, value);
	}

	public int getIntegerValue(String deviceName, String parameterName) {
		SimulatedDevice device = getDevice(deviceName);
		return device.getIntegerValue(parameterName);
	}

	// Overridden
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE_HTML);
		String deviceName = request.getParameter(DEVICE_PARAMETER);
		if (deviceName == null || deviceName.length() == 0) {
			processNullCommand(request, response);
		} else {
			SimulatedDevice device = (SimulatedDevice) devices.get(deviceName);
			if (device == null)
				response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, UNKNOWN_DEVICE + deviceName);
			else {
				device.generate(request, response, id);
			}
		}
	}

	// Private
	private SimulatedDevice getDevice(String deviceName) {
		SimulatedDevice device = (SimulatedDevice) devices.get(deviceName);
		if (device == null)
			throw new RuntimeException(UNKNOWN_DEVICE + deviceName);
		return device;
	}

	private void processNullCommand(HttpServletRequest request, HttpServletResponse response) throws IOException {
		gen = new HtmlGenerator(response.getWriter());
		gen.replace("id", id);
		gen.write("/templates/DevicesPageTop.template", getClass());
		generateDevices();
		gen.write("/templates/DevicesPageBottom.template", getClass());
	}

	private void generateDevices() {
		resetEvenOdd();
		if (devices.isEmpty()) {
			gen.replace("evenOdd", nextEvenOdd());
			gen.write("/templates/DevicesPageEmptyEntry.template", getClass());
		} else {
			Object[] devicesArray = devices.keySet().toArray();
			Arrays.sort(devicesArray);
			for (int i = 0; i < devicesArray.length; i++) {
				String name = (String) devicesArray[i];
				SimulatedDevice device = getDevice(name);
				gen.replace("evenOdd", nextEvenOdd());
				gen.replace("deviceName", name);
				gen.replace("deviceLabel", device.getLabel());
				gen.write("/templates/DevicesPageDeviceEntry.template", getClass());
			}
		}
	}

	private void resetEvenOdd() {
		evenOdd = true;
	}

	private String nextEvenOdd() {
		evenOdd = !evenOdd;
		return evenOdd ? "even" : "odd";
	}
}

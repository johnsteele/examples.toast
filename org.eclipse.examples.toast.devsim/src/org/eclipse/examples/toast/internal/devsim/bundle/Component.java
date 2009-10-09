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
package org.eclipse.examples.toast.internal.devsim.bundle;

import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.PropertyManager;
import org.eclipse.examples.toast.devsim.IDeviceSimulator;
import org.eclipse.examples.toast.devsim.IDeviceSimulatorListener;
import org.eclipse.examples.toast.devsim.fw.DeviceSimulatorServlet;
import org.eclipse.examples.toast.devsim.fw.SimulatedParameter;
import org.osgi.service.http.HttpService;

public class Component implements IDeviceSimulator {
	private static final String SERVLET_ALIAS_ROOT_PROPERTY = "servlet.alias.root"; //$NON-NLS-1$
	private static final String SERVLET_ALIAS_ROOT_DEFAULT = "/client"; //$NON-NLS-1$
	private static final String SERVLET_SUFFIX = "/devices"; //$NON-NLS-1$
	private static final String RESOURCES_PATH = "/resources"; //$NON-NLS-1$

	private DeviceSimulatorServlet servlet;
	private String servletAlias;
	private String resourcesAlias;
	private HttpService httpService;

	public void setHttp(HttpService value) {
		httpService = value;
	}

	protected void startup() {
		try {
			String servletAliasRoot = PropertyManager.getProperty(SERVLET_ALIAS_ROOT_PROPERTY, SERVLET_ALIAS_ROOT_DEFAULT);
			servletAlias = servletAliasRoot + SERVLET_SUFFIX;
			resourcesAlias = servletAlias + RESOURCES_PATH;

			String id = PropertyManager.getProperty(ICoreConstants.ID_PROPERTY, ICoreConstants.ID_DEFAULT);
			servlet = new DeviceSimulatorServlet(id);
			httpService.registerServlet(servletAlias, servlet, null, null);
			httpService.registerResources(resourcesAlias, RESOURCES_PATH, null);
			LogUtility.logDebug("Registered DeviceSimulatorServlet at " + servletAlias);
		} catch (Exception e) {
			LogUtility.logError(this, "Error registering servlet with HttpService", e);
		}
	}

	protected void shutdown() {
		httpService.unregister(servletAlias);
		httpService.unregister(resourcesAlias);
		servlet = null;
	}

	// IDeviceSimulator implementation
	public void addRepeatableActionSensor(String deviceName, String sensorName, String label, String actionName) {
		servlet.addRepeatableActionSensor(deviceName, sensorName, label, actionName);
	}

	public void addNonRepeatableActionSensor(String deviceName, String sensorName, String label, String actionName) {
		servlet.addNonRepeatableActionSensor(deviceName, sensorName, label, actionName);
	}

	public void addBooleanActuator(String deviceName, String actuatorName, String label) {
		servlet.addBooleanActuator(deviceName, actuatorName, label);
	}

	public void addBooleanSensor(String deviceName, String sensorName, String label, boolean defaultValue) {
		servlet.addBooleanSensor(deviceName, sensorName, label, defaultValue);
	}

	public void addIntegerActuator(String deviceName, String actuatorName, String label, String units) {
		servlet.addIntegerActuator(deviceName, actuatorName, label, units);
	}

	public void addIntegerActuator(String deviceName, String actuatorName, String label, int scaleFactor, String units) {
		servlet.addIntegerActuator(deviceName, actuatorName, label, scaleFactor, units);
	}

	public void addIntegerSensor(String deviceName, String sensorName, String label, int minValue, int maxValue, int defaultValue, int smallInc, int largeInc, String units) {
		servlet.addIntegerSensor(deviceName, sensorName, label, minValue, maxValue, defaultValue, smallInc, largeInc, units);
	}

	public void addIntegerSensor(String deviceName, String sensorName, String label, int scaleFactor, int minValue, int maxValue, int defaultValue, int smallInc, int largeInc, String units) {
		servlet.addIntegerSensor(deviceName, sensorName, label, scaleFactor, minValue, maxValue, defaultValue, smallInc, largeInc, units);
	}

	public void addParameter(SimulatedParameter parameter, String deviceName) {
		servlet.addParameter(parameter, deviceName);
	}

	public boolean getBooleanValue(String deviceName, String parameterName) {
		return servlet.getBooleanValue(deviceName, parameterName);
	}

	public int getIntegerValue(String deviceName, String parameterName) {
		return servlet.getIntegerValue(deviceName, parameterName);
	}

	public void registerDevice(String name, String label) {
		servlet.registerDevice(name, label);
	}

	public void registerDevice(String name, String label, IDeviceSimulatorListener listener) {
		servlet.registerDevice(name, label, listener);
	}

	public void setBooleanValue(String deviceName, String parameterName, boolean value) {
		servlet.setBooleanValue(deviceName, parameterName, value);
	}

	public void setIntegerValue(String deviceName, String parameterName, int value) {
		servlet.setIntegerValue(deviceName, parameterName, value);
	}

	public void unregisterDevice(String name) {
		servlet.unregisterDevice(name);
	}
}

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
package org.eclipse.examples.toast.devsim;

import org.eclipse.examples.toast.devsim.fw.SimulatedParameter;

public interface IDeviceSimulator {
	public void registerDevice(String name, String label);

	public void registerDevice(String deviceName, String deviceLabel, IDeviceSimulatorListener listener);

	public void unregisterDevice(String name);

	public void addIntegerSensor(String deviceName, String sensorName, String label, int minValue, int maxValue, int defaultValue, int smallInc, int largeInc, String units);

	public void addIntegerSensor(String deviceName, String sensorName, String label, int scaleFactor, int minValue, int maxValue, int defaultValue, int smallInc, int largeInc, String units);

	public void addBooleanSensor(String deviceName, String sensorName, String label, boolean defaultValue);

	public int getIntegerValue(String deviceName, String parameterName);

	public boolean getBooleanValue(String deviceName, String parameterName);

	public void addRepeatableActionSensor(String deviceName, String sensorName, String label, String actionName);

	public void addNonRepeatableActionSensor(String deviceName, String sensorName, String label, String actionName);

	public void addBooleanActuator(String deviceName, String actuatorName, String label);

	public void setBooleanValue(String deviceName, String parameterName, boolean value);

	public void addIntegerActuator(String deviceName, String actuatorName, String label, String units);

	public void addIntegerActuator(String deviceName, String actuatorName, String label, int scaleFactor, String units);

	public void setIntegerValue(String deviceName, String parameterName, int value);

	public void addParameter(SimulatedParameter parameter, String deviceName);
}

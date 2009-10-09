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
package org.eclipse.examples.toast.dev.climate;

public interface IClimateControl {
	public static final short AIR_FLOW_DEFROST = 0;
	public static final short AIR_FLOW_DEFROST_LOWER = 1;
	public static final short AIR_FLOW_LOWER = 2;
	public static final short AIR_FLOW_MIDDLE = 3;
	public static final short AIR_FLOW_UPPER = 4;

	public void addListener(IClimateControlListener listener);

	public void removeListener(IClimateControlListener listener);

	// Driver side temperature
	public int getMaxTemperature();

	public int getMinTemperature();

	public void driverTemperatureUp();

	public void driverTemperatureDown();

	public int getDriverTemperature();

	public void setDriverTemperature(int temperature);

	// Passenger side temperature
	public void passengerTemperatureUp();

	public void passengerTemperatureDown();

	public int getPassengerTemperature();

	public void setPassengerTemperature(int temperature);

	// Driver side fan speed
	public int getMaxFanSpeed();

	public int getMinFanSpeed();

	public void driverFanSpeedUp();

	public void driverFanSpeedDown();

	public int getDriverFanSpeed();

	public void setDriverFanSpeed(int speed);

	// Passenger side fan speed
	public void passengerFanSpeedUp();

	public void passengerFanSpeedDown();

	public int getPassengerFanSpeed();

	public void setPassengerFanSpeed(int speed);

	// Air conditioning
	public void turnOnAirConditioning();

	public void turnOffAirConditioning();

	public boolean isAirConditioningOn();

	// Recirculation
	public void turnOnRecirculation();

	public void turnOffRecirculation();

	public boolean isRecirculationOn();

	// Rear defrost
	public void turnOnRearDefrost();

	public void turnOffRearDefrost();

	public boolean isRearDefrostOn();

	// Air flow
	public void setAirFlow(short airFlow);

	public short getAirFlow();
}

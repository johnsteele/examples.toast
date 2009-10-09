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
package org.eclipse.examples.toast.internal.dev.climate.fake;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.examples.toast.dev.climate.IClimateControl;
import org.eclipse.examples.toast.dev.climate.IClimateControlListener;

public class FakeClimateControl implements IClimateControl {
	private static final int MAX_TEMPERATURE = 90;
	private static final int MIN_TEMPERATURE = 50;
	private static final int DEFAULT_TEMPERATURE = 70;
	private static final int MAX_FAN_SPEED = 4;
	private static final int MIN_FAN_SPEED = 0;
	private static final int DEFAULT_FAN_SPEED = 1;
	private List listeners;
	private int driverTemperature = FakeClimateControl.DEFAULT_TEMPERATURE;
	private int passengerTemperature = FakeClimateControl.DEFAULT_TEMPERATURE;
	private int driverFanSpeed = FakeClimateControl.DEFAULT_FAN_SPEED;
	private int passengerFanSpeed = FakeClimateControl.DEFAULT_FAN_SPEED;
	private boolean isAirConditioningOn = false;
	private boolean isRecirculationOn = false;
	private boolean isRearDefrostOn = false;
	private short airFlow = IClimateControl.AIR_FLOW_MIDDLE;

	public FakeClimateControl() {
		super();
		listeners = new ArrayList(2);
	}

	// ClimateService implementation
	public void addListener(IClimateControlListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	public void removeListener(IClimateControlListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	// Driver side temperature
	public int getMaxTemperature() {
		return FakeClimateControl.MAX_TEMPERATURE;
	}

	public int getMinTemperature() {
		return FakeClimateControl.MIN_TEMPERATURE;
	}

	public void driverTemperatureUp() {
		if (driverTemperature < FakeClimateControl.MAX_TEMPERATURE) {
			driverTemperature++;
			notifyDriverTemperatureChanged();
		}
	}

	public void driverTemperatureDown() {
		if (driverTemperature > FakeClimateControl.MIN_TEMPERATURE) {
			driverTemperature--;
			notifyDriverTemperatureChanged();
		}
	}

	public int getDriverTemperature() {
		return driverTemperature;
	}

	public void setDriverTemperature(int temperature) {
		if (temperature != driverTemperature) {
			driverTemperature = temperature;
			notifyDriverTemperatureChanged();
		}
	}

	// Passenger side temperature
	public void passengerTemperatureUp() {
		if (passengerTemperature < FakeClimateControl.MAX_TEMPERATURE) {
			passengerTemperature++;
			notifyPassengerTemperatureChanged();
		}
	}

	public void passengerTemperatureDown() {
		if (passengerTemperature > FakeClimateControl.MIN_TEMPERATURE) {
			passengerTemperature--;
			notifyPassengerTemperatureChanged();
		}
	}

	public int getPassengerTemperature() {
		return passengerTemperature;
	}

	public void setPassengerTemperature(int temperature) {
		if (temperature != passengerTemperature) {
			passengerTemperature = temperature;
			notifyPassengerTemperatureChanged();
		}
	}

	// Fan speed
	public int getMaxFanSpeed() {
		return FakeClimateControl.MAX_FAN_SPEED;
	}

	public int getMinFanSpeed() {
		return FakeClimateControl.MIN_FAN_SPEED;
	}

	public void driverFanSpeedUp() {
		if (driverFanSpeed < FakeClimateControl.MAX_FAN_SPEED) {
			driverFanSpeed++;
			notifyDriverFanSpeedChanged();
		}
	}

	public void driverFanSpeedDown() {
		if (driverFanSpeed > FakeClimateControl.MIN_FAN_SPEED) {
			driverFanSpeed--;
			notifyDriverFanSpeedChanged();
		}
	}

	public int getDriverFanSpeed() {
		return driverFanSpeed;
	}

	public void setDriverFanSpeed(int speed) {
		if (speed != driverFanSpeed) {
			driverFanSpeed = speed;
			notifyDriverFanSpeedChanged();
		}
	}

	public void passengerFanSpeedUp() {
		if (passengerFanSpeed < FakeClimateControl.MAX_FAN_SPEED) {
			passengerFanSpeed++;
			notifyPassengerFanSpeedChanged();
		}
	}

	public void passengerFanSpeedDown() {
		if (passengerFanSpeed > FakeClimateControl.MIN_FAN_SPEED) {
			passengerFanSpeed--;
			notifyPassengerFanSpeedChanged();
		}
	}

	public int getPassengerFanSpeed() {
		return passengerFanSpeed;
	}

	public void setPassengerFanSpeed(int speed) {
		if (speed != passengerFanSpeed) {
			passengerFanSpeed = speed;
			notifyPassengerFanSpeedChanged();
		}
	}

	// Air conditioning
	public void turnOnAirConditioning() {
		if (isAirConditioningOn == false) {
			isAirConditioningOn = true;
			notifyAirConditioningChanged();
		}
	}

	public void turnOffAirConditioning() {
		if (isAirConditioningOn == true) {
			isAirConditioningOn = false;
			notifyAirConditioningChanged();
		}
	}

	public boolean isAirConditioningOn() {
		return isAirConditioningOn;
	}

	// Recirculation
	public void turnOnRecirculation() {
		if (isRecirculationOn == false) {
			isRecirculationOn = true;
			notifyRecirculationChanged();
		}
	}

	public void turnOffRecirculation() {
		if (isRecirculationOn == true) {
			isRecirculationOn = false;
			notifyRecirculationChanged();
		}
	}

	public boolean isRecirculationOn() {
		return isRecirculationOn;
	}

	// Rear defrost
	public void turnOnRearDefrost() {
		if (isRearDefrostOn == false) {
			isRearDefrostOn = true;
			notifyRearDefrostChanged();
		}
	}

	public void turnOffRearDefrost() {
		if (isRearDefrostOn == true) {
			isRearDefrostOn = false;
			notifyRearDefrostChanged();
		}
	}

	public boolean isRearDefrostOn() {
		return isRearDefrostOn;
	}

	// Air flow
	public void setAirFlow(short airFlow) {
		if (airFlow != this.airFlow) {
			this.airFlow = airFlow;
			notifyAirFlowChanged();
		}
	}

	public short getAirFlow() {
		return airFlow;
	}

	// Private
	private void notifyDriverTemperatureChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IClimateControlListener listener = (IClimateControlListener) iterator.next();
				listener.driverTemperatureChanged(driverTemperature);
			}
		}
	}

	private void notifyPassengerTemperatureChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IClimateControlListener listener = (IClimateControlListener) iterator.next();
				listener.passengerTemperatureChanged(passengerTemperature);
			}
		}
	}

	private void notifyDriverFanSpeedChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IClimateControlListener listener = (IClimateControlListener) iterator.next();
				listener.driverFanSpeedChanged(driverFanSpeed);
			}
		}
	}

	private void notifyPassengerFanSpeedChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IClimateControlListener listener = (IClimateControlListener) iterator.next();
				listener.passengerFanSpeedChanged(passengerFanSpeed);
			}
		}
	}

	private void notifyAirConditioningChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IClimateControlListener listener = (IClimateControlListener) iterator.next();
				listener.airConditioningChanged(isAirConditioningOn);
			}
		}
	}

	private void notifyRecirculationChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IClimateControlListener listener = (IClimateControlListener) iterator.next();
				listener.recirculationChanged(isRecirculationOn);
			}
		}
	}

	private void notifyRearDefrostChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IClimateControlListener listener = (IClimateControlListener) iterator.next();
				listener.rearDefrostChanged(isRearDefrostOn);
			}
		}
	}

	private void notifyAirFlowChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IClimateControlListener listener = (IClimateControlListener) iterator.next();
				listener.airFlowChanged(airFlow);
			}
		}
	}
}

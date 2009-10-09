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

public interface IClimateControlListener {
	public void driverTemperatureChanged(int temperature);

	public void passengerTemperatureChanged(int temperature);

	public void driverFanSpeedChanged(int speed);

	public void passengerFanSpeedChanged(int speed);

	public void airConditioningChanged(boolean isOn);

	public void recirculationChanged(boolean isOn);

	public void rearDefrostChanged(boolean isOn);

	public void airFlowChanged(short flow);
}

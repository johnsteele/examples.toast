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
import org.eclipse.examples.toast.devsim.fw.HtmlGenerator;
import org.eclipse.examples.toast.devsim.fw.IntegerSensor;

public class GpsSpeedSensor extends IntegerSensor {
	private IGps master;

	public GpsSpeedSensor(String name, String label, int minValue, int maxValue, int defaultValue, int smallIncrement, int largeIncrement, String units) {
		super(name, label, minValue, maxValue, defaultValue, smallIncrement, largeIncrement, units, null);
	}

	public int getValue() {
		return master == null ? super.getValue() : master.getSpeed();
	}

	protected void setValue(int value) {
		if (master != null)
			return;
		super.setValue(value);
	}

	public void generate(HtmlGenerator gen) {
		gen.replace("sensorName", name);
		gen.replace("sensorLabel", label);
		gen.replace("sensorValue", getPrintable());
		gen.replace("units", units);
		gen.write("GpsSpeedSensor.template", getClass());
	}

	public void clearMaster() {
		if (master == null)
			return;
		value = master.getSpeed();
		master = null;
	}

	public void setMaster(IGps master) {
		this.master = master;
	}
}

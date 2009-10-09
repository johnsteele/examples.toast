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

import java.io.PrintWriter;

public class IntegerActuator extends SimulatedParameter {
	private static final int NO_SCALE_FACTOR = 0;
	protected int value;
	protected String units;
	protected int scaleFactor;

	public IntegerActuator(String name, String label, int scaleFactor, String units) {
		super(name, label);
		this.scaleFactor = scaleFactor;
		this.units = units;
	}

	public IntegerActuator(String name, String label, String units) {
		super(name, label);
		this.scaleFactor = NO_SCALE_FACTOR;
		this.units = units;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void generate(HtmlGenerator gen) {
		gen.replace("actuatorName", name);
		gen.replace("actuatorLabel", label);
		gen.replace("actuatorValue", getPrintable());
		gen.replace("units", units);
		gen.write("/templates/IntegerActuator.template", getClass());
	}

	public void performAction(String action) {
	}

	public boolean generateRefreshValue(PrintWriter writer, String prefix) {
		writer.print(prefix);
		writer.print('"');
		writer.print(name);
		writer.print("\": \"");
		writer.print(getPrintable());
		writer.print('"');
		return true;
	}

	// Private
	protected String getPrintable() {
		if (scaleFactor == NO_SCALE_FACTOR)
			return Integer.toString(value);
		return Double.toString(1.0 * value / scaleFactor);
	}
}

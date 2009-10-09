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

public class BooleanActuator extends SimulatedParameter {
	protected boolean value;

	public BooleanActuator(String name, String label) {
		super(name, label);
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public void generate(HtmlGenerator gen) {
		gen.replace("actuatorName", name);
		gen.replace("actuatorLabel", label);
		gen.replace("actuatorValue", getPrintable());
		gen.write("/templates/BooleanActuator.template", getClass());
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
		return value ? "Y" : "N";
	}
}

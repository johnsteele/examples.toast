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

import org.eclipse.examples.toast.devsim.IDeviceSimulatorListener;

public class NonRepeatableActionSensor extends SimulatedParameter {
	private static final String EXECUTE_ACTION = "execute"; //$NON-NLS-1$
	protected String actionName;
	protected IDeviceSimulatorListener listener;

	public NonRepeatableActionSensor(String name, String label, String commandName, IDeviceSimulatorListener listener) {
		super(name, label);
		this.actionName = commandName;
		this.listener = listener;
	}

	public void generate(HtmlGenerator gen) {
		gen.replace("sensorName", name);
		gen.replace("sensorLabel", label);
		gen.write("/templates/NonRepeatableActionSensor.template", getClass());
	}

	public void performAction(String action) {
		if (EXECUTE_ACTION.equals(action)) {
			executeOwnerAction();
		}
	}

	// Private
	protected void executeOwnerAction() {
		if (listener == null)
			return;
		listener.performAction(name, actionName);
	}
}

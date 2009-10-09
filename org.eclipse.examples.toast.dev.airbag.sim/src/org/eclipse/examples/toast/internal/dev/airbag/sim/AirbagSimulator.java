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
package org.eclipse.examples.toast.internal.dev.airbag.sim;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.examples.toast.dev.airbag.IAirbag;
import org.eclipse.examples.toast.dev.airbag.IAirbagListener;
import org.eclipse.examples.toast.devsim.IDeviceSimulator;
import org.eclipse.examples.toast.devsim.IDeviceSimulatorListener;

public class AirbagSimulator implements IAirbag, IDeviceSimulatorListener {
	private static final String DEVICE_NAME = "airbag";
	private static final String DEVICE_LABEL = "Airbag";
	private static final String DEPLOY_NAME = "deploy";
	private List listeners;

	public void setDevSim(IDeviceSimulator simulator) {
		listeners = new ArrayList(3);
		simulator.registerDevice(DEVICE_NAME, DEVICE_LABEL, this);
		simulator.addRepeatableActionSensor(DEVICE_NAME, DEPLOY_NAME, "Deploy", DEPLOY_NAME);
	}

	public void clearDevSim(IDeviceSimulator simulator) {
		simulator.unregisterDevice(DEVICE_NAME);
		simulator = null;
	}

	// ISampleDevice implementation
	public void addListener(IAirbagListener listener) {
		listeners.add(listener);
	}

	public void removeListener(IAirbagListener listener) {
		listeners.remove(listener);
	}

	// IDeviceSimulatorListener implementation
	public void performAction(String parameterName, String actionName) {
		if (DEPLOY_NAME.equals(parameterName) && DEPLOY_NAME.equals(actionName))
			airbagDeployed();
	}

	public void valueChanged(String name, int newValue) {
	}

	public void valueChanged(String name, boolean newValue) {
	}

	// Private
	private void airbagDeployed() {
		for (Iterator i = listeners.iterator(); i.hasNext();) {
			IAirbagListener listener = (IAirbagListener) i.next();
			listener.deployed();
		}
	}
}

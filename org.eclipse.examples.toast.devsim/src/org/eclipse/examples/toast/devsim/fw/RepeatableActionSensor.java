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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.examples.toast.devsim.IDeviceSimulatorListener;

public class RepeatableActionSensor extends SimulatedParameter {
	private static final String STOP_ACTION = "stop"; //$NON-NLS-1$
	private static final String RUN_ACTION = "run"; //$NON-NLS-1$
	private static final String EXECUTE_ACTION = "execute"; //$NON-NLS-1$
	private static final long RUN_DELAY = 5000;
	protected String actionName;
	protected IDeviceSimulatorListener listener;
	protected Job job;

	public RepeatableActionSensor(String name, String label, String actionName, IDeviceSimulatorListener listener) {
		super(name, label);
		this.actionName = actionName;
		this.listener = listener;
	}

	public void generate(HtmlGenerator gen) {
		gen.replace("sensorName", name);
		gen.replace("sensorLabel", label);
		gen.write("/templates/RepeatableActionSensor.template", getClass());
	}

	public void performAction(String action) {
		if (EXECUTE_ACTION.equals(action)) {
			stopRunning();
			executeOwnerAction();
		} else if (STOP_ACTION.equals(action))
			stopRunning();
		else if (RUN_ACTION.equals(action))
			startRunning();
	}

	public void run() {
		startRunning();
	}

	public void resetToDefault() {
		stopRunning();
	}

	// Private
	protected synchronized void startRunning() {
		if (job != null)
			return;
		job = new Job(name) {
			protected IStatus run(IProgressMonitor monitor) {
				executeOwnerAction();
				schedule(RUN_DELAY);
				return Status.OK_STATUS;
			}
		};
		job.schedule(RUN_DELAY);
	}

	protected synchronized void stopRunning() {
		if (job == null)
			return;
		job.cancel();
		try {
			job.join();
		} catch (InterruptedException e) {
			// shutting down, ok to ignore
		}
		job = null;
	}

	protected void executeOwnerAction() {
		if (listener == null)
			return;
		listener.performAction(name, actionName);
	}
}

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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.devsim.IDeviceSimulatorListener;

public class BooleanSensor extends SimulatedParameter {
	private static final String DEFAULT_ACTION = "def"; //$NON-NLS-1$
	private static final String YES_ACTION = "yes"; //$NON-NLS-1$
	private static final String NO_ACTION = "no"; //$NON-NLS-1$
	private static final String RUN_ACTION = "run"; //$NON-NLS-1$
	private static final String STOP_ACTION = "stop"; //$NON-NLS-1$
	private static final long RUN_DELAY = 1000;
	protected boolean defaultValue;
	protected boolean value;
	protected Job job;
	private IDeviceSimulatorListener listener;

	public BooleanSensor(String name, String label, boolean defaultValue, IDeviceSimulatorListener listener) {
		super(name, label);
		this.defaultValue = defaultValue;
		this.listener = listener;
		value = defaultValue;
	}

	public boolean getValue() {
		return value;
	}

	public void generate(HtmlGenerator gen) {
		gen.replace("sensorName", name);
		gen.replace("sensorLabel", label);
		gen.replace("sensorValue", getPrintable());
		gen.write("/templates/BooleanSensor.template", getClass());
	}

	public void performAction(String action) {
		if (YES_ACTION.equals(action))
			setToTrue();
		else if (NO_ACTION.equals(action))
			setToFalse();
		else if (DEFAULT_ACTION.equals(action))
			setToDefault();
		else if (RUN_ACTION.equals(action))
			startRunning();
		else if (STOP_ACTION.equals(action))
			stopRunning();
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

	public void run() {
		startRunning();
	}

	public void resetToDefault() {
		setToDefault();
	}

	// Private
	protected void setValue(boolean value) {
		this.value = value;
		if (listener != null)
			listener.valueChanged(name, value);
		LogUtility.logDebug(label, String.valueOf(getPrintable()));
	}

	protected void setToDefault() {
		stopRunning();
		setValue(defaultValue);
	}

	protected void setToTrue() {
		stopRunning();
		setValue(true);
	}

	protected void setToFalse() {
		stopRunning();
		setValue(false);
	}

	protected synchronized void startRunning() {
		if (job != null)
			return;
		setValue(defaultValue);
		Job job = new Job(name) {
			protected IStatus run(IProgressMonitor monitor) {
				setValue(!value);
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

	protected String getPrintable() {
		return value ? "Y" : "N";
	}
}

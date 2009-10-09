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

public class IntegerSensor extends SimulatedParameter {
	private static final String MIN_ACTION = "min"; //$NON-NLS-1$
	private static final String LARGE_DEC_ACTION = "bigdec"; //$NON-NLS-1$
	private static final String SMALL_DEC_ACTION = "dec"; //$NON-NLS-1$
	private static final String DEFAULT_ACTION = "def"; //$NON-NLS-1$
	private static final String SMALL_INC_ACTION = "inc"; //$NON-NLS-1$
	private static final String LARGE_INC_ACTION = "biginc"; //$NON-NLS-1$
	private static final String MAX_ACTION = "max"; //$NON-NLS-1$
	private static final String RUN_ACTION = "run"; //$NON-NLS-1$
	private static final String STOP_ACTION = "stop"; //$NON-NLS-1$
	private static final int NO_SCALE_FACTOR = 0;
	private static final long RUN_DELAY = 1000;
	protected int minValue;
	protected int maxValue;
	protected int defaultValue;
	protected int smallIncrement;
	protected int largeIncrement;
	protected int value;
	protected String units;
	protected int scaleFactor = NO_SCALE_FACTOR;
	protected Job job;
	protected int runningStep;
	private IDeviceSimulatorListener listener;

	public IntegerSensor(String name, String label, int minValue, int maxValue, int defaultValue, int smallIncrement, int largeIncrement, String units, IDeviceSimulatorListener listener) {
		super(name, label);
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.defaultValue = defaultValue;
		this.smallIncrement = smallIncrement;
		this.largeIncrement = largeIncrement;
		this.units = units;
		this.listener = listener;
		value = defaultValue;
	}

	public IntegerSensor(String name, String label, int scaleFactor, int minValue, int maxValue, int defaultValue, int smallIncrement, int largeIncrement, String units, IDeviceSimulatorListener listener) {
		this(name, label, minValue, maxValue, defaultValue, smallIncrement, largeIncrement, units, listener);
		this.scaleFactor = scaleFactor;
	}

	public void generate(HtmlGenerator gen) {
		gen.replace("sensorName", name);
		gen.replace("sensorLabel", label);
		gen.replace("sensorValue", getPrintable());
		gen.replace("units", units);
		gen.write("/templates/IntegerSensor.template", getClass());
	}

	public void performAction(String action) {
		if (MIN_ACTION.equals(action))
			setToMin();
		else if (LARGE_DEC_ACTION.equals(action))
			decrementLarge();
		else if (SMALL_DEC_ACTION.equals(action))
			decrementSmall();
		else if (DEFAULT_ACTION.equals(action))
			setToDefault();
		else if (SMALL_INC_ACTION.equals(action))
			incrementSmall();
		else if (LARGE_INC_ACTION.equals(action))
			incrementLarge();
		else if (MAX_ACTION.equals(action))
			setToMax();
		else if (RUN_ACTION.equals(action))
			startRunning();
		else if (STOP_ACTION.equals(action))
			stopRunning();
	}

	public int getValue() {
		return value;
	}

	public void run() {
		startRunning();
	}

	public void resetToDefault() {
		setToDefault();
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
	protected void setValue(int value) {
		this.value = value;
		if (listener != null)
			listener.valueChanged(name, value);
		LogUtility.logDebug(label, getPrintable());
	}

	protected void setToMin() {
		stopRunning();
		setValue(minValue);
	}

	protected void setToMax() {
		stopRunning();
		setValue(maxValue);
	}

	protected void setToDefault() {
		stopRunning();
		setValue(defaultValue);
	}

	protected void decrementLarge() {
		stopRunning();
		setValue(Math.max(value - largeIncrement, minValue));
	}

	protected void decrementSmall() {
		stopRunning();
		setValue(Math.max(value - smallIncrement, minValue));
	}

	protected void incrementLarge() {
		stopRunning();
		setValue(Math.min(value + largeIncrement, maxValue));
	}

	protected void incrementSmall() {
		stopRunning();
		setValue(Math.min(value + smallIncrement, maxValue));
	}

	protected synchronized void startRunning() {
		if (job != null)
			return;
		firstStep();
		job = new Job(name) {
			protected IStatus run(IProgressMonitor monitor) {
				nextStep();
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

	protected void firstStep() {
		runningStep = -1;
		nextStep();
	}

	protected void nextStep() {
		runningStep++;
		int range = maxValue - minValue;
		int increments = range / smallIncrement;
		int lengths = runningStep / increments;
		int remainder = runningStep % increments;
		if (lengths % 2 == 0) {
			// increasing
			setValue(minValue + remainder * smallIncrement);
		} else {
			// decreasing
			setValue(maxValue - remainder * smallIncrement);
		}
	}

	protected String getPrintable() {
		if (scaleFactor == NO_SCALE_FACTOR)
			return Integer.toString(value);
		return Double.toString(1.0 * value / scaleFactor);
	}
}

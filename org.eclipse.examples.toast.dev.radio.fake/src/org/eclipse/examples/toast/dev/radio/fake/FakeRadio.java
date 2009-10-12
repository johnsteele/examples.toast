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
package org.eclipse.examples.toast.dev.radio.fake;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.examples.toast.dev.radio.IRadio;
import org.eclipse.examples.toast.dev.radio.IRadioListener;

public class FakeRadio implements IRadio {
	private static final int DEFAULT_FREQUENCY = 915;
	private static final int MAX_FREQUENCY = 1059;
	private static final int MIN_FREQUENCY = 881;
	private static final int FREQUENCY_INCREMENT = 2;
	private static final int[] FAKE_SIGNALS = {885, 891, 903, 915, 923, 933, 947, 957, 961, 977, 981, 993, 1001, 1025, 1041, 1057};
	private static final int[] DEFAULT_PRESETS = {885, 915, 957, 969, 981, 1013, 1041, 1057};
	private List listeners;
	private int frequency = DEFAULT_FREQUENCY;

	public FakeRadio() {
		super();
		listeners = new ArrayList(1);
	}

	public void addListener(IRadioListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	public void removeListener(IRadioListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	public int getMaxFrequency() {
		return MAX_FREQUENCY;
	}

	public int getMinFrequency() {
		return MIN_FREQUENCY;
	}

	public void frequencyUp() {
		if (frequency < MAX_FREQUENCY) {
			frequency += FREQUENCY_INCREMENT;
		} else {
			frequency = MIN_FREQUENCY;
		}
		notifyFrequencyChanged();
	}

	public void frequencyDown() {
		if (frequency > MIN_FREQUENCY) {
			frequency -= FREQUENCY_INCREMENT;
		} else {
			frequency = MAX_FREQUENCY;
		}
		notifyFrequencyChanged();
	}

	public void setFrequency(int frequency) {
		if (frequency != this.frequency) {
			this.frequency = frequency;
			notifyFrequencyChanged();
		}
	}

	public int getFrequency() {
		return frequency;
	}

	public void seekUp() {
		int[] fakeSignals = FAKE_SIGNALS;
		for (int i = 0; i < fakeSignals.length; i++) {
			if (fakeSignals[i] > frequency) {
				setFrequency(fakeSignals[i]);
				return;
			}
		}
		setFrequency(fakeSignals[0]);
	}

	public void seekDown() {
		for (int i = FAKE_SIGNALS.length - 1; i >= 0; i--) {
			if (FAKE_SIGNALS[i] < frequency) {
				setFrequency(FAKE_SIGNALS[i]);
				return;
			}
		}
		setFrequency(FAKE_SIGNALS[FAKE_SIGNALS.length - 1]);
	}

	public int getPresetCount() {
		return DEFAULT_PRESETS.length;
	}

	public int getPreset(int presetIndex) {
		return DEFAULT_PRESETS[presetIndex];
	}

	public void tuneToPreset(int presetIndex) {
		if (DEFAULT_PRESETS[presetIndex] != frequency) {
			setFrequency(DEFAULT_PRESETS[presetIndex]);
		}
	}

	private void notifyFrequencyChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IRadioListener listener = (IRadioListener) iterator.next();
				listener.frequencyChanged(frequency);
			}
		}
	}
}

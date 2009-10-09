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
import org.eclipse.examples.toast.dev.radio.IAbstractRadio;
import org.eclipse.examples.toast.dev.radio.IRadioListener;

public abstract class AbstractFakeRadio implements IAbstractRadio {
	private List listeners;
	private int frequency = getDefaultFrequency();
	private static final int PRESET_COUNT = 8;
	private int[] presets = getDefaultPresets();

	protected AbstractFakeRadio() {
		super();
		listeners = new ArrayList(1);
	}

	// Subclass responsibilities
	protected abstract int getDefaultFrequency();

	protected abstract int[] getDefaultPresets();

	protected abstract int getFrequencyIncrement();

	protected abstract int[] getFakeSignals();

	// IAbstractRadioService implementation
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

	// Frequency
	public void frequencyUp() {
		if (frequency < getMaxFrequency()) {
			frequency += getFrequencyIncrement();
		} else {
			frequency = getMinFrequency();
		}
		notifyFrequencyChanged();
	}

	public void frequencyDown() {
		if (frequency > getMinFrequency()) {
			frequency -= getFrequencyIncrement();
		} else {
			frequency = getMaxFrequency();
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

	// Seek
	public void seekUp() {
		int[] fakeSignals = getFakeSignals();
		for (int i = 0; i < fakeSignals.length; i++) {
			if (fakeSignals[i] > frequency) {
				setFrequency(fakeSignals[i]);
				return;
			}
		}
		setFrequency(fakeSignals[0]);
	}

	public void seekDown() {
		int[] fakeSignals = getFakeSignals();
		for (int i = fakeSignals.length - 1; i >= 0; i--) {
			if (fakeSignals[i] < frequency) {
				setFrequency(fakeSignals[i]);
				return;
			}
		}
		setFrequency(fakeSignals[fakeSignals.length - 1]);
	}

	// Presets (always 0-based indexed)
	public int getPresetCount() {
		return AbstractFakeRadio.PRESET_COUNT;
	}

	public int getPreset(int presetIndex) {
		return presets[presetIndex];
	}

	public void setPreset(int presetIndex, int frequency) {
		if (presets[presetIndex] != frequency) {
			presets[presetIndex] = frequency;
			notifyPresetChanged(presetIndex);
		}
	}

	public void tuneToPreset(int presetIndex) {
		if (presets[presetIndex] != frequency) {
			setFrequency(presets[presetIndex]);
		}
	}

	// Private
	private void notifyFrequencyChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IRadioListener listener = (IRadioListener) iterator.next();
				listener.frequencyChanged(frequency);
			}
		}
	}

	private void notifyPresetChanged(int presetIndex) {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IRadioListener listener = (IRadioListener) iterator.next();
				listener.presetChanged(presetIndex, presets[presetIndex]);
			}
		}
	}
}

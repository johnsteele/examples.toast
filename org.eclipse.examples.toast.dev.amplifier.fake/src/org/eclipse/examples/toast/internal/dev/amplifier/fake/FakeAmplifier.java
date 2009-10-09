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
package org.eclipse.examples.toast.internal.dev.amplifier.fake;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.examples.toast.dev.amplifier.IAmplifier;
import org.eclipse.examples.toast.dev.amplifier.IAmplifierListener;

public class FakeAmplifier implements IAmplifier {
	private List listeners;
	private int mode = IAmplifier.MODE_FM;
	private static final int MAX_VOLUME = 10;
	private static final int MIN_VOLUME = 0;
	private static final int DEFAULT_VOLUME = 4;
	private int volume = FakeAmplifier.DEFAULT_VOLUME;
	private boolean isMuted = false;
	private static final int MAX_BASS = 5;
	private static final int MIN_BASS = -5;
	private static final int DEFAULT_BASS = 0;
	private int bass = FakeAmplifier.DEFAULT_BASS;
	private static final int MAX_TREBLE = 5;
	private static final int MIN_TREBLE = -5;
	private static final int DEFAULT_TREBLE = 0;
	private int treble = FakeAmplifier.DEFAULT_TREBLE;
	private static final int MAX_BALANCE = 5; // right
	private static final int MIN_BALANCE = -5; // left
	private static final int DEFAULT_BALANCE = 0;
	private int balance = FakeAmplifier.DEFAULT_BALANCE;
	private static final int MAX_FADE = 5; // front
	private static final int MIN_FADE = -5; // rear
	private static final int DEFAULT_FADE = 0;
	private int fade = FakeAmplifier.DEFAULT_FADE;

	public FakeAmplifier() {
		super();
		listeners = new ArrayList(1);
	}

	// IAmplifier implementation
	public void addListener(IAmplifierListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	public void removeListener(IAmplifierListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	// Mode
	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		if (mode != this.mode) {
			this.mode = mode;
			notifyModeChanged();
		}
	}

	// Volume
	public int getMaxVolume() {
		return FakeAmplifier.MAX_VOLUME;
	}

	public int getMinVolume() {
		return FakeAmplifier.MIN_VOLUME;
	}

	public void volumeUp() {
		if (volume < FakeAmplifier.MAX_VOLUME) {
			volume += 1;
			notifyVolumeChanged();
		}
	}

	public void volumeDown() {
		if (volume > FakeAmplifier.MIN_VOLUME) {
			volume -= 1;
			notifyVolumeChanged();
		}
	}

	public void setVolume(int volume) {
		if (volume != this.volume) {
			this.volume = volume;
			notifyVolumeChanged();
		}
	}

	public int getVolume() {
		return volume;
	}

	// Mute
	public boolean isMuted() {
		return isMuted;
	}

	public void mute() {
		if (isMuted == false) {
			isMuted = true;
			notifyMuteChanged();
		}
	}

	public void unMute() {
		if (isMuted == true) {
			isMuted = false;
			notifyMuteChanged();
		}
	}

	public void toggleMute() {
		isMuted = !isMuted;
		notifyMuteChanged();
	}

	// Bass
	public int getMaxBass() {
		return FakeAmplifier.MAX_BASS;
	}

	public int getMinBass() {
		return FakeAmplifier.MIN_BASS;
	}

	public void bassUp() {
		if (bass < FakeAmplifier.MAX_BASS) {
			bass++;
			notifyBassChanged();
		}
	}

	public void bassDown() {
		if (bass > FakeAmplifier.MIN_BASS) {
			bass--;
			notifyBassChanged();
		}
	}

	public void setBass(int bass) {
		if (bass != this.bass) {
			this.bass = bass;
			notifyBassChanged();
		}
	}

	public int getBass() {
		return bass;
	}

	// Treble
	public int getMaxTreble() {
		return FakeAmplifier.MAX_TREBLE;
	}

	public int getMinTreble() {
		return FakeAmplifier.MIN_TREBLE;
	}

	public void trebleUp() {
		if (treble < FakeAmplifier.MAX_TREBLE) {
			treble++;
			notifyTrebleChanged();
		}
	}

	public void trebleDown() {
		if (treble > FakeAmplifier.MIN_TREBLE) {
			treble--;
			notifyTrebleChanged();
		}
	}

	public void setTreble(int treble) {
		if (treble != this.treble) {
			this.treble = treble;
			notifyTrebleChanged();
		}
	}

	public int getTreble() {
		return treble;
	}

	// Balance
	public int getMaxBalance() {
		return FakeAmplifier.MAX_BALANCE;
	}

	public int getMinBalance() {
		return FakeAmplifier.MIN_BALANCE;
	}

	public void balanceRight() {
		if (balance < FakeAmplifier.MAX_BALANCE) {
			balance++;
			notifyBalanceChanged();
		}
	}

	public void balanceLeft() {
		if (balance > FakeAmplifier.MIN_BALANCE) {
			balance--;
			notifyBalanceChanged();
		}
	}

	public void setBalance(int balance) {
		if (balance != this.balance) {
			this.balance = balance;
			notifyBalanceChanged();
		}
	}

	public int getBalance() {
		return balance;
	}

	// Fade
	public int getMaxFade() {
		return FakeAmplifier.MAX_FADE;
	}

	public int getMinFade() {
		return FakeAmplifier.MIN_FADE;
	}

	public void fadeFront() {
		if (fade < FakeAmplifier.MAX_FADE) {
			fade++;
			notifyFadeChanged();
		}
	}

	public void fadeBack() {
		if (fade > FakeAmplifier.MIN_FADE) {
			fade--;
			notifyFadeChanged();
		}
	}

	public void setFade(int fade) {
		if (fade != this.fade) {
			this.fade = fade;
			notifyFadeChanged();
		}
	}

	public int getFade() {
		return fade;
	}

	// Private
	private void notifyModeChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IAmplifierListener listener = (IAmplifierListener) iterator.next();
				listener.modeChanged(mode);
			}
		}
	}

	private void notifyVolumeChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IAmplifierListener listener = (IAmplifierListener) iterator.next();
				listener.volumeChanged(volume);
			}
		}
	}

	private void notifyBassChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IAmplifierListener listener = (IAmplifierListener) iterator.next();
				listener.bassChanged(bass);
			}
		}
	}

	private void notifyTrebleChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IAmplifierListener listener = (IAmplifierListener) iterator.next();
				listener.trebleChanged(treble);
			}
		}
	}

	private void notifyBalanceChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IAmplifierListener listener = (IAmplifierListener) iterator.next();
				listener.balanceChanged(balance);
			}
		}
	}

	private void notifyFadeChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IAmplifierListener listener = (IAmplifierListener) iterator.next();
				listener.fadeChanged(fade);
			}
		}
	}

	private void notifyMuteChanged() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				IAmplifierListener listener = (IAmplifierListener) iterator.next();
				listener.muteChanged(isMuted);
			}
		}
	}
}

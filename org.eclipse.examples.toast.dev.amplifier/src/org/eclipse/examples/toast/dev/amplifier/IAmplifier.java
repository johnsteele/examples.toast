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
package org.eclipse.examples.toast.dev.amplifier;

public interface IAmplifier {
	public static final int MODE_CD = 0;
	public static final int MODE_FM = 1;

	public void addListener(IAmplifierListener listener);

	public void removeListener(IAmplifierListener listener);

	// Mode
	public int getMode();

	public void setMode(int mode);

	// Volume
	public int getMaxVolume();

	public int getMinVolume();

	public void volumeUp();

	public void volumeDown();

	public void setVolume(int volume);

	public int getVolume();

	// Mute
	public boolean isMuted();

	public void mute();

	public void unMute();

	public void toggleMute();

	// Bass
	public int getMaxBass();

	public int getMinBass();

	public void bassUp();

	public void bassDown();

	public void setBass(int bass);

	public int getBass();

	// Treble
	public int getMaxTreble();

	public int getMinTreble();

	public void trebleUp();

	public void trebleDown();

	public void setTreble(int treble);

	public int getTreble();

	// Balance
	public int getMaxBalance();

	public int getMinBalance();

	public void balanceLeft();

	public void balanceRight();

	public void setBalance(int balance);

	public int getBalance();

	// Fade
	public int getMaxFade();

	public int getMinFade();

	public void fadeFront();

	public void fadeBack();

	public void setFade(int fade);

	public int getFade();
}

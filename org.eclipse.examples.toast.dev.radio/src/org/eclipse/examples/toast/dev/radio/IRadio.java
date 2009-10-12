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
package org.eclipse.examples.toast.dev.radio;

public interface IRadio {
	public void addListener(IRadioListener listener);

	public void removeListener(IRadioListener listener);

	// Frequency
	public int getMaxFrequency();

	public int getMinFrequency();

	public void frequencyUp();

	public void frequencyDown();

	public void setFrequency(int frequency);

	public int getFrequency();

	// Seek
	public void seekUp();

	public void seekDown();

	// Presets (always 0-based indexed)
	public int getPresetCount();

	public int getPreset(int presetIndex);

	public void tuneToPreset(int presetIndex);
}

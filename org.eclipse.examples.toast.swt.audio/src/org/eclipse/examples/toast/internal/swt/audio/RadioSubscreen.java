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
package org.eclipse.examples.toast.internal.swt.audio;

import org.eclipse.examples.toast.crust.shell.DisplayBlock;
import org.eclipse.examples.toast.crust.shell.ICrustShell;
import org.eclipse.examples.toast.crust.shell.ScaledWidgetFactory;
import org.eclipse.examples.toast.crust.widgets.ImageButton;
import org.eclipse.examples.toast.dev.radio.am.IAmRadio;
import org.eclipse.examples.toast.dev.radio.fm.IFmRadio;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class RadioSubscreen implements SelectionListener {
	private static final int PRESET_COUNT = 8;
	private static final int FM = 0;
	private static final int AM = 1;
	private static final String B1_DOWN_IMAGE = "artwork/radio/1Down.png"; //$NON-NLS-1$
	private static final String B1_UP_IMAGE = "artwork/radio/1Up.png"; //$NON-NLS-1$
	private static final String B2_DOWN_IMAGE = "artwork/radio/2Down.png"; //$NON-NLS-1$
	private static final String B2_UP_IMAGE = "artwork/radio/2Up.png"; //$NON-NLS-1$
	private static final String B3_DOWN_IMAGE = "artwork/radio/3Down.png"; //$NON-NLS-1$
	private static final String B3_UP_IMAGE = "artwork/radio/3Up.png"; //$NON-NLS-1$
	private static final String B4_DOWN_IMAGE = "artwork/radio/4Down.png"; //$NON-NLS-1$
	private static final String B4_UP_IMAGE = "artwork/radio/4Up.png"; //$NON-NLS-1$
	private static final String B5_DOWN_IMAGE = "artwork/radio/5Down.png"; //$NON-NLS-1$
	private static final String B5_UP_IMAGE = "artwork/radio/5Up.png"; //$NON-NLS-1$
	private static final String B6_DOWN_IMAGE = "artwork/radio/6Down.png"; //$NON-NLS-1$
	private static final String B6_UP_IMAGE = "artwork/radio/6Up.png"; //$NON-NLS-1$
	private static final String B7_DOWN_IMAGE = "artwork/radio/7Down.png"; //$NON-NLS-1$
	private static final String B7_UP_IMAGE = "artwork/radio/7Up.png"; //$NON-NLS-1$
	private static final String B8_DOWN_IMAGE = "artwork/radio/8Down.png"; //$NON-NLS-1$
	private static final String B8_UP_IMAGE = "artwork/radio/8Up.png"; //$NON-NLS-1$
	private static final String BAND_DOWN_IMAGE = "artwork/radio/BandDown.png"; //$NON-NLS-1$
	private static final String BAND_UP_IMAGE = "artwork/radio/BandUp.png"; //$NON-NLS-1$
	private static final String RADIO_PANEL_IMAGE = "artwork/radio/RadioPanel.png"; //$NON-NLS-1$
	private static final String SEEK_DEC_DOWN_IMAGE = "artwork/radio/SeekDecDown.png"; //$NON-NLS-1$
	private static final String SEEK_DEC_UP_IMAGE = "artwork/radio/SeekDecUp.png"; //$NON-NLS-1$
	private static final String SEEK_INC_DOWN_IMAGE = "artwork/radio/SeekIncDown.png"; //$NON-NLS-1$
	private static final String SEEK_INC_UP_IMAGE = "artwork/radio/SeekIncUp.png"; //$NON-NLS-1$
	private static final String TUNE_DEC_DOWN_IMAGE = "artwork/radio/TuneDecDown.png"; //$NON-NLS-1$
	private static final String TUNE_DEC_UP_IMAGE = "artwork/radio/TuneDecUp.png"; //$NON-NLS-1$
	private static final String TUNE_INC_DOWN_IMAGE = "artwork/radio/TuneIncDown.png"; //$NON-NLS-1$
	private static final String TUNE_INC_UP_IMAGE = "artwork/radio/TuneIncUp.png"; //$NON-NLS-1$
	private static final int REFERENCE_WIDTH = 425;
	private static final int REFERENCE_HEIGHT = 400;
	private ICrustShell crustShell;
	private AmRadioController amController;
	private FmRadioController fmController;
	private int currentBand;
	private ScaledWidgetFactory f;
	private Composite screenComposite;
	private ImageButton seekUpButton, seekDownButton, tuneUpButton, tuneDownButton, bandButton;
	private ImageButton[] presetButtons;
	private Label frequencyLabel, bandLabel;

	public RadioSubscreen(Composite screenComposite) {
		this.screenComposite = screenComposite;
		currentBand = FM;
		presetButtons = new ImageButton[PRESET_COUNT];
	}

	public void bind(ICrustShell crustShell, IFmRadio fm, IAmRadio am) {
		this.crustShell = crustShell;
		amController = new AmRadioController();
		amController.bindDevice(am);
		fmController = new FmRadioController();
		fmController.bindDevice(fm);
		new DisplayBlock() {
			public void run() {
				populateScreenComposite();
			}
		}.sync();
	}

	public void unbind() {
		new DisplayBlock() {
			public void run() {
				unpopulateScreenComposite();
			}
		}.sync();
		if (amController != null) {
			amController.unbind();
			amController = null;
		}
		if (fmController != null) {
			fmController.unbind();
			fmController = null;
		}
	}

	// SelectionListener implementation
	public void widgetDefaultSelected(SelectionEvent arg0) {
	}

	public void widgetSelected(SelectionEvent arg0) {
		Object href = arg0.getSource();
		if (tuneDownButton.equals(href)) { //$NON-NLS-1$
			frequencyDown();
		} else if (tuneUpButton.equals(href)) { //$NON-NLS-1$
			frequencyUp();
		} else if (seekDownButton.equals(href)) { //$NON-NLS-1$
			seekDown();
		} else if (seekUpButton.equals(href)) { //$NON-NLS-1$
			seekUp();
		} else if (bandButton.equals(href)) { //$NON-NLS-1$
			changeBand();
			//		} else if (href.startsWith("preset:")) { //$NON-NLS-1$
			// preset(Integer.parseInt(href.substring(7)));
			//		} else if (href.startsWith("setpreset:")) { //$NON-NLS-1$
			// setPreset(Integer.parseInt(href.substring(10)));
		} else {// its own of the preset buttons
			for (int i = 0; i < PRESET_COUNT; i++) {
				if (presetButtons[i].equals(href)) {
					preset(i);
					break;
				}
			}
		}
	}

	// Private
	private void populateScreenComposite() {
		f = new ScaledWidgetFactory(this.getClass(), screenComposite.getSize(), REFERENCE_HEIGHT, REFERENCE_WIDTH);
		screenComposite.setBackgroundImage(f.getSizedImageForWidget(RADIO_PANEL_IMAGE, screenComposite));
		screenComposite.setBackgroundMode(SWT.INHERIT_FORCE);
		tuneDownButton = f.createImageButton(screenComposite, SWT.PUSH, 54, 127, 65, 75, TUNE_DEC_UP_IMAGE, TUNE_DEC_DOWN_IMAGE, true, this);
		tuneUpButton = f.createImageButton(screenComposite, SWT.PUSH, 53, 53, 65, 75, TUNE_INC_UP_IMAGE, TUNE_INC_DOWN_IMAGE, true, this);
		bandButton = f.createImageButton(screenComposite, SWT.PUSH, 121, 168, 182, 33, BAND_UP_IMAGE, BAND_DOWN_IMAGE, false, this);
		seekDownButton = f.createImageButton(screenComposite, SWT.PUSH, 305, 127, 65, 75, SEEK_DEC_UP_IMAGE, SEEK_DEC_DOWN_IMAGE, true, this);
		seekUpButton = f.createImageButton(screenComposite, SWT.PUSH, 305, 52, 65, 75, SEEK_INC_UP_IMAGE, SEEK_INC_DOWN_IMAGE, true, this);
		Font font = crustShell.getFont(ICrustShell.FONT_H3);
		presetButtons[0] = f.createImageButton(screenComposite, SWT.PUSH, 44, 256, 88, 60, B1_UP_IMAGE, B1_DOWN_IMAGE, true, this, font);
		presetButtons[1] = f.createImageButton(screenComposite, SWT.PUSH, 132, 256, 79, 60, B2_UP_IMAGE, B2_DOWN_IMAGE, true, this, font);
		presetButtons[2] = f.createImageButton(screenComposite, SWT.PUSH, 211, 256, 79, 60, B3_UP_IMAGE, B3_DOWN_IMAGE, true, this, font);
		presetButtons[3] = f.createImageButton(screenComposite, SWT.PUSH, 291, 256, 88, 60, B4_UP_IMAGE, B4_DOWN_IMAGE, true, this, font);
		presetButtons[4] = f.createImageButton(screenComposite, SWT.PUSH, 44, 317, 88, 60, B5_UP_IMAGE, B5_DOWN_IMAGE, true, this, font);
		presetButtons[5] = f.createImageButton(screenComposite, SWT.PUSH, 132, 317, 79, 60, B6_UP_IMAGE, B6_DOWN_IMAGE, true, this, font);
		presetButtons[6] = f.createImageButton(screenComposite, SWT.PUSH, 211, 317, 79, 60, B7_UP_IMAGE, B7_DOWN_IMAGE, true, this, font);
		presetButtons[7] = f.createImageButton(screenComposite, SWT.PUSH, 291, 317, 88, 60, B8_UP_IMAGE, B8_DOWN_IMAGE, true, this, font);
		frequencyLabel = f.createLabel(screenComposite, SWT.CENTER, 131, 87, 165, 45, crustShell.getFont(ICrustShell.FONT_H1));
		bandLabel = f.createLabel(screenComposite, SWT.RIGHT | SWT.BOTTOM, 261, 129, 30, 30, font);
		updateWidgetsFromModel();
	}

	private void unpopulateScreenComposite() {
		f.disposeLabelImage(bandLabel);
		f.disposeLabelImage(frequencyLabel);
		f.disposeImageButtonImages(presetButtons[7]);
		f.disposeImageButtonImages(presetButtons[6]);
		f.disposeImageButtonImages(presetButtons[5]);
		f.disposeImageButtonImages(presetButtons[4]);
		f.disposeImageButtonImages(presetButtons[3]);
		f.disposeImageButtonImages(presetButtons[2]);
		f.disposeImageButtonImages(presetButtons[1]);
		f.disposeImageButtonImages(presetButtons[0]);
		f.disposeImageButtonImages(seekUpButton);
		f.disposeImageButtonImages(seekDownButton);
		f.disposeImageButtonImages(bandButton);
		f.disposeImageButtonImages(tuneUpButton);
		f.disposeImageButtonImages(tuneDownButton);
		f.disposeCompositeImage(screenComposite);
		f = null;
	}

	// screen events
	private void updateWidgetsFromModel() {
		amController.bindElements(frequencyLabel, presetButtons);
		fmController.bindElements(frequencyLabel, presetButtons);
		updateCurrentBand();
	}

	private void updateCurrentBand() {
		if (currentBand == FM) {
			if (bandLabel != null) {
				bandLabel.setText("FM"); //$NON-NLS-1$
				bandLabel.redraw();
			}
			amController.setDisplayed(false);
			fmController.setDisplayed(true);
		} else {
			if (bandLabel != null) {
				bandLabel.setText("AM"); //$NON-NLS-1$
				bandLabel.redraw();
			}
			fmController.setDisplayed(false);
			amController.setDisplayed(true);
		}
	}

	private void preset(int presetIndex) {
		if (currentBand == FM) {
			fmController.tuneToPreset(presetIndex);
		} else {
			amController.tuneToPreset(presetIndex);
		}
	}

	private void frequencyDown() {
		if (currentBand == FM) {
			fmController.frequencyDown();
		} else {
			amController.frequencyDown();
		}
	}

	private void frequencyUp() {
		if (currentBand == FM) {
			fmController.frequencyUp();
		} else {
			amController.frequencyUp();
		}
	}

	private void seekDown() {
		if (currentBand == FM) {
			fmController.seekDown();
		} else {
			amController.seekDown();
		}
	}

	private void seekUp() {
		if (currentBand == FM) {
			fmController.seekUp();
		} else {
			amController.seekUp();
		}
	}

	private void changeBand() {
		if (currentBand == FM) {
			currentBand = AM;
		} else {
			currentBand = FM;
		}
		updateCurrentBand();
	}
}

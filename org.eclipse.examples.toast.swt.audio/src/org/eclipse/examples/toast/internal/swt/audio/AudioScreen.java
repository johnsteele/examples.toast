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

import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.crust.shell.DisplayBlock;
import org.eclipse.examples.toast.crust.shell.ICrustScreenListener;
import org.eclipse.examples.toast.crust.shell.ICrustShell;
import org.eclipse.examples.toast.crust.shell.ScaledWidgetFactory;
import org.eclipse.examples.toast.crust.widgets.ImageButton;
import org.eclipse.examples.toast.crust.widgets.ImageProgressBar;
import org.eclipse.examples.toast.crust.widgets.ImageSlider;
import org.eclipse.examples.toast.dev.amplifier.IAmplifier;
import org.eclipse.examples.toast.dev.amplifier.IAmplifierListener;
import org.eclipse.examples.toast.dev.cdplayer.ICdPlayer;
import org.eclipse.examples.toast.dev.radio.IRadio;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class AudioScreen implements IAmplifierListener, SelectionListener, ICrustScreenListener {
	private static final int SLOT = 5;
	private static final String TOPBAR_ICON_ON_IMAGE = "artwork/NoteOn.png"; //$NON-NLS-1$
	private static final String TOPBAR_ICON_OFF_IMAGE = "artwork/NoteOff.png"; //$NON-NLS-1$
	private static final int[] VOLUME_BAR_POSITIONS = {0, 17, 28, 39, 50, 61, 72, 83, 94, 105, 120};
	private static final String AUDIO_PANEL_IMAGE = "artwork/AudioPanel.png"; //$NON-NLS-1$
	private static final String DOWN_DOWN_IMAGE = "artwork/DownDown.png"; //$NON-NLS-1$
	private static final String DOWN_UP_IMAGE = "artwork/DownUp.png"; //$NON-NLS-1$
	private static final String LEFT_DOWN_IMAGE = "artwork/LeftDown.png"; //$NON-NLS-1$
	private static final String LEFT_UP_IMAGE = "artwork/LeftUp.png"; //$NON-NLS-1$
	private static final String MUTE_DEPRESSED_IMAGE = "artwork/MuteDownOff.png"; //$NON-NLS-1$
	private static final String MUTE_ON_IMAGE = "artwork/MuteDownOn.png"; //$NON-NLS-1$
	private static final String MUTE_OFF_IMAGE = "artwork/MuteUpOff.png"; //$NON-NLS-1$
	private static final String RIGHT_DOWN_IMAGE = "artwork/RightDown.png"; //$NON-NLS-1$
	private static final String RIGHT_UP_IMAGE = "artwork/RightUp.png"; //$NON-NLS-1$
	private static final String CD_DOWN_OFF_IMAGE = "artwork/CdDownOff.png"; //$NON-NLS-1$
	private static final String CD_DOWN_ON_IMAGE = "artwork/CdDownOn.png"; //$NON-NLS-1$
	private static final String CD_UP_OFF_IMAGE = "artwork/CdUpOff.png"; //$NON-NLS-1$
	private static final String RADIO_DOWN_OFF_IMAGE = "artwork/RadioDownOff.png"; //$NON-NLS-1$
	private static final String RADIO_DOWN_ON_IMAGE = "artwork/RadioDownOn.png"; //$NON-NLS-1$
	private static final String RADIO_UP_OFF_IMAGE = "artwork/RadioUpOff.png"; //$NON-NLS-1$
	private static final String SLIDER_HORIZ_IMAGE = "artwork/SliderHoriz.png"; //$NON-NLS-1$
	private static final String SLIDER_VERT_IMAGE = "artwork/SliderVert.png"; //$NON-NLS-1$
	private static final String SLIDER_HORIZ_BAR_IMAGE = "artwork/SliderHorizBar.png"; //$NON-NLS-1$
	private static final String SLIDER_VERT_BAR_IMAGE = "artwork/SliderVertBar.png"; //$NON-NLS-1$
	private static final String HORIZ_SLIDER_BACKGROUND_IMAGE = "artwork/HorzSliderBackground.png"; //$NON-NLS-1$
	private static final String VERT_SLIDER_BACKGROUND_IMAGE = "artwork/VertSliderBackground.png"; //$NON-NLS-1$
	private static final String UP_DOWN_IMAGE = "artwork/UpDown.png"; //$NON-NLS-1$
	private static final String UP_UP_IMAGE = "artwork/UpUp.png"; //$NON-NLS-1$
	private static final String VOLUME_BAR_IMAGE = "artwork/VolumeBar.png"; //$NON-NLS-1$
	private static final String VOLUME_DEC_DOWN_IMAGE = "artwork/VolumeDecDown.png"; //$NON-NLS-1$
	private static final String VOLUME_DEC_UP_IMAGE = "artwork/VolumeDecUp.png"; //$NON-NLS-1$
	private static final String VOLUME_INC_DOWN_IMAGE = "artwork/VolumeIncDown.png"; //$NON-NLS-1$
	private static final String VOLUME_INC_UP_IMAGE = "artwork/VolumeIncUp.png"; //$NON-NLS-1$
	private static final String BALANCE_ON_IMAGE = "artwork/BalanceOn.png"; //$NON-NLS-1$
	private static final String BALANCE_OFF_IMAGE = "artwork/BalanceOff.png"; //$NON-NLS-1$
	private static final String FADE_ON_IMAGE = "artwork/FadeOn.png"; //$NON-NLS-1$
	private static final String FADE_OFF_IMAGE = "artwork/FadeOff.png"; //$NON-NLS-1$
	private static final String TREBLE_ON_IMAGE = "artwork/TrebleOn.png"; //$NON-NLS-1$
	private static final String TREBLE_OFF_IMAGE = "artwork/TrebleOff.png"; //$NON-NLS-1$
	private static final String BASS_ON_IMAGE = "artwork/BassOn.png"; //$NON-NLS-1$
	private static final String BASS_OFF_IMAGE = "artwork/BassOff.png"; //$NON-NLS-1$
	private static final int REFERENCE_WIDTH = 640;
	private static final int REFERENCE_HEIGHT = 400;
	private ICrustShell crustShell;
	private IAmplifier amplifier;
	private ScaledWidgetFactory f;
	private RadioSubscreen radioSubscreen;
	private CdSubscreen cdSubscreen;
	private Composite screenComposite, controlComposite, audioComposite, cdComposite, radioComposite;
	private StackLayout audioStackLayout;
	private ImageButton cdButton;
	private ImageButton radioButton;
	private ImageProgressBar volumeBar;
	private ImageButton volumeUpButton, volumeDownButton;
	private ImageButton muteButton;
	private Composite adjustStackComposite;
	private StackLayout adjustStackLayout;
	private Composite trebleComposite, bassComposite, balanceComposite, fadeComposite;
	private ImageSlider trebleSlider;
	private ImageButton trebleButton, trebleUpButton, trebleDownButton;
	private ImageSlider bassSlider;
	private ImageButton bassButton, bassUpButton, bassDownButton;
	private ImageSlider balanceSlider;
	private ImageButton balanceButton, balanceLeftButton, balanceRightButton;
	private ImageSlider fadeSlider;
	private ImageButton fadeButton, fadeFrontButton, fadeBackButton;
	private IRadio radio;
	private ICdPlayer cd;

	public void setShell(ICrustShell value) {
		crustShell = value;
	}

	public void setAmp(IAmplifier value) {
		amplifier = value;
	}

	public void setRadio(IRadio value) {
		radio = value;
	}

	public void setCd(ICdPlayer value) {
		cd = value;
	}

	public void startup() {
		screenComposite = crustShell.installScreen(SLOT, this.getClass(), TOPBAR_ICON_OFF_IMAGE, TOPBAR_ICON_ON_IMAGE, null, this);
		new DisplayBlock() {
			public void run() {
				populateScreenComposite();
			}
		}.sync();
		amplifier.addListener(this);
		radioSubscreen = new RadioSubscreen(radioComposite);
		radioSubscreen.bind(crustShell, radio);
		cdSubscreen = new CdSubscreen(cdComposite);
		cdSubscreen.bind(crustShell, cd);
		updateWidgetsFromModel();
	}

	public void shutdown() {
		crustShell.deactivateScreen(SLOT, this);
		cdSubscreen.unbind();
		radioSubscreen.unbind();
		amplifier.removeListener(this);
		new DisplayBlock() {
			public void run() {
				unpopulateScreenComposite();
			}
		}.sync();
		crustShell.uninstallScreen(SLOT, this);
	}

	// IAmplifierListener implementation
	public void balanceChanged(int balance) {
		updateBalance();
	}

	public void bassChanged(int bass) {
		updateBass();
	}

	public void fadeChanged(int fade) {
		updateFade();
	}

	public void modeChanged(int mode) {
		updateDeviceFrame();
	}

	public void trebleChanged(int treble) {
		updateTreble();
	}

	public void volumeChanged(int volume) {
		updateVolume();
	}

	public void muteChanged(boolean isMuted) {
		updateMute();
	}

	// Private
	// screen events
	private void updateWidgetsFromModel() {
		updateVolume();
		updateBass();
		updateTreble();
		updateBalance();
		updateFade();
		updateDeviceFrame();
	}

	// update screen from model
	private void updateDeviceFrame() {
		new DisplayBlock() {
			public void run() {
				if (amplifier.getMode() == IAmplifier.MODE_CD) {
					radioButton.setSelection(false);
					cdButton.setSelection(true);
					audioStackLayout.topControl = cdComposite;
					audioComposite.layout();
				} else {
					cdButton.setSelection(false);
					radioButton.setSelection(true);
					audioStackLayout.topControl = radioComposite;
					audioComposite.layout();
				}
			}
		}.sync();
	}

	private void updateAdjuster(final Composite composite) {
		new DisplayBlock() {
			public void run() {
				adjustStackLayout.topControl = composite;
				adjustStackComposite.layout();
			}
		}.sync();
	}

	private void updateVolume() {
		int length = VOLUME_BAR_POSITIONS.length - 1;
		final int index = (amplifier.getVolume() * length) / amplifier.getMaxVolume();
		new DisplayBlock() {
			public void run() {
				volumeBar.setSelection(VOLUME_BAR_POSITIONS[index]);
			}
		}.sync();
	}

	private void updateMute() {
		final boolean isMuted = amplifier.isMuted();
		new DisplayBlock() {
			public void run() {
				muteButton.setSelection(isMuted);
			}
		}.sync();
	}

	private void updateTreble() {
		new DisplayBlock() {
			public void run() {
				trebleSlider.setSelection((amplifier.getTreble() - amplifier.getMinTreble()) * 100 / (amplifier.getMaxTreble() - amplifier.getMinTreble()));
			}
		}.sync();
	}

	private void updateBalance() {
		new DisplayBlock() {
			public void run() {
				balanceSlider.setSelection((amplifier.getBalance() - amplifier.getMinBalance()) * 100 / (amplifier.getMaxBalance() - amplifier.getMinBalance()));
			}
		}.sync();
	}

	private void updateFade() {
		new DisplayBlock() {
			public void run() {
				fadeSlider.setSelection((amplifier.getFade() - amplifier.getMinFade()) * 100 / (amplifier.getMaxFade() - amplifier.getMinFade()));
			}
		}.sync();
	}

	private void updateBass() {
		new DisplayBlock() {
			public void run() {
				bassSlider.setSelection((amplifier.getBass() - amplifier.getMinBass()) * 100 / (amplifier.getMaxBass() - amplifier.getMinBass()));
			}
		}.sync();
	}

	private void populateScreenComposite() {
		f = new ScaledWidgetFactory(this.getClass(), screenComposite.getSize(), REFERENCE_HEIGHT, REFERENCE_WIDTH);
		controlComposite = f.createComposite(screenComposite, SWT.NONE, 0, 0, 215, 400, AUDIO_PANEL_IMAGE, null);
		muteButton = f.createImageButton(controlComposite, SWT.PUSH, 32, 19, 62, 49, MUTE_OFF_IMAGE, MUTE_ON_IMAGE, MUTE_DEPRESSED_IMAGE, true, this);
		volumeUpButton = f.createImageButton(controlComposite, SWT.PUSH, 14, 50, 66, 67, VOLUME_INC_UP_IMAGE, VOLUME_INC_DOWN_IMAGE, true, this);
		volumeDownButton = f.createImageButton(controlComposite, SWT.PUSH, 14, 117, 66, 67, VOLUME_DEC_UP_IMAGE, VOLUME_DEC_DOWN_IMAGE, true, this);
		volumeBar = f.createImageProgressBar(controlComposite, SWT.HORIZONTAL | SWT.LEFT_TO_RIGHT, 31, 179, 121, 37, VOLUME_BAR_IMAGE);
		volumeBar.setMaximum(120);
		audioStackLayout = new StackLayout();
		audioComposite = f.createComposite(screenComposite, SWT.NONE, 215, 0, 425, 400, null, audioStackLayout);
		cdButton = f.createImageButton(controlComposite, SWT.PUSH, 120, 30, 72, 67, CD_UP_OFF_IMAGE, CD_DOWN_ON_IMAGE, CD_DOWN_OFF_IMAGE, false, this);
		cdComposite = f.createComposite(audioComposite, SWT.NONE, 0, 0, 425, 400);
		radioButton = f.createImageButton(controlComposite, SWT.PUSH, 120, 97, 60, 71, RADIO_UP_OFF_IMAGE, RADIO_DOWN_ON_IMAGE, RADIO_DOWN_OFF_IMAGE, false, this);
		radioComposite = f.createComposite(audioComposite, SWT.NONE, 0, 0, 425, 400);
		adjustStackLayout = new StackLayout();
		adjustStackComposite = f.createComposite(controlComposite, SWT.NONE, 15, 256, 139, 106, null, adjustStackLayout);
		balanceButton = f.createImageButton(controlComposite, SWT.RADIO, 15, 225, 69, 31, BALANCE_OFF_IMAGE, BALANCE_ON_IMAGE, false, this);
		balanceComposite = createAdjustComposite(true);
		balanceSlider = createAdjustImageSlider(balanceComposite, SWT.HORIZONTAL | SWT.LEFT_TO_RIGHT);
		balanceLeftButton = f.createImageButton(balanceComposite, SWT.PUSH, 20, 49, 46, 52, LEFT_UP_IMAGE, LEFT_DOWN_IMAGE, true, this);
		balanceRightButton = f.createImageButton(balanceComposite, SWT.PUSH, 73, 49, 46, 52, RIGHT_UP_IMAGE, RIGHT_DOWN_IMAGE, true, this);
		fadeButton = f.createImageButton(controlComposite, SWT.RADIO, 84, 225, 70, 31, FADE_OFF_IMAGE, FADE_ON_IMAGE, false, this);
		fadeComposite = createAdjustComposite(false);
		fadeSlider = createAdjustImageSlider(fadeComposite, SWT.VERTICAL | SWT.UP);
		fadeFrontButton = f.createImageButton(fadeComposite, SWT.PUSH, 14, 5, 52, 46, UP_UP_IMAGE, UP_DOWN_IMAGE, true, this);
		fadeBackButton = f.createImageButton(fadeComposite, SWT.PUSH, 14, 53, 52, 46, DOWN_UP_IMAGE, DOWN_DOWN_IMAGE, true, this);
		trebleButton = f.createImageButton(controlComposite, SWT.RADIO, 15, 362, 69, 32, TREBLE_OFF_IMAGE, TREBLE_ON_IMAGE, false, this);
		trebleComposite = createAdjustComposite(false);
		trebleSlider = createAdjustImageSlider(trebleComposite, SWT.VERTICAL | SWT.UP);
		trebleUpButton = f.createImageButton(trebleComposite, SWT.PUSH, 14, 5, 52, 46, UP_UP_IMAGE, UP_DOWN_IMAGE, true, this);
		trebleDownButton = f.createImageButton(trebleComposite, SWT.PUSH, 14, 53, 52, 46, DOWN_UP_IMAGE, DOWN_DOWN_IMAGE, true, this);
		bassButton = f.createImageButton(controlComposite, SWT.RADIO, 84, 362, 70, 32, BASS_OFF_IMAGE, BASS_ON_IMAGE, false, this);
		bassComposite = createAdjustComposite(false);
		bassSlider = createAdjustImageSlider(bassComposite, SWT.VERTICAL | SWT.UP);
		bassUpButton = f.createImageButton(bassComposite, SWT.PUSH, 14, 5, 52, 46, UP_UP_IMAGE, UP_DOWN_IMAGE, true, this);
		bassDownButton = f.createImageButton(bassComposite, SWT.PUSH, 14, 53, 52, 46, DOWN_UP_IMAGE, DOWN_DOWN_IMAGE, true, this);
		// pick the balance as the top adjuster
		balanceButton.setSelection(true);
		updateAdjuster(balanceComposite);
	}

	private void unpopulateScreenComposite() {
		f.disposeImageButtonImages(bassDownButton);
		f.disposeImageButtonImages(bassUpButton);
		disposeAdjustImageSliderImages(bassSlider);
		disposeAdjustCompositeImage(bassComposite);
		f.disposeImageButtonImages(bassButton);
		f.disposeImageButtonImages(trebleDownButton);
		f.disposeImageButtonImages(trebleUpButton);
		disposeAdjustImageSliderImages(trebleSlider);
		disposeAdjustCompositeImage(trebleComposite);
		f.disposeImageButtonImages(trebleButton);
		f.disposeImageButtonImages(fadeBackButton);
		f.disposeImageButtonImages(fadeFrontButton);
		disposeAdjustImageSliderImages(fadeSlider);
		disposeAdjustCompositeImage(fadeComposite);
		f.disposeImageButtonImages(fadeButton);
		f.disposeImageButtonImages(balanceRightButton);
		f.disposeImageButtonImages(balanceLeftButton);
		disposeAdjustImageSliderImages(balanceSlider);
		disposeAdjustCompositeImage(balanceComposite);
		f.disposeImageButtonImages(balanceButton);
		f.disposeCompositeImage(adjustStackComposite);
		adjustStackLayout = null;
		f.disposeCompositeImage(radioComposite);
		f.disposeImageButtonImages(radioButton);
		f.disposeCompositeImage(cdComposite);
		f.disposeImageButtonImages(cdButton);
		f.disposeCompositeImage(audioComposite);
		audioStackLayout = null;
		f.disposeImageProgressBarImage(volumeBar);
		f.disposeImageButtonImages(volumeDownButton);
		f.disposeImageButtonImages(volumeUpButton);
		f.disposeImageButtonImages(muteButton);
		f.disposeCompositeImage(controlComposite);
		f = null;
	}

	private Composite createAdjustComposite(boolean isHorizontal) {
		Composite comp = new Composite(adjustStackComposite, SWT.NONE);
		comp.setBounds(f.getScaledBounds(0, 0, 139, 106));
		comp.setBackgroundImage(f.getSizedImageForWidget(isHorizontal ? HORIZ_SLIDER_BACKGROUND_IMAGE : VERT_SLIDER_BACKGROUND_IMAGE, comp));
		return comp;
	}

	private void disposeAdjustCompositeImage(Composite composite) {
		Image image = composite.getBackgroundImage();
		if (image != null)
			image.dispose();
	}

	private ImageSlider createAdjustImageSlider(Composite parent, int style) {
		boolean horizontal = ((style & SWT.HORIZONTAL) != 0);
		ImageSlider slider = new ImageSlider(parent, style);
		if (horizontal) {
			slider.setBounds(f.getScaledBounds(29, 12, 84, 25));
			Point size = slider.getSize();
			slider.setImages(f.getSizedImageForWidget(SLIDER_HORIZ_BAR_IMAGE, slider), f.getSizedImage(SLIDER_HORIZ_IMAGE, 7 * size.x / 84, 19 * size.y / 25));
		} else {
			slider.setBounds(f.getScaledBounds(88, 11, 25, 84));
			Point size = slider.getSize();
			slider.setImages(f.getSizedImageForWidget(SLIDER_VERT_BAR_IMAGE, slider), f.getSizedImage(SLIDER_VERT_IMAGE, 19 * size.x / 25, 7 * size.y / 84));
		}
		return slider;
	}

	private void disposeAdjustImageSliderImages(ImageSlider slider) {
		Image[] images = slider.getImages();
		if (images[0] != null)
			images[0].dispose();
		if (images[1] != null)
			images[1].dispose();
	}

	public void widgetDefaultSelected(SelectionEvent arg0) {
	}

	public void widgetSelected(SelectionEvent e) {
		if (cdButton == e.widget) {
			amplifier.setMode(IAmplifier.MODE_CD);
		} else if (radioButton == e.widget) {
			amplifier.setMode(IAmplifier.MODE_FM);
		} else if (volumeUpButton == e.widget) {
			amplifier.volumeUp();
		} else if (volumeDownButton == e.widget) {
			amplifier.volumeDown();
		} else if (bassUpButton == e.widget) {
			amplifier.bassUp();
		} else if (bassDownButton == e.widget) {
			amplifier.bassDown();
		} else if (trebleUpButton == e.widget) {
			amplifier.trebleUp();
		} else if (trebleDownButton == e.widget) {
			amplifier.trebleDown();
		} else if (balanceRightButton == e.widget) {
			amplifier.balanceRight();
		} else if (balanceLeftButton == e.widget) {
			amplifier.balanceLeft();
		} else if (fadeFrontButton == e.widget) {
			amplifier.fadeFront();
		} else if (fadeBackButton == e.widget) {
			amplifier.fadeBack();
		} else if (balanceButton == e.widget & balanceButton.getSelection()) {
			updateAdjuster(balanceComposite);
			adjustStackComposite.layout();
		} else if (bassButton == e.widget & bassButton.getSelection()) {
			updateAdjuster(bassComposite);
		} else if (fadeButton == e.widget & fadeButton.getSelection()) {
			updateAdjuster(fadeComposite);
		} else if (trebleButton == e.widget & trebleButton.getSelection()) {
			updateAdjuster(trebleComposite);
		} else if (muteButton == e.widget) {
			amplifier.toggleMute();
		}
	}

	public void activate() {
		updateWidgetsFromModel();
		LogUtility.logDebug(this, "activated");
	}

	public void deactivate() {
		LogUtility.logDebug(this, "deactivated");
	}
}

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
import org.eclipse.examples.toast.dev.cdplayer.ICdPlayer;
import org.eclipse.examples.toast.dev.cdplayer.ICdPlayerListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class CdSubscreen implements ICdPlayerListener, SelectionListener {
	private static final String CD_PANEL_IMAGE = "artwork/cd/CdPanel.png"; //$NON-NLS-1$
	private static final String EJECT_DOWN_IMAGE = "artwork/cd/EjectDown.png"; //$NON-NLS-1$
	private static final String EJECT_UP_IMAGE = "artwork/cd/EjectUp.png"; //$NON-NLS-1$
	private static final String FF_DOWN_IMAGE = "artwork/cd/FfDown.png"; //$NON-NLS-1$
	private static final String FF_UP_IMAGE = "artwork/cd/FfUp.png"; //$NON-NLS-1$
	private static final String PAUSE_DOWN_IMAGE = "artwork/cd/PauseDown.png"; //$NON-NLS-1$
	private static final String PAUSE_UP_IMAGE = "artwork/cd/PauseUp.png"; //$NON-NLS-1$
	private static final String PLAY_DOWN_IMAGE = "artwork/cd/PlayDown.png"; //$NON-NLS-1$
	private static final String PLAY_UP_IMAGE = "artwork/cd/PlayUp.png"; //$NON-NLS-1$
	private static final String RW_DOWN_IMAGE = "artwork/cd/RwDown.png"; //$NON-NLS-1$
	private static final String RW_UP_IMAGE = "artwork/cd/RwUp.png"; //$NON-NLS-1$
	private static final String STOP_DOWN_IMAGE = "artwork/cd/StopDown.png"; //$NON-NLS-1$
	private static final String STOP_UP_IMAGE = "artwork/cd/StopUp.png"; //$NON-NLS-1$
	private static final int REFERENCE_WIDTH = 425;
	private static final int REFERENCE_HEIGHT = 400;
	private ICdPlayer cd;
	private boolean isFastForwarding;
	private boolean isRewinding;
	private boolean wasPaused;
	private ScaledWidgetFactory f;
	private Composite screenComposite, playPauseComposite;
	private StackLayout playPauseStackLayout;
	private ImageButton ejectButton, ffButton, pauseButton, playButton, rwButton, stopButton;
	private Label trackInfo;
	private ICrustShell crustShell;

	public CdSubscreen(Composite screenComposite) {
		this.screenComposite = screenComposite;
	}

	public void bind(ICrustShell crustShell, ICdPlayer cd) {
		this.crustShell = crustShell;
		this.cd = cd;
		isFastForwarding = false;
		isRewinding = false;
		wasPaused = false;
		new DisplayBlock() {
			public void run() {
				populateScreenComposite();
			}
		}.sync();
		cd.addListener(this);
	}

	public void unbind() {
		cd.removeListener(this);
		new DisplayBlock() {
			public void run() {
				unpopulateScreenComposite();
			}
		}.sync();
		this.cd = null;
		this.crustShell = null;
	}

	// ICdPlayerListener implementation
	public void ejected() {
	}

	public void inserted() {
	}

	public void paused() {
		new DisplayBlock() {
			public void run() {
				updatePlayPauseButton();
			}
		}.sync();
	}

	public void randomChanged(boolean isRandomOn) {
	}

	public void started() {
		new DisplayBlock() {
			public void run() {
				updatePlayPauseButton();
			}
		}.sync();
	}

	public void stopped() {
		new DisplayBlock() {
			public void run() {
				updatePlayPauseButton();
			}
		}.sync();
	}

	public void timeChanged(int seconds) {
		new DisplayBlock() {
			public void run() {
				updateTrack();
			}
		}.sync();
	}

	public void trackChanged(int track) {
		new DisplayBlock() {
			public void run() {
				updateTrack();
			}
		}.sync();
	}

	public void trackCountChanged(int trackCount) {
	}

	public void widgetDefaultSelected(SelectionEvent event) {
		if (ffButton == event.widget) {
			System.out.println("Double Clicked ff");
		} else if (rwButton == event.widget) {
			System.out.println("Double Clicked rw");
		}
	}

	public void widgetSelected(SelectionEvent event) {
		if (playButton == event.widget) {
			cd.play();
		} else if (pauseButton == event.widget) {
			cd.pause();
		} else if (stopButton == event.widget) {
			cd.stop();
		} else if (ejectButton == event.widget) {
			cd.eject();
		} else if (ffButton == event.widget) {
			if (isFastForwarding) {
				isFastForwarding = false;
				if (wasPaused == true) {
					cd.pause();
				} else {
					cd.play();
				}
			} else {
				cd.nextTrack();
			}
		} else if (rwButton == event.widget) {
			if (isRewinding) {
				isRewinding = false;
				if (wasPaused == true) {
					cd.pause();
				} else {
					cd.play();
				}
			} else {
				cd.previousTrack();
			}
		}
	}

	// Private
	private void populateScreenComposite() {
		f = new ScaledWidgetFactory(this.getClass(), screenComposite.getSize(), REFERENCE_HEIGHT, REFERENCE_WIDTH);
		screenComposite.setBackgroundImage(f.getSizedImageForWidget(CD_PANEL_IMAGE, screenComposite));
		screenComposite.setBackgroundMode(SWT.INHERIT_FORCE);
		ejectButton = f.createImageButton(screenComposite, SWT.PUSH, 118, 266, 188, 54, EJECT_UP_IMAGE, EJECT_DOWN_IMAGE, true, this);
		rwButton = f.createImageButton(screenComposite, SWT.PUSH, 47, 150, 71, 150, RW_UP_IMAGE, RW_DOWN_IMAGE, true, this);
		ffButton = f.createImageButton(screenComposite, SWT.PUSH, 306, 150, 71, 149, FF_UP_IMAGE, FF_DOWN_IMAGE, true, this);
		stopButton = f.createImageButton(screenComposite, SWT.PUSH, 246, 100, 102, 50, STOP_UP_IMAGE, STOP_DOWN_IMAGE, true, this);
		playPauseStackLayout = new StackLayout();
		playPauseComposite = f.createComposite(screenComposite, SWT.NONE, 75, 97, 171, 53, null, playPauseStackLayout);
		playButton = f.createImageButton(playPauseComposite, SWT.PUSH, 0, 0, 171, 53, PLAY_UP_IMAGE, PLAY_DOWN_IMAGE, true, this);
		pauseButton = f.createImageButton(playPauseComposite, SWT.PUSH, 0, 0, 171, 53, PAUSE_UP_IMAGE, PAUSE_DOWN_IMAGE, true, this);
		trackInfo = f.createLabel(screenComposite, SWT.CENTER, 129, 163, 167, 93, crustShell.getFont(ICrustShell.FONT_H3), "Track:");
		updateWidgetsFromModel();
	}

	private void unpopulateScreenComposite() {
		f.disposeLabelImage(trackInfo);
		f.disposeImageButtonImages(pauseButton);
		f.disposeImageButtonImages(playButton);
		f.disposeCompositeImage(playPauseComposite);
		playPauseStackLayout = null;
		f.disposeImageButtonImages(stopButton);
		f.disposeImageButtonImages(ffButton);
		f.disposeImageButtonImages(rwButton);
		f.disposeImageButtonImages(ejectButton);
		f.disposeCompositeImage(screenComposite);
	}

	private void updateWidgetsFromModel() {
		updateTrack();
		updatePlayPauseButton();
	}

	// update screen from model
	private void updateTrack() {
		final String text;
		if (cd.hasCd()) {
			int time = cd.getTime();
			int minutes = time / 60;
			time -= 60 * minutes;
			int tens = time / 10;
			int ones = time % 10;
			text = "TRACK: " + cd.getTrack() + "     " + minutes + ":" + tens + ones; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else {
			text = "NO DISC"; //$NON-NLS-1$
		}
		new DisplayBlock() {
			public void run() {
				trackInfo.setText(text);
				trackInfo.redraw();
			}
		}.async();
	}

	private void updatePlayPauseButton() {
		playPauseStackLayout.topControl = cd.isPausable() ? pauseButton : playButton;
		playPauseComposite.layout();
	}
}

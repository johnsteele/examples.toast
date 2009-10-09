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
package org.eclipse.examples.toast.internal.swt.emergency;

import org.eclipse.examples.toast.client.emergency.IEmergencyMonitor;
import org.eclipse.examples.toast.client.emergency.IEmergencyMonitorListener;
import org.eclipse.examples.toast.crust.shell.DisplayBlock;
import org.eclipse.examples.toast.crust.shell.ICrustScreenListener;
import org.eclipse.examples.toast.crust.shell.ICrustShell;
import org.eclipse.examples.toast.crust.shell.ScaledWidgetFactory;
import org.eclipse.examples.toast.crust.widgets.ImageButton;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class EmergencyScreen implements IEmergencyMonitorListener, SelectionListener, ICrustScreenListener {
	private static final int SLOT = 0;
	private static final String TOPBAR_ICON_ON_IMAGE = "artwork/AlarmOn.png"; //$NON-NLS-1$
	private static final String TOPBAR_ICON_OFF_IMAGE = "artwork/AlarmOff.png"; //$NON-NLS-1$
	private static final String EMERGENCY_BACKGROUND_IMAGE = "artwork/SmallEmergencyPanel.png"; //$NON-NLS-1$
	private static final String DISMISS_DOWN_IMAGE = "artwork/DismissDown.png"; //$NON-NLS-1$
	private static final String DISMISS_UP_IMAGE = "artwork/DismissUp.png"; //$NON-NLS-1$
	private static final String NO_DOWN_IMAGE = "artwork/NoDown.png"; //$NON-NLS-1$
	private static final String NO_UP_IMAGE = "artwork/NoUp.png"; //$NON-NLS-1$
	private static final String YES_DOWN_IMAGE = "artwork/YesDown.png"; //$NON-NLS-1$
	private static final String YES_UP_IMAGE = "artwork/YesUp.png"; //$NON-NLS-1$
	private static final int REFERENCE_WIDTH = 640;
	private static final int REFERENCE_HEIGHT = 400;
	private static final int POPUP_WIDTH = 433;
	private static final int POPUP_HEIGHT = 209;
	private ScaledWidgetFactory f;
	private IEmergencyMonitor monitor;
	private ICrustShell crustShell;
	private Composite screenComposite;
	private Shell popupShell;
	private ImageButton yesButton, noButton;
	private Image[] noImages, dismissImages;
	private Label line1, line2, line3;
	private Font fontH1, fontH2, fontH3;
	private Color RED;
	private Color BLACK;

	public void setShell(ICrustShell value) {
		crustShell = value;
	}

	public void setEmergency(IEmergencyMonitor value) {
		monitor = value;
	}

	public void startup() {
		screenComposite = crustShell.installScreen(SLOT, this.getClass(), TOPBAR_ICON_OFF_IMAGE, TOPBAR_ICON_ON_IMAGE, null, this);
		monitor.addListener(this);
		new DisplayBlock() {
			public void run() {
				f = new ScaledWidgetFactory(this.getClass(), screenComposite.getSize(), REFERENCE_HEIGHT, REFERENCE_WIDTH);
			}
		}.sync();
		Rectangle bounds = f.getScaledBounds(0, 0, POPUP_WIDTH, POPUP_HEIGHT);
		popupShell = crustShell.createPopupShell(SWT.NO_TRIM | SWT.APPLICATION_MODAL, bounds.width, bounds.height);
		new DisplayBlock() {
			public void run() {
				populatePopupShell();
			}
		}.sync();
	}

	public void shutdown() {
		new DisplayBlock() {
			public void run() {
				unpopulatePopupShell();
			}
		}.sync();
		monitor.removeListener(this);
		crustShell.uninstallScreen(SLOT, this);
	}

	// IEmergencyMonitorListener implementation
	public void failed(Exception e) {
		setPopupLineText("EMERGENCY", "Unable to contact service center", "No connectivity"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	public void succeeded(String reply) {
		setPopupLineText("EMERGENCY", "Contacted service center", reply); //$NON-NLS-1$ //$NON-NLS-2$ 
	}

	public void started() {
		setPopupLineText("EMERGENCY", "Contacting service center", "Please stand by..."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	// SelectionListener implementation
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	public void widgetSelected(SelectionEvent event) {
		if (yesButton == event.widget) {
			monitor.emergency();
			popupShell.setVisible(false);
		} else if (noButton == event.widget) {
			popupShell.setVisible(false);
			crustShell.deactivateScreen(SLOT, this);
		}
	}

	// ICrustScreenListener implementation
	public void activate() {
		new DisplayBlock() {
			public void run() {
				setEmergencyQuestionText();
				popupShell.layout();
				popupShell.open();
			}
		}.sync();
	}

	public void deactivate() {
	}

	// Private
	private void populatePopupShell() {
		Image image = f.getSizedImageForWidget(EMERGENCY_BACKGROUND_IMAGE, popupShell);
		popupShell.setBackgroundImage(image);
		// possible bug in mac swt implementation setRegion causes location to
		// be forgotten so get location and set location after setRegion
		Point location = popupShell.getLocation();
		popupShell.setRegion(f.getRegion(image));
		popupShell.setLocation(location.x, location.y);
		popupShell.setBackgroundMode(SWT.INHERIT_FORCE);
		RED = popupShell.getDisplay().getSystemColor(SWT.COLOR_RED);
		BLACK = popupShell.getDisplay().getSystemColor(SWT.COLOR_BLACK);
		fontH3 = crustShell.getFont(ICrustShell.FONT_H3);
		fontH2 = crustShell.getFont(ICrustShell.FONT_H2);
		fontH1 = crustShell.getFont(ICrustShell.FONT_H1);
		line1 = f.createLabel(popupShell, SWT.CENTER, 95, 25, 315, 35, fontH2);
		line2 = f.createLabel(popupShell, SWT.CENTER, 95, 60, 315, 35, fontH1);
		line3 = f.createLabel(popupShell, SWT.CENTER, 95, 95, 315, 35, fontH2);
		yesButton = f.createImageButton(popupShell, SWT.PUSH, 92, 145, 153, 53, YES_UP_IMAGE, YES_DOWN_IMAGE, true, this);
		noButton = f.createImageButton(popupShell, SWT.PUSH, 257, 145, 153, 53, NO_UP_IMAGE, NO_DOWN_IMAGE, true, this);
		noImages = noButton.getImages();
		dismissImages = new Image[3];
		dismissImages[0] = f.getSizedImageForWidget(DISMISS_UP_IMAGE, noButton);
		dismissImages[1] = f.getSizedImageForWidget(DISMISS_DOWN_IMAGE, noButton);
		dismissImages[2] = null;
	}

	private void unpopulatePopupShell() {
		dismissImages[1].dispose();
		dismissImages[0].dispose();
		dismissImages = null;
		noButton.setImages(noImages[0], noImages[1], noImages[2]);
		f.disposeImageButtonImages(noButton);
		f.disposeImageButtonImages(yesButton);
		f.disposeLabelImage(line3);
		f.disposeLabelImage(line2);
		f.disposeLabelImage(line1);
		fontH1 = null;
		fontH2 = null;
		BLACK = null;
		RED = null;
		f.disposeCompositeImage(popupShell);
		popupShell.dispose();
	}

	private void setEmergencyQuestionText() {
		line1.setFont(fontH2);
		line1.setText("Are you sure this is an");
		line1.setForeground(BLACK);
		line1.redraw();
		line2.setFont(fontH1);
		line2.setText("EMERGENCY?");
		line2.setForeground(RED);
		line2.redraw();
		line3.setText("");
		line3.redraw();
		yesButton.setVisible(true);
		noButton.setImages(noImages[0], noImages[1], noImages[2]);
	}

	private void setPopupLineText(final String text1, final String text2, final String text3) {
		new DisplayBlock() {
			public void run() {
				line1.setFont(fontH1);
				line1.setText(text1);
				line1.setForeground(RED);
				line1.redraw();
				line2.setFont(fontH3);
				line2.setText(text2);
				line2.setForeground(line2.getDisplay().getSystemColor(SWT.COLOR_BLACK));
				line2.redraw();
				line3.setFont(fontH3);
				line3.setText(text3);
				line3.redraw();
				yesButton.setVisible(false);
				noButton.setImages(dismissImages[0], dismissImages[1], dismissImages[2]);
				popupShell.setVisible(true);
			}
		}.sync();
	}
}

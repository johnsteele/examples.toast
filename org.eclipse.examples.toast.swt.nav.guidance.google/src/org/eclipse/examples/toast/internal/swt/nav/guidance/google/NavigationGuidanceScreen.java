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
package org.eclipse.examples.toast.internal.swt.nav.guidance.google;

import org.eclipse.examples.toast.client.nav.guidance.Destination;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.crust.shell.DisplayBlock;
import org.eclipse.examples.toast.crust.shell.ICrustShell;
import org.eclipse.examples.toast.crust.shell.ScaledWidgetFactory;
import org.eclipse.examples.toast.crust.widgets.ImageButton;
import org.eclipse.examples.toast.dev.gps.IGps;
import org.eclipse.examples.toast.swt.nav.mapping.INavigationMappingScreen;
import org.eclipse.examples.toast.swt.nav.mapping.INavigationMappingSubscreen;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class NavigationGuidanceScreen implements INavigationMappingSubscreen, SelectionListener {
	//	private static final String GUIDE_UP_ON_IMAGE = "artwork/GuidanceUpOn.png"; //$NON-NLS-1$
	private static final String GUIDE_UP_OFF_IMAGE = "artwork/GuidanceUpOff.png"; //$NON-NLS-1$
	private static final String GUIDE_DOWN_ON_IMAGE = "artwork/GuidanceDownOn.png"; //$NON-NLS-1$
	private static final String GUIDE_DOWN_OFF_IMAGE = "artwork/GuidanceDownOff.png"; //$NON-NLS-1$
	private static final String DEST_PANEL_IMAGE = "artwork/DestPanel.png"; //$NON-NLS-1$
	private static final String DEST_ADDRESS_IMAGE = "artwork/DestAddress.png"; //$NON-NLS-1$
	private static final String DEST_NAME_IMAGE = "artwork/DestName.png"; //$NON-NLS-1$
	private static final String CANCEL_DOWN_IMAGE = "artwork/CancelDown.png"; //$NON-NLS-1$
	private static final String CANCEL_UP_IMAGE = "artwork/CancelUp.png"; //$NON-NLS-1$
	private static final String SELECT_DOWN_IMAGE = "artwork/SelectDown.png"; //$NON-NLS-1$
	private static final String SELECT_UP_IMAGE = "artwork/SelectUp.png"; //$NON-NLS-1$
	private static final int NUM_DEST_BUTTONS = 4;
	private static final int REFERENCE_WIDTH = 640;
	private static final int REFERENCE_HEIGHT = 400;
	private INavigationMappingScreen mappingScreen;
	private Composite screenComposite;
	private Destination[] destinations;
	private Label[] destinationAddresses;
	private Label[] destinationNames;
	private ImageButton[] destinationButtons;
	private ImageButton cancelButton;
	private ScaledWidgetFactory f;
	private IGps gps;
	private IGoogleEarth ge;

	private Destination[] getDestinations() {
		return new Destination[] {new Destination("EclipseCon 2009 Beer Delivery", "Santa Clara Convention Center", -121954262, 3735548), new Destination("Blowfish Sushi to Die For - 3 cases Sake", "2170 Bryant Street", -12241009, 3775961), new Destination("The Stinking Rose - 12 cases Shiraz", "325 Columbus, SF", -122407426, 37798226), new Destination("The Thirsty Bear - 27 Kegs", "661 Howard St, SF", -122399568, 37785552),};
	}

	public void setMappingScreen(INavigationMappingScreen value) {
		mappingScreen = value;
	}

	public void setGps(IGps value) {
		gps = value;
	}

	public void setGoogleEarth(IGoogleEarth value) {
		ge = value;
	}

	public void startup() {
		screenComposite = mappingScreen.installSubscreen(this.getClass(), GUIDE_UP_OFF_IMAGE, GUIDE_DOWN_ON_IMAGE, GUIDE_DOWN_OFF_IMAGE, this);
		new DisplayBlock() {
			public void run() {
				populateScreenComposite();
			}
		}.sync();
	}

	public void shutdown() {
		new DisplayBlock() {
			public void run() {
				unpopulateScreenComposite();
			}
		}.sync();
		mappingScreen.uninstallSubscreen(this);
	}

	// INavigationMappingSubscreen implementation
	public void activate() {
		LogUtility.logDebug(this, "activated");
		updateDestinations();
	}

	// SelectionListener implementation
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	public void widgetSelected(SelectionEvent event) {
		for (int i = 0; i < NUM_DEST_BUTTONS; i++) {
			if (event.widget == destinationButtons[i]) {
				guideToDestination(i);
				return;
			}
		}
		if (event.widget == cancelButton) { //$NON-NLS-1$
			cancel();
		}
	}

	// Private
	private void populateScreenComposite() {
		f = new ScaledWidgetFactory(this.getClass(), screenComposite.getSize(), REFERENCE_HEIGHT, REFERENCE_WIDTH);
		screenComposite.setBackgroundImage(f.getSizedImageForWidget(DEST_PANEL_IMAGE, screenComposite));
		screenComposite.setBackgroundMode(SWT.INHERIT_FORCE);
		cancelButton = f.createImageButton(screenComposite, SWT.PUSH, 11, 83, 72, 64, CANCEL_UP_IMAGE, CANCEL_DOWN_IMAGE, true, this);
		Font font = mappingScreen.getFont(ICrustShell.FONT_H3);
		destinationAddresses = new Label[NUM_DEST_BUTTONS];
		destinationButtons = new ImageButton[NUM_DEST_BUTTONS];
		destinationNames = new Label[NUM_DEST_BUTTONS];
		for (int i = 0; i < NUM_DEST_BUTTONS; i++) {
			destinationButtons[i] = f.createImageButton(screenComposite, SWT.PUSH, 158, 44 + 89 * i, 66, 66, SELECT_UP_IMAGE, SELECT_DOWN_IMAGE, true, this);
			destinationNames[i] = f.createLabel(screenComposite, SWT.WRAP | SWT.CENTER, 235, 36 + 89 * i, 352, 40, font);
			Point size = destinationNames[i].getSize();
			destinationNames[i].setBackgroundImage(f.getSizedImage(DEST_NAME_IMAGE, size.x, size.y));
			destinationAddresses[i] = f.createLabel(screenComposite, SWT.CENTER, 235, 76 + 89 * i, 352, 39, font);
			size = destinationAddresses[i].getSize();
			destinationAddresses[i].setBackgroundImage(f.getSizedImage(DEST_ADDRESS_IMAGE, size.x, size.y));
		}
	}

	private void unpopulateScreenComposite() {
		f.disposeImageButtonImages(cancelButton);
		for (int i = 0; i < NUM_DEST_BUTTONS; i++) {
			f.disposeImageButtonImages(destinationButtons[i]);
			f.disposeLabelImage(destinationAddresses[i]);
		}
		f = null;
	}

	private void cancel() {
		mappingScreen.deactivateSubscreen(this);
	}

	private void updateDestinations() {
		new DisplayBlock() {
			public void run() {
				destinations = getDestinations();
				for (int i = 0; i < NavigationGuidanceScreen.NUM_DEST_BUTTONS; i++) {
					boolean isVisible = i < destinations.length;
					if (isVisible) {
						destinationNames[i].setText("\n" + destinations[i].getName());
						destinationNames[i].redraw();
						destinationAddresses[i].setText(destinations[i].getDescription());
						destinationAddresses[i].redraw();
					}
					destinationButtons[i].setVisible(isVisible);
					destinationAddresses[i].setVisible(isVisible);
					destinationNames[i].setVisible(isVisible);
				}
			}
		}.sync();
	}

	private void guideToDestination(int index) {
		mappingScreen.deactivateSubscreen(this);
		Destination dest = destinations[index];
		ge.loadDirections(gps.getLatitude(), gps.getLongitude(), dest.getTudLatitude(), dest.getTudLongitude());
	}
}

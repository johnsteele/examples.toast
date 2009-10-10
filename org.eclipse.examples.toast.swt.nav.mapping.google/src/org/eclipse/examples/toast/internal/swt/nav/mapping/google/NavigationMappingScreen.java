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
package org.eclipse.examples.toast.internal.swt.nav.mapping.google;

import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.crust.shell.DisplayBlock;
import org.eclipse.examples.toast.crust.shell.ICrustScreenListener;
import org.eclipse.examples.toast.crust.shell.ICrustShell;
import org.eclipse.examples.toast.crust.shell.ScaledWidgetFactory;
import org.eclipse.examples.toast.crust.widgets.ImageButton;
import org.eclipse.examples.toast.dev.google.IGoogleEarth;
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

public class NavigationMappingScreen implements SelectionListener, ICrustScreenListener, INavigationMappingScreen {
	private static final int SLOT = 4;
	private static final String TOPBAR_ICON_ON_IMAGE = "artwork/CompassOn.png"; //$NON-NLS-1$
	private static final String TOPBAR_ICON_OFF_IMAGE = "artwork/CompassOff.png"; //$NON-NLS-1$
	private static final String MAPPING_BACKGROUND = "artwork/NavPanel.png"; //$NON-NLS-1$
	private static final String CAR_FORWARD_ON_IMAGE = "artwork/CarForwardDownOn.png"; //$NON-NLS-1$
	private static final String CAR_FORWARD_OFF_IMAGE = "artwork/CarForwardUpOff.png"; //$NON-NLS-1$
	private static final String FOLLOW_ON_IMAGE = "artwork/FollowDownOn.png"; //$NON-NLS-1$
	private static final String FOLLOW_OFF_IMAGE = "artwork/FollowUpOff.png"; //$NON-NLS-1$
	private static final String INFO_ON_IMAGE = "artwork/InfoDownOn.png"; //$NON-NLS-1$
	private static final String INFO_OFF_IMAGE = "artwork/InfoUpOff.png"; //$NON-NLS-1$
	private static final String CENTER_ON_IMAGE = "artwork/CenterDownOn.png"; //$NON-NLS-1$
	private static final String CENTER_OFF_IMAGE = "artwork/CenterUpOff.png"; //$NON-NLS-1$
	private static final String ZOOM_IN_ON_IMAGE = "artwork/ZoomInDown.png"; //$NON-NLS-1$
	private static final String ZOOM_IN_OFF_IMAGE = "artwork/ZoomInUp.png"; //$NON-NLS-1$
	private static final String ZOOM_OUT_ON_IMAGE = "artwork/ZoomOutDown.png"; //$NON-NLS-1$
	private static final String ZOOM_OUT_OFF_IMAGE = "artwork/ZoomOutUp.png"; //$NON-NLS-1$
	private static final int REFERENCE_WIDTH = 640;
	private static final int REFERENCE_HEIGHT = 400;
	private static final int CENTER = 0;
	private static final int INFO = 1;
	private static final int ZOOM_FACTOR = 100;

	private int zoom = 200;
	private ScaledWidgetFactory f;
	private Object syncLock = new Object();
	private ICrustShell crustShell;
	private Composite screenComposite;
	private IGps gps;
	private ImageButton rotateButton;
	private ImageButton followButton;
	private ImageButton infoButton;
	private ImageButton centerButton;
	private ImageButton zoomInButton;
	private ImageButton zoomOutButton;
	private Label statusBarLabel;
	private boolean isFollowing = true;
	private int mapClickMode = NavigationMappingScreen.CENTER;
	private MappingStatusBar statusBar;
	private ImageButton subscreenButton;
	private Composite subscreenComposite;
	private Composite mappingComposite;
	private ScaledWidgetFactory subscreenFactory;
	private INavigationMappingSubscreen subscreen;
	private IGoogleEarth ge;

	public void setGoogleEarth(IGoogleEarth value) {
		ge = value;
	}

	public void setShell(ICrustShell value) {
		crustShell = value;
	}

	public void setGps(IGps value) {
		gps = value;
	}

	public void startup() {
		screenComposite = crustShell.installScreen(SLOT, this.getClass(), TOPBAR_ICON_OFF_IMAGE, TOPBAR_ICON_ON_IMAGE, null, this);
		new DisplayBlock() {
			public void run() {
				populateScreenComposite();
			}
		}.sync();
		// launchMappingThread();
		statusBar = new MappingStatusBar(statusBarLabel);
		updateWidgetsFromModel();
	}

	public void shutdown() {
		statusBar.shutDown();
		new DisplayBlock() {
			public void run() {
				unpopulateScreenComposite();
			}
		}.sync();
		crustShell.uninstallScreen(SLOT, this);
	}

	// INavigationMappingScreen implementation
	public void setGuidanceLabel(String label) {
		statusBar.updateGuidance(label);
	}

	public void clearGuidanceLabel() {
		statusBar.updateGuidance(new String());
	}

	public void deactivateSubscreen(INavigationMappingSubscreen subscreen) {
		mappingComposite.setVisible(true);
		subscreenComposite.setVisible(false);
	}

	public Font getFont(String fontName) {
		return crustShell.getFont(fontName);
	}

	public Composite installSubscreen(final Class clazz, final String offImage, final String onImage, final String depressedImage, INavigationMappingSubscreen subscreen) {
		this.subscreen = subscreen;
		new DisplayBlock() {
			public void run() {
				final Point screenSize = screenComposite.getSize();
				subscreenFactory = new ScaledWidgetFactory(clazz, screenSize, REFERENCE_HEIGHT, REFERENCE_WIDTH);
				subscreenButton = subscreenFactory.createImageButton(mappingComposite, SWT.PUSH, 11, 323, 72, 37, offImage, onImage, depressedImage, true, NavigationMappingScreen.this);
				subscreenComposite = new Composite(screenComposite, SWT.NONE);
				subscreenComposite.setBounds(subscreenFactory.getScaledBounds(0, 0, screenSize.x, screenSize.y));
				subscreenComposite.setVisible(false);
			}
		}.sync();
		return subscreenComposite;
	}

	public void setSubscreenButtonSelection(INavigationMappingSubscreen subscreen, boolean state) {
		subscreenButton.setSelection(state);
	}

	public void uninstallSubscreen(INavigationMappingSubscreen subscreen) {
		new DisplayBlock() {
			public void run() {
				subscreenFactory.disposeImageButtonImages(subscreenButton);
				subscreenButton.dispose();
				subscreenComposite.dispose();
			}
		}.sync();
		subscreen = null;
	}

	// SelectionListener implementation
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	public void widgetSelected(SelectionEvent e) {
		LogUtility.logDebug(this, e.widget.toString());
		if (e.widget == rotateButton) {
			toggleRotate();
		} else if (e.widget == followButton) {
			toggleFollow();
		} else if (e.widget == infoButton) {
			toggleInfo();
		} else if (e.widget == centerButton) {
			toggleCenter();
		} else if (e.widget == zoomInButton) {
			zoomIn();
		} else if (e.widget == zoomOutButton) {
			zoomOut();
		} else if (e.widget == subscreenButton) {
			activateSubscreen();
		}
	}

	// ICrustScreenListener implementation
	public void activate() {
		LogUtility.logDebug(this, "activated");
		updateRotateButton();
		updateFollowButton();
		updateInfoButton();
		updateCenterButton();
		ge.setVisible(mappingComposite, f.getScaledBounds(115, 22, 510, 318));
		ge.flyTo(gps, zoom);
		synchronized (this) {
			notifyAll();
		}
	}

	public void deactivate() {
		LogUtility.logDebug(this, "deactivated");
		ge.hide();
		synchronized (this) {
			notifyAll();
		}
	}

	// Private
	private void populateScreenComposite() {
		Point screenSize = screenComposite.getSize();
		f = new ScaledWidgetFactory(this.getClass(), screenSize, REFERENCE_HEIGHT, REFERENCE_WIDTH);
		mappingComposite = new Composite(screenComposite, SWT.NONE);
		mappingComposite.setBounds(f.getScaledBounds(0, 0, screenSize.x, screenSize.y));
		mappingComposite.setBackgroundImage(f.getSizedImageForWidget(MAPPING_BACKGROUND, screenComposite));
		mappingComposite.setBackgroundMode(SWT.INHERIT_FORCE);
		rotateButton = f.createImageButton(mappingComposite, SWT.PUSH, 11, 14, 72, 58, CAR_FORWARD_OFF_IMAGE, CAR_FORWARD_ON_IMAGE, true, this);
		followButton = f.createImageButton(mappingComposite, SWT.PUSH, 11, 72, 72, 58, FOLLOW_OFF_IMAGE, FOLLOW_ON_IMAGE, true, this);
		infoButton = f.createImageButton(mappingComposite, SWT.PUSH, 11, 132, 72, 54, INFO_OFF_IMAGE, INFO_ON_IMAGE, true, this);
		centerButton = f.createImageButton(mappingComposite, SWT.PUSH, 11, 186, 72, 64, CENTER_OFF_IMAGE, CENTER_ON_IMAGE, true, this);
		zoomInButton = f.createImageButton(mappingComposite, SWT.PUSH, 45, 231, 46, 90, ZOOM_IN_OFF_IMAGE, ZOOM_IN_ON_IMAGE, true, this);
		zoomOutButton = f.createImageButton(mappingComposite, SWT.PUSH, 10, 243, 40, 66, ZOOM_OUT_OFF_IMAGE, ZOOM_OUT_ON_IMAGE, true, this);
		Font font = crustShell.getFont(ICrustShell.FONT_H3);
		statusBarLabel = f.createLabel(mappingComposite, SWT.NONE, 115, 365, 510, 22, font);
	}

	private void unpopulateScreenComposite() {
		f.disposeImageButtonImages(rotateButton);
		f.disposeImageButtonImages(followButton);
		f.disposeImageButtonImages(infoButton);
		f.disposeImageButtonImages(centerButton);
		f.disposeImageButtonImages(zoomInButton);
		f.disposeImageButtonImages(zoomOutButton);
		f.disposeLabelImage(statusBarLabel);
		f = null;
	}

	private void activateSubscreen() {
		subscreenComposite.setVisible(true);
		mappingComposite.setVisible(false);
		subscreen.activate();
	}

	private void updateWidgetsFromModel() {
		updateRotateButton();
		updateFollowButton();
		updateInfoButton();
		updateCenterButton();
	}

	private void updateRotateButton() {
		new DisplayBlock() {
			public void run() {
			}
		}.sync();
	}

	private void updateFollowButton() {
		new DisplayBlock() {
			public void run() {
				followButton.setSelection(isFollowing);
			}
		}.sync();
	}

	private void updateInfoButton() {
		new DisplayBlock() {
			public void run() {
				infoButton.setSelection(mapClickMode == NavigationMappingScreen.INFO);
			}
		}.sync();
	}

	private void updateCenterButton() {
		new DisplayBlock() {
			public void run() {
				centerButton.setSelection(mapClickMode == NavigationMappingScreen.CENTER);
			}
		}.sync();
	}

	private void toggleRotate() {
		updateRotateButton();
	}

	private void toggleFollow() {
		isFollowing = !isFollowing;
		if (!isFollowing) {
		}
		updateFollowButton();
	}

	private void toggleInfo() {
		mapClickMode = NavigationMappingScreen.INFO;
		updateCenterButton();
		updateInfoButton();
	}

	private void toggleCenter() {
		mapClickMode = NavigationMappingScreen.CENTER;
		updateCenterButton();
		updateInfoButton();
	}

	private void zoomIn() {
		synchronized (syncLock) {
			zoom -= ZOOM_FACTOR;
			ge.flyTo(gps, zoom);
		}
	}

	private void zoomOut() {
		synchronized (syncLock) {
			zoom += ZOOM_FACTOR;
			ge.flyTo(gps, zoom);
		}
	}
}

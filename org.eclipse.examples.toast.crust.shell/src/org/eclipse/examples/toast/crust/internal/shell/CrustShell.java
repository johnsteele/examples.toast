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
package org.eclipse.examples.toast.crust.internal.shell;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.crust.display.ICrustDisplay;
import org.eclipse.examples.toast.crust.shell.DisplayBlock;
import org.eclipse.examples.toast.crust.shell.ICrustScreenListener;
import org.eclipse.examples.toast.crust.shell.ICrustShell;
import org.eclipse.examples.toast.crust.shell.ScaledWidgetFactory;
import org.eclipse.examples.toast.crust.shell.ScreenManager;
import org.eclipse.examples.toast.crust.widgets.ImageButton;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class CrustShell implements Listener, ICrustShell {
	public static final String CRUST_PROPERTIES_FILE = "crust.properties";
	public static final String RANWITH_PROPERTIES_FILE = "ranwith.properties";
	public static final String SHELL_STYLE_PROPERTY = "crust.shell.style"; //$NON-NLS-1$
	public static final String SHELL_STYLE_UNDEFINED = "undefined"; //$NON-NLS-1$
	public static final String SHELL_TITLE_PROPERTY = "crust.shell.title"; //$NON-NLS-1$
	public static final String SHELL_TITLE_DEFAULT = "Crust"; //$NON-NLS-1$
	public static final String SHELL_X_PROPERTY = "crust.shell.x"; //$NON-NLS-1$
	public static final String SHELL_X_DEFAULT = "100"; //$NON-NLS-1$
	public static final String SHELL_Y_PROPERTY = "crust.shell.y"; //$NON-NLS-1$
	public static final String SHELL_Y_DEFAULT = "100"; //$NON-NLS-1
	public static final String SHELL_WIDTH_PROPERTY = "crust.shell.width"; //$NON-NLS-1$
	public static final String SHELL_WIDTH_DEFAULT = "640";
	public static final String SHELL_HEIGHT_PROPERTY = "crust.shell.height"; //$NON-NLS-1$
	public static final String SHELL_HEIGHT_DEFAULT = "480";
	public static final String SHELL_OS_HEIGHT_PROPERTY = "crust.shell.os.height"; //$NON-NLS-1$
	public static final String SHELL_OS_HEIGHT_DEFAULT = "20"; //$NON-NLS-1$
	public static final String MENU_SLOTS_PROPERTY = "crust.menu.slots"; //$NON-NLS-1$
	public static final String MENU_SLOTS_DEFAULT = "8";
	public static final String MENU_HEIGHT_PROPERTY = "crust.menu.height"; //$NON-NLS-1$
	public static final String MENU_HEIGHT_DEFAULT = "80";
	private static int SHELL_STYLE = SWT.BORDER | SWT.CLOSE | SWT.MIN;
	private static String SHELL_TITLE = SHELL_TITLE_DEFAULT;
	private static int SHELL_X;
	private static int SHELL_Y;
	private static int SHELL_WIDTH;
	private static int SHELL_HEIGHT;
	private static int SHELL_OS_HEIGHT;
	private static int APP_PANEL_WIDTH;
	private static int APP_PANEL_HEIGHT;
	private static int MENU_SLOTS;
	private static int MENU_HEIGHT;
	private ICrustDisplay crustDisplay;
	private HashMap fonts;
	private Shell shell;
	private ImageButton[] menuBarButtons;
	private Composite parentComposite;
	private ScreenManager screenManager;
	private Shell popup;

	public CrustShell() {
		super();
		this.setProperties();
	}

	public void setDisplay(ICrustDisplay value) {
		crustDisplay = value;
	}

	public void startup() {
		new DisplayBlock() {
			public void run() {
				createShell();
				createFonts();
				createWidgets();
				createScreenManager();
				open();
			}
		}.sync();
		LogUtility.logDebug(this, "Started.");
	}

	public void shutdown() {
		close();
		LogUtility.logDebug(this, "Stopped.");
	}

	// Listener implementation
	public void handleEvent(Event swtEvent) {
		switch (swtEvent.type) {
			case SWT.Close :
				crustDisplay.shutdown();
				// keep shell from disposing so other widgets can clean up
				swtEvent.doit = false;
				break;
			default :
				// do nothing
		}
	}

	// ICrustShell implementation
	public Font getFont(String fontName) {
		Font font = (Font) fonts.get(fontName);
		if (fonts == null)
			font = crustDisplay.getDisplay().getSystemFont();
		return font;
	}

	public Composite installScreen(final int slot, final Class clazz, final String upImage, final String downImage, final String depressedImage, final ICrustScreenListener screenListener) {
		return screenManager.installScreen(slot, clazz, upImage, downImage, depressedImage, screenListener);
	}

	public void uninstallScreen(final int slot, ICrustScreenListener screenListener) {
		screenManager.uninstallScreen(slot, screenListener);
	}

	public void deactivateScreen(final int slot, ICrustScreenListener screenListener) {
		screenManager.deactivateScreen(slot, screenListener);
	}

	public void widgetDefaultSelected(SelectionEvent e) {
		// Do Nothing
	}

	public Shell createPopupShell(final int style, final int width, final int height) {
		new DisplayBlock() {
			public void run() {
				int popupWidth = width == SWT.DEFAULT ? APP_PANEL_WIDTH : width;
				int popupHeight = height == SWT.DEFAULT ? APP_PANEL_HEIGHT : height;
				popup = new Shell(shell, style);
				Point location = shell.getLocation();
				popup.setBounds((APP_PANEL_WIDTH - popupWidth) / 2 + location.x, (APP_PANEL_HEIGHT - popupHeight) / 2 + location.y + MENU_HEIGHT + SHELL_OS_HEIGHT, popupWidth, popupHeight);
			}
		}.sync();
		return popup;
	}

	// Private
	private void createShell() {
		shell = new Shell(crustDisplay.getDisplay(), SHELL_STYLE);
		shell.setBounds(SHELL_X, SHELL_Y, SHELL_WIDTH, SHELL_HEIGHT + SHELL_OS_HEIGHT);
		ScaledWidgetFactory f = new ScaledWidgetFactory(getClass());
		shell.setBackgroundImage(f.getSizedImage("artwork/FullBackground.png", SHELL_WIDTH, SHELL_HEIGHT));
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setText(SHELL_TITLE);
		listenToEvents();
	}

	private void listenToEvents() {
		shell.addListener(SWT.Close, this);
	}

	private void open() {
		shell.open();
	}

	private void close() {
		if (shell.isDisposed()) {
			shell = null;
		} else {
			crustDisplay.getDisplay().syncExec(new Runnable() {
				public void run() {
					shell.dispose();
					shell = null;
				}
			});
		}
	}

	private void createFonts() {
		fonts = new HashMap(5);
		Display disp = crustDisplay.getDisplay();
		FontData fd = disp.getSystemFont().getFontData()[0];
		// there are 72pts/inch and inch/(getDPI().y)pixels we want 24pixel font
		// on a 480 pixel screen
		int height = SHELL_HEIGHT * 72 / (disp.getDPI().y * 34);
		fd.setHeight(height);
		Font font = new Font(disp, fd);
		fonts.put(ICrustShell.FONT_H3, font);
		fd.setHeight(height * 3 / 2);
		font = new Font(disp, fd);
		fonts.put(ICrustShell.FONT_H2, font);
		fd.setHeight(height * 2);
		font = new Font(disp, fd);
		fonts.put(ICrustShell.FONT_H1, font);
	}

	private void createWidgets() {
		createMenuBar();
		parentComposite = new Composite(shell, SWT.NONE);
		parentComposite.setBounds(0, MENU_HEIGHT, APP_PANEL_WIDTH, APP_PANEL_HEIGHT);
	}

	private void createMenuBar() {
		menuBarButtons = new ImageButton[MENU_SLOTS];
		int menuButtonWidth = SHELL_WIDTH / MENU_SLOTS;
		for (int i = 0; i < MENU_SLOTS; i++) {
			menuBarButtons[i] = new ImageButton(shell, SWT.PUSH);
			menuBarButtons[i].setBounds(i * menuButtonWidth + 1, 1, menuButtonWidth - 2, MENU_HEIGHT - 2);
		}
	}

	private void createScreenManager() {
		screenManager = new ScreenManager(parentComposite, menuBarButtons);
	}

	private void setProperties() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(CRUST_PROPERTIES_FILE));
		} catch (IOException e) {
			LogUtility.logDebug(this, "unable to open properties file: " + CRUST_PROPERTIES_FILE); //$NON-NLS-1$  
		}
		// write out a properties file so we can see what we actually ran with
		try {
			properties.store(new FileOutputStream(RANWITH_PROPERTIES_FILE), null);
		} catch (IOException e) {
			LogUtility.logDebug(this, "unable to write properties file: " + RANWITH_PROPERTIES_FILE); //$NON-NLS-1$  
		}
		String styleString = getProperty(properties, SHELL_STYLE_PROPERTY, SHELL_STYLE_UNDEFINED);
		if (!styleString.equalsIgnoreCase(SHELL_STYLE_UNDEFINED)) {
			SHELL_STYLE = SWT.BORDER | SWT.CLOSE | SWT.MIN;
		}
		SHELL_TITLE = getProperty(properties, SHELL_TITLE_PROPERTY, SHELL_TITLE_DEFAULT);
		SHELL_X = Integer.parseInt(getProperty(properties, SHELL_X_PROPERTY, SHELL_X_DEFAULT));
		SHELL_Y = Integer.parseInt(getProperty(properties, SHELL_Y_PROPERTY, SHELL_Y_DEFAULT));
		SHELL_WIDTH = Integer.parseInt(getProperty(properties, SHELL_WIDTH_PROPERTY, SHELL_WIDTH_DEFAULT));
		SHELL_HEIGHT = Integer.parseInt(getProperty(properties, SHELL_HEIGHT_PROPERTY, SHELL_HEIGHT_DEFAULT));
		SHELL_OS_HEIGHT = Integer.parseInt(getProperty(properties, SHELL_OS_HEIGHT_PROPERTY, SHELL_OS_HEIGHT_DEFAULT));
		MENU_SLOTS = Integer.parseInt(getProperty(properties, MENU_SLOTS_PROPERTY, MENU_SLOTS_DEFAULT));
		MENU_HEIGHT = Integer.parseInt(getProperty(properties, MENU_HEIGHT_PROPERTY, MENU_HEIGHT_DEFAULT));
		APP_PANEL_WIDTH = SHELL_WIDTH;
		APP_PANEL_HEIGHT = SHELL_HEIGHT - MENU_HEIGHT;
	}

	private String getProperty(Properties properties, String key, String def) {
		// using this method will cause command line values to override the
		// property file values which in turn override the defaults
		// this also saves the properties to the System structure so that they
		// can be seen through an equinox "props" command
		String value = System.getProperty(key, properties.getProperty(key, def));
		System.setProperty(key, value);
		return value;
	}

	public Shell getShell() {
		return shell;
	}
}

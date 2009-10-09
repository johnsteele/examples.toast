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
package org.eclipse.examples.toast.crust.shell;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public interface ICrustShell {
	public static final String FONT_H1 = "fontH1";
	public static final String FONT_H2 = "fontH2";
	public static final String FONT_H3 = "fontH3";

	public Font getFont(String fontName);

	public Composite installScreen(int slot, Class clazz, String offImage, String onImage, String depressedImage, ICrustScreenListener screenListener);

	public void uninstallScreen(int slot, ICrustScreenListener screenListener);

	public void deactivateScreen(int slot, ICrustScreenListener screenListener);

	public Shell createPopupShell(int flags, int width, int height);
}

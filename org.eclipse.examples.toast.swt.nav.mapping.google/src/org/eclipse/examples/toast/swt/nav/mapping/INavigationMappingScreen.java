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
package org.eclipse.examples.toast.swt.nav.mapping;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;

public interface INavigationMappingScreen {
	public void setGuidanceLabel(String label);

	public void clearGuidanceLabel();

	public Composite installSubscreen(Class clazz, String offImage, String onImage, String depressedImage, INavigationMappingSubscreen subscreen);

	public void deactivateSubscreen(INavigationMappingSubscreen subscreen);

	public Font getFont(String fontName);

	public void setSubscreenButtonSelection(INavigationMappingSubscreen subscreen, boolean state);

	public void uninstallSubscreen(INavigationMappingSubscreen subscreen);
}

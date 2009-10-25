/*******************************************************************************
 * Copyright (c) 2009 EclipseSource Inc. and others. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v1.0 
 * which accompanies this distribution. The Eclipse Public License is available at 
 * http://www.eclipse.org/legal/epl-v10.html and the Eclipse Distribution License 
 * is available at http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors: 
 *     EclipseSource Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.examples.toast.backend.rap;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "org.eclipse.examples.toast.backend.rap.perspective"; //$NON-NLS-1$

	public void createInitialLayout(final IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.addView(SearchView.ID, IPageLayout.LEFT, 0.3f, editorArea);
		layout.getViewLayout(SearchView.ID).setCloseable(false);
		layout.addStandaloneView(VehicleView.ID, false, IPageLayout.RIGHT, 0.95f, editorArea);
		layout.getViewLayout(VehicleView.ID).setCloseable(false);
		IFolderLayout folder = layout.createFolder("belowEditor", IPageLayout.BOTTOM, 0.5f, VehicleView.ID); //$NON-NLS-1$
		folder.addView(SoftwareView.ID);
		folder.addView(MapsView.ID);
	}
}

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

import org.eclipse.swt.layout.GridLayout;

public class LayoutUtil {

	public static GridLayout createGridLayout() {
		return createGridLayout(1, false, 0, 0);
	}

	public static GridLayout createGridLayout(final int margin, final int spacing) {
		return createGridLayout(1, false, margin, spacing);
	}

	public static GridLayout createGridLayout(final int columns, final boolean equalWidth) {
		return createGridLayout(columns, equalWidth, 0, 0);
	}

	public static GridLayout createGridLayout(final int columns, final boolean equalWidth, final int margin, final int spacing) {
		GridLayout layout = new GridLayout(columns, false);
		layout.marginHeight = margin;
		layout.marginWidth = margin;
		layout.horizontalSpacing = spacing;
		layout.verticalSpacing = spacing;
		layout.makeColumnsEqualWidth = equalWidth;
		return layout;
	}
}

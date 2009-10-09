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
package org.eclipse.examples.toast.internal.backend.tracking.ui;

public interface IPortalConstants {
	public static final String ACTION_PARAMETER = "action";
	public static final String BROWSE_ACTION = "browse";
	public static final String BROWSE_TRACKING_ACTION = BROWSE_ACTION + "/" + "tracking";
	public static final String TRACKING_MAP_ACTION = BROWSE_TRACKING_ACTION + "/" + "trackmap";

	public static final String RESOURCE_IMAGES_ALIAS = "images";
	public static final String RESOURCE_IMAGES_PATH = "resources/images";
}

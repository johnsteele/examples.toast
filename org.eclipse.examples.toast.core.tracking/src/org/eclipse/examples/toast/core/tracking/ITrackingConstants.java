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
package org.eclipse.examples.toast.core.tracking;

public interface ITrackingConstants {
	public static final String TRACKING_FUNCTION = "tracking";
	public static final String LATITUDE_PARAMETER = "latitude"; //$NON-NLS-1$
	public static final String LONGITUDE_PARAMETER = "longitude"; //$NON-NLS-1$
	public static final String HEADING_PARAMETER = "heading"; //$NON-NLS-1$
	public static final String SPEED_PARAMETER = "speed"; //$NON-NLS-1$
	public static final String TRACKING_LOCATION = "initial@reference:file:../../../../org.eclipse.examples.toast.client.tracking/";
	public static final String TRACKING_PID = "org.eclipse.examples.toast.client.tracking";
	public static final String TRACKING_DELAY_PROPERTY = "delay";
	public static final int TRACKING_DELAY_DEFAULT = 10;
	public static final int MAX_TRACKING_HISTORY = 4;
}

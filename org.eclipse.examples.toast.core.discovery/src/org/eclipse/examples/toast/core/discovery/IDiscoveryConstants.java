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
package org.eclipse.examples.toast.core.discovery;

/**
 * Constants for use in discovery.  For now the constants related to several
 * discovery implementations are contained here.
 */
public interface IDiscoveryConstants {
	// ECF Discovery constants
	public static final String SERVICE_TYPE = "toastclient";
	public static final String TALKBACK_PROP = "talkback";

	// HTTP Discovery constants
	public static final String DISCOVERY_ACTION = "discovery"; //$NON-NLS-1$
	public static final String REGISTER_OPERATION = "register"; //$NON-NLS-1$
	public static final String UNREGISTER_OPERATION = "unregister"; //$NON-NLS-1$
	public static final String OPERATION_PARAMETER = "operation"; //$NON-NLS-1$
	public static final String ID_PARAMETER = "id"; //$NON-NLS-1$
	public static final String LOCATION_PARAMETER = "location"; //$NON-NLS-1$
	public static final String ACTION_PARAMETER = "action"; //$NON-NLS-1$
	public static final String CONTENT_TYPE_PLAIN = "text/plain"; //$NON-NLS-1$
	public static final char ACK_REPLY = '1';

}

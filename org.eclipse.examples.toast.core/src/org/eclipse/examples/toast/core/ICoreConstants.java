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
package org.eclipse.examples.toast.core;

public interface ICoreConstants {
	public static final String CONFIG_PARAMETER = "config"; //$NON-NLS-1$	
	public static final String ID_PARAMETER = "id"; //$NON-NLS-1$	
	public static final String CONTENT_TYPE_PLAIN = "text/plain"; //$NON-NLS-1$
	public static final String CONTENT_TYPE_HTML = "text/html"; //$NON-NLS-1$
	public static final String MISSING_PARAMETER = "Missing parameter: "; //$NON-NLS-1$
	// -D properties
	public static final String ID_PROPERTY = "toast.id"; //$NON-NLS-1$
	public static final String ID_DEFAULT = "ABC123"; //$NON-NLS-1$
	public static final String BACK_END_URL_PROPERTY = "toast.backend.url"; //$NON-NLS-1$
	public static final String BACK_END_URL_DEFAULT = "http://localhost:8080/toast"; //$NON-NLS-1$
	public static final String BACKEND_SERVLET_CONTAINER_DEFAULT = ""; //$NON-NLS-1$
	public static final String BACKEND_SERVLET_CONTAINER_PROPERTY = "toast.backend.servlet.container"; //$NON-NLS-1$
}

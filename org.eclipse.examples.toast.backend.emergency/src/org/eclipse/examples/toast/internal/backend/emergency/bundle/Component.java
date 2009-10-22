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
package org.eclipse.examples.toast.internal.backend.emergency.bundle;

import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.PropertyManager;
import org.eclipse.examples.toast.core.UrlBuilder;
import org.eclipse.examples.toast.core.emergency.IEmergencyCenter;
import org.eclipse.examples.toast.core.emergency.IEmergencyConstants;
import org.eclipse.examples.toast.internal.backend.emergency.EmergencyServlet;
import org.osgi.service.http.HttpService;

public class Component {
	private IEmergencyCenter center;
	private HttpService http;
	private String servletAlias;

	public void setEmergencyCenter(IEmergencyCenter value) {
		center = value;
	}

	public void setHttp(HttpService value) {
		http = value;
	}

	protected void shutdown() {
		http.unregister(servletAlias);
	}

	protected void startup() {
		try {
			String servletRoot = PropertyManager.getProperty(ICoreConstants.BACK_END_URL_PROPERTY, ICoreConstants.BACK_END_URL_DEFAULT);
			UrlBuilder urlBuilder = new UrlBuilder(servletRoot);
			urlBuilder.appendPath(IEmergencyConstants.EMERGENCY_FUNCTION);
			servletAlias = urlBuilder.getPath();

			EmergencyServlet servlet = new EmergencyServlet(center);
			http.registerServlet(servletAlias, servlet, null, null);
			LogUtility.logDebug(this, "Registered EmergencyServlet at " + servletAlias);
		} catch (Exception e) {
			LogUtility.logError(this, "Error registering servlet with HttpService", e);
		}
	}
}

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
package org.eclipse.examples.toast.internal.backend.tracking.bundle;

import javax.servlet.http.HttpServlet;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.PropertyManager;
import org.eclipse.examples.toast.core.UrlBuilder;
import org.eclipse.examples.toast.core.tracking.ITrackingCenter;
import org.eclipse.examples.toast.core.tracking.ITrackingConstants;
import org.eclipse.examples.toast.internal.backend.tracking.TrackingServlet;
import org.osgi.service.http.HttpService;

public class Component {
	private String servletAlias;
	private HttpService http;
	private ITrackingCenter center;

	public void setHttp(HttpService value) {
		http = value;
	}

	public void setTrackingCenter(ITrackingCenter value) {
		center = value;
	}

	protected void startup() {
		try {
			String servletRoot = PropertyManager.getProperty(ICoreConstants.BACK_END_URL_PROPERTY, ICoreConstants.BACK_END_URL_DEFAULT);
			UrlBuilder urlBuilder = new UrlBuilder(servletRoot);
			urlBuilder.appendPath(ITrackingConstants.TRACKING_FUNCTION);
			servletAlias = urlBuilder.getPath();

			HttpServlet servlet = new TrackingServlet(center);
			http.registerServlet(servletAlias, servlet, null, null);
			LogUtility.logDebug(this, "Registered TrackingServlet at " + servletAlias);
		} catch (Exception e) {
			LogUtility.logError(this, "Error registering servlet with HttpService", e);
		}
	}

	protected void shutdown() {
		http.unregister(servletAlias);
	}
}

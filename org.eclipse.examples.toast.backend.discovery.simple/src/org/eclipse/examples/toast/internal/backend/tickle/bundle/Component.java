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
package org.eclipse.examples.toast.internal.backend.tickle.bundle;

import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.PropertyManager;
import org.eclipse.examples.toast.core.UrlBuilder;
import org.eclipse.examples.toast.core.discovery.IDiscovery;
import org.eclipse.examples.toast.core.discovery.IDiscoveryConstants;
import org.eclipse.examples.toast.core.discovery.IDiscoveryListener;
import org.eclipse.examples.toast.internal.backend.tickle.DiscoveryServlet;
import org.osgi.service.http.HttpService;

public class Component implements IDiscovery {
	private String servletAlias;
	private HttpService http;
	private DiscoveryServlet servlet;

	public void bind(HttpService value) {
		http = value;
	}

	protected void activate() {
		try {
			String servletRoot = PropertyManager.getProperty(ICoreConstants.BACK_END_URL_PROPERTY, ICoreConstants.BACK_END_URL_DEFAULT);
			UrlBuilder urlBuilder = new UrlBuilder(servletRoot);
			urlBuilder.appendPath(IDiscoveryConstants.DISCOVERY_ACTION);
			servletAlias = urlBuilder.getPath();

			servlet = new DiscoveryServlet();
			http.registerServlet(servletAlias, servlet, null, null);
			LogUtility.logDebug(this, "Registered DiscoveryServlet at " + servletAlias);
		} catch (Exception e) {
			LogUtility.logError(this, "Error registering servlet with HttpService", e);
		}
	}

	public void unbind(HttpService value) {
		http = null;
	}

	protected void deactivate() {
		http.unregister(servletAlias);
		servlet = null;
	}

	public void addListener(IDiscoveryListener listener) {
		servlet.addListener(listener);
	}

	public String lookup(String id) {
		return servlet.lookup(id);
	}

	public void removeListener(IDiscoveryListener listener) {
		servlet.removeListener(listener);
	}
}

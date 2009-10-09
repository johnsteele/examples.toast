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
package org.eclipse.examples.toast.internal.client.tracking.config.bundle;

import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.PropertyManager;
import org.eclipse.examples.toast.internal.client.tracking.config.TrackingConfigurationServlet;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.http.HttpService;

public class Component {
	private static final String SERVLET_ALIAS_ROOT_PROPERTY = "servlet.alias.root"; //$NON-NLS-1$
	private static final String SERVLET_ALIAS_ROOT_DEFAULT = "/client"; //$NON-NLS-1$
	private static final String SERVLET_SUFFIX = "/tracking-config";

	private String servletAlias;
	private HttpService http;
	private ConfigurationAdmin configAdmin;

	public void setHttp(HttpService value) {
		http = value;
	}

	public void setConfigAdmin(ConfigurationAdmin value) {
		configAdmin = value;
	}

	protected void startup() {
		try {
			String servletAliasRoot = PropertyManager.getProperty(SERVLET_ALIAS_ROOT_PROPERTY, SERVLET_ALIAS_ROOT_DEFAULT);
			servletAlias = servletAliasRoot + SERVLET_SUFFIX;

			String id = PropertyManager.getProperty(ICoreConstants.ID_PROPERTY, ICoreConstants.ID_DEFAULT);
			TrackingConfigurationServlet servlet = new TrackingConfigurationServlet(servletAlias, id, configAdmin);
			http.registerServlet(servletAlias, servlet, null, null);
			LogUtility.logDebug("Registered TrackingConfigurationServlet at " + servletAlias);
		} catch (Exception e) {
			LogUtility.logError(this, "Error registering servlet with HttpService", e);
		}
	}

	public void clearHttp(HttpService value) {
		http = null;
	}

	public void clearConfigAdmin(ConfigurationAdmin value) {
		configAdmin = null;
	}

	protected void shutdown() {
		http.unregister(servletAlias);
	}
}

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
package org.eclipse.examples.toast.internal.client.tickle.simple.bundle;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.PropertyManager;
import org.eclipse.examples.toast.core.UrlBuilder;
import org.eclipse.examples.toast.core.tickle.IHttpTickleConstants;
import org.eclipse.examples.toast.core.tickle.ITickleListener;
import org.eclipse.examples.toast.core.tickle.ITickleReceiver;
import org.eclipse.examples.toast.internal.client.tickle.simple.SmsOverHttpTickleReceiver;
import org.osgi.service.http.HttpService;

public class Component implements ITickleReceiver {
	private SmsOverHttpTickleReceiver receiver;
	private HttpService http;
	private String servletAlias;

	public void bind(HttpService value) {
		http = value;
	}

	protected void activate() {
		try {
			UrlBuilder urlBuilder = new UrlBuilder(getTalkbackDescriptor().toString());
			servletAlias = urlBuilder.getPath();

			receiver = new SmsOverHttpTickleReceiver();
			http.registerServlet(servletAlias, receiver, null, null);
			LogUtility.logDebug(this, "Registered SMS Receiver servlet at " + urlBuilder.toUrl());
		} catch (Exception e) {
			LogUtility.logError(this, "Error registering servlet with HttpService", e);
		}
		String id = PropertyManager.getProperty(ICoreConstants.ID_PROPERTY, ICoreConstants.ID_DEFAULT);
	}

	public void unbind(HttpService value) {
		http = null;
	}

	protected void deactivate() {
		http.unregister(servletAlias);
		receiver = null;
	}

	public void addListener(ITickleListener listener) {
		receiver.addListener(listener);
	}

	public void removeListener(ITickleListener listener) {
		receiver.removeListener(listener);
	}

	public URI getTalkbackDescriptor() {
		String spec = PropertyManager.getProperty(IHttpTickleConstants.CLIENT_SMS_URL_PROPERTY, IHttpTickleConstants.CLIENT_SMS_URL_DEFAULT);
		try {
			URL url = new URL(spec);
			if (!"localhost".equalsIgnoreCase(url.getHost()))
				return new URI(spec);
			InetAddress addr = InetAddress.getLocalHost();
			// byte[] ipAddr = addr.getAddress();
			String host = addr.getHostAddress();
			return new URI("http://" + host + ":" + url.getPort() + "/" + IHttpTickleConstants.SMS_FUNCTION);
		} catch (Exception e) {
			return null;
		}
	}
}

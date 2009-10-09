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
package org.eclipse.examples.toast.internal.backend.tickle.simple;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.UrlBuilder;
import org.eclipse.examples.toast.core.discovery.IDiscovery;
import org.eclipse.examples.toast.core.tickle.IHttpTickleConstants;
import org.eclipse.examples.toast.core.tickle.ITickleSender;

public class SmsOverHttpTickleSender implements ITickleSender {

	IDiscovery discovery;

	public SmsOverHttpTickleSender() {
	}

	protected void bind(IDiscovery value) {
		discovery = value;
	}

	protected void unbind(IDiscovery value) {
		discovery = null;
	}

	private URL createTickleUrl(String location) throws MalformedURLException {
		UrlBuilder urlBuilder = new UrlBuilder(location);
		urlBuilder.addParameter(IHttpTickleConstants.ACTION_PARAMETER, IHttpTickleConstants.TICKLE_ACTION);
		URL result = urlBuilder.toUrl();
		LogUtility.logDebug(this, result.toExternalForm());
		return result;
	}

	public void tickle(String id) {
		String location = discovery.lookup(id);
		if (location == null) {
			LogUtility.logInfo(this, "Unable to tickle " + id + ".  Location unknown");
			return;
		}
		try {
			InputStream replyStream = null;
			int result;
			try {
				URL url = createTickleUrl(location);
				replyStream = url.openStream();
				result = replyStream.read();
			} finally {
				if (replyStream != null)
					replyStream.close();
			}
			if (IHttpTickleConstants.TICKLE_ACK_REPLY != result)
				LogUtility.logError(this, "Unable to tickle " + id + " at " + location + ": " + result);
		} catch (IOException e) {
			LogUtility.logError(this, "Unable to tickle " + id + " at " + location + ": " + e.getMessage());
		}
	}
}

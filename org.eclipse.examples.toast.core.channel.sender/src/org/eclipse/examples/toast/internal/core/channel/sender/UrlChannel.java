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
package org.eclipse.examples.toast.internal.core.channel.sender;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.PropertyManager;
import org.eclipse.examples.toast.core.UrlBuilder;
import org.eclipse.examples.toast.core.channel.sender.ChannelMessage;
import org.eclipse.examples.toast.core.channel.sender.IChannel;

public class UrlChannel implements IChannel {
	private String urlSpec;

	public UrlChannel() {
		super();
		urlSpec = PropertyManager.getProperty(ICoreConstants.BACK_END_URL_PROPERTY, ICoreConstants.BACK_END_URL_DEFAULT);
	}

	public InputStream send(ChannelMessage message) throws IOException {
		URL url = createUrl(urlSpec, message);
		LogUtility.logDebug(this, "Sending message: " + message);
		return url.openStream();
	}

	private URL createUrl(String urlSpec, ChannelMessage message) throws MalformedURLException {
		UrlBuilder builder = new UrlBuilder(urlSpec);
		builder.appendPath(message.getFunction());
		for (Iterator i = message.getParametersIterator(); i.hasNext();) {
			String parameter = (String) i.next();
			String value = message.valueForParameter(parameter);
			builder.addParameter(parameter, value);
		}
		URL url = builder.toUrl();
		String value = builder.toString();
		LogUtility.logDebug(this, value);
		return url;
	}
}

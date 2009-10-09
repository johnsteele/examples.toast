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
package org.eclipse.examples.toast.internal.client.discovery.simple;

import java.io.IOException;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.PropertyManager;
import org.eclipse.examples.toast.core.channel.sender.ChannelMessage;
import org.eclipse.examples.toast.core.channel.sender.IChannel;
import org.eclipse.examples.toast.core.discovery.IDiscoveryConstants;
import org.eclipse.examples.toast.core.tickle.ITickleReceiver;

public class HttpDiscoveryPublisher {
	private IChannel channel;
	private ITickleReceiver tickleReceiver;
	private String id;

	public void setChannel(IChannel value) {
		channel = value;
	}

	public void setTickleReceiver(ITickleReceiver value) {
		tickleReceiver = value;
	}

	public void clearTickleReceiver(ITickleReceiver value) {
		tickleReceiver = null;
	}

	public void startup() {
		id = PropertyManager.getProperty(ICoreConstants.ID_PROPERTY, ICoreConstants.ID_DEFAULT);
		String location = tickleReceiver.getTalkbackDescriptor().toString();
		ChannelMessage message = new ChannelMessage(IDiscoveryConstants.DISCOVERY_ACTION);
		message.addParameter(IDiscoveryConstants.OPERATION_PARAMETER, IDiscoveryConstants.REGISTER_OPERATION);
		message.addParameter(IDiscoveryConstants.ID_PARAMETER, id);
		message.addParameter(IDiscoveryConstants.LOCATION_PARAMETER, location.toString());
		try {
			channel.send(message);
		} catch (IOException e) {
			LogUtility.logInfo("Unable to register with SMS central");
		}
	}

	public void shutdown() {
		ChannelMessage message = new ChannelMessage(IDiscoveryConstants.DISCOVERY_ACTION);
		message.addParameter(IDiscoveryConstants.OPERATION_PARAMETER, IDiscoveryConstants.UNREGISTER_OPERATION);
		message.addParameter(IDiscoveryConstants.ID_PARAMETER, id);
		try {
			channel.send(message);
		} catch (IOException e) {
			LogUtility.logInfo("Unable to unregister with SMS central");
		}
		channel = null;
		tickleReceiver = null;
	}

	public void clearChannel(IChannel value) {
		channel = null;
	}
}

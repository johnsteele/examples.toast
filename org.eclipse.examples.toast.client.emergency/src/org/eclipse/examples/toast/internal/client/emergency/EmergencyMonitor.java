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
package org.eclipse.examples.toast.internal.client.emergency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.examples.toast.client.emergency.IEmergencyMonitor;
import org.eclipse.examples.toast.client.emergency.IEmergencyMonitorListener;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.PropertyManager;
import org.eclipse.examples.toast.core.channel.sender.ChannelMessage;
import org.eclipse.examples.toast.core.channel.sender.IChannel;
import org.eclipse.examples.toast.core.emergency.IEmergencyConstants;
import org.eclipse.examples.toast.dev.airbag.IAirbag;
import org.eclipse.examples.toast.dev.airbag.IAirbagListener;
import org.eclipse.examples.toast.dev.gps.IGps;

public class EmergencyMonitor implements IAirbagListener, IEmergencyMonitor {
	private IAirbag airbag;
	private IChannel channel;
	private IGps gps;
	private final String id;
	private Job job;
	private List listeners;

	public EmergencyMonitor() {
		super();
		listeners = new ArrayList(3);
		id = PropertyManager.getProperty(ICoreConstants.ID_PROPERTY, ICoreConstants.ID_DEFAULT);
	}

	public void addListener(IEmergencyMonitorListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	public void deployed() {
		startJob();
	}

	public void emergency() {
		startJob();
	}

	private void notifyFailed(Exception e) {
		synchronized (listeners) {
			for (Iterator i = listeners.iterator(); i.hasNext();) {
				IEmergencyMonitorListener listener = (IEmergencyMonitorListener) i.next();
				listener.failed(e);
			}
		}
	}

	private void notifyStarted() {
		synchronized (listeners) {
			for (Iterator i = listeners.iterator(); i.hasNext();) {
				IEmergencyMonitorListener listener = (IEmergencyMonitorListener) i.next();
				listener.started();
			}
		}
	}

	private void notifySucceeded(String reply) {
		synchronized (listeners) {
			for (Iterator i = listeners.iterator(); i.hasNext();) {
				IEmergencyMonitorListener listener = (IEmergencyMonitorListener) i.next();
				listener.succeeded(reply);
			}
		}
	}

	public void removeListener(IEmergencyMonitorListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	private void runEmergencyProcess() {
		notifyStarted();
		int latitude = gps.getLatitude();
		int longitude = gps.getLongitude();
		int heading = gps.getHeading();
		int speed = gps.getSpeed();
		ChannelMessage message = new ChannelMessage(IEmergencyConstants.EMERGENCY_FUNCTION);
		message.addParameter(ICoreConstants.ID_PARAMETER, id);
		message.addParameter(IEmergencyConstants.LATITUDE_PARAMETER, latitude);
		message.addParameter(IEmergencyConstants.LONGITUDE_PARAMETER, longitude);
		message.addParameter(IEmergencyConstants.HEADING_PARAMETER, heading);
		message.addParameter(IEmergencyConstants.SPEED_PARAMETER, speed);
		InputStream stream = null;
		try {
			try {
				stream = channel.send(message);
				InputStreamReader reader = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(reader);
				String reply = buffer.readLine();
				notifySucceeded(reply);
				LogUtility.logDebug(this, "Received reply: " + reply);
			} finally {
				if (stream != null)
					stream.close();
			}
		} catch (IOException e) {
			notifyFailed(e);
			LogUtility.logDebug(this, "Unable to send to back end: ", e);
		}
		job = null;
	}

	public void setAirbag(IAirbag value) {
		airbag = value;
	}

	public void setChannel(IChannel value) {
		channel = value;
	}

	public void setGps(IGps value) {
		gps = value;
	}

	public void shutdown() {
		stopJob();
		airbag.removeListener(this);
	}

	private void startJob() {
		if (job != null) {
			return;
		}
		job = new Job("EmergencyMonitor") {
			protected IStatus run(IProgressMonitor monitor) {
				runEmergencyProcess();
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	public void startup() {
		airbag.addListener(this);
	}

	private void stopJob() {
		if (job != null) {
			job.cancel();
			try {
				job.join();
			} catch (InterruptedException e) {
				// shutting down, ok to ignore
			}
			job = null;
		}
	}
}

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
package org.eclipse.examples.toast.internal.client.tracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.PropertyManager;
import org.eclipse.examples.toast.core.channel.sender.ChannelMessage;
import org.eclipse.examples.toast.core.channel.sender.IChannel;
import org.eclipse.examples.toast.core.tracking.ITrackingConstants;
import org.eclipse.examples.toast.dev.gps.IGps;

public class TrackingMonitor {
	private IGps gps;
	private IChannel channel;
	private Job job;
	private String id;
	private int delay;

	public TrackingMonitor() {
		super();
	}

	public void setGps(IGps value) {
		gps = value;
	}

	public void setChannel(IChannel value) {
		channel = value;
	}

	public void startup() {
		id = PropertyManager.getProperty(ICoreConstants.ID_PROPERTY);
		if (id == null)
			id = ICoreConstants.ID_DEFAULT;
		String delaySpec = PropertyManager.getProperty(ITrackingConstants.TRACKING_DELAY_PROPERTY);
		if (delaySpec == null)
			delay = ITrackingConstants.TRACKING_DELAY_DEFAULT;
		else
			delay = Integer.parseInt(delaySpec);
		LogUtility.logDebug(this, "Tracking every " + delay + " seconds");
		startJob();
	}

	public void shutdown() {
		stopJob();
	}

	// Private
	private void startJob() {
		if (job != null) {
			return;
		}
		job = createJob();
		job.schedule(delay * 1000);
	}

	private Job createJob() {
		return new Job(toString()) {
			protected IStatus run(IProgressMonitor monitor) {
				TrackingMonitor.this.runTrackingProcess();
				schedule(delay * 1000);
				return Status.OK_STATUS;
			}
		};
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

	private void runTrackingProcess() {
		int latitude = gps.getLatitude();
		int longitude = gps.getLongitude();
		int heading = gps.getHeading();
		int speed = gps.getSpeed();
		ChannelMessage message = new ChannelMessage(ITrackingConstants.TRACKING_FUNCTION);
		message.addParameter(ICoreConstants.ID_PARAMETER, id);
		message.addParameter(ITrackingConstants.LATITUDE_PARAMETER, latitude);
		message.addParameter(ITrackingConstants.LONGITUDE_PARAMETER, longitude);
		message.addParameter(ITrackingConstants.HEADING_PARAMETER, heading);
		message.addParameter(ITrackingConstants.SPEED_PARAMETER, speed);
		InputStream stream = null;
		try {
			try {
				stream = channel.send(message);
				InputStreamReader reader = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(reader);
				String reply = buffer.readLine();
				LogUtility.logDebug(this, "Received reply: " + reply);
			} finally {
				if (stream != null)
					stream.close();
			}
		} catch (IOException e) {
			LogUtility.logDebug(this, "Unable to send to back end: ", e);
		}
	}
}

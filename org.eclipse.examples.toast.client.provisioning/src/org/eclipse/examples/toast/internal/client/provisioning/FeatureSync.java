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
package org.eclipse.examples.toast.internal.client.provisioning;

import java.io.IOException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.equinox.internal.provisional.configurator.Configurator;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.tickle.ITickleListener;
import org.eclipse.examples.toast.core.tickle.ITickleReceiver;

public class FeatureSync {
	private Job job;
	private ITickleReceiver receiver;
	private ITickleListener tickleListener;
	private Configurator configurator;

	public FeatureSync() {
		super();
		job = createJob();
	}

	public void setConfigurator(Configurator value) {
		configurator = value;
	}

	public void setTicklee(ITickleReceiver value) {
		receiver = value;
	}

	public void startup() {
		tickleListener = createTickleReceiverListener();
		receiver.addListener(tickleListener);
	}

	private ITickleListener createTickleReceiverListener() {
		return new ITickleListener() {
			public void tickled() {
				FeatureSync.this.sync();
			}
		};
	}

	public void clearConfigurator(Configurator value) {
		configurator = null;
	}

	public void clearTicklee(ITickleReceiver value) {
		receiver = null;
	}

	public void shutdown() {
		receiver.removeListener(tickleListener);
	}

	public boolean sync() {
		synchronized (this) {
			if (job != null)
				return false;
			job.schedule();
			return true;
		}
	}

	// Private methods
	private void processSync() {
		try {
			configurator.applyConfiguration();
		} catch (IOException e) {
			logSyncFailed(e);
		}
	}

	private Job createJob() {
		return new Job("Features sync") {
			protected IStatus run(IProgressMonitor monitor) {
				FeatureSync.this.processSync();
				return Status.OK_STATUS;
			}
		};
	}

	private void logSyncFailed(IOException exception) {
		StringBuffer buffer = new StringBuffer(150);
		buffer.append("Unable to synchronize features with Back End (");
		String message = exception.getMessage();
		if (message != null)
			buffer.append(message);
		else
			buffer.append(exception);
		String error = buffer.toString();
		LogUtility.logError(this, error);
	}
}

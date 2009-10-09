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
package org.eclipse.examples.toast.internal.dev.airbag.fake;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.examples.toast.dev.airbag.IAirbag;
import org.eclipse.examples.toast.dev.airbag.IAirbagListener;

public class FakeAirbag implements IAirbag {
	private List listeners = new ArrayList();
	private Job job;
	private boolean isRunning;

	public synchronized void addListener(IAirbagListener listener) {
		listeners.add(listener);
	}

	private synchronized void deploy() {
		for (Iterator i = listeners.iterator(); i.hasNext();)
			((IAirbagListener) i.next()).deployed();
	}

	public synchronized void removeListener(IAirbagListener listener) {
		listeners.remove(listener);
	}

	public synchronized void shutdown() {
		isRunning = false;
		job.cancel();
		try {
			job.join();
		} catch (InterruptedException e) {
			// shutting down, safe to ignore
		}
	}

	public synchronized void startup() {
		isRunning = true;
		job = new Job("FakeAirbag") {
			protected IStatus run(IProgressMonitor monitor) {
				deploy();
				if (isRunning)
					schedule(5000);
				return Status.OK_STATUS;
			}
		};
		job.schedule(5000);
	}
}

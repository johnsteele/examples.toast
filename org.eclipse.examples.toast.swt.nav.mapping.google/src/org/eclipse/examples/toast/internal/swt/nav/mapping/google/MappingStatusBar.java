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
package org.eclipse.examples.toast.internal.swt.nav.mapping.google;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.examples.toast.crust.shell.DisplayBlock;
import org.eclipse.swt.widgets.Label;

public class MappingStatusBar {
	private static final int DISPLAY_STATUS_TIME_MILLIS = 3000;
	private Label statusBarLabel;
	private boolean hasNewStreetName = false;
	private boolean displayingGuidance = true;
	private String guidance;
	private String streetName;
	private Job job;

	public MappingStatusBar(Label statusBarLabel) {
		super();
		this.statusBarLabel = statusBarLabel;
		createJob();
		updateStatusBar();
	}

	public void shutDown() {
		job.cancel();
		try {
			job.join();
		} catch (InterruptedException e) {
			// shutting down, ok to ignore
		}
	}

	public void displayStatusLabel(String streetName) {
		synchronized (this) {
			this.streetName = streetName;
			hasNewStreetName = true;
		}
		job.cancel();
		job.schedule();
	}

	public void updateGuidance(String guidance) {
		this.guidance = guidance;
		if (displayingGuidance) {
			updateStatusBar();
		}
	}

	// Private
	private void createJob() {
		job = new Job("MappingStatusBar") {
			protected IStatus run(IProgressMonitor monitor) {
				synchronized (this) {
					if (hasNewStreetName) {
						hasNewStreetName = false;
						displayingGuidance = false;
					} else
						displayingGuidance = true;
				}
				updateStatusBar();
				if (!displayingGuidance)
					schedule(MappingStatusBar.DISPLAY_STATUS_TIME_MILLIS);
				return Status.OK_STATUS;
			}
		};
	}

	private void updateStatusBar() {
		if (statusBarLabel == null) {
			return;
		}
		new DisplayBlock() {
			public void run() {
				if (displayingGuidance) {
					if (guidance != null) {
						statusBarLabel.setText(guidance);
					} else {
						statusBarLabel.setText(new String());
					}
				} else {
					if (streetName != null) {
						statusBarLabel.setText(streetName);
					} else {
						statusBarLabel.setText(new String());
					}
				}
				statusBarLabel.update();
			};
		}.sync();
	}
}

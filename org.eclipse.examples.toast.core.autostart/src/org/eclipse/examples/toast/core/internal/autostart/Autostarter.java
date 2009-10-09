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
package org.eclipse.examples.toast.core.internal.autostart;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.util.tracker.BundleTracker;
import org.osgi.util.tracker.BundleTrackerCustomizer;

public class Autostarter implements BundleActivator, BundleTrackerCustomizer {
	BundleTracker tracker;

	public void start(BundleContext context) throws Exception {
		tracker = new BundleTracker(context, Bundle.RESOLVED | Bundle.STARTING, this);
		tracker.open();
	}

	private void startBundle(Bundle bundle) {
		if (bundle.getHeaders().get(Constants.FRAGMENT_HOST) == null)
			try {
				bundle.start();
			} catch (BundleException e) {
				// TODO do nothing for now
			}
	}

	public void stop(BundleContext context) throws Exception {
		tracker.close();
	}

	public Object addingBundle(Bundle bundle, BundleEvent event) {
		startBundle(bundle);
		return bundle;
	}

	public void modifiedBundle(Bundle bundle, BundleEvent event, Object object) {
		// TODO Auto-generated method stub
	}

	public void removedBundle(Bundle bundle, BundleEvent event, Object object) {
		// TODO Auto-generated method stub
	}
}

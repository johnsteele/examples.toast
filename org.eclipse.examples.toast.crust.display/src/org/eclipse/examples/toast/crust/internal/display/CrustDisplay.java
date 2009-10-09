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
package org.eclipse.examples.toast.crust.internal.display;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.examples.toast.crust.display.ICrustDisplay;
import org.eclipse.examples.toast.crust.internal.display.bundle.Activator;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class CrustDisplay implements IApplication, ICrustDisplay {
	private Display display;
	private boolean isRunning;
	private ServiceRegistration serviceReference;

	public Object start(IApplicationContext context) throws Exception {
		display = Display.getDefault();
		context.applicationRunning();
		BundleContext bundleContext = Activator.getBundleContext();
		serviceReference = bundleContext.registerService(ICrustDisplay.class.getName(), this, null);

		try {
			runDisplayEventQueue();
		} finally {
			display.dispose();
			// display = null;
		}
		return IApplication.EXIT_OK;
	}

	public Display getDisplay() {
		return display;
	}

	public void shutdown() {
		// this needs to be passed to another thread because if 
		// the display thread were to call this method it will deadlock
		new Thread(new Runnable() {
			public void run() {
				if (!isRunning)
					return;
				serviceReference.unregister();
				isRunning = false;
				display.wake();
			}
		}).start();
	}

	private void runDisplayEventQueue() {
		isRunning = true;
		while (isRunning && !display.isDisposed())
			try {
				if (!display.readAndDispatch())
					display.sleep();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		isRunning = false;
	}

	public void stop() {
		shutdown();
	}
}

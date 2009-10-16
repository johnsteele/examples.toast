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
package org.eclipse.examples.toast.internal.dev.google;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;

public class BrowserCallbacks {

	List functions = new ArrayList();
	Browser browser;

	static class PositionCallback extends BrowserFunction {
		public PositionCallback(Browser browser, String name) {
			super(browser, name);
		}

		public Object function(Object[] arguments) {
			System.out.println("Position:");
			for (int i = 0; i < arguments.length; i++) {
				Object arg = arguments[i];
				if (arg == null) {
					System.out.println("\t-->null");
				} else {
					System.out.println("\t-->" + arg.getClass().getName() + ": " + arg.toString());
				}
			}
			return null;
		}
	}

	public BrowserCallbacks(Browser browser) {
		this.browser = browser;
	}

	public void startup() {
		browser.addProgressListener(new ProgressAdapter() {
			public void completed(ProgressEvent event) {
				browser.addLocationListener(new LocationAdapter() {
					public void changed(LocationEvent event) {
						browser.removeLocationListener(this);
						disposeFunctions();
					}
				});
			}
		});
		registerFunction(new PositionCallback(browser, "toast_position_callback"));
	}

	public void shutdown() {
		disposeFunctions();
	}

	private void disposeFunctions() {
		for (Iterator i = functions.iterator(); i.hasNext();)
			((BrowserFunction) i.next()).dispose();
		functions.clear();
	}

	public void registerFunction(BrowserFunction function) {
		functions.add(function);
	}
}
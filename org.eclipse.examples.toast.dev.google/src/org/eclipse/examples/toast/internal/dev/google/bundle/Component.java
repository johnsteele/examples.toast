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
package org.eclipse.examples.toast.internal.dev.google.bundle;

import java.io.IOException;
import java.net.URL;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.crust.internal.shell.CrustShell;
import org.eclipse.examples.toast.crust.shell.ICrustShell;
import org.eclipse.osgi.service.urlconversion.URLConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Component {

	private Browser browser;
	private ICrustShell shell;
	private ServiceRegistration browserRegistration;
	private URLConverter converter;

	public void setShell(ICrustShell value) {
		shell = value;
	}

	public void setConverter(URLConverter value) {
		converter = value;
	}

	public void startup(final BundleContext context) {
		browser = new Browser(((CrustShell) shell).getShell(), SWT.NONE);
		browser.setBounds(new Rectangle(115, 22, 510, 318));
		browser.setVisible(false);
		URL url = this.getClass().getResource("index.html");
		try {
			url = converter.toFileURL(url);
		} catch (IOException e) {
			LogUtility.logDebug("Unable to find home page: " + url);
			return;
		}
		// work around a quirk in the browser support.  Currently it is unclear which 
		// platform is wrong.  Suspect that the Mac is right here but who knows.
		if (System.getProperty("osgi.os").equals("macosx")) {
			browser.addProgressListener(new ProgressAdapter() {
				public void completed(ProgressEvent event) {
					browser.removeProgressListener(this);
					browserRegistration = context.registerService(Browser.class.getName(), browser, null);
				}
			});
		} else {
			browser.addProgressListener(new ProgressAdapter() {
				public void completed(ProgressEvent event) {
					browser.removeProgressListener(this);
					browser.addLocationListener(new LocationAdapter() {
						public void changed(LocationEvent event) {
							browser.removeLocationListener(this);
							browserRegistration = context.registerService(Browser.class.getName(), browser, null);
						}
					});
				}
			});
		}
		browser.setUrl(url.toExternalForm());
	}

	public void shutdown() {
		if (browserRegistration == null)
			return;
		browserRegistration.unregister();
		browserRegistration = null;
		browser = null;
	}

}

/*******************************************************************************
 * Copyright (c) 2009 EclipseSource Inc. and others. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v1.0 
 * which accompanies this distribution. The Eclipse Public License is available at 
 * http://www.eclipse.org/legal/epl-v10.html and the Eclipse Distribution License 
 * is available at http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors: 
 *     EclipseSource Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.examples.toast.rap.drivingsimulator.resource;

import org.eclipse.rwt.resources.IResource;
import org.eclipse.rwt.resources.IResourceManager.RegisterOptions;

public class DrivingSimulatorAPIResource implements IResource {

	public final static String CHARSET_NAME_ISO_8859_1 = "ISO-8859-1";
	private static final String KEY_SYSTEM_PROPERTY = "org.eclipse.rap.gmaps.key";
	// key for 127.0.0.1 on port 9090
	private static final String KEY_LOCALHOST = "ABQIAAAAjE6itH-9WA-8yJZ7sZwmpR" + "TH9RyEtJJH1hs9r3R0BHLa3x4BSxSDjaazykLKmEHGTfa2eq9-MPG-jQ";
	//  private static final String KEY_LOCALHOST = "ABQIAAAADaXzprMke0eXhl5lyjwBJh" +
	//  "S0L_FtOfYiN54Hgypzbg76-pLISRQUEcazuZ-K1uTfH-TGP4mTfexI8w";
	private String location;

	public String getCharset() {
		return CHARSET_NAME_ISO_8859_1;
	}

	public ClassLoader getLoader() {
		return this.getClass().getClassLoader();
	}

	public String getLocation() {
		if (location == null) {
			String key = System.getProperty(KEY_SYSTEM_PROPERTY);
			if (key == null) {
				key = KEY_LOCALHOST;
			}
			location = "http://www.google.com/jsapi?hl=en&key=" + key;
		}
		return location;
	}

	public RegisterOptions getOptions() {
		return RegisterOptions.VERSION;
	}

	public boolean isExternal() {
		return true;
	}

	public boolean isJSLibrary() {
		return true;
	}
}

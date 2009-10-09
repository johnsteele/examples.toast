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
package org.eclipse.examples.toast.rap.gmaps;

import org.eclipse.rwt.resources.IResource;
import org.eclipse.rwt.resources.IResourceManager.RegisterOptions;

public class GMapAPIResource implements IResource {

	private static final String KEY_SYSTEM_PROPERTY = "org.eclipse.examples.toast.rap.gmaps.key";
	// key for 127.0.0.1 on port 9090
	private static final String KEY_LOCALHOST = "ABQIAAAAjE6itH-9WA-8yJZ7sZwmpRTH9RyEtJJH1hs9r3R0BHLa3x4BSxSDjaazykLKmEHGTfa2eq9-MPG-jQ";
	private String location;

	public String getCharset() {
		return "UTF-8";
	}

	public ClassLoader getLoader() {
		return this.getClass().getClassLoader();
	}

	public RegisterOptions getOptions() {
		return RegisterOptions.NONE;
	}

	public String getLocation() {
		if (location == null) {
			String key = System.getProperty(KEY_SYSTEM_PROPERTY);
			if (key == null) {
				key = KEY_LOCALHOST;
			}
			location = "http://www.google.com/jsapi?key=" + key;
		}
		return location;
	}

	public boolean isJSLibrary() {
		return true;
	}

	public boolean isExternal() {
		return true;
	}
}

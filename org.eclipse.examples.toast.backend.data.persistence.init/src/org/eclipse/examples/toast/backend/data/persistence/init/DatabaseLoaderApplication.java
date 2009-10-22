/*******************************************************************************
 * Copyright (c) 2009 Oracle and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: 
 *     Shaun Smith - initial API and implementation
 *     EclipseSource - Additional work
 *******************************************************************************/
package org.eclipse.examples.toast.backend.data.persistence.init;

import org.eclipse.examples.toast.backend.data.loader.DataLoader;
import org.eclipse.examples.toast.backend.data.loader.DataLoaderApplication;

public class DatabaseLoaderApplication extends DataLoaderApplication {

	protected DataLoader createDataLoader() {
		return new DatabaseLoader();
	}

	protected void processParameter(String arg, String parameter) {
		super.processParameter(arg, parameter);
		if (arg.equalsIgnoreCase("-destination"))
			((DatabaseLoader) loader).destination = parameter;
	}
}

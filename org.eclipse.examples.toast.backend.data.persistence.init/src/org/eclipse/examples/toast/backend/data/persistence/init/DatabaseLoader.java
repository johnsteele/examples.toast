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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.eclipse.examples.toast.backend.data.loader.DataLoader;
import org.eclipse.examples.toast.backend.data.persistence.ToastDataManager;

public class DatabaseLoader extends DataLoader {

	protected String destination;
	private ToastDataManager manager;

	/**
	 * Call back from the DataLoader superclass to actually create the objects.
	 * Here we build them all and then persist them.
	 */
	protected Collection buildObjects() {
		// TODO work out a way of building and persisting incrementally.
		Collection result = super.buildObjects();
		for (Iterator i = result.iterator(); i.hasNext();)
			manager.persist((Object) i.next());
		return result;
	}

	public List run() {
		manager = new ToastDataManager();
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("eclipselink.jdbc.url", destination);
		properties.put("eclipselink.jdbc.user", "app");
		properties.put("eclipselink.jdbc.password", "app");
		properties.put("eclipselink.jdbc.driver", "org.apache.derby.jdbc.ClientDriver");
		properties.put("eclipselink.ddl-generation", "drop-and-create-tables");
		properties.put("eclipselink.ddl-generation.output-mode", "database");
		manager.startup(properties);

		return super.run();
	}
}

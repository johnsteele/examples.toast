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
package org.eclipse.examples.toast.internal.core;

import org.eclipse.examples.toast.core.spi.IStatusIdFinder;
import org.osgi.framework.Bundle;
import org.osgi.service.packageadmin.PackageAdmin;

public class StatusIdFinder implements IStatusIdFinder {
	private PackageAdmin packageAdmin;

	protected void bind(PackageAdmin admin) {
		packageAdmin = admin;
	}

	protected void unbind(PackageAdmin admin) {
		packageAdmin = null;
	}

	public String getStatusId(Object object) {
		if (packageAdmin == null)
			return "org.eclipse.examples.toast.core.default";
		Bundle bundle = packageAdmin.getBundle(object.getClass());
		if (bundle == null)
			return "org.eclipse.examples.toast.core.default";
		return bundle.getSymbolicName();
	}
}

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
package org.eclipse.examples.toast.internal.backend.rap.bundle;

import org.eclipse.examples.toast.backend.controlcenter.IControlCenter;
import org.eclipse.examples.toast.backend.provisioning.IProvisioner;
import org.eclipse.examples.toast.backend.rap.SearchView;

public class Component {

	private static IProvisioner provisioner;
	private static IControlCenter controlCenter;

	public static IControlCenter getControlCenter() {
		return controlCenter;
	}

	public void setControlCenter(IControlCenter value) {
		SearchView.controlCenter = value;
		controlCenter = value;
	}

	public static IProvisioner getProvisioner() {
		return provisioner;
	}

	public void setProvisioner(IProvisioner value) {
		provisioner = value;
	}

}

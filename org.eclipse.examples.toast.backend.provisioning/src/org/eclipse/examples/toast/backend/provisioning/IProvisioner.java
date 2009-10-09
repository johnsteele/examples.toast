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
package org.eclipse.examples.toast.backend.provisioning;

import java.util.Collection;
import java.util.Map;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

public interface IProvisioner {

	public abstract Collection getInstalled(String id);

	public abstract IStatus install(String id, String feature, IProgressMonitor monitor);

	public abstract IStatus uninstall(String id, String feature, IProgressMonitor monitor);

	public abstract void addProfile(String id, Map properties);

	public abstract Collection getAvailableFeatures();

	public abstract Collection getAvailableFeatures(String id);

	public Collection getProfiles();

	public void removeProfile(String id);

}
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
package org.eclipse.examples.toast.internal.backend.provisioning.ui;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.examples.toast.backend.portal.spi.IPortalAction;
import org.eclipse.examples.toast.backend.provisioning.IProvisioner;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.tickle.ITickleSender;

public class InstallAction implements IPortalAction {

	private IProvisioner provisioner;
	private ITickleSender tickler;

	public void clearProvisioner(IProvisioner value) {
		provisioner = null;
	}

	public void clearTickler(ITickleSender value) {
		tickler = null;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter(ICoreConstants.ID_PARAMETER);
		String feature = request.getParameter("feature");
		provisioner.install(id, feature, null);
		tickler.tickle(id);
		String home = request.getRequestURI() + "?action=browse/manage&id=" + id;
		response.sendRedirect(home);
	}

	public void setProvisioner(IProvisioner value) {
		provisioner = value;
	}

	public void setTickler(ITickleSender value) {
		tickler = value;
	}
}

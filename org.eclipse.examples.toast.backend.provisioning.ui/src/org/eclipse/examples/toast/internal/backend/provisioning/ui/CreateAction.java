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
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.examples.toast.backend.controlcenter.IControlCenter;
import org.eclipse.examples.toast.backend.portal.spi.IPortalAction;
import org.eclipse.examples.toast.core.ICoreConstants;

public class CreateAction implements IPortalAction {

	private IControlCenter center;

	protected void setControlCenter(IControlCenter value) {
		center = value;
	}

	protected void clearControlCenter(IControlCenter value) {
		center = null;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter(ICoreConstants.ID_PARAMETER);
		if (id == null)
			getParameters(request, response);
		else
			createVehicle(request, response, id);
	}

	private void getParameters(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuffer buffer = new StringBuffer(2048);
		WebPageGenerator.writeHeader(buffer, "Create a Vehicle");
		buffer.append("<form name=\"idForm\" action=\"toast\" method=\"GET\">\n");
		buffer.append("Create New Vehicle: <input type=text name=id>\n");
		buffer.append("<select name = \"config\">\n");
		buffer.append("<option value=\"win32,win32,x86\">Windows\n");
		buffer.append("<option value=\"macos,carbon,x86\">Mac\n");
		buffer.append("</select>\n");
		buffer.append("<input type=submit value=\"Create\">\n");
		buffer.append("<input type=hidden name=action value=create>\n");
		buffer.append("</form>\n");
		WebPageGenerator.writeFooter(buffer);
		response.getWriter().print(buffer.toString());
	}

	private void createVehicle(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
		String config = request.getParameter(ICoreConstants.CONFIG_PARAMETER);
		String[] segments = config.split(",");
		Map properties = new HashMap();
		properties.put("osgi.os", segments[0]);
		properties.put("osgi.ws", segments[1]);
		properties.put("osgi.arch", segments[2]);
		center.addVehicle(id, properties);
		String home = request.getRequestURI() + "?action=browse&id=" + id;
		response.sendRedirect(home);
	}
}

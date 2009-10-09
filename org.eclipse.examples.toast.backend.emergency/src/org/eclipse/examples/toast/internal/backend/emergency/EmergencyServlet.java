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
package org.eclipse.examples.toast.internal.backend.emergency;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.examples.toast.backend.controlcenter.IControlCenter;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.emergency.IEmergencyConstants;

public class EmergencyServlet extends HttpServlet {
	private IControlCenter center;

	public EmergencyServlet(IControlCenter center) {
		super();
		this.center = center;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = getParameter(request, response, ICoreConstants.ID_PARAMETER);
		int latitude = Integer.parseInt(getParameter(request, response, IEmergencyConstants.LATITUDE_PARAMETER));
		int longitude = Integer.parseInt(getParameter(request, response, IEmergencyConstants.LONGITUDE_PARAMETER));
		int heading = Integer.parseInt(getParameter(request, response, IEmergencyConstants.HEADING_PARAMETER));
		int speed = Integer.parseInt(getParameter(request, response, IEmergencyConstants.SPEED_PARAMETER));
		center.emergency(id, latitude, longitude, heading, speed);
		PrintWriter writer = response.getWriter();
		writer.print("Help is on its way!");
		response.setContentType(ICoreConstants.CONTENT_TYPE_PLAIN);
	}

	private String getParameter(HttpServletRequest request, HttpServletResponse response, String parameter) throws IOException {
		String value = request.getParameter(parameter);
		if (value == null || value.length() == 0)
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, ICoreConstants.MISSING_PARAMETER + parameter);
		return value;
	}
}
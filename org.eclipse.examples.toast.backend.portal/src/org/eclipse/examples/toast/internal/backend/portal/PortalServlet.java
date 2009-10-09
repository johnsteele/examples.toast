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
package org.eclipse.examples.toast.internal.backend.portal;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.examples.toast.backend.controlcenter.IControlCenter;
import org.eclipse.examples.toast.backend.portal.spi.IActionLookup;
import org.eclipse.examples.toast.backend.portal.spi.IPortalAction;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.LogUtility;

public class PortalServlet extends HttpServlet {
	private static final String CONTENT_TYPE_HTML = "text/html";

	private IControlCenter center;
	private IActionLookup lookup;

	public PortalServlet(IControlCenter center, IActionLookup lookup) {
		super();
		this.center = center;
		this.lookup = lookup;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE_HTML);
		String id = request.getParameter(ICoreConstants.ID_PARAMETER);
		String actionParameter = request.getParameter(IPortalConstants.ACTION_PARAMETER);
		if ((id == null || id.length() == 0) && (actionParameter == null || actionParameter.length() == 0)) {
			handleRootRequest(request, response);
			return;
		}
		try {
			IPortalAction action = lookup.acquire(actionParameter);
			if (action == null)
				handleDefaultRequest(response, id, actionParameter);
			else
				try {
					action.execute(request, response);
				} finally {
					lookup.release(actionParameter, action);
				}
		} catch (Exception exception) {
			handleException(exception);
		}
	}

	private void handleRootRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		new WebPageGenerator(center, lookup).generateRoot(request, response);
	}

	private void handleDefaultRequest(HttpServletResponse response, String id, String action) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.print("Unknown request for id: "); //$NON-NLS-1$
		writer.print(id);
		writer.print(" action: "); //$NON-NLS-1$
		writer.print(action);
		writer.println();
	}

	private void handleException(Throwable exception) throws IOException {
		// TODO should return a 503 or something
		if (exception == null)
			return; // Early return.
		LogUtility.logError(this, "Error occurred while processing HTTP request", exception);
	}
}
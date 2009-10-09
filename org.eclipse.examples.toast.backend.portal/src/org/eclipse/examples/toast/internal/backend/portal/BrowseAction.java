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
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.examples.toast.backend.portal.spi.IActionLookup;
import org.eclipse.examples.toast.backend.portal.spi.IPortalAction;
import org.eclipse.examples.toast.core.ICoreConstants;

public class BrowseAction implements IPortalAction {

	private IActionLookup actionLookup;

	public void clearActionLookup(IActionLookup value) {
		actionLookup = null;
	}

	public void setActionLookup(IActionLookup value) {
		actionLookup = value;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		generateBrowseVehicle(request, response);
	}

	private void generateBrowseVehicle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter(ICoreConstants.ID_PARAMETER);
		StringBuffer buffer = new StringBuffer(2048);
		WebPageGenerator.writeHeader(buffer, WebPageGenerator.TITLE + id);

		String thisAction = request.getParameter(IPortalConstants.ACTION_PARAMETER);
		Collection actions = actionLookup.getAvailable(thisAction);
		for (Iterator i = actions.iterator(); i.hasNext();) {
			String action = (String) i.next();
			String label = actionLookup.getActionProperty(action, "label");
			if (label != null) {
				String link = WebPageGenerator.createLink(label, action, ICoreConstants.ID_PARAMETER, id);
				buffer.append("\t\t" + link + "<br/>\n");
			}
		}
		buffer.append("\t\t<br/>\n");
		buffer.append("\t\t<a href=\"" + WebPageGenerator.servletAlias + "\"/>Back to Toast home</a>\n");
		WebPageGenerator.writeFooter(buffer);
		response.getWriter().print(buffer.toString());
	}
}

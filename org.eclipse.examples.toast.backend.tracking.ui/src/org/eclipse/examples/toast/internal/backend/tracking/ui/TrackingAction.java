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
package org.eclipse.examples.toast.internal.backend.tracking.ui;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.examples.toast.backend.portal.spi.IPortalAction;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.UrlBuilder;

public class TrackingAction implements IPortalAction {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		generateTracking(request, response);
	}

	public void generateTracking(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String thisAction = request.getParameter(IPortalConstants.ACTION_PARAMETER);
		String id = request.getParameter(ICoreConstants.ID_PARAMETER);
		StringBuffer buffer = new StringBuffer(2048);
		WebPageGenerator.writeHeader(buffer, WebPageGenerator.TITLE + id);

		buffer.append("\t\t<h3>Vehicle Tracking</h3>\n");
		UrlBuilder urlBuilder = new UrlBuilder(WebPageGenerator.servletAlias);
		urlBuilder.addParameter(IPortalConstants.ACTION_PARAMETER, IPortalConstants.TRACKING_MAP_ACTION);
		urlBuilder.addParameter(ICoreConstants.ID_PARAMETER, id);
		buffer.append("\t\t<iframe width=\"619\" height=\"583\" src=\"" + urlBuilder + "\" frameborder=\"1\" ");
		buffer.append("hspace=\"0\" vspace=\"0\" topmargin=\"0\" leftmargin=\"0\" scrolling=\"no\"></iframe>\n");
		buffer.append("\t\t<br/>\n");
		String link = WebPageGenerator.createLink("Reload Page", thisAction, ICoreConstants.ID_PARAMETER, id); //$NON-NLS-1$
		buffer.append("\t\t" + link + "\n");
		buffer.append("\t\t<br/>\n");
		link = WebPageGenerator.createLink("Back to " + id + "'s home page", IPortalConstants.BROWSE_ACTION, ICoreConstants.ID_PARAMETER, id); //$NON-NLS-1$
		buffer.append("\t\t" + link + "\n");
		buffer.append("\t\t<br/>\n");
		buffer.append("\t\t<a href=\"" + WebPageGenerator.servletAlias + "\"/>Back to Toast home</a>\n");
		WebPageGenerator.writeFooter(buffer);
		response.getWriter().print(buffer.toString());
	}
}

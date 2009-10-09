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
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.examples.toast.backend.controlcenter.IControlCenter;
import org.eclipse.examples.toast.backend.data.ITrackedLocation;
import org.eclipse.examples.toast.backend.data.IVehicle;
import org.eclipse.examples.toast.backend.portal.spi.IPortalAction;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.UrlBuilder;

public class TrackingMapAction implements IPortalAction {
	private static final String MAP = "dcmap.gif";

	private IControlCenter center;

	protected void setControlCenter(IControlCenter value) {
		center = value;
	}

	protected void clearControlCenter(IControlCenter value) {
		center = null;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		generateTrackingMap(request, response);
	}

	private void generateTrackingMap(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter(ICoreConstants.ID_PARAMETER);
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<html>\n");
		buffer.append("\t<meta http-equiv=\"refresh\" content=\"5\">\n");
		buffer.append("\t<body>\n");
		UrlBuilder urlBuilder = new UrlBuilder(WebPageGenerator.servletAlias);
		urlBuilder.appendPath(IPortalConstants.RESOURCE_IMAGES_ALIAS);
		String resourcesAlias = urlBuilder.getPath();
		urlBuilder.appendPath(MAP);
		buffer.append("\t\t<div style=\"background-image:url('" + urlBuilder.getPath() + "'); width:595px; height:553px\">\n");

		IVehicle vehicle = center.getVehicle(id);
		ITrackedLocation loc = null;
		if (vehicle != null && vehicle.getCurrentLocation() != null)
			loc = vehicle.getCurrentLocation();
		if (loc != null) {
			String location = resourcesAlias + "/1.gif";
			int lon = convertLongitude(loc.getLongitude());
			int lat = convertLatitude(loc.getLatitude());
			buffer.append("\t\t\t<img src=\"" + location + "\" style=\"left:" + lon + "px; top:" + lat + "px; position:absolute\">\n");
		}
		buffer.append("\t\t</div>\n");
		if (loc == null)
			buffer.append("\t\t<i>No tracking history...</i><br/>\n");
		buffer.append("\t</body>\n");
		buffer.append("</html>\n");
		String text = buffer.toString();
		PrintWriter writer = response.getWriter();
		writer.print(text);
	}

	private int convertLatitude(int tudValue) {
		int originTud = 3892995;
		int cornerTud = 3886020;
		int rangeTud = cornerTud - originTud;
		int deltaTud = tudValue - originTud;
		int origin = 7;
		int corner = 553;
		int range = corner - origin;
		return (deltaTud * range + rangeTud / 2) / rangeTud + origin;
	}

	private int convertLongitude(int tudValue) {
		int originTud = -7708380;
		int cornerTud = -7698765;
		int rangeTud = cornerTud - originTud;
		int deltaTud = tudValue - originTud;
		int origin = 3;
		int corner = 595;
		int range = corner - origin;
		return (deltaTud * range + rangeTud / 2) / rangeTud + origin;
	}
}

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
package org.eclipse.examples.toast.internal.client.tracking.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.tracking.ITrackingConstants;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class TrackingConfigurationServlet extends HttpServlet {
	private static final String TRACKING_DELAY_PARAMETER = "delay"; //$NON-NLS-1$
	private static final String ILLEGAL_DELAY_VALUE = "Illegal delay value: ";
	public static final String SERVICE_PID = "service.pid";
	public static final String CONTENT_TYPE_HTML = "text/html";

	private ConfigurationAdmin configAdmin;
	private String servletAlias;
	private String id;

	public TrackingConfigurationServlet(String servletAlias, String id, ConfigurationAdmin configAdmin) {
		super();
		this.servletAlias = servletAlias;
		this.id = id;
		this.configAdmin = configAdmin;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE_HTML);
		String delayString = request.getParameter(TRACKING_DELAY_PARAMETER);
		if (delayString != null) {
			int delay = Integer.parseInt(delayString);
			if (delay == 0) {
				response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, ILLEGAL_DELAY_VALUE + delayString);
				return;
			}
			updateDelay(delay);
		}
		generateResponse(request, response);
	}

	private void generateResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		generateHeader(writer);
		generateTrackingLinks(writer);
		generateFooter(writer);
	}

	private void generateHeader(PrintWriter writer) {
		writer.println("<html>");
		writer.println("<head>");
		writer.print("<title>Tracking Configurator for ");
		writer.print(id);
		writer.println("</title>");
		writer.println("</head>");
	}

	private void generateTrackingLinks(PrintWriter writer) {
		int delay = getCurrentDelay();
		writer.println("<h3>Tracking Delay:</h3>");
		writer.println("<ul>");
		writer.print("<li> current value: ");
		writer.print(delay);
		writer.println(" sec</li>");
		writer.print("<li><a href=\"");
		writer.print(servletAlias);
		writer.print("?delay=5\">5 sec</a></li>");
		writer.print("<li><a href=\"");
		writer.print(servletAlias);
		writer.print("?delay=10\">10 sec</a></li>");
		writer.print("<li><a href=\"");
		writer.print(servletAlias);
		writer.print("?delay=30\">30 sec</a></li>");
		writer.print("<li><a href=\"");
		writer.print(servletAlias);
		writer.print("?delay=60\">60 sec</a></li>");
		writer.println("</ul>");
	}

	private void generateFooter(PrintWriter writer) {
		writer.println("</html>");
	}

	private int getCurrentDelay() {
		try {
			Configuration config = configAdmin.getConfiguration(ITrackingConstants.TRACKING_PID, ITrackingConstants.TRACKING_LOCATION);
			Dictionary properties = config.getProperties();
			int delay = ITrackingConstants.TRACKING_DELAY_DEFAULT;
			if (properties != null) {
				Integer delayInteger = (Integer) properties.get(ITrackingConstants.TRACKING_DELAY_PROPERTY);
				delay = delayInteger.intValue();
			}
			return delay;
		} catch (IOException e) {
			LogUtility.logError(this, "Unable to get tracking configuration: ", e);
		}
		return 0;
	}

	private void updateDelay(int delay) {
		try {
			Configuration config = configAdmin.getConfiguration(ITrackingConstants.TRACKING_PID, ITrackingConstants.TRACKING_LOCATION);
			Dictionary properties = config.getProperties();
			if (properties == null) {
				properties = new Hashtable();
				properties.put(SERVICE_PID, ITrackingConstants.TRACKING_PID);
			}
			Integer delayInteger = new Integer(delay);
			properties.put(ITrackingConstants.TRACKING_DELAY_PROPERTY, delayInteger);
			config.update(properties);
		} catch (IOException e) {
			LogUtility.logError(this, "Unable to update tracking configuration: ", e);
		}
	}
}

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
package org.eclipse.examples.toast.internal.client.tickle.simple;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.tickle.IHttpTickleConstants;
import org.eclipse.examples.toast.core.tickle.ITickleListener;

public class SmsOverHttpTickleReceiver extends HttpServlet {
	private List listeners = new ArrayList(10);

	public SmsOverHttpTickleReceiver() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(IHttpTickleConstants.CONTENT_TYPE_PLAIN);
		String action = request.getParameter(IHttpTickleConstants.ACTION_PARAMETER);
		try {
			PrintWriter writer = response.getWriter();
			if (IHttpTickleConstants.TICKLE_ACTION.equalsIgnoreCase(action)) {
				LogUtility.logDebug(this, "Tickled");
				notifyListeners();
				writer.println(IHttpTickleConstants.TICKLE_ACK_REPLY);
			} else {
				writer.print("Unknown request from: ");
				writer.print(request.getRemoteHost());
				writer.print("action: "); //$NON-NLS-1$
				writer.print(action);
				writer.println();
			}
		} catch (Exception exception) {
			LogUtility.logError(this, "Error occurred while processing HTTP request", exception);
		}
	}

	public void addListener(ITickleListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	public void removeListener(ITickleListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	private void notifyListeners() {
		synchronized (listeners) {
			Iterator iterator = listeners.iterator();
			while (iterator.hasNext()) {
				ITickleListener listener = (ITickleListener) iterator.next();
				listener.tickled();
			}
		}
	}
}

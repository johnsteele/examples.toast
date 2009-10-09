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
package org.eclipse.examples.toast.internal.backend.tickle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.examples.toast.core.discovery.IDiscovery;
import org.eclipse.examples.toast.core.discovery.IDiscoveryConstants;
import org.eclipse.examples.toast.core.discovery.IDiscoveryListener;

public class DiscoveryServlet extends HttpServlet implements IDiscovery {
	private static final int UNREGISTER = 1;
	private static final int REGISTER = 0;

	private Map receiversById;
	private List listeners;

	public DiscoveryServlet() {
		super();
		receiversById = new HashMap(13);
		listeners = new ArrayList(10);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter(IDiscoveryConstants.ID_PARAMETER);
		String operation = request.getParameter(IDiscoveryConstants.OPERATION_PARAMETER);
		if (operation.equals(IDiscoveryConstants.REGISTER_OPERATION)) {
			String location = request.getParameter(IDiscoveryConstants.LOCATION_PARAMETER);
			register(id, location);
		} else {
			unregister(id);
		}
		response.setContentType(IDiscoveryConstants.CONTENT_TYPE_PLAIN);
		response.getWriter().print(IDiscoveryConstants.ACK_REPLY);
	}

	public void register(String id, String location) {
		receiversById.put(id, location);
		notifyListeners(id, REGISTER);
	}

	public void unregister(String id) {
		receiversById.remove(id);
		notifyListeners(id, UNREGISTER);
	}

	public void addListener(IDiscoveryListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	public void removeListener(IDiscoveryListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	private void notifyListeners(String id, int operation) {
		synchronized (listeners) {
			for (Iterator i = listeners.iterator(); i.hasNext();) {
				IDiscoveryListener listener = (IDiscoveryListener) i.next();
				if (operation == REGISTER)
					listener.registered(id, null);
				else
					listener.unregistered(id);
			}
		}
	}

	public String lookup(String id) {
		return (String) receiversById.get(id);
	}
}
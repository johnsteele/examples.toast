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
package org.eclipse.examples.toast.internal.backend.portal.bundle;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.examples.toast.backend.controlcenter.IControlCenter;
import org.eclipse.examples.toast.backend.portal.spi.IActionLookup;
import org.eclipse.examples.toast.backend.portal.spi.IPortalAction;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.LogUtility;
import org.eclipse.examples.toast.core.PropertyManager;
import org.eclipse.examples.toast.core.UrlBuilder;
import org.eclipse.examples.toast.internal.backend.portal.IPortalConstants;
import org.eclipse.examples.toast.internal.backend.portal.PortalServlet;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

public class Portal implements IActionLookup {
	private class PortalAction implements IPortalAction {
		private ServiceReference reference;
		private IPortalAction action = null;

		PortalAction(ServiceReference reference) {
			this.reference = reference;
		}

		IPortalAction acquireService() {
			synchronized (this) {
				if (action != null)
					return action;
				BundleContext context = getBundleContext();
				return action = (IPortalAction) context.getService(reference);
			}
		}

		private BundleContext getBundleContext() {
			BundleContext result = Portal.this.context;
			if (result == null)
				throw new IllegalStateException("component is not activated");
			return result;
		}

		String getProperty(String key) {
			if (reference == null)
				return null;
			return (String) reference.getProperty(key);
		}

		boolean releaseService() {
			synchronized (this) {
				if (action == null)
					return false; // No service to unget.
				BundleContext bundleContext = getBundleContext();
				try {
					return bundleContext.ungetService(reference);
				} finally {
					action = null;
				}
			}
		}

		public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
			synchronized (this) {
				if (action == null)
					throw new IOException("Action has been invalidated: " + getActionId(reference));
				action.execute(request, response);
			}
		}
	}

	private BundleContext context;
	private Map actions = new HashMap();
	private String servletAlias;
	private String imagesAlias;
	private IControlCenter center;
	private HttpService http;

	protected void setControlCenter(IControlCenter value) {
		center = value;
	}

	protected void setHttp(HttpService value) {
		http = value;
	}

	protected void activate(BundleContext context) {
		this.context = context;
		HttpServlet servlet = new PortalServlet(center, this);
		String servletRoot = PropertyManager.getProperty(ICoreConstants.BACK_END_URL_PROPERTY, ICoreConstants.BACK_END_URL_DEFAULT);
		UrlBuilder urlBuilder = new UrlBuilder(servletRoot);
		try {
			servletAlias = urlBuilder.getPath();
			urlBuilder.appendPath(IPortalConstants.RESOURCE_IMAGES_ALIAS);
			imagesAlias = urlBuilder.getPath();
			http.registerServlet(servletAlias, servlet, null, null);
			http.registerResources(imagesAlias, IPortalConstants.RESOURCE_IMAGES_PATH, null);
			LogUtility.logDebug(this, "Registered servlet: " + servletAlias);
		} catch (Exception e) {
			LogUtility.logError(this, "Error registering servlet with HttpService", e);
		}
	}

	protected void deactivate() {
		http.unregister(servletAlias);
		http.unregister(imagesAlias);
		this.context = null;
	}

	public void addAction(ServiceReference reference) {
		Object id = getActionId(reference);
		if (id == null) {
			LogUtility.logWarning("Action service from " + reference.getBundle().getSymbolicName() + " missing id");
			return;
		}
		synchronized (this) {
			PortalAction data = new PortalAction(reference);
			actions.put(id, data);
		}
	}

	public void removeAction(ServiceReference reference) {
		synchronized (this) {
			Object id = getActionId(reference);
			if (id != null)
				actions.remove(id);
		}
	}

	private Object getActionId(ServiceReference reference) {
		return reference.getProperty(IPortalConstants.ACTION_PARAMETER);
	}

	public IPortalAction acquire(String id) {
		synchronized (this) {
			PortalAction result = (PortalAction) actions.get(id);
			if (result == null)
				return null;
			// acquire to trigger the get
			IPortalAction acquired = result.acquireService();
			return acquired == null ? null : result;
		}
	}

	public void release(String id, IPortalAction action) {
		((PortalAction) action).releaseService();
	}

	public String getActionProperty(String id, String key) {
		synchronized (this) {
			PortalAction result = (PortalAction) actions.get(id);
			if (result == null)
				return null;
			return result.getProperty(key);
		}
	}

	public Collection getAvailable(String id) {
		Collection result = new TreeSet();
		synchronized (this) {
			for (Iterator i = actions.keySet().iterator(); i.hasNext();) {
				String key = (String) i.next();
				if (key.startsWith(id)) {
					int j = id.length();
					if (key.length() > j && key.charAt(j) == '/')
						j++;
					if (j == key.length())
						continue;
					int k = key.indexOf("/", j);
					if (k < 0)
						k = key.length();
					result.add(key.substring(0, k));
				}
			}
		}
		return result;
	}
}

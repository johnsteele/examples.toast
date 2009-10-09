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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.examples.toast.backend.portal.spi.IPortalAction;
import org.eclipse.examples.toast.backend.provisioning.IProvisioner;
import org.eclipse.examples.toast.core.ICoreConstants;
import org.eclipse.examples.toast.core.PropertyManager;

public class ManageAction implements IPortalAction {
	private static final String INSTALL_ACTION = "install";
	private static final String UNINSTALL_ACTION = "uninstall";
	private static final String FEATURE_PARAMETER = "feature";
	private static final String BROWSE_ACTION = "browse";

	private static String servletAlias = PropertyManager.getProperty(ICoreConstants.BACK_END_URL_PROPERTY, ICoreConstants.BACK_END_URL_DEFAULT);

	private IProvisioner provisioner;

	protected void setProvisioner(IProvisioner value) {
		provisioner = value;
	}

	protected void clearProvisioner(IProvisioner value) {
		provisioner = null;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		generateFeatures(request, response);
	}

	private void createFeaturesTable(StringBuffer buffer, String operation, Collection features, String id) throws IOException {
		Comparator comparator = new Comparator() {
			public int compare(Object o1, Object o2) {
				String name1 = ((IInstallableUnit) o1).getProperty(IInstallableUnit.PROP_NAME);
				String name2 = ((IInstallableUnit) o2).getProperty(IInstallableUnit.PROP_NAME);
				return name1.compareTo(name2);
			}
		};
		ArrayList featureList = new ArrayList(features);
		Collections.sort(featureList, comparator);
		int length = features.size();
		if (length == 0) {
			buffer.append("\t\t<table>\n");
			// Row 1
			buffer.append("\t\t\t<tr>\n");
			// Row 1, Column 1
			buffer.append("\t\t\t\t<td><i>None</i></td>\n");
			buffer.append("\t\t\t</tr>\n");
		} else {
			buffer.append("\t\t<table>\n");
			int row = 1;
			for (Iterator i = featureList.iterator(); i.hasNext();) {
				IInstallableUnit feature = (IInstallableUnit) i.next();
				// Row i
				buffer.append("\t\t\t<tr>\n");
				// Row i, Column 1: Row Number
				buffer.append("\t\t\t\t<td align=\"right\">" + row++ + ".</td>\n");
				// Row i, Column 2: Feature Name
				buffer.append("\t\t\t\t<td align=\"left\" width=\"300\">");
				String name = feature.getProperty(IInstallableUnit.PROP_NAME);
				buffer.append(name);
				buffer.append("</td>\n");
				// Row i, Column 3: Link
				buffer.append("\t\t\t\t<td align=\"center\" width=\"100\">");
				String link = createActionLink(operation, feature, id);
				buffer.append("\t\t\t\t\t" + link + "\n");
				buffer.append("\t\t\t\t</td>\n");
				// End Row i
				buffer.append("\t\t\t</tr>\n");
			}
		}
		buffer.append("\t\t</table>\n");
	}

	public void generateFeatures(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter(ICoreConstants.ID_PARAMETER);
		StringBuffer buffer = new StringBuffer(2048);
		WebPageGenerator.writeHeader(buffer, "Software Manangement for: " + id);

		buffer.append("\t\t<h3>Installed Features</h3>\n");
		Collection features = provisioner.getInstalled(id);
		createFeaturesTable(buffer, UNINSTALL_ACTION, features, id);

		buffer.append("\t\t<h3>Available Features</h3>\n");
		features = provisioner.getAvailableFeatures(id);
		createFeaturesTable(buffer, INSTALL_ACTION, features, id);
		buffer.append("\t\t<br/>\n");

		String link = WebPageGenerator.createLink("Back to " + id + "'s home page", BROWSE_ACTION, ICoreConstants.ID_PARAMETER, id);
		buffer.append("\t\t" + link + "<br/>\n");
		buffer.append("\t\t<a href=\"" + servletAlias + "\"/>Back to Toast home</a>\n");
		WebPageGenerator.writeFooter(buffer);
		response.getWriter().print(buffer.toString());
	}

	private String createActionLink(String action, IInstallableUnit feature, String id) throws IOException {
		String label = action == INSTALL_ACTION ? "Install" : "Uninstall";
		return WebPageGenerator.createLink(label, action, FEATURE_PARAMETER, feature.getId(), ICoreConstants.ID_PARAMETER, id);
	}

}
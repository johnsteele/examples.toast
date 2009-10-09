/*******************************************************************************
 * Copyright (c) 2009 EclipseSource Inc. and others. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v1.0 
 * which accompanies this distribution. The Eclipse Public License is available at 
 * http://www.eclipse.org/legal/epl-v10.html and the Eclipse Distribution License 
 * is available at http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors: 
 *     EclipseSource Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.examples.toast.backend.rap;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction exitAction;
	private Action driversAction;
	private Action settingsAction;
	private Action packagesAction;
	private Action emergencyAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(final IWorkbenchWindow window) {
		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);
		driversAction = new Action("Drivers", Activator.getImageDescriptor("/icons/drivers-22.png")) {};
		driversAction.setId("actions.drivers");
		register(driversAction);
		packagesAction = new Action("Packages", Activator.getImageDescriptor("/icons/package-22.png")) {};
		packagesAction.setId("actions.packages");
		register(packagesAction);
		settingsAction = new Action("Settings", Activator.getImageDescriptor("/icons/settings-22.png")) {};
		settingsAction.setId("actions.settings");
		register(settingsAction);
		emergencyAction = new Action("Emergency", Activator.getImageDescriptor("/icons/emergency-22.png")) {};
		emergencyAction.setId("actions.settings");
		register(emergencyAction);
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
		MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
		menuBar.add(fileMenu);
		// Add a group marker indicating where action set menus will appear.
		menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		menuBar.add(helpMenu);
		// File
		fileMenu.add(new Separator());
		fileMenu.add(driversAction);
		fileMenu.add(packagesAction);
		fileMenu.add(settingsAction);
		fileMenu.add(emergencyAction);
		fileMenu.add(new Separator());
		fileMenu.add(exitAction);
	}

	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
		coolBar.add(new ToolBarContributionItem(toolbar, "main"));
		toolbar.add(driversAction);
		toolbar.add(packagesAction);
		toolbar.add(settingsAction);
		toolbar.add(emergencyAction);
	}
}

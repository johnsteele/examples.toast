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

import java.util.ArrayList;
import java.util.List;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class ProvisioningDialog extends Dialog {

	private ListViewer viewer;
	private List selectedPackages;
	private List input;

	protected ProvisioningDialog(final Shell parentShell) {
		super(parentShell);
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Software Packages");
	}

	protected Control createDialogArea(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setLayout(new GridLayout());
		viewer = new ListViewer(composite, SWT.MULTI | SWT.BORDER);
		viewer.setLabelProvider(new LabelProvider() {
			public String getText(Object element) {
				return (((IInstallableUnit) element).getProperty(IInstallableUnit.PROP_NAME));
			}
		});
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setInput(input);
		Control control = viewer.getControl();
		control.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		return control;
	}

	public int open() {
		selectedPackages = null;
		return super.open();
	}

	protected void okPressed() {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		selectedPackages = selection.toList();
		super.okPressed();
	}

	public void setInput(final List input) {
		this.input = input;
		if (viewer != null) {
			viewer.refresh();
		}
	}

	public List getSelectedPackages() {
		return selectedPackages == null ? null : new ArrayList(selectedPackages);
	}
}

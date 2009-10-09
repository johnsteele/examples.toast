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

import java.net.URI;
import org.eclipse.examples.toast.backend.data.IAddress;
import org.eclipse.examples.toast.backend.data.IDriver;
import org.eclipse.examples.toast.backend.data.ITrackedLocation;
import org.eclipse.examples.toast.backend.data.IVehicle;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.rwt.graphics.Graphics;
import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.ViewPart;

public class VehicleView extends ViewPart {

	public static final String ID = "org.eclipse.examples.toast.backend.rap.vehicleView";
	private static final Image DEFAULT_IMAGE = Graphics.getImage("/icons/default_user.png", VehicleView.class.getClassLoader());
	private IVehicle vehicle;
	private Composite parent;
	private Label photoLabel;
	private Label nameLabel;
	private Label addressLabel;
	private Label vidLabel;
	private Label packagesLabel;
	private Label locationLabel;
	private Label headingLabel;
	private Button editProfileButton;

	public void createPartControl(final Composite parent) {
		this.parent = parent;
		parent.setLayout(LayoutUtil.createGridLayout(2, true, 10, 10));
		Control driverGroup = createDriverGroup(parent);
		driverGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		Control vehicleGroup = createVehicleGroup(parent);
		vehicleGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		Control leftButtons = createLeftButtons(parent);
		GridData leftButtonsData = new GridData(SWT.LEFT, SWT.TOP, false, false);
		leftButtons.setLayoutData(leftButtonsData);
		Control rightButtons = createRightButtons(parent);
		GridData rightButtonsData = new GridData(SWT.RIGHT, SWT.TOP, false, false);
		rightButtons.setLayoutData(rightButtonsData);
		createSelectionListener();
	}

	public void setFocus() {
		// TODO Auto-generated method stub
	}

	private Control createDriverGroup(final Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setText("Driver");
		group.setLayout(LayoutUtil.createGridLayout(2, false, 10, 10));
		photoLabel = new Label(group, SWT.BORDER);
		photoLabel.setImage(DEFAULT_IMAGE);
		Composite comp = new Composite(group, SWT.NONE);
		comp.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		comp.setLayout(LayoutUtil.createGridLayout(1, false, 0, 5));
		nameLabel = createFormElement(comp, "Name", "");
		addressLabel = createFormElement(comp, "Address", "");
		return group;
	}

	private Control createVehicleGroup(final Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		group.setText("Vehicle");
		group.setLayout(LayoutUtil.createGridLayout(1, false, 10, 5));
		vidLabel = createFormElement(group, "Id", "");
		packagesLabel = createFormElement(group, "Packages", "");
		locationLabel = createFormElement(group, "Location", "");
		headingLabel = createFormElement(group, "Heading", "");
		return group;
	}

	private Label createFormElement(final Composite parent, final String key, final String value) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(LayoutUtil.createGridLayout(1, false, 0, 0));
		Label keyLabel = new Label(composite, SWT.NONE);
		keyLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		keyLabel.setData(WidgetUtil.CUSTOM_VARIANT, "formKey");
		keyLabel.setText(key + ":");
		Label valueLabel = new Label(composite, SWT.NONE);
		valueLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		valueLabel.setData(WidgetUtil.CUSTOM_VARIANT, "formValue");
		valueLabel.setText(value);
		return valueLabel;
	}

	private Control createLeftButtons(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		RowLayout rowLayout = new RowLayout();
		rowLayout.marginRight = 0;
		rowLayout.marginTop = 0;
		composite.setLayout(rowLayout);
		// Dummy button
		Button showDetailsButton = new Button(composite, SWT.CHECK);
		showDetailsButton.setText("Show Details");
		return composite;
	}

	private Control createRightButtons(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		RowLayout rowLayout = new RowLayout();
		rowLayout.marginRight = 0;
		rowLayout.marginTop = 0;
		composite.setLayout(rowLayout);
		editProfileButton = new Button(composite, SWT.PUSH | SWT.BORDER);
		editProfileButton.setText("Edit Profile");
		editProfileButton.setEnabled(false);
		editProfileButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				editProfile(vehicle);
			}
		});
		return composite;
	}

	protected void editProfile(final IVehicle vehicle) {
		MessageDialog.openError(parent.getShell(), "Edit Failed", "The profile is currently locked and cannot be edited.");
	}

	private void createSelectionListener() {
		IWorkbenchWindow window = getSite().getWorkbenchWindow();
		ISelectionService selectionService = window.getSelectionService();
		selectionService.addSelectionListener(new ISelectionListener() {

			public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
				if (!(part instanceof SearchView))
					return;
				if (selection != null) {
					IStructuredSelection sselection = (IStructuredSelection) selection;
					IVehicle vehicle = (IVehicle) sselection.getFirstElement();
					if (vehicle != null) {
						setVehicle(vehicle);
					}
				}
			}
		});
	}

	private void setVehicle(final IVehicle vehicle) {
		this.vehicle = vehicle;
		System.out.println("vehicle " + vehicle);
		IDriver driver = vehicle.getDriver();
		URI imageUrl = driver.getImage();
		Image image = null;
		if (imageUrl != null) {
			String query = imageUrl.getQuery();
			if (query != null && query.startsWith("PersonID=")) {
				String id = query.substring(9);
				image = Graphics.getImage("/image-cache/" + id + ".png", getClass().getClassLoader());
			}
		}
		if (image != null) {
			photoLabel.setImage(image);
		} else {
			photoLabel.setImage(DEFAULT_IMAGE);
		}
		nameLabel.setText(driver.getFirstName() + " " + driver.getLastName());
		IAddress address = driver.getAddress();
		addressLabel.setText(address.getCity());
		vidLabel.setText(vehicle.getName());
		packagesLabel.setText(String.valueOf(vehicle.getPackages().size()));
		ITrackedLocation location = vehicle.getCurrentLocation();
		String lonStr = String.valueOf(location.getLongitude() / 100000.0);
		String latStr = String.valueOf(location.getLatitude() / 100000.0);
		locationLabel.setText(latStr + ", " + lonStr);
		headingLabel.setText(getDirection(location.getHeading()) + " at " + location.getSpeed() + " mph");
		editProfileButton.setEnabled(vehicle != null);
		photoLabel.getParent().layout();
		parent.layout();
	}

	private static String getDirection(final int heading) {
		String result = null;
		int direction = (heading + 45) % 360;
		if (direction < 90) {
			result = "North";
		} else if (direction < 180) {
			result = "East";
		} else if (direction < 270) {
			result = "South";
		} else {
			result = "West";
		}
		return result;
	}
}

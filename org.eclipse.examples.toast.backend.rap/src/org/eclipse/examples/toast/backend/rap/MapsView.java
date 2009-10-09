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

import org.eclipse.examples.toast.backend.data.ITrackedLocation;
import org.eclipse.examples.toast.backend.data.IVehicle;
import org.eclipse.examples.toast.rap.gmaps.GMap;
import org.eclipse.examples.toast.rap.gmaps.GMap.Location;
import org.eclipse.examples.toast.rap.gmaps.GMap.Marker;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.ViewPart;

public class MapsView extends ViewPart {

	public static final String ID = "org.eclipse.examples.toast.backend.rap.mapview";

	private GMap map;
	private IVehicle vehicle;
	private Composite parent;

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createPartControl(Composite parent) {
		this.parent = parent;
		parent.setLayout(new FillLayout());
		map = new GMap(parent, SWT.NONE);
		map.setCenter(new Location("5001 Great America Pkwy, Santa Clara, CA 95054"));
		createSelectionListener();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	public void setFocus() {
		map.setFocus();
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
					IVehicle firstVehicle = (IVehicle) sselection.getFirstElement();
					if (firstVehicle != null) {
						System.out.println("vehicle " + firstVehicle);
						MapsView.this.vehicle = firstVehicle;
						ITrackedLocation location = firstVehicle.getCurrentLocation();
						double lat = location.getLatitude() / 100000.0;
						double lon = location.getLongitude() / 100000.0;
						map.setCenter(new Location(lat, lon));
					}
					if (!sselection.isEmpty()) {
						Object[] selected = sselection.toArray();
						Marker[] markers = new Marker[selected.length];
						for (int i = 0; i < selected.length; i++) {
							IVehicle vehicle = (IVehicle) selected[i];
							ITrackedLocation currentLocation = vehicle.getCurrentLocation();
							double lat = currentLocation.getLatitude() / 100000.0;
							double lon = currentLocation.getLongitude() / 100000.0;
							markers[i] = new Marker(new Location(lat, lon), null /*vehicle.getName()*/);
						}
						map.setMarkers(markers);
					}
				}
			}
		});
	}

}

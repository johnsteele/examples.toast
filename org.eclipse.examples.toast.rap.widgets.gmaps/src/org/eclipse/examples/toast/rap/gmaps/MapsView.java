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
package org.eclipse.examples.toast.rap.gmaps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.examples.toast.backend.data.ITrackedLocation;
import org.eclipse.examples.toast.backend.data.IVehicle;
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

	public static final String ID = "org.eclipse.examples.toast.backend.rap.mapview"; //$NON-NLS-1$
	private GMap map;

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		map = new GMap(parent, SWT.NONE);
		map.setCenter(new Location("5001 Great America Pkwy, Santa Clara, CA 95054")); //$NON-NLS-1$
		createSelectionListener();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	public void setFocus() {
		map.setFocus();
	}

	public void selectVehicles(List vehicles) {
		if (vehicles.isEmpty())
			return;
		// Center map on first vehicle in selection
		IVehicle firstVehicle = (IVehicle) vehicles.get(0);
		ITrackedLocation location = firstVehicle.getCurrentLocation();
		double lat = location.getLatitude() / 100000.0;
		double lon = location.getLongitude() / 100000.0;
		map.setCenter(new Location(lat, lon));

		// Add markers for all selected vehicles
		Marker[] markers = new Marker[vehicles.size()];
		for (int i = 0; i < vehicles.size(); i++) {
			IVehicle vehicle = (IVehicle) vehicles.get(i);
			ITrackedLocation currentLocation = vehicle.getCurrentLocation();
			lat = currentLocation.getLatitude() / 100000.0;
			lon = currentLocation.getLongitude() / 100000.0;
			markers[i] = new Marker(new Location(lat, lon), null /*vehicle.getName()*/);
		}
		map.setMarkers(markers);
	}

	private void createSelectionListener() {
		IWorkbenchWindow window = getSite().getWorkbenchWindow();
		ISelectionService selectionService = window.getSelectionService();
		selectionService.addSelectionListener(new ISelectionListener() {

			public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
				if (selection == null || !(selection instanceof IStructuredSelection))
					return;
				selectVehicles(filterSelection((IStructuredSelection) selection));
			}

			private List filterSelection(IStructuredSelection selection) {
				List result = new ArrayList(selection.size());
				for (Iterator i = selection.iterator(); i.hasNext();) {
					Object element = i.next();
					if (element instanceof IVehicle)
						result.add(element);
				}
				return result;
			}
		});
	}
}

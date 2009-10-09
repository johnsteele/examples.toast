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

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

public class GMap extends Canvas {

	private Location center;
	private Marker[] markers;

	public GMap(final Composite parent, final int style) {
		super(parent, style);
		this.markers = new Marker[0];
	}

	public Location getCenter() {
		return center;
	}

	public void setCenter(final Location center) {
		this.center = center;
	}

	public Marker[] getMarkers() {
		Marker[] result = new Marker[markers.length];
		System.arraycopy(markers, 0, result, 0, markers.length);
		return result;
	}

	public void setMarkers(final Marker[] markers) {
		Marker[] newMarkers = new Marker[markers.length];
		System.arraycopy(markers, 0, newMarkers, 0, markers.length);
		this.markers = newMarkers;
	}

	public void setLayout(final Layout layout) {
		// Intentionally empty as a map cannot have a layout
	}

	public static class Marker {
		public final Location location;
		public final String html;

		public Marker(final Location location, final String html) {
			this.location = location;
			this.html = html;
		}
	}

	public static class Location {
		public final String address;
		public final double lat;
		public final double lon;

		public Location(final double lat, final double lon) {
			this.lat = lat;
			this.lon = lon;
			this.address = null;
		}

		public Location(final String address) {
			this.address = address;
			this.lat = 0;
			this.lon = 0;
		}

		public String toString() {
			return "Location{ address='" + address + "', lat=" + lat + ", lon=" + lon + "}";
		}
	}
}

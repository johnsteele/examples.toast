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
package org.eclipse.examples.toast.rap.internal.gmaps.gmapkit;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import org.eclipse.examples.toast.rap.gmaps.GMap;
import org.eclipse.examples.toast.rap.gmaps.GMap.Location;
import org.eclipse.examples.toast.rap.gmaps.GMap.Marker;
import org.eclipse.rwt.lifecycle.AbstractWidgetLCA;
import org.eclipse.rwt.lifecycle.ControlLCAUtil;
import org.eclipse.rwt.lifecycle.IWidgetAdapter;
import org.eclipse.rwt.lifecycle.JSVar;
import org.eclipse.rwt.lifecycle.JSWriter;
import org.eclipse.rwt.lifecycle.WidgetLCAUtil;
import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

public class GMapLCA extends AbstractWidgetLCA {

	private static final String PARAM_CENTER_LAT = "centerLat";
	private static final String PARAM_CENTER_LON = "centerLon";
	private static final String JS_PROP_CENTER = "center";
	private static final String PROP_CENTER = "address";

	public void preserveValues(final Widget widget) {
		ControlLCAUtil.preserveValues((Control) widget);
		IWidgetAdapter adapter = WidgetUtil.getAdapter(widget);
		adapter.preserve(PROP_CENTER, ((GMap) widget).getCenter());
		// only needed for custom variants (theming)
		WidgetLCAUtil.preserveCustomVariant(widget);
	}

	/*
	 * Read the parameters transfered from the client
	 */
	public void readData(final Widget widget) {
		GMap map = (GMap) widget;
		String latStr = WidgetLCAUtil.readPropertyValue(map, PARAM_CENTER_LAT);
		String lonStr = WidgetLCAUtil.readPropertyValue(map, PARAM_CENTER_LON);
		try {
			double lat = Double.parseDouble(latStr);
			double lon = Double.parseDouble(lonStr);
			map.setCenter(new Location(lat, lon));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * Initial creation procedure of the widget
	 */
	public void renderInitialization(final Widget widget) throws IOException {
		JSWriter writer = JSWriter.getWriterFor(widget);
		String id = WidgetUtil.getId(widget);
		writer.newWidget("org.eclipse.examples.toast.rap.gmaps.GMap", new Object[] {id});
		writer.set("appearance", "composite");
		writer.set("overflow", "hidden");
		ControlLCAUtil.writeStyleFlags((GMap) widget);
	}

	public void renderChanges(final Widget widget) throws IOException {
		GMap gmap = (GMap) widget;
		ControlLCAUtil.writeChanges(gmap);
		writeCenter(gmap);
		writeMarkers(gmap);
		// only needed for custom variants (theming)
		WidgetLCAUtil.writeCustomVariant(widget);
	}

	public void renderDispose(final Widget widget) throws IOException {
		JSWriter writer = JSWriter.getWriterFor(widget);
		writer.dispose();
	}

	public void createResetHandlerCalls(String typePoolId) throws IOException {
		// no pooling
	}

	public String getTypePoolId(Widget widget) {
		return null;
	}

	private void writeCenter(final GMap gmap) throws IOException {
		JSWriter writer = JSWriter.getWriterFor(gmap);
		Location center = gmap.getCenter();
		if (WidgetLCAUtil.hasChanged(gmap, PROP_CENTER, center)) {
			if (center.address == null) {
				String jsArray = getJsArray(center.lat, center.lon);
				writer.set(PROP_CENTER, JS_PROP_CENTER, new JSVar(jsArray));
			} else {
				writer.set(PROP_CENTER, JS_PROP_CENTER, center.address);
			}
		}
	}

	private void writeMarkers(final GMap gmap) throws IOException {
		JSWriter writer = JSWriter.getWriterFor(gmap);
		Marker[] markers = gmap.getMarkers();
		if (WidgetLCAUtil.hasChanged(gmap, PROP_CENTER, markers)) {
			writer.call("clearMarkers", new Object[0]);
			for (int i = 0; i < markers.length; i++) {
				Marker marker = markers[i];
				if (marker.location.address == null) {
					String jsArray = getJsArray(marker.location.lat, marker.location.lon);
					writer.call("addMarker", new Object[] {new JSVar(jsArray), marker.html});
				} else {
					writer.call("addMarker", new Object[] {marker.location.address, marker.html});
				}
			}
		}
	}

	private static String getJsArray(final double lat, final double lon) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		DecimalFormat fmt = new DecimalFormat("#.########", symbols);
		String jsArray = "[" + fmt.format(lat) + "," + fmt.format(lon) + "]";
		return jsArray;
	}
}

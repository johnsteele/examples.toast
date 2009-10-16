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
package org.eclipse.examples.toast.dev.google;

import org.eclipse.examples.toast.dev.gps.IGps;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

public interface IGoogleEarth {
	public static final String COMMAND_START = "start";
	public static final String COMMAND_PAUSE = "pause";
	public static final String COMMAND_RESUME = "resume";
	public static final String COMMAND_FASTER = "faster";
	public static final String COMMAND_SLOWER= "slower";

	public void flyTo(int latitude, int longitude, int heading, int zoom);
	
	public void flyTo(IGps gps, int zoom);
	
	public void loadDirections(int originLatitude, int originLongitude, int destLatitude, int destLongitude);

	public void controlSimulator(String command);

	public void setVisible(Composite parent, Rectangle bounds);

	public void hide();
}

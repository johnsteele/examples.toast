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
package org.eclipse.examples.toast.internal.dev.gps.fake;

import org.eclipse.examples.toast.dev.gps.IGps;

public class FakeGps implements IGps {
	public int getHeading() {
		return 90; // 90 degrees (east)
	}

	public int getLatitude() {
		return 3888746; // 38.88746 N
	}

	public int getLongitude() {
		return -7702192; // 77.02192 W
	}

	public int getSpeed() {
		return 50; // 50 kph
	}
}

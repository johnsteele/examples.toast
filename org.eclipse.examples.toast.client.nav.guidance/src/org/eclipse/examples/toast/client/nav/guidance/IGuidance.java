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
package org.eclipse.examples.toast.client.nav.guidance;

public interface IGuidance {
	// Codes:
	public static final byte TURN = 0;
	public static final byte DESTINATION = 1;
	public static final byte OFF_ROUTE = 2;

	public byte getCode();

	public int getDegTurn();

	public int getMDistance();

	public String getCurrentStreetName();

	public String getNextStreetName();

	public IRouteSegment getRouteSegment();
}

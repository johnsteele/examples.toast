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

public class Destination {
	private String name;
	private String description;
	private int tudLatitude;
	private int tudLongitude;

	public Destination(String name, String description, int tudLongitude, int tudLatitude) {
		super();
		this.tudLongitude = tudLongitude;
		this.tudLatitude = tudLatitude;
		this.name = name;
		this.description = description;
	}

	public int getTudLatitude() {
		return tudLatitude;
	}

	public int getTudLongitude() {
		return tudLongitude;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}

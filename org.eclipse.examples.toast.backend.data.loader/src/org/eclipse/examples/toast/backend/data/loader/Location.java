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
package org.eclipse.examples.toast.backend.data.loader;

public class Location {
	private String street;
	private String zip;
	private String state;
	private String city;
	private int latitude;
	private int longitude;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = convertToTud(latitude);
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = convertToTud(longitude);
	}

	private int convertToTud(double value) {
		return (int) Math.round(value * 100000.0);
	}
}

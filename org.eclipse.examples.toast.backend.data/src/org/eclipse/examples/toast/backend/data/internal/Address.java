/*******************************************************************************
 * Copyright (c) 2009 Jeff McAffer, Ed Merks and others. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v1.0 
 * which accompanies this distribution. The Eclipse Public License is available at 
 * http://www.eclipse.org/legal/epl-v10.html and the Eclipse Distribution License 
 * is available at http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors: 
 *     Jeff McAffer and Ed Merks - initial API and implementation
 *******************************************************************************/
package org.eclipse.examples.toast.backend.data.internal;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.examples.toast.backend.data.IAddress;
import org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Address</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Address#getArea <em>Area</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Address#getCity <em>City</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Address#getCountry <em>Country</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Address#getPostcode <em>Postcode</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Address#getStreet <em>Street</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Address#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Address extends EObjectImpl implements IAddress {
	/**
	 * The default value of the '{@link #getArea() <em>Area</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArea()
	 * @generated
	 * @ordered
	 */
	protected static final String AREA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArea() <em>Area</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArea()
	 * @generated
	 * @ordered
	 */
	protected String area = AREA_EDEFAULT;

	/**
	 * The default value of the '{@link #getCity() <em>City</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCity()
	 * @generated
	 * @ordered
	 */
	protected static final String CITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCity() <em>City</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCity()
	 * @generated
	 * @ordered
	 */
	protected String city = CITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getCountry() <em>Country</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCountry()
	 * @generated
	 * @ordered
	 */
	protected static final String COUNTRY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCountry() <em>Country</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCountry()
	 * @generated
	 * @ordered
	 */
	protected String country = COUNTRY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPostcode() <em>Postcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostcode()
	 * @generated
	 * @ordered
	 */
	protected static final String POSTCODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPostcode() <em>Postcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostcode()
	 * @generated
	 * @ordered
	 */
	protected String postcode = POSTCODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStreet() <em>Street</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStreet()
	 * @generated
	 * @ordered
	 */
	protected static final String STREET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStreet() <em>Street</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStreet()
	 * @generated
	 * @ordered
	 */
	protected String street = STREET_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected int id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Address() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IToastBackEndDataPackage.Literals.ADDRESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getArea() {
		return area;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArea(String newArea) {
		String oldArea = area;
		area = newArea;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.ADDRESS__AREA, oldArea, area));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCity() {
		return city;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCity(String newCity) {
		String oldCity = city;
		city = newCity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.ADDRESS__CITY, oldCity, city));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCountry(String newCountry) {
		String oldCountry = country;
		country = newCountry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.ADDRESS__COUNTRY, oldCountry, country));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPostcode(String newPostcode) {
		String oldPostcode = postcode;
		postcode = newPostcode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.ADDRESS__POSTCODE, oldPostcode, postcode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStreet(String newStreet) {
		String oldStreet = street;
		street = newStreet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.ADDRESS__STREET, oldStreet, street));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(int newId) {
		int oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.ADDRESS__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IToastBackEndDataPackage.ADDRESS__AREA:
				return getArea();
			case IToastBackEndDataPackage.ADDRESS__CITY:
				return getCity();
			case IToastBackEndDataPackage.ADDRESS__COUNTRY:
				return getCountry();
			case IToastBackEndDataPackage.ADDRESS__POSTCODE:
				return getPostcode();
			case IToastBackEndDataPackage.ADDRESS__STREET:
				return getStreet();
			case IToastBackEndDataPackage.ADDRESS__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IToastBackEndDataPackage.ADDRESS__AREA:
				setArea((String)newValue);
				return;
			case IToastBackEndDataPackage.ADDRESS__CITY:
				setCity((String)newValue);
				return;
			case IToastBackEndDataPackage.ADDRESS__COUNTRY:
				setCountry((String)newValue);
				return;
			case IToastBackEndDataPackage.ADDRESS__POSTCODE:
				setPostcode((String)newValue);
				return;
			case IToastBackEndDataPackage.ADDRESS__STREET:
				setStreet((String)newValue);
				return;
			case IToastBackEndDataPackage.ADDRESS__ID:
				setId((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case IToastBackEndDataPackage.ADDRESS__AREA:
				setArea(AREA_EDEFAULT);
				return;
			case IToastBackEndDataPackage.ADDRESS__CITY:
				setCity(CITY_EDEFAULT);
				return;
			case IToastBackEndDataPackage.ADDRESS__COUNTRY:
				setCountry(COUNTRY_EDEFAULT);
				return;
			case IToastBackEndDataPackage.ADDRESS__POSTCODE:
				setPostcode(POSTCODE_EDEFAULT);
				return;
			case IToastBackEndDataPackage.ADDRESS__STREET:
				setStreet(STREET_EDEFAULT);
				return;
			case IToastBackEndDataPackage.ADDRESS__ID:
				setId(ID_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case IToastBackEndDataPackage.ADDRESS__AREA:
				return AREA_EDEFAULT == null ? area != null : !AREA_EDEFAULT.equals(area);
			case IToastBackEndDataPackage.ADDRESS__CITY:
				return CITY_EDEFAULT == null ? city != null : !CITY_EDEFAULT.equals(city);
			case IToastBackEndDataPackage.ADDRESS__COUNTRY:
				return COUNTRY_EDEFAULT == null ? country != null : !COUNTRY_EDEFAULT.equals(country);
			case IToastBackEndDataPackage.ADDRESS__POSTCODE:
				return POSTCODE_EDEFAULT == null ? postcode != null : !POSTCODE_EDEFAULT.equals(postcode);
			case IToastBackEndDataPackage.ADDRESS__STREET:
				return STREET_EDEFAULT == null ? street != null : !STREET_EDEFAULT.equals(street);
			case IToastBackEndDataPackage.ADDRESS__ID:
				return id != ID_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (area: ");
		result.append(area);
		result.append(", city: ");
		result.append(city);
		result.append(", country: ");
		result.append(country);
		result.append(", postcode: ");
		result.append(postcode);
		result.append(", street: ");
		result.append(street);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //Address

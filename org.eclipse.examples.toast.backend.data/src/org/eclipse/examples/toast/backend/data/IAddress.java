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
package org.eclipse.examples.toast.backend.data;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Address</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IAddress#getArea <em>Area</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IAddress#getCity <em>City</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IAddress#getCountry <em>Country</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IAddress#getPostcode <em>Postcode</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IAddress#getStreet <em>Street</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IAddress#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getAddress()
 * @model
 * @generated
 */
public interface IAddress {
	/**
	 * Returns the value of the '<em><b>Area</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Area</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Area</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getAddress_Area()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	String getArea();

	/**
	 * Returns the value of the '<em><b>City</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>City</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>City</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getAddress_City()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	String getCity();

	/**
	 * Returns the value of the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Country</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Country</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getAddress_Country()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	String getCountry();

	/**
	 * Returns the value of the '<em><b>Postcode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Postcode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Postcode</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getAddress_Postcode()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	String getPostcode();

	/**
	 * Returns the value of the '<em><b>Street</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Street</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Street</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getAddress_Street()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	String getStreet();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(int)
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getAddress_Id()
	 * @model
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link org.eclipse.examples.toast.backend.data.IAddress#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

} // IAddress

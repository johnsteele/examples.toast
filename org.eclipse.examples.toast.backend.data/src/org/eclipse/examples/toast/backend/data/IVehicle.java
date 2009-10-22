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

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vehicle</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IVehicle#getCurrentLocation <em>Current Location</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IVehicle#getEmergencyLocation <em>Emergency Location</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IVehicle#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IVehicle#getDriver <em>Driver</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IVehicle#getPackages <em>Packages</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IVehicle#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IVehicle#isOnline <em>Online</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getVehicle()
 * @model
 * @generated
 */
public interface IVehicle {
	/**
	 * Returns the value of the '<em><b>Current Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Current Location</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Current Location</em>' containment reference.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getVehicle_CurrentLocation()
	 * @model containment="true" suppressedSetVisibility="true"
	 * @generated
	 */
	ITrackedLocation getCurrentLocation();

	/**
	 * Returns the value of the '<em><b>Emergency Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Emergency Location</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Emergency Location</em>' containment reference.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getVehicle_EmergencyLocation()
	 * @model containment="true" suppressedSetVisibility="true"
	 * @generated
	 */
	ITrackedLocation getEmergencyLocation();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getVehicle_Name()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	String getName();

	/**
	 * Returns the value of the '<em><b>Driver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Driver</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Driver</em>' reference.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getVehicle_Driver()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	IDriver getDriver();

	/**
	 * Returns the value of the '<em><b>Packages</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.examples.toast.backend.data.IWaybill}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Packages</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Packages</em>' reference list.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getVehicle_Packages()
	 * @model
	 * @generated
	 */
	List<IWaybill> getPackages();

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
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getVehicle_Id()
	 * @model
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link org.eclipse.examples.toast.backend.data.IVehicle#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

	/**
	 * Returns the value of the '<em><b>Online</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Online</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Online</em>' attribute.
	 * @see #setOnline(boolean)
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getVehicle_Online()
	 * @model default="false"
	 * @generated
	 */
	boolean isOnline();

	/**
	 * Sets the value of the '{@link org.eclipse.examples.toast.backend.data.IVehicle#isOnline <em>Online</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Online</em>' attribute.
	 * @see #isOnline()
	 * @generated
	 */
	void setOnline(boolean value);

} // IVehicle

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
 * A representation of the model object '<em><b>Tracked Location</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.examples.toast.backend.data.ITrackedLocation#getHeading <em>Heading</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.ITrackedLocation#getLatitude <em>Latitude</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.ITrackedLocation#getLongitude <em>Longitude</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.ITrackedLocation#getSpeed <em>Speed</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.ITrackedLocation#getTime <em>Time</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.ITrackedLocation#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getTrackedLocation()
 * @model
 * @generated
 */
public interface ITrackedLocation {
	/**
	 * Returns the value of the '<em><b>Heading</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Heading</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Heading</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getTrackedLocation_Heading()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	int getHeading();

	/**
	 * Returns the value of the '<em><b>Latitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Latitude</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Latitude</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getTrackedLocation_Latitude()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	int getLatitude();

	/**
	 * Returns the value of the '<em><b>Longitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Longitude</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Longitude</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getTrackedLocation_Longitude()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	int getLongitude();

	/**
	 * Returns the value of the '<em><b>Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Speed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Speed</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getTrackedLocation_Speed()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	int getSpeed();

	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getTrackedLocation_Time()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	long getTime();

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
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getTrackedLocation_Id()
	 * @model
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link org.eclipse.examples.toast.backend.data.ITrackedLocation#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

} // ITrackedLocation

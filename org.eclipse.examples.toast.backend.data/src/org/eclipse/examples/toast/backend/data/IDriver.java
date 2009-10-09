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

import java.net.URI;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Driver</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IDriver#getAddress <em>Address</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IDriver#getFirstName <em>First Name</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IDriver#getImage <em>Image</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IDriver#getLastName <em>Last Name</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IDriver#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getDriver()
 * @model
 * @generated
 */
public interface IDriver {
	/**
	 * Returns the value of the '<em><b>Address</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Address</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Address</em>' containment reference.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getDriver_Address()
	 * @model containment="true" suppressedSetVisibility="true"
	 * @generated
	 */
	IAddress getAddress();

	/**
	 * Returns the value of the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Name</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getDriver_FirstName()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	String getFirstName();

	/**
	 * Returns the value of the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Image</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getDriver_Image()
	 * @model dataType="org.eclipse.examples.toast.backend.data.URI" suppressedSetVisibility="true"
	 * @generated
	 */
	URI getImage();

	/**
	 * Returns the value of the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Name</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getDriver_LastName()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	String getLastName();

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
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getDriver_Id()
	 * @model
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link org.eclipse.examples.toast.backend.data.IDriver#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

} // IDriver

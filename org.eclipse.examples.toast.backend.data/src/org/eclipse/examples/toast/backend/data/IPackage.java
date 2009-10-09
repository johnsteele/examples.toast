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
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IPackage#getWaybill <em>Waybill</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IPackage#getWeight <em>Weight</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IPackage#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getPackage()
 * @model
 * @generated
 */
public interface IPackage {
	/**
	 * Returns the value of the '<em><b>Waybill</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.examples.toast.backend.data.IWaybill#getItem <em>Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Waybill</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Waybill</em>' container reference.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getPackage_Waybill()
	 * @see org.eclipse.examples.toast.backend.data.IWaybill#getItem
	 * @model opposite="item" transient="false" suppressedSetVisibility="true"
	 * @generated
	 */
	IWaybill getWaybill();

	/**
	 * Returns the value of the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getPackage_Weight()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	int getWeight();

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
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getPackage_Id()
	 * @model
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link org.eclipse.examples.toast.backend.data.IPackage#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

} // IPackage

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

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Waybill</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IWaybill#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IWaybill#getItem <em>Item</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IWaybill#getReceived <em>Received</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IWaybill#getSent <em>Sent</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IWaybill#getDestination <em>Destination</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IWaybill#getOrigin <em>Origin</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getWaybill()
 * @model
 * @generated
 */
public interface IWaybill {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getWaybill_Id()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	int getId();

	/**
	 * Returns the value of the '<em><b>Item</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.examples.toast.backend.data.IPackage#getWaybill <em>Waybill</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Item</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Item</em>' containment reference.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getWaybill_Item()
	 * @see org.eclipse.examples.toast.backend.data.IPackage#getWaybill
	 * @model opposite="waybill" containment="true" suppressedSetVisibility="true"
	 * @generated
	 */
	IPackage getItem();

	/**
	 * Returns the value of the '<em><b>Received</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Received</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Received</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getWaybill_Received()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	Date getReceived();

	/**
	 * Returns the value of the '<em><b>Sent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sent</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sent</em>' attribute.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getWaybill_Sent()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	Date getSent();

	/**
	 * Returns the value of the '<em><b>Destination</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Destination</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destination</em>' containment reference.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getWaybill_Destination()
	 * @model containment="true" suppressedSetVisibility="true"
	 * @generated
	 */
	IAddress getDestination();

	/**
	 * Returns the value of the '<em><b>Origin</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Origin</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin</em>' containment reference.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getWaybill_Origin()
	 * @model containment="true" suppressedSetVisibility="true"
	 * @generated
	 */
	IAddress getOrigin();

} // IWaybill

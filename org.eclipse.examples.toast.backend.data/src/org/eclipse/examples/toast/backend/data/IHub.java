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
 * A representation of the model object '<em><b>Hub</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IHub#getVehicles <em>Vehicles</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IHub#getWaybills <em>Waybills</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IHub#getDrivers <em>Drivers</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.IHub#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getHub()
 * @model
 * @generated
 */
public interface IHub {
	/**
	 * Returns the value of the '<em><b>Vehicles</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.examples.toast.backend.data.IVehicle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vehicles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vehicles</em>' containment reference list.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getHub_Vehicles()
	 * @model containment="true"
	 * @generated
	 */
	List<IVehicle> getVehicles();

	/**
	 * Returns the value of the '<em><b>Waybills</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.examples.toast.backend.data.IWaybill}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Waybills</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Waybills</em>' containment reference list.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getHub_Waybills()
	 * @model containment="true"
	 * @generated
	 */
	List<IWaybill> getWaybills();

	/**
	 * Returns the value of the '<em><b>Drivers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.examples.toast.backend.data.IDriver}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Drivers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Drivers</em>' containment reference list.
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getHub_Drivers()
	 * @model containment="true"
	 * @generated
	 */
	List<IDriver> getDrivers();

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
	 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage#getHub_Id()
	 * @model
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link org.eclipse.examples.toast.backend.data.IHub#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

} // IHub

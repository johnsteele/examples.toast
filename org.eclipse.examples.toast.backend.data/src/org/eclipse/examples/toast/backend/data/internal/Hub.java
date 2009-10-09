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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.examples.toast.backend.data.IDriver;
import org.eclipse.examples.toast.backend.data.IHub;
import org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage;
import org.eclipse.examples.toast.backend.data.IVehicle;
import org.eclipse.examples.toast.backend.data.IWaybill;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Hub</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Hub#getVehicles <em>Vehicles</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Hub#getWaybills <em>Waybills</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Hub#getDrivers <em>Drivers</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Hub#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Hub extends EObjectImpl implements IHub {
	/**
	 * The cached value of the '{@link #getVehicles() <em>Vehicles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVehicles()
	 * @generated
	 * @ordered
	 */
	protected EList<IVehicle> vehicles;

	/**
	 * The cached value of the '{@link #getWaybills() <em>Waybills</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWaybills()
	 * @generated
	 * @ordered
	 */
	protected EList<IWaybill> waybills;

	/**
	 * The cached value of the '{@link #getDrivers() <em>Drivers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDrivers()
	 * @generated
	 * @ordered
	 */
	protected EList<IDriver> drivers;

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
	protected Hub() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IToastBackEndDataPackage.Literals.HUB;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<IVehicle> getVehicles() {
		if (vehicles == null) {
			vehicles = new EObjectContainmentEList<IVehicle>(IVehicle.class, this, IToastBackEndDataPackage.HUB__VEHICLES);
		}
		return vehicles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<IWaybill> getWaybills() {
		if (waybills == null) {
			waybills = new EObjectContainmentEList<IWaybill>(IWaybill.class, this, IToastBackEndDataPackage.HUB__WAYBILLS);
		}
		return waybills;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<IDriver> getDrivers() {
		if (drivers == null) {
			drivers = new EObjectContainmentEList<IDriver>(IDriver.class, this, IToastBackEndDataPackage.HUB__DRIVERS);
		}
		return drivers;
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
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.HUB__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IToastBackEndDataPackage.HUB__VEHICLES:
				return ((InternalEList<?>)getVehicles()).basicRemove(otherEnd, msgs);
			case IToastBackEndDataPackage.HUB__WAYBILLS:
				return ((InternalEList<?>)getWaybills()).basicRemove(otherEnd, msgs);
			case IToastBackEndDataPackage.HUB__DRIVERS:
				return ((InternalEList<?>)getDrivers()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IToastBackEndDataPackage.HUB__VEHICLES:
				return getVehicles();
			case IToastBackEndDataPackage.HUB__WAYBILLS:
				return getWaybills();
			case IToastBackEndDataPackage.HUB__DRIVERS:
				return getDrivers();
			case IToastBackEndDataPackage.HUB__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IToastBackEndDataPackage.HUB__VEHICLES:
				getVehicles().clear();
				getVehicles().addAll((Collection<? extends IVehicle>)newValue);
				return;
			case IToastBackEndDataPackage.HUB__WAYBILLS:
				getWaybills().clear();
				getWaybills().addAll((Collection<? extends IWaybill>)newValue);
				return;
			case IToastBackEndDataPackage.HUB__DRIVERS:
				getDrivers().clear();
				getDrivers().addAll((Collection<? extends IDriver>)newValue);
				return;
			case IToastBackEndDataPackage.HUB__ID:
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
			case IToastBackEndDataPackage.HUB__VEHICLES:
				getVehicles().clear();
				return;
			case IToastBackEndDataPackage.HUB__WAYBILLS:
				getWaybills().clear();
				return;
			case IToastBackEndDataPackage.HUB__DRIVERS:
				getDrivers().clear();
				return;
			case IToastBackEndDataPackage.HUB__ID:
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
			case IToastBackEndDataPackage.HUB__VEHICLES:
				return vehicles != null && !vehicles.isEmpty();
			case IToastBackEndDataPackage.HUB__WAYBILLS:
				return waybills != null && !waybills.isEmpty();
			case IToastBackEndDataPackage.HUB__DRIVERS:
				return drivers != null && !drivers.isEmpty();
			case IToastBackEndDataPackage.HUB__ID:
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
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //Hub

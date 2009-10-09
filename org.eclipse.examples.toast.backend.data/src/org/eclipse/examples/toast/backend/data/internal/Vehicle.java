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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.examples.toast.backend.data.IDriver;
import org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage;
import org.eclipse.examples.toast.backend.data.ITrackedLocation;
import org.eclipse.examples.toast.backend.data.IVehicle;
import org.eclipse.examples.toast.backend.data.IWaybill;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vehicle</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Vehicle#getCurrentLocation <em>Current Location</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Vehicle#getEmergencyLocation <em>Emergency Location</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Vehicle#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Vehicle#getDriver <em>Driver</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Vehicle#getPackages <em>Packages</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Vehicle#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Vehicle extends EObjectImpl implements IVehicle {
	/**
	 * The cached value of the '{@link #getCurrentLocation() <em>Current Location</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentLocation()
	 * @generated
	 * @ordered
	 */
	protected ITrackedLocation currentLocation;

	/**
	 * The cached value of the '{@link #getEmergencyLocation() <em>Emergency Location</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmergencyLocation()
	 * @generated
	 * @ordered
	 */
	protected ITrackedLocation emergencyLocation;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDriver() <em>Driver</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDriver()
	 * @generated
	 * @ordered
	 */
	protected IDriver driver;

	/**
	 * The cached value of the '{@link #getPackages() <em>Packages</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackages()
	 * @generated
	 * @ordered
	 */
	protected EList<IWaybill> packages;

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
	protected Vehicle() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IToastBackEndDataPackage.Literals.VEHICLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ITrackedLocation getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCurrentLocation(ITrackedLocation newCurrentLocation, NotificationChain msgs) {
		ITrackedLocation oldCurrentLocation = currentLocation;
		currentLocation = newCurrentLocation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.VEHICLE__CURRENT_LOCATION, oldCurrentLocation, newCurrentLocation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurrentLocation(ITrackedLocation newCurrentLocation) {
		if (newCurrentLocation != currentLocation) {
			NotificationChain msgs = null;
			if (currentLocation != null)
				msgs = ((InternalEObject)currentLocation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IToastBackEndDataPackage.VEHICLE__CURRENT_LOCATION, null, msgs);
			if (newCurrentLocation != null)
				msgs = ((InternalEObject)newCurrentLocation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IToastBackEndDataPackage.VEHICLE__CURRENT_LOCATION, null, msgs);
			msgs = basicSetCurrentLocation(newCurrentLocation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.VEHICLE__CURRENT_LOCATION, newCurrentLocation, newCurrentLocation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ITrackedLocation getEmergencyLocation() {
		return emergencyLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEmergencyLocation(ITrackedLocation newEmergencyLocation, NotificationChain msgs) {
		ITrackedLocation oldEmergencyLocation = emergencyLocation;
		emergencyLocation = newEmergencyLocation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.VEHICLE__EMERGENCY_LOCATION, oldEmergencyLocation, newEmergencyLocation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEmergencyLocation(ITrackedLocation newEmergencyLocation) {
		if (newEmergencyLocation != emergencyLocation) {
			NotificationChain msgs = null;
			if (emergencyLocation != null)
				msgs = ((InternalEObject)emergencyLocation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IToastBackEndDataPackage.VEHICLE__EMERGENCY_LOCATION, null, msgs);
			if (newEmergencyLocation != null)
				msgs = ((InternalEObject)newEmergencyLocation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IToastBackEndDataPackage.VEHICLE__EMERGENCY_LOCATION, null, msgs);
			msgs = basicSetEmergencyLocation(newEmergencyLocation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.VEHICLE__EMERGENCY_LOCATION, newEmergencyLocation, newEmergencyLocation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.VEHICLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IDriver getDriver() {
		if (driver != null && ((EObject)driver).eIsProxy()) {
			InternalEObject oldDriver = (InternalEObject)driver;
			driver = (IDriver)eResolveProxy(oldDriver);
			if (driver != oldDriver) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IToastBackEndDataPackage.VEHICLE__DRIVER, oldDriver, driver));
			}
		}
		return driver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IDriver basicGetDriver() {
		return driver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDriver(IDriver newDriver) {
		IDriver oldDriver = driver;
		driver = newDriver;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.VEHICLE__DRIVER, oldDriver, driver));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<IWaybill> getPackages() {
		if (packages == null) {
			packages = new EObjectResolvingEList<IWaybill>(IWaybill.class, this, IToastBackEndDataPackage.VEHICLE__PACKAGES);
		}
		return packages;
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
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.VEHICLE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IToastBackEndDataPackage.VEHICLE__CURRENT_LOCATION:
				return basicSetCurrentLocation(null, msgs);
			case IToastBackEndDataPackage.VEHICLE__EMERGENCY_LOCATION:
				return basicSetEmergencyLocation(null, msgs);
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
			case IToastBackEndDataPackage.VEHICLE__CURRENT_LOCATION:
				return getCurrentLocation();
			case IToastBackEndDataPackage.VEHICLE__EMERGENCY_LOCATION:
				return getEmergencyLocation();
			case IToastBackEndDataPackage.VEHICLE__NAME:
				return getName();
			case IToastBackEndDataPackage.VEHICLE__DRIVER:
				if (resolve) return getDriver();
				return basicGetDriver();
			case IToastBackEndDataPackage.VEHICLE__PACKAGES:
				return getPackages();
			case IToastBackEndDataPackage.VEHICLE__ID:
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
			case IToastBackEndDataPackage.VEHICLE__CURRENT_LOCATION:
				setCurrentLocation((ITrackedLocation)newValue);
				return;
			case IToastBackEndDataPackage.VEHICLE__EMERGENCY_LOCATION:
				setEmergencyLocation((ITrackedLocation)newValue);
				return;
			case IToastBackEndDataPackage.VEHICLE__NAME:
				setName((String)newValue);
				return;
			case IToastBackEndDataPackage.VEHICLE__DRIVER:
				setDriver((IDriver)newValue);
				return;
			case IToastBackEndDataPackage.VEHICLE__PACKAGES:
				getPackages().clear();
				getPackages().addAll((Collection<? extends IWaybill>)newValue);
				return;
			case IToastBackEndDataPackage.VEHICLE__ID:
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
			case IToastBackEndDataPackage.VEHICLE__CURRENT_LOCATION:
				setCurrentLocation((ITrackedLocation)null);
				return;
			case IToastBackEndDataPackage.VEHICLE__EMERGENCY_LOCATION:
				setEmergencyLocation((ITrackedLocation)null);
				return;
			case IToastBackEndDataPackage.VEHICLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case IToastBackEndDataPackage.VEHICLE__DRIVER:
				setDriver((IDriver)null);
				return;
			case IToastBackEndDataPackage.VEHICLE__PACKAGES:
				getPackages().clear();
				return;
			case IToastBackEndDataPackage.VEHICLE__ID:
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
			case IToastBackEndDataPackage.VEHICLE__CURRENT_LOCATION:
				return currentLocation != null;
			case IToastBackEndDataPackage.VEHICLE__EMERGENCY_LOCATION:
				return emergencyLocation != null;
			case IToastBackEndDataPackage.VEHICLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case IToastBackEndDataPackage.VEHICLE__DRIVER:
				return driver != null;
			case IToastBackEndDataPackage.VEHICLE__PACKAGES:
				return packages != null && !packages.isEmpty();
			case IToastBackEndDataPackage.VEHICLE__ID:
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
		result.append(" (name: ");
		result.append(name);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //Vehicle

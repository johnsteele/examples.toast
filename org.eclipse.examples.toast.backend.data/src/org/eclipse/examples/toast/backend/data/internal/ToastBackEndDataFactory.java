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

import java.net.URI;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.examples.toast.backend.data.IAddress;
import org.eclipse.examples.toast.backend.data.IDriver;
import org.eclipse.examples.toast.backend.data.IHub;
import org.eclipse.examples.toast.backend.data.IPackage;
import org.eclipse.examples.toast.backend.data.IToastBackEndDataFactory;
import org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage;
import org.eclipse.examples.toast.backend.data.ITrackedLocation;
import org.eclipse.examples.toast.backend.data.IVehicle;
import org.eclipse.examples.toast.backend.data.IWaybill;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ToastBackEndDataFactory extends EFactoryImpl implements IToastBackEndDataFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IToastBackEndDataFactory init() {
		try {
			IToastBackEndDataFactory theToastBackEndDataFactory = (IToastBackEndDataFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/equinoxosgi/toast/backend/data.ecore"); 
			if (theToastBackEndDataFactory != null) {
				return theToastBackEndDataFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ToastBackEndDataFactory();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ToastBackEndDataFactory() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case IToastBackEndDataPackage.ADDRESS: return (EObject)createAddress();
			case IToastBackEndDataPackage.DRIVER: return (EObject)createDriver();
			case IToastBackEndDataPackage.PACKAGE: return (EObject)createPackage();
			case IToastBackEndDataPackage.TRACKED_LOCATION: return (EObject)createTrackedLocation();
			case IToastBackEndDataPackage.VEHICLE: return (EObject)createVehicle();
			case IToastBackEndDataPackage.WAYBILL: return (EObject)createWaybill();
			case IToastBackEndDataPackage.HUB: return (EObject)createHub();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case IToastBackEndDataPackage.URI:
				return createURIFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case IToastBackEndDataPackage.URI:
				return convertURIToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IAddress createAddress() {
		Address address = new Address();
		return address;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IDriver createDriver() {
		Driver driver = new Driver();
		return driver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IPackage createPackage() {
		Package package_ = new Package();
		return package_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ITrackedLocation createTrackedLocation() {
		TrackedLocation trackedLocation = new TrackedLocation();
		return trackedLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IVehicle createVehicle() {
		Vehicle vehicle = new Vehicle();
		return vehicle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IWaybill createWaybill() {
		Waybill waybill = new Waybill();
		return waybill;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IHub createHub() {
		Hub hub = new Hub();
		return hub;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URI createURIFromString(EDataType eDataType, String initialValue) {
		return (URI)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertURIToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IToastBackEndDataPackage getToastBackEndDataPackage() {
		return (IToastBackEndDataPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static IToastBackEndDataPackage getPackage() {
		return IToastBackEndDataPackage.eINSTANCE;
	}

} //ToastBackEndDataFactory

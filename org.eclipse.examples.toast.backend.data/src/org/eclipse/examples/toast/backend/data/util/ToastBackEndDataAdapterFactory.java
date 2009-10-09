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
package org.eclipse.examples.toast.backend.data.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.examples.toast.backend.data.IAddress;
import org.eclipse.examples.toast.backend.data.IDriver;
import org.eclipse.examples.toast.backend.data.IHub;
import org.eclipse.examples.toast.backend.data.IPackage;
import org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage;
import org.eclipse.examples.toast.backend.data.ITrackedLocation;
import org.eclipse.examples.toast.backend.data.IVehicle;
import org.eclipse.examples.toast.backend.data.IWaybill;


/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage
 * @generated
 */
public class ToastBackEndDataAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static IToastBackEndDataPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ToastBackEndDataAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = IToastBackEndDataPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ToastBackEndDataSwitch<Adapter> modelSwitch =
		new ToastBackEndDataSwitch<Adapter>() {
			@Override
			public Adapter caseAddress(IAddress object) {
				return createAddressAdapter();
			}
			@Override
			public Adapter caseDriver(IDriver object) {
				return createDriverAdapter();
			}
			@Override
			public Adapter casePackage(IPackage object) {
				return createPackageAdapter();
			}
			@Override
			public Adapter caseTrackedLocation(ITrackedLocation object) {
				return createTrackedLocationAdapter();
			}
			@Override
			public Adapter caseVehicle(IVehicle object) {
				return createVehicleAdapter();
			}
			@Override
			public Adapter caseWaybill(IWaybill object) {
				return createWaybillAdapter();
			}
			@Override
			public Adapter caseHub(IHub object) {
				return createHubAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.examples.toast.backend.data.IAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.examples.toast.backend.data.IAddress
	 * @generated
	 */
	public Adapter createAddressAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.examples.toast.backend.data.IDriver <em>Driver</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.examples.toast.backend.data.IDriver
	 * @generated
	 */
	public Adapter createDriverAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.examples.toast.backend.data.IPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.examples.toast.backend.data.IPackage
	 * @generated
	 */
	public Adapter createPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.examples.toast.backend.data.ITrackedLocation <em>Tracked Location</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.examples.toast.backend.data.ITrackedLocation
	 * @generated
	 */
	public Adapter createTrackedLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.examples.toast.backend.data.IVehicle <em>Vehicle</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.examples.toast.backend.data.IVehicle
	 * @generated
	 */
	public Adapter createVehicleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.examples.toast.backend.data.IWaybill <em>Waybill</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.examples.toast.backend.data.IWaybill
	 * @generated
	 */
	public Adapter createWaybillAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.examples.toast.backend.data.IHub <em>Hub</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.examples.toast.backend.data.IHub
	 * @generated
	 */
	public Adapter createHubAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ToastBackEndDataAdapterFactory

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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.examples.toast.backend.data.IPackage;
import org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage;
import org.eclipse.examples.toast.backend.data.IWaybill;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Package#getWaybill <em>Waybill</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Package#getWeight <em>Weight</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Package#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Package extends EObjectImpl implements IPackage {
	/**
	 * The default value of the '{@link #getWeight() <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeight()
	 * @generated
	 * @ordered
	 */
	protected static final int WEIGHT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getWeight() <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeight()
	 * @generated
	 * @ordered
	 */
	protected int weight = WEIGHT_EDEFAULT;

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
	protected Package() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IToastBackEndDataPackage.Literals.PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IWaybill getWaybill() {
		if (eContainerFeatureID() != IToastBackEndDataPackage.PACKAGE__WAYBILL) return null;
		return (IWaybill)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWaybill(IWaybill newWaybill, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newWaybill, IToastBackEndDataPackage.PACKAGE__WAYBILL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWaybill(IWaybill newWaybill) {
		if (newWaybill != eInternalContainer() || (eContainerFeatureID() != IToastBackEndDataPackage.PACKAGE__WAYBILL && newWaybill != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newWaybill))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWaybill != null)
				msgs = ((InternalEObject)newWaybill).eInverseAdd(this, IToastBackEndDataPackage.WAYBILL__ITEM, IWaybill.class, msgs);
			msgs = basicSetWaybill(newWaybill, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.PACKAGE__WAYBILL, newWaybill, newWaybill));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeight(int newWeight) {
		int oldWeight = weight;
		weight = newWeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.PACKAGE__WEIGHT, oldWeight, weight));
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
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.PACKAGE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IToastBackEndDataPackage.PACKAGE__WAYBILL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetWaybill((IWaybill)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IToastBackEndDataPackage.PACKAGE__WAYBILL:
				return basicSetWaybill(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case IToastBackEndDataPackage.PACKAGE__WAYBILL:
				return eInternalContainer().eInverseRemove(this, IToastBackEndDataPackage.WAYBILL__ITEM, IWaybill.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IToastBackEndDataPackage.PACKAGE__WAYBILL:
				return getWaybill();
			case IToastBackEndDataPackage.PACKAGE__WEIGHT:
				return getWeight();
			case IToastBackEndDataPackage.PACKAGE__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IToastBackEndDataPackage.PACKAGE__WAYBILL:
				setWaybill((IWaybill)newValue);
				return;
			case IToastBackEndDataPackage.PACKAGE__WEIGHT:
				setWeight((Integer)newValue);
				return;
			case IToastBackEndDataPackage.PACKAGE__ID:
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
			case IToastBackEndDataPackage.PACKAGE__WAYBILL:
				setWaybill((IWaybill)null);
				return;
			case IToastBackEndDataPackage.PACKAGE__WEIGHT:
				setWeight(WEIGHT_EDEFAULT);
				return;
			case IToastBackEndDataPackage.PACKAGE__ID:
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
			case IToastBackEndDataPackage.PACKAGE__WAYBILL:
				return getWaybill() != null;
			case IToastBackEndDataPackage.PACKAGE__WEIGHT:
				return weight != WEIGHT_EDEFAULT;
			case IToastBackEndDataPackage.PACKAGE__ID:
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
		result.append(" (weight: ");
		result.append(weight);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //Package

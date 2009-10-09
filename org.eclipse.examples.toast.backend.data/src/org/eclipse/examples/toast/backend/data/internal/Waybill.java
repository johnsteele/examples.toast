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

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.examples.toast.backend.data.IAddress;
import org.eclipse.examples.toast.backend.data.IPackage;
import org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage;
import org.eclipse.examples.toast.backend.data.IWaybill;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Waybill</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Waybill#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Waybill#getItem <em>Item</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Waybill#getReceived <em>Received</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Waybill#getSent <em>Sent</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Waybill#getDestination <em>Destination</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.Waybill#getOrigin <em>Origin</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Waybill extends EObjectImpl implements IWaybill {
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
	 * The cached value of the '{@link #getItem() <em>Item</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getItem()
	 * @generated
	 * @ordered
	 */
	protected IPackage item;

	/**
	 * The default value of the '{@link #getReceived() <em>Received</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReceived()
	 * @generated
	 * @ordered
	 */
	protected static final Date RECEIVED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReceived() <em>Received</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReceived()
	 * @generated
	 * @ordered
	 */
	protected Date received = RECEIVED_EDEFAULT;

	/**
	 * The default value of the '{@link #getSent() <em>Sent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSent()
	 * @generated
	 * @ordered
	 */
	protected static final Date SENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSent() <em>Sent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSent()
	 * @generated
	 * @ordered
	 */
	protected Date sent = SENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDestination() <em>Destination</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestination()
	 * @generated
	 * @ordered
	 */
	protected IAddress destination;

	/**
	 * The cached value of the '{@link #getOrigin() <em>Origin</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigin()
	 * @generated
	 * @ordered
	 */
	protected IAddress origin;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Waybill() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IToastBackEndDataPackage.Literals.WAYBILL;
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
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.WAYBILL__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IPackage getItem() {
		return item;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetItem(IPackage newItem, NotificationChain msgs) {
		IPackage oldItem = item;
		item = newItem;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.WAYBILL__ITEM, oldItem, newItem);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setItem(IPackage newItem) {
		if (newItem != item) {
			NotificationChain msgs = null;
			if (item != null)
				msgs = ((InternalEObject)item).eInverseRemove(this, IToastBackEndDataPackage.PACKAGE__WAYBILL, IPackage.class, msgs);
			if (newItem != null)
				msgs = ((InternalEObject)newItem).eInverseAdd(this, IToastBackEndDataPackage.PACKAGE__WAYBILL, IPackage.class, msgs);
			msgs = basicSetItem(newItem, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.WAYBILL__ITEM, newItem, newItem));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getReceived() {
		return received;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReceived(Date newReceived) {
		Date oldReceived = received;
		received = newReceived;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.WAYBILL__RECEIVED, oldReceived, received));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getSent() {
		return sent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSent(Date newSent) {
		Date oldSent = sent;
		sent = newSent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.WAYBILL__SENT, oldSent, sent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IAddress getDestination() {
		return destination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDestination(IAddress newDestination, NotificationChain msgs) {
		IAddress oldDestination = destination;
		destination = newDestination;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.WAYBILL__DESTINATION, oldDestination, newDestination);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDestination(IAddress newDestination) {
		if (newDestination != destination) {
			NotificationChain msgs = null;
			if (destination != null)
				msgs = ((InternalEObject)destination).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IToastBackEndDataPackage.WAYBILL__DESTINATION, null, msgs);
			if (newDestination != null)
				msgs = ((InternalEObject)newDestination).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IToastBackEndDataPackage.WAYBILL__DESTINATION, null, msgs);
			msgs = basicSetDestination(newDestination, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.WAYBILL__DESTINATION, newDestination, newDestination));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IAddress getOrigin() {
		return origin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOrigin(IAddress newOrigin, NotificationChain msgs) {
		IAddress oldOrigin = origin;
		origin = newOrigin;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.WAYBILL__ORIGIN, oldOrigin, newOrigin);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrigin(IAddress newOrigin) {
		if (newOrigin != origin) {
			NotificationChain msgs = null;
			if (origin != null)
				msgs = ((InternalEObject)origin).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IToastBackEndDataPackage.WAYBILL__ORIGIN, null, msgs);
			if (newOrigin != null)
				msgs = ((InternalEObject)newOrigin).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IToastBackEndDataPackage.WAYBILL__ORIGIN, null, msgs);
			msgs = basicSetOrigin(newOrigin, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.WAYBILL__ORIGIN, newOrigin, newOrigin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IToastBackEndDataPackage.WAYBILL__ITEM:
				if (item != null)
					msgs = ((InternalEObject)item).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IToastBackEndDataPackage.WAYBILL__ITEM, null, msgs);
				return basicSetItem((IPackage)otherEnd, msgs);
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
			case IToastBackEndDataPackage.WAYBILL__ITEM:
				return basicSetItem(null, msgs);
			case IToastBackEndDataPackage.WAYBILL__DESTINATION:
				return basicSetDestination(null, msgs);
			case IToastBackEndDataPackage.WAYBILL__ORIGIN:
				return basicSetOrigin(null, msgs);
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
			case IToastBackEndDataPackage.WAYBILL__ID:
				return getId();
			case IToastBackEndDataPackage.WAYBILL__ITEM:
				return getItem();
			case IToastBackEndDataPackage.WAYBILL__RECEIVED:
				return getReceived();
			case IToastBackEndDataPackage.WAYBILL__SENT:
				return getSent();
			case IToastBackEndDataPackage.WAYBILL__DESTINATION:
				return getDestination();
			case IToastBackEndDataPackage.WAYBILL__ORIGIN:
				return getOrigin();
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
			case IToastBackEndDataPackage.WAYBILL__ID:
				setId((Integer)newValue);
				return;
			case IToastBackEndDataPackage.WAYBILL__ITEM:
				setItem((IPackage)newValue);
				return;
			case IToastBackEndDataPackage.WAYBILL__RECEIVED:
				setReceived((Date)newValue);
				return;
			case IToastBackEndDataPackage.WAYBILL__SENT:
				setSent((Date)newValue);
				return;
			case IToastBackEndDataPackage.WAYBILL__DESTINATION:
				setDestination((IAddress)newValue);
				return;
			case IToastBackEndDataPackage.WAYBILL__ORIGIN:
				setOrigin((IAddress)newValue);
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
			case IToastBackEndDataPackage.WAYBILL__ID:
				setId(ID_EDEFAULT);
				return;
			case IToastBackEndDataPackage.WAYBILL__ITEM:
				setItem((IPackage)null);
				return;
			case IToastBackEndDataPackage.WAYBILL__RECEIVED:
				setReceived(RECEIVED_EDEFAULT);
				return;
			case IToastBackEndDataPackage.WAYBILL__SENT:
				setSent(SENT_EDEFAULT);
				return;
			case IToastBackEndDataPackage.WAYBILL__DESTINATION:
				setDestination((IAddress)null);
				return;
			case IToastBackEndDataPackage.WAYBILL__ORIGIN:
				setOrigin((IAddress)null);
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
			case IToastBackEndDataPackage.WAYBILL__ID:
				return id != ID_EDEFAULT;
			case IToastBackEndDataPackage.WAYBILL__ITEM:
				return item != null;
			case IToastBackEndDataPackage.WAYBILL__RECEIVED:
				return RECEIVED_EDEFAULT == null ? received != null : !RECEIVED_EDEFAULT.equals(received);
			case IToastBackEndDataPackage.WAYBILL__SENT:
				return SENT_EDEFAULT == null ? sent != null : !SENT_EDEFAULT.equals(sent);
			case IToastBackEndDataPackage.WAYBILL__DESTINATION:
				return destination != null;
			case IToastBackEndDataPackage.WAYBILL__ORIGIN:
				return origin != null;
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
		result.append(", received: ");
		result.append(received);
		result.append(", sent: ");
		result.append(sent);
		result.append(')');
		return result.toString();
	}

} //Waybill

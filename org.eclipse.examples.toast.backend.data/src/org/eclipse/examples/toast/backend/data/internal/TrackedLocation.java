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

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.examples.toast.backend.data.IToastBackEndDataPackage;
import org.eclipse.examples.toast.backend.data.ITrackedLocation;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tracked Location</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.TrackedLocation#getHeading <em>Heading</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.TrackedLocation#getLatitude <em>Latitude</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.TrackedLocation#getLongitude <em>Longitude</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.TrackedLocation#getSpeed <em>Speed</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.TrackedLocation#getTime <em>Time</em>}</li>
 *   <li>{@link org.eclipse.examples.toast.backend.data.internal.TrackedLocation#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TrackedLocation extends EObjectImpl implements ITrackedLocation {
	/**
	 * The default value of the '{@link #getHeading() <em>Heading</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeading()
	 * @generated
	 * @ordered
	 */
	protected static final int HEADING_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHeading() <em>Heading</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeading()
	 * @generated
	 * @ordered
	 */
	protected int heading = HEADING_EDEFAULT;

	/**
	 * The default value of the '{@link #getLatitude() <em>Latitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLatitude()
	 * @generated
	 * @ordered
	 */
	protected static final int LATITUDE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLatitude() <em>Latitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLatitude()
	 * @generated
	 * @ordered
	 */
	protected int latitude = LATITUDE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLongitude() <em>Longitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongitude()
	 * @generated
	 * @ordered
	 */
	protected static final int LONGITUDE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLongitude() <em>Longitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongitude()
	 * @generated
	 * @ordered
	 */
	protected int longitude = LONGITUDE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSpeed() <em>Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpeed()
	 * @generated
	 * @ordered
	 */
	protected static final int SPEED_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSpeed() <em>Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpeed()
	 * @generated
	 * @ordered
	 */
	protected int speed = SPEED_EDEFAULT;

	/**
	 * The default value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected static final long TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected long time = TIME_EDEFAULT;

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
	protected TrackedLocation() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IToastBackEndDataPackage.Literals.TRACKED_LOCATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getHeading() {
		return heading;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeading(int newHeading) {
		int oldHeading = heading;
		heading = newHeading;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.TRACKED_LOCATION__HEADING, oldHeading, heading));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLatitude() {
		return latitude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLatitude(int newLatitude) {
		int oldLatitude = latitude;
		latitude = newLatitude;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.TRACKED_LOCATION__LATITUDE, oldLatitude, latitude));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLongitude() {
		return longitude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLongitude(int newLongitude) {
		int oldLongitude = longitude;
		longitude = newLongitude;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.TRACKED_LOCATION__LONGITUDE, oldLongitude, longitude));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpeed(int newSpeed) {
		int oldSpeed = speed;
		speed = newSpeed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.TRACKED_LOCATION__SPEED, oldSpeed, speed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getTime() {
		return time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTime(long newTime) {
		long oldTime = time;
		time = newTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.TRACKED_LOCATION__TIME, oldTime, time));
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
			eNotify(new ENotificationImpl(this, Notification.SET, IToastBackEndDataPackage.TRACKED_LOCATION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IToastBackEndDataPackage.TRACKED_LOCATION__HEADING:
				return getHeading();
			case IToastBackEndDataPackage.TRACKED_LOCATION__LATITUDE:
				return getLatitude();
			case IToastBackEndDataPackage.TRACKED_LOCATION__LONGITUDE:
				return getLongitude();
			case IToastBackEndDataPackage.TRACKED_LOCATION__SPEED:
				return getSpeed();
			case IToastBackEndDataPackage.TRACKED_LOCATION__TIME:
				return getTime();
			case IToastBackEndDataPackage.TRACKED_LOCATION__ID:
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
			case IToastBackEndDataPackage.TRACKED_LOCATION__HEADING:
				setHeading((Integer)newValue);
				return;
			case IToastBackEndDataPackage.TRACKED_LOCATION__LATITUDE:
				setLatitude((Integer)newValue);
				return;
			case IToastBackEndDataPackage.TRACKED_LOCATION__LONGITUDE:
				setLongitude((Integer)newValue);
				return;
			case IToastBackEndDataPackage.TRACKED_LOCATION__SPEED:
				setSpeed((Integer)newValue);
				return;
			case IToastBackEndDataPackage.TRACKED_LOCATION__TIME:
				setTime((Long)newValue);
				return;
			case IToastBackEndDataPackage.TRACKED_LOCATION__ID:
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
			case IToastBackEndDataPackage.TRACKED_LOCATION__HEADING:
				setHeading(HEADING_EDEFAULT);
				return;
			case IToastBackEndDataPackage.TRACKED_LOCATION__LATITUDE:
				setLatitude(LATITUDE_EDEFAULT);
				return;
			case IToastBackEndDataPackage.TRACKED_LOCATION__LONGITUDE:
				setLongitude(LONGITUDE_EDEFAULT);
				return;
			case IToastBackEndDataPackage.TRACKED_LOCATION__SPEED:
				setSpeed(SPEED_EDEFAULT);
				return;
			case IToastBackEndDataPackage.TRACKED_LOCATION__TIME:
				setTime(TIME_EDEFAULT);
				return;
			case IToastBackEndDataPackage.TRACKED_LOCATION__ID:
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
			case IToastBackEndDataPackage.TRACKED_LOCATION__HEADING:
				return heading != HEADING_EDEFAULT;
			case IToastBackEndDataPackage.TRACKED_LOCATION__LATITUDE:
				return latitude != LATITUDE_EDEFAULT;
			case IToastBackEndDataPackage.TRACKED_LOCATION__LONGITUDE:
				return longitude != LONGITUDE_EDEFAULT;
			case IToastBackEndDataPackage.TRACKED_LOCATION__SPEED:
				return speed != SPEED_EDEFAULT;
			case IToastBackEndDataPackage.TRACKED_LOCATION__TIME:
				return time != TIME_EDEFAULT;
			case IToastBackEndDataPackage.TRACKED_LOCATION__ID:
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
		result.append(" (heading: ");
		result.append(heading);
		result.append(", latitude: ");
		result.append(latitude);
		result.append(", longitude: ");
		result.append(longitude);
		result.append(", speed: ");
		result.append(speed);
		result.append(", time: ");
		result.append(time);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //TrackedLocation

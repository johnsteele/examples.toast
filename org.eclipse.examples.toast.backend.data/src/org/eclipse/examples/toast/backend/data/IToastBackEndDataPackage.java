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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.examples.toast.backend.data.IToastBackEndDataFactory
 * @model kind="package"
 * @generated
 */
public interface IToastBackEndDataPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "data";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/equinoxosgi/toast/backend/data.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.examples.toast.backend.data";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IToastBackEndDataPackage eINSTANCE = org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage.init();

	/**
	 * The meta object id for the '{@link org.eclipse.examples.toast.backend.data.internal.Address <em>Address</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.examples.toast.backend.data.internal.Address
	 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getAddress()
	 * @generated
	 */
	int ADDRESS = 0;

	/**
	 * The feature id for the '<em><b>Area</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS__AREA = 0;

	/**
	 * The feature id for the '<em><b>City</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS__CITY = 1;

	/**
	 * The feature id for the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS__COUNTRY = 2;

	/**
	 * The feature id for the '<em><b>Postcode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS__POSTCODE = 3;

	/**
	 * The feature id for the '<em><b>Street</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS__STREET = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS__ID = 5;

	/**
	 * The number of structural features of the '<em>Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.examples.toast.backend.data.internal.Driver <em>Driver</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.examples.toast.backend.data.internal.Driver
	 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getDriver()
	 * @generated
	 */
	int DRIVER = 1;

	/**
	 * The feature id for the '<em><b>Address</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVER__ADDRESS = 0;

	/**
	 * The feature id for the '<em><b>First Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVER__FIRST_NAME = 1;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVER__IMAGE = 2;

	/**
	 * The feature id for the '<em><b>Last Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVER__LAST_NAME = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVER__ID = 4;

	/**
	 * The number of structural features of the '<em>Driver</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVER_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.examples.toast.backend.data.internal.Package <em>Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.examples.toast.backend.data.internal.Package
	 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getPackage()
	 * @generated
	 */
	int PACKAGE = 2;

	/**
	 * The feature id for the '<em><b>Waybill</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__WAYBILL = 0;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__WEIGHT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__ID = 2;

	/**
	 * The number of structural features of the '<em>Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.examples.toast.backend.data.internal.TrackedLocation <em>Tracked Location</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.examples.toast.backend.data.internal.TrackedLocation
	 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getTrackedLocation()
	 * @generated
	 */
	int TRACKED_LOCATION = 3;

	/**
	 * The feature id for the '<em><b>Heading</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKED_LOCATION__HEADING = 0;

	/**
	 * The feature id for the '<em><b>Latitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKED_LOCATION__LATITUDE = 1;

	/**
	 * The feature id for the '<em><b>Longitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKED_LOCATION__LONGITUDE = 2;

	/**
	 * The feature id for the '<em><b>Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKED_LOCATION__SPEED = 3;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKED_LOCATION__TIME = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKED_LOCATION__ID = 5;

	/**
	 * The number of structural features of the '<em>Tracked Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKED_LOCATION_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.examples.toast.backend.data.internal.Vehicle <em>Vehicle</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.examples.toast.backend.data.internal.Vehicle
	 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getVehicle()
	 * @generated
	 */
	int VEHICLE = 4;

	/**
	 * The feature id for the '<em><b>Current Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE__CURRENT_LOCATION = 0;

	/**
	 * The feature id for the '<em><b>Emergency Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE__EMERGENCY_LOCATION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE__NAME = 2;

	/**
	 * The feature id for the '<em><b>Driver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE__DRIVER = 3;

	/**
	 * The feature id for the '<em><b>Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE__PACKAGES = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE__ID = 5;

	/**
	 * The feature id for the '<em><b>Online</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE__ONLINE = 6;

	/**
	 * The number of structural features of the '<em>Vehicle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.examples.toast.backend.data.internal.Waybill <em>Waybill</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.examples.toast.backend.data.internal.Waybill
	 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getWaybill()
	 * @generated
	 */
	int WAYBILL = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAYBILL__ID = 0;

	/**
	 * The feature id for the '<em><b>Item</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAYBILL__ITEM = 1;

	/**
	 * The feature id for the '<em><b>Received</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAYBILL__RECEIVED = 2;

	/**
	 * The feature id for the '<em><b>Sent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAYBILL__SENT = 3;

	/**
	 * The feature id for the '<em><b>Destination</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAYBILL__DESTINATION = 4;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAYBILL__ORIGIN = 5;

	/**
	 * The number of structural features of the '<em>Waybill</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAYBILL_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.examples.toast.backend.data.internal.Hub <em>Hub</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.examples.toast.backend.data.internal.Hub
	 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getHub()
	 * @generated
	 */
	int HUB = 6;

	/**
	 * The feature id for the '<em><b>Vehicles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUB__VEHICLES = 0;

	/**
	 * The feature id for the '<em><b>Waybills</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUB__WAYBILLS = 1;

	/**
	 * The feature id for the '<em><b>Drivers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUB__DRIVERS = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUB__ID = 3;

	/**
	 * The number of structural features of the '<em>Hub</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUB_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '<em>URI</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.net.URI
	 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getURI()
	 * @generated
	 */
	int URI = 7;

	/**
	 * Returns the meta object for class '{@link org.eclipse.examples.toast.backend.data.IAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Address</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IAddress
	 * @generated
	 */
	EClass getAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IAddress#getArea <em>Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Area</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IAddress#getArea()
	 * @see #getAddress()
	 * @generated
	 */
	EAttribute getAddress_Area();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IAddress#getCity <em>City</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>City</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IAddress#getCity()
	 * @see #getAddress()
	 * @generated
	 */
	EAttribute getAddress_City();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IAddress#getCountry <em>Country</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Country</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IAddress#getCountry()
	 * @see #getAddress()
	 * @generated
	 */
	EAttribute getAddress_Country();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IAddress#getPostcode <em>Postcode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Postcode</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IAddress#getPostcode()
	 * @see #getAddress()
	 * @generated
	 */
	EAttribute getAddress_Postcode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IAddress#getStreet <em>Street</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Street</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IAddress#getStreet()
	 * @see #getAddress()
	 * @generated
	 */
	EAttribute getAddress_Street();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IAddress#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IAddress#getId()
	 * @see #getAddress()
	 * @generated
	 */
	EAttribute getAddress_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.examples.toast.backend.data.IDriver <em>Driver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Driver</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IDriver
	 * @generated
	 */
	EClass getDriver();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.examples.toast.backend.data.IDriver#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Address</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IDriver#getAddress()
	 * @see #getDriver()
	 * @generated
	 */
	EReference getDriver_Address();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IDriver#getFirstName <em>First Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Name</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IDriver#getFirstName()
	 * @see #getDriver()
	 * @generated
	 */
	EAttribute getDriver_FirstName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IDriver#getImage <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IDriver#getImage()
	 * @see #getDriver()
	 * @generated
	 */
	EAttribute getDriver_Image();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IDriver#getLastName <em>Last Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Name</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IDriver#getLastName()
	 * @see #getDriver()
	 * @generated
	 */
	EAttribute getDriver_LastName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IDriver#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IDriver#getId()
	 * @see #getDriver()
	 * @generated
	 */
	EAttribute getDriver_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.examples.toast.backend.data.IPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IPackage
	 * @generated
	 */
	EClass getPackage();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.examples.toast.backend.data.IPackage#getWaybill <em>Waybill</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Waybill</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IPackage#getWaybill()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Waybill();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IPackage#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IPackage#getWeight()
	 * @see #getPackage()
	 * @generated
	 */
	EAttribute getPackage_Weight();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IPackage#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IPackage#getId()
	 * @see #getPackage()
	 * @generated
	 */
	EAttribute getPackage_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.examples.toast.backend.data.ITrackedLocation <em>Tracked Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tracked Location</em>'.
	 * @see org.eclipse.examples.toast.backend.data.ITrackedLocation
	 * @generated
	 */
	EClass getTrackedLocation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.ITrackedLocation#getHeading <em>Heading</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Heading</em>'.
	 * @see org.eclipse.examples.toast.backend.data.ITrackedLocation#getHeading()
	 * @see #getTrackedLocation()
	 * @generated
	 */
	EAttribute getTrackedLocation_Heading();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.ITrackedLocation#getLatitude <em>Latitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Latitude</em>'.
	 * @see org.eclipse.examples.toast.backend.data.ITrackedLocation#getLatitude()
	 * @see #getTrackedLocation()
	 * @generated
	 */
	EAttribute getTrackedLocation_Latitude();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.ITrackedLocation#getLongitude <em>Longitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Longitude</em>'.
	 * @see org.eclipse.examples.toast.backend.data.ITrackedLocation#getLongitude()
	 * @see #getTrackedLocation()
	 * @generated
	 */
	EAttribute getTrackedLocation_Longitude();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.ITrackedLocation#getSpeed <em>Speed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Speed</em>'.
	 * @see org.eclipse.examples.toast.backend.data.ITrackedLocation#getSpeed()
	 * @see #getTrackedLocation()
	 * @generated
	 */
	EAttribute getTrackedLocation_Speed();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.ITrackedLocation#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see org.eclipse.examples.toast.backend.data.ITrackedLocation#getTime()
	 * @see #getTrackedLocation()
	 * @generated
	 */
	EAttribute getTrackedLocation_Time();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.ITrackedLocation#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.examples.toast.backend.data.ITrackedLocation#getId()
	 * @see #getTrackedLocation()
	 * @generated
	 */
	EAttribute getTrackedLocation_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.examples.toast.backend.data.IVehicle <em>Vehicle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vehicle</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IVehicle
	 * @generated
	 */
	EClass getVehicle();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.examples.toast.backend.data.IVehicle#getCurrentLocation <em>Current Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Current Location</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IVehicle#getCurrentLocation()
	 * @see #getVehicle()
	 * @generated
	 */
	EReference getVehicle_CurrentLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.examples.toast.backend.data.IVehicle#getEmergencyLocation <em>Emergency Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Emergency Location</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IVehicle#getEmergencyLocation()
	 * @see #getVehicle()
	 * @generated
	 */
	EReference getVehicle_EmergencyLocation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IVehicle#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IVehicle#getName()
	 * @see #getVehicle()
	 * @generated
	 */
	EAttribute getVehicle_Name();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.examples.toast.backend.data.IVehicle#getDriver <em>Driver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Driver</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IVehicle#getDriver()
	 * @see #getVehicle()
	 * @generated
	 */
	EReference getVehicle_Driver();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.examples.toast.backend.data.IVehicle#getPackages <em>Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Packages</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IVehicle#getPackages()
	 * @see #getVehicle()
	 * @generated
	 */
	EReference getVehicle_Packages();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IVehicle#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IVehicle#getId()
	 * @see #getVehicle()
	 * @generated
	 */
	EAttribute getVehicle_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IVehicle#isOnline <em>Online</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Online</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IVehicle#isOnline()
	 * @see #getVehicle()
	 * @generated
	 */
	EAttribute getVehicle_Online();

	/**
	 * Returns the meta object for class '{@link org.eclipse.examples.toast.backend.data.IWaybill <em>Waybill</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Waybill</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IWaybill
	 * @generated
	 */
	EClass getWaybill();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IWaybill#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IWaybill#getId()
	 * @see #getWaybill()
	 * @generated
	 */
	EAttribute getWaybill_Id();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.examples.toast.backend.data.IWaybill#getItem <em>Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Item</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IWaybill#getItem()
	 * @see #getWaybill()
	 * @generated
	 */
	EReference getWaybill_Item();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IWaybill#getReceived <em>Received</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Received</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IWaybill#getReceived()
	 * @see #getWaybill()
	 * @generated
	 */
	EAttribute getWaybill_Received();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IWaybill#getSent <em>Sent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sent</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IWaybill#getSent()
	 * @see #getWaybill()
	 * @generated
	 */
	EAttribute getWaybill_Sent();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.examples.toast.backend.data.IWaybill#getDestination <em>Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Destination</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IWaybill#getDestination()
	 * @see #getWaybill()
	 * @generated
	 */
	EReference getWaybill_Destination();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.examples.toast.backend.data.IWaybill#getOrigin <em>Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Origin</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IWaybill#getOrigin()
	 * @see #getWaybill()
	 * @generated
	 */
	EReference getWaybill_Origin();

	/**
	 * Returns the meta object for class '{@link org.eclipse.examples.toast.backend.data.IHub <em>Hub</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hub</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IHub
	 * @generated
	 */
	EClass getHub();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.examples.toast.backend.data.IHub#getVehicles <em>Vehicles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Vehicles</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IHub#getVehicles()
	 * @see #getHub()
	 * @generated
	 */
	EReference getHub_Vehicles();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.examples.toast.backend.data.IHub#getWaybills <em>Waybills</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Waybills</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IHub#getWaybills()
	 * @see #getHub()
	 * @generated
	 */
	EReference getHub_Waybills();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.examples.toast.backend.data.IHub#getDrivers <em>Drivers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Drivers</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IHub#getDrivers()
	 * @see #getHub()
	 * @generated
	 */
	EReference getHub_Drivers();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.examples.toast.backend.data.IHub#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.examples.toast.backend.data.IHub#getId()
	 * @see #getHub()
	 * @generated
	 */
	EAttribute getHub_Id();

	/**
	 * Returns the meta object for data type '{@link java.net.URI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>URI</em>'.
	 * @see java.net.URI
	 * @model instanceClass="java.net.URI"
	 * @generated
	 */
	EDataType getURI();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	IToastBackEndDataFactory getToastBackEndDataFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.examples.toast.backend.data.internal.Address <em>Address</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.examples.toast.backend.data.internal.Address
		 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getAddress()
		 * @generated
		 */
		EClass ADDRESS = eINSTANCE.getAddress();

		/**
		 * The meta object literal for the '<em><b>Area</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDRESS__AREA = eINSTANCE.getAddress_Area();

		/**
		 * The meta object literal for the '<em><b>City</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDRESS__CITY = eINSTANCE.getAddress_City();

		/**
		 * The meta object literal for the '<em><b>Country</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDRESS__COUNTRY = eINSTANCE.getAddress_Country();

		/**
		 * The meta object literal for the '<em><b>Postcode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDRESS__POSTCODE = eINSTANCE.getAddress_Postcode();

		/**
		 * The meta object literal for the '<em><b>Street</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDRESS__STREET = eINSTANCE.getAddress_Street();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDRESS__ID = eINSTANCE.getAddress_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.examples.toast.backend.data.internal.Driver <em>Driver</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.examples.toast.backend.data.internal.Driver
		 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getDriver()
		 * @generated
		 */
		EClass DRIVER = eINSTANCE.getDriver();

		/**
		 * The meta object literal for the '<em><b>Address</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DRIVER__ADDRESS = eINSTANCE.getDriver_Address();

		/**
		 * The meta object literal for the '<em><b>First Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRIVER__FIRST_NAME = eINSTANCE.getDriver_FirstName();

		/**
		 * The meta object literal for the '<em><b>Image</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRIVER__IMAGE = eINSTANCE.getDriver_Image();

		/**
		 * The meta object literal for the '<em><b>Last Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRIVER__LAST_NAME = eINSTANCE.getDriver_LastName();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRIVER__ID = eINSTANCE.getDriver_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.examples.toast.backend.data.internal.Package <em>Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.examples.toast.backend.data.internal.Package
		 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getPackage()
		 * @generated
		 */
		EClass PACKAGE = eINSTANCE.getPackage();

		/**
		 * The meta object literal for the '<em><b>Waybill</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__WAYBILL = eINSTANCE.getPackage_Waybill();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE__WEIGHT = eINSTANCE.getPackage_Weight();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE__ID = eINSTANCE.getPackage_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.examples.toast.backend.data.internal.TrackedLocation <em>Tracked Location</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.examples.toast.backend.data.internal.TrackedLocation
		 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getTrackedLocation()
		 * @generated
		 */
		EClass TRACKED_LOCATION = eINSTANCE.getTrackedLocation();

		/**
		 * The meta object literal for the '<em><b>Heading</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKED_LOCATION__HEADING = eINSTANCE.getTrackedLocation_Heading();

		/**
		 * The meta object literal for the '<em><b>Latitude</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKED_LOCATION__LATITUDE = eINSTANCE.getTrackedLocation_Latitude();

		/**
		 * The meta object literal for the '<em><b>Longitude</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKED_LOCATION__LONGITUDE = eINSTANCE.getTrackedLocation_Longitude();

		/**
		 * The meta object literal for the '<em><b>Speed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKED_LOCATION__SPEED = eINSTANCE.getTrackedLocation_Speed();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKED_LOCATION__TIME = eINSTANCE.getTrackedLocation_Time();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKED_LOCATION__ID = eINSTANCE.getTrackedLocation_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.examples.toast.backend.data.internal.Vehicle <em>Vehicle</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.examples.toast.backend.data.internal.Vehicle
		 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getVehicle()
		 * @generated
		 */
		EClass VEHICLE = eINSTANCE.getVehicle();

		/**
		 * The meta object literal for the '<em><b>Current Location</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VEHICLE__CURRENT_LOCATION = eINSTANCE.getVehicle_CurrentLocation();

		/**
		 * The meta object literal for the '<em><b>Emergency Location</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VEHICLE__EMERGENCY_LOCATION = eINSTANCE.getVehicle_EmergencyLocation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VEHICLE__NAME = eINSTANCE.getVehicle_Name();

		/**
		 * The meta object literal for the '<em><b>Driver</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VEHICLE__DRIVER = eINSTANCE.getVehicle_Driver();

		/**
		 * The meta object literal for the '<em><b>Packages</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VEHICLE__PACKAGES = eINSTANCE.getVehicle_Packages();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VEHICLE__ID = eINSTANCE.getVehicle_Id();

		/**
		 * The meta object literal for the '<em><b>Online</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VEHICLE__ONLINE = eINSTANCE.getVehicle_Online();

		/**
		 * The meta object literal for the '{@link org.eclipse.examples.toast.backend.data.internal.Waybill <em>Waybill</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.examples.toast.backend.data.internal.Waybill
		 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getWaybill()
		 * @generated
		 */
		EClass WAYBILL = eINSTANCE.getWaybill();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WAYBILL__ID = eINSTANCE.getWaybill_Id();

		/**
		 * The meta object literal for the '<em><b>Item</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WAYBILL__ITEM = eINSTANCE.getWaybill_Item();

		/**
		 * The meta object literal for the '<em><b>Received</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WAYBILL__RECEIVED = eINSTANCE.getWaybill_Received();

		/**
		 * The meta object literal for the '<em><b>Sent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WAYBILL__SENT = eINSTANCE.getWaybill_Sent();

		/**
		 * The meta object literal for the '<em><b>Destination</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WAYBILL__DESTINATION = eINSTANCE.getWaybill_Destination();

		/**
		 * The meta object literal for the '<em><b>Origin</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WAYBILL__ORIGIN = eINSTANCE.getWaybill_Origin();

		/**
		 * The meta object literal for the '{@link org.eclipse.examples.toast.backend.data.internal.Hub <em>Hub</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.examples.toast.backend.data.internal.Hub
		 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getHub()
		 * @generated
		 */
		EClass HUB = eINSTANCE.getHub();

		/**
		 * The meta object literal for the '<em><b>Vehicles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HUB__VEHICLES = eINSTANCE.getHub_Vehicles();

		/**
		 * The meta object literal for the '<em><b>Waybills</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HUB__WAYBILLS = eINSTANCE.getHub_Waybills();

		/**
		 * The meta object literal for the '<em><b>Drivers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HUB__DRIVERS = eINSTANCE.getHub_Drivers();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HUB__ID = eINSTANCE.getHub_Id();

		/**
		 * The meta object literal for the '<em>URI</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.net.URI
		 * @see org.eclipse.examples.toast.backend.data.internal.ToastBackEndDataPackage#getURI()
		 * @generated
		 */
		EDataType URI = eINSTANCE.getURI();

	}

} //IToastBackEndDataPackage

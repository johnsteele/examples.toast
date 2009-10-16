/*******************************************************************************
 * Copyright (c) 2009 EclipseSource Inc. and others. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v1.0 
 * which accompanies this distribution. The Eclipse Public License is available at 
 * http://www.eclipse.org/legal/epl-v10.html and the Eclipse Distribution License 
 * is available at http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors: 
 *     EclipseSource Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.examples.toast.rap.drivingsimulator;

import org.eclipse.swt.widgets.Composite;

/**
 * This class represents the Driving Simulator widget. It creates a google
 * earth representation on the client site. You can fly to positions or 
 * calculate a route. After calculating a rout you can send a Smart on the 
 * trip. Works only with if the Google Earth Browser Plugin is installed. If
 * not see: http://code.google.com/intl/de-DE/apis/earth/documentation/#install 
 */
public class DrivingSimulator extends Composite {
  
  private DrivingSimulatorAdapter adapter;
  private int[] coordinates;
  private boolean decreaseSpeed;
  private int[] flyData;
  private boolean flyTo;
  private boolean increaseSpeed;
  private boolean readyToStart;
  private boolean reset;
  private boolean start;
  private boolean stop;
  
  public DrivingSimulator( Composite parent, int style ) {
    super( parent, style ); 
    adapter = new DrivingSimulatorAdapter( this );
    readyToStart = false;
    start = false;
    stop = false;
    increaseSpeed = false;
    decreaseSpeed = false;
    reset = false;
    flyTo = false;
  }
  
  /**
   * Decrease the driving speed of the Smart
   */
  public void decreaseSpeed() {
    decreaseSpeed = true;
  }

  /**
   * Move the users view to the given location.
   */
  public void flyTo(
    final int latitude, 
    final int longitude, 
    final int heading, 
    final int zoom) 
  {
    flyData = new int[] { latitude, longitude, heading, zoom };
    flyTo = true;
  }
  
  public Object getAdapter( Class adapter ) {
    Object result = null;
    if( adapter == DrivingSimulatorAdapter.class ) {
      result = this.adapter;
    } else {
      result = super.getAdapter( adapter );
    }
    return result;
  }
  
  /**
   * Returns the starting and endpoint lat/long as an array.
   * the 1st and 2nd entries are the origin lat/long. The 3rd and 4th entries 
   * are the target lat/long.
   * 
   * @return The route points as an array.
   */
  public int[] getDirections() {
    return coordinates;
  }
  
  int[] getFlyData() {
    return flyData;
  }
  
  boolean getReadyToStart() {
    return readyToStart;
  }
  
  /**
   * Increase the driving speed of the Smart
   */
  public void increaseSpeed() {
    increaseSpeed = true;
  }
  
  boolean isDecreaseSpeed() {
    return decreaseSpeed && readyToStart;
  }
  
  /**
   * Check if the Smart is driving.
   * @return <code>true</code> when the Smart drives.
   */
  public boolean isDriving() {
    return start && readyToStart;
  }
  
  boolean isFlyTo() {
    return flyTo;
  }
  
  boolean isIncreaseSpeed() {
    return increaseSpeed && readyToStart;
  }

  boolean isReset() {
    return reset && readyToStart;
  }
  
  /**
   * Calculates the route from the origin lat/long the the destination
   * lat/long.
   * @param originLatitude The starting latitude.
   * @param originLongitude The starting longitude.
   * @param destLatitude The target latitude.
   * @param destLongitude The target longitude.
   */
  public void loadDirections( 
    final int originLatitude, 
    final int originLongitude, 
    final int destLatitude, 
    final int destLongitude) 
  {
    coordinates = new int[] { originLatitude, originLongitude, 
                              destLatitude, destLongitude };
    readyToStart = true;
  }
  
  /**
   * Reset the the Smart to it's starting point.
   */
  public void reset() {
    reset = true;
  }
  
  void resetDecreaseSpeed() {
    decreaseSpeed = false;
  }
  
  void resetDone() {
    reset = false;
  }
  
  void resetFlyTo() {
    flyTo = false;
  }
  
  void resetIncreaseSpeed() {
    increaseSpeed = false;
  }
  
  void resetStart() {
    start = false;
  }
  
  void resetStop() {
    stop = false;
  }
  
  /**
   * Let the Smart drive.
   */
  public void start() {
    if( readyToStart && !start ) {
      start = true;
    }
  }
  
  /**
   * Stops the Smart if it's driving.
   */
  public void stop() {
    if( readyToStart && !stop ) {
      stop = true;
    }
  }
  
  boolean wantToStart() {
    return readyToStart && start;
  }
  
  boolean wantToStop() {
    return readyToStart && stop;
  }

}

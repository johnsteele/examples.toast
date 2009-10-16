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


public class DrivingSimulatorAdapter {

  private DrivingSimulator simulator;

  public DrivingSimulatorAdapter( final DrivingSimulator drivingSimulator ) {
    simulator = drivingSimulator;
  }
  
  public Object[] getFlyData() {
    int[] flyData = simulator.getFlyData();
    Object[] result = null;
    if( flyData != null && flyData.length > 0 ) {
      result = new Object[] { new Integer( flyData[ 0 ] ),
                              new Integer( flyData[ 1 ] ),
                              new Integer( flyData[ 2 ] ),
                              new Integer( flyData[ 3 ] )
      };
    }
    return result;
  }
  
  public boolean getReadyToStart() {
    return simulator.getReadyToStart();
  }
  
  public boolean isDecreaseSpeed() {
    return simulator.isDecreaseSpeed();
  }
  
  public boolean isFlyTo() {
    return simulator.isFlyTo();
  }
  
  public boolean isIncreaseSpeed() {
    return simulator.isIncreaseSpeed();
  }
  
  public boolean isReset() {
    return simulator.isReset();
  }
  
  public void resetDecreaseSpeed() {
    simulator.resetDecreaseSpeed();
  }
  
  public void resetDone() {
    simulator.resetDone();
  }
  
  public void resetFlyTo() {
    simulator.resetFlyTo();
  }
  
  public void resetIncreaseSpeed() {
    simulator.resetIncreaseSpeed();
  }
  
  public void resetStart() {
    simulator.resetStart();
  }

  public void resetStop() {
    simulator.resetStop();
  }
  
  public boolean wantToStart() {
    return simulator.wantToStart();
  }
  
  public boolean wantToStop() {
    return simulator.wantToStop();
  }
  
  
}

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
package org.eclipse.examples.toast.rap.internal.drivingsimulator.drivingsimulatorkit;

import java.io.IOException;

import org.eclipse.examples.toast.rap.drivingsimulator.DrivingSimulator;
import org.eclipse.examples.toast.rap.drivingsimulator.DrivingSimulatorAdapter;
import org.eclipse.rwt.lifecycle.AbstractWidgetLCA;
import org.eclipse.rwt.lifecycle.ControlLCAUtil;
import org.eclipse.rwt.lifecycle.IWidgetAdapter;
import org.eclipse.rwt.lifecycle.JSWriter;
import org.eclipse.rwt.lifecycle.WidgetLCAUtil;
import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.widgets.Widget;


public class DrivingSimulatorLCA extends AbstractWidgetLCA {

  private static final String READY_TO_START = "readyToStart";

  private DrivingSimulatorAdapter getSimulatorAdapter( 
    final DrivingSimulator simulator )
  {
    DrivingSimulatorAdapter result = null;
    Object adapter = simulator.getAdapter( DrivingSimulatorAdapter.class );
    if( adapter instanceof DrivingSimulatorAdapter ) {
      result = ( DrivingSimulatorAdapter ) adapter;
    }
    return result;
  }

  public void preserveValues( final Widget widget ) {
    DrivingSimulator simulator = ( DrivingSimulator ) widget;
    ControlLCAUtil.preserveValues( simulator );
    IWidgetAdapter adapter = WidgetUtil.getAdapter( widget ); 
    DrivingSimulatorAdapter simAdapter = getSimulatorAdapter( simulator );
    if( simAdapter != null ) {
      adapter.preserve( READY_TO_START, 
                        new Boolean( simAdapter.getReadyToStart() ) );
    }
  }

  public void readData( final Widget widget ) {
    DrivingSimulator sim = ( DrivingSimulator ) widget;
    // start
    String val = WidgetLCAUtil.readPropertyValue( sim, "wantStart" );
    try {
      boolean wantStart = Boolean.valueOf( val ).booleanValue();
      if( wantStart ) {
        sim.start();
      }
    } catch( Exception e ) {
      // do nothing
    }
    // flyto
    val = WidgetLCAUtil.readPropertyValue( sim, "wantFly" );
    try {
      boolean wantFly = Boolean.valueOf( val ).booleanValue();
      if( wantFly ) {
        // TODO
      }
    } catch( Exception e ) {
      // do nothing
    }
  }

  public void renderChanges( final Widget widget ) throws IOException {
    DrivingSimulator simulator = ( DrivingSimulator ) widget;
    ControlLCAUtil.writeChanges( simulator );
    JSWriter writer = JSWriter.getWriterFor( widget );
    
    DrivingSimulatorAdapter adapter = getSimulatorAdapter( simulator );
    if( adapter != null ) {
      // check fly to starting point
      Boolean widgetReady = new Boolean( adapter.getReadyToStart() );
      if( WidgetLCAUtil.hasChanged( widget, READY_TO_START, widgetReady ) ) {
        int[] originDirections = simulator.getDirections();
        if( originDirections != null && originDirections.length > 0 ) {
          Object[] directions 
            = new Object[] { new Integer( originDirections[ 0 ] ),
                             new Integer( originDirections[ 1 ] ),
                             new Integer( originDirections[ 2 ] ),
                             new Integer( originDirections[ 3 ] )
                            };                  
          writer.call( widget, "loadDirections", directions );
        }
      }
      
      // start driving
      if( adapter.wantToStart() ) {
        writer.call( widget, "startDriving", null );
        adapter.resetStart();
      }
      
      // stop driving
      if( adapter.wantToStop() ) {
        writer.call( widget, "stopDriving", null );
        adapter.resetStop();
      }
      
      // increase speed
      if( adapter.isIncreaseSpeed() ) {
        writer.call( widget, "increaseSpeed", null );
        adapter.resetIncreaseSpeed();
      }
      
      // decrease speed
      if( adapter.isDecreaseSpeed() ) {
        writer.call( widget, "decreaseSpeed", null );
        adapter.resetDecreaseSpeed();
      }
      
      // reset
      if( adapter.isReset() ) {
        writer.call( widget, "reset", null );
        adapter.resetDone();
      }
      
      // flyTo
      if( adapter.isFlyTo() ) {
        Object[] flyData = adapter.getFlyData();
        if( flyData != null ) {
          writer.call( widget, "flyTo", flyData );
        }
        adapter.resetFlyTo();
      } 
    }
  }

  public void renderDispose( final Widget widget ) throws IOException {
    JSWriter writer = JSWriter.getWriterFor( widget );
    writer.dispose();
  }

  public void renderInitialization( final Widget widget ) throws IOException {
    JSWriter writer = JSWriter.getWriterFor( widget );
    writer.newWidget( "org.eclipse.examples.toast.rap.drivingsimulator.DrivingSimulator" );
    writer.set( "appearance", "composite" );
    writer.set( "overflow", "hidden" );
    ControlLCAUtil.writeStyleFlags( ( DrivingSimulator ) widget );
  }
}

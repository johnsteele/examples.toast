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
package org.eclipse.examples.toast.backend.rap;

import org.eclipse.examples.toast.rap.drivingsimulator.DrivingSimulator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class EarthView extends ViewPart {

	public static final String ID = "org.eclipse.examples.toast.backend.rap.earthview";

	private DrivingSimulator simulator;

	public EarthView() {
	}

	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());
		simulator = new DrivingSimulator(parent, SWT.NONE);
		FormData fdSimulator = new FormData();
		simulator.setLayoutData(fdSimulator);
		fdSimulator.bottom = new FormAttachment(100);
		fdSimulator.top = new FormAttachment(0, 0);
		fdSimulator.left = new FormAttachment(0);
		fdSimulator.right = new FormAttachment(100);

		//    // just a demo
		//    Job j = new Job( "test") {
		//    
		//      protected IStatus run( IProgressMonitor monitor ) {
		//        int lat = 49012119;
		//        int longi = 8395357;
		//        while( longi < 8495357 ) {
		//          simulator.flyTo( lat, longi, 90, 200 );
		//          try {
		//            Thread.sleep( 10000 );
		//          } catch( InterruptedException e ) {
		//            e.printStackTrace();
		//          }
		//          lat += 200;
		//          longi += 200;
		//        }
		//        return Status.OK_STATUS;
		//      }
		//      
		//    };
		//    j.schedule();

	}

	public void setFocus() {
		simulator.setFocus();
		//simulator.moveAbove( null );
	}

}

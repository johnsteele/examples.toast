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
qx.Class.define( "org.eclipse.examples.toast.rap.drivingsimulator.DrivingSimulator", {
  extend: qx.ui.layout.CanvasLayout,
  
  construct: function() {
    this.base( arguments );
    DS_widget = this;
    this.addEventListener( "appear", this._loadEarth, this );
  
    this.timer.addEventListener("interval", function(e) {
      if( !org_eclipse_rap_rwt_EventUtil_suspend ) {
          if( DS_ge != null ) {
            var wm = org.eclipse.swt.WidgetManager.getInstance();
            var sim = wm.findIdByWidget( DS_widget );
            var req = org.eclipse.swt.Request.getInstance();
            req.addParameter( sim + ".wantFly", true );
            req.send();
          }
      }
        
     }, this );      
     this.timer.start(); 
     
     this.addEventListener( "blur", function(e){
       this.timer.stop();
       DS_ge = null;
     }, this );
    
  },
  
    
  members : {
    timer : new qx.client.Timer( 5000 ),
    wantLoad : false,
    wantStart : false,
    wantFly : false,
    latitude : 0,
    longitude : 0,
    heading : 0,
    zoom : 0,
    oLat : 0,
    oLong : 0,
    dLat : 0,
    dLong : 0,

    _loadEarth : function() {
      if( DS_ge == null ) {
        google.earth.createInstance( this.getElement(), 
                                     this._initCB, 
                                     this._failureCB );
        mapParent = document.createElement('div');
  
        DS_map = new GMap2( mapParent );           
      }                            
    },
    
    _doActivate : function() {
      var shell = null;
      var parent = this.getParent();
      while( shell == null && parent != null ) {
        if( parent.classname == "org.eclipse.swt.widgets.Shell" ) {
          shell = parent;
        }
        parent = parent.getParent();
      }
      if( shell != null ) {
        shell.setActiveChild( this );
      }
     },
      
    _initCB : function( object ) {
      DS_ge = object;
      DS_ge.getWindow().setVisibility( true );
      DS_ge.getLayerRoot().enableLayerById( DS_ge.LAYER_BUILDINGS, true );
      DS_ge.getLayerRoot().enableLayerById( DS_ge.LAYER_BORDERS, true );
      DS_geHelpers = new GEHelpers( DS_ge );
      
      if( DS_widget.wantLoad ) {
        DS_widget.wantLoad = false;
        DS_widget.loadDirections( DS_widget.oLat, 
                                  DS_widget.oLong, 
                                  DS_widget.dLat, 
                                  DS_widget.dLong );  
      } 
      if( DS_widget.wantFly ) {
        DS_widget.flyTo( DS_widget.latitude,
                         DS_widget.longitude,
                         DS_widget.heading,
                         DS_widget.zoom );
        DS_widget.wantFly = false;                         
      }
    },
    
    _failureCB : function() {
      alert('loading earth failed, maybe its destroyed?');
    },
    
    startDriving : function() {
      if( DS_ge != null ) {
        DS_controlSimulator( 'start' );
      } else {
        DS_widget.wantStart = true;
      }
    },
    
    stopDriving : function() {
      if( DS_ge != null ) {
        DS_controlSimulator( 'pause' );
      }
    },
    
    increaseSpeed : function() {
      if( DS_ge != null ) {
        DS_controlSimulator( 'faster' );
      }
    },
    
    decreaseSpeed : function() {
      if( DS_ge != null ) {
        DS_controlSimulator( 'slower' );
      }
    },
    
    reset : function() {
      if( DS_ge != null ) {
        DS_controlSimulator( 'reset' );
      }
    },
    
    flyTo : function( latitude, longitude, heading, zoom ) {
      DS_widget.latitude = latitude;
      DS_widget.longitude = longitude;
      DS_widget.heading = heading;
      DS_widget.zoom = zoom;
      if( DS_ge != null ) {
        lats = latitude / 1000000.0;
        longs = longitude / 1000000.0;
        DS_flyToLatLng2( new google.maps.LatLng( lats, longs ), heading, zoom); 
      } else {
        DS_widget.wantFly = true;
      }
    },
    
    loadDirections : function( oLat, oLong, dLat, dLong ) {
      DS_widget.oLat = oLat;
      DS_widget.oLong = oLong;
      DS_widget.dLat = dLat;
      DS_widget.dLong = dLong;
      if( DS_ge != null ) {
        oLat2 = DS_widget.oLat / 1000000.0;
        oLong2 = DS_widget.oLong / 1000000.0;
        dLat2 = DS_widget.dLat / 1000000.0;
        dLong2 = DS_widget.dLong / 1000000.0;
        DS_goDirections2( oLat2, oLong2, dLat2, dLong2);
      } else {
        DS_widget.wantLoad = true;
      }
    },
    
    _doResize : function() {
      //qx.ui.core.Widget.flushGlobalQueues();
    }      
    
  }

} );   
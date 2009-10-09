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

qx.Class.define( "org.eclipse.examples.toast.rap.gmaps.GMap", {
  extend: qx.ui.layout.CanvasLayout,

  construct: function( id ) {
    this.base( arguments );
    this.setHtmlAttribute( "id", id );
    this._id = id;
    this._map = null;
    if( GBrowserIsCompatible() ) {
      this._geocoder = new GClientGeocoder();
      this.addEventListener( "changeHeight", this._doResize, this );
      this.addEventListener( "changeWidth", this._doResize, this );
    }
  },

  members : {

    _createMap : function() {
      qx.ui.core.Widget.flushGlobalQueues();
      if( this._map == null ) {
        this._map = new GMap2( document.getElementById( this._id ) );
        this._map.addControl( new GSmallMapControl() );
        this._map.addControl( new GMapTypeControl() );
        GEvent.bind( this._map, "click", this, this._doActivate );
        GEvent.bind( this._map, "moveend", this, this._onMapMove );
        this._map.setCenter( new GLatLng( 37.4419, -122.1419 ), 13 );
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

    /*
     * address is either a string or an array of coordinates [lat, lon].
     * marker is the text to be displayed in the marker. If null, no marker is being displayed.
     */
    setCenter : function( address ) {
      if( GBrowserIsCompatible() && address != null && address != "" ) {
        this._createMap();
        var map = this._map;
        var func = function( point ) {
          if( !point ) {
            alert( "Address not found: '" + address + "'" );
          } else {
//            map.setCenter( point, 13 );
            map.setCenter( point );
          }
        };
        if( typeof( address ) == "string" ) {
          this._geocoder.getLatLng( address, func );
        } else {
          func( new GLatLng( address[ 0 ], address[ 1 ] ) );
        }
      }
    },

    addMarker : function( address, html ) {
      if( GBrowserIsCompatible() && address != null && address != "" ) {
        this._createMap();
        var map = this._map;
        var func = function( point ) {
          if( !point ) {
            alert( "Address not found: '" + address + "'" );
          } else {
            var marker = new GMarker( point );
            map.addOverlay( marker );
            if( html != null ) {
              marker.openInfoWindowHtml( html );
            }
          }
        };
        if( typeof( address ) == "string" ) {
          this._geocoder.getLatLng( address, func );
        } else {
          func( new GLatLng( address[ 0 ], address[ 1 ] ) );
        }
      }
    },

    clearMarkers : function() {
      if( GBrowserIsCompatible() ) {
        this._createMap();
        this._map.clearOverlays();
      }
    },

    _onMapMove : function() {
      if( !org_eclipse_rap_rwt_EventUtil_suspend ) {
        var wm = org.eclipse.swt.WidgetManager.getInstance();
        var gmapId = wm.findIdByWidget( this );
        var req = org.eclipse.swt.Request.getInstance();
        var center = this._map.getCenter();
        req.addParameter( gmapId + ".centerLat", center.lat() );
        req.addParameter( gmapId + ".centerLon", center.lng() );
      }
    },

    _doResize : function() {
      qx.ui.core.Widget.flushGlobalQueues();
      if( this._map != null ) {
        this._map.checkResize();
      }
    }
  }

} );

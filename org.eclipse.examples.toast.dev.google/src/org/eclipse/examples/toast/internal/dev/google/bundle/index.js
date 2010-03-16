// index.js
/*
Copyright 2008 Google Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

/**
 * @fileoverview This is the main JavaScript file for the Driving Simulator
 * @author Roman Nurik
 * @supported Tested in IE6+ and FF2+
 */

/**
 * The global Directions object for the currently loaded directions
 * @type {google.maps.Directions}
 */
var DS_directions = null;

/**
 * The list of driving steps loaded from google.maps.Directions
 * @type {Array.<Object>}
 */
var DS_steps = [];

/**
 * The list of path vertices and their metadata for the driving directions
 * @type {Array.<Object>}
 */
var DS_path = []; // entire driving path

/**
 * The global simulator instance that conducts the driving simulation
 * @type {DDSimulator}
 */
var DS_simulator; // instance of the DDSimulator class

/**
 * The car marker that appears on the reference map to the right of the main
 * simulation screen
 * @type {google.maps.Marker}
 */
var DS_mapMarker = null; // car marker on the Map

/**
 * Instead of using the plugin's built-in ID system, which doesn't like when
 * IDs are reused, we will use a separate dictionary mapping ID to placemark
 * object
 * @type {Object}
 */
var DS_placemarks = {};

/**
 * The callback for when the 'Go!' button is pressed. This uses the Maps API's
 * Directions class to get the route and pull out the individual route steps
 * into a path, which is rendered as a polyline.
 */
function DS_goDirections(from, to) {
  if (DS_directions)
    DS_directions.clear();
  DS_directions = new google.maps.Directions(DS_map, null);
  google.maps.Event.addListener(DS_directions, 'load', DS_directionsLoaded);
  DS_directions.load('from: ' + from + ' to: ' + to, {getSteps: true, getPolyline: true});
}

/**
 * Initialization after directions are loaded
 */
function DS_directionsLoaded() {
  // Directions data has loaded
  var route = DS_directions.getRoute(0);
  var start = route.getStartGeocode();
  var end = route.getEndGeocode();
  
  // build the path and step arrays from the google.maps.Directions route
  DS_buildPathStepArrays();
  
  DS_geHelpers.clearFeatures();
  DS_placemarks = {};
  
  // create the starting point placemark
  DS_placemarks['start'] = DS_geHelpers.createPointPlacemark(
      new google.maps.LatLng(start.Point.coordinates[1],
                             start.Point.coordinates[0]),
      {description: start.address, standardIcon: 'grn-diamond'});
  
  // create the point placemarks for each step in the driving directions
  for (var i = 0; i < DS_steps.length; i++) {
    var step = DS_steps[i];
    
    var placemark = DS_geHelpers.createPointPlacemark(
        step.loc, {description: step.desc, standardIcon: 'red-circle'});
    
    DS_placemarks['step-' + i] = placemark; 
    
    google.earth.addEventListener(placemark, 'click', function(event) {
      // match up the placemark to its id in the dictionary to find out
      // which step number it is
      var id = '';
      for (k in DS_placemarks)
        if (DS_placemarks[k].equals(event.getTarget()))
          id = k;
      
      var stepNum = parseInt(id.match(/step-(\d+)/)[1]);
      
      DS_flyToStep(stepNum);
    });
  }
  
  // create the ending point placemark
  DS_placemarks['end'] = DS_geHelpers.createPointPlacemark(
      new google.maps.LatLng(end.Point.coordinates[1],
                             end.Point.coordinates[0]),
      {description: end.address, standardIcon: 'grn-diamond'});
  
  // build the route LineString; instead of creating a LineString using
  // pushLatLngAlt, which has some performance issues, we will construct a
  // KML blob and use parseKml() 
  var lineStringKml = '<LineString><coordinates>\n';
  
  for (var i = 0; i < DS_path.length; i++)
    lineStringKml +=
        DS_path[i].loc.lng().toString() + ',' +
        DS_path[i].loc.lat().toString() +
        ',10\n';
  
  lineStringKml += '</coordinates></LineString>';
  
  // create the route placemark from the LineString KML blob
  var routeLineString = DS_ge.parseKml(lineStringKml);
  routeLineString.setTessellate(true);
  
  var routePlacemark = DS_ge.createPlacemark('');
  routePlacemark.setGeometry(routeLineString);
  DS_placemarks['route'] = routePlacemark;
  
  routePlacemark.setStyleSelector(
      DS_geHelpers.createLineStyle({width: 10, color: '88ff0000'}));
  
  DS_ge.getFeatures().appendChild(routePlacemark);

  // fly to the start of the route
  DS_flyToLatLng(start.Point.coordinates[1], start.Point.coordinates[0], 0, 200);
  
  // enable the simulator controls
//  $('#simulator-form input').removeAttr('disabled');
  
  // destroy the simulator if exists
  if (DS_simulator) {
    DS_simulator.destroy();
    DS_simulator = null;
  }
}

/**
 * Generates the DS_path and DS_step arrays from the global DS_directions
 * instance
 * 
 * NOTE: only the first route is used
 */
function DS_buildPathStepArrays() {
  // begin processing the directions' steps and path
  DS_steps = [];
  DS_path = [];
  
  var polyline = DS_directions.getPolyline();
  var route = DS_directions.getRoute(0);
  var numPolylineVertices = polyline.getVertexCount();
  var numSteps = route.getNumSteps();
  
  for (var i = 0; i < numSteps; i++) {
    var step = route.getStep(i);
    
    var firstPolylineIndex = step.getPolylineIndex();
    
    var lastPolylineIndex = -1;
    if (i == numSteps - 1)
      lastPolylineIndex = numPolylineVertices - 1;
    else {
      // subtract 2 because the last vertex of a step is duplicated
      // as the first vertex of the next step in google.maps.Directions results
      lastPolylineIndex = route.getStep(i + 1).getPolylineIndex() - 2;
    }
    
    DS_steps.push({
      loc: step.getLatLng(),
      desc: step.getDescriptionHtml(),
      distanceHtml: step.getDistance().html,
      pathIndex: DS_path.length
    });
    
    var stepDistance = step.getDistance().meters;
    for (var j = firstPolylineIndex; j <= lastPolylineIndex; j++) {
      var loc = polyline.getVertex(j);
      var distance = (j == numPolylineVertices - 1) ?
                     0 : DS_geHelpers.distance(loc, polyline.getVertex(j + 1));
      
      DS_path.push({
        loc: loc,
        step: i,
        distance: distance,
        
        // this segment's time duration is proportional to its length in
        // relation to the length of the step
        duration: step.getDuration().seconds * distance / stepDistance
      });
    }
  }
}

/**
 * Fly the camera to the given step index in the route, and highlight it in
 * the directions list
 * @param {number} stepNum The 0-based step index to fly to
 */
function DS_flyToStep(stepNum) {
  var step = DS_steps[stepNum];
  
  var la = DS_ge.createLookAt('');
  la.set(step.loc.lat(), step.loc.lng(),
      0, // altitude
      DS_ge.ALTITUDE_RELATIVE_TO_GROUND,
      DS_geHelpers.getHeading(step.loc, DS_path[step.pathIndex + 1].loc),
      60, // tilt
      50 // range (inverse of zoom)
      );
  DS_ge.getView().setAbstractView(la);
}

/**
 * Move the camera to the given location, staring straight down, and unhighlight
 * all items in the left directions list
 * @param {google.maps.LatLng} loc The location to fly the camera to
 */
function DS_flyToLatLng(lat, long, heading, range) {
  var la = DS_ge.createLookAt('');
  la.set(lat, long,
      10, // altitude
      DS_ge.ALTITUDE_RELATIVE_TO_GROUND,
      heading, // heading
      0, // tilt
      range // range (inverse of zoom)
      );
  DS_ge.getView().setAbstractView(la);
}

function getLat() {
	if (DS_simulator && DS_simulator.currentLoc) 
		return DS_simulator.currentLoc.lat(); 
	return 37.38946;
}

function getLng() {
	if (DS_simulator && DS_simulator.currentLoc) 
		return DS_simulator.currentLoc.lng(); 
	return -122.08168;
}

function toast_position_callback(){}

/**
 * Simulator controls
 * @param {string} command The control command to run
 * @param {Function?} opt_cb Optional callback to run when the command
 *     completes its task
 */
function DS_controlSimulator(command, opt_cb) {
  switch (command) {
    case 'reset':
      if (DS_simulator)
        DS_simulator.destroy();
      
      // create a DDSimulator object for the current DS_path array
      // on the DS_ge Earth instance
      DS_simulator = new DDSimulator(DS_ge, DS_path, {
        // as the simulator runs, reposition the map on the right and the
        // car marker on the map, and update the status box on the bottom
        on_tick: function() {
          if (toast_position_callback)
            toast_position_callback(DS_simulator.currentLoc.lat(), DS_simulator.currentLoc.lng());
       },
        
        // when the simulator moves to a new step (specified as an integer
        // index in DS_path items), highlight that step in the directions
        // list
        on_changeStep: function(stepNum) {
          if (toast_segment_callback)
            toast_segment_callback(stepNum);
        }
      });
      
      DS_simulator.initUI(opt_cb);
      break;
    
    case 'start':
      if (!DS_simulator)
        DS_controlSimulator('reset', function() {
          DS_simulator.start();
          if (opt_cb) opt_cb();
        });
      else {
        DS_simulator.start();
        if (opt_cb) opt_cb();
      }
      break;
    
    case 'pause':
      if (DS_simulator)
        DS_simulator.stop();
      
      if (opt_cb) opt_cb();
      break;
    
    case 'resume':
      if (DS_simulator)
        DS_simulator.start();
      
      if (opt_cb) opt_cb();
      break;
    
    case 'slower':
      if (DS_simulator && DS_simulator.options.speed > 0.125) {
        DS_simulator.options.speed /= 2.0;
      }
      break;
    
    case 'faster':
      if (DS_simulator && DS_simulator.options.speed < 32.0) {
        DS_simulator.options.speed *= 2.0;
      }
      break;
  }
}

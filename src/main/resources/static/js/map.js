let map;
let markers;
let myLatLng = {lat: 46.7693924, lng: 23.5902006};

function initializeMap() {
    map = new OpenLayers.Map("mapdiv");
    map.addLayer(new OpenLayers.Layer.OSM());

    let zoom = 10;
    let lonLat = getLonLat(myLatLng.lat, myLatLng.lng);

    markers = new OpenLayers.Layer.Markers("Markers");
    map.addLayer(markers);

    markers.addMarker(new OpenLayers.Marker(lonLat));

    map.setCenter(lonLat, zoom);
}

function addStaticMarker(pos) {
    let lonLat = getLonLat(pos.lat, pos.lng);
    markers.addMarker(new OpenLayers.Marker(lonLat))
}

function getRandomPosition() {
    let randLatLng = {
        lat: (myLatLng["lat"] + Math.floor(Math.random() * 5) + 1),
        lng: (myLatLng["lng"] + Math.floor(Math.random() * 5) + 1)
    };
    return randLatLng;
}

function getLonLat(lat, lng) {
    return new OpenLayers.LonLat(lng, lat)
        .transform(
            new OpenLayers.Projection("EPSG:4326"),
            map.getProjectionObject()
        );
}

function cleanMarkers()
{


    map.removeLayer(markers);

    let lonLat = getLonLat(myLatLng.lat, myLatLng.lng);
    markers = new OpenLayers.Layer.Markers("Markers");
    map.addLayer(markers);
    markers.addMarker(new OpenLayers.Marker(lonLat));



}

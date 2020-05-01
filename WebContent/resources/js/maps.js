var map;
var myURI = "/jruteros/rest/puntos";
var mapProp = {
	center : new google.maps.LatLng(-34.9038055, -57.9392111, 18),
	zoom : 5,
	mapTypeId : google.maps.MapTypeId.ROADMAP
};

var puntos = [];

// Evento
//google.maps.event.addDomListener(window, 'load', initialize);

/**
 * Inicializa el mapa
 */
var positions = [];

function initialize1(puntos){
	
	positions = [];
	map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
	map.addListener('click', function(e) {
			var position = e.latLng;
			position.id = null;
			positions.push(position);
			dibujarMarker1(position);
			dibujarRecorrido1();
			//redibujar todo el recorrido!!!
    
	});
	
	puntos.forEach(function(punto){
		var position = new google.maps.LatLng(punto.latitud, punto.longitud);
		positions.id = punto.id;
		positions.push(position);
		dibujarMarker1(position);
	});
	dibujarRecorrido1();
}

function dibujarMarker1(position) {

	var marker = new google.maps.Marker({
		position: position,
		icon: {
		      path: google.maps.SymbolPath.CIRCLE,
		      scale: 3
		}
	});

	marker.addListener("rightclick", function(e) {
		var position = e.latLng;
		positions = positions.filter(function(p){
			return p.lat() != position.lat() && p.lng() != position.lng();
		});
		marker.setMap(null);
		map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
		map.addListener('click', function(e) {
				var position = e.latLng;
				position.id = null;
				positions.push(position);
				dibujarMarker1(position);
				dibujarRecorrido1();
		});
		positions.forEach(function(p){
			dibujarMarker1(p);
		});
	    dibujarRecorrido1();
	});

	marker.setMap(map);
}

function borrarMarker1(id) {
	punto = {
		id: id
	};
	$.ajax({
		data : punto,
		url : myURI+"/"+id ,
		type : "DELETE",
		success : function(result) {
			initialize();
		}
	});
}

function dibujarRecorrido1() {

	var flightPath = new google.maps.Polyline({
		path : positions,
		strokeColor : "#0000FF",
		strokeOpacity : 0.8,
		strokeWeight : 2
	});

	flightPath.setMap(map);
}

function initialize() {
	positions = [];
	map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

	map.addListener('click', function(e) {
		agregarMarker(e.latLng, map);
    
	});
	
	obtenerMarkers();
}

function getPuntos(){
	return positions.map( function(punto){ return {id: punto.id, latitud: punto.lat(), longitud: punto.lng() };});
}

// Obtiene markers y los dibuja
function obtenerMarkers() {

	$.ajax({
		dataType : "json",
		url : myURI,
		success : function(result) {
			puntos = [];
			$.each(result, function(i, dato) {
				dibujarMarker1(dato);

			});
			dibujarRecorrido();
		}
	});
}

function dibujarMarker(dato) {
	var position = new google.maps.LatLng(dato.latitud, dato.longitud);

	var marker = new google.maps.Marker({
		position : position,
		icon: {
		      path: google.maps.SymbolPath.CIRCLE,
		      scale: 3
		    },
		id : dato.id
	});

	marker.addListener("rightclick", function(point) {
		borrarMarker(dato.id);
		marker.setMap(null);
	});

	puntos[puntos.length] = position;

	marker.setMap(map);
}

function agregarMarker(latLng) {
	
	var punto = {
		latitud: latLng.lat(),
		longitud: latLng.lng()
	};

	$.ajax({
		data : JSON.stringify(punto),
		url : myURI,
		type : "POST",
		contentType: "application/json",
		success : function(result) {
			obtenerMarkers();

		}
	});

}

function dibujarRecorrido() {

	var flightPath = new google.maps.Polyline({
		path : puntos,
		strokeColor : "#0000FF",
		strokeOpacity : 0.8,
		strokeWeight : 2
	});

	flightPath.setMap(map);
}

function dibujarRecorridoCircular() {
	markers = puntos;
	markers[markers.length] = puntos[0];
	var flightPath = new google.maps.Polyline({
		path : markers,
		strokeColor : "#0000FF",
		strokeOpacity : 0.8,
		strokeWeight : 2
	});

	flightPath.setMap(map);
}

function limpiarMapa() {
	punto = {
		id: null
	};
	$.ajax({
		data : punto,
		url : myURI,
		type : "DELETE",
		success : function(result) {
			initialize();
		}
	});
}

function limpiarMapa1() {
	initialize1();
}

function borrarMarker(id) {
	punto = {
		id: id
	};
	$.ajax({
		data : punto,
		url : myURI+"/"+id ,
		type : "DELETE",
		success : function(result) {
			initialize();
		}
	});
}



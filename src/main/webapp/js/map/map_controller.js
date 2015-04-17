OpenLayers.ProxyHost 	= baseUrl + "proxy/proxy.jsp?url=";

var map = null;

var vBaseMap = null;
var vGrayMap = null;
var vSatelliteMap = null;
var vHybridMap = null;
var gBaseMap = null;
var gSatelliteMap = null;
var openStreetMap = null;
var openStreetGrayMap = null;

var info = null;
var selectControl = null;

var gCurrentZoneLayer_sido 		= null;
var gCurrentZoneLayer_sigungu	= null;
var gCurrentZoneLayer_emd 		= null;

var LINK_LAYER = "LINK_LAYER";

var searchMarkers		= null;

/*
var infoStyleMap = new OpenLayers.StyleMap({
    "default": new OpenLayers.Style({
        strokeColor: "blue",
        fillColor: "red",
        strokeWidth: 2,
        strokeOpacity: 1,
        fillOpacity: 0.7,
        pointRadius: 5
    })
});
*/


// var map_1 = null;
// var map_2 = null;
var map_location = null;


/********************************************************************************
 * 기      능   	:  load
 * @param obj	: 
 * @return  		: 
 ********************************************************************************/
	 $(window).load(function(){
		 
	 });
 
/********************************************************************************
 * 기      능   	:  ready
 * @param obj	: 
 * @return  		: 
 ********************************************************************************/
	 $(document).ready(function(){
		 map_init();
	 });
	 
 /********************************************************************************
  * 기      능  : map 초기화
  * @param obj	:
  * @return  	:
  ********************************************************************************/
  var map_init = function (baselayerName){
	  	
	  	OpenLayers.IMAGE_RELOAD_ATTEMPTS = 0;
		OpenLayers.Util.onImageLoadErrorColor = "transparent";
	  
		var maxBounds	= new OpenLayers.Bounds(-20037508.34, -20037508.34, 20037508.34, 20037508.34);
		var tileSize	= new OpenLayers.Size(256, 256);
		
		var options = {
			maxExtent: maxBounds,			
			restrictedExtent: maxBounds,
			titleSize: tileSize,
			numZoomLevels: 19,
			maxResolution: 156543.0339,		
			controls: [],	
			projection: 'EPSG:900913',
			displayProjection: 'EPSG:4326',
			units: 'm'
		};
		
		map = new OpenLayers.Map('map', options);
		
		// vworld 일반
		vBaseMap 		= new vworld.Layers.Base('VBASE');		
		map.addLayer(vBaseMap);
		
		vGrayMap 	= new vworld.Layers.Gray('VGRAY'); 	
		map.addLayer(vGrayMap);
		
		// vBaseMap 	= new vworld.Layers.Midnight('VMIDNIGHT');  // Midnight지도
		
		vHybridMap 		= new vworld.Layers.Hybrid('VHYBRID'); 		// 하이브리드지도
		map.addLayer(vHybridMap);
		
		var hybrid_tlayer = map.getLayersByName("VHYBRID");
		hybrid_tlayer[0].setVisibility(false);
		
		// ITS 실시간 교통정보
		var itsLayer = new OpenLayers.Layer.TMS("ITSTrafficInfo", "http://example.com/", {
							'type': 'PNG',
							'getURL': callback_ITSTrafficInfo_URL
						});
		itsLayer.isBaseLayer = false;
		itsLayer.setVisibility(false);
		map.addLayer(itsLayer);
		
		var its_tlayer = map.getLayersByName("ITSTrafficInfo");
		its_tlayer[0].setVisibility(false);

		vSatelliteMap	= new vworld.Layers.Satellite('VSAT');	
 		map.addLayer(vSatelliteMap);	
 		
		gBaseMap = new OpenLayers.Layer.Google("Google Roadmap", {
			type: google.maps.MapTypeId.ROADMAP
		});
		map.addLayer(gBaseMap);
		
		gSatelliteMap = new OpenLayers.Layer.Google("Google Satellite", {
			type: google.maps.MapTypeId.SATELLITE
		});		
		
		/*
			gSatelliteMap =	new OpenLayers.Layer.Google("Google Physical", {
				type: google.maps.MapTypeId.TERRAIN,
				sphericalMercator: true
			});
		*/		
		
		map.addLayer(gSatelliteMap);
		
		openStreetMap = new OpenLayers.Layer.OSM('Simple OSM Map', null);
	 	map.addLayer(openStreetMap);
	 	
	 	openStreetGrayMap = new OpenLayers.Layer.OSM('Simple OSM Map', null, {
            eventListeners: {
                tileloaded: function(evt) 
                {
                    var ctx = evt.tile.getCanvasContext();
                    if (ctx) 
                    {
                        var imgd = ctx.getImageData(0, 0, evt.tile.size.w, evt.tile.size.h);
                        var pix = imgd.data;
                        for (var i = 0, n = pix.length; i < n; i += 4)
                        {
                            pix[i] = pix[i + 1] = pix[i + 2] = (3 * pix[i] + 4 * pix[i + 1] + pix[i + 2]) / 8;
                        }
                        ctx.putImageData(imgd, 0, 0);
                        evt.tile.imgDiv.removeAttribute("crossorigin");
                        evt.tile.imgDiv.src = ctx.canvas.toDataURL();
                    }
                }
            }
        });
	 	map.addLayer(openStreetGrayMap);
	 	 	        
		map.addControl(new OpenLayers.Control.PanZoomBar());								
		map.addControl(new OpenLayers.Control.Navigation());
		var mapBounds = new OpenLayers.Bounds(123.75, 32.063956, 131.000977, 44.402392);
		
		map.zoomToExtent(mapBounds.transform(map.displayProjection, map.projection)); 	
		map.setCenter(new OpenLayers.LonLat(14243425.793355, 4342305.8698004), 8);
		
		/*	
		 map.events.register('move', map, function (e) {
  	       	 $('#map').addClass('animated-grayscale');
  	     });
  	    */
  		setWMSGetFeatureInfo();
  		
  		// 검색 결과용 Marker를 생성
  		searchMarkers = new OpenLayers.Layer.Markers("SearchResultMarkers");   		
  		searchMarkers.setZIndex(10001); 
  	    map.addLayer(searchMarkers);
  }

/********************************************************************************
 * 기      능   : BaseLayer 선택
 * @param obj	:
 * @return  	:
 ********************************************************************************/
 var changeMapBaseLayer = function (baselayerName){
	 
 	switch (baselayerName)
 	{
 		// vworld 일반
 		case "vBaseMap"		 : 
		if(vBaseMap == null){
			vBaseMap 		= new vworld.Layers.Base('VBASE');		
			map.addLayer(vBaseMap);
		}
		map.setBaseLayer(vBaseMap);
 		break;	
 		
 		// VGRAY
 		
 		// vworld 그레이
 		case "vGrayMap"		 : 
		if(vGrayMap == null){
			vGrayMap 		= new vworld.Layers.Base('VGRAY');		
			map.addLayer(vGrayMap);
		}
		map.setBaseLayer(vGrayMap);
 		break;	
 	
 		
 		// vworld 위성
 		case "vSatelliteMap" : 
		if(vSatelliteMap == null){
			vSatelliteMap	= new vworld.Layers.Satellite('VSAT');	
			map.addLayer(vSatelliteMap);	
		}
		map.setBaseLayer(vSatelliteMap);
 		break;	
 		
 		// Google 일반
 		case "gBaseMap"		 : 
		if(gBaseMap == null){
 			gBaseMap = new OpenLayers.Layer.Google("Google Roadmap", {
 				type: google.maps.MapTypeId.ROADMAP,
 				sphericalMercator: true
 			});
 			map.addLayer(gBaseMap);
		}
		map.setBaseLayer(gBaseMap);	
 		break;	
 		
 		// Google 위성 
 		case "gSatelliteMap" : 
		if(gSatelliteMap == null){
 			gSatelliteMap = new OpenLayers.Layer.Google("Google Satellite", {
 				type: google.maps.MapTypeId.SATELLITE,
 				sphericalMercator: true
 			});		
 			map.addLayer(gSatelliteMap);
		}
		map.setBaseLayer(gSatelliteMap);	
 		break;
 		
 		// OpenStreetMap	
 		case "openStreetMap" : 
		if(openStreetMap == null){
 			openStreetMap = new OpenLayers.Layer.OSM('Simple OSM Map', null);
 	        map.addLayer(openStreetMap);
		}
        map.setBaseLayer(openStreetMap);	
 		break;	
 		
 		// OpenStreetMap	
 		case "openStreetGrayMap" : 
		if(openStreetGrayMap == null){
			openStreetGrayMap = new OpenLayers.Layer.OSM('Simple OSM Map', null);
 	        map.addLayer(openStreetGrayMap);
		}
        map.setBaseLayer(openStreetGrayMap);	
 		break;	
 	}
 }

 // 지도상으로 이동 
 var moveToOnMap = function(lon, lat, zoomlevel) {
 	var lonlat = new OpenLayers.LonLat(lon, lat);
 	lonlat.transform(map.displayProjection, map.baseLayer.projection);
 	map.setCenter(lonlat, zoomlevel);
 }
 
 
// ITS에서 WMS 데이터를 받아오기 위한 콜백 함수
var callback_ITSTrafficInfo_URL = function(bounds) {
	 var res = this.map.getResolution();
     var x = Math.round ((bounds.left - this.maxExtent.left) / (res * this.tileSize.w));
     var y = Math.round ((this.maxExtent.top - bounds.top) / (res * this.tileSize.h));
     var z = this.map.getZoom();
 	 var path = "http://openapi.its.go.kr/api/wmtsTile?key=1415602655312&zoom="+z+"&row="+y+"&col="+x;
 	 return path;
}

var createZone = function(zone_layername) {
	var myStyle = {fill: true,fillColor: "#ff0000"};
	//var vector_layer = new OpenLayers.Layer.Vector('CODLayer', {style: myStyle});
	var tlayer = new OpenLayers.Layer.WMS(
		zone_layername,
		'http://192.168.40.4:9090/geoserver/congestion/wms',
		{
			layers : zone_layername,
			isBaseLayer : false,		// 해당 옵션을 주어야 오버레이가능
			transparent : true,
			Tiling:"Tiled",
			style: myStyle
		}
	);
	// tlayer.styleMap = infoStyleMap;
	// map.events.register('click', tlayer, onClicks);
	tlayer.setZIndex(10000); 
	map.addLayer(tlayer);
    
	return tlayer;
}
/*
function onClicks(e) {
    OpenLayers.Request.GET({
        url: "http://192.168.40.4:9090/geoserver/congestion/wms",
        async: true,
        params: {
	REQUEST: "GetFeatureInfo",
	XCEPTIONS: "XML",
	BBOX: map.getExtent().toBBOX(),
	X: e.xy.x,
	Y: e.xy.y,
	INFO_FORMAT: 'application/vnd.ogc.gml',
 	LAYERS : 'zone_sido_netcon_wd',		          
 	QUERY_LAYERS: 'zone_sido_netcon_wd',
 	WIDTH: gCurrentZoneLayer_sido.map.size.w,
	HEIGHT: gCurrentZoneLayer_sido.map.size.h	        
        }, 
        success: function(response) {		
        	//alert("!!");
        	setFeatures(response);
        }, 
        failure: function() {
        	alert("실패");
        }
    });	

    OpenLayers.Event.stop(e);
}

function setFeatures(response) {
	console.log(response);
    g =  new OpenLayers.Format.GML();
    features = g.read(response.responseText);
    //displayInfo(features);
   console.log(features);
}

function displayInfo(features) {
         for(var feat in features) {
        	 console.log(feat);
         }
    	
}

*/
// 행정구역 레이어 생성
var setZoneVisibility = function() {
	
	// 행정구역 선택 체크 여부 확인 
	var is_zone_sido 	= document.getElementById('id_zone_sido').checked;
	var is_zone_sigungu = document.getElementById('id_zone_sigungu').checked;
	var is_zone_emd 	= document.getElementById('id_zone_emd').checked;
	
	// 행정구역 선택 값 확인
	var id_sido_value 	= document.getElementById('id_zone_sido').value;
	var id_sigungu_value= document.getElementById('id_zone_sigungu').value;
	var id_emd_value 	= document.getElementById('id_zone_emd').value;

	var periodValue = $(':radio[name="period"]:checked').val();
	
	// 지표에 해당하는 값 확인
	var indexValue = document.getElementById("index").value;

	var tlayername_sido 	= id_sido_value + "_" + indexValue + "_" + periodValue;
	var tlayername_sigungu 	= id_sigungu_value + "_" + indexValue + "_" + periodValue;
	var tlayername_emd 		= id_emd_value + "_" + indexValue + "_" + periodValue;

	if (is_zone_sido == true) {
		if (isLayerCreated(tlayername_sido) == false) gCurrentZoneLayer_sido = createZone(tlayername_sido);
	}else{
		deleteLayer(tlayername_sido);
	}

	if (is_zone_sigungu == true){
		if (isLayerCreated(tlayername_sigungu) == false) gCurrentZoneLayer_sigungu = createZone(tlayername_sigungu);
	}else{
		deleteLayer(tlayername_sigungu);
	}

	if (is_zone_emd == true){
		if (isLayerCreated(tlayername_emd) == false) gCurrentZoneLayer_emd = createZone(tlayername_emd);
	}else{
		deleteLayer(tlayername_emd);
	}
}

// 도로등급 레이어 생성
var setLinkLayerVisibility = function(){
	
	deleteLayer(LINK_LAYER);
	
	var is_idctr_101 = document.getElementById('idctr_101').checked;
	var is_idctr_102 = document.getElementById('idctr_102').checked;
	var is_idctr_103 = document.getElementById('idctr_103').checked;
	var is_idctr_104 = document.getElementById('idctr_104').checked;
	var is_idctr_105 = document.getElementById('idctr_105').checked;
	var is_idctr_106 = document.getElementById('idctr_106').checked;
	var is_idctr_107 = document.getElementById('idctr_107').checked;	

	// 선택한 것이 아무것도 없을 경우
	if (!is_idctr_101 && !is_idctr_102 && 
		!is_idctr_103 && !is_idctr_104 &&
		!is_idctr_105 && !is_idctr_106 && !is_idctr_107) {return;}

	// 필터 문자열 생성
	var cqlfilter = "road_rank in (";
	if(is_idctr_101 == true)	cqlfilter += "101,";
	if(is_idctr_102 == true)	cqlfilter += "102,";
	if(is_idctr_103 == true)	cqlfilter += "103,";
	if(is_idctr_104 == true)	cqlfilter += "104,";
	if(is_idctr_105 == true)	cqlfilter += "105,";
	if(is_idctr_106 == true)	cqlfilter += "106,";
	if(is_idctr_107 == true)	cqlfilter += "107,";
	
	// 마지막 ','를 ')'로 바꾼다
	var tfilter = cqlfilter.substring(0, cqlfilter.length-1);
	tfilter += ")";
	cqlfilter = tfilter;
	
	var indexValue = document.getElementById("index").value;
	var periodValue = $(':radio[name="period"]:checked').val();
	var tparamlayers = "klink_" + indexValue + "_" + periodValue;
	var tlayer = new OpenLayers.Layer.WMS(
		LINK_LAYER,
		'http://192.168.40.4:9090/geoserver/congestion/wms',
		{
			layers : tparamlayers,
			isBaseLayer : false,		// 해당 옵션을 주어야 오버레이가능
			CQL_FILTER: cqlfilter,		// 필터 적용
			transparent : true
		}
	);
	map.addLayer(tlayer);
}

// 지정 레이어 삭제
var deleteLayer = function(layername) {
	for (var i = 0; i < map.layers.length; i++) {
		var layer = map.layers[i];
		
        if (layer.name == layername) {
        	map.removeLayer(layer);
        	return;
        }
    }
}

var isLayerCreated = function(zone_layerName) {
	var bReturn = false;
	for (var i = 0; i < map.layers.length; i++) {
		var layer = map.layers[i];
        if (layer.name == zone_layerName) {
        	bReturn = true;
        }
    }
	return bReturn;
}

var deleteZoneLayers = function() {
	if (gCurrentZoneLayer_sido)		map.removeLayer(gCurrentZoneLayer_sido);
	if (gCurrentZoneLayer_sigungu)	map.removeLayer(gCurrentZoneLayer_sigungu);
	if (gCurrentZoneLayer_emd)		map.removeLayer(gCurrentZoneLayer_emd);
}

var setDisabled = function(check){
	if(check ==""){
		initSearchCondition();
	}else{
		$( "input[name='zone']" ).attr("disabled",false);
		$( "input[name='road_level']" ).attr("disabled",false);
	}
}

// 초기화
var initSearchCondition = function() {
	
	// 기간별 초기화
	$('input:radio[name="period"][value="wd"]').prop('checked', true);
	
	// 지표별 초기화
	$('#index').find('option:first').attr('selected', 'selected'); 
	
	var checkBoxZone = document.getElementsByName("zone");
	for (var i = 0 ; i < checkBoxZone.length ; i++) {
		checkBoxZone[i].checked = false;
	}
	
	var checkBoxRoadLevel = document.getElementsByName("road_level");
	for (var i = 0 ; i < checkBoxRoadLevel.length ; i++) {
		checkBoxRoadLevel[i].checked = false;
	}
	
	// popupClear();

	deleteZoneLayers();
	deleteLayer(LINK_LAYER);
	
	$( "input[name='zone']" ).attr("disabled",true);
	$( "input[name='road_level']" ).attr("disabled",true);
	
}

//레이어 클릭시 정보를 보여주기 위한 처리
var setWMSGetFeatureInfo = function()
{
	info = new OpenLayers.Control.WMSGetFeatureInfo({
        url: 'http://192.168.40.4:9090/geoserver/congestion/wms', 
        title: 'FeatureInfoByClicking',
        queryVisible: true,
        eventListeners: {
            getfeatureinfo: function(event) 
            {
            	//console.log(event);
	            	var msg = event.text;
	            	if (msg.length <= 0) {
	            		msg = "System could not get WMS feature information!"
	            	}
	                map.addPopup(new OpenLayers.Popup.FramedCloud("defaultFeatureInfo", map.getLonLatFromPixel(event.xy), null, event.text, null, true));
            }
        }
    });
	
	/*
	info = new OpenLayers.Control.WMSGetFeatureInfo({
        url: 'http://192.168.40.4:9090/geoserver/congestion/wms', 
        title: 'FeatureInfoByClicking',
        queryVisible: true,
        infoFormat:'application/json',
        format: new OpenLayers.Format.JSON,
        eventListeners: {
            getfeatureinfo: function(event) 
            {
            	// console.log(event);
	            var msg = eval("("+event.text+")");
	            // console.log(msg);
	            console.log(msg.features[0].properties.ID);
	            // 	if (msg.length <= 0) {
	            // 		msg = "System could not get WMS feature information!"
	            //	}
	            //   map.addPopup(new OpenLayers.Popup.FramedCloud("defaultFeatureInfo", map.getLonLatFromPixel(event.xy), null, event.text, null, true));
            }
        }
    });
	*/
	
    map.addControl(info);
    info.activate();
}

var showMarkerOnSearchResultLayer = function(a_link, lon, lat) {
	var name = $('#' + 'juso_' + a_link).text(); 
	moveToOnMap(lon, lat, 17);
	searchMarkers.clearMarkers();
	var location 		= new OpenLayers.LonLat(lon, lat);
	location.transform(map.displayProjection, map.baseLayer.projection);
	var size		= new OpenLayers.Size(24, 37);
    var offset 		= new OpenLayers.Pixel(-(size.w/2), -size.h);
    var icon 		= new OpenLayers.Icon('http://127.0.0.1:9999/qbic/images/map/marker/marker.png', size, offset);
    var marker 		= new OpenLayers.Marker(location, icon.clone());
    // marker.setOpacity(0.2);
    // marker.events.register('mousedown', marker, function(evt) { alert(name + "\n[" + location.lon + ", " + location.lat + "]"); OpenLayers.Event.stop(evt); });
    // 참조: http://stackoverflow.com/questions/8523446/openlayers-simple-mouseover-on-marker
    var popup		= null;
    marker.events.register('mouseover', marker, function(evt) {
    	var msg		= "<div>" + name + "</div><div>[" + location.lon + ", " + location.lat + "]" + "</div>";
        popup 		= new OpenLayers.Popup.FramedCloud("Popup", location, null, msg, null, false);        
        map.addPopup(popup);
    });
    marker.events.register('mouseout', marker, function(evt) {
    	popup.hide();
    });    
    searchMarkers.addMarker(marker);
}

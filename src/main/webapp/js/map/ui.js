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
		 MapLayoutHandle.menuSelection();
		 MapLayoutHandle.leftMenuTabSelection();
		 MapLayoutHandle.mapResize();
		 BaseMapSelection.vworldBaseMap();
		 BaseMapSelection.vworldGrayMap();
		 BaseMapSelection.vworldSatelliteMap();
		 BaseMapSelection.googleBaseMap();
		 BaseMapSelection.googleSatelliteMap();
		 BaseMapSelection.openStreetMap();
		 BaseMapSelection.openStreetGrayMap();
		 MapAdditionalInfoSelection.vworldHybridMapSelection();
		 MapAdditionalInfoSelection.realTrafficInfo();
		 MapAdditionalInfoSelection.mutilMapInfo();
	 });

/********************************************************************************
 * 기      능   :  지도 tab_menu 선택
 * @param obj	:
 * @return  	:
 ********************************************************************************/
	 var MapLayoutHandle = function(){}; 
	 
	 MapLayoutHandle.leftMenuTabSelection = function(){
		var tabArray = [];
		$('#left_tab > li').children('a').each(function(index) {
		    tabArray.push($(this).attr('href'));
		});
		
		$('#left_tab li').click(function() {
  			if($(this).hasClass('active') == false){
				$('#left_tab li').removeClass('active');
				$(this).addClass('active');
				var selectedId = $(this).children('a').attr('href');
				
				for (var i = 0; i < tabArray.length; i++) {
					if(tabArray[i] == selectedId) {
						$(selectedId).show();
					}else{
						$(tabArray[i]).hide();
					}
				}
			}
  			
			return false;
		});
 	};
 	
/********************************************************************************
  * 기      능   :  지도 menu 선택
  * @param obj	:
  * @return  	:
  ********************************************************************************/
 	MapLayoutHandle.menuSelection = function(){
		var menu = $("#menu").val();
		if(menu=="congestionIndex"){
			$("#congestionIndex").addClass("menu_selected");
		}else if(menu=="congestionCost"){
			$("#congestionCost").addClass("menu_selected");
		}else if(menu=="trafficVolume"){
			$("#trafficVolume").addClass("menu_selected");
		}else if(menu=="demarcationSpeed"){
			$("#demarcationSpeed").addClass("menu_selected");
		}else if(menu=="map_info"){
			$("#map_info").addClass("menu_selected");
		}else if(menu=="congestion_info"){
			$("#congestion_info").addClass("menu_selected");
		}else if(menu=="faq_info"){
			$("#faq_info").addClass("menu_selected");
		}
 	};
	 
/********************************************************************************
 * 기      능   :  지도영역확장
 * @param obj	:
 * @return  	:
 ********************************************************************************/
 	MapLayoutHandle.mapResize = function(){
		var mapResizeFlag = 1;
		$('.map_bar button').click(function() {
			if(mapResizeFlag == 1){
				$('#map_container').removeClass('congest_map');
				$('.map_left').hide();
				
				//$("#mutil_map").css("width", $("#mutil_map").width()+ 320);
				
				$('#map_container').addClass('congest_map_distention');
				$(".map_bar button img").attr("src", baseUrl + "/images/map/btn/map_bar_btn02.gif" );
				mapResizeFlag = 0;
			}else{
				$('#map_container').addClass('congest_map');
				$('.map_left').show('slow');
				
				//$("#mutil_map").css("width", $("#mutil_map").width()-320);
				
				$('#map_container').removeClass('congest_map_distention');
				$(".map_bar button img").attr("src", baseUrl + "/images/map/btn/map_bar_btn01.gif" );
				mapResizeFlag = 1;
				
				//console.log("스케일   : " + map.getScale());
				//console.log("프로젝션 : " + map.getProjection());
				//console.log("줌레벨 : " + map.getZoom());
				/*	
				    console.log("레이어1의 바운드 :" + map.layers[0].mapBounds);
					console.log("레이어2의 바운드 :" + map.layers[1].mapBounds);
					console.log("레이어3의 바운드 :" + map.layers[2].mapBounds);
					console.log("레이어4의 바운드 :" + map.layers[3].mapBounds);
					console.log("레이어4의 바운드 :" + map.layers[4].mapBounds);
					console.log("레이어5의 바운드 :" + map.layers[5].mapBounds);
					console.log("레이어6의 바운드 :" + map.layers[6].mapBounds);
					console.log("레이어7의 바운드 :" + map.layers[7].mapBounds);
				*/
				//console.log("calculateBounds() 호출 :" + calculateBounds());
				//console.log(calculateBounds());
				
				
				//console.log("div map의 폭 : " +  $("#map").width());
			 	//console.log("div map의 높이 : " +  $("#map").height());
			 	
			 	/*
			    var spherical_mercator = new OpenLayers.Projection("EPSG:900913");
		        console.log("spherical_mercator :" + spherical_mercator);
			    var wgs84 = new OpenLayers.Projection("EPSG:4326");
		        console.log("wgs84 : " + wgs84)
		        var map = feature.layer.map;
		        console.log("map : " + map);
		        */
			 	
			   // console.log(map.getCenter());
			 	
		       // var geometry = map.getCenter();
		        
		       /* console.log("geometry : " + geometry);
		        var center_lonlat = new OpenLayers.LonLat(geometry.lat, geometry.lon);
		        console.log("center_lonlat : " + center_lonlat);
		        var center_px = map.getViewPortPxFromLonLat(center_lonlat);
		        console.log("center_px :" + center_px);
		        var radius_m = parseFloat(feature.attributes["radius"]);
		        console.log("radius_m : " + radius_m);
		        var radius_lonlat = OpenLayers.Util.destinationVincenty(center_lonlat.clone().transform(spherical_mercator, wgs84), 0, radius_m).transform(wgs84, spherical_mercator);
		        console.log("radius_lonlat : " + radius_lonlat);
		        var radius_px = map.getViewPortPxFromLonLat(radius_lonlat);
		        console.log("radius_px : " + radius_px);
		        var radius = Math.abs(radius_px.y - center_px.y);
		        console.log("radius : " + radius);*/
		        
		       // map.setCenter(new OpenLayers.LonLat(geometry.lon, geometry.lat), map.getZoom());
			}
		});
	 };
	 
 /********************************************************************************
  * 기      능   :  지도 BaseMap 선택
  * @param obj	:
  * @return  	:
  ********************************************************************************/
	var BaseMapSelection = function(){};
	 
	BaseMapSelection.vworldBaseMap = function(){
		$('#vworld_base_map').click(function() {
			if($(this).hasClass('active') == true){
				console.log("이미 호출되었음!")
			}else{
				$("#base_map_select").children().removeClass('active');
				$(this).toggleClass('active');
				changeMapBaseLayer("vBaseMap");
			}
		});
	};
	
	BaseMapSelection.vworldGrayMap = function(){
		$('#vworld_gray_map').click(function() {
			if($(this).hasClass('active') == true){
				console.log("이미 호출되었음!")
			}else{
				$("#base_map_select").children().removeClass('active');
				$(this).toggleClass('active');
				changeMapBaseLayer("vGrayMap");
			}
		});
	};
	
 	
	BaseMapSelection.vworldSatelliteMap = function(){
 		$('#vworld_satellite_map').click(function() {
			if($(this).hasClass('active') == true){
				console.log("이미 호출되었음!")
			}else{
				$("#base_map_select").children().removeClass('active');
				$(this).toggleClass('active');
				changeMapBaseLayer("vSatelliteMap");
			}
 		});
 	};	
 	
 	BaseMapSelection.googleBaseMap = function(){
		$('#google_base_map').click(function() {
			if($(this).hasClass('active') == true){
				console.log("이미 호출되었음!")
			}else{
				$("#base_map_select").children().removeClass('active');
				$(this).toggleClass('active');
				changeMapBaseLayer("gBaseMap");
			}
		});
	};
 	
	BaseMapSelection.googleSatelliteMap = function(){
 		$('#google_satellite_map').click(function() {
			if($(this).hasClass('active') == true){
				console.log("이미 호출되었음!")
			}else{
				$("#base_map_select").children().removeClass('active');
				$(this).toggleClass('active');
				changeMapBaseLayer("gSatelliteMap");
			}
		});
 	};
 	
 	BaseMapSelection.openStreetMap = function(){
		$('#openstreet_base_map').click(function() {
			if($(this).hasClass('active') == true){
				console.log("이미 호출되었음!")
			}else{
				$("#base_map_select").children().removeClass('active');
				$(this).toggleClass('active');
				changeMapBaseLayer("openStreetMap");
			}
		});
	};
	
 	BaseMapSelection.openStreetGrayMap = function(){
		$('#openstreet_gray_map').click(function() {
			if($(this).hasClass('active') == true){
				console.log("이미 호출되었음!")
			}else{
				$("#base_map_select").children().removeClass('active');
				$(this).toggleClass('active');
				changeMapBaseLayer("openStreetGrayMap");
			}
		});
	};
	
	
	 
 /********************************************************************************
  * 기      능   :  지도 부가기능 선택
  * @param obj	:
  * @return  	:
  ********************************************************************************/
 	var MapAdditionalInfoSelection = function() {};
 	
 	MapAdditionalInfoSelection.vworldHybridMapSelection = function(){
 		$('#vworld_hybrid_map').click(function() {
			$(this).toggleClass('active');
			if($(this).hasClass('active') == true){
				var tlayer = map.getLayersByName("VHYBRID");
				tlayer[0].setVisibility(true);
			}else{
				var tlayer = map.getLayersByName("VHYBRID");
				tlayer[0].setVisibility(false);
			}
 		});
 	};
 	
 	MapAdditionalInfoSelection.realTrafficInfo = function(){
 		$('#real_traffic_info').click(function() {
			$(this).toggleClass('active');
			
			if($(this).hasClass('active') == true){
				var tlayer = map.getLayersByName("ITSTrafficInfo");
				tlayer[0].setVisibility(true);
			}else{
				var tlayer = map.getLayersByName("ITSTrafficInfo");
				tlayer[0].setVisibility(false);
			}
 		});
 	};
 	
 	MapAdditionalInfoSelection.mutilMapInfo = function(){
 		$('#mutil_map_info').click(function() {
 			$(this).toggleClass('active');
 			
 			if($(this).hasClass('active') == true){
 				// $('.map_bar button').trigger('click');
	 			$('#mutil_map_container').css({'width':'100%','height':'100%', 'background-color':"#fff"});
	 			
	 			//일단 1초후 80% 불투명도 적용.
	 			$('#mutil_map_container').fadeIn(1000);      
	 			//$('#mutil_map_container').fadeTo("slow",0.9);    
	 			
	 			var mapBounds = new OpenLayers.Bounds(123.75, 32.063956, 131.000977, 44.402392);
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
	 			
	            var map_1, map_2, layer_1, layer_2;

	            map_1 =  new OpenLayers.Map('map_1', options);
	            map_2 =  new OpenLayers.Map('map_2', options);
	           /* layer_1 = new OpenLayers.Layer.WMS( "OpenLayers WMS",
                        "http://vmap0.tiles.osgeo.org/wms/vmap0", {layers: 'basic'} ); */
	            
	            layer_1 = new vworld.Layers.Base('VBASE');	
	            layer_2 = new vworld.Layers.Base('VBASE');
	            
	            // console.log(layer_1 == layer_2);
	            
                //var control = new OpenLayers.Control();
/*                OpenLayers.Util.extend(control, {
                    draw: function () {
                        // this Handler.Box will intercept the shift-mousedown
                        // before Control.MouseDefault gets to see it
                        this.box = new OpenLayers.Handler.Box( control,
                            {"done": this.notice},
                            {keyMask: OpenLayers.Handler.MOD_SHIFT});
                        this.box.activate();
                    },

                    notice: function (bounds) {
                        var ll = map_1.getLonLatFromPixel(new OpenLayers.Pixel(bounds.left, bounds.bottom)); 
                        var ur = map_1.getLonLatFromPixel(new OpenLayers.Pixel(bounds.right, bounds.top)); 
                        alert(ll.lon.toFixed(4) + ", " + 
                              ll.lat.toFixed(4) + ", " + 
                              ur.lon.toFixed(4) + ", " + 
                              ur.lat.toFixed(4));
                    }
                });*/

                map_1.addLayer(layer_1);
                map_2.addLayer(layer_2);
                // map_1.addControl(control);
            	// map_1.addControl(new OpenLayers.Control.PanZoomBar());	
                
                var control_1 = new OpenLayers.Control.Navigation();
                var control_2 = new OpenLayers.Control.Navigation();
                  
        		map_1.addControl(control_1);
        		map_2.addControl(control_2);
            	map_1.zoomToExtent(mapBounds.transform(map_1.displayProjection, map_1.projection)); 	
            	map_2.zoomToExtent(mapBounds.transform(map_2.displayProjection, map_2.projection));
            	
            	map_1.setCenter(new OpenLayers.LonLat(14243425.793355, 4342305.8698004), 8);
            	map_2.setCenter(new OpenLayers.LonLat(14243425.793355, 4342305.8698004), 8);
                /******************************************************************/
                
	    //    map_2 = new OpenLayers.Map('map_2', options);
	        
            /*    layer_2 = new OpenLayers.Layer.WMS( "OpenLayers WMS",
                        "http://vmap0.tiles.osgeo.org/wms/vmap0", {layers: 'basic'} ); */
	        
	     //   layer_2 = new vworld.Layers.Base('VBASE');	
	    //    var mapBounds_2 = new OpenLayers.Bounds(123.75, 32.063956, 131.000977, 44.402392);
	        //var control_1 = new OpenLayers.Control();
/*                
                
                OpenLayers.Util.extend(control_1, {
                    draw: function () {
                        // this Handler.Box will intercept the shift-mousedown
                        // before Control.MouseDefault gets to see it
                        this.box = new OpenLayers.Handler.Box( control_1,
                            {"done": this.notice},
                            {keyMask: OpenLayers.Handler.MOD_SHIFT});
                        this.box.activate();
                    },

                    notice: function (bounds) {
                        var ll = map_2.getLonLatFromPixel(new OpenLayers.Pixel(bounds.left, bounds.bottom)); 
                        var ur = map_2.getLonLatFromPixel(new OpenLayers.Pixel(bounds.right, bounds.top)); 
                        alert(ll.lon.toFixed(4) + ", " + 
                              ll.lat.toFixed(4) + ", " + 
                              ur.lon.toFixed(4) + ", " + 
                              ur.lat.toFixed(4));
                    }
                });*/
                
            //    map_2.addLayer(layer_2);
                //map_2.addControl(control_1);
            	//map_2.addControl(new OpenLayers.Control.PanZoomBar());	
                
          //      var controller_2 = new OpenLayers.Control.Navigation();
                
        //		map_2.addControl(controller_2);
                
        	//	map_2.zoomToExtent(mapBounds_2.transform(map_2.displayProjection, map_2.projection)); 	
        //		map_2.setCenter(new OpenLayers.LonLat(14243425.793355, 4342305.8698004), 8);

                
                /******************************************************************/

            	$( "#map_1" ).mouseover(function() {
            		map_location = "map_1";
            		console.log("mouseover_map_1");
            	});
            	
            	$( "#map_2" ).mouseover(function() {
            		map_location = "map_2";
            		console.log("mouseover_map_2");
            	});
            	
          map_1.events.register('move', map_1, function (e) {
        	  if(map_location=="map_1"){
        		  var zoom = map_1.getZoom();
        		  var geometry = map_1.getCenter();
        		  map_2.setCenter(new OpenLayers.LonLat(geometry.lon, geometry.lat), zoom);
        		  console.log("map_1");
        	  }
          });
                
          // var zomm = map_1.getZoom();
          // var geometry = map_1.getCenter();
          // map_2.setCenter(new OpenLayers.LonLat(geometry.lon, geometry.lat), map_1.getZoom());
          map_2.events.register('move', map_2, function (e) {
        	  if(map_location=="map_2"){
     				var zoom = map_2.getZoom();
     				var geometry = map_2.getCenter();
     				map_1.setCenter(new OpenLayers.LonLat(geometry.lon, geometry.lat), zoom);
     			}
        		  console.log(" map_2.events = map_2");
          });
                
        /*        map_1.events.register('movestart', map, function (e) {
                	console.log("movestart");
                });

                map_1.events.register('moveend', map, function (e) {
                	console.log("moveend");
                });*/
                
                
                
 			}else{
 				$('#mutil_map_container').hide();
 			}
 		});
 	};
 	
	/********************************************************************************
	* 기      능   	: 
	* @param obj 	: 
	* @return  		: 
	********************************************************************************/
 	var moveMapToLandCode = function(){
		var lawd_cd = null; 
 		var sido_cd = $("#sido_cd").val();
 		var sgg_cd 	= $("#sgg_cd").val();
 		var umd_cd 	= $("#umd_cd").val();
 		
 		if(sido_cd == "") {
 			alert("광역시/도를 선택해주세요");
 			$("#sido_cd").focus();
 			return false;
 		}
 		
 		if(sgg_cd == ""){
 			lawd_cd = sido_cd + "00000000";
 		}else{
 			if(umd_cd == ""){
 				lawd_cd = sido_cd + sgg_cd + "00000";
 			}else{
 				lawd_cd = sido_cd + sgg_cd + umd_cd + "00";
 			}
 		}
 		
		var pointData = requestJsonData(baseUrl+'/map/landcode.do',{'code' : 'lawd_cd', 'lawd_cd' : lawd_cd}, 'get');
		
 		moveToOnMap(pointData.returnList[0].lon, pointData.returnList[0].lat, pointData.returnList[0].zoomLevel);
		return false;
 	};

	/********************************************************************************
	* 기      능   	: 
	* @param obj 	: 
	* @return  		: 
	********************************************************************************/
 	var locationSearch = function (page_num) {
 		
 		if(page_num == null){
 			page_num = 1;
 		}
 		
 		var location_search_value = $("#location_search_value").val();
 		if (!location_search_value) {
 			alert("위치 검색어를 입력해 주세요!");
 			return;
 		}	
 		var params = {"callback":"callbackFunc", "category":"Juso", "pageUnit":"10", "output":"json", "apiKey":"EF4CC2CA-899C-3898-9130-6E777D006C76"}	
 		params.q = location_search_value;	
 		params.pageIndex = page_num;
 		
 		var parameter = $.param(params);
 		var proxy_url = "http://map.vworld.kr/search.do?" + parameter;
 		$.ajax({
	 			url: proxy_url,
	 			type: "get", 
	 			dataType: "jsonp",
	 			jsonp : "callback"
  			}
 		);
 	}
 	
	/********************************************************************************
	* 기      능   	: 
	* @param obj 	: 
	* @return  		: 
	********************************************************************************/
 	var callbackFunc = function(data){
 		searchListPaging(data);
 	}
	
	/********************************************************************************
	* 기      능   	: 
	* @param obj 	: 
	* @return  		: 
	********************************************************************************/
 	var searchListPaging = function(data){
 		var page_count = data.Juso;
		$('#searchCount').text(thouSandComma(page_count));
 		$('.search_list').empty();
 		$('#paging > .list').empty();
 		if(page_count == 0){
 			$('.search_list').html('<li style="margin:5px; font-weight:bold;">검색 결과가 없습니다.</li>');
 			$("#paging > .list").html('<a href="#" class="page active">1</a>');
 		}else{
 			// console.log(data);
 			
 			var juso = null;
 			
 			$.each(data.LIST, function(index,list){
 				juso = "<li class='juso'>";
 				juso += "<a href='#' id='juso_" + index +"'"; 
 				juso += "onclick='javascript:showMarkerOnSearchResultLayer("+ index + ","  + list.xpos + "," + list.ypos + ");'>" + list.JUSO + "</a>";
 				juso += "</li>";
 				$('.search_list').append(juso);
 			});
 			
 			var linePerPage = 10;
 			var currentPage = data.paginationInfo.currentPageNo;
 			var tabPerPage 	= 3;
 				
 			var startTab = 0;
 			var endTab 	 = 0;
 			var prevTab  = 0;	// 이전 
 			var nextTab  = 0;	// 다음
 			
 			var totalPage = page_count / linePerPage; // 10 = linePerPage
 			if( (page_count % linePerPage) > 0 )  totalPage = totalPage + 1;
 			totalPage =  (totalPage == 0) ? 1 : totalPage;   
 			
 		    startTab = Math.ceil(((currentPage -1)/ tabPerPage) * tabPerPage + 1);    
 		    endTab = Math.ceil(startTab + tabPerPage -1);                            
 			
 			prevTab = Math.ceil(((currentPage - 1) / tabPerPage) * tabPerPage);
 			nextTab = Math.ceil(((currentPage - 1) / tabPerPage + 1) * tabPerPage + 1);	
 			if(prevTab <= 0) { prevTab = 1; }
 			if(nextTab > totalPage) { nextTab = totalPage; }
 			
 			var prevTabHtml = "";
 			var pagingList = null;
 			
 			prevTabHtml += "<a href='#' class='prev' onclick='javascript:locationSearch(" + prevTab + ");'>" + "이전"  + "</a>";
 			$("#paging > .prev").remove();
 			$("#paging").prepend(prevTabHtml);
 			
 		    for (var i = startTab ; i <= endTab  ; i++) {
 		    	if (i == currentPage) {
 		 			pagingList = "<a href='#' class='page active'"; 
 		 			pagingList += " onclick='javascript:locationSearch(" + i + ");'>" + i;
 		 			pagingList += "</a>";
 		    	} else {
 		 			pagingList = "<a href='#' class='page'"; 
 		 			pagingList += " onclick='javascript:locationSearch(" + i + ");'>" + i;
 		 			pagingList += "</a>";
 		    	}
 		    	$("#paging > .list").append(pagingList);
 		    }
			
 			var nextTabHtml = "";
 			nextTabHtml += "<a href='#' class='next' onclick='javascript:locationSearch(" +  nextTab + ");'>" + "다음"  + "</a>";
 			$("#paging > .next").remove();
 			$("#paging").append(nextTabHtml);
 		}
 	}

	/********************************************************************************
	* 기      능   	: 
	* @param obj 	: 
	* @return  		: 
	********************************************************************************/
 	var searchSggList = function(){
 		var sido_cd = $('#sido_cd').val();
 		$('#sgg_cd').empty().append('<option value="">시/군/구</option>');
 		$('#umd_cd').empty().append('<option value="">읍/면/동</option>');
 		
 		if(sido_cd != ""){
 			 var data = requestJsonData(baseUrl+'/map/landcode.do',{'code' : 'sgg_cd', 'sido_cd' : sido_cd}, 'get');
 			 $.each(data.returnList, function(index,optionData){
	 	        	var options = new Option(optionData.sggNm, optionData.sggCode);
	 	        	$('#sgg_cd').append(options);
	 	     });
 		} 	
 	};
 	
	/********************************************************************************
	* 기      능   	: 
	* @param obj 	: 
	* @return  		: 
	********************************************************************************/
 	var searchEmdList = function(){
 		var sgg_cd = $('#sido_cd').val() + $('#sgg_cd').val();
 		$('#umd_cd').empty().append('<option value="">읍/면/동</option>');
 		
 		if(sgg_cd != ""){
 			 var data = requestJsonData(baseUrl+'/map/landcode.do',{'code' : 'emd_cd', 'sgg_cd' : sgg_cd}, 'get');
 			 $.each(data.returnList, function(index,optionData){
	 	        	var options = new Option(optionData.umdNm, optionData.umdCode);
	 	        	$('#umd_cd').append(options);
	 	     });
 		} 
 	};
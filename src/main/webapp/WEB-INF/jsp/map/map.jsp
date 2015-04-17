<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/inc/taglib.jsp"%>
<script src="http://www.openlayers.org/api/2.13.1/OpenLayers.js" type="text/javascript"></script>
<!-- <script src="http://openlayers.org/en/v3.4.0/build/ol.js" type="text/javascript"></script> -->

<!-- http://192.168.40.4:9090/qbic/map/map.do : 로컬 서버 확인용
	<script src="http://map.vworld.kr/js/apis.do?type=Base&apiKey=5C7C4C72-7300-3F18-8120-BD56F371C076" type="text/javascript"></script> 
-->

<script src="http://map.vworld.kr/js/apis.do?type=Base&apiKey=EF4CC2CA-899C-3898-9130-6E777D006C76" type="text/javascript"></script> 
<!-- http://localhost:9999/qbic/map/map.do : 로컬 개발
-->
<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/map/map_controller.js"></script>
<div id="map_container" class="congest_map">
	<div class="map_bar">	
		<button type="button"><img src="${pageContext.request.contextPath}/images/map/btn/map_bar_btn01.gif" alt="지도 확장하기"></button>
	</div>
	<div class="control_bar">
		<div class="area01">
			<span class="btn-group" id="base_map_select">
			  	<a class="btn btn-mini btn-gray-black active" id="vworld_base_map">브이월드</a>
			 <!-- <a class="btn btn-mini btn-gray-black" id="vworld_gray_map">흑백 (브이월드)</a> -->
			  	<a class="btn btn-mini btn-gray-black" id="vworld_satellite_map">브이월드 - 위성</a>
			  	<a class="btn btn-mini btn-gray-black" id="google_base_map">구글</a>
			  	<a class="btn btn-mini btn-gray-black" id="google_satellite_map">구글 - 위성</a>
			  	<a class="btn btn-mini btn-gray-black" id="openstreet_base_map">오픈스트리트맵</a>
			<!--  <a class="btn btn-mini btn-gray-black" id="openstreet_gray_map">흑백 (오픈스트리트맵)</a> -->
			</span>
			<span style="vertical-align: sub;">&nbsp;|&nbsp;</span>
			<span class="btn-group" id="map_additional_info" style="vertical-align: middle;">
				<input type="checkbox" id="vworld_hybrid_map" name="vworld_hybrid_map">
				<label for="vworld_hybrid_map" class="normal padright10" style="vertical-align: middle;">하이브리드</label>
				<input type="checkbox" id="real_traffic_info" name="road_level">
				<label for="real_traffic_info" class="normal" style="vertical-align: middle;">실시간 소통 정보</label>
				<!--
					<a class="btn btn-mini btn-gray-black" id="vworld_hybrid_map">하이브리드</a>
			  		<a class="btn btn-mini btn-gray-black" id="real_traffic_info">실시간 소통 정보</a> 
			  	-->
			</span>
			<!-- 
				<span style="vertical-align: sub;">&nbsp;|&nbsp;</span>
				<a class="btn btn-mini btn-gray-black" id="mutil_map_info">멀티맵</a> 
			-->
		</div>
		<div class="area02">
			<fieldset>
				<select id="sido_cd" name="sido_cd" class="selbox" style="width:120px" onchange="javascript:searchSggList()">
					<option value="">광역시/도</option>
					<c:forEach var="sidoCodeList" items="${sidoCodeList}" varStatus="status">
						<option value="${sidoCodeList.sidoCode}">${sidoCodeList.sidoNm}</option>
					</c:forEach>
				</select>
				<select id="sgg_cd" name="sgg_cd" class="selbox" style="width:120px" onchange="javascript:searchEmdList()">
					<option value="">시/군/구</option>
				</select>
				<select id="umd_cd" name="umd_cd" class="selbox" style="width:120px">
					<option value="">읍/면/동</option>
				</select>
				<!-- <a class="btn btn-mini btn-gray">이동</a> -->
				<input type="image" src="${pageContext.request.contextPath}/images/map/btn/map_address_move.gif" onclick="javascript:moveMapToLandCode()" alt="이동">
			</fieldset> 
		</div>
	</div>
	<div id="map">
		<%-- 
		<div id="legendInfoBox">
			<img src="${pageContext.request.contextPath}/images/map/btn/legendbar.jpg" width="235px" height="36px" border="0" usemap="#legend">
		</div> 
		--%>
	</div>
	<div id="mutil_map_container">
		<div id="mutil_map_area1">
			<div id="mutil_map_1">
				<div id="map_1">
				</div>
			</div>
		</div>
		<div id="mutil_map_area2">
			<div id="mutil_map_2">
				<div id="map_2">
				</div>
			</div>
		</div> 
	</div>
</div>
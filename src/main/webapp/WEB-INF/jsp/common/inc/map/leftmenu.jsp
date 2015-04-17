<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/inc/taglib.jsp"%>
<div class="map_left">
	<div class="congest_form">
		<ul style="margin-top:5px;" id="left_tab" class="tab tab-top">
			<li class="active mouse_cursor"><a href="#contents">${menu_name}</a></li>
			<li class="mouse_cursor"><a href="#location_search">위 치 검 색</a></li>
		</ul>
		
		<div id="contents">			
			<div class="pad10" style="border-bottom:2px solid #1e65c5;">
				<fieldset>
					<p class="marbottom15">
						<span class="span_label_head">기간별</span>
						<input type="radio" id="weekday" name="period" value="wd" checked="checked">
						<label for="weekday" class="normal">주중&nbsp;</label>
						<input type="radio" id="weekend" name="period" value="we">
						<label for="weekend" class="normal">주말&nbsp;</label>
						<!-- 					
							<input type="radio" id="both" name="period" value="both">
							<label for="both" class="normal">주중/주말</label> 
						-->
					</p>
					<p class="marbottom15">
						<span class="span_label_head">지표별</span>
						<select id="index" class="selbox" style="width:115px" onchange="javascript:setDisabled(this.value);">
							<option value="">선택</option>
							<c:forEach var="indexCodeList" items="${indexCodeList}" varStatus="status">
								<option value="${indexCodeList.codeData}">${indexCodeList.codeName}</option>
							</c:forEach>
						</select>	
					</p>
					<p class="marbottom15">
						<span class="span_label_head">권역별</span>
						<input type="checkbox" id="id_zone_sido" value="zone_sido" name="zone" disabled="disabled" onclick="javascript:setZoneVisibility();">
						<label for="id_zone_sido" class="normal">시도&nbsp;</label>
						<input type="checkbox" id="id_zone_sigungu" value="zone_sigungu" disabled="disabled" name="zone" onclick="javascript:setZoneVisibility();">
						<label for="id_zone_sigungu" class="normal">시군구&nbsp;</label>
						<input type="checkbox" id="id_zone_emd" value="zone_emd" name="zone" disabled="disabled" onclick="javascript:setZoneVisibility();">
						<label for="id_zone_emd" class="normal">읍면동&nbsp;</label>
					</p>
					<p class="marbottom15">
						<span class="span_label_head marbottom07">도로별</span>
						<input type="checkbox" id="idctr_101" name="road_level" value="101" disabled="disabled" onclick="javascript:setLinkLayerVisibility();">
						<label for="idctr_101" class="normal padright10">고속도로</label>
						<input type="checkbox" id="idctr_102" name="road_level" value="102" disabled="disabled" onclick="javascript:setLinkLayerVisibility();">
						<label for="idctr_102" class="normal">도시고속도로</label>
						<span class="span_label marbottom07">&nbsp;</span>
						<input type="checkbox" id="idctr_103" name="road_level" value="103" disabled="disabled" onclick="javascript:setLinkLayerVisibility();">
						<label for="idctr_103" class="normal padright10">일반국도</label>
						<input type="checkbox" id="idctr_104" name="road_level" value="104" disabled="disabled" onclick="javascript:setLinkLayerVisibility();">
						<label for="idctr_104" class="normal">특별광역시도</label>
						<span class="span_label marbottom07">&nbsp;</span>
						<input type="checkbox" id="idctr_105" name="road_level" value="105" disabled="disabled" onclick="javascript:setLinkLayerVisibility();">
						<label for="idctr_105" class="normal padright23">국가지원지방도</label>
						<br />
						<span class="span_label">&nbsp;</span>
						<input type="checkbox" id="idctr_106" name="road_level" value="106" disabled="disabled" onclick="javascript:setLinkLayerVisibility();">
						<label for="idctr_106" class="normal padright23">지방도</label>
						<input type="checkbox" id="idctr_107" name="road_level" value="107" disabled="disabled" onclick="javascript:setLinkLayerVisibility();">
						<label for="idctr_107" class="normal">시군도</label>
					</p>
 					<p class="marbottom15">
						<span class="span_label_head">옵션별</span>
						<input type="radio" id="basic" name="color_option" value="basic" checked="checked">
						<label for="basic" class="normal">Color&nbsp;</label>
						<input type="radio" id="gray" name="color_option" value="gray">
						<label for="gray" class="normal">Grayscale&nbsp;</label>
					</p> 
					<p class="marbottom10 txtalignRight">
						<button type="button" class="btn btn-small" onclick="javascript:initSearchCondition()">초기화</button>
					</p>
				</fieldset>
			</div>
	
			<div class="contents_info">
				<div class="pad10">
					<p class="pButton" style="margin-bottom:20px;"><span style="font-weight:bold; color: #6F6F6F;">이 용 안 내</span></p>
					<p class="contents_info_01">* 본 분석결과는 2013년 12월 ~ 2014년 1월의 네비게이션 자료를 분석한 결과입니다.</p>
					
					<ol class="contents_info_02">
						<li>① 혼잡강도(%) : 해당 도로구간을 이용한 차량이 경<br/>&nbsp;&nbsp;&nbsp;험한 총 통행시간대비 혼잡 경계속도 이하로 주행<br/>&nbsp;&nbsp;&nbsp;한 차량의 총 통행시간 비율</li>
						<li>② CO2 배출량 : 해당 도로구간을 이용한 차량이 배<br/>&nbsp;&nbsp;&nbsp;출한 평균 CO2배출량(g/Km/대)</li>
						<li>③ 연료소모량 : 특정 시간대에 도로구간을 이용한 차<br/>&nbsp;&nbsp;&nbsp;량의 평균 유류소모량(l/Km/대)</li>
						<li>④ 지체시간 : 특정 시간대에 도로구간을 이용한 차량<br/>&nbsp;&nbsp;&nbsp;의 평균 제어지체(/Km/대)</li>
					</ol>
				<!-- <div class="info_comment03"><span style="font-size:12px">※</span>지도에서 도로를 클릭하면 소통정보가 툴팁으로 나타납니다.</div> -->
				</div>
			</div>
		</div>
		
		<div id="location_search" style="display: none;">
			<div class="pad10" style="border-bottom:2px solid #1e65c5;">
				<fieldset>
					<p class="marbottom15">
						<input type="text" style="width:200px; margin-right:7px; ime-mode:active;" id="location_search_value">
						<button type="button" class="btn btn-small" onclick="javascript:locationSearch()">검색</button>
					</p>
				</fieldset>
			</div>
			
			<div style="overflow:hidden; height: 491px;">	
				<div class="sch_result">
					<div class="search_title">
						<span class="floatRight">전체 <span id="searchCount" style="color:#003399; font-weight: bold;">&nbsp;-&nbsp;</span>건</span>
					</div>
					<div class="pad10">
						<ul class="search_list">
							<li style="margin:5px; font-weight:bold;">검색 결과가 없습니다.</li>
						</ul>
					</div>
					
					<div id="paging" class="paging" style="width: 100%; margin-top: 5px;">
						<div class="list">
							<a href="#" class="page active">1</a>
						</div>
					</div>
					<!--
					 	<div id="paging" class="paging" style="width: 100%; margin-top: 5px;">
							<a href="#" class="prev">이전</a>
							<div class="list">
								<a href="#" class="page active">1</a>
								<a href="#" class="page">2</a>
								<a href="#" class="page">3</a>
							</div>
							<a href="#" class="next">다음</a>
						</div>
					 -->
				</div>	
			</div>
		</div>
	</div>
</div>
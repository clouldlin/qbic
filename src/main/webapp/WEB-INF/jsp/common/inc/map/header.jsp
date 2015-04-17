<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/inc/taglib.jsp"%>
<div class="logo">
	<a href="${pageContext.request.contextPath}/map/map.do"><img class="headerLogo" src="${pageContext.request.contextPath}/images/map/logo.png" alt="The smartest map in the world - QbicMap"></a>
	<img class="headerSlogan" src="${pageContext.request.contextPath}/images/map/slogan.png" alt="You can find the place making you happy!!">
</div>
<input type="hidden" id="menu" name="menu" value="${menu}" />
<div class="menu">
   <div class="menulist">
		<ul class="menu_list">
			<li><a href="${pageContext.request.contextPath}/map/page.do?page=map_info" id="map_info">혼잡지도란</a><span class="bar">&nbsp;|&nbsp;</span></li>
			<li><a href="${pageContext.request.contextPath}/map/page.do?page=congestion_info" id="congestion_info">혼잡이란</a><span class="bar">&nbsp;|&nbsp;</span></li>
			<li><a href="${pageContext.request.contextPath}/map/map.do?menu=congestionIndex" id="congestionIndex">혼잡지표</a><span class="bar">&nbsp;|&nbsp;</span></li>
			<li><a href="${pageContext.request.contextPath}/map/map.do?menu=congestionCost" id="congestionCost">혼잡비용</a><span class="bar">&nbsp;|&nbsp;</span></li>
			<li><a href="${pageContext.request.contextPath}/map/map.do?menu=trafficVolume" id="trafficVolume">교통량</a><span class="bar">&nbsp;|&nbsp;</span></li>
			<li><a href="${pageContext.request.contextPath}/map/map.do?menu=demarcationSpeed" id="demarcationSpeed">속도</a><span class="bar">&nbsp;|&nbsp;</span></li>
			<li><a href="${pageContext.request.contextPath}/map/page.do?page=faq_info" id="faq_info">FAQ</a></li>
		</ul>
	</div>
	<div class="login_menu">
		<ul>
		    <c:if test="${empty userId}">
				<li><a href="#" id="login_button">로그인</a></li>	
		    </c:if>
		    <c:if test="${not empty userId}">
			     <li><a href="#"><span>${userName}님 환영합니다.</span></a></li>
			     <li><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
		    </c:if>
		</ul>
	</div>
</div>
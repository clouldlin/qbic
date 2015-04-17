<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/inc/taglib.jsp"%>
<%
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
	if (request.getProtocol().equals("HTTP/1.1"))
	{
		response.setHeader("Cache-Control", "no-cache");
	}
%>
<div class="footer">
	<span class="flogo"><img src="${pageContext.request.contextPath}/images/map/footer/logoB.gif" alt="KOTI 한국교통연구원"></span>
	<address><img src="${pageContext.request.contextPath}/images/map/footer/address_01.gif" alt="(우)339-007 세종특별자치시 시청대로 370 세종국책연구단지 과학인프라동 대표전화 : 044-211-3114  |  팩스 : 044-211-3222"></address>
	<p class="copyright"><img src="${pageContext.request.contextPath}/images/map/footer/copyright.gif" alt="Copyright(c)2011한국교통연구원.All right reserved."></p>
</div>	

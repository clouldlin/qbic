<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/inc/taglib.jsp"%>
<form id="commFrm" name="commFrm"></form>
<div class="global_wrap">
	<h1 class="logo"><a href="${pageContext.request.contextPath }/board/list.do">QBic Web</a></h1>
	<div class="global">
	    <c:if test="${empty userId}">
			<ul>
				<li class="last"><a href="#" id="login_button">로그인</a></li>	
		    </ul>
	    </c:if>
		<c:if test="${not empty userId}">
			<ul>
		      <li class="global1"><a href="${pageContext.request.contextPath}/mypage.do"><span>${userName}님 환영합니다.</span></a></li>
		      <li class="last"><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
		    </ul>
	    </c:if>
	</div>
</div>
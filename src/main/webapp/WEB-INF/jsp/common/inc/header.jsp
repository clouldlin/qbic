<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/inc/taglib.jsp"%>
<form id="commFrm" name="commFrm"></form>
<div class="global_wrap">
	<h1 class="logo"><a href="${pageContext.request.contextPath }/board/list.do">QBic Web</a></h1>
	<div class="global">
		<ul>
			<li class="global1"><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
		</ul>
	</div>
	
	<div id="login" class="login" title="관리자 로그인" style="display: block;">
		<p class="login_title">
			<img src="${pageContext.request.contextPath}/images/login/login_title.gif" alt="관리자 로그인">
			<span class="layerclose">
				<button type="button" onclick="showHide('login');return false;"><img src="${pageContext.request.contextPath}/images/login/btn_login_close.gif" alt="관리자 로그인 창닫기"></button>
			</span>
		</p>
		<fieldset>
			<div class="inner">
				<p>
					<label for="login_mail">아이디</label>
					<input type="text" id="login_mail" class="tbox01" maxlength="20">
				</p>
				<p class="last">
					<label for="password">패스워드</label>
					<input type="password" id="password" class="tbox01" maxlength="20">
				</p>
			</div>
			<div class="btn_center">
				<button onclick="adminLogin()"><img src="${pageContext.request.contextPath}/images/login/btn_login.gif" alt="들어가기"></button>
				<button onclick="loginReset()"><img src="${pageContext.request.contextPath}/images/login/btn_cancel.gif" alt="취소"></button>
			</div>
		</fieldset>
	</div>
</div>
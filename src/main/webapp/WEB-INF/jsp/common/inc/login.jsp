<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/inc/taglib.jsp"%>
<script type="text/javascript">
	<c:if test="${flag == '0' }">
		alert('${message}');
	</c:if>
</script>
<div class="login_area">
	<div id="login" class="login" title="관리자 로그인" style="display: none;">
		<p class="login_title">
			<img src="${pageContext.request.contextPath}/images/login/login_title.gif" alt="관리자 로그인">
			<span class="layerclose">
				<button id="login_close" type="button"><img src="${pageContext.request.contextPath}/images/login/btn_login_close.gif" alt="관리자 로그인 창닫기"></button>
			</span>
		</p>
		<form name="loginFrm" id="loginFrm" method="post" action="">
			<fieldset>
				<div class="inner">
					<p>
						<label for="login_id">아이디</label>
						<input type="text" name="id" id="login_id" class="tbox01" maxlength="20">
					</p>
					<p class="last">
						<label for="password">패스워드</label>
						<input type="password" name="password" id="password" class="tbox01" maxlength="20">
					</p>
				</div>
				<div class="btn_center">
					<button id="login_confirm"><img src="${pageContext.request.contextPath}/images/login/btn_login.gif" alt="들어가기"></button>
					<button id="login_reset"><img src="${pageContext.request.contextPath}/images/login/btn_cancel.gif" alt="취소"></button>
				</div>
			</fieldset>
		</form>
	</div>
</div>
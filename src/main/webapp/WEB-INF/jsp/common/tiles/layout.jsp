<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %> 
<%@ include file="/WEB-INF/jsp/common/inc/taglib.jsp"%>
<tiles:insertAttribute name="common" />
<script type="text/javascript" src="${pageContext.request.contextPath}/${script}"></script>
</head>
<body>
<!-- wrap start -->
<div id="wrap">
	<!-- header start -->
	<div id="header">
		<tiles:insertAttribute name="header" />
	</div>
	<!-- header end -->
	<!-- login start -->
	<tiles:insertAttribute name="login" />
	<!-- login end -->
	<!-- container start -->
	<div id="container">
		<div id="content">
			<tiles:insertAttribute name="leftmenu" />
			<tiles:insertAttribute name="content" />
		</div>
	</div>
	<!-- container end -->
</div>

<!-- footer start -->
<div id="footer">
	<tiles:insertAttribute name="footer" />
</div>
<!-- footer end -->
<!-- wrap end -->
</body>
</html>
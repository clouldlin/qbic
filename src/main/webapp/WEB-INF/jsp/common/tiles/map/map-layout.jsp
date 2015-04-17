<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %> 
<%@ include file="/WEB-INF/jsp/common/inc/taglib.jsp"%>
<tiles:insertAttribute name="common" />
<script type="text/javascript" src="${pageContext.request.contextPath}/${script}"></script>
</head>
<body class="jui"> 
<!-- wrap start -->
<div id="wrap_map">
	<!-- header start -->
	<div id="header_map">
		<tiles:insertAttribute name="header" />
	</div>
	<!-- header end -->
	<!-- login start -->
	<tiles:insertAttribute name="login" />
	<!-- login end -->
	<!-- container start -->
	<div id="container_map">
		<tiles:insertAttribute name="leftmenu" />
		<tiles:insertAttribute name="content" />
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
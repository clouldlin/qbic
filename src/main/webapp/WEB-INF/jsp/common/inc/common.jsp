<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<%@ include file="/WEB-INF/jsp/common/inc/taglib.jsp"%>
<%
	response.setHeader("Cache-Control","no-store");   
	response.setHeader("Pragma","no-cache");   
	response.setDateHeader("Expires",0);   
	if (request.getProtocol().equals("HTTP/1.1")) 
		response.setHeader("Cache-Control", "no-cache"); 
%>
<head>
<meta http-equiv="Content-Type" 	content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" 	content="IE=Edge" />
<title>QBic Web</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/common/jquery/jquery-ui-1.9.2.custom.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/common/jquery/jquery.ui.all.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/common/jquery/jquery.ui.datepicker.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/common/common.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/common/login.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/board/board.css'/>" />
<script type="text/javascript">
var baseUrl		= 	"<%=contextPath%>/";
</script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery-1.9.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery-ui-1.9.2.custom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.ui.button.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.ui.tabs.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.ui.mouse.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.ui.draggable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.ui.droppable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.cookie.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.print.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.validate.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.alphanumeric.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.MultiFile.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/common/common.js'/>"></script>


<%@ taglib prefix="tiles" 	  uri="http://tiles.apache.org/tags-tiles"  %>
<%@ taglib prefix="c" 		  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 		  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" 	  uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" 	  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="ui" 		  uri="http://egovframework.gov/ctl/ui" %>
<%
	String baseURL 		 = "http://" + request.getServerName()+ ":" + request.getServerPort() + request.getContextPath();
	String contextPath   = request.getContextPath();
%>
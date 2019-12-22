<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>testPage</title>
</head>
<link rel="stylesheet" type="text/css" href="<%=context%>/css/semantic.css">
<link rel="stylesheet" type="text/css" href="${context}/css/components/rating.css">
<body>
	
	<table class="ui celled padded table">
	  <tbody>
		<tr><td>no : ${vo.no}</td></tr>
		<tr><td>title : ${vo.title}</td></tr>
		<tr><td>id : ${vo.userId}</td></tr>
		<tr><td>regDt : ${vo.regDt}</td></tr>
		<tr><td>contents : ${vo.contents}</td></tr>
	  </tbody>
	</table>
	
	<jsp:include page="board.jsp"/>

<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous">	
</script>
<script src="${context}/js/semantic.js"></script>
<script src="${context}/css/components/rating.js"></script>
<script type="text/javascript">

</script>
</body>
</html>

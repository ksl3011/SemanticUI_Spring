<%@page import="com.edu.vo.FileVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%String context = request.getContextPath();%>
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
		<tr><td>no : ${selectPost.postNum}</td></tr>
		<tr><td>title : ${selectPost.title}</td></tr>
		<tr><td>id : ${selectPost.userId}</td></tr>
		<tr><td>regDt : ${selectPost.regDt}</td></tr>
		<tr><td>contents : ${selectPost.contents}</td></tr>
	  </tbody>
	</table>
	
	<table class="ui celled padded unstackable table">
		<tbody>
			<tr>
				<td colspan="3">${selectPost.title}</td>
			</tr>
			<tr>
				<td>${selectPost.postNum}</td>
				<td>${selectPost.regDt}</td>
				<td>${selectPost.userId}</td>
			</tr>
			<tr>
				<td colspan="3">${selectPost.contents}</td>
			</tr>
		</tbody>
	</table>
	
	<table class="ui red table">
		<thead>
		    <tr>
		    	<th>${selectPost.title} ${selectPost.regDt} ${selectPost.userId}</th>
		    	<th hidden="hidden"></th>
		    	<th hidden="hidden"></th>
			</tr>
		</thead>
		<tbody>
		    <tr>
				<td>${selectPost.contents}</td>
		    </tr>
		    <c:if test="${not empty fileList && fileList.size() > 0}">
	    		<c:forEach var="file" items="${fileList}">
	    			<tr>
	    				<td>${file.oName}<button type="button" name="downloadBtn">Download</button></td>
	    				<td hidden="hidden">${file.rName}</td>
	    				<td hidden="hidden">${file.size}</td>
	    			</tr>
	    		</c:forEach>
	    	</c:if>
		</tbody>
	</table>

	<form action="download" method="post">
		<input type="hidden" name="postNum" value="">
		<input type="hidden" name="rName" value="">
		<input type="hidden" name="size" value="">
	</form>

	<form action="" method="post" id="UDForm">
		<input type="hidden" name="pw" value="">
		<input type="hidden" name="postNum" value="${selectPost.postNum}">
	</form>

	<input type="button" value="삭제" id="delete">
	<input type="button" value="수정" id="update">
	
	<jsp:include page="board.jsp"/>

<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous">	
</script>
<script src="${context}/js/semantic.js"></script>
<script src="${context}/css/components/rating.js"></script>
<script type="text/javascript">

	$("#update").on("click", function(){
		var input = prompt("Password");
		var pw ="${selectPost.pw}";
		if(pw != input) return;
		
		var $f = $("#UDForm");
		$("input[name='pw']")[0].value = pw;
		$f.attr("action", "update");
		$f.submit();
	});

	$("#delete").on("click", function(){
		var input = prompt("Password");
		var pw = "${selectPost.pw}";
		if(pw != input) return;
		
		var $f = $("#UDForm");
		$("input[name='pw']")[0].value = pw;
		$f.attr("action", "delete");
		$f.submit();
	});
	
	$("button[name=downloadBtn]").on("click", function(){
		var $p = $(this).parent().parent()[0];
		var $f = $("form[action='download']")[0];
		var rName = $p.cells[1].textContent;
		var postNum = ${selectPost.postNum};
		var size = $p.cells[2].textContent;
		
		$f.size.value = size;
		$f.postNum.value = parseInt(postNum);
		$f.rName.value = rName;
		$f.submit();
		
	});
	
</script>
</body>
</html>

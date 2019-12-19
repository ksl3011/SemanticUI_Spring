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

	<button class="mini ui brown button" id="mainfsUpBtn">Size Up</button>
	<button class="mini ui gray button" id="mainfsDownBtn">Size Down</button>
	<button class="mini ui gray button" id="reBtn">re</button>
	<div class="ui sizer vertical segment" id="header">
	  <div class="ui huge header">Semantic UI - Header</div>
	</div>

	<table class="ui celled padded table">
	  <thead>
	    <tr>
		    <th>No</th>
		    <th>Title</th>
		    <th>User</th>
		    <th>Date</th>
		    <th>Star</th>
	  	</tr>
	  </thead>
	  <tbody>
		<c:choose>
			<c:when test="${not empty list && list.size() > 0}">
				<c:forEach var="list" items="${list}">
					<tr>
				      <td class="collapsing">
				      	${list.no}
				      </td>
				      <td>
				      	<a onclick="javascript:goPage('${list.no}')">${list.title}</a>
				      </td>
				      <td class="right aligned collapsing">
				        ${list.id}
				      </td>
				      <td class="right aligned collapsing">
				        ${list.regDt}
				      </td>
				      <td class="right collapsing">
				      	<div class="ui star rating" data-rating="1" data-max-rating="3"></div>
				      </td>
				    </tr>	   
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="99">NO DATA</td>
				</tr>
			</c:otherwise>
		</c:choose>
	  </tbody>
	  <tfoot>
	    <tr><th colspan="5">
	      <div class="ui right floated pagination menu">
	        <a class="icon item">
	          <i class="left chevron icon"></i>
	        </a>
	        <a class="item">1</a>
	        <a class="icon item">
	          <i class="right chevron icon"></i>
	        </a>
	      </div>
	    </th>
	  </tr></tfoot>
	</table>	

	<form action="contents" method="get" id="boardFrm">
		<input type="hidden" name="no" value="0" id="no">
	</form>

<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous">	
</script>
<script src="${context}/js/semantic.js"></script>
<script src="${context}/css/components/rating.js"></script>
<script type="text/javascript">
	$('.ui.rating').rating();

	$("#mainfsUpBtn").on('click', function(){
		var rawsize = $("#header").css("fontSize");
		var size = parseInt(rawsize)+1;  
		var px = rawsize.slice(-2);
		$("#header").css("font-size", size+px);
	});

	$("#mainfsDownBtn").on('click', function(){
		var rawsize = $("#header").css("fontSize");
		var size = parseInt(rawsize)+-1;  
		var px = rawsize.slice(-2);
		$("#header").css("font-size", size+px);
	});
	
	$("#reBtn").on('click', function(){
		location.href="board";
	});
	
	function goPage(number){
		var $f = $("#boardFrm");
		$("#no").val(number);
		$($f).submit();
		
	}
</script>
</body>
</html>

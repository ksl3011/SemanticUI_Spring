<%@page import="com.edu.vo.BoardVO"%>
<%@page import="com.edu.vo.BoardtestVO"%>
<%@page import="java.util.List"%>
<%@page import="com.edu.cmn.Common"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%
	String context = request.getContextPath();

	int nowPage = (request.getAttribute("nowPage")==null)?1:(int)request.getAttribute("nowPage");
	int listSize = (request.getAttribute("listSize")==null)?1:(int)request.getAttribute("listSize");
	int pageSize = (request.getAttribute("pageSize")==null)?1:(int)request.getAttribute("pageSize");
	String searchWord = (request.getAttribute("searchWord")==null)?"":(String)request.getAttribute("searchWord");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>testPage</title>
<style>
	.selected{
		background-color: lightcoral !important;
		color: white !important;
	}
	.container{
		margin: 10px;
	}
	.prompt{
		padding: 5px !important;
	}
</style>
</head>
<link rel="stylesheet" type="text/css" href="<%=context%>/css/semantic.css">
<link rel="stylesheet" type="text/css" href="${context}/css/components/rating.css">
<body>

	<div class="container">
		<button class="mini ui gray button" id="reBtn">목록</button>
	<div class="ui sizer vertical segment" id="header">
	  <div class="ui huge header">Semantic UI - Header</div>
	</div>
	
	<div class="ui search">
		<input class="prompt" type="text" placeholder="search">
	</div>
	<table class="ui celled padded unstackable selectable table">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>아이디</th>
				<th>날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty postList && postList.size() > 0}">
					<c:forEach var="list" items="${postList}">
						<tr>
						    <td class="collapsing">
						    	${list.postNum}
						    </td>
						    <td>
						    	<a onclick="javascript:goContents('${list.postNum}')">${list.title}</a>
						    </td>
						    <td class="right aligned collapsing">
						    	${list.userId}
						    </td>
						    <td class="right aligned collapsing">
								${list.regDt}
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
			<tr>
				<th colspan="5">
		      		<%=Common.pagination(listSize, pageSize, nowPage, "goPage") %>
				</th>
			</tr>
		</tfoot>
	</table>	
	
	<button class="mini ui gray button" id="postBtn">글쓰기</button>
	
	<form action="contents" method="get" id="boardFrm">
		<input type="hidden" name="postNum" value="0" id="postNum">
		<input type="hidden" name="pageNum" value="<%=nowPage%>">
		<input type="hidden" name="pageSize" value="<%=pageSize%>">
		<input type="hidden" name="searchWord_a" value="<%=searchWord%>">	
	</form>
	</div>

<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous">	
</script>
<script src="${context}/js/semantic.js"></script>
<script src="${context}/css/components/rating.js"></script>
<script type="text/javascript">

	$("#reBtn").on('click', function(){
		location.href="retrieve";
	});
	
	$("#postBtn").on('click', function(){
		location.href="post.jsp";
	});
	
	$(".prompt").keydown(function(key) {
		if (key.keyCode == 13) {
			$("input[name=searchWord_a]").val($(this).val());
			goPage(1);
		}
	});

	function goContents(postNum){
		var $f = $("#boardFrm");
		$("#postNum").val(postNum);
		$($f).submit();
	}
	
	function goPage(goPageNum){
		var $f = $("#boardFrm");
		$f.attr("action", "retrieve");
		$("input[name=pageNum]").val(goPageNum);
		$($f).submit();
	}

</script>
</body>
</html>

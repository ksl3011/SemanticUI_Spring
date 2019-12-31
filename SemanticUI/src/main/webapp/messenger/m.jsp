<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String context = pageContext.getRequest().getServletContext().getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=context%>/css/semantic.css">
<style type="text/css">
	.edge{
		border: 1px solid black;
		padding: 10px;
		width: 500px;
		height: 600px;
	}
	.commentBox{
		width: 450px;
		height: 450px;
		overflow-y: scroll;
	}
</style>
</head>
<body>
	
	<div class="edge">
		<div class="ui comments">
		<h3 class="ui dividing header">Comments</h3>
		<div class="commentBox"></div>

		</div>
		<div class="field">
			<textarea cols="65"></textarea>
		</div>
		<div class="ui blue labeled submit icon button" id="sendBtn">
			<i class="icon edit"></i> Add Reply
		</div>
	</div>

<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous">	
</script>
<script src="${context}/js/semantic.js"></script>
<script src="${context}/css/components/rating.js"></script>
<script type="text/javascript">

	$("textarea").keydown(function(key) {
		if (key.keyCode == 13) {
			key.preventDefault();
			$("#sendBtn").click();
		}
	});

	$("#sendBtn").on("click", function(){
		var text = $("textarea").val();
		if(text == "") return;
		$(".commentBox").append(
			"<div class='comment'>"
			+"<a class='avatar'>"
			+"<img src='https://semantic-ui.com/images/avatar/small/jenny.jpg'>"
			+"</a>"
			+"<div class='content'>"
			+"<a class='author'>Elliot Fu</a>"
			+"<div class='metadata'>"
			+"<span class='date'>Yesterday at 12:30AM</span>"
			+"</div>"
			+"<div class='text'>"
			+"<p>"+text+"</p>"
			+"</div>"
			+"</div>"
			+"</div>"
		);
		$("textarea").val("");
		scrollFullDown(".commentBox");
	});

	function scrollFullDown(div){
		var h = $(div)[0].scrollHeight;
		$(div).scrollTop(h);
	}
	
</script>
</body>
</html>
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
	
	<form action="" method="post">
		<table class="ui red table">
			<thead>
			    <tr>
			    	<th>글 쓰기</th>
				</tr>
			</thead>
			<tbody>
			    <tr>
					<td>
						<div class="ui form">
							<div class="fields">
							  <div class="twelve wide field">
							    <label for="title">제목</label>
							    <input type="text" placeholder="제목" id="title" name="title">
							  </div>
							</div>
							<div class="fields">
							  <div class="four wide field">
							    <input type="text" placeholder="ID" name="userId">
							  </div>
							  <div class="four wide field">
							    <input type="password" placeholder="Password" name="pw">
							  </div>
							</div>
							<div class="fields">
							  <textarea rows="10" cols="20" placeholder="내용" name="contents"></textarea>
							</div>
						</div>
						<button onclick="javascript:post();">제출</button>
						<button onclick="javascript:index();">목록</button>
					</td>
			    </tr>
			</tbody>
		</table>
	</form>

<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous">	
</script>
<script src="${context}/js/semantic.js"></script>
<script src="${context}/css/components/rating.js"></script>
<script type="text/javascript">
	
	function index(){
		location.href = "retrieve";
	}
	
	function post(){
		var $form = $("form")[0];
		$form.action = "post";
		$form.submit();
		
	}
</script>
</body>
</html>

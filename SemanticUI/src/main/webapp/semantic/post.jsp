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
<style type="text/css">
	.fileBox>input{
		width: 94% !important;
	}
	.fileBox>Button{
		width: 6%;
		height: 44px;
	}
	#files{
		border:1px solid lightgray;
		border-radius:5px;
	}
</style>
</head>
<link rel="stylesheet" type="text/css" href="<%=context%>/css/semantic.css">
<link rel="stylesheet" type="text/css" href="${context}/css/components/rating.css">
<body>
	<div class="ui segment">
		<form action="" method="post" enctype="multipart/form-data">
			<table class="ui red table">
				<thead>
				    <tr>
				    	<th>글 쓰기</th>
					</tr>
				</thead>
				<tbody>
				    <tr>
						<td>
							<div class="ui form unstackable">
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
									<div class="twelve wide field">
										<textarea rows="10" cols="20" placeholder="내용" name="contents"></textarea>
									</div>
								</div>
								<div class="fields">
									<div class="twelve wide field">
										<label>첨부파일 </label>
										<div id="files">
											<label>없음</label>
										</div>
										<button type="button" id="addBtn">파일추가</button>
									</div>
								</div>
							</div>
							<br/>
							<button onclick="javascript:post();">제출</button>
							<button type="button" onclick="javascript:index();">목록</button>
						</td>
				    </tr>
				</tbody>
			</table>
		</form>
		<div class="ui dimmer">
		    <div class="content">
		    	<p>uploading...</p>
		    </div>
		`</div>
	</div>
<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous">	
</script>
<script src="${context}/js/semantic.js"></script>
<script src="${context}/css/components/rating.js"></script>
<script type="text/javascript">
	
	$(function(){
		$('.segment').dimmer("hide");
	});
	
	function index(){
		location.href = "retrieve";
	};
	
	function post(){
		var $form = $("form")[0];
		$form.action = "post";
		$('.segment').dimmer("show");
		$form.submit();
		
	};
	
	function inputFile(e){
		var size = e.files[0].size;
		console.log(size);
		if(size > 1024*1024*50){
			alert(">50MB");
			$(e).val(""); 
		}
	};
	
	$("#addBtn").on("click", function(){
		var length = $("input[type=file]").length;
		if(length <5){
			$("#files>label").detach();
			$("#files").append(
				"<div class='fileBox'><input type='file' name='files"+length+"' onchange='javascript:inputFile(this);'><button type='button' onclick='javascript:deleteFile(this)'>x</button></div>"
			);
		}else{
			alert("5");
		}
		fileName();
	});
	
	function fileName(){
		var length = $("input[type=file]").length;
		for(var i=0 ; i<length ; i++){
			$("input[type=file]")[i].name = "files"+i;
		}	
	}
	
	function deleteFile(e){
		$(e).parent().detach();
		fileName();
	}
</script>
</body>
</html>

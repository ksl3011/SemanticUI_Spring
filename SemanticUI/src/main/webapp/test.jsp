<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<button class="mini ui brown button" id="mainfsUpBtn">Size Up</button>
	<button class="mini ui gray button" id="mainfsDownBtn">Size Down</button>
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

	  	<tr>
	      <td class="collapsing">
	        2
	      </td>
	      <td >
	       test2
	      </td>
	      <td class="right aligned collapsing">
	        user2
	      </td>
	      <td class="collapsing right aligned">
	        2020-01-01
	      </td>
	      <td class="right collapsing">
	      	<div class="ui star rating" data-rating="1" data-max-rating="3"></div>
	      </td>
	    </tr>
	  
	    <tr>
	      <td class="collapsing">
	        1
	      </td>
	      <td >
	       test1
	      </td>
	      <td class="right aligned collapsing">
	        user1
	      </td>
	      <td class="collapsing right aligned">
	        2020-01-01
	      </td>
	      <td class="right collapsing">
	      	<div class="ui star rating" data-rating="3" data-max-rating="3"></div>
	      </td>
	    </tr>
	    

	  </tbody>
	  <tfoot>
	    <tr><th colspan="5">
	      <div class="ui right floated pagination menu">
	        <a class="icon item">
	          <i class="left chevron icon"></i>
	        </a>
	        <a class="item">1</a>
	        <a class="item">2</a>
	        <a class="item">3</a>
	        <a class="item">4</a>
	        <a class="icon item">
	          <i class="right chevron icon"></i>
	        </a>
	      </div>
	    </th>
	  </tr></tfoot>
	</table>


<link rel="stylesheet" type="text/css" href="css/semantic.css">
<link rel="stylesheet" type="text/css" href="css/components/rating.css">
<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous">	
</script>
<script src="js/semantic.js"></script>
<script src="css/components/rating.js"></script>
<script type="text/javascript">
$('.ui.rating')
.rating()
;

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
</script>
</body>
</html>

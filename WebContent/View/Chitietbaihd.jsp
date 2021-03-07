<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="Template/Fontend/css/bootstrap.min.css" rel="stylesheet">
	<link href="Template/Fontend/css/bootstrap.css" rel="stylesheet">
    <link href="Template/Fontend/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="Template/Fontend/css/style.css" rel="stylesheet"> 
	<link rel="stylesheet" href="Template/Backend/Font/font-awesome.min.css" />
	<script src="Template/Fontend/js/jquery1.js"></script>
    <script src="Template/Fontend/js/bootstrap.min.js"></script>
    <script src="Template/Fontend/js/remarkable.min.js"></script>

    
</head>
<body>
	<!--HEADER ROW-->
	
	<jsp:include page="Header.jsp"></jsp:include>
  
  <!-- /HEADER ROW -->
  <div class="container">
	  <!--PAGE TITLE-->

	<div class="row">
		<div class="span12">
		<div class="page-header">
				<c:if test="${guidelineroleid==1}">
				<h1>
				Bài hướng dẫn học ngữ pháp
				</h1>
				</c:if>
				<c:if test="${guidelineroleid==2}">
				<h1>
				Bài hướng dẫn phần nghe
				</h1>
				</c:if>
				
		</div>
		</div>
	</div>

  <!-- /. PAGE TITLE-->
  

	<div class="row">

	
		<div class="span9">
			<!--Blog Post-->
			<c:forEach items="${listgl}" var="list">
			<div class="blog-post">
				<h2>${list.guidelinename}</h2>
				<div class="postmetadata">
					<ul>
					
						<li>
							 <i class="icon-calendar"></i><%= (new java.util.Date()).toLocaleString()%>
						</li>

						<li>
							<i class="icon-comment"></i> <a href="#">${countcmt} Bình luận</a>
						</li>
						
					</ul>
				</div>


			
				<img src="ImageUpload/${list.guidelineimage}" width="584px" height="416px">
				<p>
				<c:set var ="kq1" value="${fn:replace(list.content,kitutrongdatabase1,kitutronghtml1)}"/>
				
					<script>

      					var md = new Remarkable();
      					console.log('${kq1}');
						var str = md.render('${kq1}');
						console.log(str);
						var content = str.replaceAll('&lt;', '<');
						var content1 = content.replaceAll('&gt;', '>');
						var content2 = content1.replaceAll('&quot;', '"');
						console.log(content2);
						document.write(content2);
				
    				</script>
				</p>
			</div>
			</c:forEach>



			<h2>Bình luận</h2>


			<div class="comments">
				<div id="listcomment">
				  
				  <c:forEach items="${listcommentguideline}" var="list">
					<div class="media" >
					 <a href="#" class="pull-left"><img id="boderimg" alt="hinh" src="Avatar/${list.userimage}" width = "60px" height ="60px" class="media-object"  /></a>
					<div class="media-body">
						<h4 class="media-heading">
							<b>${list.name}</b>
						</h4> ${list.cmtguidelinecontent}
					</div>
					</div>
					</c:forEach>
				
				</div>
			<!--===============-->
			<%
				if(session.getAttribute("sessionuser")==null){
			%>
				<input class="span8"  type="text" placeholder="Đăng nhập để bình luận" name="cmtguidelinecontent" disabled/>
				<button type="button" class="btn btn-large btn-primary" disabled>Đăng bình luận</button>
			<%
			}else{
			%>
			<form name="formbinhluan">
				<input class="span8"  type="text" placeholder="Viết bình luận" name="cmtguidelinecontent"/>
				<button type="button" class="btn btn-large btn-primary" onclick="Binhluan()">Đăng bình luận</button>
			</form>
			<%} %>
			<!--Comments-->
				
			</div>

			
		</div>
		<div class="span3">
			<div class="side-bar">

				<h3>Danh mục</h3>
				<ul class="nav nav-list">
					<li><a href="DsbaihdForward?pageid=1&guidelineroleid=1">Hướng dẫn học ngữ pháp</a></li>
					<li><a href="DsbaihdForward?pageid=1&guidelineroleid=2">Hướng dẫn phần nghe</a></li>
					<li><a href="List_Exercise_Forward?pageid=1&type=1">Làm bài tập phần đọc</a></li>
					<li><a href="List_Exercise_Forward?pageid=1&type=2">Làm bài tập phần nghe</a></li>
					<li><a href="ListQuizForward?pageid=1">Thi thử toeic</a></li>
				</ul>

			</div>
		</div>
	</div>
	</div>
	 <div>
		<jsp:include page="Footer.jsp"></jsp:include>
	 </div>
</body>

	<script type="text/javascript">
    	function Binhluan() {
			var xhttp;
			var cmtguidelinecontent = document.formbinhluan.cmtguidelinecontent.value;
			var guidelineid = "<%=request.getAttribute("glid")%>";
			
			var url = "CmtGuidelineController?cmtguidelinecontent="+cmtguidelinecontent+"&guidelineid="+guidelineid;
			
			if(window.XMLHttpRequest){
				xhttp = new XMLHttpRequest();
			}
			else
			{
				xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			xhttp.onreadystatechange = function()
			{
				if(xhttp.readyState == 4){
					var data = xhttp.responseText;
					document.getElementById("listcomment").innerHTML = data;
				}
			}
			xhttp.open("POST",url,true);
			xhttp.send();
			document.formbinhluan.cmtguidelinecontent.value="";
		}
    </script>

    <style type="text/css">
      #boderimg{
      	border-radius: 50%;
      }
    </style>
</html>
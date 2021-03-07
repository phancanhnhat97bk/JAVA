<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="Template/Fontend/css/bootstrap.css" rel="stylesheet">
    <link href="Template/Fontend/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="Template/Fontend/css/style.css" rel="stylesheet"> 
	<link rel="stylesheet" href="Template/Backend/Font/font-awesome.min.css" />
	<script src="Template/Fontend/js/jquery1.js"></script>
    <script src="Template/Fontend/js/bootstrap.min.js"></script>
	<style type="text/css">
			#para1{
				padding-top:40px;
			}
			.label-danger{
				background-color: red; 
			} 
	</style>
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
				Danh sách bài tập hướng dẫn ngữ pháp
			</h1>
			</c:if>
			<c:if test="${guidelineroleid==2}">
			<h1>
				Danh sách bài tập hướng dẫn nghe
			</h1>
			</c:if>
			<c:if test="${guidelineroleid==3}">
			<h1>
				Bài hướng dẫn phù hợp với bạn
			</h1>
			</c:if>
		</div>
		</div>
	</div>

  <!-- /. PAGE TITLE-->
  	<div class="row">
  		<c:forEach items="${listgl}" var="list">
			<div class="span6">
			<div class="media">
					<a href="#" class="pull-left"><img src="ImageUpload/${list.guidelineimage}" class="media-object" alt='Hình' width = "250px" height = "250px" /></a>
					<div class="media-body">
						<p>
							${list.guidelinename}
						</p>
					<c:if test="${list.level == 1}">
						<span class="label label-success arrowed">bắt đầu</span>
					</c:if>
					<c:if test="${list.level == 2}">
						<span class="label label-warning arrowed">cơ bản</span>
					</c:if>
					<c:if test="${list.level == 3}">
						<span class="label label-danger arrowed">nâng cao</span>
					</c:if>
					
					<br/>
					<br/>
					<a href="ChitietbaihdForward?glid=${list.guidelineid}&guidelineroleid=${guidelineroleid}" class="btn" type="button">Xem bài hướng dẫn</a>
					
					</div>
			</div>
			</div>
		</c:forEach>
	</div>	
		<div class="row">

	
		<div class="span9">
		<!--Pagination-->
			<div class="pagination">
				<ul>
					
					<c:if test="${numberpage == 1 && numberpage == maxpageid}">
						<li class="disabled"><a href="#">Prev</a></li>
						<li class="disabled"><a href="#">Next</a></li>
					</c:if>
					<c:if test="${numberpage ==1 && numberpage != maxpageid}">
						<li class="disabled"><a href="#">Prev</a></li>
						<li><a href="DsbaihdForward?pageid=${numberpage +1}&guidelineroleid=${guidelineroleid}">Next</a></li>
					</c:if>
					
					<c:if test="${numberpage == maxpageid && numberpage !=1}">
						<li><a href="DsbaihdForward?pageid=${numberpage -1}&guidelineroleid=${guidelineroleid}">Prev</a></li>
						<li class="disabled"><a href="#">Next</a></li>
					</c:if>
					
					<c:if test="${numberpage >1 && numberpage < maxpageid}">
						<li><a href="DsbaihdForward?pageid=${numberpage -1}&guidelineroleid=${guidelineroleid}">Prev</a></li>
						<li><a href="DsbaihdForward?pageid=${numberpage +1}&guidelineroleid=${guidelineroleid}">Next</a></li>
					</c:if>
				</ul>
			</div>	

		<!--/.Pagination-->
			</div>
		</div>
	 </div>
	 <div id="para1">
		<jsp:include page="Footer.jsp"></jsp:include>
	 </div>
</body>
</html>
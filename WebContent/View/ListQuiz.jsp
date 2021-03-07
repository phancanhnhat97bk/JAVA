<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
			<h1>
				Danh sách đề thi
			</h1>
		</div>
		</div>
	</div>

  <!-- /. PAGE TITLE-->
  	<div class="row">
  		<c:forEach items="${listexamination}" var="list">
			<div class="span6">
			<div class="media">
					<a href="#" class="pull-left"><img src="ImageAudioExam/${list.examinationimage}" class="media-object" alt='Hình' width = "250px" height = "250px" /></a>
					<div class="media-body">
						<p>
							${list.examinationname}
						</p>
						<p>
							${list.time} phút
						</p>
					<%
						if(session.getAttribute("sessionuser")==null){
					%>		
						<button type="button" class="btn" disabled>Bạn cần đăng nhập để thi</button>
					<%
						}else{
					%>
						<a href="QuizForward?examinationid=${list.examinationid}&time=${list.time}" class="btn" type="button">Bắt đầu làm bài</a>
					<%} %>
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
						<li><a href="ListQuizForward?pageid=${numberpage +1}">Next</a></li>
					</c:if>
					
					<c:if test="${numberpage == maxpageid && numberpage !=1}">
						<li><a href="ListQuizForward?pageid=${numberpage -1}">Prev</a></li>
						<li class="disabled"><a href="#">Next</a></li>
					</c:if>
					
					<c:if test="${numberpage >1 && numberpage < maxpageid}">
						<li><a href="ListQuizForward?pageid=${numberpage -1}">Prev</a></li>
						<li><a href="ListQuizForward?pageid=${numberpage +1}">Next</a></li>
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
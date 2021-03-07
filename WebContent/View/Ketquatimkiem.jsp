<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kết quả tìm kiếm</title>
</head>
<body>
	<!-- /. PAGE TITLE -->
	<%
		if(request.getAttribute("ketqua")!=null)
		{
	%>
		<div class="row">
			<div class="span6">
				<div class="media">
					<%= request.getAttribute("ketqua") %>
				</div>
			</div>
		</div>
	<%
		}
		else
		{
	%>
  	<div class="row">

  	<c:forEach items="${listsearchgl}" var="listgl">
			<div class="span6">
			<div class="media">
					<a href="#" class="pull-left"><img src="ImageUpload/${listgl.guidelineimage}" class="media-object" alt='Hình' width = "250px" height = "250px" /></a>
					<div class="media-body">
						<p>
							${listgl.guidelinename}
						</p>
					<a href="ChitietbaihdForward?glid=${listgl.guidelineid}&guidelineroleid=${listgl.guidelineroleid}" class="btn" type="button">Xem bài hướng dẫn</a>
					</div>
			</div>
			</div>
		</c:forEach>
	</div>	
	
	<%
		}
	%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
	      #boderimg{
	      	border-radius: 50%;
	      }
	</style>
</head>
<body>
			
				<c:forEach items="${listcommentguideline}" var="list">
					<div class="media" >
					 <a href="#" class="pull-left"><img id="boderimg" src="Avatar/${list.userimage}" width = "60px" height ="60px" class="media-object" alt='' /></a>
					<div class="media-body">
						<h4 class="media-heading">
							<b>${list.name}</b>
						</h4> ${list.cmtguidelinecontent}
					</div>
					</div>
				</c:forEach>
</body>
</html>
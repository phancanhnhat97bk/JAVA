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
				padding-top:20px;
			} 
			.blockexam{
				display: block;
				background-color: #F1ECEC;
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
				"Kết quả bài thi :   ${countCorrect} / ${countrow} câu đúng
			</h1>
		</div>
		</div>
	</div>

  <!-- /. PAGE TITLE-->

<div class="row">
	<form action="List_Exercise_Forward?pageid=1&type=${type}"  method="POST">
		<c:forEach items="${listquiz}" var = "list">
			<div class="blockexam">
				<h3><i><b>${list.paragraph}</b></i></h3>
			
				<h4><b>Question ${list.num}: </b> ${list.question}</h4>
				<br>
				<c:if test="${list.image != null}">
				<img src="ImageAudioExercise/${list.image}" class="media-object" alt='Hình' width = "400px" height = "300px" />
				<br/>
				</c:if>
				<c:if test="${list.audiogg != null || list.audiomp3 != null}">
					<audio controls>
			    		<source src="ImageAudioExercise/${list.audiogg}" type="audio/ogg">
			    		<source src="ImageAudioExercise/${list.audiogg}" type="audio/mpeg">
					</audio>
					<br/>
				</c:if>
				<br/>
				
				
			<c:forEach items="${listansweruser}" var = "listansweruser">
				<c:if test="${list.num == listansweruser.number }">
					
			<!-- Correct is A -->
							
					<c:if test="${list.correctanswer == 'A'}">
						
						<c:if test="${listansweruser.answer == null}">
									<c:if test="${list.option1 != null}">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										 ${list.option4}
										<br>
									</c:if>
						</c:if>
						
						<c:if test="${listansweruser.answer == 'A'}">
									<c:if test="${list.option1 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										 ${list.option4}
										<br>
									</c:if>
						</c:if>
						
						
						<c:if test="${listansweruser.answer == 'B'}">
									<c:if test="${list.option1 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										<img alt="" src="Template/Fontend/img/error.png">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										 ${list.option4}
										<br>
									</c:if>
									
						</c:if>
						
						
						<c:if test="${listansweruser.answer == 'C'}">
									<c:if test="${list.option1 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										<img alt="" src="Template/Fontend/img/error.png">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										 ${list.option4}
										<br>
									</c:if>
									
						</c:if>
						
						
						<c:if test="${listansweruser.answer == 'D'}">
									<c:if test="${list.option1 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										<img alt="" src="Template/Fontend/img/error.png">
										 ${list.option4}
										<br>
									</c:if>
									
						</c:if>
						
						
					</c:if>
					
					
					
				<!-- Correct is B -->	
					<c:if test="${list.correctanswer == 'B'}">
						
						<c:if test="${listansweruser.answer == null}">
									<c:if test="${list.option1 != null}">
										
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										 ${list.option4}
										<br>
									</c:if>
						</c:if>
						
						<c:if test="${listansweruser.answer == 'A'}">
									<c:if test="${list.option1 != null}">
										<img alt="" src="Template/Fontend/img/error.png">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										 ${list.option4}
										<br>
									</c:if>
						</c:if>
						
						
						<c:if test="${listansweruser.answer == 'B'}">
									<c:if test="${list.option1 != null}">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										 ${list.option4}
										<br>
									</c:if>
									
						</c:if>
						
						
						<c:if test="${listansweruser.answer == 'C'}">
									<c:if test="${list.option1 != null}">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										<img alt="" src="Template/Fontend/img/error.png">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										 ${list.option4}
										<br>
									</c:if>
									
						</c:if>
						
						
						<c:if test="${listansweruser.answer == 'D'}">
									<c:if test="${list.option1 != null}">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										<img alt="" src="Template/Fontend/img/error.png">
										 ${list.option4}
										<br>
									</c:if>
									
						</c:if>
					</c:if>
					
					
				<!-- Correct is C -->	
				
					<c:if test="${list.correctanswer == 'C'}">
						
						<c:if test="${listansweruser.answer == null}">
									<c:if test="${list.option1 != null}">
										
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										 ${list.option4}
										<br>
									</c:if>
						</c:if>
						
						<c:if test="${listansweruser.answer == 'A'}">
									<c:if test="${list.option1 != null}">
										<img alt="" src="Template/Fontend/img/error.png">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">	
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										 ${list.option4}
										<br>
									</c:if>
						</c:if>
						
						
						<c:if test="${listansweruser.answer == 'B'}">
									<c:if test="${list.option1 != null}">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										<img alt="" src="Template/Fontend/img/error.png">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">	
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										 ${list.option4}
										<br>
									</c:if>
									
						</c:if>
						
						
						<c:if test="${listansweruser.answer == 'C'}">
									<c:if test="${list.option1 != null}">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">	
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										 ${list.option4}
										<br>
									</c:if>
									
						</c:if>
						
						
						<c:if test="${listansweruser.answer == 'D'}">
									<c:if test="${list.option1 != null}">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">	
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										<img alt="" src="Template/Fontend/img/error.png">
										 ${list.option4}
										<br>
									</c:if>
									
						</c:if>
					</c:if>
					
					
					
					
					<!-- Correct is D -->	
					<c:if test="${list.correctanswer == 'D'}">
						
						<c:if test="${listansweruser.answer == null}">
									<c:if test="${list.option1 != null}">
										
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										 ${list.option4}
										<br>
									</c:if>
						</c:if>
						
						<c:if test="${listansweruser.answer == 'A'}">
									<c:if test="${list.option1 != null}">
										<img alt="" src="Template/Fontend/img/error.png">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">
										 ${list.option4}
										<br>
									</c:if>
						</c:if>
						
						
						<c:if test="${listansweruser.answer == 'B'}">
									<c:if test="${list.option1 != null}">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										<img alt="" src="Template/Fontend/img/error.png">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">
										 ${list.option4}
										<br>
									</c:if>
									
						</c:if>
						
						
						<c:if test="${listansweruser.answer == 'C'}">
									<c:if test="${list.option1 != null}">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										<img alt="" src="Template/Fontend/img/error.png">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">
										 ${list.option4}
										<br>
									</c:if>
									
						</c:if>
						
						
						<c:if test="${listansweruser.answer == 'D'}">
									<c:if test="${list.option1 != null}">
										 ${list.option1}
										<br>
									</c:if>
									<c:if test="${list.option2 != null}">
										
										 ${list.option2}
										<br>
									</c:if>
									<c:if test="${list.option3 != null}">
										 ${list.option3}
										<br>
									</c:if>
									<c:if test="${list.option4 != null}">
										<img alt="" src="Template/Fontend/img/correct.png">
										 ${list.option4}
										<br>
									</c:if>
									
						</c:if>
					</c:if>				
				
				</c:if>
			</c:forEach>
			</div>
		</c:forEach>
		<br/>
		<br/>
		<input type="submit" class="btn btn-large btn-primary" value="Làm lại">
	</form>
	
	</div>
		<div class="row">
		</div>
	</div>
	<div id="para1">
		<jsp:include page="Footer.jsp"></jsp:include>
	 </div>
</body>
</html>
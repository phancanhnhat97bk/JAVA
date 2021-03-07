<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Chủ</title>
    <link href="Template/Fontend/css/bootstrap.css" rel="stylesheet">
    <link href="Template/Fontend/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="Template/Fontend/css/style.css" rel="stylesheet"> 
    
	<link rel="stylesheet" href="Template/Backend/Font/font-awesome.min.css" />
 
    <link rel="shortcut icon" href="ico/favicon.ico">
    
    <script src="Template/Fontend/js/jquery1.js"></script>
    <script src="Template/Fontend/js/bootstrap.min.js"></script>
    <style>
    	#size{
    		padding-top:20px;
    		padding-bottom:10px;
    	}
    	#size1{
    		padding-top:60px;
    	}
    </style>
    <script type="text/javascript">
    	function Search() {
			var xhttp;
			var guidelinename = document.myform.guidelinename.value;
			if(guidelinename != ""){
				var url = "SearchController?guidelinename="+guidelinename;
				if(window.XMLHttpRequest){
					xhttp = new XMLHttpRequest();
				}
				else
				{
					xhttp = new ActiveXObject("Microsoft.XMLHTTP");	
				}
				xhttp.onreadystatechange = function(){
					if(xhttp.readyState == 4){
						var data = xhttp.responseText;
						document.getElementById("searchresult").innerHTML = data;
					}
				}
				xhttp.open("POST",url,true);
				xhttp.send();
			}
			else{
				document.getElementById("searchresult").innerHTML = "";
			}
			
		}
    </script>
</head>
<body>
	<!--HEADER ROW-->
	
	<jsp:include page="Header.jsp"></jsp:include>
  
  <!-- /HEADER ROW -->

  <!-- begin search -->
  
  	<div class="container" >
		<div class="row">
			<div class="span6">
				<div id="size">
					<form name="myform">
						<input type="text" class="form-control" placeholder="Tìm kiểm" style="width:500px" name="guidelinename" onkeyup="Search()">
					</form>
				</div>
			</div>
		</div>  	
  	</div>


  <div class="container" id="searchresult">

  <!--Carousel
  ==================================================-->

  <div id="myCarousel" class="carousel slide">
    <div class="carousel-inner">

      <div class="active item">
        <div class="container">
          <div class="row">
            
              <div class="span6">

                <div class="carousel-caption">
                      <h1>Hướng Dẫn Phần Nghe, Đọc Toeic</h1>
                      <p class="lead">Chúng tôi cung cấp cho các bạn những kiến thức tốt nhất, hãy tham gia ngay!</p>
                </div>

              </div>

                <div class="span6"> <img src="Template/Fontend/img/slide/banner1.jpg" height ="550px" width = "550px"></div>

          </div>
        </div>
       

      </div>

	<c:forEach items="${listslidebanner}" var ="list">
		<div class="item">
       
        <div class="container">
          <div class="row">
            
              <div class="span6">

                <div class="carousel-caption">
                      <h1>${list.slidename }</h1>
                      <p class="lead">${list.slidecontent}</p>
                </div>

              </div>

                <div class="span6"> <img src="Template/Fontend/img/slide/${list.slideimage}" height ="550px" width = "550px"></div>

          </div>
        </div>

      </div>
	</c:forEach>
      




    </div>
       <!-- Carousel nav -->
      <a class="carousel-control left " href="#myCarousel" data-slide="prev"><i class="icon-chevron-left"></i></a>
      <a class="carousel-control right" href="#myCarousel" data-slide="next"><i class="icon-chevron-right"></i></a>
        <!-- /.Carousel nav -->

  </div>
    <!-- /Carousel -->



<!-- Feature 
  ==============================================-->


  <div class="row feature-box">
      <div class="span12 cnt-title">
       <h1>Chúng tôi cung cấp cho bạn các giao diện học và thi</h1> 
        <span>(--Học thử, Làm bài tập, Thi thử--)</span>        
      </div>

      <div class="span4">
        <img src="Template/Fontend/img/study-go-ico.jpg">
        <h2>Hướng dẫn phần từ vựng, ngữ pháp</h2>
        <p>
			Cung cấp các bài hướng dẫn sát với đề thi
        </p>

        <a href="#" data-toggle="modal" data-target="#myModal">Chi tiết &rarr;</a>

      </div>

      <div class="span4">
        <img src="Template/Fontend/img/practical-ico.jpg">
        <h2>Bài tập phần nghe, đọc</h2>
        <p>
			Chúng tôi cung cấp các dạng bài tập có trong đề thi Toeic.
        </p>   
          <a href="#" data-toggle="modal" data-target="#myModalExercise" >Chi tiết &rarr;</a>    
      </div>

      <div class="span4">
        <img src="Template/Fontend/img/document-ico.jpg">
        <h2>Đề thi thử</h2>
        <p>
			Chúng tôi cung cấp cho các bạn đề thi sát với thi thật.
        </p>
          <a href="ListQuizForward?pageid=1">Chi tiết &rarr;</a>
      </div>
  </div>


<!-- /.Feature -->


    
</div>


<!--Footer
==========================-->
<div id="size1">
	<jsp:include page="Footer.jsp"></jsp:include>
</div>

<!-- Modal Làm Bài tập -->
		<div id="myModalExercise" class="modal fade" role="dialog">
		  <div class="modal-dialog">
		  
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">DANH SÁCH LOẠI BÀI TẬP</h4>
		      </div>
		      <div class="modal-body">
		      
				 <div class="media">
		        	<a class="pull-left"><img alt="" src="Template/Fontend/img/readicon.png" width ="64" height ="64" class="media-object"></a>
					<div class="media-body">
						<h4>
							<a href="List_Exercise_Forward?pageid=1&type=1">Bài tập phần đọc</a>
						</h4>
					</div>
				</div>
				<div class="media">
		        	<a class="pull-left"><img alt="" src="Template/Fontend/img/listenicon.png" width ="64" height ="64" class="media-object"></a>
					<div class="media-body">
						<h4>
							<a href="List_Exercise_Forward?pageid=1&type=2">Bài tập phần nghe</a>
						</h4>
					</div>
				</div>
		      </div>
		      <br/>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Thoát</button>
		      </div>
		    </div>
		  </div>
		</div>
		<!-- End Modal làm bài tập -->


		<!-- Modal Hướng dẫn-->
		<div id="myModal" class="modal fade" role="dialog">
		  <div class="modal-dialog">
		  
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">DANH SÁCH LOẠI BÀI HƯỚNG DẪN</h4>
		      </div>
		      <div class="modal-body">
		      	<%
				if(session.getAttribute("sessionuser")!=null){
				%>
		      	 <div class="media">
		        	<a class="pull-left"><img alt="" src="Template/Fontend/img/icon2.png" width ="64" height ="64" class="media-object"></a>
					<div class="media-body">
						<h4>
							<a href="DsbaihdForward?pageid=1&guidelineroleid=3">Bài hướng dẫn phù hợp với bạn</a>
						</h4>
					</div>
				 </div>
				 <% } %>
				 <div class="media">
		        	<a class="pull-left"><img alt="" src="Template/Fontend/img/grammar.jpg" width ="64" height ="64" class="media-object"></a>
					<div class="media-body">
						<h4>
							<a href="DsbaihdForward?pageid=1&guidelineroleid=1">Bài hướng dẫn phần ngữ pháp</a>
						</h4>
					</div>
				</div>
				<div class="media">
		        	<a class="pull-left"><img alt="" src="Template/Fontend/img/listen.png" width ="64" height ="64" class="media-object"></a>
					<div class="media-body">
						<h4>
							<a href="DsbaihdForward?pageid=1&guidelineroleid=2">Bài hướng dẫn phần nghe</a>
						</h4>
					</div>
				</div>
		      </div>
		      <br/>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Thoát</button>
		      </div>
		    </div>
		  </div>
		</div>

</body>
</html>
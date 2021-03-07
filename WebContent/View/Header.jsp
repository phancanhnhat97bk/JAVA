<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
      #boderimg{
      	border-radius: 50%;
      	width: 60px;
      	height: 60px;
      	
      	
      }
    </style>
</head>
<body>
<%

	if(session.getAttribute("sessionuser")==null){

%>

<div id="header-row">
    <div class="container">
      <div class="row">
              <!--LOGO-->
              <div class="span3"><a class="brand" href="HomeForward"><img src="Template/Fontend/img/logo.jpg" height ="100px" width="100px"/></a></div>
              <!-- /LOGO -->

            <!-- MAIN NAVIGATION -->  
              <div class="span9">
                <div class="navbar  pull-right">
                  <div class="navbar-inner">
                    <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a>
                    <div class="nav-collapse collapse navbar-responsive-collapse">
                    <ul class="nav">
                        

                        <li><a href="LoginForward">Đăng nhập</a></li>
                        <li><a href="RegisterForward">Đăng ký</a></li>
        
                 
                    </ul>
                  </div>

                  </div>
                </div>
              </div>
            <!-- MAIN NAVIGATION -->  
      </div>
    </div>
  </div>
 <%
	}
	else{
 %>
 <div id="header-row">
    <div class="container">
      <div class="row">
              <!--LOGO-->
              <div class="span3"><a class="brand" href="HomeForward"><img src="Template/Fontend/img/logo.jpg" height ="100px" width="100px"/></a></div>
              <!-- /LOGO -->

            <!-- MAIN NAVIGATION -->  
              <div class="span9">
                <div class="navbar  pull-right">
                  <div class="navbar-inner">
                    <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a>
                    <div class="nav-collapse collapse navbar-responsive-collapse">
                    <ul class="nav">
                        <li>
                        	<a href="ProfileUserForward"><img id="boderimg" alt="" src="Avatar/<%=session.getAttribute("sessionimage") %>"  >
                        	</a>
                        </li>
                     
                        <li class="dropdown">
                        <br/>
                          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=session.getAttribute("sessionname") %> <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                  <li><a href="LogoutController">Thoát</a></li>
                            </ul>

                        </li>
                        
                    </ul>
                  </div>

                  </div>
                </div>
              </div>
            <!-- MAIN NAVIGATION -->  
      </div>
    </div>
  </div>
  <% } %>
  

</body>
</html>
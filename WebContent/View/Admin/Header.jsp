<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="navbar" class="navbar navbar-default          ace-save-state">
		<div class="navbar-container ace-save-state" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="AdminForward" class="navbar-brand">
					<c:if test="${roleid == 1 }">
						<small>
							<i class="fa fa-leaf"></i>
							Trang Quản Trị Admin
						</small>
					</c:if><c:if test="${roleid == 3 }">
						<small>
							<i class="fa fa-leaf"></i>
							Quản Lý Biên Tập
						</small>
					</c:if>
				</a>
			</div>
			<div class="navbar-buttons navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue dropdown-modal">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle">
							<span class="user-info">
								<small>Xin chào,</small>
								<%=session.getAttribute("sessionname") %>
							</span>

							<i class="ace-icon fa fa-caret-down"></i>
						</a>

						<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

							<li class="divider"></li>

							<li>
								<a href="LogoutController">
									<i class="ace-icon fa fa-power-off"></i>
										Thoát
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
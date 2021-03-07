<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>User Profile Page - Ace Admin</title>

		<meta name="description" content="3 styles with inline editable feature" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<link rel="stylesheet" href="Template/Backend/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="Template/Backend/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<link rel="stylesheet" href="Template/Backend/assets/css/jquery-ui.custom.min.css" />
		<link rel="stylesheet" href="Template/Backend/assets/css/jquery.gritter.min.css" />
		<link rel="stylesheet" href="Template/Backend/assets/css/select2.min.css" />
		<link rel="stylesheet" href="Template/Backend/assets/css/bootstrap-datepicker3.min.css" />
		<link rel="stylesheet" href="Template/Backend/assets/css/bootstrap-editable.min.css" />

		<link rel="stylesheet" href="Template/Backend/assets/css/fonts.googleapis.com.css" />

		<link rel="stylesheet" href="Template/Backend/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="Template/Backend/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="Template/Backend/assets/css/ace-rtl.min.css" />

		<script src="Template/Backend/assets/js/ace-extra.min.js"></script>
		<style type="text/css">
			
		</style>
		

	</head>

	<body class="no-skin">
		<div id="navbar" class="navbar navbar-default          ace-save-state">
			<div class="navbar-container ace-save-state" id="navbar-container">

				<div class="navbar-header pull-left">
					<a href="ProfileUserForward" class="navbar-brand">
						<small>
							Trang cá nhân
						</small>
					</a>
				</div>

			</div><!-- /.navbar-container -->
		</div>


			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="HomeForward">Trang chủ</a>
							</li>
						</ul><!-- /.breadcrumb -->
					</div>

					<div class="page-content">

						<div class="page-header">
							<h1>
								Thông tin cá nhân
								
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="hr dotted"></div>

								<div>
									<div id="user-profile-1" class="user-profile row">
										<div class="col-xs-12 col-sm-3 center">
											
											<div>
											
												<span class="profile-picture">
													<img  class= "editable img-responsive" alt="" src="Avatar/<%=session.getAttribute("sessionimage") %>" width = "180px" height="200px" />
													
												</span>
												<br/>
												

												<div class="space-4"></div>

												<div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
													<div class="inline position-relative">
														<a href="#" class="user-title-label dropdown-toggle" data-toggle="dropdown">
															<i class="ace-icon fa fa-circle light-green"></i>
															&nbsp;
															<span class="white"><%=session.getAttribute("sessionname") %></span>
														</a>

													</div>
												</div>
											</div>
										
										<form action="UpdateImageUser" method="POST" enctype="multipart/form-data">
											<input type="file" name='file'>
											<input type="submit" value = "Cập nhật ảnh đại diện">
										</form>
											
										</div>
										

										<div class="col-xs-12 col-sm-9">
											<div class="center">
												

											<div class="space-12"></div>
											<div class="row">
											<div class="col-xs-12">
											
											
								<!-- Table here -->
											
											<div class="row">
											<div class="col-xs-10">
												<table id="simple-table" class="table  table-bordered table-hover">
													<thead>
														<tr>
															<th class="center">
																STT
															</th>
															<th class="center">
																Tên bài thi
															</th>
															<th class="center">
																Số điểm
															</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${listresult}" var = "list">
															<tr>
															<td class="center">
																${list.resultid}
															</td>
															<td class="center">
																${list.examinationname}
															</td>
															<td class="center">
																${list.score}/100
															</td>	
															</tr>
														</c:forEach>										
													</tbody>
												</table>
											</div>
										</div>
										<div class="space-20"></div>
										
										
							<!-- End Table -->
											
											
											<form class="form-horizontal" role="form" action="changePassword" method="POST">
												
												<div class="form-group">
													<div class="col-sm-10">
														<h3><b>Đổi mật khẩu</b></h3>
														<label class="block clearfix" style="color:red">
														<%=request.getAttribute("msgchangePass") !=null?request.getAttribute("msgchangePass"):" " %>
														</label>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> Mật khẩu cũ </label>
		
													<div class="col-sm-9">
														<input type="password" name="oldpassword"  placeholder="Nhập mật khẩu cũ" class="col-xs-10 col-sm-5" />
													</div>
													
												</div>
												<div class="space-4"></div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> Mật khẩu mới </label>
		
													<div class="col-sm-9">
														<input type="password" name="password" id="password"  placeholder="Nhập mật khẩu mới" class="col-xs-10 col-sm-5" />
													</div>
													
												</div>
												<div class="space-4"></div>
												
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> Nhập lại mật khẩu mới </label>
		
													<div class="col-sm-9">
														<input type="password"  name="confirm_password" id="confirm_password"   placeholder="Nhập lại mật khẩu mới" class="col-xs-10 col-sm-5" />
													</div>
													<div class="col-sm-9">
													<span id='message'></span>
													</div>
												</div>
												<div class="space-4"></div>
												
												<div class="clearfix form-actions">
													<div class="col-md-offset-3 col-md-9">
														<button class="btn btn-info" type="submit">
															<i class="ace-icon fa fa-check bigger-110"></i>
															Đổi mật khẩu
														</button>
													</div>
												</div>
											</form>
										</div>
									</div>
									<div class="space-20"></div>

											

											
									</div>
									</div>
									</div>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
				
		

			</div><!-- /.main-content -->
			<div class="footer">
				<div class="footer-inner">
					<div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">English</span>
							Application 
						</span>

						&nbsp; &nbsp;
						<span class="action-buttons">
							<a href="#">
								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-rss-square orange bigger-150"></i>
							</a>
						</span>
					</div>
				</div>
			</div>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="Template/Backend/assets/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='Template/Backend/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="Template/Backend/assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->
		<script src="Template/Backend/assets/js/jquery-ui.custom.min.js"></script>
		<script src="Template/Backend/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="Template/Backend/assets/js/jquery.gritter.min.js"></script>
		<script src="Template/Backend/assets/js/bootbox.js"></script>
		<script src="Template/Backend/assets/js/jquery.easypiechart.min.js"></script>
		<script src="Template/Backend/assets/js/bootstrap-datepicker.min.js"></script>
		<script src="Template/Backend/assets/js/jquery.hotkeys.index.min.js"></script>
		<script src="Template/Backend/assets/js/bootstrap-wysiwyg.min.js"></script>
		<script src="Template/Backend/assets/js/select2.min.js"></script>
		<script src="Template/Backend/assets/js/spinbox.min.js"></script>
		<script src="Template/Backend/assets/js/bootstrap-editable.min.js"></script>
		<script src="Template/Backend/assets/js/ace-editable.min.js"></script>
		<script src="Template/Backend/assets/js/jquery.maskedinput.min.js"></script>

		<!-- ace scripts -->
		<script src="Template/Backend/assets/js/ace-elements.min.js"></script>
		<script src="Template/Backend/assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
			<script type="text/javascript">
		
			$('#password, #confirm_password').on('keyup', function () {
			    if ($('#password').val() == $('#confirm_password').val()) {
			        $('#message').html('Mật Khẩu Trùng khớp').css('color', 'green');
			    } else 
			        $('#message').html('Mật Khẩu Không trùng khớp').css('color', 'red');
			});
			
			$("#upfile1").click(function () {
			    $("#file1").trigger('click');
			});
		</script>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Thêm danh sách bài thi</title>

		<meta name="description" content="Static &amp; Dynamic Tables" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="Template/Backend/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="Template/Backend/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->

		<!-- text fonts -->
		<link rel="stylesheet" href="Template/Backend/assets/css/fonts.googleapis.com.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="Template/Backend/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="Template/Backend/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="Template/Backend/assets/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="Template/Backend/assets/js/ace-extra.min.js"></script>
		
		<script src="Template/Backend/assets/js/Jquery3.5.1.js"></script>
		<script>
    		$(document)
           		 .ready(
                    function() {
                        //add more file components if Add is clicked
                        $('#addFile')
                                .click(
                                        function() {
                                            var fileIndex = $('#fileTable tr')
                                                    .children().length - 1;
                                            $('#fileTable')
                                                    .append(
                                                            '<tr><td>'
                                                                    + '   <input type="file" name="files['+ fileIndex +']" />'
                                                                    + '</td></tr>');
                                        });
 
                    });
		</script>
</head>
<body class="no-skin">
	<!-- Header -->
	<jsp:include page="Header.jsp"></jsp:include>
	<!--End Header -->
	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try{ace.settings.loadState('main-container')}catch(e){}
		</script>
	<!-- Menu -->
	<jsp:include page="Menu.jsp"></jsp:include>
	<!-- End Menu -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="AdminForward">Trang chủ</a>
							</li>
							<li>
								<a href="#">Quản lý đề thi</a>
							</li>
							
						</ul><!-- /.breadcrumb -->

					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
							<c:if test="${msglistexammanage!=null}">
								<h4 class="pink">
									<i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
									<a class="green" data-toggle="modal"> ${msglistexammanage} </a>
								</h4>
								<div class="hr hr-18 dotted hr-double"></div>
							</c:if>
							
							 	<label class="block clearfix" style="color:red">
									<%=request.getAttribute("msguploadaudioimage") !=null?request.getAttribute("msguploadaudioimage"):" " %>
								</label>
							
								<div class="row">
									<div class="col-xs-12">
										<table id="simple-table" class="table  table-bordered table-hover">
											<thead>
												<tr>
													<th class="center">
														ID
													</th>
													<th class="center">
														Tên đề thi
													</th>
													<th class="center">
														Tên hình đề thi 
													</th>
													<th class="center">
														Thời gian làm bài 
													</th>
													<th class="center">
														Xóa đề thi
													</th>
													<th class="center">
														Thêm câu hỏi đề thi
													</th>
													
													<th class="center">
														Check câu hỏi đề thi
													</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${listexammanage}" var = "list">
													<tr>
													<td class="center">
														${list.examinationid}
													</td>
													<td class="center">
														${list.examinationname}
													</td>
													<td class="center">
														${list.examinationimage}
													</td>
													<td class="center">
														${list.time} phút
													</td>
													<td class="center">
														<a class="red" href="DeleteExamination?examinationid=${list.examinationid}">
															<i class="ace-icon fa fa-trash-o bigger-130"></i>
														</a>
													</td>
													
													<td class="center">
														<a class="green" href="Uploadexamquestion?examinationid=${list.examinationid}">
															<i class="ace-icon fa fa-pencil bigger-130"></i>
														</a>
													</td>
												
													<td class="center">
														<ul class="list-unstyled">
															<c:if test="${list.checkquestion==1}">
																<li>
																	<i class="ace-icon fa fa-check-square-o"></i>
																</li>
															</c:if>
															<c:if test="${list.checkquestion==0}">
																<li>
																	<i class="ace-icon fa fa-square-o"></i>
															</c:if>
														</ul>
													</td>
													
													
												</tr>
												</c:forEach>												
											</tbody>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div>
											<ul class="pagination">
												
												<c:if test="${numberpage ==1 && numberpage == maxpageid}">
													<li class="disabled"><a href="#">Prev</a></li>
													<li class="disabled"><a href="#">Next</a></li>
												</c:if>
												
												<c:if test="${numberpage ==1 && numberpage != maxpageid}">
													<li class="active"><a href="#">Prev</a></li>
													<li><a href="Listexammanage?pageid=${numberpage +1}">Next</a></li>
												</c:if>
												
												<c:if test="${numberpage == maxpageid  && numberpage !=1}">
													<li><a href="Listexammanage?pageid=${numberpage -1}">Prev</a></li>
													<li class="active"><a href="#">Next</a></li>
												</c:if>
												
												<c:if test="${numberpage >1 && numberpage < maxpageid}">
													<li><a href="Listexammanage?pageid=${numberpage -1}">Prev</a></li>
													<li><a href="Listexammanage?pageid=${numberpage +1}">Next</a></li>
												</c:if>

											
											</ul>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
								
										<button type="button" class="btn btn-white btn-warning btn-bold" data-toggle="modal" data-target="#myModal">
											<i class="ace-icon fa fa-floppy-o bigger-120 blue"></i>
											Thêm đề thi
										</button>
										
										<button type="button" class="btn btn-white btn-warning btn-bold" data-toggle="modal" data-target="#myModalAudio">
											<i class="ace-icon fa fa-floppy-o bigger-120 blue"></i>
											Thêm audio và hình ảnh đề thi
										</button>
										
									</div>
								</div>
							</div>
						</div>
					</div>
			</div>
		</div>
		</div><!-- /.main-content -->
		<!-- Footer -->
		<jsp:include page="Footer.jsp"></jsp:include>
		<!-- End Footer -->
		

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
	


		<!-- Modal Thêm đề thi-->
		<div id="myModal" class="modal fade" role="dialog">
		  <div class="modal-dialog">
		  
		  
		  
			<form action="Insertexamname?userid=${userid}" method="POST">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        	<h4 class="modal-title">Thêm đề thi</h4>
		       
		       
		      </div>
		      <div class="modal-body">
		      <div class="col-xs-12">
		        <div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Nhập Tên </label>
					<div class="col-sm-9">
							<input type="text" id="form-field-1-1" placeholder="Nhập tên đề thi" class="form-control" name ="examinationname"/>
					</div>
					<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Nhập thời gian làm bài (phút) </label>
					<div class="col-sm-9">
							<input type="text" id="form-field-1-1" placeholder="Nhập thời gian làm bài (phút)" class="form-control" name ="time"/>
					</div>
				</div>
		      </div>
		      </div>
		      <br/>
		      <div class="modal-footer">
		      	<button class="btn btn-info" type="submit">
					<i class="ace-icon fa fa-check bigger-110"></i>
					Thêm tên đề thi
				</button>
		        <button type="button" class="btn btn-default" data-dismiss="modal">Thoát</button>
		        
		      </div>
		    </div>
			</form>
		  </div>
		</div>
		
		
		
		
		
		<!-- Modal thêm Audio Hình ảnh -->
		<div id="myModalAudio" class="modal fade" role="dialog">
		  <div class="modal-dialog">
		  
		  
		  
			<form action="UploadAudioImageExam" method="POST" enctype="multipart/form-data">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        	<h4 class="modal-title">Thêm Audio và hình ảnh đề thi</h4>
		       
		       
		      </div>
		      <div class="modal-body">
		      <div class="col-xs-12">
		        <div class="form-group">
					<table id="fileTable">
						<tr>
							<td><input name="files[0]" type="file" /></td>
						</tr>
						<tr>
							<td><input name="files[1]" type="file" /></td>
						</tr>						
					</table>
	
					<div class="space"></div>
				</div>
		      </div>
		      </div>
		      <br/>
		      <div class="modal-footer">
		      	<input id ="addFile" type="button" value="Thêm file" class="btn btn-info"/>
				<input type="submit" value="Thêm Audio và hình ảnh vào" class="btn btn-info"/>
		        <button type="button" class="btn btn-default" data-dismiss="modal">Thoát</button>
		        
		      </div>
		    </div>
			</form>
		  </div>
		</div>
	
		  


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
		<script src="Template/Backend/assets/js/jquery.dataTables.min.js"></script>
		<script src="Template/Backend/assets/js/jquery.dataTables.bootstrap.min.js"></script>
		<script src="Template/Backend/assets/js/dataTables.buttons.min.js"></script>
		<script src="Template/Backend/assets/js/buttons.flash.min.js"></script>
		<script src="Template/Backend/assets/js/buttons.html5.min.js"></script>
		<script src="Template/Backend/assets/js/buttons.print.min.js"></script>
		<script src="Template/Backend/assets/js/buttons.colVis.min.js"></script>
		<script src="Template/Backend/assets/js/dataTables.select.min.js"></script>

		<!-- ace scripts -->
		<script src="Template/Backend/assets/js/ace-elements.min.js"></script>
		<script src="Template/Backend/assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
				//initiate dataTables plugin
				var myTable = 
				$('#dynamic-table')
				//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
				.DataTable( {
					bAutoWidth: false,
					"aoColumns": [
					  { "bSortable": false },
					  null, null,null, null, null,
					  { "bSortable": false }
					],
					"aaSorting": [],
					
					
					//"bProcessing": true,
			        //"bServerSide": true,
			        //"sAjaxSource": "http://127.0.0.1/table.php"	,
			
					//,
					//"sScrollY": "200px",
					//"bPaginate": false,
			
					//"sScrollX": "100%",
					//"sScrollXInner": "120%",
					//"bScrollCollapse": true,
					//Note: if you are applying horizontal scrolling (sScrollX) on a ".table-bordered"
					//you may want to wrap the table inside a "div.dataTables_borderWrap" element
			
					//"iDisplayLength": 50
			
			
					select: {
						style: 'multi'
					}
			    } );
			
				
				
				$.fn.dataTable.Buttons.defaults.dom.container.className = 'dt-buttons btn-overlap btn-group btn-overlap';
				
				new $.fn.dataTable.Buttons( myTable, {
					buttons: [
					  {
						"extend": "colvis",
						"text": "<i class='fa fa-search bigger-110 blue'></i> <span class='hidden'>Show/hide columns</span>",
						"className": "btn btn-white btn-primary btn-bold",
						columns: ':not(:first):not(:last)'
					  },
					  {
						"extend": "copy",
						"text": "<i class='fa fa-copy bigger-110 pink'></i> <span class='hidden'>Copy to clipboard</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "csv",
						"text": "<i class='fa fa-database bigger-110 orange'></i> <span class='hidden'>Export to CSV</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "excel",
						"text": "<i class='fa fa-file-excel-o bigger-110 green'></i> <span class='hidden'>Export to Excel</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "pdf",
						"text": "<i class='fa fa-file-pdf-o bigger-110 red'></i> <span class='hidden'>Export to PDF</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "print",
						"text": "<i class='fa fa-print bigger-110 grey'></i> <span class='hidden'>Print</span>",
						"className": "btn btn-white btn-primary btn-bold",
						autoPrint: false,
						message: 'This print was produced using the Print button for DataTables'
					  }		  
					]
				} );
				myTable.buttons().container().appendTo( $('.tableTools-container') );
				
				//style the message box
				var defaultCopyAction = myTable.button(1).action();
				myTable.button(1).action(function (e, dt, button, config) {
					defaultCopyAction(e, dt, button, config);
					$('.dt-button-info').addClass('gritter-item-wrapper gritter-info gritter-center white');
				});
				
				
				var defaultColvisAction = myTable.button(0).action();
				myTable.button(0).action(function (e, dt, button, config) {
					
					defaultColvisAction(e, dt, button, config);
					
					
					if($('.dt-button-collection > .dropdown-menu').length == 0) {
						$('.dt-button-collection')
						.wrapInner('<ul class="dropdown-menu dropdown-light dropdown-caret dropdown-caret" />')
						.find('a').attr('href', '#').wrap("<li />")
					}
					$('.dt-button-collection').appendTo('.tableTools-container .dt-buttons')
				});
			
				////
			
				setTimeout(function() {
					$($('.tableTools-container')).find('a.dt-button').each(function() {
						var div = $(this).find(' > div').first();
						if(div.length == 1) div.tooltip({container: 'body', title: div.parent().text()});
						else $(this).tooltip({container: 'body', title: $(this).text()});
					});
				}, 500);
				
				
				
				
				
				myTable.on( 'select', function ( e, dt, type, index ) {
					if ( type === 'row' ) {
						$( myTable.row( index ).node() ).find('input:checkbox').prop('checked', true);
					}
				} );
				myTable.on( 'deselect', function ( e, dt, type, index ) {
					if ( type === 'row' ) {
						$( myTable.row( index ).node() ).find('input:checkbox').prop('checked', false);
					}
				} );
			
			
			
			
				/////////////////////////////////
				//table checkboxes
				$('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
				
				//select/deselect all rows according to table header checkbox
				$('#dynamic-table > thead > tr > th input[type=checkbox], #dynamic-table_wrapper input[type=checkbox]').eq(0).on('click', function(){
					var th_checked = this.checked;//checkbox inside "TH" table header
					
					$('#dynamic-table').find('tbody > tr').each(function(){
						var row = this;
						if(th_checked) myTable.row(row).select();
						else  myTable.row(row).deselect();
					});
				});
				
				//select/deselect a row when the checkbox is checked/unchecked
				$('#dynamic-table').on('click', 'td input[type=checkbox]' , function(){
					var row = $(this).closest('tr').get(0);
					if(this.checked) myTable.row(row).deselect();
					else myTable.row(row).select();
				});
			
			
			
				$(document).on('click', '#dynamic-table .dropdown-toggle', function(e) {
					e.stopImmediatePropagation();
					e.stopPropagation();
					e.preventDefault();
				});
				
				
				
				//And for the first simple table, which doesn't have TableTools or dataTables
				//select/deselect all rows according to table header checkbox
				var active_class = 'active';
				$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
					var th_checked = this.checked;//checkbox inside "TH" table header
					
					$(this).closest('table').find('tbody > tr').each(function(){
						var row = this;
						if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
						else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
					});
				});
				
				//select/deselect a row when the checkbox is checked/unchecked
				$('#simple-table').on('click', 'td input[type=checkbox]' , function(){
					var $row = $(this).closest('tr');
					if($row.is('.detail-row ')) return;
					if(this.checked) $row.addClass(active_class);
					else $row.removeClass(active_class);
				});
			
				
			
				/********************************/
				//add tooltip for small view action buttons in dropdown menu
				$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				
				//tooltip placement on right or left
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					//var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
				
				
				
				
				/***************/
				$('.show-details-btn').on('click', function(e) {
					e.preventDefault();
					$(this).closest('tr').next().toggleClass('open');
					$(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
				});
				/***************/
				
				
				
				
				
				/**
				//add horizontal scrollbars to a simple table
				$('#simple-table').css({'width':'2000px', 'max-width': 'none'}).wrap('<div style="width: 1000px;" />').parent().ace_scroll(
				  {
					horizontal: true,
					styleClass: 'scroll-top scroll-dark scroll-visible',//show the scrollbars on top(default is bottom)
					size: 2000,
					mouseWheelLock: true
				  }
				).css('padding-top', '12px');
				*/
			
			
			})
		</script>
</body>
</html>
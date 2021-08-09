<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>관리자 페이지</title>

    <!-- Custom fonts for this template-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../css/sb-admin-2.min.css" rel="stylesheet">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
    <!-- Core plugin JavaScript-->
    <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
	<script type="text/javascript">
	$(function() {	
		var mainChartLabel = ${mainChartLabel};
		var purchaseChartData = ${purchaseChartData};
		var postChartData = ${postChartData};
		var accountChartData = ${accountChartData};
		var complaintChartData = ${complaintChartData};
		
		
		var txPieChartData = ${txPieChartData};
		
		mainChartUpdate(mainChartLabel,postChartData,purchaseChartData,accountChartData,complaintChartData);
		txPieChartUpdate(txPieChartData);

		$.datepicker.setDefaults($.datepicker.regional['ko']); 
		$("#startDate").datepicker({
			maxDate: 0,
			 onClose: function( selectedDate ) {
                 $("#endDate").datepicker( "option", "minDate", selectedDate );
			 }
		});
		$("#endDate").datepicker({
			maxDate: 0,
			onClose: function( selectedDate ) {
                $("#startDate").datepicker( "option", "maxDate", selectedDate );
            }  
		});
		$("#txChartForm").on("submit", function(event){
			event.preventDefault();	
			var selectType = $("#txChartSelect").val();
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			if(startDate.length == 0){
				alert("시작 날짜를 선택하세요.");
				$("#startDate").focus();
			} else if(endDate.length == 0){
				alert("마지막 날짜를 선택하세요.");
				$("#endDate").focus();
			}else if(selectType=="default"){
				alert("단위를 선택하세요.");
				$("#txChartSelect").focus();
			}else {
				$.ajax({
					type: "get",
					url: "/admin/txChartChange",
					data: {
						startDate : startDate,
						endDate: endDate,
						selectType: selectType
					}, //data
					dataType: "json",
					success: function(data) {
						txChartUpdate(data.txChartLabel,data.txChartData);
					}, //success
					error: function(xhr, status, error) {
						$("#result").append(error);
						$("#result").append(status);
					} //error
				});//ajax
			}//if_else
		});//on
	});//end ready
	
	</script>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-dark sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/admin">
                <div class="sidebar-brand-icon">
      <!--               <i class="fas fa-laugh-wink"></i> -->
                <img class="img-profile" src="/Dong-Dong/images/util/DongDonglogo2.png" width="80">
                </div>
                <div class="sidebar-brand-text mx-3">관리자<br>페이지</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="/admin">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>현황판</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
           	신고 관리
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link" href="/admin/complaintMember">
                    <i class="fas fa-fw fa-user-cog"></i>
                    <span>회원</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/complaintPost">
                    <i class="far fa-fw fa-file-alt"></i>
                    <span>게시글</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/complaintComment">
                	<i class="fas fa-fw fa-list"></i>
                    <span>댓글</span>
                </a>
            </li>
        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">
                        
                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">관리자</span>
                                <img class="img-profile rounded-circle"
                                    src="/Dong-Dong/images/profile/default_userImg.PNG">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                   	로그아웃
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">일일 통계</h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                              	글 등록</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${dailyPostWrite}건</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                              	 거래완료</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${dailyPurchase}건</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                              	 회원가입</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${dailyAccount}명</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Pending Requests Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-secondary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-secondary text-uppercase mb-1">
                                                	신고</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${dailyComplaint}건</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-comments fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Content Row -->

                    <div class="row">

                        <!-- Area Chart -->
                        <div class="col-xl-8 col-lg-7">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">TX 종류별 통계</h6>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
                                    <div class="chart-area">
                                        <canvas id="myAreaChart"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Pie Chart -->
                        <div class="col-xl-4 col-lg-5">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">실시간 TX 비율</h6>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
                                    <div class="chart-pie pt-4 pb-2">
                                        <canvas id="txPieChart"></canvas>
                                    </div>
                                    <div class="mt-4 text-center small">
                                        <span class="mr-2">
                                            <i class="fas fa-circle text-primary"></i> 글 등록
                                        </span>
                                        <span class="mr-2">
                                            <i class="fas fa-circle text-success"></i> 거래
                                        </span>
                                        <span class="mr-2">
                                            <i class="fas fa-circle text-info"></i> 가입
                                        </span>
                                        <span class="mr-2">
                                            <i class="fas fa-circle text-secondary"></i> 신고
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

					<!-- Content Row -->

                    <div class="row">

                        <!-- Area Chart -->
                        <div class="col-xl-12 col-lg-12">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">TX 통계 조회</h6>
                                    <div class="date-select-div">
	                                    <form action="" id="txChartForm">
  											<div class="form-row">
  												<div class="col-xs-3">
	                                        		<input type="text" class="datepicker form-control" id="startDate" readonly="readonly"> 
	                                        	</div> ~ 
	                                        	<div class="col-xs-3">
	                                        		<input type="text" class="datepicker form-control" id="endDate" readonly="readonly">
	                                        	</div>
	                                        	<div class="col-xs-3">
	                                        		<select class="form-control" id="txChartSelect">
			                                        	<option value="default">단위</option>
			                                        	<option value="H">시</option>
			                                        	<option value="D">일</option>
			                                        	<option value="M">월</option>
	                                        		</select>
	                                        	</div>
	                                        	<div class="col-xs-3">
	                                        		<input class="form-control" type="submit" value="선택">
	                                        	</div>
	                                        </div>
	                                    </form>
                                    </div>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
                                    <div class="chart-area">
                                        <canvas id="txChart"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Content Row -->
                    <div class="row">

                        <!-- Content Column -->
                        <div class="col-lg-12 mb-12">

                           

                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>&copy; DongDong Market Inc.</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">관리자 로그아웃</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">현재 관리자 세션을 종료하고 메인으로 이동합니다.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                    <a class="btn btn-primary" href="/admin/logout">로그아웃</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
	
    <!-- Core plugin JavaScript-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="../js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <!-- Page level custom scripts -->
    <script src="../js/adminPageScript/chart-area.js"></script>
    <script src="../js/adminPageScript/chart-area-tx.js"></script>
    <script src="../js/adminPageScript/chart-pie.js"></script>

</body>

</html>
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

    <title>신고 세부 정보</title>

    <!-- Custom fonts for this template -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap4.min.css"  rel="stylesheet">
	
	<!-- Bootstrap core JavaScript-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="../js/adminPageScript/datatables.js"></script>
    
    <script type="text/javascript">
			$(document).ready(function(){
				$("form").on("submit",function(event){
					var saType = $("#saType").val();
					var saDate = $("#saDate").val();		
					
					if(saType.length == 4){
						alert("제재할 카테고리를 선택하세요.");
						$("#saType").focus();
						event.preventDefault();	
					} else if(saDate.length == 4){
						alert("제재 기간 선택을 정하세요");
						$("#saDate").focus();
						event.preventDefault();	
					} 
				});
			});
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
            <li class="nav-item">
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
            <li class="nav-item active">
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
                    <h1 class="h3 mb-2 text-gray-800">신고번호 ${coDTO.coNum}. 세부 내용</h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	
                            	
                            	<div class="complaintDetail card w-90 m-4 p-3 bg-white rounded">
	                                <h4 class="m-0 font-weight-bold text-primary">신고자의 설명</h4>
		                            <div class="row">
		                            	<div class="col-md-12"><br>
			                        		<h4>${coDTO.coContent}</h4><br>
		                           		</div>
	                            	</div>
                            	</div>
                            	
                            	<div class="memberDetail card w-90 m-4 p-3 bg-white rounded">
									<h4 class="m-0 font-weight-bold text-primary">작성자 정보</h4><br>
                            		 <div class="row">
			                            <div class="col-md-4">
											<h4 class="m-0 font-weight-bold text-dark">프로필 이미지</h4>
		                            		<img id="mainImage" class="img-fluid rounded mb-4 mb-lg-0" src="/Dong-Dong/images/profile/${mDTO.userimage}" width="400px"><br>
	        							</div>
			                            <div class="col-md-8">
	        								<h4 class="m-0 font-weight-bold text-dark">아이디</h4>
		        							<h4 class="" style="line-height: 1.5;">${mDTO.nickName}</h4><br>
		        						</div>
	        						</div>
								</div>
								
								
								<div class="sanctionDetail card w-90 m-4 p-3 bg-white rounded">
									<h4 class="m-0 font-weight-bold text-primary">제재 내역 정보</h4><br>
									
									<c:if test="${isSanctioned==true}">
										<font class="text-danger">현재 계정 상태 : 정지중 (${endSanctionDate}까지)</font><br>
									</c:if>
									<c:if test="${isSanctioned==false}">
										<font class="text-primary">현재 계정 상태 : 정상</font><br>
									</c:if>
									<c:if test="${! empty sanctionList}">
										<font class="text-danger">제재 이력이 ${sanctionList.size()}건 있는 계정입니다.</font><br>
										<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		                                    <thead>
		                                        <tr>
		                                            <th>신고 번호</th>
		                                            <th>처리내용</th>
		                                            <th>처리일시</th>
		                                            <th>제재기간</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody>
		                                    	<c:forEach var="saDTO" items="${sanctionList}" varStatus="stat">
		                                        <tr>
		                                            <td>${saDTO.saNum}</td>
		                                            <td>
		                                           		<c:choose>
		                                           			<c:when test="${saDTO.saType==1}">
		                                           				불법사이트 홍보/계정 해킹 시도
		                                           			</c:when>
		                                           			<c:when test="${saDTO.saType==2}">
		                                           				불법 거래 / 범죄 행위
		                                           			</c:when>
		                                           			<c:when test="${saDTO.saType==3}">
		                                           				폭력젹인 언어, 욕설, 비속어 사용
		                                           			</c:when>
		                                           			<c:when test="${saDTO.saType==4}">
		                                           				부적절한 사진/게시글 게시
		                                           			</c:when>
		                                           			<c:when test="${saDTO.saType==5}">
		                                           				반복적 게시글/댓글 등록 행위
		                                           			</c:when>
		                                           		</c:choose>
		                                            
		                                            </td>
		                                            <td>${saDTO.startDate}</td>
		                                            <td>${saDTO.endDate}</td>
		                                        </tr>
		                                        </c:forEach>
		                                    </tbody>
	                                	</table><br>			
									</c:if>
									<c:if test="${empty sanctionList}">
										<font class="text-primary">제재 이력이 없는 계정입니다.</font><br><br>
									</c:if>
								</div>
                               
                               
                            	<div class="scantionChoice card w-90 m-4 p-3 bg-white rounded">
	                                <h4 class="m-0 font-weight-bold text-primary">제재 결과 입력</h4><br>
                               		<c:if test="${coDTO.coYn=='n'}">
	                                <form action="/admin/complaintEnd" method="post">
	                                	<input type="hidden" name="coNum" value="${coDTO.coNum}">
		                                <div class="row">
											<div class="col-md-12 col-sm-8 mb-3">
												<select class="form-control" aria-label="Default select example" name="saType" id="saType">
								  					<option value="none" selected>제재할 대상의 종류를 선택하세요.</option>
								  					<option value="0">문제 없음</option>
								  					<option value="1">불법 사이트 홍보나 계정 해킹 시도</option>
													<option value="2">불법적인 거래나 범죄 행위</option>
													<option value="3">폭력적인 언어, 욕설, 비속어, 은어 등의 사용</option>
													<option value="4">타인에게 불쾌감, 혐오감을 일으킬 수 있는 게시글이나 이미지 게시</option>
													<option value="5">반복적으로 댓글이나 게시글을 등록하는 등 서비스 방해</option>
												</select>
											</div>
								  		</div>
								  		<div class="row">
											<div class="col-md-12 col-sm-8 mb-3">
												<select class="form-control" aria-label="Default select example" name="saDate" id="saDate">
								  					<option value="none" selected>제재 기간을 선택하세요.</option>
								  					<option value="0">정지 기간 없음</option>
								  					<option value="1">1일</option>
													<option value="3">3일</option>
													<option value="7">7일</option>
													<option value="14">14일</option>
													<option value="30">30일</option>
													<option value="999">영구</option>
												</select>
											</div>
								  		</div>
										<input type="submit" value="신고 처리" class="btn btn-danger" style="align-items: right">
								  	</form>
								  	</c:if>
								  	<c:if test="${coDTO.coYn=='y'}">
								  		<br><h4 class="text-danger">신고 처리 완료 (${coDTO.endDate})</h4>
								  	</c:if>
								  	</div>
                            	</div>
                            	<br><br>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
				<br><br>
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
</body>

</html>
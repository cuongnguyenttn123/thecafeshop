<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>The Coffe Shop || </title>


<!-- plugins:css -->
<link rel="stylesheet"
	href="../resouces/node_modules/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="../resouces/node_modules/simple-line-icons/css/simple-line-icons.css">
<link rel="stylesheet"
	href="../resouces/node_modules/flag-icon-css/css/flag-icon.min.css">
<link rel="stylesheet" href="../resouces/node_modules/perfect-scrollbar/css/perfect-scrollbar.css">
<!-- endinject -->
<!-- plugin css for this page -->
<link rel="stylesheet"
	href="../resouces/node_modules/font-awesome/css/font-awesome.min.css" />
  <link rel="stylesheet" href="../resouces/node_modules/jvectormap/jquery-jvectormap.css" />
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet" href="../resouces/css/style.css">
<!-- endinject -->
<link rel="shortcut icon" href="../resouces/images/favicon.png" />

<!-- my-style-css -->
<link rel="stylesheet" href="../resouces/css/my-style-v2.css">
<!-- plugins:js -->
<script src="../resouces/node_modules/jquery/dist/jquery.min.js"></script>
</head>

<body class="horizontal-menu">
	<div class="container-scroller">
		<!-- partial:../resouces/partials/_navbar.html -->
		<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
			<div class="text-center navbar-brand-wrapper">
				<a class="navbar-brand brand-logo logo" href="index.html"><img
					src="../resouces/images/logo-full.png" alt="logo"></a> <a
					class="navbar-brand brand-logo-mini logo" href="index.html"><img
					src="../resouces/images/logo.png" alt="logo"></a>
			</div>
			<div class="navbar-menu-wrapper d-flex align-items-center">
				<span class="icon-heart icons"></span>
				<p class="page-name d-none d-lg-block">
					Chào,
					<c:out value="${employee.getName()}" />
				</p>
				<ul class="navbar-nav ml-lg-auto">
					<li class="nav-item">
						<form class="mt-2 mt-md-0 d-none d-lg-block search-input">
							<div class="input-group">
								<span class="input-group-addon d-flex align-items-center"><i
									class="icon-magnifier icons"></i></span> <input type="text"
									class="form-control" placeholder="Tìm kiếm...">
							</div>
						</form>
					</li>
					<li class="nav-item dropdown mail-dropdown"><a
						class="nav-link count-indicator" id="MailDropdown" href="#"
						data-toggle="dropdown"> <i class="icon-envelope-letter icons"></i>
							<span class="count bg-danger"></span>
					</a>
						<div
							class="dropdown-menu navbar-dropdown mail-notification dropdownAnimation"
							aria-labelledby="MailDropdown">
							<a class="dropdown-item" href="#">
								<div class="sender-img">
									<img src="images/faces/face6.jpg" alt=""> <span
										class="badge badge-success">&nbsp;</span>
								</div>
								<div class="sender">
									<p class="Sende-name">Thuận Nguyễn</p>
									<p class="Sender-message">Đặt bàn số 8, ngày 13/9/2018, lúc
										8h30p.</p>
								</div>
							</a> <a class="dropdown-item" href="#">
								<div class="sender-img">
									<img src="images/faces/face2.jpg" alt=""> <span
										class="badge badge-success">&nbsp;</span>
								</div>
								<div class="sender">
									<p class="Sende-name">An Hà</p>
									<p class="Sender-message">Đặt bàn số 12, ngày 18/9/2018,
										lúc 9h30p.</p>
								</div>
							</a> <a class="dropdown-item" href="#">
								<div class="sender-img">
									<img src="images/faces/face3.jpg" alt=""> <span
										class="badge badge-primary">&nbsp;</span>
								</div>
								<div class="sender">
									<p class="Sende-name">Tiến Trần</p>
									<p class="Sender-message">Đặt bàn số 22, ngày 18/9/2018,
										lúc 9h30p.</p>
								</div>
							</a> <a class="dropdown-item" href="#">
								<div class="sender-img">
									<img src="images/faces/face4.jpg" alt=""> <span
										class="badge badge-warning">&nbsp;</span>
								</div>
								<div class="sender">
									<p class="Sende-name">Thiện Bùi</p>
									<p class="Sender-message">Đặt bàn số 12, ngày 18/9/2018,
										lúc 9h30p.</p>
								</div>
							</a> <a href="#" class="dropdown-item view-all">Xem thêm</a>
						</div></li>
					<li class="nav-item dropdown notification-dropdown"><a
						class="nav-link count-indicator" id="notificationDropdown"
						href="#" data-toggle="dropdown"> <i class="icon-speech icons"></i>
							<span class="count"></span>
					</a>
						<div
							class="dropdown-menu navbar-dropdown preview-list notification-drop-down dropdownAnimation"
							aria-labelledby="notificationDropdown">
							<a class="dropdown-item preview-item">
								<div class="preview-thumbnail">
									<div class="preview-icon">
										<i class="icon-speech mx-0"></i>
									</div>
								</div>
								<div class="preview-item-content">
									<p class="preview-subject">Tiến Trần</p>
									<p class="font-weight-light small-text">Chào ad, mình muốn
										đặt bàn cho 4 người ngày mai 12h30p</p>
								</div>
							</a> <a class="dropdown-item preview-item">
								<div class="preview-thumbnail">
									<div class="preview-icon">
										<i class="icon-speech mx-0"></i>
									</div>
								</div>
								<div class="preview-item-content">
									<p class="preview-subject">Tiến Trần</p>
									<p class="font-weight-light small-text">Chào ad, mình muốn
										đặt bàn cho 4 người ngày mai 12h30p</p>
								</div>
							</a> <a class="dropdown-item preview-item">
								<div class="preview-thumbnail">
									<div class="preview-icon">
										<i class="icon-speech mx-0"></i>
									</div>
								</div>
								<div class="preview-item-content">
									<p class="preview-subject">Tiến Trần</p>
									<p class="font-weight-light small-text">Chào ad, mình muốn
										đặt bàn cho 4 người ngày mai 12h30p</p>
								</div>
							</a>
						</div></li>
					<li class="nav-item lang-dropdown d-none d-sm-block"><a
						class="nav-link" href="#">
							<p class="mb-0">
								Vietnamese <i class="flag-icon flag-icon-vn"></i>
							</p>
					</a></li>
					<li class="nav-item d-none d-sm-block profile-img"><a
						class="nav-link profile-image" href="#"> <img
							src="../resouces/images/faces/face4.jpg" alt="profile-img">
							<span class="online-status online bg-success"></span>
					</a></li>
				</ul>
				<button
					class="navbar-toggler navbar-toggler-right d-lg-none align-self-center ml-auto"
					type="button" data-toggle="offcanvas">
					<span class="icon-menu icons"></span>
				</button>
			</div>
		</nav>
		
		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<div class="row row-offcanvas row-offcanvas-right">
				<!-- partial:../resouces/partials/_sidebar.html -->
				<nav class="sidebar sidebar-offcanvas" id="sidebar">
					<ul class="nav">
						<li class="nav-item"><a class="nav-link collapsed"
							href="/admin/index"> <i
								class="icon-home menu-icon"></i> <span class="menu-title">TRANG
									CHỦ</span>
						</a></li>
						<li class="nav-item"><a class="nav-link collapsed"
							data-toggle="collapse" href="#ui-basic" aria-expanded="false"
							aria-controls="ui-basic"> <i class="icon-chart menu-icon"></i>
								<span class="menu-title">THỐNG KÊ DOANH THU </span>
						</a>
							<div class="collapse" id="ui-basic" style="">
								<ul class="nav flex-column sub-menu">
									<li class="nav-item"><a class="nav-link"
										href="/admin/statistic-profit"> <span
											class="menu-title">Thu chi</span>
									</a></li>
									<li class="nav-item"><a class="nav-link"
										href="pages/icons/font-awesome.html"> <span
											class="menu-title">Sản phẩm bán ra</span>
									</a></li>
									<li class="nav-item"><a class="nav-link"
										href="pages/icons/font-awesome.html"> <span
											class="menu-title">Hóa đơn</span>
									</a></li>
								</ul>
							</div></li>
						<li class="nav-item"><a class="nav-link collapsed"
							data-toggle="collapse" href="#ui-basic1" aria-expanded="false"
							aria-controls="ui-basic1"> <i class="icon-handbag menu-icon"></i>
								<span class="menu-title">QUẢN LÝ KHO </span>
						</a>
							<div class="collapse" id="ui-basic1" style="">
								<ul class="nav flex-column sub-menu">
									<li class="nav-item"><a class="nav-link"
										href="/admin/warehouse-product"> <span
											class="menu-title">Thức ăn- Đồ uống tồn kho</span>
									</a></li>
									<li class="nav-item"><a class="nav-link"
										href="/admin/warehouse-material"> <span
											class="menu-title">Nguyên liệu tồn kho</span>
									</a></li>
									<li class="nav-item"><a class="nav-link"
										href="/admin/warehouse-import-material"> <span
											class="menu-title">Phiếu nhập nguyên liệu</span>
									</a></li>
									<li class="nav-item"><a class="nav-link"
										href="/admin/warehouse-export-material"> <span
											class="menu-title">Phiếu xuất nguyên liệu</span>
									</a></li>
									<li class="nav-item"><a class="nav-link"
										href="/admin/warehouse-supplier"> <span
											class="menu-title">Nhà cung cấp nguyên liệu</span>
									</a></li>
								</ul>
							</div></li>
						<li class="nav-item"><a class="nav-link collapsed"
							data-toggle="collapse" href="#ui-basic4" aria-expanded="false"
							aria-controls="ui-basic4"> <i class="icon-handbag menu-icon"></i>
								<span class="menu-title">QUẢN LÝ NHÂN VIÊN </span>
						</a>
							<div class="collapse" id="ui-basic4" style="">
								<ul class="nav flex-column sub-menu">
									<li class="nav-item"><a href="/admin/employee"
										class="nav-link"> <span class="menu-title">Nhân
												viên</span>
									</a>
									</li>
									<li class="nav-item"><a href="/admin/register-schedule"
										class="nav-link"> <span class="menu-title">Đăng ký ca làm</span>
									</a></li>
									</li>
									<li class="nav-item"><a href="/admin/accept-register"
										class="nav-link"> <span class="menu-title">Duyệt ca làm</span>
									</a></li>
								</ul>
							</div></li>
						<li class="nav-item"><a class="nav-link collapsed"
							data-toggle="collapse" href="#ui-basic2" aria-expanded="false"
							aria-controls="ui-basic2"> <i class="icon-layers menu-icon"></i>
								<span class="menu-title">HỆ THỐNG QUẢN LÝ</span>
						</a>
							<div class="collapse" id="ui-basic2" style="">
								<ul class="nav flex-column sub-menu">
									<li class="nav-item"><a href="/admin/bill"
										class="nav-link"> <span class="menu-title">Hóa đơn</span>
									</a></li>
									<li class="nav-item"><a href="/admin/dinner-table"
										class="nav-link"> <span class="menu-title">Bàn</span>
									</a></li>
									<li class="nav-item"><a href="/admin/position"
										class="nav-link"> <span class="menu-title">Chức vụ</span>
									</a></li>
									<li class="nav-item"><a href="/admin/schedule"
										class="nav-link"> <span class="menu-title">Lịch
												làm</span>
									</a></li>
									<li class="nav-item"><a href="/admin/product"
										class="nav-link"> <span class="menu-title">Thức
												ăn- Đồ uống</span>
									</a></li>
									<li class="nav-item"><a class="nav-link"
										href="/admin/material"> <span
											class="menu-title">Nguyên liệu</span>
									</a></li>
									<li class="nav-item"><a href="/admin/category-product"
										class="nav-link"> <span class="menu-title">Loại
												thức ăn</span>
									</a></li>
									<li class="nav-item"><a href="/admin/voucher"
										class="nav-link"> <span class="menu-title">Voucher</span>
									</a></li>
									<li class="nav-item"><a href="/admin/bill-status"
										class="nav-link"> <span class="menu-title">Trạng
												thái hóa đơn</span>
									</a></li>
									<li class="nav-item"><a href="/admin/table-status"
										class="nav-link"> <span class="menu-title">Trạng
												thái bàn</span>
									</a></li>
								</ul>
							</div></li>
						<li class="nav-item"><a class="nav-link"
							data-toggle="collapse" href="#ui-basic3" aria-expanded="false"
							aria-controls="ui-basic3"> <i class="icon-settings menu-icon"></i>
								<span class="menu-title">TÀI KHOẢN CÁ NHÂN</span>
						</a>
							<div class="collapse" id="ui-basic3">
								<ul class="nav flex-column sub-menu">
									<li class="nav-item"><a class="nav-link"
										data-toggle="collapse" href="#auth" aria-expanded="false"
										aria-controls="auth"> <span class="menu-title">Lịch
												làm</span>
									</a></li>
									<li class="nav-item"><a class="nav-link"
										data-toggle="collapse" href="#auth" aria-expanded="false"
										aria-controls="auth"> <span class="menu-title">Thống
												kê lương tháng</span>
									</a></li>
									<li class="nav-item"><a class="nav-link"
										data-toggle="collapse" href="#auth" aria-expanded="false"
										aria-controls="auth"> <span class="menu-title">Thông
												tin cá nhân</span>
									</a></li>
									<li class="nav-item"><a class="nav-link"
										data-toggle="collapse" href="#auth" aria-expanded="false"
										aria-controls="auth"> <span class="menu-title">Đổi
												mật khẩu</span>
									</a></li>
									<li class="nav-item"><a class="nav-link"
										data-toggle="collapse" href="#auth" aria-expanded="false"
										aria-controls="auth"> <span class="menu-title">Đăng
												xuất</span>
									</a></li>
								</ul>
							</div></li>
					</ul>
				</nav>
				<!-- partial ends -->
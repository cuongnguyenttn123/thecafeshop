<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>The Coffe Shop</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="../resouces/node_modules/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="../resouces/node_modules/simple-line-icons/css/simple-line-icons.css">
<link rel="stylesheet"
	href="../resouces/node_modules/flag-icon-css/css/flag-icon.min.css">
<!-- endinject -->
<!-- plugin css for this page -->
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet" href="../resouces/css/style.css">
<link rel="stylesheet" href="../resouces/css/my-style-v2.css">
<!-- endinject -->
<link rel="shortcut icon" href="../resouces/images/favicon.png" />
</head>

<body>
	<div class="container-scroller">
		<div class="container-fluid page-body-wrapper">
			<div class="row">
				<div
					class="content-wrapper full-page-wrapper d-flex align-items-center auth login-full-bg">
					<div class="row w-100">
						<div class="col-lg-4 mx-auto">
							<div class="auth-form-dark text-left p-5">
								<h2>Đăng nhập</h2>
								<h4 class="font-weight-light">Xin chào! Hãy nhập thông tin</h4>
								<form method="POST" action="/admin/login" class="pt-5">
									<div class="form-group">
										<label for="emUsername">Tài khoản</label> <input type="text" required
											class="form-control" id="emUsername" name="emUsername"
											aria-describedby="emailHelp" placeholder="Tài khoản" value="admin">
										<i class="mdi mdi-account"></i>
									</div>
									<div class="form-group">
										<label for="emPassword">Mật khẩu</label> <input
											type="password" class="form-control" id="emPassword" name="emPassword"
											placeholder="Mật khẩu" value="admin"> <i class="mdi mdi-eye"></i>
									</div>
									<div class="form-group">
										<p class="label-error"><c:out value = "${error}"/></p>
									</div>
									<div class="mt-5">
										<button class="btn btn-block btn-warning btn-lg font-weight-medium"
											href="../../index.html">Đăng nhập</button>
									</div>
									<div class="mt-3 text-center">
										<a href="#" class="auth-link text-white">Quên mật khẩu?</a>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- content-wrapper ends -->
			</div>
			<!-- row ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->
	<!-- plugins:js -->
	<script src="../resouces/node_modules/jquery/dist/jquery.min.js"></script>
	<script src="../resouces/node_modules/popper.js/dist/umd/popper.min.js"></script>
	<script
		src="../resouces/node_modules/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- endinject -->
	<!-- inject:js -->
	<script src="../resouces/js/off-canvas.js"></script>
	<script src="../resouces/js/misc.js"></script>
	<!-- endinject -->
</body>

</html>

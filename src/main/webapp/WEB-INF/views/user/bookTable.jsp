
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<!-- head -->
<%@ include file="./common/head.jsp"%>
<!-- head [END] -->

<body class="">
<div class="container-scroller">

	<!-- nav -->0
	
	<%@ include file="./common/nav.jsp"%>
	<!-- nav [END] -->

	<!-- content-wrapper -->
	<div class="container-fluid page-body-wrapper"
		style="background: url(../resouces/images/background.jpg); background-size: cover;">
		<!-- content -->
		<div id="content-index" class="content-wrapper dislpay-product" style="width: 100%; margin-left: 0;">
			
			<%@ include file="./content/content-bookTable.jsp"%>
			
		</div>
		<!-- content [END] -->
		<!-- content-wrapper ends -->
		<!-- messenger -->
		<div id="div-box-chat"
			class="messenger nav-link count-indicator d-none d-lg-block fixed-bottom dropup "
			style="">
			<!-- chat-online -->
			<div class="popover fade show bs-popover-right chat-online"
				role="tooltip">
				<h3 id="btn-show" class="popover-header" onclick="">Tư vấn
					trức tuyến</h3>
				<h3 id="btn-hidden" class="popover-header" onclick="">Tư vấn
					trức tuyến</h3>
				<div id="display-chat">
					<div class="form-group ">
						<div class="container-fluid">
							<p class="mes-employee">
								<img
									src="../resouces/images/my-images/11820887136_ee58b64ba7.jpg"
									alt="image"> Chào bạn
							</p>
						</div>
						<div class="container-fluid float-right" style="">
							<p class="mes-client">
								Chào Admin <img
									src="../resouces/images/my-images/11820887136_ee58b64ba7.jpg"
									alt="image">
							</p>
						</div>
					</div>
					<div class="input-group popover-header form-chat-online">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Nhập tin nhắn..." aria-label="Username"
								aria-describedby="colored-addon3">
							<div class="input-group-append">
								<i class="fa fa-send"></i>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- end chat online -->
		</div>
		<!-- end messenger -->
		<!-- footer-->
		<%@ include file="./common/footer.jsp"%>
		<!-- footer [END] -->
		<!-- row-offcanvas ends -->
	</div>
	<!-- page-body-wrapper ends -->
</div>

<!-- js libary-->
<%@ include file="./common/jsLibary.jsp"%>
<!-- js libary [END] -->

<!-- Modal -->
<script src="../resouces/ajax-jquery/book-table.js"></script>
<!-- End Modal -->

</body>

</html>
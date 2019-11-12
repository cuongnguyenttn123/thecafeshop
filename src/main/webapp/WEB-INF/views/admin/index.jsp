<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- HEADER -->
<%@ include file="./public/header.jsp"%>
<!-- ./. HEADER -->
<!-- content-wrapper -->
<div class="content-wrapper">
	<div class="row section social-section">

		<c:forEach items="${dtos}" var="dto">
				<c:set var="background" scope="session"
					value="social-card w-100 free-table-status" />
				<c:set var="icon" scope="session" value="icon-status-table-ghetrong" />
			<c:if
				test="${dto.getDinnertable().getTablestatus().getTablestatusid() == 1}">
				<c:set var="background" scope="session"
					value="social-card w-100 none-table-status" />
				<c:set var="icon" scope="session" value="icon-status-table-none" />
			</c:if>
			<c:if
				test="${dto.getDinnertable().getTablestatus().getTablestatusid() == 2}">
				<c:set var="background" scope="session"
					value="social-card w-100 full-table-status" />
				<c:set var="icon" scope="session" value="icon-status-table-menu" />
			</c:if>
			<c:if
				test="${dto.getDinnertable().getTablestatus().getTablestatusid() == 3}">
				<c:set var="background" scope="session"
					value="social-card w-100 full-table-status" />
				<c:set var="icon" scope="session" value="icon-status-table-dangnau" />
			</c:if>
			<c:if
				test="${dto.getDinnertable().getTablestatus().getTablestatusid() == 4}">
				<c:set var="background" scope="session"
					value="social-card w-100 ordered-table-status" />
				<c:set var="icon" scope="session"
					value="icon-status-table-banconguoidat" />
			</c:if>
			<c:if
				test="${dto.getDinnertable().getTablestatus().getTablestatusid() == 5}">
				<c:set var="background" scope="session"
					value="social-card w-100 free-table-status" />
				<c:set var="icon" scope="session" value="icon-status-table-ghetrong" />
			</c:if>
			<c:if
				test="${dto.getDinnertable().getTablestatus().getTablestatusid() == 6}">
				<c:set var="background" scope="session"
					value="social-card w-100 full-table-status" />
				<c:set var="icon" scope="session" value="icon-status-table-donmon" />
			</c:if>
			<c:if
				test="${dto.getDinnertable().getTablestatus().getTablestatusid() == 7}">
				<c:set var="background" scope="session"
					value="social-card w-100 full-table-status" />
				<c:set var="icon" scope="session" value="icon-status-table-dangan" />
			</c:if> 

			<div id='<c:out value="${dto.getDinnertable().getDinnertableid() }"></c:out>'
			 class="col-lg-3 grid-margin stretch-card" data-dinnertableid='<c:out value="${dto.getDinnertable().getDinnertableid() }"></c:out>'>
				<!-- data-toggle="modal" data-target="#exampleModal" -->
				<div class='<c:out value="${background}"></c:out>'>
					<div class="badge badge-pill badge-primary number-table" style="">
						<c:out value="${dto.getDinnertable().getName() }"></c:out>
					</div>
					<div class="social-icon">
						<div class='<c:out value="${icon}"></c:out>'></div>
					</div>
					<div class="content">
						<p></p>
						<p>
							<c:out
								value="${dto.getDinnertable().getTablestatus().getName() }"></c:out>
						</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div
		class="d-flex align-items-center justify-content-between flex-wrap">
		<nav>
		</nav>
	</div>
</div>
<!-- content-wrapper ends -->
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel"><div class=""></div></h5>
				<input id="dinnertableid" value="" type="hidden" />
				<button id="close-modal" type="button" class="close">
					<span aria-hidden="true">&times;</span>
				</button>
				
			</div>
			<div class="modal-body">
				
			</div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>
<!-- End Modal -->
<!-- partial -->
</div>
<!-- row-offcanvas ends -->
</div>
<!-- FOOTER -->
<%@ include file="./public/footer.jsp"%>

<script src="../resouces/ajax-jquery/admin/index.js"></script>
<!-- ./. FOOTER -->
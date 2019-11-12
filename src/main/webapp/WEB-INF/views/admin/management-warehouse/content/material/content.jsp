<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- my-js -->
<script src="../resouces/ajax-jquery/warehouse/material/material.js"></script>
<!-- my-js[END] -->
<div class="row">
	<div class="col-12">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Kho nguyên liệu</h4>
				<div class="row grid-margin">
					<div class="col-12">
						<div class="row">
							<div class="col-md-3">
								<div class="form-group row">
									<label class="col-sm-3 col-form-label">Mã</label>
									<div class="col-sm-9">
										<input id="materialdetailid" type="text" class="form-control">
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group row">
									<label class="col-sm-3 col-form-label">Tên</label>
									<div class="col-sm-9">
										<input id="name" type="text" class="form-control">
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<button id="btnSearch" type="button"
									class="btn btn-inverse-primary btn-fw">Tìm kiếm</button>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<table id="order-listing" class="table" cellspacing="0"
							width="100%">
							<thead>
								<tr class="bg-primary text-white">
									<th>ID #</th>
									<th>Tên</th>
									<th>Số lượng</th>
									<th>Ngày sản xuất</th>
									<th>Ngày hết hạn</th>
									<th>Đơn giá</th>
									<th>Cập nhật lần cuối</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${dtos}" var="dto">
									<tr>
										<td><c:out value="${ dto.getMaterialdetailid()}"></c:out></td>
										<td><c:out value="${ dto.getName()}"></c:out></td>
										<td><c:out value="${ dto.getQuantity()}"></c:out></td>
										<td><c:out value="${ dto.getDateofmanufacture()}"></c:out></td>
										<td><c:out value="${ dto.getDateofmanufacture()}"></c:out></td>
										<td><c:out value="${ dto.getPrice()}"></c:out></td>
										<td><c:out value="${ dto.getUpdateat()}"></c:out></td>
										<td><label class="badge badge-info">On hold</label></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
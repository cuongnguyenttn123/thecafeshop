<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div data-repeater-list="group-a">
	<div data-repeater-item="" class="d-flex mb-2">
		<div class="input-group mb-2 mr-sm-2 mb-sm-0">
			<div class="input-group-prepend">
				<span class="input-group-text"><c:out value="${billdetail.getProduct().getName()}" /></span>
			</div>
			<input type="hidden" id="billid" value='<c:out value="${billdetail.getId().getBillid()}" />'>
			<input type="hidden" id="productid" value='<c:out value="${billdetail.getId().getProductid()}" />'>
			<input type="number" class="form-control form-control-sm"
				id="quantity" placeholder="Số lượng" value='<c:out value="${billdetail.getQuantity()}" />'>
		</div>
		<button id="btnUpdate" type="button" class="btn btn-success btn-sm">Cập nhật</button>

	</div>
</div>
<script src="../resouces/ajax-jquery/bill/bill-detail-edit-post.js"></script>

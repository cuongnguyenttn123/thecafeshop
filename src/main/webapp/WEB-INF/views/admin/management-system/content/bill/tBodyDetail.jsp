<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<thead>
	<tr>
		<th></th>
		<th>Sản phẩm</th>
		<th>Số lượng</th>
		<th>Đơn giá</th>
		<th>Tổng tiền</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${dtos}" var="dto">
		<tr>
			<td><label
				data-billId='<c:out value="${dto.getBilldetail().getId().getBillid()}" />'
				data-productId='<c:out value="${dto.getProductid()}" />'
				class="badge badge-info edit-billDetail"><i
					class="fa fa-wrench"></i></label> <label
				data-billId='<c:out value="${dto.getBilldetail().getId().getBillid()}" />'
				data-productId='<c:out value="${dto.getProductid()}" />'
				class="badge badge-danger remove-billDetail"><i
					class="fa fa-times"></i></label></td>
			<td><c:out value="${dto.getName()}" /></td>
			<td class="text-danger"><c:out
					value="${dto.getQuantity()}" /></td>
			<td><c:out value="${dto.getSinglePrice()}" /></td>
			<td><c:out value="${dto.getTotalPrice()}" /></td>
		</tr>
	</c:forEach>
</tbody>
<script src="../resouces/ajax-jquery/bill/bill-detail-edit-get.js"></script>
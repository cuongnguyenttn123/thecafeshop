<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${dtos}" var="dto">
	<tr>
		<td><label
			data-categoryproductid='<c:out value="${dto.getCategoryproduct().getCategoryproductid()}" />'
			class="badge badge-info edit"><i class="fa fa-wrench x"></i></label>
			<c:if test="${!dto.getCanDelete()}">
				<label
					data-categoryproductid='<c:out value="${dto.getCategoryproduct().getCategoryproductid()}" />'
					class="badge badge-danger remove"><i class="fa fa-times"></i></label>

			</c:if></td>
		<td><c:out
				value="${dto.getCategoryproduct().getCategoryproductid()}" /></td>
		<td><c:out value="${dto.getCategoryproduct().getName()}" /></td>
		<td class="text-danger"><c:out
				value="${dto.getCategoryproduct().getUpdateat()}" /></td>
	</tr>
</c:forEach>
<script src="../resouces/ajax-jquery/category-product/category-product-add.js"></script>
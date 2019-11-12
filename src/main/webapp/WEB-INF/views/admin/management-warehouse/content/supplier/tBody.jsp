<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${dtos}" var="dto">
	<tr>
		<td><label
			data-supplierid='<c:out value="${dto.getSupplier().getSupplierid()}" />'
			class="badge badge-info edit"><i class="fa fa-wrench"></i></label>
			<c:if test = "${!dto.getCanDelete()}">
				<label 
				data-supplierid='<c:out value="${dto.getSupplier().getSupplierid()}" />'
				class="badge badge-danger remove"><i class="fa fa-times"></i></label>       
			</c:if>
			
		</td>
		<td><c:out value="${dto.getSupplier().getSupplierid()}" /></td>
		<td><c:out value="${dto.getSupplier().getName()}" /></td>
		<td><c:out value="${dto.getSupplier().getAddress()}" /></td>
		<td><c:out value="${dto.getSupplier().getPhone()}" /></td>
		<td class="text-danger"><c:out
				value="${dto.getSupplier().getUpdateat()}" /></td>
	</tr>
</c:forEach>
<script src="../resouces/ajax-jquery/warehouse/supplier/supplier-add.js"></script>
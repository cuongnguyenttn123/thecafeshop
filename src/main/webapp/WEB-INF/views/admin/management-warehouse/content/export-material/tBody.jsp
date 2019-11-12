<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${dtos}" var="dto">
	<tr>
		<td><label
			data-exportbillid='<c:out value="${dto.getExportbill().getExportbillid()}" />'
			class="badge badge-info edit"><i class="fa fa-wrench"></i></label>
			<c:if test = "${!dto.getCanDelete()}">
				<label 
				data-exportbillid='<c:out value="${dto.getExportbill().getExportbillid()}" />'
				class="badge badge-danger remove"><i class="fa fa-times"></i></label>       
			</c:if>
			
		</td>
		<td><c:out value="${dto.getExportbill().getExportbillid()}" /></td>
		<td><c:out value="${dto.getExportbill().getProduct().getName()}" /></td>
		<td><c:out value="${dto.getCountBillDetail()}" /></td>
		<td><c:out value="${dto.getEmployee().getName()}" /></td>
		<td class="text-danger"><c:out
				value="${dto.getExportbill().getUpdateat()}" /></td>
	</tr>
</c:forEach>
<script src="../resouces/ajax-jquery/warehouse/export-material/export-bill-add.js"></script>
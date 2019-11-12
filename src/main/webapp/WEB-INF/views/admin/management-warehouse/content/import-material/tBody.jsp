<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${dtos}" var="dto">
	<tr>
		<td><label
			data-importbillid='<c:out value="${dto.getImportbill().getImportbillid()}" />'
			class="badge badge-info edit"><i class="fa fa-wrench"></i></label>
			<c:if test = "${!dto.getCanDelete()}">
				<label 
				data-importbillid='<c:out value="${dto.getImportbill().getImportbillid()}" />'
				class="badge badge-danger remove"><i class="fa fa-times"></i></label>       
			</c:if>
			
		</td>
		<td><c:out value="${dto.getImportbill().getImportbillid()}" /></td>
		<td><c:out value="${dto.getSupplier().getName()}" /></td>
		<td><c:out value="${dto.getCountBillDetail()}" /></td>
		<td><c:out value="${dto.getEmployee().getName()}" /></td>
		<td class="text-danger"><c:out
				value="${dto.getImportbill().getUpdateat()}" /></td>
	</tr>
</c:forEach>
<script src="../resouces/ajax-jquery/warehouse/import-material/import-bill-add.js"></script>
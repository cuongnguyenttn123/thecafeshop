<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${dtos}" var="dto">
	<tr>
		<td><label
			data-billstatusid='<c:out value="${dto.getBillstatus().getBillstatusid()}" />'
			class="badge badge-info edit"><i class="fa fa-wrench"></i></label>
			<c:if test = "${!dto.getCanDelete()}">
				<label 
				data-billstatusid='<c:out value="${dto.getBillstatus().getBillstatusid()}" />'
				class="badge badge-danger remove"><i class="fa fa-times"></i></label>       
			</c:if>
			
		</td>
		<td><c:out value="${dto.getBillstatus().getBillstatusid()}" /></td>
		<td><c:out value="${dto.getBillstatus().getName()}" /></td>
		<td class="text-danger"><c:out
				value="${dto.getBillstatus().getUpdateat()}" /></td>
	</tr>
</c:forEach>
<script src="../resouces/ajax-jquery/bill-status/bill-status-add.js"></script>
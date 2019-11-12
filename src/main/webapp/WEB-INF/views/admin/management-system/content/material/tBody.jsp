<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${dtos}" var="dto">
	<tr>
		<td><label
			data-materialid='<c:out value="${dto.getMaterial().getMaterialid()}" />'
			class="badge badge-info edit"><i class="fa fa-wrench"></i></label>
			<c:if test = "${!dto.getCanDelete()}">
				<label 
				data-materialid='<c:out value="${dto.getMaterial().getMaterialid()}" />'
				class="badge badge-danger remove"><i class="fa fa-times"></i></label>       
			</c:if>
			
		</td>
		<td><c:out value="${dto.getMaterial().getMaterialid()}" /></td>
		<td><c:out value="${dto.getMaterial().getName()}" /></td>
		<td class="text-danger"><c:out
				value="${dto.getMaterial().getUpdateat()}" /></td>
	</tr>
</c:forEach>
<script src="../resouces/ajax-jquery/material/material-add.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${dtos}" var="dto">
	<tr>
		<td><label
			data-employeeid='<c:out value="${dto.getEmployee().getEmployeeid()}" />'
			class="badge badge-info edit"><i class="fa fa-wrench x"></i></label>
			<label
			data-employeeid='<c:out value="${dto.getEmployee().getEmployeeid()}" />'
			class="badge badge-danger remove"><i class="fa fa-times"></i></label>
		</td>
		<td><c:out value="${dto.getEmployee().getEmployeeid()}" /></td>
		<td><c:out value="${dto.getEmployee().getName()}" /></td>
		<td><c:if test="${dto.getEmployee().getSex()}">Nam</c:if> <c:if
				test="${!dto.getEmployee().getSex()}">Nữ</c:if></td>
		<td><c:out value="${dto.getEmployee().getPhone()}" /></td>
		<td><c:out value="${dto.getEmployee().getAddress()}" /></td>
		<td><c:out
				value="${dto.getSalary()}" /></td>
		<td class="text-danger"><c:out
				value="${dto.getEmployee().getUpdateat()}" /></td>
	</tr>
</c:forEach>
<script src="../resouces/ajax-jquery/employee/loadTable.js"></script>
<script src="../resouces/ajax-jquery/employee/employee-add.js"></script>
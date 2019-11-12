<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${dtos}" var="dto">
    <tr>
        <td>
           <label data-scheduleid='<c:out value="${dto.getScheduleid()}" />' class="badge badge-info edit" ><i class="fa fa-wrench x"></i></label>
            <label data-scheduleid='<c:out value="${dto.getScheduleid()}" />' class="badge badge-danger remove"><i class="fa fa-times"></i></label>
        </td>
        <td><c:out value="${dto.getScheduleid()}" /></td>
        <td><c:out value="${dto.getStarttime()}" /></td>
        <td><c:out value="${dto.getEndtime()}" /></td>
        <td><c:out value="${dto.getPayrate()}" /></td>
        <td class="text-danger"><c:out value="${dto.getUpdateat()}" /></td>
    </tr>
</c:forEach>
<script src="../resouces/ajax-jquery/schedule/schedule-add.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${results}" var="result">
	<div class="badge badge-danger">
		<c:out value="${result}" />
	</div>
	<br>
</c:forEach>
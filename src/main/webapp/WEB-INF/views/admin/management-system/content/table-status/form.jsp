<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="form-group">
	<label for="tablestatusid" class="col-sm-12 col-form-label">Tên
		trạng thái</label> <input id="tablestatusid" type="hidden"
		value='<c:out value="${tablestatus.getTablestatusid()}" />' /> <input
		id="name" type="text"
		value='<c:out value="${tablestatus.getName()}" />'
		class="form-control" placeholder="Tên trạng thái" />
</div>


<c:if test="${tablestatus.getTablestatusid()==null}">
	<button id="btnSave" type="button" class="btn btn-success mr-2">
		Thêm</button>
</c:if>
<c:if test="${tablestatus.getTablestatusid()!=null}">
	<button id="btnUpdate" type="button" class="btn btn-success mr-2">
		Cập nhật</button>
</c:if>
<button id="btnClear" class="btn btn-light">Làm mới</button>
<script src="../resouces/ajax-jquery/table-status-edit.js"></script>
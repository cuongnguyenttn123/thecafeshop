<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${material.getMaterialid()!=null}">
	<div class="form-group row">
		<label for="materialid" class="col-sm-3 col-form-label">Mã
			số(*)</label> <input id="materialid" name="materialid" type="text"
			required minlength="1" maxlength="7" disabled
			value='<c:out value="${material.getMaterialid()}" />'
			class="form-control" placeholder="Mã trạng thái" />
	</div>
</c:if>
<div class="form-group row">
	<label class="col-sm-12 col-form-label">Tên nguyên liệu</label> <input
		id="name" name="name" type="text" required
		value='<c:out value="${material.getName()}" />' class="form-control"
		placeholder="Tên nguyên liệu" />
</div>
<div class="form-group row">
	<label class="col-sm-3 col-form-label">Đơn vị tính</label> <input
		id="unit" name="unit" type="text" required
		value='<c:out value="${material.getUnit()}" />' class="form-control"
		placeholder="Đơn vị tính" />
</div>
<c:if test="${material.getMaterialid()==null}">
	<button id="btnSave" type="button" class="btn btn-success mr-2">
		Thêm</button>
</c:if>
<c:if test="${material.getMaterialid()!=null}">
	<button id="btnUpdate" type="button" class="btn btn-success mr-2">
		Cập nhật</button>
</c:if>
<button id="btnClear" class="btn btn-light">Làm mới</button>

<script src="../resouces/ajax-jquery/material/material-edit.js"></script>

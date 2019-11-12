<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${supplier.getSupplierid()!=null}">
	<div class="form-group row">
		<label for="billstatusid" class="col-sm-3 col-form-label">Mã
			số</label> <input id="supplierid" name="supplierid" type="text" required
			minlength="1" maxlength="7" disabled
			value='<c:out value="${supplier.getSupplierid()}" />'
			class="form-control" placeholder="Mã trạng thái" />
	</div>
</c:if>
<div class="form-group row">
	<label class="col-sm-3 col-form-label">Tên</label> <input id="name"
		name="name" type="text" required
		value='<c:out value="${supplier.getName()}" />' class="form-control"
		placeholder="Tên" />
</div>
<div class="form-group row">
	<label class="col-sm-3 col-form-label">Số điện thoại</label> <input
		id="phone" name="phone" type="text" required
		value='<c:out value="${supplier.getPhone()}" />' class="form-control"
		placeholder="Số điện thoại" />
</div>
<div class="form-group row">
	<label class="col-sm-3 col-form-label">Địa chỉ</label> <input
		id="address" name="address" type="text" required
		value='<c:out value="${supplier.getAddress()}" />'
		class="form-control" placeholder="Địa chỉ" />
</div>
<c:if test="${supplier.getSupplierid()==null}">
	<button id="btnSave" type="button" class="btn btn-success mr-2">
		Thêm</button>
</c:if>
<c:if test="${supplier.getSupplierid()!=null}">
	<button id="btnUpdate" type="button" class="btn btn-success mr-2">
		Cập nhật</button>
</c:if>
<button id="btnClear" class="btn btn-light">Làm mới</button>

<script src="../resouces/ajax-jquery/warehouse/supplier/supplier-edit.js"></script>

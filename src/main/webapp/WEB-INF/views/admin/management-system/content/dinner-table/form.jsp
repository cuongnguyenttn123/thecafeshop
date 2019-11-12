<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="form-group row">
		<c:if test = "${dinnertable.getDinnertableid()!=null}">
	<label for="billstatusid" class="col-sm-3 col-form-label">Mã
		bàn</label> <input id="dinnertableid" name="dinnertableid" type="text"
		required minlength="1" maxlength="7"
              disabled
		value='<c:out value="${dinnertable.getDinnertableid()}" />'
		class="form-control" placeholder="Mã bàn" />
          </c:if>
</div>
<div class="form-group row">
	<label class="col-sm-3 col-form-label">Tên bàn</label> <input id="name"
		name="name" type="text" required
		value='<c:out value="${dinnertable.getName()}" />' class="form-control"
		placeholder="Tên bàn" />
</div>
<div class="form-group row">
	<label class="col-sm-3 col-form-label">Số ghế</label> <input
		id="countchair" name="countchair" type="number" required
		value='<c:out value="${dinnertable.getCountchair()}" />' class="form-control"
		placeholder="Số ghế" />
</div>
<c:if test="${dinnertable.getDinnertableid()==null}">
	<button id="btnSave" type="button" class="btn btn-success mr-2">
		Thêm</button>
</c:if>
<c:if test="${dinnertable.getDinnertableid()!=null}">
	<button id="btnUpdate" type="button" class="btn btn-success mr-2">
		Cập nhật</button>
</c:if>
<button id="btnClear" class="btn btn-light">Làm mới</button>

<script src="../resouces/ajax-jquery/dinner-table/dinner-table-edit.js"></script>

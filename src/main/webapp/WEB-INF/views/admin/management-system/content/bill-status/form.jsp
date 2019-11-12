<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<div class="form-group row">
		<label for="billstatusid" class="col-sm-3 col-form-label">Mã số(*)</label>
			<input id="billstatusid" name="billstatusid" type="text" required
				minlength="1" maxlength="7"
				<c:if test = "${billstatus.getBillstatusid()!=null}">
              disabled
          </c:if>
				value='<c:out value="${billstatus.getBillstatusid()}" />'
				class="form-control" placeholder="Mã trạng thái" />
	</div>
	<div class="form-group row">
		<label class="col-sm-3 col-form-label">Tên(*)</label>
			<input id="name" name="name" type="text" required
				value='<c:out value="${billstatus.getName()}" />'
				class="form-control" placeholder="Tên trạng thái" />
	</div>
	<c:if test="${billstatus.getBillstatusid()==null}">
		<button id="btnSave" type="button" class="btn btn-success mr-2">
			Thêm</button>
	</c:if>
	<c:if test="${billstatus.getBillstatusid()!=null}">
		<button id="btnUpdate" type="button" class="btn btn-success mr-2">
			Cập nhật</button>
	</c:if>
	<button id="btnClear" class="btn btn-light">Làm mới</button>

<script src="../resouces/ajax-jquery/bill-status/bill-status-edit.js"></script> 
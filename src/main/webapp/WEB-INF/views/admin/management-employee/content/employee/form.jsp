<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="form-group row">
	<label for="employeeid" class="  col-form-label">Mã nhân
		viên(*)</label> <input id="employeeid" type="text"
		<c:if test = "${employee.getEmployeeid()!=null}">
              disabled
          </c:if>
		value='<c:out value="${employee.getEmployeeid()}" />'
		class="form-control" placeholder="Mã nhân viên" />
</div>
<div class="form-group row">
	<label for="name" class="  col-form-label">Tên nhân
		viên(*)</label> <input id="name" type="text"
		value='<c:out value="${employee.getName()}" />' class="form-control"
		placeholder="Tên nhân viên" />
</div>
<div class="form-group">
	<div class="row">
		<div class="form-radio form-radio-flat">
			<label class="form-check-label"> <input type="radio"
				class="form-check-input sex" name="sex"  value="Male"
				checked="checked"> Nam <i class="input-helper"></i></label>
		</div>
		<div class="form-radio form-radio-flat">
			<label class="form-check-label"> <input type="radio"
				class="form-check-input sex" name="sex"  value="FeMale">
				Nữ <i class="input-helper"></i></label>
		</div>
	</div>
</div>
<div class="form-group row">
	<label for="phone" class="  col-form-label">Số điện
		thoại(*)</label> <input id="phone" type="text"
		value='<c:out value="${employee.getPhone()}" />' class="form-control"
		placeholder="Số điện thoại" />
</div>
<div class="form-group row">
	<label for="address" class="  col-form-label">Địa chỉ(*)</label> <input
		id="address" type="text"
		value='<c:out value="${employee.getAddress()}" />'
		class="form-control" placeholder="Địa chỉ" />
</div>
<c:if test="${employee.getEmployeeid()==null}">
	<div class="form-group row">
		<label for="usename" class="  col-form-label">Tài
			khoản(*)</label> <input id="usename" type="text" class="form-control"
			placeholder="Tài khoản" />
	</div>
	<div class="form-group row">
		<label for="password" class="  col-form-label">Mật khẩu(*)</label>
		<input id="password" type="password"
			value='<c:out value="${billstatus.getName()}" />'
			class="form-control" placeholder="Mật khẩu" />
	</div>
</c:if>
<div class="form-group row">
	<label for="position" class="  col-form-label">Chức vụ(*)</label> <select
		class="form-control" id="position">
		<option value="-1">Phân loại</option>
		<c:forEach items="${positions}" var="position">
			<option
				<c:if test="${xx.getCategoryproduct().getCategoryproductid()==position.getPositionid()}">
                                selected
                        </c:if>
				value='<c:out value = "${position.getPositionid()}" />'>
				<c:out value="${position.getName() }" />
			</option>
		</c:forEach>
	</select>
</div>
<div class="form-group row">
	<label for="salaryonhour" class="  col-form-label">Lương /
		giờ(*)</label> <input id="salaryonhour" type="number"
		value='<c:out value="${ dto.getSalary()}" />'
		class="form-control" placeholder="Lương / giờ" />


</div>
<div class="form-group row">
	<label for="startdate" class="  col-form-label">Ngày bắt đầu
		làm(*)</label> <input id="startdate" type="date"
		value='<c:out value="${ employee.getSalary().getStartdate()}" />'
		class="form-control" placeholder="Ngày bắt đầu làm" />


</div>
<c:if test="${employee.getEmployeeid()==null}">
	<button id="btnSave" type="button" class="btn btn-success mr-2">
		Thêm</button>
</c:if>
<c:if test="${employee.getEmployeeid()!=null}">
	<button id="btnUpdate" type="button" class="btn btn-success mr-2">
		Cập nhật</button>
</c:if>
<button id="btnClear" class="btn btn-light">Làm mới</button>
<script src="../resouces/ajax-jquery/employee/loadTable.js"></script>
<script src="../resouces/ajax-jquery/employee/employee-edit.js"></script>
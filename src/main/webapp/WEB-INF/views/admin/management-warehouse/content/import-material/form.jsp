<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="form-group ">
	<label for="position" class="  col-form-label">Nhà cung cấp</label> <input
		id="importbillid" name="importbillid" type="hidden"
		value='<c:out value="${importbill.getImportbillid()}" />' /> <select
		<c:if test="${importbilldetails != null}">
                                disabled="disabled"
                        </c:if>
		class="form-control" id="supplierid">
		<option value="-1"></option>
		<c:forEach items="${suppliers}" var="supplier">
			<option
				<c:if test="${importbill.getSupplier().getSupplierid()==supplier.getSupplierid()}">
                                selected
                        </c:if>
				value='<c:out value = "${supplier.getSupplierid()}" />'>
				<c:out value="${supplier.getName() }" />
			</option>
		</c:forEach>
	</select>
</div>
<table class="table">
	<thead>
		<tr>
			<th></th>
			<th>Nguyên liệu</th>
			<th>Đơn giá</th>
			<th>Số lượng</th>
			<th>Sản xuất</th>
			<th>Hết hạn</th>
		</tr>
	</thead>
	<tbody>
		<tr class="-1" hidden>
			<td><label class="badge badge-danger remove-import" data-remove-import="-1"><i
					class="fa fa-times"></i></label></td>
			<td><input id="materialdetailid" name="materialdetailid"
				type="hidden" /><select
				class="" id="materialid"
				style="border: 2px solid #f2f2f2; width: 80px;">
					<option value="-1"></option>
					<c:forEach items="${materials}" var="material">
						<option value='<c:out value = "${material.getMaterialid()}" />'>
							<c:out value="${material.getName() }" />
						</option>
					</c:forEach>
			</select></td>
			<td><input id="price" name="price" type="number"
				style="border: 2px solid #f2f2f2; width: 70px;" placeholder="0" /></td>
			<td><input id="quantity" name="quantity" type="number"
				style="border: 2px solid #f2f2f2; width: 70px;" placeholder="0" /></td>
			<td><input id="dateofmanufacture" type="date"
				style="border: 2px solid #f2f2f2; width: 100px;" /></td>
			<td><input id="expirationdate" type="date"
				style="border: 2px solid #f2f2f2; width: 100px;" /></td>
		</tr>
		<c:if test="${ importbilldetails != null}">
				<c:set var = "i" scope = "session" value = "${1}"></c:set>
			<c:forEach items="${importbilldetails}" var="importbilldetail">
				<tr class="0">
					<td><label onclick="name();" class="badge badge-danger remove-import" data-remove-import='<c:out value="${i }"></c:out>'><i
							class="fa fa-times"></i></label></td>
					<td><input id="materialdetailid" name="materialdetailid"
						type="hidden"
						value='<c:out value="${importbilldetail.getMaterialdetail().getMaterialdetailid()}" />' />
						<select class="" id="materialid" disabled="disabled"
						style="border: 2px solid #f2f2f2; width: 80px;">
							<option value="-1"></option>
							<c:forEach items="${materials}" var="material">
								<option
									<c:if test="${importbilldetail.getMaterialdetail().getMaterial().getMaterialid()==material.getMaterialid()}">
                                selected
                        </c:if>
									value='<c:out value = "${material.getMaterialid()}" />'>
									<c:out value="${material.getName() }" />
								</option>
							</c:forEach>
					</select></td>
					<td><input id="price" name="price" type="number"
				style="border: 2px solid #f2f2f2; width: 70px;" placeholder="0" value='<c:out value="${importbilldetail.getMaterialdetail().getPrice()}" />' /></td>
					<td><input id="quantity" name="quantity" type="number"
						style="border: 2px solid #f2f2f2; width: 70px;"
						value='<c:out value="${importbilldetail.getMaterialdetail().getQuantity()}" />'
						placeholder="0" /></td>
					<td><input id="dateofmanufacture" type="date"
						style="border: 2px solid #f2f2f2; width: 100px;"
						value='<c:out value="${ importbilldetail.getMaterialdetail().getDateofmanufacture()}" />' /></td>
					<td><input id="expirationdate" type="date"
						style="border: 2px solid #f2f2f2; width: 100px;"
						value='<c:out value="${ importbilldetail.getMaterialdetail().getExpirationdate()}" />' /></td>
				</tr>
				<c:set var = "i" scope = "session" value = "${i + 1}"></c:set>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<div class="form-group" style="text-align: center;">
	<button id="btn-Add-Row" type="button"
		class="btn btn-light btn-rounded btn-fw">
		<i class="mdi mdi-plus"></i>
	</button>
</div>
<c:if test="${importbilldetails ==null}">
	<button id="btnSave" type="button" class="btn btn-success mr-2">
		Thêm</button>
</c:if>
<c:if test="${importbilldetails!=null}">
	<button id="btnUpdate" type="button" class="btn btn-success mr-2">
		Cập nhật</button>
</c:if>
<button id="btnClear" class="btn btn-light">Làm mới</button>

<div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Bạn muốn xóa dòng này?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
        <button type="button" class="btn btn-primary">Đồng ý</button>
      </div>
    </div>
  </div>
</div>

<script
	src="../resouces/ajax-jquery/warehouse/import-material/import-bill-edit.js"></script>

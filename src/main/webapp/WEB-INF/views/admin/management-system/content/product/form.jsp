<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="form-group">
	<label for="billstatusid" class="col-sm-12 col-form-label">Mã sản phẩm</label> <input id="productid" type="text"
		<c:if test = "${product.getProductid()!=null}">
              disabled
          </c:if>
		value='<c:out value="${product.getProductid()}" />'
		class="form-control" placeholder="Mã sản phẩm" />
</div>
<div class="form-group">
	<label for="billstatusid" class="col-sm-12 col-form-label">Tên
		sản phẩm</label> <input id="name" type="text"
		value='<c:out value="${product.getName()}" />' class="form-control"
		placeholder="Tên sản phẩm" />
</div>
<div class="form-group">
	<label for="billstatusid" class="col-sm-12 col-form-label">Mô tả
		sản phẩm</label>
	<textarea id="description" class="form-control" rows="3"
		placeholder="Mô tả sản phẩm">
                <c:out value="${product.getDescription()}" />
          </textarea>
</div>
<div class="form-group">
	<label for="price" class="col-sm-12 col-form-label">Đơn giá</label>
	<input id="price" class="form-control" type="number"
		placeholder="0"
                value ='<c:out value="${price}"/>'
          />
</div>
<div class="form-group">
	<label for="billstatusid" class="col-sm-12 col-form-label">Loại
		sản phẩm</label> <select class="form-control" id="categoryproductid">
		<option value="-1">Phân loại</option>
		<c:forEach items="${categoryproducts}" var="categoryproduct">
			<option
				<c:if test="${product.getCategoryproduct().getCategoryproductid()==categoryproduct.getCategoryproductid()}">
                                selected
                        </c:if>
				value='<c:out value = "${categoryproduct.getCategoryproductid()}" />'>
				<c:out value="${categoryproduct.getName() }" />
			</option>
		</c:forEach>
	</select>
</div>

<c:if test="${product.getProductid()==null}">
	<button id="btnSave" type="button" class="btn btn-success mr-2">
		Thêm</button>
</c:if>
<c:if test="${product.getProductid()!=null}">
	<button id="btnUpdate" type="button" class="btn btn-success mr-2">
		Cập nhật</button>
</c:if>
<button id="btnClear" class="btn btn-light">Làm mới</button>
<script src="../resouces/ajax-jquery/product/product-edit.js"></script>
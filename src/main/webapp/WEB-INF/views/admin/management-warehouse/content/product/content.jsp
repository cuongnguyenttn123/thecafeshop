<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- my-js -->
<script src="../resouces/ajax-jquery/warehouse/product/product.js"></script>
<!-- my-js[END] -->
<div class="row">
	<div class="col-12">
		<div class="row">
			<div class="col-md-3">
				<div class="form-group row">
					<label class="col-sm-3 col-form-label">Loại</label>
					<div class="col-sm-9">
						<select id="categoryproductid" class="form-control">
							<option value=""></option>
							<c:forEach items="${categoryProducts}" var="categoryProduct">
								<option value='<c:out value = "${categoryProduct.getCategoryproductid()}" />'>
									<c:out value="${categoryProduct.getName() }" />
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="form-group row">
					<label class="col-sm-3 col-form-label">Mã</label>
					<div class="col-sm-9">
						<input id="productid" type="text" class="form-control">
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="form-group row">
					<label class="col-sm-3 col-form-label">Tên</label>
					<div class="col-sm-9">
						<input id="name" type="text" class="form-control">
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<button id="btnSearch" type="button" class="btn btn-inverse-primary btn-fw">Tìm kiếm</button>
			</div>
		</div>
		<p class="card-description">
		<div class="badge " id='<c:out value="result-form" />'></div>
		</p>
		<div class="row portfolio-grid">
			<c:forEach items="${dtos}" var="dto">
				<div
					class="col-xl-2 col-lg-2 col-md-2 col-sm-6 col-12 product-widget"
					style="padding: 3px;">
					<figure class="effect-text-in">
						<img style="height: 150px; padding: 0;"
							class="d-block w-100 col-lg-12 img-11"
							src='../resouces/images/my-images/<c:out value="${dto.getImages().get(0).getName()}" />'
							alt="First slide">
						<figcaption style="text-align: center;">
							<h4>
								<c:out value="${dto.getName()}" />
							</h4>
							<h7>Tồn kho:
							<h5 class="badge badge-warning"
								id='quantity-<c:out value="${dto.getProductid()}" />'>
								<c:out value="${dto.getQuantityInventory()}" />
							</h5>
							</h7>
							<p>
								Bỏ đi: <input
									id='<c:out value="${dto.getProductid()}" />'
									type="number" class="col-md-4" value="0"> <i
									data-productId='<c:out value="${dto.getProductid()}" />'
									style="font-size: 20px; color: red;"
									class="fa fa-trash-o btnUpdate" title="Vứt bỏ"></i>
							</p>
						</figcaption>
					</figure>
					<button
						id='<c:out value="result-${dto.getProductid()}" />'
						class="delete"
						style="background-color: #33c92e; height: 25px; width: 25px;">
						<i style="font-size: 25px" class="fa fa-check-square-o"></i>
					</button>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
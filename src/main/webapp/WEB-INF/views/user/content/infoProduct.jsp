
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-lg-8">
	<div id="product_details" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<i class="fa fa-caret-left" data-target="#product_details"
				data-slide-to="0"></i>
			<i class="fa fa-square" data-target="#product_details"
				data-slide-to="1"></i>
			<i class="fa fa-caret-right active" data-target="#product_details"
				data-slide-to="2"></i>
		</ol>
		<div class="carousel-inner">
			<c:forEach items="${images}" var="image">
				<div class="carousel-item 
				<c:if test='${i==0}'><c:out value="active" /></c:if> 	" 	>
					<img class="d-block w-100 col-lg-12"
						src="../resouces/images/my-images/<c:out value="${image.getName()}" />">
				</div>
			</c:forEach>
		</div>
		<a class="carousel-control-prev" href="#product_details" role="button"
			data-slide="prev"> <span class="carousel-control-prev-icon"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#product_details"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>
</div>
<div class="col-lg-4">
	<div class="card-body">
		<div class="card rounded border mb-2 alert-success">
			<div class="card-body p-3">
				<div class="media">
					<div class="media-body">
						<p class="mb-0 text-muted detail-product-name">
							<c:out value="${product.getName()}" />
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="card rounded border mb-2 alert-danger">
			<div class="card-body p-3">
				<div class="media">

					<div class="media-body">
						<p class="mb-0 text-muted detail-product-price">
							<c:out value="${old_prPrice}đ" />
						</p>
						<c:if test="${new_Price != null}">
							<p class="mb-0 text-muted detail-product-price">
								<c:out value="${new_Price.getPrice()}đ" />
							</p>
							<p class="mb-0 text-muted detail-product-price">
								<c:out value="Áp dụng ${new_Price.getStartdatetime()}" />
							</p>
						</c:if>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="card rounded border mb-2 alert-info">
			<div class="card-body p-3">
				<div class="media">
					<div class="media-body">
						<p class="mb-0 text-muted">
							<c:out value="${product.getDescription()}" />
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row section social-section">
	<div class="row">
		<!-- Display Product -->
		<c:forEach items="${productDTOs}" var="productDTO">
			<div class="col-lg-2 col-md-6 " style="">
				<div class="product">
					<div class="product_img">
						<div
							id="<c:out value="${productDTO.getProductid()}" />"
							class="carousel slide product_img" data-ride="carousel">
							<ol class="carousel-indicators">
								<i class="fa fa-circle active"
									data-target="#<c:out value="${productDTO.getProductid()}" />"
									data-slide-to="0"></i>
								<i class="fa fa-circle"
									data-target="#<c:out value="${productDTO.getProductid()}" />"
									data-slide-to="1"></i>
								<i class="fa fa-circle"
									data-target="#<c:out value="${productDTO.getProductid()}" />"
									data-slide-to="2"></i>
							</ol>
							<div class="carousel-inner ">
								<c:set var="i" scope="session" value="${1}" />

								<c:forEach items="${productDTO.getImages()}" var="image">
									<c:if test="${i == 1}">
										<c:set var="alt" scope="session" value="First slide" />
									</c:if>
									<c:if test="${i == 2}">
										<c:set var="alt" scope="session" value="Second slide" />
									</c:if>
									<c:if test="${i == 3}">
										<c:set var="alt" scope="session" value="Third slide" />
									</c:if>
									<div
										class="carousel-item 
										<c:if test="${i == 1}">	
											active
										</c:if>
									">
										<img
											class="d-block w-100 col-lg-12 img-<c:if test="${i == 1}"><c:out value="${productDTO.getProductid()}" /></c:if>"
											src='../resouces/images/my-images/<c:out value="${image.getName()}" />'
											alt='<c:out value="${alt}" />'>
									</div>
									<c:set var="i" scope="session" value="${i + 1 }" />
								</c:forEach>


							</div>
							<a class="carousel-control-prev"
								href="#<c:out value="${productDTO.getProductid()}" />"
								role="button" data-slide="prev"> <span
								class="carousel-control-prev-icon" aria-hidden="true"></span> <span
								class="sr-only">Previous</span>
							</a> <a class="carousel-control-next"
								href="#<c:out value="${productDTO.getProductid()}" />"
								role="button" data-slide="next"> <span
								class="carousel-control-next-icon" aria-hidden="true"></span> <span
								class="sr-only">Next</span>
							</a>
						</div>
						<div class="product-label">
							<c:if test="${productDTO.getCheckIsNew() == false}">
								<!-- product have new price -->
								<c:if test="${productDTO.getRateOldAndNewPrice() > 0}">
									<span class="sale"> <c:out
											value="-${productDTO.getRateOldAndNewPrice()}%" /> <c:if
											test="${productDTO.getCheckIsNew() == false}">
											<c:out
												value="Từ ${productDTO.getNewPrice().getStartdatetime()}" />
										</c:if> <c:if test="${productDTO.getCheckIsNew() == true}">
											<c:out
												value="Ra mắt ${productDTO.getNewPrice().getStartdatetime()}" />
										</c:if>

									</span>
								</c:if>
							</c:if>
							<c:if test="${productDTO.getCheckIsNew() == true}">
								<span class="new">NEW</span>
							</c:if>
						</div>
					</div>
					<div class="product-body">
						<p class="product-category">
							<c:out
								value="${productDTO.getCategoryproductNAME()}" />
						</p>
						<h3
							class="product-name product-name-<c:out value="${productDTO.getProductid()}" />">
							<c:out value="${productDTO.getName()}" />
						</h3>
						<h4
							class="product-price product-price-<c:out value="${productDTO.getProductid()}" />">
							<c:if test="${productDTO.getNewPrice()!=null}">
								<!-- new price -->
								<c:out value="${productDTO.getNewPrice().getPrice()}" />
								<del class="product-old-price">
									<c:out value="${productDTO.getPrice()}đ" />
								</del>
							</c:if>
							<c:if test="${productDTO.getNewPrice()==null}">
								<c:out value="${productDTO.getPrice()}đ" />
							</c:if>
						</h4>
						<div class="product-rating">
							<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star"></i>
						</div>
						<div class="product-btns">
							<button id="IDsanpham" title="Quan tâm" class="add-to-wishlist"
								tabindex="-1">
								<i class="fa fa-heart-o"></i>
							</button>
							<button title="Xem đánh giá" class="add-to-compare" tabindex="-1">
								<i class="fa fa-star-half-empty"></i>
							</button>
							<button
								data-PId='<c:out value="${productDTO.getProductid()}"></c:out>'
								title="Xem nhanh" class="quick-view btn-View" tabindex="-1"
								data-toggle="modal" data-target="#exampleModal">
								<i class="fa fa-eye"></i>
							</button>
							<button
								data-PId='<c:out value="${productDTO.getProductid()}"></c:out>'
								title="Thêm vào giỏ" class="quick-view btn-add-to-cart"
								tabindex="-1">
								<i class="fa fa-shopping-cart"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<!-- End Display Product -->
		<!-- Detail Product -->
		<div class="modal fade show detail-product" id="exampleModal"
			tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-body">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<div id="detail-product" class="container row"></div>
					</div>
				</div>
			</div>
		</div>
		<!-- End Detail Product -->
	</div>
</div>

<div
	class="align-items-center justify-content-between flex-wrap container"
	style="text-align: center;">
	<a id="viewMore" data-startPosition = '<c:out value="${startPosition }"></c:out>'
		class="btn btn-inverse-light btn-rounded btn-fw btn-see-more">Xem
		thêm</a>
</div>
	<script src="../resouces/ajax-jquery/user-index-search.js"></script>
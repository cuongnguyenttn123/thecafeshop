
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row section social-section">
	<div class="col-lg-6 grid-margin stretch-card">
		<!--form mask starts-->
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Loại bàn</h4>
				<div class="card performance-cards">
					<div class="row">
						<c:set var="i" scope="session" value="1" />
						<c:forEach items="${lists}" var="list">
							<div
								class="col d-flex flex-row justify-content-center align-items-center">
								<div class="wrapper icon-circle bg-danger">
									<i class="icon-loop"></i>
								</div>
								<div class="wrapper text-wrapper">
									<div class="form-radio">
										<label class="form-check-label"> <input type="radio"
											<c:if test = "${i == 1}">
									         checked
									      </c:if>
											value='<c:out value="${list }"></c:out>'
											class="form-check-input countchair" name="countchair">
											Chọn <i class="input-helper"></i></label>
									</div>
									<p>
										Bàn
										<c:out value="${list }"></c:out>
										người
									</p>
								</div>
							</div>
							<c:set var="i" scope="session" value="${ i + 1}" />
						</c:forEach>
					</div>
					<br>
					<div class="row">
						<div class="form-group">
							<label for="tablestatusid" class="col-sm-12 col-form-label">Số
								lượng khác</label>
							<div class="input-group">
								<input id="countPeopleFake" type="number" class="form-control"
									placeholder="Số người">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--form mask ends-->
	</div>
	<div class="col-lg-6 grid-margin stretch-card">
		<!--form mask starts-->
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Thông tin khách hàng</h4>
				<p id="result-form" class="card-description"></p>
				<form class="forms-sample">
					<div class="form-group">
						<label for="tablestatusid" class="col-sm-12 col-form-label">Họ
							tên</label> <input id="countpeople" name="countpeople" type="hidden"
							class="form-control form-control-sm"> <input id="name"
							name="name" type="text" class="form-control form-control-sm"
							placeholder="Họ tên:(*)">
					</div>
					<div class="form-group">
						<label for="tablestatusid" class="col-sm-12 col-form-label">Địa
							chỉ</label> <input id="address" name="address" type="text"
							class="form-control form-control-sm" placeholder="Địa chỉ:(*)">
					</div>
					<div class="form-group">
						<label for="tablestatusid" class="col-sm-12 col-form-label">Số
							điện thọai</label> <input id="phone" name="phone" type="text"
							class="form-control form-control-sm"
							placeholder="Số điện thọai:(*)">
					</div>
					<div class="form-group">
						<label for="tablestatusid" class="col-sm-12 col-form-label">Ngày
							giờ đến</label> <input id="startdatetime" name="startdatetime"
							type="date" class="form-control form-control-sm">
					</div>
					<div class="form-group">
						<label for="notice" class="col-sm-12 col-form-label">Ghi
							chú</label>
						<textarea id="notice" class="form-control" rows="2"
							placeholder="Mô tả sản phẩm">                
          				</textarea>
					</div>
					<button id="btnOrderTable" type="button"
						class="btn btn-success mr-2">Hoàn tất</button>
					<a href="/index" class="btn btn-light">Hủy</a>
				</form>
			</div>
		</div>
		<!--form mask ends-->
	</div>
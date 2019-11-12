<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-lg-12">
		<div class="card px-2">
			<div class="card-body">
				<div  class="col-12">
					<div id="mes"  class="alert alert-success" role="alert">
						<strong ></strong>
					</div>
				</div>
				<!-- <div
					class="container-fluid mt-5 d-flex justify-content-center w-100"> -->
					<div class="table-responsive w-100">
						<table class="table">
							<thead>
								<tr class="bg-dark">
									<th class="text-center text-white">#</th>
									<th class="text-center text-white">CN</th>
									<th class="text-center text-white">T2</th>
									<th class="text-center text-white">T3</th>
									<th class="text-center text-white">T4</th>
									<th class="text-center text-white">T5</th>
									<th class="text-center text-white">T6</th>
									<th class="text-center text-white">T7</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${dtos}" var="dto">
									<tr class="text-right">
										<td class="text-center"><c:out value="${dto.getScheduleid()}" /></td>
										<c:forEach var="i" begin="1" end="7">
											<td class="text-left">
												<div class="form-check">
													<label class="form-check-label"> <input
														data-day='<c:out value="${i}" />'
														data-scheduleid='<c:out value="${dto.getScheduleid()}" />'
														type="checkbox" class="form-check-input schedule">
														<c:out value="${dto.getStarttime()}" />-<c:out
															value="${dto.getEndtime()}" />
											</td>
											<i class="input-helper"></i>
											</label>
											</div>
											</td>
										</c:forEach>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				<!-- </div> -->
				<div class="container-fluid w-100">
					<a id="btnRegister" class="btn btn-primary float-right mt-4 ml-2 text-white">ĐĂNG
						KÝ</a>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-lg-12">
		<div class="card px-2">
			<div class="card-body">
				<div class="col-12">
					<div id="mes" class="alert alert-success" role="alert">
						<strong></strong>
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
							<c:forEach items="${scheduleDTOs}" var="scheduleDTO">
								<tr class="text-right">
									<td class="text-center"><c:out
											value="${scheduleDTO.getScheduleid()}" /></td>
									<c:forEach var="i" begin="1" end="7">
										<td class="text-left">
											<c:forEach items="${dtos}" var="dto">
												<c:if test = "${i == dto.getDay() &&  scheduleDTO.getScheduleid() == dto.getScheduleid()}">
													 <c:forEach items="${dto.getRegisters()}" var="register">
														<div class="form-check">
															<label class="form-check-label"> <input 
																data-registerid = '<c:out value="${register.getRegisterid()}" />'
																type="checkbox" 
																<c:if test = "${register.getResult() == 2}">
																	checked
																</c:if>
																class="form-check-input registerid"> <c:out value="${register.getEmployee().getName()}" /><i
																class="input-helper"></i></label>
														</div>
													</c:forEach>
												</c:if>
											</c:forEach>
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
					<a id="btnAccept" class="btn btn-primary float-right mt-4 ml-2 text-white">DUYỆT</a>
				</div>
			</div>
		</div>
	</div>
</div>
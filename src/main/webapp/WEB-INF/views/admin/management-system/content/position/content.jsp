<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-md-5 d-flex align-items-stretch">
		<div class="row flex-grow">
			<div class="col-12 grid-margin">
				<div class="card">
					<div class="card-body">
						<h4 class="card-title">Chức vụ</h4>
						<p id="result-form" class="card-description"></p>
						<form id="position_form" class="forms-sample">
							<%@ include file="./form.jsp"%>
						</form>
					</div>
				</div>
			</div>

		</div>
	</div>
	<div class="col-md-7 grid-margin stretch-card">
		`
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Danh sách chức vụ</h4>
				<p id="result-tbody" class="card-description">
					<code></code>
				</p>
				<table class="table table-hover">
					<thead>
						<tr>
							<th></th>
							<th>ID</th>
							<th>Tên</th>
							<th>Cập nhập lần cuối</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
				<nav>
					<input id="totalPage"
						value="
						<c:if test="${totalPage == null}">
							1
						</c:if>
						<c:if test="${totalPage != null}">
							<c:out value="${totalPage}" />
						</c:if>"
						type="hidden">
					<ul class="pagination separated pagination-secondary">

					</ul>
				</nav>
			</div>
		</div>
	</div>
</div>
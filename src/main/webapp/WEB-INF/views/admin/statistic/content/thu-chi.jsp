<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-lg-6 grid-margin stretch-card">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Thống kê thu chi theo ngày</h4>
				<canvas id="doughnutChart" style="height: 250px"></canvas>
			</div>
		</div>
	</div>
	<div class="col-lg-6 grid-margin stretch-card">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Thống kê thu chi theo tuần</h4>
				<canvas id="areaChart" style="height: 250px"></canvas>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-6 grid-margin stretch-card">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Thống kê thu chi theo tuần</h4>
				<canvas id="month" style="height: 250px"></canvas>
			</div>
		</div>
	</div>
	<div class="col-lg-6 grid-margin stretch-card">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">Thống kê doanh thu theo năm</h4>
				<canvas id="barChart" style="height: 230px"></canvas>
			</div>
		</div>
	</div>
</div>
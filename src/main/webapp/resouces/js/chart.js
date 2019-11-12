$(function() {
	/*
	 * ChartJS ------- Data and config for chartjs
	 */
	'use strict';
	var obj;
	$.post("/admin/statistic-profit", {}, function(data, status) {

		obj = JSON.parse(data);

	var thongKeNgay = {
		datasets : [ {
			data : [ obj.ngay.tongtienImportBillTrongNgay, obj.ngay.tongtienBillTrongNgay, 30 ],
			backgroundColor : [ 'rgba(255, 99, 132, 0.5)',
					'rgba(54, 162, 235, 0.5)', 'rgba(255, 206, 86, 0.5)',
					'rgba(75, 192, 192, 0.5)', 'rgba(153, 102, 255, 0.5)',
					'rgba(255, 159, 64, 0.5)' ],
			borderColor : [ 'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)' ],
		} ],

		// These labels appear in the legend and in the tooltips when hovering
		// different arcs
		labels : [ 'Phiếu nhập', 'Hoá đơn',/* 'Yellow',*/ ]
	};
	
	var dataTongtienBillTrongTuan=[];
	var dataTongtienImportBillTrongTuan=[];
	var i;
	for(i=0; i<7; i++){
		dataTongtienBillTrongTuan[i] = obj.tuan[i].tongtienBillTrongTuan;
		dataTongtienImportBillTrongTuan[i] = obj.tuan[i].tongtienImportBillTrongTuan;
	}
	var thongkeTuan = {
			labels : [ "CN", "T2", "T3", "T4", "T5", "T6", "T7" ],
			datasets : [ {
				label : 'Hóa đơn',
				data : dataTongtienBillTrongTuan,
				borderColor : [ '#587ce4' ],
				backgroundColor : [ '#587ce4' ],
				borderWidth : 2,
				fill : false
			},  {
				label : 'Phiếu nhập',
				data : dataTongtienImportBillTrongTuan,
				borderColor : [ '#f44252' ],
				backgroundColor : [ '#f44252' ],
				borderWidth : 2,
				fill : false
			} ]
		};
	
	var dataTongtienBillTrongThang=[];
	var dataTongtienImportBillTrongThang=[];
	var i;
	for(i=0; i<4; i++){
		dataTongtienBillTrongThang[i] = obj.thang[i].tongtienBillTrongThang;
		dataTongtienImportBillTrongThang[i] = obj.thang[i].tongtienImportBillTrongThang;
	}
	var thongkeThang = {
			labels : [ "Tuần 1", "Tuần 2", "Tuần 3", "Tuần 4"],
			datasets : [ {
				label : 'Hóa đơn',
				data : dataTongtienBillTrongThang,
				borderColor : [ '#587ce4' ],
				backgroundColor : [ '#587ce4' ],
				borderWidth : 2,
				fill : false
			},  {
				label : 'Phiếu nhập',
				data : dataTongtienImportBillTrongThang,
				borderColor : [ '#f44252' ],
				backgroundColor : [ '#f44252' ],
				borderWidth : 2,
				fill : false
			} ]
		};
	
	var dataDoanhThuTrongNam=[];
	var i;
	for(i=0; i<12; i++){
		var tienBill = obj.nam[i].tongtienBillTrongNam;
		var tienImportBill = obj.nam[i].tongtienImportBillTrongNam;
		dataDoanhThuTrongNam[i] = tienBill - tienImportBill;
	}
	var thongkeNam = {
			labels : [ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" ],
			datasets : [ {
				label : 'Doanh thu',
				data : dataDoanhThuTrongNam,
				backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
						'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
						'rgba(255, 159, 64, 0.2)','rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
						'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
						'rgba(255, 159, 64, 0.2)' ],
				borderColor : [ 'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)',
						'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)',
						'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)', 
						'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)',
						'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)',
						'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)' ],
				borderWidth : 1
			} ]
		};
	
	
	
	
	
	var data = {
		labels : [ "2013", "2014", "2014", "2015", "2016", "2017" ],
		datasets : [ {
			label : '# of Votes',
			data : [ 10, 19, 3, 5, 2, 3 ],
			backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
					'rgba(255, 159, 64, 0.2)' ],
			borderColor : [ 'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)' ],
			borderWidth : 1
		} ]
	};
	var multiLineData = {
		labels : [ "Red", "Blue", "Yellow", "Green", "Purple", "Orange" ],
		datasets : [ {
			label : 'Dataset 1',
			data : [ 12, 19, 3, 5, 2, 3 ],
			borderColor : [ '#587ce4' ],
			borderWidth : 2,
			fill : false
		}, {
			label : 'Dataset 2',
			data : [ 5, 23, 7, 12, 42, 23 ],
			borderColor : [ '#ede190' ],
			borderWidth : 2,
			fill : false
		}, {
			label : 'Dataset 3',
			data : [ 15, 10, 21, 32, 12, 33 ],
			borderColor : [ '#f44252' ],
			borderWidth : 2,
			fill : false
		} ]
	};
	var options = {
		scales : {
			yAxes : [ {
				ticks : {
					beginAtZero : true
				}
			} ]
		},
		legend : {
			display : false
		},
		elements : {
			point : {
				radius : 0
			}
		}

	};
	var doughnutPieData = {
		datasets : [ {
			data : [ 30, 40, 30 ],
			backgroundColor : [ 'rgba(255, 99, 132, 0.5)',
					'rgba(54, 162, 235, 0.5)', 'rgba(255, 206, 86, 0.5)',
					'rgba(75, 192, 192, 0.5)', 'rgba(153, 102, 255, 0.5)',
					'rgba(255, 159, 64, 0.5)' ],
			borderColor : [ 'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)' ],
		} ],

		// These labels appear in the legend and in the tooltips when hovering
		// different arcs
		labels : [ 'Pink', 'Blue', 'Yellow', ]
	};
	var doughnutPieOptions = {
		responsive : true,
		animation : {
			animateScale : true,
			animateRotate : true
		}
	};
	var browserTrafficData = {
		datasets : [ {
			data : [ 20, 20, 10, 30, 20 ],
			backgroundColor : [ 'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)',
					'rgba(75, 192, 117, 1)', 'rgba(255, 159, 64, 1)' ],
			borderColor : [ 'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)',
					'rgba(75, 192, 117, 1)', 'rgba(255, 159, 64, 1)' ],
		} ],

		// These labels appear in the legend and in the tooltips when hovering
		// different arcs
		labels : [ 'Firefox', 'Safari', 'Explorer', 'Chrome', 'Opera Mini' ]
	};
	var areaData = {
		labels : [ "2013", "2014", "2015", "2016", "2017" ],
		datasets : [ {
			label : '# of Votes',
			data : [ 12, 19, 3, 5, 2, 3 ],
			backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
					'rgba(255, 159, 64, 0.2)' ],
			borderColor : [ 'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)' ],
			borderWidth : 1,
			fill : 'origin', // 0: fill to 'origin'
			fill : '+2', // 1: fill to dataset 3
			fill : 1, // 2: fill to dataset 1
			fill : false, // 3: no fill
			fill : '-2' // 4: fill to dataset 2
		} ]
	};

	var areaOptions = {
		plugins : {
			filler : {
				propagate : true
			}
		}
	}

	var multiAreaData = {
		labels : [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
				"Sep", "Oct", "Nov", "Dec" ],
		datasets : [ {
			label : 'Facebook',
			data : [ 8, 11, 13, 15, 12, 13, 16, 15, 13, 19, 11, 14 ],
			borderColor : [ 'rgba(255, 99, 132, 0.5)' ],
			backgroundColor : [ 'rgba(255, 99, 132, 0.5)' ],
			borderWidth : 1,
			fill : true
		}, {
			label : 'Twitter',
			data : [ 7, 17, 12, 16, 14, 18, 16, 12, 15, 11, 13, 9 ],
			borderColor : [ 'rgba(54, 162, 235, 0.5)' ],
			backgroundColor : [ 'rgba(54, 162, 235, 0.5)' ],
			borderWidth : 1,
			fill : true
		}, {
			label : 'Linkedin',
			data : [ 6, 14, 16, 20, 12, 18, 15, 12, 17, 19, 15, 11 ],
			borderColor : [ 'rgba(255, 206, 86, 0.5)' ],
			backgroundColor : [ 'rgba(255, 206, 86, 0.5)' ],
			borderWidth : 1,
			fill : true
		} ]
	};

	var multiAreaOptions = {
		plugins : {
			filler : {
				propagate : true
			}
		},
		elements : {
			point : {
				radius : 0
			}
		},
		scales : {
			xAxes : [ {
				gridLines : {
					display : false
				}
			} ],
			yAxes : [ {
				gridLines : {
					display : false
				}
			} ]
		}
	}

	var scatterChartData = {
		datasets : [ {
			label : 'First Dataset',
			data : [ {
				x : -10,
				y : 0
			}, {
				x : 0,
				y : 3
			}, {
				x : -25,
				y : 5
			}, {
				x : 40,
				y : 5
			} ],
			backgroundColor : [ 'rgba(255, 99, 132, 0.2)' ],
			borderColor : [ 'rgba(255,99,132,1)' ],
			borderWidth : 1
		}, {
			label : 'Second Dataset',
			data : [ {
				x : 10,
				y : 5
			}, {
				x : 20,
				y : -30
			}, {
				x : -25,
				y : 15
			}, {
				x : -10,
				y : 5
			} ],
			backgroundColor : [ 'rgba(54, 162, 235, 0.2)', ],
			borderColor : [ 'rgba(54, 162, 235, 1)', ],
			borderWidth : 1
		} ]
	}

	var scatterChartOptions = {
		scales : {
			xAxes : [ {
				type : 'linear',
				position : 'bottom'
			} ]
		}
	}

	if ($("#lineChart").length) {
		var lineChartCanvas = $("#lineChart").get(0).getContext("2d");
		var lineChart = new Chart(lineChartCanvas, {
			type : 'line',
			data : data,
			options : options
		});
	}

	if ($("#areachart-multi").length) {
		var multiAreaCanvas = $("#areachart-multi").get(0).getContext("2d");
		var multiAreaChart = new Chart(multiAreaCanvas, {
			type : 'line',
			data : multiAreaData,
			options : multiAreaOptions
		});
	}

	// Thống kê thu chi theo ngày
	if ($("#doughnutChart").length) {
		var doughnutChartCanvas = $("#doughnutChart").get(0).getContext("2d");
		var doughnutChart = new Chart(doughnutChartCanvas, {
			type : 'doughnut',
			data : thongKeNgay,
			options : doughnutPieOptions
		});
	}
	// Thống kê thu chi theo tuần
	if ($("#areaChart").length) {
		var areaChartCanvas = $("#areaChart").get(0).getContext("2d");
		var areaChart = new Chart(areaChartCanvas, {
			type : 'line',
			data : thongkeTuan,
			options : areaOptions
		});
	}
	
	// Thống kê thu chi theo tháng
	if ($("#month").length) {
		var areaChartCanvas = $("#month").get(0).getContext("2d");
		var areaChart = new Chart(areaChartCanvas, {
			type : 'line',
			data : thongkeThang,
			options : areaOptions
		});
	}
	// Thống kê theo năm
	// Get context with jQuery - using jQuery's .get() method.
	if ($("#barChart").length) {
		var barChartCanvas = $("#barChart").get(0).getContext("2d");
		// This will get the first returned node in the jQuery collection.
		var barChart = new Chart(barChartCanvas, {
			type : 'bar',
			data : thongkeNam,
			options : options
		});
	}
	
	if ($("#linechart-multi").length) {
		var multiLineCanvas = $("#linechart-multi").get(0).getContext("2d");
		var lineChart = new Chart(multiLineCanvas, {
			type : 'line',
			data : thongkeTuan,
			options : options
		});
	}

	if ($("#pieChart").length) {
		var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
		var pieChart = new Chart(pieChartCanvas, {
			type : 'pie',
			data : doughnutPieData,
			options : doughnutPieOptions
		});
	}

	if ($("#scatterChart").length) {
		var scatterChartCanvas = $("#scatterChart").get(0).getContext("2d");
		var scatterChart = new Chart(scatterChartCanvas, {
			type : 'scatter',
			data : scatterChartData,
			options : scatterChartOptions
		});
	}

	if ($("#browserTrafficChart").length) {
		var doughnutChartCanvas = $("#browserTrafficChart").get(0).getContext(
				"2d");
		var doughnutChart = new Chart(doughnutChartCanvas, {
			type : 'doughnut',
			data : browserTrafficData,
			options : doughnutPieOptions
		});
	}

	// if ($("#radarChart").length) {
	// var myRadarChartCanvas = $("#radarChart").get(0).getContext("2d");
	// var myRadarChart = new Chart(ctx, {
	// type: 'radar',
	// data: {
	// labels: ['Running', 'Swimming', 'Eating', 'Cycling'],
	// datasets: [{
	// data: [20, 10, 4, 2]
	// }]
	// },
	// options = {
	// scale: {
	// // Hides the scale
	// display: false
	// }
	// }
	// });
	// }
	});
});
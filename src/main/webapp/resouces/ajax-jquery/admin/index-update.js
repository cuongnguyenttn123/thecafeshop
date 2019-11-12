/**
 * 
 */

$(".remove").click(function () {
	var id=$(this).attr('data-productid');
	$('#'+id).remove();
});

$("#btnCheckVoucher").click(function() {
	$.post("/admin/index-checkVoucher", {
		voucherName : $('#voucherName').val(),
		billid : $('#billid').val()
	}, function(data, status) {
		var obj = JSON.parse(data);
		$("#discount").text('Giảm giá: -' + obj.discount + 'đ');
		$("#totalPriceOLD").text('Giá cũ: ' + obj.totalPriceOLD + 'đ');
		$("#totalPriceNEW").text('Tổng tiền: ' + obj.totalPriceNEW + 'đ');
	});
});

$("#btnDoiBan").click(function() {
	var dinnertableidOLD = $("#dinnertableid").val();
	var dinnertableid = $("#selectDINNERTABLEID").val();
	$.post("/admin/index-changeTable", {
		dinnertableid : dinnertableid,
		billid : $('#billid').val()
	}, function(data, status) {
		var obj = JSON.parse(data);
		_effectTrangThaiBan(obj[0].id, obj[0].name, dinnertableid );
		_effectTrangThaiBan(obj[1].id, obj[1].name, dinnertableidOLD );
	});
});

$("#btnSearch").click(function() {
	var inputSearch = $('#inputSearch').val();
	$.get("/admin/index-modal", {
		dinnertableid : $("#dinnertableid").val(),
		startPosition : 0,
		inputSearch : $('#inputSearch').val()
	}, function(data, status) {
		$('.modal-body').html(data);
	});
});

$("#btnCapNhatTrangThaiBan").click(function() {
var dinnertableid = $("#dinnertableid").val();
	$.post("/admin/index-updateTableStatus", {
		dinnertableid,
		tablestatusid : $('input[name="tablestatusid"]:checked').val()
	}, function(data, status) {
		var obj = JSON.parse(data);
		
		var textClass, mes, name;
		mes = obj.mes;
		if(obj.id == undefined){
			textClass  ='badge badge-danger';
		}
		else{
			textClass  ='badge badge-success';
			_effectTrangThaiBan(obj.id, obj.name, dinnertableid);
		}
		$('#exampleModalLabel div').attr("class","");
		$('#exampleModalLabel div').attr("class",textClass);
		$('#exampleModalLabel div').html(mes);
		$("#exampleModalLabel").show();

		$(".stretch-card").click();
	});
});

$("#btnThemVaoHoaDon").click(function() {
					var countTr = $(".add-food .stretch-card").length;
					var list = [];
					for (var i = 0; i < countTr; i++) {
						list[i] = {
							productid : $(
									".add-food .stretch-card input:eq(" + i
											+ ")").attr('data-productid'),
							quantity : $(
									".add-food .stretch-card input:eq(" + i
											+ ")").val()
						};
					}
					var listProduct = {
						listProduct : list
					};
					$.post("/admin/index-updateBillDetail", {
						dinnertableid : $("#dinnertableid").val(),
						listProduct : JSON.stringify(listProduct)
					}, function(data, status) {
						$('#exampleModalLabel').html(data);
						$("#exampleModalLabel").fadeToggle(3000);

						$(".stretch-card").click();
					});
				});

$("#btnLuuChinhSua").click(function () {
	
	var countTr = $("#bill tbody tr").length;
	var list = [];
	for (var i = 0; i < countTr; i++) {
		list[i] = {
			productid : $( "#bill tbody tr:eq(" + i + ")").attr('data-productid')
		};
	}
	var listProduct = {
		listProduct : list
	};
	
	$.post("/admin/index-updateBill", {
		billid : $('#billid').val(),
		listProduct : JSON.stringify(listProduct)
	}, function(data, status) {
		$('#exampleModalLabel').html(data);
		$("#exampleModalLabel").fadeToggle(3000);

		$(".stretch-card").click();
	});
});

$("#btnThanhToan").click(function() {
	$("#btnLuuChinhSua").click();
	$.post("/admin/index-updateBillStatus", {
		billid : $('#billid').val(),
		voucherName : $('#voucherName').val()
	}, function(data, status) {
		$('#exampleModalLabel').html(data);
		$("#exampleModalLabel").fadeToggle(3000);

		$(".stretch-card").click();
	});
});

function _effectTrangThaiBan(id, name, dinnertableid){
	var background, icon;
	switch (id) {
	case 1:
		background = 'social-card w-100 none-table-status';
		icon = 'icon-status-table-none';
		break;
	case 2:
		background = 'social-card w-100 full-table-status';
		icon = 'icon-status-table-menu';
		break;
	case 3:
		background = 'social-card w-100 full-table-status';
		icon = 'icon-status-table-dangnau';
		break;
	case 4:
		background = 'social-card w-100 ordered-table-status';
		icon = 'icon-status-table-banconguoidat';
		break;
	case 5:
		background = 'social-card w-100 free-table-status';
		icon = 'icon-status-table-ghetrong';
		break;
	case 6:
		background = 'social-card w-100 full-table-status';
		icon = 'icon-status-table-donmon';
		break;
	case 7:
		background = 'social-card w-100 full-table-status';
		icon = 'icon-status-table-dangan';
		break;

	default:
		background = 'social-card w-100 free-table-status';
		icon = 'icon-status-table-ghetrong';
		break;
	}

	$('#'+dinnertableid + ' p:eq(1)').text(name);
	$('#'+dinnertableid + ' div:eq(0)').attr("class","");
	$('#'+dinnertableid + ' div:eq(0)').addClass(background);
}

function _modalContent(dinnertableid, startPosition, inputSearch) {
	$.get("/admin/index-modal", {
		dinnertableid,
		startPosition,
		inputSearch
	}, function(data, status) {
		$('.modal-body').html(data);
	});
}

_loadPagination(1);// at loadTable.js
hide_all_Pagination();
_hide_show_Pagination(1, "right");// at loadTable.js

$(".pa").click(function() {
	$(".active").removeClass("active");
	$(this).addClass("active");

	_modalContent($("#dinnertableid").val(), $(this).attr("data-startPosition"), '');
});

$(".right").click(
		function() {
			$(".active").removeClass("active");
			$(
					"[data-startPosition='"
							+ $(this).attr("data-startPosition") + "']")
					.addClass("active");
			
			_modalContent($("#dinnertableid").val(), $(this).attr("data-startPosition"), '');
			_hide_show_Pagination($(this).attr("data-startPosition"), "right");// at
			// loadTable.js
		});
$(".left").click(function() {

	_modalContent($("#dinnertableid").val(), $(this).attr("data-startPosition"), '');
	_hide_show_Pagination($(this).attr("data-startPosition"), "left");// at
	// loadTable.js
});
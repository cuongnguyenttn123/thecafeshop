$(function() {

	var link = "/admin/warehouse-import-material/table";

	function _list(link, startPosition) {
		$.get(link, {
			startPosition : startPosition
		}, function(data, status) {
			$("#tbody").html(data);
		});
	};
	
	$("#materialid").change(function() {
		alert();
	});
	
	$("#btnSave").click(function() {
		// materialid : $("#materialid").val(),
		// quantity : $("#quantity").val(),
		// dateofmanufacture : $("#dateofmanufacture").val(),
		// expirationdate: $("#expirationdate").val()

		var countTr = $("#import_material_form table tbody tr").length;
		// alert($("#import_material_form table tbody tr:eq(2)
		var list = [];
		var k = 0;
		for(var j=1; j< countTr; j++){
			list[k] = {
					materialid : $("#import_material_form table tbody tr:eq("+j+") #materialid").val(),
					dateofmanufacture :  $("#import_material_form table tbody tr:eq("+j+") #dateofmanufacture").val(),
					expirationdate :  $("#import_material_form table tbody tr:eq("+j+") #expirationdate").val(),
					quantity :  $("#import_material_form table tbody tr:eq("+j+") #quantity").val(),
					price :  $("#import_material_form table tbody tr:eq("+j+") #price").val()
				};
			k++;
		}
		var listMaterialDetail = {
			listMaterialDetail : list
		};

		$.post("/admin/warehouse-import-material/insert", {
			supplierid : $("#supplierid").val(),
			listMaterialDetail : JSON.stringify(listMaterialDetail)
		}, function(data, status) {
			$("#result-form").html(data);
			$("#result-form").fadeToggle(3000);
			// Pagination không tự load được nên phải tặng thêm 1 khi thêm thành
			// công
			if (data.indexOf("success") != -1) {
				$("totalPage").val($("totalPage").val() + 1);
			}
			// _list(link, 1);// at loadTable.js
		});
	});

	$("#btnClear").click(function() {
		document.getElementById("billStatus_form").reset();
	});

	_list(link, 1);// at loadTable.js
	_loadPagination(1);// at loadTable.js
	hide_all_Pagination();
	_hide_show_Pagination(1, "right");// at loadTable.js

	$(".pa").click(function() {
		$(".active").removeClass("active");
		$(this).addClass("active");
		_list(link, $(this).attr("data-startPosition"));
	});

	$(".right").click(
			function() {
				$(".active").removeClass("active");
				$(
						"[data-startPosition='"
								+ $(this).attr("data-startPosition") + "']")
						.addClass("active");
				_list(link, $(this).attr("data-startPosition"));
				_hide_show_Pagination($(this).attr("data-startPosition"),
						"right");// at
				// loadTable.js
			});
	$(".left").click(function() {
		_list(link, $(this).attr("data-startPosition"));
		_hide_show_Pagination($(this).attr("data-startPosition"), "left");// at
		// loadTable.js
	});

});

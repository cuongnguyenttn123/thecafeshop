$(function() {
	var iRemove = $("#import_material_form table tbody tr:last label").attr("data-remove-import");
	
	var i = 1;
	$("#btn-Add-Row").click(
			function() {
				var htmlText = $(".-1").html();
				if(iRemove == -1){
					iRemove = 1;
				}
				$("#import_material_form table tbody").append(
						"<tr class='"+ iRemove++ +"'>" + htmlText + "</tr>");
				$("#import_material_form table tbody tr:last label").attr(
						"data-remove-import", iRemove++);
			});

	
	$(".remove-import").click(function() {
		alert();
	});

	$("#btnUpdate").click(
			function() {
				// materialid : $("#materialid").val(),
				// quantity : $("#quantity").val(),
				// dateofmanufacture : $("#dateofmanufacture").val(),
				// expirationdate: $("#expirationdate").val(),
				// materialdetailid: $("#materialdetailid").val(),
				// importbillid: $("#importbillid").val()

				var countTr = $("#import_material_form table tbody tr").length;
				// alert($("#import_material_form table tbody tr:eq(2)
				var list = [];
				var k = 0;
				for (var j = 1; j < countTr; j++) {// bỏ tr đầu tiên(tr đầu
					// tiên là nháp)

					list[k] = {
						materialid : $(
								"#import_material_form table tbody tr:eq(" + j
										+ ") #materialid").val(),
						dateofmanufacture : $(
								"#import_material_form table tbody tr:eq(" + j
										+ ") #dateofmanufacture").val(),
						expirationdate : $(
								"#import_material_form table tbody tr:eq(" + j
										+ ") #expirationdate").val(),
						quantity : $(
								"#import_material_form table tbody tr:eq(" + j
										+ ") #quantity").val(),
						materialdetailid : $(
								"#import_material_form table tbody tr:eq(" + j
										+ ") #materialdetailid").val(),
						price :  $("#import_material_form table tbody tr:eq("+j+") #price").val()
					};
					k++;
				}
				var listMaterialDetail = {
					listMaterialDetail : list
				};

				$.post("/admin/warehouse-import-material/edit", {
					importbillid : $("#importbillid").val(),
					listMaterialDetail : JSON.stringify(listMaterialDetail)
				}, function(data, status) {
					$("#result-form").html(data);
					$("#result-form").fadeToggle(3000);
					// Pagination không tự load được nên phải tặng thêm 1 khi
					// thêm thành
					// công
					if (data.indexOf("success") != -1) {
						$("totalPage").val($("totalPage").val() + 1);
					}
					// _list(link, 1);// at loadTable.js
				});
			});
});
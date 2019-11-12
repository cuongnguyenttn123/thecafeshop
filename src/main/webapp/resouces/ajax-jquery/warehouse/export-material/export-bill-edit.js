$(function() {
	var iRemove = $("#import_material_form table tbody tr:last label").attr("data-remove-import");
	
	var i = 1;
	$("#btn-Add-Row").click(
			function() {
				var htmlText = $(".-1").html();
				$("#export_material_form table tbody").append(
						"<tr>" + htmlText + "</tr>");

				if(iRemove == -1){
					iRemove = 1;
				}
				$("#export_material_form table tbody tr:last label").attr(
						"data-remove-export", iRemove++);
			});

	
	$(".remove-import").click(function() {
		alert();
	});

	$("#btnUpdate").click(
			function() {
				// materialid : $("#materialid").val(),
				// quantity : $("#quantity").val(),
				// materialdetailid: $("#materialdetailid").val(),
				// exportbillid: $("#exportbillid").val()

				var countTr = $("#export_material_form table tbody tr").length;
				// alert($("#import_material_form table tbody tr:eq(2)
				var list = [];
				var k = 0;
				for (var j = 1; j < countTr; j++) {// bỏ tr đầu tiên(tr đầu
					// tiên là nháp)
					list[k] = {
						materialid : $("#export_material_form table tbody tr:eq(" + j + ") #materialid").val(),
						quantity : $( "#export_material_form table tbody tr:eq(" + j + ") #quantity").val(),
						materialdetailid : $( "#export_material_form table tbody tr:eq(" + j + ") #materialdetailid").val(),
						price :  $("#export_material_form table tbody tr:eq("+j+") #price").val()
					};
					k++;
				}
				var listMaterialDetail = {
					listMaterialDetail : list
				};

				$.post("/admin/warehouse-export-material/edit", {
					exportbillid : $("#exportbillid").val(),
					quantityProduct : $("#quantityProduct").val(),
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
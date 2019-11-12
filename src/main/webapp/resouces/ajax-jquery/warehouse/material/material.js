/**
 * 
 */

$(function () {
	
	$(".delete").hide();
	
	$("#btnSearch").click(function() {
		$.post("/admin/warehouse-material", {
			materialdetailid:$("#materialdetailid").val(),
			name:$("#name").val()
		}, function(data, status) {
			$("#content-index").html(data);
		});
	});
});

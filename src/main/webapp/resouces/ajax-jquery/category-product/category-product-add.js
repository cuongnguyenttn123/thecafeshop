$(function() {
	$(".remove").click(function() {
		$.post("/admin/category-product/remove", {
			categoryproductid : $(this).attr("data-categoryproductid")
		}, function(data, status) {
			$("#result-tbody").html(data);
			$("#result-tbody").fadeToggle(3000);
			_list(link, 1);
		});
	});

	$(".edit").click(function() {
		$.get("/admin/category-product/edit", {
			categoryproductid : $(this).attr("data-categoryproductid")
		}, function(data, status) {
			$("#categoryProduct_form").html(data);
		});
	});
});
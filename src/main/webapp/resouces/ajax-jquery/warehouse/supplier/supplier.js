$(function() {
	
	var link = "/admin/warehouse-supplier/table";

	$("#btnSave").click(function() {
		$.post("/admin/warehouse-supplier/insert", {
			name : $("#name").val(),
			phone : $("#phone").val(),
			address : $("#address").val()
		}, function(data, status) {
			$("#result-form").html(data);
			$("#result-form").fadeToggle(3000);
			//Pagination không tự load được nên phải tặng thêm 1 khi thêm thành công
			if(data.indexOf("success") != -1){
				$("totalPage").val($("totalPage").val() + 1);
			}
//			_list(link, 1);// at loadTable.js
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

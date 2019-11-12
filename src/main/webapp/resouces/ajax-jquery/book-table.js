/**
 * 
 */

$(function() {
	
	// gán giá trị cho input hidden khi click radio
	$(".countchair").click(function () {
		$("#countpeople").val($(this).val());
	});
	$("#countPeopleFake").change(function () {
		$("#countpeople").val($(this).val());
	});
	// gán giá trị cho input hidden khi click radio[END]
	
	$("#btnOrderTable").click(function() {
		$.post("/book-table", {
			name : $("#name").val(),
			address : $("#address").val(),
			phone : $("#phone").val(),
			startdatetime : $("#startdatetime").val(),
			countpeople : $("#countpeople").val(),
			notice : $("#notice").val()
		}, function(data, status) {
			$("#result-form").html(data);
		});
		
	});
});
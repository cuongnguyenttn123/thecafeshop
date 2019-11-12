$(function() {

	$("#btnUpdate").click(function() {
		var sex = "female";
		if ($('#Male').prop('checked')) {
			sex = "male";
		}
		$.post("/admin/employee/edit", {
			employeeid : $("#employeeid").val(),
			name : $("#name").val(),
			sex : sex,
			phone : $("#phone").val(),
			address : $("#address").val(),
			position : $("#position").val(),
			salaryonhour : $("#salaryonhour").val(),
			startdate : $("#startdate").val()
		}, function(data, status) {
			$("#result-form").html(data);
			$("#result-form").fadeToggle(3000);
			_list(link, 1);// at loadTable.js
		});
	});
});
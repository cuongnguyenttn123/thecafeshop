$(function() {

	$("#btnUpdate").click(function() {
		$.post("/admin/dinner-table/edit", {
			dinnertableid : $("#dinnertableid").val(),
			name : $("#name").val(),
			countchair : $("#countchair").val()
		}, function(data, status) {
			$("#result-form").html(data);
			$("#result-form").fadeToggle(3000);
//			_list(link, 1);// at loadTable.js
		});
	});
});
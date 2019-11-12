/**
 * 
 */
$("#mes").hide();
$("#btnRegister").hide();

$(".schedule").change(function() {
	if($(".schedule").is(":checked")){
		$("#btnRegister").show();
	}
	if(!$(".schedule").is(":checked")){
		$("#btnRegister").hide();
	}
});

$("#btnRegister").click(function() {
	var count = $(".schedule").length;
	var list = [];
	var k = 0;
	for (var i = 0; i < count; i++) {
		if($(".schedule:eq(" + i + ")").prop("checked")){
			var day =$(".schedule:eq(" + i + ")").attr("data-day");
			var scheduleid = $(".schedule:eq(" + i + ")").attr("data-scheduleid");
			list[k] = {
					day :  day,
					scheduleid : scheduleid
				};
			k++;
		}
	}
	var listRegister = {
			listRegister : list
		};
	$.post("/admin/register-schedule", {
		listRegister : JSON.stringify(listRegister)
	}, function(data, status) {
		$("#mes").show();
		$("#mes strong").text(data);
	});
});
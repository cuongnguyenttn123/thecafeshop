/**
 * 
 */


$("#btnAccept").click(function() {
	var count = $(".registerid").length;
	var list = [];
	var k = 0;
	for (var i = 0; i < count; i++) {
			var registerid =$(".registerid:eq(" + i + ")").attr("data-registerid");
			var result = 0;
			if($(".registerid:eq(" + i + ")").prop("checked")){
				result = 2;
			}
			list[k] = {
					registerid :  registerid,
					result : result
				};
			k++;
	}
	var listRegisterid = {
			listRegisterid : list
		};
	$.post("/admin/accept-register", {
		listRegisterid : JSON.stringify(listRegisterid)
	}, function(data, status) {
		$("#mes").show();
		$("#mes strong").text(data);
	});
});
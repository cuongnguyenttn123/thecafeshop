$(function () {
    $("tbody input[type=number]").change(function(){
        var listNumberProduct="";
		var num = $("tbody input[type=number]").length;
		var input = $("tbody input[type=number]");
		for (var i = 0; i < num; i++) {
			listNumberProduct += input.eq(i).val()+"~";
        }

        $("#listNumberProduct2").val(listNumberProduct);
    });
});
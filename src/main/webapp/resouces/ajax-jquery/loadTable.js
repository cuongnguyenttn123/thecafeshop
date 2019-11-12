
var numberPagination = 3;

function _list(link, startPosition) {
	$.get(link, {
		startPosition : startPosition
	}, function(data, status) {
		$("tbody").html(data);
	});
};

function _loadPagination(startPosition) {
	var temb = startPosition;
	var totalPage = $("#totalPage").val().trim();
	var first = parseInt(startPosition / numberPagination * numberPagination
			- 1);
	if (first <= 0) {
		first = 1;
	}
	var last = parseInt( parseInt(startPosition) + numberPagination);
	if (last > totalPage) {
		last = totalPage;
	}
	var textHTML = '<li class="page-item left" data-startPosition="'
			+ first
			+ '"><a class="page-link" ><i class="mdi mdi-chevron-left" ></i></a></li>';
	while (temb <= totalPage) {
		var active;
		if (temb == startPosition) {
			active = "active";
		}
		textHTML += '<li class="page-item pa' + active + '" data-startPosition="'
				+ temb + '"><a class="page-link" >' + temb + '</a></li>';
		active = "";
		temb++;
	}
	textHTML += '<li class="page-item right" data-startPosition="'
			+ last
			+ '"><a class="page-link"><i class="mdi mdi-chevron-right" ></i></a></li>';
	$(".pagination").html(textHTML);
};
function hide_all_Pagination() {

	var totalPage = $("#totalPage").val().trim();
	
	for(var i= 1; i<= totalPage;i++){
		$("[data-startPosition='" + i + "']").hide();
	}
}
function _hide_show_Pagination(startPosition, rightOrLeft) {

	var totalPage = $("#totalPage").val().trim();
	var first = (parseInt(startPosition) - numberPagination);
	if(first < 0){
		first = 1;
	}
	var last =parseInt(startPosition) + numberPagination;
	if(last >= totalPage){
		last = totalPage;
	}
	$(".left").attr("data-startPosition", first );
	$(".right").attr("data-startPosition", last );
	var temb = first;
	// nhấn nút right
	if(rightOrLeft == "right"){
		while (temb < startPosition) {
			$("[data-startPosition='" + temb + "']").hide();
			temb++;
		}
	}
	if(rightOrLeft == "left"){
		// nhấn nút left
		var temb = first +  numberPagination;
		while (temb < last + numberPagination) {
		$("[data-startPosition='" + temb + "']").hide();
		temb++;
	}
}
	$(".left").show();
	while (startPosition < last) {
		$("[data-startPosition='" + startPosition + "']").show();
		startPosition++;
	}
	if(startPosition == totalPage){
		$("[data-startPosition='" + startPosition + "']").show();
	}
	//hết trang
	
//	$(".right[data-startPosition='" + startPosition + "']").show();
	$(".right").show();
};

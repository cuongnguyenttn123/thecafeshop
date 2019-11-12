/**
 * 
 */

$(function() {

	function _searchPage(page) {
		var cgPrdId = $("#selectCategoryProduct").val();
		var strSearch = $("#inputSearch").val();
		var isHotDeal = $("#isHotDeal").val();
		var priceAZ = $("#priceAZ").val();
		var priceZA = $("#priceZA").val();

		 $.post("/index/search",{
			page,
			cgPrdId,
			strSearch,
			isHotDeal,
			priceAZ,
			priceZA
			}, function(data, status){
				// if(data==null){
					$("#content-index").html(data);
				// }
				// else{
				// 	$("#content-index").prepend(data);
				// }
		});
	};
	
	/*page seacrch*/
	$("#selectCategoryProduct").change(function() {
		$("#cgPrdId").val($(this).val());
	});
	
	$("#inputIsHotDeal").click(function() {
		$("#isHotDeal").val("true");
	});
	
	$("#inputPriceAZ").change(function() {
		$("#priceAZ").val("true");
	});
	
	$("#inputPriceZA").change(function() {
		$("#priceZA").val("true");
	});
	
	$("#viewMore").click(function() {
		_searchPage($("#viewMore").attr("data-startPosition"));
	});
	
	$("#btnSearch").click(function() {
		_searchPage(0);
	});
	/*page seacrch[END]*/
	
})

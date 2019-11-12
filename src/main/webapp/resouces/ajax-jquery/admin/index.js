/**
 * 
 */

$(".stretch-card").click(function() {
	$("#dinnertableid").val($(this).attr("data-dinnertableid"));
	
	var dinnertableid = $(this).attr("data-dinnertableid");
	_modalContent(dinnertableid, 0, "");
	
	$('#exampleModal').modal('show');
});

$("#close-modal").click(function() {
	$('.modal-body').html('');
	 $('#exampleModal').modal('hide');
});

function _modalContent(dinnertableid, startPosition, inputSearch) {
	$.get("/admin/index-modal", {
		dinnertableid,
		startPosition,
		inputSearch
	}, function(data, status) {
		$('.modal-body').html(data);
	});
}





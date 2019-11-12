/**
 * 
 */


$(function () {

	document.querySelector("#biDatetimeStart").valueAsDate = new Date();
	
	/*total price (thành tiền)*/
	$("tbody tr td input").change(function(){
		var number = $(this).val();
		var price = $(this).closest('tr').find('td:nth-child(3)').text().trim();
		var totalPrice = number * price; 

		$(this).closest('tr').find('td:nth-child(4)').text(totalPrice);
	});
	/*total price (thành tiền)[END]*/
	
});
$(function(){
	
    $(".remove").click(function() {
        $.post("/admin/product/remove",{
            productid : $(this).attr("data-productid")
        }, function(data, status){
            $("#result-tbody").html(data);
			$("#result-tbody").fadeToggle(3000);
			_list(link,1);// at loadTable.js
        });
    });
    
    $(".edit").click(function() {
        $.get("/admin/product/edit",{
            productid : $(this).attr("data-productid")
         }, function(data, status){
             $("#product_form").html(data);
         });
    });
    
    
});
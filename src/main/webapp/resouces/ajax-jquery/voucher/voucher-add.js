$(function(){

    $(".remove").click(function() {
        $.post("/admin/voucher/remove",{
            voucherid : $(this).attr("data-voucherid")
        }, function(data, status){
            $("#result-tbody").html(data);
			$("#result-tbody").fadeToggle(3000);
			_list(link,1);// at loadTable.js
        });
    });
    
    $(".edit").click(function() {
        $.get("/admin/voucher/edit",{
            voucherid : $(this).attr("data-voucherid")
         }, function(data, status){
             $("#voucher_form").html(data);
         });
    });
});
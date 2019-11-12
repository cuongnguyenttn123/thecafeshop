$(function(){
    // hàm giống với file table-status.js
    function _list() {
        $.get("/admin/product-status/table",{
       }, function(data, status){
           $("tbody").html(data);
       });
    };
    // hàm giống với file table-status.js[END]

    $(".remove").click(function() {
        $.post("/admin/product-status/remove",{
            productstatusid : $(this).attr("data-productstatusid")
        }, function(data, status){
            $("#result-tbody").html(data);
            _list();
        });
    });
    
    $(".edit").click(function() {
        $.get("/admin/bill-status/edit",{
            productstatusid : $(this).attr("data-productstatusid")
         }, function(data, status){
             $("#productStatus_form").html(data);
         });
    });
});
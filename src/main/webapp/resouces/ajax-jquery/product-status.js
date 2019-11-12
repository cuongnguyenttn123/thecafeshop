$(function(){


    $("#btnSave").click(function(){
        $.post("/admin/product-status/insert",{
            billstatusid : $("#productstatusid").val(),
            name : $("#name").val()
       }, function(data, status){
           $("#result-form").html(data);
           _list();
       });
    });
    

    $("#btnClear").click(function() {
        document.getElementById("productStatus_form").reset();
    });

    function _list() {
        $.get("/admin/product-status/table",{
       }, function(data, status){
           $("tbody").html(data);
       });
    };

    _list();
});
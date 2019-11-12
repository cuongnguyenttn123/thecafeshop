$(function(){
    $(".remove").click(function() {
        $.post("/admin/table-status/remove",{
           tablestatusid : $(this).attr("data-tablestatusid")
        }, function(data, status){
            $("#result-tbody").html(data);
			$("#result-tbody").fadeToggle(3000);
			_list(link,1);// at loadTable.js
        });
    });
    
    $(".edit").click(function() {
        $.get("/admin/table-status/edit",{
            tablestatusid : $(this).attr("data-tablestatusid")
         }, function(data, status){
             $("#tableStatus_form").html(data);
         });
    });
});
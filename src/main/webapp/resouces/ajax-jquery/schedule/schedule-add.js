$(function(){
    $(".remove").click(function() {
        $.post("/admin/schedule/remove",{
            scheduleid : $(this).attr("data-scheduleid")
        }, function(data, status){
            $("#result-tbody").html(data);
			$("#result-tbody").fadeToggle(3000);
            _list(link,1);// at loadTable.js
        });
    });
    
    $(".edit").click(function() {
        $.get("/admin/schedule/edit",{
            scheduleid : $(this).attr("data-scheduleid")
         }, function(data, status){
             $("#schedule_form").html(data);
         });
    });
});
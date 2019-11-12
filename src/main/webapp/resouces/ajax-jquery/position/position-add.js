$(function(){

    $(".remove").click(function() {
        $.post("/admin/position/remove",{
            positionid : $(this).attr("data-positionid")
        }, function(data, status){
            $("#result-tbody").html(data);
			$("#result-tbody").fadeToggle(3000);
            _list(link, 1);//at loadTable.js
        });
    });
    
    $(".edit").click(function() {
        $.get("/admin/position/edit",{
            positionid : $(this).attr("data-positionid")
         }, function(data, status){
             $("#position_form").html(data);
         });
    });
});
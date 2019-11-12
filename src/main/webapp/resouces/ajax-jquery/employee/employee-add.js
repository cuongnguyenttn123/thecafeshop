$(function(){
    $(".remove").click(function() {
        $.post("/admin/employee/remove",{
        	employeeid : $(this).attr("data-employeeid")
        }, function(data, status){
            $("#result-tbody").html(data);
			$("#result-tbody").fadeToggle(3000);
            _list(link, 1);// at loadTable.js
        });
    });
    
    $(".edit").click(function() {
        $.get("/admin/employee/edit",{
        	employeeid : $(this).attr("data-employeeid")
         }, function(data, status){
             $("#employee_form").html(data);
         });
    });
});
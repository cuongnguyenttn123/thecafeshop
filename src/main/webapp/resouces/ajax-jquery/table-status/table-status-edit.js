
  $(function(){
    $("#btnUpdate").click(function() {
        $.post("/admin/table-status/edit",{
            tablestatusid : $("#tablestatusid").val(),
            name : $("#name").val()
         }, function(data, status){
             $("#result-form").html(data);
 			$("#result-form").fadeToggle(3000);
//             _list(link, 1);// at loadTable.js
         });
      });
  });
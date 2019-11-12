
  $(function(){
    $("#btnUpdate").click(function() {
        $.post("/admin/schedule/edit",{
            scheduleid : $("#scheduleid").val(),
            starttime : $("#starttime").val(),
            endtime : $("#endtime").val(),
            payrate : $("#payrate").val()
         }, function(data, status){
             $("#result-form").html(data);
 			$("#result-form").fadeToggle(3000);
// 			_list(link, 1);// at loadTable.js
         });
      });
  });
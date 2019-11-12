
  $(function(){
    // hàm giống với file table-status.js
    function _list() {
        $.get("/admin/bill-status/table",{
       }, function(data, status){
           $("tbody").html(data);
       });
    };
    // hàm giống với file table-status.js[END]

    $("#btnUpdate").click(function() {
        $.post("/admin/bill-status/edit",{
            billstatusid : $("#billstatusid").val(),
            name : $("#name").val()
         }, function(data, status){
             $("#result-tbody").html(data);
             _list();
         });
      });
  });
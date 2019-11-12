
  $(function(){

    $("#btnUpdate").click(function() {
        $.post("/admin/product/edit",{
            productid : $("#productid").val(),
            name : $("#name").val(),
            description : $("#description").val(),
            categoryproductid : $("#categoryproductid").val(),
			price : $("#price").val()
         }, function(data, status){
             $("#result-form").html(data);
 			$("#result-form").fadeToggle(3000);
//             _list(link, 1);
         });
      });
  });
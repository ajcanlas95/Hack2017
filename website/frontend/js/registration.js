$("document").ready(function(){
  $(".user_registration").submit(function(){
    var data = {
      "register": "success"
    };
    data = $(this).serialize() + "&" + $.param(data);
    $.ajax({
      type: "POST",
      dataType: "json",
      url: "../backend/register.php", //Relative or absolute path to response.php file
      data: data,
      success: function(data) {
        for (var key in data) {
          Materialize.toast("Registration of "+data[key]['data']+data[key]['result'], 3000, 'rounded'); 
        }
      }
    });
    return false;
  });
});
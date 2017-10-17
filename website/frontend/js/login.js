$("document").ready(function(){
  $(".user_login").submit(function(){
    var data = {
      "action": "test"
    };
    data = $(this).serialize() + "&" + $.param(data);
    $.ajax({
      type: "POST",
      dataType: "json",
      url: "../backend/login.php", //Relative or absolute path to response.php file
      data: data,
      success: function(data) {
        for (var key in data) {
          Materialize.toast("login "+data[key]['result'], 3000, 'rounded'); 
        }
      }
    });
    return false;
  });
});
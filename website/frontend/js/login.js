$("document").ready(function(){
  $("#input_organization").hide();
  $("#name").focusout(function(){
    var name = $('#name').val();
    var match = name.match(/@/g);
    if (match) {
      $("#input_organization").fadeOut();
      $('#organization').val("");
    }else{
      $("#input_organization").fadeIn();
    }
  });
  $(".user_login").submit(function(){
    var data = {
      "action": "test"
    };
    data = $(this).serialize() + "&" + $.param(data);
    value = $('#organization').val().length;
    if (value === 0) {
      $.ajax({
      type: "POST",
      dataType: "json",
      url: "../backend/login.without.org.php", //Relative or absolute path to response.php file
      data: data,
      success: function(data) {
        for (var key in data) {
          Materialize.toast("login "+data[key].result, 3000, 'rounded'); 
        }
      }
      });
    }else{
      $.ajax({
      type: "POST",
      dataType: "json",
      url: "../backend/login.with.org.php", //Relative or absolute path to response.php file
      data: data,
      success: function(data) {
        for (var key in data) {
          Materialize.toast("login "+data[key].result, 3000, 'rounded'); 
        }
      }
      });
    }
    
    return false;
  });
});
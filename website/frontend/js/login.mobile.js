$("document").ready(function(){
  $("#organization_mobile").hide();
  $("#mobile_name").focusout(function(){
    var name = $('#mobile_name').val();
    var match = name.match(/@/g);
    if (match) {
      $("#organization_mobile").fadeOut();
       $('#mobile_organization').val("");
    }else{
      $("#organization_mobile").fadeIn();
    }
  });
  $(".mobile_login").submit(function(){
    var data = {
      "action": "test"
    };
    data = $(this).serialize() + "&" + $.param(data);
    value = $('#mobile_organization').val().length;
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
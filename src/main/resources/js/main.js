$(document).ready(function () {
  $("#nav_control").click(function () {
    navControll();
  });


  function navControll(){
    if ($("#nav_control").is(':checked') && $(".sidebar").css("opacity") == 0) {
      $(".sidebar").removeClass('hidden');
      setTimeout(function () {
        $("#nav_control_icon").removeClass('fa-bars');
        $("#nav_control_icon").addClass('fa-times');
      }, 300);
      $(".sidebar").css("display", "block");
      $(".sidebar").animate({
        opacity: 1,
        // flexBasis: "-=20rem",
        width: "20rem"
      }, 500);
      $(".container").animate({
        // paddingLeft: "+=0rem"
        marginLeft: "20rem"
      }, 500);
    } else {
      $(".sidebar").addClass('hidden');
      $("#nav_control_icon").removeClass('fa-times');
      $("#nav_control_icon").addClass('fa-bars');
      $(".sidebar").animate({
        opacity: 0,
        // flexBasis: "+=20rem",
        width: "0rem"
      }, 500);
      $(".container").animate({
        // paddingLeft: "-=0rem"
        marginLeft: "0rem"
      }, 500);
    }
  }


  $("[name='date_begin']").datepicker({
    dateFormat: "dd/mm/yy",
    // autoSize: true,
    hideIfNoPrevNext: true,
    minDate: new Date(2020, 1 - 1, 20),
    maxDate: new Date(2021, 4 - 1, 20),
    navigationAsDateFormat: true,
    onClose: function (selectedDate) {
      $(".to_date").datepicker("option", "minDate", selectedDate);
      $(this).next().next().focus();
    }
  });

  $("[name='date_end']").datepicker({
    dateFormat: "dd/mm/yy",
    // autoSize: true,
    hideIfNoPrevNext: true,
    minDate: new Date(2020, 1 - 1, 21),
    maxDate: new Date(2021, 4 - 1, 21),
    navigationAsDateFormat: true
  });

  $('#data-tables').DataTable({
    "paging": true,
    "ordering": true,
    "info": true
  });

  $('#data-table-country').DataTable({
    "paging": true,
    "ordering": true,
    "info": true
  });

  // $("tr").click(function () {
  //   var country = $(this).data("country");
  //   console.log(country);
  //   if(country !== "none"){
  //     showData(country);
  //   }
  // });

  navControll();
});

$(".question").click(function(){ 
  //console.log("click click!");
  $(this).toggleClass("question-active");
  $(this).next().slideToggle(700);
  $(this).children("img.arrow").toggleClass("arrow-active");
});

var searchBox = ``;

$(searchBox).insertAfter("#search");

$("#search").keydown(function () { 
  
});
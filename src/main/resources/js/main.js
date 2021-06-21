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

  var table = $('#data-tables').DataTable({
    "paging": true,
    "ordering": true,
    "info": true
  });

  
  var table2 =$('#data-table-country').DataTable({
    "paging": true,
    "ordering": true,
    "info": true
  });

  navControll();
});

$(".question").click(function(){ 
  //console.log("click click!");
  $(this).toggleClass("question-active");
  $(this).next().slideToggle(700);
  $(this).children("img.arrow").toggleClass("arrow-active");
});

var searchResults = '';

$("#search").keyup(function () {
  
  if($("#search").val().length > 0){
  $.ajax({
    type: "GET",
    url: "/ajax/search/"+$("#search").val(),
    data: {},
    dataType: "json",
    success: function (data) {
      for (var key in data) {
        searchResults = searchResults + `
        <a href="">
          <div class="result flex items-center hover:bg-gray-300 px-3 py-1">
              <img src="https://www.countryflags.io/`+key+`/shiny/24.png" alt="flag"
                  class="rounded rounded-md mr-3">`+data[key]+`
          </div>
        </a>
        `;
      }

     $("#searchResults").html(searchResults);

     searchResults = '';
    }
  });
}else{
  $("#searchResults").html("<svg version='1.1' id='L1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' viewBox='0 0 100 100' enable-background='new 0 0 100 100' xml:space='preserve'> <circle fill='none' stroke='#2f49d0' stroke-width='6' stroke-miterlimit='15' stroke-dasharray='14.2472,14.2472' cx='50' cy='50' r='47' > <animateTransform attributeName='transform' attributeType='XML' type='rotate' dur='5s' from='0 50 50' to='360 50 50' repeatCount='indefinite'/> </circle> <circle fill='none' stroke='#2f49d0' stroke-width='1' stroke-miterlimit='10' stroke-dasharray='10,10' cx='50' cy='50' r='39'> <animateTransform attributeName='transform' attributeType='XML' type='rotate' dur='5s' from='0 50 50' to='-360 50 50' repeatCount='indefinite'/> </circle> <g fill='#2f49d0'> <rect x='30' y='35' width='5' height='30'> <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.1'/> </rect> <rect x='40' y='35' width='5' height='30' > <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.2'/> </rect> <rect x='50' y='35' width='5' height='30' > <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.3'/> </rect> <rect x='60' y='35' width='5' height='30' > <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.4'/> </rect> <rect x='70' y='35' width='5' height='30' > <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.5'/> </rect> </g> </svg>");
}
});

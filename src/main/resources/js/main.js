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
    minDate: new Date(2020, 1 - 1, 1),
    maxDate: new Date(2021, 1 - 1, 31),
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
    minDate: new Date(2020, 1 - 1, 1),
    maxDate: new Date(2021, 1 - 1, 31),
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

  function showData(country) {
      $("#countryModal").removeClass("hidden");
      $("#countryModal").css({
        display: "flex"
      });

    var ctx = document.getElementById('canvas');
    var myChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
          label: '# of Votes',
          data: [12, 19, 3, 5, 2, 3],
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(255, 159, 64, 0.2)'
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        tension: 0.7,
        plugins: {
          title: {
            display: true,
            text: 'Chart.js Line Chart - Cubic interpolation mode'
          },
        },
        interaction: {
          intersect: false,
        },
        // scales: {
        //   x: {
        //     display: true,
        //     title: {
        //       display: true
        //     }
        //   },
        //   y: {
        //     display: true,
        //     title: {
        //       display: true,
        //       text: 'Value'
        //     },
        //     suggestedMin: -10,
        //     suggestedMax: 200
        //   }
        // }
      },
    });
  }

  $("tr").click(function () {
    var country = $(this).data("country");
    if(country !== "none"){
      showData(country);
    }
  });

  $("#countryModal .close").click(function () {
    $("#countryModal").fadeOut(400, function () {
      $("#countryModal").addClass("hidden")
    });
  });

  $("svg g path").hover(function () {
      // over
      
    }, function () {
      // out
    }
  );

  navControll();
});
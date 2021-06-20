function showData(country) {
    $("#countryModal").removeClass("hidden");
    $("#countryModal").css({
        display: "flex"
    });

    $.ajax({
        type: "GET",
        url: "/ajax/reportModal/" + country,
        data: {},
        dataType: "json",
        success: function (data) {
            var html = `
            <div class='close absolute right-2 top-1' onClick='hideData();'> <span class='fas fa-times'></span> </div>
            <div class='data-country-items'>
                <div class='country-name mb-4 flex items-center'>
                    <h1 class='text-2xl font-semibold ml-3'>`+ data[0][0] +`</h1>&nbsp;&nbsp;
                    <img src='https://www.countryflags.io/`+ data[0][8] +`/shiny/32.png' class='rounded rounded-md mr-3'>
                </div>
                <div class='flex flex-row flex-wrap'>
                    <div class='base'>
                        <div class='death flex justify-between text-lg'>
                            <h1>Death</h1>
                            <h1>:&nbsp;&nbsp;&nbsp;&nbsp;`+ data[0][1] +`</h1>
                        </div>
                        <div class='recovery flex justify-between text-lg'>
                            <h1>Number of Cases</h1>
                            <h1>:&nbsp;&nbsp;&nbsp;&nbsp;`+ data[0][2] +`</h1>
                        </div>
                        <div class='fatality flex justify-between text-lg'>
                            <h1>Fatality Rate</h1>
                            <h1>:&nbsp;&nbsp;&nbsp;&nbsp;`+ data[0][3] +`</h1>
                        </div>
                        <div class='infections flex justify-between text-lg mb-2'>
                            <h1>Infections to death per Capita</h1>
                            <h1>:&nbsp;&nbsp;&nbsp;&nbsp;`+ data[0][4] +`</h1>
                        </div>
                        <hr>
                        <br>
                        <div class='extra'>
                            <div class='latlong'>
                                <h1 class='font-light'>Latutude/ Longitude:</h1>
                                <h1 class='text-xl'>`+ data[0][5] +`/ `+ data[0][6] +`</h1>
                            </div>
                            <div class='population'>
                                <h1 class='font-light'>Population:</h1>
                                <h1 class='text-xl'>`+ data[0][7] +`</h1>
                            </div>
                        </div>
                    </div>
                    <div class='chart'>
                        <canvas class='px-4' id='canvas'></canvas>
                    </div>
                </div>
            </div>`;

            $("#countryModalData").html(html);

            var dates = [];
            var infections = [];
            var deaths = [];

            for (let i = 0; i < data[1].length/3; i+=3) {
                dates.push(data[1][i]);
                infections.push(data[1][i + 1]);
                deaths.push(data[1][i + 2]);
            }

            var plot = {
                labels: dates,
                datasets: [
                  {
                    label: 'Infections',
                    data: infections,
                    borderColor: "rgb(201, 203, 207)",
                    backgroundColor: "rgba(201, 203, 207, 0.5)",
                  },
                  {
                    label: 'Deaths',
                    data: deaths,
                    borderColor: "rgb(54, 162, 235)",
                    backgroundColor: "rgba(54, 162, 235, 0.5)",
                  }
                ]
              };

            setTimeout(function() {                
                var ctx = document.getElementById('canvas');
                var myChart = new Chart(ctx, {
                    type: 'line',
                    data: plot,
                    options: {
                        responsive: true,
                        tension: 0.7,
                        plugins: {
                            title: {
                                display: true,
                                text: data[0][0]+'\'s Data Comparision [Infections vs Deaths]'
                            },
                        },
                        interaction: {
                            intersect: true,
                        },
                        scales: {
                          x: {
                            title: {
                              display: true,
                              text: 'Date'
                            },
                            ticks: {
                                stepSize: 0.5
                            }
                          },
                          y: {
                            title: {
                              display: true,
                              text: 'Numbers'
                            }
                          }
                        }
                    }
                });
            }, 100);
        }
    });

}

function hideData(){
    $("#countryModal").addClass("hidden");
    $("#countryModal").css({
        display: "none"
    });
}
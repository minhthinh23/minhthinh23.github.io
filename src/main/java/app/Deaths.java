package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Deaths implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/deaths";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<!DOCTYPE html><html lang='en'><head> <meta charset='UTF-8'> <meta http-equiv='X-UA-Compatible' content='IE=edge'> <meta name='viewport' content='width=device-width, initial-scale=1.0'> <title>Milestone Project</title> <link rel='stylesheet' href='tailwind.min.css'> <link rel='stylesheet' href='main.css'> <link rel='stylesheet' href='jquery-ui.min.css'> <link rel='stylesheet' href='jquery-ui.structure.min.css'> <link rel='stylesheet' href='jquery-ui.theme.min.css'> <link rel='stylesheet' type='text/css' href='datatables.min.css'/></head><body> <div class='cover flex'> <div class='sidebar hidden relative px-2'> <div class='top'> <div class='logo pl-1 pt-4'> <img src='logo.svg' alt='logo'> </div><input type='text' name='search' id='search' class='mx-auto' placeholder='Search Country...'> </div><div class='bottom pt-3'> <a href='/'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg'> <div class='icon'> <img src='home.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Home</div></div></a> <a href='/overview'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='overview.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Overview</div></div></a> <a href='/infections-by-country'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='infections.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Infections by Country</div></div></a> <a href='/deaths'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='deaths.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Deaths by Country</div></div></a> <a href='/compare-countries'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='countries.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Compare Countries</div></div></a> <a href='/reports'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg selected'> <div class='icon'> <img src='reports.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Reports</div></div></a> </div><div class='profile-short-menu absolute bottom-0 left-0 flex justify-between px-4 w-full h-16 border-t border-gray-100 items-center shadow-lg'> <div class='profile-pic w-12'> <img src='profile.jpeg' alt='Profile' class='w-full rounded-full'> </div><div class='icons'> <span class='fas fa-cog fa-lg text-white'></span> </div></div></div><div class='container w-full h-full'> <nav class='nav px-4 h-10'> <div class='patch'> <span class='fas fa-bars fa-lg' id='nav_control_icon'></span> <input type='checkbox' id='nav_control' class='cursor-pointer'> </div></nav> <div class='content px-4 pb-2' id='content'> <h2 class='text-2xl font-semibold'>Deaths by Country:</h2> <div class='parent flex flex-row flex-wrap'> <div class='left mt-4 border rounded-xl border-gray-300 py-3 px-4'> <div class='head border-b border-gray-300 pb-1'> <div class='heading'> <div class='title'> <h2 class='text-2xl font-semibold'>Deaths</h2> </div><div class='info text-gray-400 text-xs'> Information provided by RMIT | Last updated since data given </div></div></div><div class='sorting flex flex-row flex-wrap'>";

        
        JDBCConnection jdbc = new JDBCConnection();

        // Next we will ask this *class* for the movies
        ArrayList<String> movies = jdbc.getMovies();


        html = html + 
        "<form action='' method='get' class='flex flex-row flex-wrap'>" +
            "<select name='country' id='country' class='select w-28'>" +
                "<option value='worldwide'>Worldwide</option>" +
                "<option value='AUS'>Australia</option>" +
            "</select>" +
            "<div class='time-period flex flex-nowrap px-2' style='height: 26px;'>" +
                "<p class='text-gray-500'>From:</p>" +
                "<input type='text' name='date_begin' id='date_begin' class='inp'>" +
                "<p class='text-gray-500'> To:</p>" +
                "<input type='text' name='date_end' id='date_end' class='inp'>" +
                "<img src='calendar.svg' alt='calendar'>" +
            "</div>" +
            "<input type='submit' value='Go' class='submit'>" +
        "</form>";

        html = html + "</div><div class='case-death-info flex flex-row flex-wrap my-3'> <div class='cases px-4 border-r border-gray-200'> <h1 class='text-xl font-semibold'>Confirm Deaths</h1>";

        // Confirmed Deaths here                               here
        html = html + "<p class='text-gray-500 text-sm'>" + "//HERE//" + "</p>";

        html = html + 
        "<div class='deaths px-4'>" +
            "<h1 class='text-xl font-semibold'>Global per capita</h1>" +
            // Confirmed deaths PerCapita here                here
            "<p class='text-gray-500 text-sm'>"+ "//HERE//" +"</p>" +
        "</div>";

        html = html + " </div><div class='data-tables px-3' data-check='side'> <table id='data-tables' class='display' style='width:100%'> <thead> <tr data-country='none'> <th id='data-name'>Death/ Infection Ratio</th> <th id='data-death'>Per Capita</th> <th id='data-recovery'>Deaths</th> </tr></thead> <tbody>";


        html = html + 
        "<tr data-country='none'>" +
            "<td>Edinburgh</td>" +
            "<td>61</td>" +
            "<td>2011/04/25</td>" +
        "</tr>";

        html = html + " </tbody> </table> </div></div><div class='right'> <div class='top-chart mt-4 border rounded-xl border-gray-300 py-3 px-4 ml-3'> <div class='chart'> <canvas class='px-4' id='graph'></canvas> </div></div><div class='bottom mt-2 border rounded-xl border-gray-300 py-3 px-4 ml-3'> <div class='head border-b border-gray-300 pb-1'> <div class='heading'> <div class='title'>";

        html = html + 
        "<form action='' method='get' class='flex flex-row flex-wrap'>" +
            "<select name='country' id='country' class='select w-28'>" +
                "<option value='worldwide'>Worldwide</option>" +
                "<option value='AUS'>Australia</option>" +
            "</select>" +
            "<input type='submit' value='Go' class='submit'>" +
        "</form>";

        html = html + " </div><div class='info text-gray-400 text-xs'> Information provided by RMIT | Last updated since data given </div></div></div><div class='sorting flex flex-row flex-wrap'>";

        html = html +
        "<form action='' method='get' class='flex flex-row flex-wrap'>" +
            "<div class='time-period flex flex-nowrap px-2' style='height: 26px;'>" +
                "<p class='text-gray-500'>From:</p>" +
                "<input type='text' name='date_begin' id='date_begin' class='inp'>" +
                "<p class='text-gray-500'> To:</p>" +
                "<input type='text' name='date_end' id='date_end' class='inp'>" +
                "<img src='calendar.svg' alt='calendar'>" +
            "</div>" +

            "<input type='submit' value='Go' class='submit'>" +
        "</form>" +
        "</div>" +
        "<div class='case-death-info flex flex-row flex-wrap my-3'>" +
            "<div class='cases px-4 border-r border-gray-200'>" +
                "<h1 class='text-xl font-semibold'>Total Cases</h1>" +
                // TOTAL CASES HERE
                "<p class='text-gray-500 text-sm'>30,000</p>" +
            "</div>" +
            "<div class='deaths px-4'>" +
                "<h1 class='text-xl font-semibold'>Total Deaths</h1>" +
                //TOTAL DEATHS HERE
                "<p class='text-gray-500 text-sm'>30,000</p>" +
            "</div>" +
        "</div>;";

        html = html + " <div class='data-tables px-3' data-check='side'> <div class='sides'> <h1 class='text-xl mb-4 font-semibold'>Figures</h1>";

        // Loop for key value pair
        html = html +
        "<div class='col flex py-3 px-2 border-t border-gray-400 justify-between'>" +
            "<h1>Heading</h1>" +
            "<h1>Value</h1>" +
        "</div>";

        html = html + " </div></div></div></div></div></div></div></div><script defer src='https://use.fontawesome.com/releases/v5.15.3/js/all.js' integrity='sha384-haqrlim99xjfMxRP6EWtafs0sB1WKcMdynwZleuUSwJR0mDeRYbhtY+KPMr+JL6f' crossorigin='anonymous'></script> <script src='https://code.jquery.com/jquery-3.5.1.js'></script> <script src='https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js'></script> <script src='jquery-ui.min.js'></script> <script src='https://cdn.jsdelivr.net/npm/chart.js@3.3.2/dist/chart.min.js'></script> <script src='https://cdn.amcharts.com/lib/4/core.js'></script> <script src='https://cdn.amcharts.com/lib/4/maps.js'></script> <script src='https://cdn.amcharts.com/lib/4/geodata/usaLow.js'></script> <script src='https://cdn.amcharts.com/lib/4/themes/animated.js'></script> <script src='main.js'></script>";

        // Script for making Line Graph


        //     <script>
        //     var ctx = document.getElementById('graph');
        //     var myChart = new Chart(ctx, {
        //         type: 'line',
        //         data: {
        //             labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        //             datasets: [{
        //                 label: '# of Votes',
        //                 data: [12, 19, 3, 5, 2, 3],
        //                 backgroundColor: [
        //                     'rgba(255, 99, 132, 0.2)',
        //                     'rgba(54, 162, 235, 0.2)',
        //                     'rgba(255, 206, 86, 0.2)',
        //                     'rgba(75, 192, 192, 0.2)',
        //                     'rgba(153, 102, 255, 0.2)',
        //                     'rgba(255, 159, 64, 0.2)'
        //                 ],
        //                 borderColor: [
        //                     'rgba(255, 99, 132, 1)',
        //                     'rgba(54, 162, 235, 1)',
        //                     'rgba(255, 206, 86, 1)',
        //                     'rgba(75, 192, 192, 1)',
        //                     'rgba(153, 102, 255, 1)',
        //                     'rgba(255, 159, 64, 1)'
        //                 ],
        //                 borderWidth: 1
        //             }]
        //         },
        //         options: {
        //             responsive: true,
        //             tension: 0.7,
        //             plugins: {
        //                 title: {
        //                     display: true,
        //                     text: 'Chart.js Line Chart - Cubic interpolation mode'
        //                 },
        //             },
        //             interaction: {
        //                 intersect: false,
        //             },
        //         },
        //     });
        // </script>

        // Finish the HTML webpage
        html = html + 
        "</body>" + 
        "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}

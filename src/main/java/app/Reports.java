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
public class Reports implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/reports";

    @Override
    public void handle(Context context) throws Exception {

        // Static Layout
        String html = "<!DOCTYPE html><html lang='en'><head> <meta charset='UTF-8'> <meta http-equiv='X-UA-Compatible' content='IE=edge'> <meta name='viewport' content='width=device-width, initial-scale=1.0'> <title>Milestone Project</title> <link rel='stylesheet' href='tailwind.min.css'> <link rel='stylesheet' href='main.css'> <link rel='stylesheet' href='jquery-ui.min.css'> <link rel='stylesheet' href='jquery-ui.structure.min.css'> <link rel='stylesheet' href='jquery-ui.theme.min.css'> <link rel='stylesheet' type='text/css' href='datatables.min.css'/></head><body> <div class='cover flex'> <div class='sidebar hidden relative px-2'> <div class='top'> <div class='logo pl-1 pt-4'> <img src='logo.svg' alt='logo'> </div><input type='text' name='search' id='search' class='mx-auto' placeholder='Search Country...'> </div><div class='bottom pt-3'> <a href='/'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg'> <div class='icon'> <img src='home.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Home</div></div></a> <a href='/overview'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='overview.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Overview</div></div></a> <a href='/infections-by-country'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='infections.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Infections by Country</div></div></a> <a href='/deaths'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='deaths.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Deaths by Country</div></div></a> <a href='/compare-countries'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='countries.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Compare Countries</div></div></a> <a href='/reports'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg selected'> <div class='icon'> <img src='reports.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Reports</div></div></a> </div><div class='profile-short-menu absolute bottom-0 left-0 flex justify-between px-4 w-full h-16 border-t border-gray-100 items-center shadow-lg'> <div class='profile-pic w-12'> <img src='profile.jpeg' alt='Profile' class='w-full rounded-full'> </div><div class='icons'> <span class='fas fa-cog fa-lg text-white'></span> </div></div></div><div class='container w-full h-full'> <nav class='nav px-4 h-10'> <div class='patch'> <span class='fas fa-bars fa-lg' id='nav_control_icon'></span> <input type='checkbox' id='nav_control' class='cursor-pointer'> </div></nav> <div class='content px-4' id='content'> <div class='head border-b border-gray-200 pb-1'> <div class='heading'> <div class='title'> <h2 class='text-2xl font-semibold'>Reports:</h2> </div><div class='info text-gray-400 text-xs'> Information provided by RMIT | Last updated since data given </div></div></div><div class='sorting flex flex-row flex-wrap'>";

        // JDBC Connection
        JDBCConnection jdbc = new JDBCConnection();

        // Get all the data here
        ArrayList<String> movies = jdbc.getMovies();


        // HTML form for filtering data (Seperated just cause we might need to edit it)
        html = html + 
        "<form action='' method='get' class='flex flex-row flex-wrap'>" + 
            "<div class='time-period flex flex-nowrap px-2' style='height: 26px;'>"+
                "<p class='text-gray-500'>From:</p>" +
                "<input type='text' name='date_begin' id='date_begin' class='inp'>" +
                "<p class='text-gray-500'> To:</p>" +
                "<input type='text' name='date_end' id='date_end' class='inp'> <img src='calendar.svg' alt='calendar'>" +
            "</div>" +
            "<select name='sort_by' id='sort_by' class='select w-60 text-center'>" +
                    "<option value='worldwide' class='text-center'>--- SELECT ---</option>" +
                    "<option value='1' class='text-center'>Total Deaths</option>" +
                    "<option value='1' class='text-center'>Deaths per Capita</option>" +
                    "<option value='1' class='text-center'>Fatality Rate</option>" +
                    "<option value='1' class='text-center'>Infections to death per Capita</option>" +
                "</select>" +
            "<input type='submit' value='Go' class='submit'>" +
        "</form>";

        html = html + " </div><div class='case-death-info flex flex-row flex-wrap my-3'> <div class='cases px-4 border-r border-gray-200'> <h1 class='text-xl font-semibold'>Cases</h1> <p class='text-gray-500 text-sm'>30,000</p></div><div class='deaths px-4'> <h1 class='text-xl font-semibold'>Deaths</h1> <p class='text-gray-500 text-sm'>30,000</p></div></div></div><div class='data-tables px-3' data-check='modal'> <table id='data-tables' class='display' style='width:100%'> <thead> <tr data-country='none'> <th id='data-name'>Country</th> <th id='data-death'>Infections</th> <th id='data-recovery'>Deaths</th> <th id='data-fatalityRate'>Average Infection Rate</th> <th id='data-infectionsToDeathPerCapita'>Fatality Rate</th> </tr></thead> <tbody>";

        // Run loop here to fill the table with all data
 
       for(int i = 1; i < 191; i++){
        html = html + "<tr data-country='countryName1'>";
        ArrayList<String> countryData = jdbc.printCountryReportData(i);

        for(int j = 0; j < 5; j++){
            html = html + "<td>" + countryData.get(j) + "</td>";
        }
        html = html + "</tr>";
    }
        // End loop

        html = html + " </tbody> </table> </div></div></div><div class='country bg-black bg-opacity-20 flex justify-center hidden' id='countryModal'> <div class='data self-center px-3 py-2 relative' id='countryModalData'>";

        // JavaScript material to put data in Country Modal [id='countryModalData']
        
        // <div class='close absolute right-2 top-1'> <span class='fas fa-times'></span> </div>
        // <div class='data-country-items'>
        //     <div class='country-name mb-4'>
        //         <h1 class='text-2xl font-semibold'>Australia</h1>
        //     </div>
        //     <div class='flex flex-row flex-wrap'>
        //         <div class='base'>
        //             <div class='death flex justify-between text-lg'>
        //                 <h1>Death:</h1>
        //                 <h1>:&nbsp;&nbsp;&nbsp;&nbsp;24 M</h1>
        //             </div>
        //             <div class='recovery flex justify-between text-lg'>
        //                 <h1>Recovery</h1>
        //                 <h1>:&nbsp;&nbsp;&nbsp;&nbsp;24 M</h1>
        //             </div>
        //             <div class='fatality flex justify-between text-lg'>
        //                 <h1>Fatality</h1>
        //                 <h1>:&nbsp;&nbsp;&nbsp;&nbsp;24 M</h1>
        //             </div>
        //             <div class='infections flex justify-between text-lg mb-2'>
        //                 <h1>Infections to death per Capita</h1>
        //                 <h1>:&nbsp;&nbsp;&nbsp;&nbsp;24 M</h1>
        //             </div>
        //             <hr>
        //             <br>
        //             <div class='extra'>
        //                 <div class='latlong'>
        //                     <h1 class='font-light'>Latutude/ Longitude:</h1>
        //                     <h1 class='text-xl'>+67.2452345/ -87.2837332</h1>
        //                 </div>
        //                 <div class='population'>
        //                     <h1 class='font-light'>Population:</h1>
        //                     <h1 class='text-xl'>86238576</h1>
        //                 </div>
        //             </div>
        //         </div>
        //         <div class='chart'>
        //             <canvas class='px-4' id='canvas'></canvas>
        //         </div>
        //     </div>
        // </div>

        // Finish the HTML webpage
        html = html + " </div></div><script defer src='https://use.fontawesome.com/releases/v5.15.3/js/all.js' integrity='sha384-haqrlim99xjfMxRP6EWtafs0sB1WKcMdynwZleuUSwJR0mDeRYbhtY+KPMr+JL6f' crossorigin='anonymous'></script> <script src='https://code.jquery.com/jquery-3.5.1.js'></script> <script src='https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js'></script> <script src='jquery-ui.min.js'></script> <script src='https://cdn.jsdelivr.net/npm/chart.js@3.3.2/dist/chart.min.js'></script> <script src='main.js'></script></body></html>";

        context.html(html);
    }

}

package app;

import java.util.*;

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

    public static String dateFormat(String date){
        String[] broke = date.split("/");
        return broke[2].length() > 2 ? broke[2].substring(broke[2].length() - 2) +"/"+broke[1]+"/"+broke[0] : broke[2]+"/"+broke[1]+"/"+broke[0];
    }

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<!DOCTYPE html><html lang='en'><head> <meta charset='UTF-8'> <meta http-equiv='X-UA-Compatible' content='IE=edge'> <meta name='viewport' content='width=device-width, initial-scale=1.0'> <title>Milestone Project</title> <link rel='stylesheet' href='tailwind.min.css'> <link rel='stylesheet' href='main.css'> <link rel='stylesheet' href='jquery-ui.min.css'> <link rel='stylesheet' href='jquery-ui.structure.min.css'> <link rel='stylesheet' href='jquery-ui.theme.min.css'> <link rel='stylesheet' type='text/css' href='datatables.min.css'/></head><body> <div class='cover flex'> <div class='sidebar hidden relative px-2'> <div class='top'> <div class='logo pl-1 pt-4'> <img src='logo.svg' alt='logo'> </div>  <div class='searchBox relative'> <input type='text' name='search' id='search' class='mx-auto' placeholder='Search Country...'> <div class='loading absolute bg-white' id='searchResults'> <svg version='1.1' id='L1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' viewBox='0 0 100 100' enable-background='new 0 0 100 100' xml:space='preserve'> <circle fill='none' stroke='#2f49d0' stroke-width='6' stroke-miterlimit='15' stroke-dasharray='14.2472,14.2472' cx='50' cy='50' r='47' > <animateTransform attributeName='transform' attributeType='XML' type='rotate' dur='5s' from='0 50 50' to='360 50 50' repeatCount='indefinite'/> </circle> <circle fill='none' stroke='#2f49d0' stroke-width='1' stroke-miterlimit='10' stroke-dasharray='10,10' cx='50' cy='50' r='39'> <animateTransform attributeName='transform' attributeType='XML' type='rotate' dur='5s' from='0 50 50' to='-360 50 50' repeatCount='indefinite'/> </circle> <g fill='#2f49d0'> <rect x='30' y='35' width='5' height='30'> <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.1'/> </rect> <rect x='40' y='35' width='5' height='30' > <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.2'/> </rect> <rect x='50' y='35' width='5' height='30' > <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.3'/> </rect> <rect x='60' y='35' width='5' height='30' > <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.4'/> </rect> <rect x='70' y='35' width='5' height='30' > <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.5'/> </rect> </g> </svg> </div></div> </div><div class='bottom pt-3'> <a href='/'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg'> <div class='icon'> <img src='home.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Home</div></div></a> <a href='/overview'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='overview.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Overview</div></div></a> <a href='/infections-by-country'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='infections.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Infections by Country</div></div></a> <a href='/deaths'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg selected'> <div class='icon'> <img src='deaths.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Deaths by Country</div></div></a> <a href='/compare-countries'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='countries.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Compare Countries</div></div></a> <a href='/reports'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='reports.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Reports</div></div></a> </div><div class='profile-short-menu absolute bottom-0 left-0 flex justify-between px-4 w-full h-16 border-t border-gray-100 items-center shadow-lg'> <div class='profile-pic w-12'> <img src='profile.jpeg' alt='Profile' class='w-full rounded-full'> </div><div class='icons'> <span class='fas fa-cog fa-lg text-white'></span> </div></div></div><div class='container w-full h-full'> <nav class='nav px-4 h-10'> <div class='patch'> <span class='fas fa-bars fa-lg' id='nav_control_icon'></span> <input type='checkbox' id='nav_control' class='cursor-pointer'> </div></nav> <div class='content px-4 pb-2' id='content'> <h2 class='text-2xl font-semibold'>Deaths by Country:</h2> <div class='parent flex flex-row flex-wrap'> <div class='left mt-4 border rounded-xl border-gray-300 py-3 px-4'> <div class='head border-b border-gray-300 pb-1'> <div class='heading'> <div class='title'> <h2 class='text-2xl font-semibold'>Deaths</h2> </div><div class='info text-gray-400 text-xs'> Information provided by RMIT | Last updated since data given </div></div></div><div class='sorting flex flex-row flex-wrap'>";
        
        JDBCConnection jdbc = new JDBCConnection();



        // Assign these variables with details as named | All these are used in text below and will give error if not assigned
        int confirmedDeaths = 0;
        int globalPerCapitaDeath = 0;
        int totalCases = 0;
        int totalDeaths = 0;

        html = html + 
        "<form action='/deaths' method='POST' class='flex flex-row flex-wrap'>" +
            "<div class='time-period flex flex-nowrap px-2' style='height: 26px;'>" +
                "<p class='text-gray-500'>From:</p>" +
                "<input type='text' name='date_begin' id='date_begin' class='inp'>" +
                "<p class='text-gray-500'> To:</p>" +
                "<input type='text' name='date_end' id='date_end' class='inp'>" +
                "<img src='calendar.svg' alt='calendar'>" +
            "</div>" +

            "<input type='submit' value='Go' class='submit'>" +
        "</form>";

        html = html + " </div><div class='case-death-info flex flex-row flex-wrap my-3'> <div class='cases px-4 border-r border-gray-200'> <h1 class='text-xl font-semibold'>Confirm Deaths</h1> <p class='text-gray-500 text-sm'>" + confirmedDeaths + 
        "</p></div><div class='deaths px-4'> <h1 class='text-xl font-semibold'>Global per capita</h1> <p class='text-gray-500 text-sm'>" + globalPerCapitaDeath + 
        "</p></div></div><div class='data-tables px-3' data-check='side'> <table id='data-tables' class='display' style='width:100%'> <thead> <tr data-country='none'> <th id='data-name'>Country</th> <th id='data-death'>Population</th> <th id='data-recovery'>Deaths</th> <th>Average Deaths per Day</th><th>Most Fatalities in a Day</th> </tr></thead> <tbody>";
        
        
        String entrydate_textbox_unformatted = context.formParam("date_begin");
        String exitdate_textbox_unformatted = context.formParam("date_end");

        // Loop for left side table on the Deaths page (All countries table)

        if (entrydate_textbox_unformatted == null || entrydate_textbox_unformatted == "") {
            for(int i = 1; i < 191; i++){
                html = html + "<tr data-country='none'>";
                ArrayList<String> countryData = jdbc.printCountryDeathsData(i);
                html = html + "<td>" + JDBCConnection.printCountryName(i) + "</td>";

                for(int j = 0; j < 4; j++){
                    html = html + "<td>" + countryData.get(j) + "</td>";
                }
            }
        }

        else{

            String entrydate_textbox = dateFormat(entrydate_textbox_unformatted);
            String exitdate_textbox = dateFormat(exitdate_textbox_unformatted);
            for(int i = 1; i < 191; i++){
                html = html + "<tr data-country='none'>";
                html = html + "<td>" + JDBCConnection.printCountryName(i) + "</td>";
                ArrayList<String> countryData = jdbc.printCountryDeathDataWithRange(i, entrydate_textbox, exitdate_textbox);
        
                for(int j = 0; j < 4; j++){
                    html = html + "<td>" + countryData.get(j) + "</td>";
                }
                html = html + "</tr>";
            }   
        }
        html = html + "</tbody> </table> </div></div><div class='right'> <div class='top-chart mt-4 border rounded-xl border-gray-300 py-3 px-4 ml-3'> <div class='chart relative'> <img src='mappp.png' alt='map' class='w-full' style='height: 280px !important;'> </div></div><div class='bottom mt-2 border rounded-xl border-gray-300 py-3 px-4 ml-3'> <div class='head border-b border-gray-300 pb-1'> <div class='heading'> <div class='title'>";
        ArrayList<String> category = jdbc.getCountryList();
        html = html + "<form action='/deaths' method='POST' class='flex flex-row flex-wrap'>" +
            "<select name='country2' id='country2' class='select w-28'>";
            for (String categories : category) {
                html = html + "<option>" + categories + "</option>";
            }
            html = html + "      </select>" +
            " </div></div></div><div class='sorting flex flex-row flex-wrap'>" +      
            "<div class='time-period flex flex-nowrap px-2' style='height: 26px;'>" +
                "<p class='text-gray-500'>From:</p>" +
                "<input type='text' name='date_begin2' id='date_begin2' class='inp'>" +
                "<p class='text-gray-500'> To:</p>" +
                "<input type='text' name='date_end2' id='date_end2' class='inp'>" +
                "<img src='calendar.svg' alt='calendar'>" +
            "</div>" +
            "<input type='submit' value='Go' class='submit'>" +
        "</form>";
        html = html + " </div><div class='case-death-info flex flex-row flex-wrap my-3'> <div class='cases px-4 border-r border-gray-200'> <h1 class='text-xl font-semibold'>Total Cases</h1> <p class='text-gray-500 text-sm'>" + totalCases + "</p></div><div class='deaths px-4'> <h1 class='text-xl font-semibold'>Total Deaths</h1> <p class='text-gray-500 text-sm'>" + totalDeaths + "</p></div></div><div class='data-tables px-3' data-check='side'> <div class='sides'> <h1 class='text-xl mb-4 font-semibold'>Figures</h1>"; 
       
       
        String Countryname_drop = context.formParam("country2");
        String entrydate_textbox_unformatted2 = context.formParam("date_begin2");
        String exitdate_textbox_unformatted2 = context.formParam("date_end2");

        ArrayList<String> CloserLookDeathHeaders= new ArrayList<String>();
        CloserLookDeathHeaders.add("Total Deaths");
        CloserLookDeathHeaders.add("Total Deaths per capita");
        CloserLookDeathHeaders.add("Mortality Rate");
        CloserLookDeathHeaders.add("Deadliest Day");

        //no dates inputted//
        if (entrydate_textbox_unformatted2 == null || entrydate_textbox_unformatted2 == "") {
            if(Countryname_drop == null || Countryname_drop == ""){

                ArrayList<String> CloserLookDeathData= jdbc.printCloserLookDeathsData("Afghanistan");
                html = html + "<h2>Displaying results for Afghanistan from   22/01/2020 until 22/04/2021</h2>";
                for (int i = 0; i < 4; i++){
                    html = html + "<div class='col flex py-3 px-2 border-t border-gray-400 justify-between'>";
                    html = html + "<h1>" + CloserLookDeathHeaders.get(i) + "</h1>";
                    html = html + "<h1>" + CloserLookDeathData.get(i) + "</h1>";
                    html = html + "</div>";
                }
            }
            //country inputted//
            else{
                html = html + "<h2>" + Countryname_drop+ "</h2>";
                ArrayList<String> CloserLookDeathData= jdbc.printCloserLookDeathsData(Countryname_drop);
                html = html + "<h2>Displaying results for " + Countryname_drop + " from 22/01/2020 until 22/04/2021</h2>";
                for (int i = 0; i < 4; i++){
                    html = html + "<div class='col flex py-3 px-2 border-t border-gray-400 justify-between'>";
                    html = html + "<h1>" + CloserLookDeathHeaders.get(i) + "</h1>";
                    html = html + "<h1>" + CloserLookDeathData.get(i) + "</h1>";
                    html = html + "</div>";
                }
            }
        }
        else{
            if(Countryname_drop == null || Countryname_drop == ""){
                String entrydate_textbox2 = dateFormat(entrydate_textbox_unformatted2);
                String exitdate_textbox2 = dateFormat(exitdate_textbox_unformatted2);
     
            html = html + "<h2>Displaying results for Afghanistan from " + dateFormat(entrydate_textbox2) + " until " + dateFormat(exitdate_textbox2) + "</h2>";
            ArrayList<String> CloserLookDeathData= jdbc.printCloserLookDeathsDataWithRange("Afghanistan", entrydate_textbox2, exitdate_textbox2);
                for (int i = 0; i < 4; i++){
                    html = html + "<div class='col flex py-3 px-2 border-t border-gray-400 justify-between'>";
                    html = html + "<h1>" + CloserLookDeathHeaders.get(i) + "</h1>";
                    html = html + "<h1>" + CloserLookDeathData.get(i) + "</h1>";
                    html = html + "</div>";    
                }
            }
            else{

                String entrydate_textbox2 = dateFormat(entrydate_textbox_unformatted2);
                String exitdate_textbox2 = dateFormat(exitdate_textbox_unformatted2);
                html = html + "<h2>Displaying results for " + Countryname_drop + " from " + dateFormat(entrydate_textbox2) + " until " + dateFormat(exitdate_textbox2) + "</h2>";
                ArrayList<String> CloserLookDeathData= jdbc.printCloserLookDeathsDataWithRange(Countryname_drop, entrydate_textbox2, exitdate_textbox2);  
                for (int i = 0; i < 4; i++){
                    html = html + "<div class='col flex py-3 px-2 border-t border-gray-400 justify-between'>";
                    html = html + "<h1>" + CloserLookDeathHeaders.get(i) + "</h1>";
                    html = html + "<h1>" + CloserLookDeathData.get(i) + "</h1>";
                    html = html + "</div>";
                }
            }
        }

        //date inputted//



        html = html + " </div></div></div></div></div></div></div></div><script defer src='https://use.fontawesome.com/releases/v5.15.3/js/all.js' integrity='sha384-haqrlim99xjfMxRP6EWtafs0sB1WKcMdynwZleuUSwJR0mDeRYbhtY+KPMr+JL6f' crossorigin='anonymous'></script> <script src='https://code.jquery.com/jquery-3.5.1.js'></script> <script src='https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js'></script> <script src='jquery-ui.min.js'></script> <script src='https://cdn.jsdelivr.net/npm/chart.js@3.3.2/dist/chart.min.js'></script> <script src='https://cdn.amcharts.com/lib/4/core.js'></script> <script src='https://cdn.amcharts.com/lib/4/maps.js'></script> <script src='https://cdn.amcharts.com/lib/4/geodata/usaLow.js'></script> <script src='https://cdn.amcharts.com/lib/4/themes/animated.js'></script><script src='main.js'></script></body></html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}


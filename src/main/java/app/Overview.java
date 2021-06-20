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
public class Overview implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/overview";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<!DOCTYPE html><html lang='en'><head> <meta charset='UTF-8'> <meta http-equiv='X-UA-Compatible' content='IE=edge'> <meta name='viewport' content='width=device-width, initial-scale=1.0'> <title>Milestone Project</title> <link rel='stylesheet' href='tailwind.min.css'> <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/Glide.js/3.4.1/css/glide.theme.min.css' integrity='sha512-wCwx+DYp8LDIaTem/rpXubV/C1WiNRsEVqoztV0NZm8tiTvsUeSlA/Uz02VTGSiqfzAHD4RnqVoevMcRZgYEcQ==' crossorigin='anonymous' referrerpolicy='no-referrer'/> <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/Glide.js/3.4.1/css/glide.core.min.css' integrity='sha512-wYsVD8ho6rJOAo1Xf92gguhOGQ+aWgxuyKydjyEax4bnuEmHUt6VGwgpUqN8VlB4w50d0nt+ZL+3pgaFMAmdNQ==' crossorigin='anonymous' referrerpolicy='no-referrer'/> <link rel='stylesheet' href='main.css'> <style>body{overflow-x: hidden;}.glide__arrow--left{left: -25px; background-color: rgba(0, 0, 0, 10%); border-radius: 100%; width: 50px; height: 50px; box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3); -webkit-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3); -moz-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3);}.glide__arrow--right{right: -25px; background-color: rgba(0, 0, 0, 10%); border-radius: 100%; width: 50px; height: 50px; box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3); -webkit-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3); -moz-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3);}</style></head><body> <div class='cover flex'> <div class='sidebar hidden relative px-2'> <div class='top'> <div class='logo pl-1 pt-4'> <img src='logo.svg' alt='logo'> </div><input type='text' name='search' id='search' class='mx-auto' placeholder='Search Country...'> </div><div class='bottom pt-3'> <a href='/'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='home.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Home</div></div></a> <a href='/overview'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg selected'> <div class='icon'> <img src='overview.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Overview</div></div></a> <a href='/infections-by-country'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='infections.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Infections by Country</div></div></a> <a href='/deaths'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='deaths.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Deaths by Country</div></div></a> <a href='/compare-countries'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='countries.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Compare Countries</div></div></a> <a href='/reports'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='reports.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Reports</div></div></a> </div><div class='profile-short-menu absolute bottom-0 left-0 flex justify-between px-4 w-full h-16 border-t border-gray-100 items-center shadow-lg'> <div class='profile-pic w-12'> <img src='profile.jpeg' alt='Profile' class='w-full rounded-full'> </div><div class='icons'> <span class='fas fa-cog fa-lg text-white'></span> </div></div></div><div class='container w-full h-full'> <nav class='nav px-4 h-10'> <div class='patch'> <span class='fas fa-bars fa-lg' id='nav_control_icon'></span> <input type='checkbox' id='nav_control' class='cursor-pointer'> </div></nav> <div class='content px-4' id='content'> <div class='pageHeading my-3 mx-auto ham' style='width: 85%;'> <h1 class='text-3xl font-semibold'>Overview:</h1> </div><div class='topStories w-10/12 mx-auto ham'> <div class='header flex mb-4'> <h1 class='text-2xl mr-3'>Top Stories</h1> </div><div class='glide' style='width: 100% !important;' id='topStories'> <div class='glide__track' data-glide-el='track'> <ul class='glide__slides'>";

        String[] topStoriesImgLink = {"https://ichef.bbci.co.uk/news/976/cpsprodpb/16BB3/production/_118870139_vaccinationcentretiruvannamalai.jpg",
         "https://static.ffx.io/images/$zoom_0.159%2C$multiply_0.4431%2C$ratio_1.5%2C$width_756%2C$x_0%2C$y_0/t_crop_custom/q_86%2Cf_auto/d82b0c2d19eabaae30c983be6bd691ff8f47af31", "Ford", "Mazda"};
        String[] topStoriesLink = {"https://www.bbc.com/news/world-asia-india-57400620",
         "https://www.theage.com.au/national/victoria/one-new-coronavirus-case-as-melburnians-head-to-the-regions-20210619-p582ea.html", "https://live-production.wcms.abc-cdn.net.au/d33871fb1b26f3b46b2f9a2aed8a9cea?impolicy=wcms_crop_resize&amp;cropH=1000&amp;cropW=1500&amp;xPos=0&amp;yPos=0&amp;width=862&amp;height=575", "Mazda"};
        String[] topStoriesName = {"India's vaccine drive: Stories from the best and worst districts", "Virus ‘absolutely still out there’ as Victoria records one new COVID-19 case", "Ford", "Mazda"};
        String[] topStoriesTimestamp = {"2 hours ago", "3 days ago", "Ford", "Mazda"};

        for(int i=0; i < topStoriesImgLink.length; i++){
            html = html +
            "<li class='glide__slide'>" +
                "<div class='cover border border-gray-300 rounded-lg pb-3'>" +
                    "<div class='imageHolder mb-2' >" +
                        "<img src='"+ topStoriesImgLink[i] +"' alt='Image' class='w-full rounded-t-lg'>" +                           
                    "</div>" +
                    "<div class='contentHolder px-3'>" +
                        "<div class='headingLink'>" +
                            "<a href='"+ topStoriesLink[i] +"' target='_blank' rel='noopener noreferrer' class='text-blue-600 font-semibold'>"+ topStoriesName[i] +"</a>" +
                        "</div>" +
                        "<div class='timestamp'>" +
                            "<p class='text-xs text-gray-400'>"+ topStoriesTimestamp[i] +".</p>" +
                        "</div>" +
                    "</div>" +
                "</div>" +
            "</li>";
        }

        html = html + " </ul> </div><div class='glide__arrows' data-glide-el='controls'> <button class='glide__arrow glide__arrow--left' data-glide-dir='<'><span class='fas fa-angle-left fa-2x pb-1 pr-1'></span></button> <button class='glide__arrow glide__arrow--right' data-glide-dir='>'><span class='fas fa-angle-right fa-2x pb-1 pl-1'></span></button> </div></div></div><div class='statistics w-10/12 mx-auto ham mt-8'> <div class='header flex mb-4'> <h1 class='text-2xl mr-3'>Statistics</h1> </div><div class='overviewGraph' class='w-8/12 h-auto mx-auto relative' style='width: 80%; margin: 0 auto;'> <canvas id='overviewGraph' class='w-full h-auto'></canvas> </div></div><div class='news w-10/12 mx-auto ham mt-8'> <div class='header flex mb-4'> <h1 class='text-2xl mr-3'>News</h1> </div><div class='glide' style='width: 100% !important;' id='news'> <div class='glide__track' data-glide-el='track'> <ul class='glide__slides'>";

        
        String[] topNewsImgLink = {"Volvo", "BMW", "Ford", "Mazda"};
        String[] topNewsLink = {"Volvo", "BMW", "Ford", "Mazda"};
        String[] topNewsName = {"Volvo", "BMW", "Ford", "Mazda"};
        String[] topNewsTimestamp = {"Volvo", "BMW", "Ford", "Mazda"};


        for(int i=0; i < topNewsImgLink.length; i++){
            html = html +
            "<li class='glide__slide'>" +
                "<div class='cover border border-gray-300 rounded-lg pb-3'>" +
                    "<div class='imageHolder mb-2' >" +
                        "<img src='"+ topNewsImgLink[i] +"' alt='Image' class='w-full rounded-t-lg'>" +                           
                    "</div>" +
                    "<div class='contentHolder px-3'>" +
                        "<div class='headingLink'>" +
                            "<a href='"+ topNewsLink[i] +"' target='_blank' rel='noopener noreferrer' class='text-blue-600 font-semibold'>"+ topNewsName[i] +"</a>" +
                        "</div>" +
                        "<div class='timestamp'>" +
                            "<p class='text-xs text-gray-400'>"+ topNewsTimestamp[i] +".</p>" +
                        "</div>" +
                    "</div>" +
                "</div>" +
            "</li>";
        }

        html = html + " </ul> </div><div class='glide__arrows' data-glide-el='controls'> <button class='glide__arrow glide__arrow--left' data-glide-dir='<'><span class='fas fa-angle-left fa-2x pb-1 pr-1'></span></button> <button class='glide__arrow glide__arrow--right' data-glide-dir='>'><span class='fas fa-angle-right fa-2x pb-1 pl-1'></span></button> </div></div></div><div class='statistics w-10/12 mx-auto ham mt-8'> <div class='header flex mb-4'> <h1 class='text-2xl mr-3'>Statistics</h1> </div><div class='overviewGraph' class='w-8/12 h-auto mx-auto relative' style='width: 80%; margin: 0 auto;'> <canvas id='overviewGraph' class='w-full h-auto'></canvas> </div></div><div class='facts w-10/12 mx-auto ham mt-8'> <div class='header flex mb-4'> <h1 class='text-2xl mr-3'>Facts</h1> </div><div class='glide' style='width: 100% !important;' id='facts'> <div class='glide__track' data-glide-el='track'> <ul class='glide__slides'>";
        

        
        String[] factsImageLink = {"Volvo", "BMW", "Ford", "Mazda"};
        String[] factsLink = {"Volvo", "BMW", "Ford", "Mazda"};
        String[] factsName = {"Volvo", "BMW", "Ford", "Mazda"};
        String[] factTimestamp = {"Volvo", "BMW", "Ford", "Mazda"};


        for(int i=0; i < factsImageLink.length; i++){
            html = html +
            "<li class='glide__slide'>" +
                "<div class='cover border border-gray-300 rounded-lg pb-3'>" +
                    "<div class='imageHolder mb-2' >" +
                        "<img src='"+ factsImageLink[i] +"' alt='Image' class='w-full rounded-t-lg'>" +                           
                    "</div>" +
                    "<div class='contentHolder px-3'>" +
                        "<div class='headingLink'>" +
                            "<a href='"+ factsLink[i] +"' target='_blank' rel='noopener noreferrer' class='text-blue-600 font-semibold'>"+ factsName[i] +"</a>" +
                        "</div>" +
                        "<div class='timestamp'>" +
                            "<p class='text-xs text-gray-400'>"+ factTimestamp[i] +".</p>" +
                        "</div>" +
                    "</div>" +
                "</div>" +
            "</li>";
        }




        html = html + " </ul> </div><div class='glide__arrows' data-glide-el='controls'> <button class='glide__arrow glide__arrow--left' data-glide-dir='<'><span class='fas fa-angle-left fa-2x pb-1 pr-1'></span></button> <button class='glide__arrow glide__arrow--right' data-glide-dir='>'><span class='fas fa-angle-right fa-2x pb-1 pl-1'></span></button> </div></div></div><div class='links w-10/12 mx-auto ham mt-8'> <div class='header flex mb-4'> <h1 class='text-2xl mr-3'>Links</h1> </div><div class='links-box'>";

        String[] LinkLinks = {"Volvo", "BMW", "Ford", "Mazda"};
        String[] LinkName = {"Volvo", "BMW", "Ford", "Mazda"};


        for(int i=0; i < LinkLinks.length; i++){
            html = html +
            "<a href='" + LinkLinks[i] + "' target='_blank' rel='noopener noreferrer'>" +
                "<div class='link w-full px-4 py-3 bg-gray-200 my-4 flex justify-center'>" +
                    LinkName[i] +
                    "<span class='fas fa-sm fa-external-link-alt mt-1 ml-3'></span>" +                           
                "</div>" +
            "</a>";
        }
        
        
        html = html + " </div></div></div></div></div></div><script defer src='https://use.fontawesome.com/releases/v5.15.3/js/all.js' integrity='sha384-haqrlim99xjfMxRP6EWtafs0sB1WKcMdynwZleuUSwJR0mDeRYbhtY+KPMr+JL6f' crossorigin='anonymous'></script> <script src='https://code.jquery.com/jquery-3.5.1.js'></script> <script src='https://cdn.jsdelivr.net/npm/chart.js@3.3.2/dist/chart.min.js'></script> <script src='https://cdnjs.cloudflare.com/ajax/libs/Glide.js/3.4.1/glide.min.js' integrity='sha512-BXASbMmKLu+RC5TDnkupyhvrjiOQXILh/5zgIS8k5JAC71a73lNweVEg/X+9XJQ7GK22PH9WpztY3zqrji+xrQ==' crossorigin='anonymous' referrerpolicy='no-referrer'></script> <script src='main.js'></script> <script src='setter.js'></script></body></html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}

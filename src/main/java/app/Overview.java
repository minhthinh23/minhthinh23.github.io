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
        String html = "<!DOCTYPE html><html lang='en'><head> <meta charset='UTF-8'> <meta http-equiv='X-UA-Compatible' content='IE=edge'> <meta name='viewport' content='width=device-width, initial-scale=1.0'> <title>Milestone Project</title> <link rel='stylesheet' href='tailwind.min.css'> <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/Glide.js/3.4.1/css/glide.theme.min.css' integrity='sha512-wCwx+DYp8LDIaTem/rpXubV/C1WiNRsEVqoztV0NZm8tiTvsUeSlA/Uz02VTGSiqfzAHD4RnqVoevMcRZgYEcQ==' crossorigin='anonymous' referrerpolicy='no-referrer'/> <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/Glide.js/3.4.1/css/glide.core.min.css' integrity='sha512-wYsVD8ho6rJOAo1Xf92gguhOGQ+aWgxuyKydjyEax4bnuEmHUt6VGwgpUqN8VlB4w50d0nt+ZL+3pgaFMAmdNQ==' crossorigin='anonymous' referrerpolicy='no-referrer'/> <link rel='stylesheet' href='main.css'> <style>body{overflow-x: hidden;}.glide__arrow--left{left: -25px; background-color: rgba(0, 0, 0, 10%); border-radius: 100%; width: 50px; height: 50px; box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3); -webkit-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3); -moz-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3);}.glide__arrow--right{right: -25px; background-color: rgba(0, 0, 0, 10%); border-radius: 100%; width: 50px; height: 50px; box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3); -webkit-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3); -moz-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3);}</style></head><body> <div class='cover flex'> <div class='sidebar hidden relative px-2'> <div class='top'> <div class='logo pl-1 pt-4'> <img src='logo.svg' alt='logo'> </div> <div class='searchBox relative'> <input type='text' name='search' id='search' class='mx-auto' placeholder='Search Country...'> <div class='loading absolute bg-white' id='searchResults'> <svg version='1.1' id='L1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' viewBox='0 0 100 100' enable-background='new 0 0 100 100' xml:space='preserve'> <circle fill='none' stroke='#2f49d0' stroke-width='6' stroke-miterlimit='15' stroke-dasharray='14.2472,14.2472' cx='50' cy='50' r='47'> <animateTransform attributeName='transform' attributeType='XML' type='rotate' dur='5s' from='0 50 50' to='360 50 50' repeatCount='indefinite'/> </circle> <circle fill='none' stroke='#2f49d0' stroke-width='1' stroke-miterlimit='10' stroke-dasharray='10,10' cx='50' cy='50' r='39'> <animateTransform attributeName='transform' attributeType='XML' type='rotate' dur='5s' from='0 50 50' to='-360 50 50' repeatCount='indefinite'/> </circle> <g fill='#2f49d0'> <rect x='30' y='35' width='5' height='30'> <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.1'/> </rect> <rect x='40' y='35' width='5' height='30'> <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.2'/> </rect> <rect x='50' y='35' width='5' height='30'> <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.3'/> </rect> <rect x='60' y='35' width='5' height='30'> <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.4'/> </rect> <rect x='70' y='35' width='5' height='30'> <animateTransform attributeName='transform' dur='1s' type='translate' values='0 5 ; 0 -5; 0 5' repeatCount='indefinite' begin='0.5'/> </rect> </g> </svg> </div></div> </div><div class='bottom pt-3'> <a href='/'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='home.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Home</div></div></a> <a href='/overview'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg selected'> <div class='icon'> <img src='overview.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Overview</div></div></a> <a href='/infections-by-country'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='infections.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Infections by Country</div></div></a> <a href='/deaths'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='deaths.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Deaths by Country</div></div></a> <a href='/compare-countries'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='countries.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Compare Countries</div></div></a> <a href='/reports'> <div class='item cursor-pointer w-full px-3 flex flex-row h-10 items-center my-3 rounded-lg '> <div class='icon'> <img src='reports.svg' alt='Home'> </div><div class='name-item cursor-pointer text-white px-2'>Reports</div></div></a> </div><div class='profile-short-menu absolute bottom-0 left-0 flex justify-between px-4 w-full h-16 border-t border-gray-100 items-center shadow-lg'> <div class='profile-pic w-12'> <img src='profile.jpeg' alt='Profile' class='w-full rounded-full'> </div><div class='icons'> <span class='fas fa-cog fa-lg text-white'></span> </div></div></div><div class='container w-full h-full'> <nav class='nav px-4 h-10'> <div class='patch'> <span class='fas fa-bars fa-lg' id='nav_control_icon'></span> <input type='checkbox' id='nav_control' class='cursor-pointer'> </div></nav> <div class='content px-4' id='content'> <div class='pageHeading my-3 mx-auto ham' style='width: 85%;'> <h1 class='text-3xl font-semibold'>Overview:</h1> </div><div class='topStories w-10/12 mx-auto ham'> <div class='header flex mb-4'> <h1 class='text-2xl mr-3'>Top Stories</h1> </div><div class='glide' style='width: 100% !important;' id='topStories'> <div class='glide__track' data-glide-el='track'> <ul class='glide__slides'>";

        String[] topStoriesImgLink = {"https://ichef.bbci.co.uk/news/976/cpsprodpb/16BB3/production/_118870139_vaccinationcentretiruvannamalai.jpg",
         "https://static.ffx.io/images/$zoom_0.159%2C$multiply_0.4431%2C$ratio_1.5%2C$width_756%2C$x_0%2C$y_0/t_crop_custom/q_86%2Cf_auto/d82b0c2d19eabaae30c983be6bd691ff8f47af31", 
         "https://static01.nyt.com/images/2021/06/16/science/00virus-pill2/merlin_188625387_cc66deef-2f70-46a9-8523-b84462118fa5-articleLarge.jpg?quality=75&auto=webp&disable=upscale", 
         "https://api.time.com/wp-content/uploads/2021/06/Afghanistan.jpg?w=800&quality=85",
         "https://img.etimg.com/thumb/msid-83706225,width-300,imgsize-361178,,resizemode-4,quality-100/.jpg",
         "https://images.theconversation.com/files/404506/original/file-20210604-21-8ba1qb.jpg?ixlib=rb-1.1.0&rect=27%2C68%2C4544%2C2940&q=45&auto=format&w=754&fit=clip",
         "https://img.etimg.com/thumb/msid-83692877,width-300,imgsize-50119,,resizemode-4,quality-100/covid-vaccine1r.jpg",
         "https://i.guim.co.uk/img/media/6f3d63a20831e0e175e0a85585268728750dd030/0_0_3504_2102/master/3504.jpg?width=445&quality=45&auto=format&fit=max&dpr=2&s=0ed106b4eecc046d703b7cc70c51dab2",
         "https://i.guim.co.uk/img/media/0cb1c6f25ea41b3893a647a8b4c9299ae894be39/0_31_3500_2100/master/3500.jpg?width=445&quality=45&auto=format&fit=max&dpr=2&s=5cc7f13a7e49ee0e520e0165597bb7ec"
        };
        String[] topStoriesLink = {"https://www.bbc.com/news/world-asia-india-57400620",
         "https://www.theage.com.au/national/victoria/one-new-coronavirus-case-as-melburnians-head-to-the-regions-20210619-p582ea.html", 
         "https://www.nytimes.com/2021/06/17/health/covid-pill-antiviral.html", 
         "https://time.com/6074378/afghanistan-covid19/",
         "https://economictimes.indiatimes.com/news/international/world-news/covid-19-leads-to-cognitive-behavioural-problems-in-patients-study/articleshow/83706225.cms",
         "https://theconversation.com/covid-19-recovery-some-economies-will-take-longer-to-rebound-this-is-bad-for-everyone-162023",
         "https://economictimes.indiatimes.com/news/international/world-news/china-says-one-billion-covid-19-vaccine-doses-administered/articleshow/83692840.cms",
         "https://www.theguardian.com/australia-news/2021/jun/21/covid-19-list-queensland-public-exposure-sites-hotspots-brisbane-regional-south-east-qld-sunshine-coast-caloundra-toowoomba-venues-case-location-alerts-metro-coronavirus-cases-outbreak-locations",
         "https://www.theguardian.com/australia-news/2021/jun/21/covid-border-restrictions-travel-victoria-vic-nsw-qld-queensland-wa-sa-where-you-can-not-go-australia"
        };
        String[] topStoriesName = {"India's vaccine drive: Stories from the best and worst districts", 
        "Virus ‘absolutely still out there’ as Victoria records one new COVID-19 case", 
        "A Pill to Treat Covid-19? The U.S. Is Betting on It.", 
        "Afghanistan Running Out of Oxygen as COVID-19 Surge Worsens",
        "COVID-19 leads to cognitive, behavioural problems in patients: Study",
        "COVID-19 recovery: some economies will take longer to rebound – this is bad for everyone",
        "China says one billion COVID-19 vaccine doses administered",
        "Queensland Covid-19 exposure sites: list of Qld coronavirus hotspots and case location alerts",
        "Covid travel restrictions: where you can and can’t go within Australia"
    };
        String[] topStoriesTimestamp = {"2 hours ago", "3 days ago", "5 days ago", "4 days ago", "5 hours ago", "14 days ago", "2 days ago", "19 days ago", "18 hours ago"
    };

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

        html = html + " </ul> </div><div class='glide__arrows' data-glide-el='controls'> <button class='glide__arrow glide__arrow--left' data-glide-dir='<'><span class='fas fa-angle-left fa-2x pb-1 pr-1'></span></button> <button class='glide__arrow glide__arrow--right' data-glide-dir='>'><span class='fas fa-angle-right fa-2x pb-1 pl-1'></span></button> </div></div></div><div class='statistics w-10/12 mx-auto ham mt-8'> <div class='header flex mb-4'> <h1 class='text-2xl mr-3'>Reports</h1> </div><div class='overviewGraph' class='w-8/12 h-auto mx-auto relative' style='width: 80%; margin: 0 auto; position= relative;'> <img src='112233.jpg' class='w-full'> </div></div><div class='news w-10/12 mx-auto ham mt-8'> <div class='header flex mb-4'> <h1 class='text-2xl mr-3'>Facts</h1> </div><div class='glide' style='width: 100% !important;' id='news'> <div class='glide__track' data-glide-el='track'> <ul class='glide__slides'>";

        
        String[] topFactsImgLink = {"https://vermontsouthclub.com.au/wp-content/uploads/2020/05/covid-19-banner.jpg", 
        "https://www.ncausa.org/portals/56/Images/Industry/covid-large-banner2.png?ver=2020-03-31-122349-787", 
        "https://criticalthinkinghub.com.au/wp-content/uploads/2020/03/covid-19-banner.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_xl52-c1uqhuhnAE1bHO8WsC1JOU4gar1sw&usqp=CAU",
        "https://www.escardio.org/static-file/Escardio/Medias/education/covid-19/Covid-19-Webpage-banner-1170x240-opt2.jpg?mts=1594053162000.jpg"
        };
        String[] topFactsLink = {"https://vermontsouthclub.com.au/wp-content/uploads/2020/05/covid-19-banner-300x83.jpg", 
        "https://www.ncausa.org/portals/56/Images/Industry/covid-large-banner2.png?ver=2020-03-31-122349-787", 
        "https://criticalthinkinghub.com.au/wp-content/uploads/2020/03/covid-19-banner.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_xl52-c1uqhuhnAE1bHO8WsC1JOU4gar1sw&usqp=CAU",
        "https://www.escardio.org/static-file/Escardio/Medias/education/covid-19/Covid-19-Webpage-banner-1170x240-opt2.jpg?mts=1594053162000.jpg" 
        };
        String[] topFactsName = {"There are currently 3,082,663 confirmed deaths due to COVID-19", 
        "The amount of confirmed COVID-19 cases equates to approximiately 1.89% of the global population", 
        "The mortality rate of COVID - 19 is currently 2.13%",
        "Around 2.4bn COVID-19 vaccinations have been administed worldwide",
        "The USA has the most cases with 31,929,199 confirmed infections"
        };
        String[] topFactsTimestamp = {"RMIT COVID-19 Data", 
        "RMIT COVID-19 Data",
        "RMIT COVID-19 Data",
        "https://www.statista.com/",
        "RMIT COVID-19 Data"
        };


        for(int i=0; i < topFactsImgLink.length; i++){
            html = html +
            "<li class='glide__slide'>" +
                "<div class='cover border border-gray-300 rounded-lg pb-3'>" +
                    "<div class='imageHolder mb-2' >" +
                        "<img src='"+ topFactsImgLink[i] +"' alt='Image' class='w-full rounded-t-lg'>" +                           
                    "</div>" +
                    "<div class='contentHolder px-3'>" +
                        "<div class='headingLink'>" +
                            "<a href='"+ topFactsLink[i] +"' target='_blank' rel='noopener noreferrer' class='text-blue-600 font-semibold'>"+ topFactsName[i] +"</a>" +
                        "</div>" +
                        "<div class='timestamp'>" +
                            "<p class='text-xs text-gray-400'>"+ topFactsTimestamp[i] +".</p>" +
                        "</div>" +
                    "</div>" +
                "</div>" +
            "</li>";
        }

        // html = html + " </ul> </div><div class='glide__arrows' data-glide-el='controls'> <button class='glide__arrow glide__arrow--left' data-glide-dir='<'><span class='fas fa-angle-left fa-2x pb-1 pr-1'></span></button> <button class='glide__arrow glide__arrow--right' data-glide-dir='>'><span class='fas fa-angle-right fa-2x pb-1 pl-1'></span></button> </div></div></div><div class='facts w-10/12 mx-auto ham mt-8'> <div class='header flex mb-4'> <h1 class='text-2xl mr-3'>News</h1> </div><div class='glide' style='width: 100% !important;' id='facts'> <div class='glide__track' data-glide-el='track'> <ul class='glide__slides'>";
        

        



        html = html + " </ul> </div><div class='glide__arrows' data-glide-el='controls'> <button class='glide__arrow glide__arrow--left' data-glide-dir='<'><span class='fas fa-angle-left fa-2x pb-1 pr-1'></span></button> <button class='glide__arrow glide__arrow--right' data-glide-dir='>'><span class='fas fa-angle-right fa-2x pb-1 pl-1'></span></button> </div></div></div><div class='links w-10/12 mx-auto ham mt-8'> <div class='header flex mb-4'> <h1 class='text-2xl mr-3'>Links</h1> </div><div class='links-box'>";

        String[] LinkLinks = {"https://www.who.int/", 
        "https://www.health.gov.au/", "https://www.mohfw.gov.in/", "https://www.worldometers.info/coronavirus/"};
        String[] LinkName = {"The World Health Organisation", "Australian Government - Department of Health", "Government of India - Ministry of Health and Family Welfare", "WorldOMeter COVID 1"};


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

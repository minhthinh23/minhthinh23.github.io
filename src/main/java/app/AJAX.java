package app;

import java.util.*;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.json.simple.JSONObject;
/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin by writing the raw HTML into a Java
 * String object
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */

public class AJAX implements Handler {
    public static String dateFormat(String date){
        String[] broke = date.split("/");
        return broke[2].length() > 2 ? broke[2].substring(broke[2].length() - 2) +"/"+broke[1]+"/"+broke[0] : broke[2]+"/"+broke[1]+"/"+broke[0];
    }

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/ajax/:key/:data";

    @Override
    public void handle(Context context) throws Exception {

        // JDBC Connection
        JDBCConnection jdbc = new JDBCConnection();
        // australianStateData
        if(context.pathParam("key").equals("reportModal")){
            ArrayList<ArrayList<String>> country = jdbc.modalCountryData(Integer.parseInt(context.pathParam("data")));
            context.json(country);
        }else if(context.pathParam("key").equals("search")){
            JSONObject search = jdbc.searchResults(context.pathParam("data"));
            context.json(search);
        }else if(context.pathParam("key").equals("countryName")){
            ArrayList<String> names = jdbc.getCountryList();
            context.json(names);
        }else if(context.pathParam("key").equals("country")){
            String[] splitter = context.pathParam("data").split(" ");
            JSONObject pass;
            if(splitter.length == 1){
                pass = jdbc.countryCompare(Integer.parseInt(splitter[0]), "");
            }else{
                StringBuilder sb = new StringBuilder(splitter[1]);
                sb.insert(splitter[1].length() - 4, '/');
                sb.insert(splitter[1].length() - 6, '/');
                sb.insert(splitter[1].length() - 13, '/');
                sb.insert(splitter[1].length() - 15, '/');
                splitter[1] = sb.toString();
                String date[] = splitter[1].split("&");
                date[0] = dateFormat(date[0]);
                date[1] = dateFormat(date[1]);
                splitter[1] = date[0]+"&"+date[1];

                pass =  jdbc.countryCompare(Integer.parseInt(splitter[0]), splitter[1]);

            }
            context.json(pass);
        }else if(context.pathParam("key").equals("region")){
            ArrayList<String> names = jdbc.getCountryList();
            context.json(names);
        }
    }



}
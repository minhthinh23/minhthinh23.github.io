package app;

import java.util.*;
import io.javalin.http.Context;
import io.javalin.http.Handler;

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
        }else if(context.pathParam("key").equals("searchResults")){
            ArrayList<String[]> searchRes = jdbc.searchResults(context.pathParam("data"));
            context.json(searchRes);
        }
    }



}
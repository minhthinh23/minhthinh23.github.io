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
public class Compare implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/compare-countries";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Movies</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        // Add the body
        html = html + "<body>";

        // Add HTML for link back to the homepage
        html = html + "<h1>Comparing Countries:</h1>";
        html = html + "<h2>Select the Country or State you would like to compare.</h2>";
        //MAKE BUTTON WHICH DISPLAYS LIST OF COUNTRIES
        
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> category = jdbc.getCountryList();
        html = html + "<form action='/comparecountries.html' method='post'>";
        html = html + "   <div class='form-group'>";
        html = html + "      <label for='countryname_drop'>Select the type Movie Type (Dropdown):</label>";
        html = html + "      <select id='countryname_drop' name='countryname_drop'>";
        for (String categories : category) {
            html = html + "<option>" + categories + "</option>";
        }
        html = html + "      </select>";
        html = html + "   </div>";
        html = html + "   <button type='submit' class='btn btn-primary'>Submit</button>";
        html = html + "</form>";


        String countryname_drop = context.formParam("countryname_drop");
        if (countryname_drop == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<h2><i>No Results to show for dropbox</i></h2>";
        } else {
            // If NOT NULL, then lookup the movie by type!
            html = html + jdbc.outputCountryDataString(countryname_drop);
        }
        html = html + "<p>Return to Homepage: ";
        html = html + "<a href='/'>Link to Homepage</a>";
        html = html + "</p>";

 
        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}

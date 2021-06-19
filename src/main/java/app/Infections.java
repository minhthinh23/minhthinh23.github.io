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
public class Infections implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/infections-by-country";

    @Override
    public void handle(Context context) throws Exception {

        String html = "<html>";


        html = html + "<head>" + 
               "<title>Infections By Country:</title>";


        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";


        html = html + "<body>";


        html = html + "<div class='world'>";
        html = html + "<h1>Cases Per Country</h1>";
        html = html + "<h2>Apply filters here:</h2>";

        html = html + "<form action='/infectionsbycountry.html' method='post'>";

                html = html + "   <div class='form-group'>";
                    html = html + "      <label for='entrydate_textbox'>First Date (dd/mm/yy)</label>";
                    html = html + "      <input class='form-control' id='entrydate_textbox' name='entrydate_textbox'>";
                html = html + "      <label for='exitdate_textbox'>Last Date (dd/mm/yy)</label>";
                html = html + "      <input class='form-control' id='exitdate_textbox' name='exitdate_textbox'>";
            html = html + "   </div>";
            html = html + "   <button type='submit' class='btn btn-primary'>Submit</button>";
        html = html + "</form>";


        String entrydate_textbox = context.formParam("entrydate_textbox");
        String exitdate_textbox = context.formParam("exitdate_textbox");


        html = html + "<div id ='d1'>";
        if (entrydate_textbox == null || entrydate_textbox == "") {
            html = html + "<p><i>Displaying info for 20/01/2020 until 21/04/2020</i></p>"; 
            html = html + "<div class='container'>"; 
            html = html + "<table class = 'infectionstable sticky'><thead><tr><th>Country:</th> <th>Longitude:</th><th>Latitude:</th> <th>Population:</th> <th>Total Cases:</th> <th>Most Cases in a Day:</th>";
            html = html + "</tr></thead><tbody>";
        }
        else {
            html = html + "<p><i>Displaying info for " + entrydate_textbox + " until " + exitdate_textbox + "</i></p>";
            html = html + "<div class='container'>";
            html = html + "<table class = 'infectionstable sticky'><thead><tr><th>Country:</th> <th>Longitude:</th><th>Latitude:</th> <th>Population:</th> <th>Total Cases:</th> <th>Most Cases in a Day:</th>";
            html = html + "</tr></thead><tbody>";
        }


        

        if (entrydate_textbox == null || entrydate_textbox == "") {
            for(int i = 1; i < 191; i++){
                html = html + "<tr>";
                JDBCConnection jdbc = new JDBCConnection();
                ArrayList<String> countryData = jdbc.printCountryData(i);
                html = html + "<td>" + JDBCConnection.printCountryName(i) + "</td>";
    
                for(int j = 0; j < 5; j++){
                    html = html + "<td>" + countryData.get(j) + "</td>";
                }
            }
        }         
        else {
            for(int i = 1; i < 191; i++){
                html = html + "<tr>";
                JDBCConnection jdbc = new JDBCConnection();
                ArrayList<String> countryData = jdbc.printCountryDataWithRange(i, entrydate_textbox, exitdate_textbox);
                html = html + "<td>" + JDBCConnection.printCountryName(i) + "</td>";
    
                for(int j = 0; j < 5; j++){
                    html = html + "<td>" + countryData.get(j) + "</td>";
                }

            }
        }

        html = html + "</tbody></table>";
        html = html + "</div>";
        html = html + "</div>";





    html = html + "<div class='australia'>";

        html = html + "<h2>Australia</h2>";
        html = html + "<form action='/infectionsbycountry.html' method='post'>";
        html = html + "      <label for='australia_drop'>Select the range:</label>";
        html = html + "      <select id='australia_drop' name='australia_drop'>";
            html = html + "<option selected>Per Capita</option>";
            html = html + "<option>Total</option>";
        html = html + "      </select>";

        html = html + "   <button type='submit' class='btn btn-primary'>Submit</button>";
        html = html + "</form>";

        html = html + "<table class='infectionstable'><thead><tr><th>States:</th> <th>Cases:</th><th>Recovered:</th></tr></thead><tbody>";
        JDBCConnection jdbc = new JDBCConnection();
        html = html + jdbc.outputAustralianStateInfo("Victoria");
        html = html + jdbc.outputAustralianStateInfo("Queensland");
        html = html + jdbc.outputAustralianStateInfo("Tasmania");
        html = html + jdbc.outputAustralianStateInfo("New South Wales");
        html = html + jdbc.outputAustralianStateInfo("Western Australia");
        html = html + jdbc.outputAustralianStateInfo("Northern Territory");
        html = html + jdbc.outputAustralianStateInfo("Australian Capital Territory");
        html = html + jdbc.outputAustralianStateInfo("South Australia");
        html = html + "</tbody></table>";
        html = html + "</div>";
  

        html = html + "<div class='retu'>";
        html = html + "<p>Return to Homepage: ";
        html = html + "<a href='/'>Link to Homepage</a>";
        html = html + "</p>";
        html = html + "</div>";




        // Finish the List HTML
        html = html + "</ul>";

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}

package app;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;


/**
 * Main Application Class.
 * <p>
 * Running this class as regular java application will start the 
 * Javalin HTTP Server and our web application.
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class App {

    public static final int         JAVALIN_PORT    = 7000;
    public static final String      CSS_DIR         = "css/";

    public static void main(String[] args) {
        // Create our HTTP server and listen in port 7000
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));
            
            // Uncomment this if you have files in the CSS Directory
            config.addStaticFiles(CSS_DIR);

            // Other Files
            config.addStaticFiles("assets/");
            config.addStaticFiles("DataTables/");
            config.addStaticFiles("jquery-ui/");
            config.addStaticFiles("js/");
            //config.addStaticFiles("node_modules/");
        }).start(JAVALIN_PORT);


        // Configure Web Routes
        configureRoutes(app);
    }

    public static void configureRoutes(Javalin app) {
        // ADD ALL OF YOUR WEBPAGES HERE (GET)
        app.get(Index.URL, new Index());
        app.get(Infections.URL, new Infections());
        app.get(Deaths.URL, new Deaths());
        app.get(Reports.URL, new Reports());
        app.get(Overview.URL, new Overview());
        app.get(Compare.URL, new Compare());
        app.get(AJAX.URL, new AJAX());
        
        // Fetch Data (POST)
        app.post(Infections.URL, new Infections());
        app.post(Compare.URL, new Compare());
        app.post(Reports.URL, new Reports());
        app.post(Deaths.URL, new Deaths());
    }

}

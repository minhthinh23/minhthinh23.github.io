package app;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 * 
 * This is an example JDBC Connection that has a single query for the Movies Database
 * This is similar to the project workshop JDBC examples.
 *
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    private static final String DATABASE = "jdbc:sqlite:database/COVID19.db";

    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    public ArrayList<String> getMovies() {
        ArrayList<String> movies = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                //int id              = results.getInt("mvnumb");
                String movieName     = results.getString("mvtitle");
                //int year            = results.getInt("yrmde");
                //String type         = results.getString("mvtype");

                // For now we will just store the movieName and ignore the id
                movies.add(movieName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return movies;
    }

    public ArrayList<String> getCountries() {
        ArrayList<String> movies = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM Country";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                //int id              = results.getInt("mvnumb");
                String movieName     = results.getString("Country_Name");
                //int year            = results.getInt("yrmde");
                //String type         = results.getString("mvtype");

                // For now we will just store the movieName and ignore the id
                movies.add(movieName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return movies;
    }
    
    public static int getDeaths() {

        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM Country";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                //int id              = results.getInt("mvnumb");
                int nodeaths     = results.getInt("total_deaths");
                //int year            = results.getInt("yrmde");
                //String type         = results.getString("mvtype");

                // For now we will just store the movieName and ignore the id
                
                deaths = deaths + nodeaths;

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }
   
    public static int getMostDeaths() {

        int deaths=0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MAX(Total_Deaths), Country_name FROM Country;";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                //int id              = results.getInt("mvnumb");
                int nodeaths     = results.getInt("MAX(Total_Deaths)");
                //int year            = results.getInt("yrmde");
                //String countryname         = results.getString("Country_name");

                // For now we will just store the movieName and ignore the id
                
                deaths = nodeaths;
        

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }
    
    public static String getMostDeathsCountry() {
        String countryname = "";
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT MAX(Total_Deaths), Country_name FROM Country;";
            ResultSet results = statement.executeQuery(query);

                countryname         = results.getString("Country_name");

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

                System.err.println(e.getMessage());
            }
        }


        return countryname;
    }
    
    public static int getCases() {

        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM Country";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                //int id              = results.getInt("mvnumb");
                int nodeaths     = results.getInt("total_cases");
                //int year            = results.getInt("yrmde");
                //String type         = results.getString("mvtype");

                // For now we will just store the movieName and ignore the id
                
                deaths = deaths + nodeaths;

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }
   
    public ArrayList<String> getCountryList() {
        ArrayList<String> category = new ArrayList<String>();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT * FROM COUNTRY";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String type         = results.getString("Country_name");
                category.add(type);
            }

            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return category;
    }     

    public ArrayList<Integer> outputCompareCountryData(String countryName) {
        ArrayList<Integer> countryData = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Total_Cases, Total_Deaths, Population, MAX(No_Cases) FROM Country JOIN CountryData ON country.Country_Name=countrydata.CountryName WHERE Country_Name ='" + countryName + "'";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                int Total_Cases     = results.getInt("Total_Cases");
                int Total_Deaths    = results.getInt("Total_Deaths");
                int Population      = results.getInt("Population");
                int max_Cases       = results.getInt("MAX(No_Cases)");
                countryData.add(Total_Cases);
                countryData.add(Total_Deaths);
                countryData.add(Population);
                countryData.add(max_Cases);

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countryData;
    }

    public String outputCountryDataString(String countryName) {
        String html = "";
        html = html + "<h2>" + countryName + "</h2>";

        // Look up movies from JDBC
        ArrayList<Integer> movies = outputCompareCountryData(countryName);
        
        // Add HTML for the movies list
        html = html + "<table><tr>";
        html = html + "<th>Total Cases:</th><th>Total Deaths:</th><th>Population:</th><th>Most Infections in a Day</th>";
        html = html + "</tr><tr>";
        for (Integer movie : movies) {
            html = html + "<td>" + movie + "</td>";
        }
        html = html + "</tr></table>";

        return html;
    }

    public String outputAustralianStateInfo(String State) {
        String html = "";


        // Look up movies from JDBC
        ArrayList<Integer> stateinfo = australianStateData(State);
        
        // Add HTML for the movies list
        html = html + "<tr><td>" + State + "</td>";

        for (Integer value : stateinfo) {
            html = html + "<td>" + value + "</td>";
        }
        html = html + "</tr>";

        return html;
    }

    public ArrayList<Integer> australianStateData(String countryName) {
        ArrayList<Integer> countryData = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT REGION_NAME, TOTAL_CASES, TOTAL_DEATHS FROM REGION WHERE REGION_NAME = '" + countryName + "'";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                
                int Total_Cases     = results.getInt("Total_Cases");
                int Total_Deaths    = results.getInt("Total_Deaths");
                countryData.add(Total_Cases);
                countryData.add(Total_Deaths);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countryData;
    }

    public static int getCasesLastMonth() {

        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT SUM(No_Cases) FROM COUNTRYDATA WHERE DATE BETWEEN '22/03/2021' AND '22/04/2021';";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                //int id              = results.getInt("mvnumb");
                int nodeaths     = results.getInt("SUM(No_Cases)");
                //int year            = results.getInt("yrmde");
                //String type         = results.getString("mvtype");

                // For now we will just store the movieName and ignore the id
                
                deaths = deaths + nodeaths;

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }
 
    public static int get30DayAverage() {

        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT AVG(No_Cases) FROM COUNTRYDATA WHERE DATE BETWEEN '22/03/2021' AND '22/04/2021';";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                //int id              = results.getInt("mvnumb");
                int nodeaths     = results.getInt("AVG(No_Cases)");
                //int year            = results.getInt("yrmde");
                //String type         = results.getString("mvtype");

                // For now we will just store the movieName and ignore the id
                
                deaths = deaths + nodeaths;

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deaths;
    }    

    public ArrayList<String> printCountryData(int i) {
        ArrayList<String> countryData = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT *,ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2), MAX(No_Cases) FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.ROWID='" + i + "';";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String Longitude    = results.getString("Longitude");
                String Latitude      = results.getString("Latitude");
                String Total_Cases     = results.getString("Total_Cases");
                String Population      = results.getString("Population");
                String max_Cases       = results.getString("MAX(No_Cases)");
                //String perCapita       = results.getString("ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2)");
                String date            = results.getString("Date");
                countryData.add(Longitude);
                countryData.add(Latitude);
                countryData.add(Population);
                countryData.add(Total_Cases);
                //countryData.add(perCapita);
                max_Cases = max_Cases + " - " + date;
                countryData.add(max_Cases);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        return countryData;
    }

    public ArrayList<String> printCountryDataWithRange(int k, String i, String j) {
        ArrayList<String> countryData = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Longitude, Latitude, Population, sum(no_cases), max(no_cases), date FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.ROWID = "+ k +" AND DATE BETWEEN '" + i + "' AND '"+ j + "' GROUP BY COUNTRY.COUNTRY_NAME;";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String Longitude    = results.getString("Longitude");
                String Latitude      = results.getString("Latitude");
                String Total_Cases     = results.getString("sum(no_cases)");
                String Population      = results.getString("Population");
                String max_Cases       = results.getString("max(no_cases)");
                //String perCapita       = results.getString("ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2)");
                String date            = results.getString("date");
                countryData.add(Longitude);
                countryData.add(Latitude);
                countryData.add(Population);
                countryData.add(Total_Cases);
                //countryData.add(perCapita);
                max_Cases = max_Cases + " - " + date;
                countryData.add(max_Cases);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        return countryData;
    }
    
    
    
    public static String printCountryName(int i) {
        String countryName = "";

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT COUNTRY_NAME FROM COUNTRY WHERE ROWID =" + i +";";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                //int id              = results.getInt("mvnumb");
                countryName     = results.getString("Country_Name");
                //int year            = results.getInt("yrmde");
                //String type         = results.getString("mvtype");

                // For now we will just store the movieName and ignore the id
                

            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countryName;
    }    









//FUNCTIONS IMPLEMENTED INTO FINAL DESIGN BELOW//

    public ArrayList<String> printCountryReportData(int i) {
        ArrayList<String> countryData = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT COUNTRY.COUNTRY_NAME, SUM(NO_CASES), SUM(NO_DEATHS), ROUND(AVG(NO_CASES), 2) AS AVG, ROUND(SUM(NO_DEATHS) * 100.0 / SUM(NO_CASES), 2) AS Percent FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.ROWID = " + i + " GROUP BY COUNTRY.COUNTRY_NAME;";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {

                String Name            = results.getString("Country_name");
                String Total_Cases     = results.getString("SUM(No_Cases)");
                String Total_Deaths      = results.getString("SUM(No_Deaths)");
                String max_Cases       = results.getString("AVG");
                //String perCapita       = results.getString("ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2)");
                String date            = results.getString("Percent");
                countryData.add(Name);
                countryData.add(Total_Cases);
                countryData.add(Total_Deaths);
                countryData.add(max_Cases);
                //countryData.add(perCapita);
                countryData.add(date);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        return countryData;
    } 
    
    public ArrayList<String> printCountryReportDataWithRange(int i, String j, String k) {
        ArrayList<String> countryData = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT COUNTRY.COUNTRY_NAME, SUM(NO_CASES), SUM(NO_DEATHS), ROUND(AVG(NO_CASES), 2) AS AVG, ROUND(SUM(NO_DEATHS) * 100.0 / SUM(NO_CASES), 2) AS Percent FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.ROWID = " + i + " GROUP BY COUNTRY.COUNTRY_NAME;";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {

                String Name            = results.getString("Country_name");
                String Total_Cases     = results.getString("SUM(No_Cases)");
                String Total_Deaths      = results.getString("SUM(No_Deaths)");
                String max_Cases       = results.getString("AVG");
                //String perCapita       = results.getString("ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2)");
                String date            = results.getString("Percent");
                countryData.add(Name);
                countryData.add(Total_Cases);
                countryData.add(Total_Deaths);
                countryData.add(max_Cases);
                //countryData.add(perCapita);
                countryData.add(date);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        return countryData;
    }   

}

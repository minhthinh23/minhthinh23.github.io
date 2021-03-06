package app;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONObject;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database. Allows SQL
 * queries to be used with the SQLLite Databse in Java.
 * 
 * This is an example JDBC Connection that has a single query for the Movies
 * Database This is similar to the project workshop JDBC examples.
 *
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    private static final String DATABASE = "jdbc:sqlite:database/COVID19.db";

    public ArrayList<String> getCountries() {
        ArrayList<String> countryName = new ArrayList<String>();

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT COUNTRY_NAME FROM Country";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String country = results.getString("Country_Name");
                countryName.add(country);
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
        return countryName;
    }

    public static int getTotalDeaths(int rowId, String countryName) {
        int deaths = 0;
        String query = "";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            if (rowId != 0) {
                query = "SELECT TOTAL_DEATHS FROM Country WHERE COUNTRY.ROWID=" + rowId + ";";
            } else if (countryName != "" && countryName != null) {
                query = "SELECT TOTAL_DEATHS FROM COUNTRY WHERE COUNTRY.COUNTRY_NAME='" + countryName + "'; ";
            } else {
                query = "SELECT TOTAL_DEATHS FROM COUNTRY;";
            }

            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                int nodeaths = results.getInt("total_deaths");
                deaths = deaths + nodeaths;
            }

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
        return deaths;
    }

    // Potentially multiple values can be included//

    public static int getMostDeaths() {
        int deaths = 0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT MAX(Total_Deaths), Country_name FROM Country;";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                int nodeaths = results.getInt("MAX(Total_Deaths)");
                deaths = nodeaths;
            }
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
        return deaths;
    }

    public static String getMaxDeaths(int flag, String CountryName) {
        flag = 0;

        String countryname = "";
        Connection connection = null;
        String query = "";
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            if (flag == 0) {
                query = "SELECT MAX(No_Deaths), Country_name FROM Country;";
            }
            ResultSet results = statement.executeQuery(query);
            countryname = results.getString("Country_name");
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
                // int id = results.getInt("mvnumb");
                int nodeaths = results.getInt("total_cases");
                // int year = results.getInt("yrmde");
                // String type = results.getString("mvtype");

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
                String type = results.getString("Country_name");
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
            String query = "SELECT Total_Cases, Total_Deaths, Population, MAX(No_Cases) FROM Country JOIN CountryData ON country.Country_Name=countrydata.CountryName WHERE Country_Name ='"
                    + countryName + "'";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                int Total_Cases = results.getInt("Total_Cases");
                int Total_Deaths = results.getInt("Total_Deaths");
                int Population = results.getInt("Population");
                int max_Cases = results.getInt("MAX(No_Cases)");
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
        html = html
                + "<th>Total Cases:</th><th>Total Deaths:</th><th>Population:</th><th>Most Infections in a Day</th>";
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
            String query = "SELECT REGION_NAME, TOTAL_CASES, TOTAL_DEATHS FROM REGION WHERE REGION_NAME = '"
                    + countryName + "'";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {

                int Total_Cases = results.getInt("Total_Cases");
                int Total_Deaths = results.getInt("Total_Deaths");
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
                // int id = results.getInt("mvnumb");
                int nodeaths = results.getInt("SUM(No_Cases)");
                // int year = results.getInt("yrmde");
                // String type = results.getString("mvtype");

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
                // int id = results.getInt("mvnumb");
                int nodeaths = results.getInt("AVG(No_Cases)");
                // int year = results.getInt("yrmde");
                // String type = results.getString("mvtype");

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

    // FUNCTIONS IMPLEMENTED INTO FINAL DESIGN BELOW//
    // FUNCTIONS FOR INFECTIONS PAGE//
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
            String query = "SELECT COUNTRY_NAME FROM COUNTRY WHERE ROWID =" + i + ";";

            // Get Result
            ResultSet results = statement.executeQuery(query);
            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                // int id = results.getInt("mvnumb");
                countryName = results.getString("Country_Name");
                // int year = results.getInt("yrmde");
                // String type = results.getString("mvtype");

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
            String query = "SELECT *,ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2), MAX(No_Cases) FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.ROWID='"
                    + i + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String Longitude = results.getString("Longitude");
                String Latitude = results.getString("Latitude");
                String Total_Cases = results.getString("Total_Cases");
                String Population = results.getString("Population");
                String max_Cases = results.getString("MAX(No_Cases)");
                // String perCapita =
                // results.getString("ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2)");
                String date = results.getString("Date");
                countryData.add(Longitude);
                countryData.add(Latitude);
                countryData.add(Population);
                countryData.add(Total_Cases);
                // countryData.add(perCapita);
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
            String query = "SELECT Longitude, Latitude, Population, sum(no_cases), max(no_cases), date FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.ROWID = "
                    + k + " AND DATE BETWEEN '" + i + "' AND '" + j + "' GROUP BY COUNTRY.COUNTRY_NAME;";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String Longitude = results.getString("Longitude");
                String Latitude = results.getString("Latitude");
                String Total_Cases = results.getString("sum(no_cases)");
                String Population = results.getString("Population");
                String max_Cases = results.getString("max(no_cases)");
                // String perCapita =
                // results.getString("ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2)");
                String date = results.getString("date");
                countryData.add(Longitude);
                countryData.add(Latitude);
                countryData.add(Population);
                countryData.add(Total_Cases);
                // countryData.add(perCapita);
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

    public ArrayList<String> printCountryDeathDataWithRange(int k, String i, String j) {
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
            String query = "SELECT ROUND(AVG(NO_DEATHS), 2) AS AVG, Population, sum(no_Deaths), max(no_deaths), date FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.ROWID= "
                    + k + " AND DATE BETWEEN '" + i + "' AND '" + j + "' GROUP BY COUNTRY.COUNTRY_NAME;";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String Population = results.getString("Population");
                String Total_Deaths = results.getString("sum(no_Deaths)");
                String Average = results.getString("AVG");
                String max_Cases = results.getString("max(no_deaths)");
                // String perCapita =
                // results.getString("ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2)");
                String date = results.getString("date");
                // countryData.add(Longitude);

                countryData.add(Population);
                countryData.add(Total_Deaths);
                countryData.add(Average);
                // countryData.add(perCapita);
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

    public String outputStateInfo(String State) {
        String html = "";

        // Look up movies from JDBC
        ArrayList<Integer> stateinfo = GetStateData(State);

        // Add HTML for the movies list
        html = html + "<tr data-country='none'><td>" + State + "</td>";

        for (Integer value : stateinfo) {
            html = html + "<td>" + value + "</td>";
        }
        html = html + "</tr>";

        return html;
    }

    public ArrayList<Integer> GetStateData(String countryName) {
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
            String query = "SELECT REGION_NAME, TOTAL_CASES, TOTAL_DEATHS FROM REGION WHERE REGION_NAME = '"
                    + countryName + "'";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {

                int Total_Cases = results.getInt("Total_Cases");
                int Total_Deaths = results.getInt("Total_Deaths");
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

    public ArrayList<String> printCountryDataWithRangeStringInput(String k, String i, String j) {
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
            String query = "SELECT *, Longitude, Latitude, Population, sum(no_cases), max(no_cases), date FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.Country_name ='"
                    + k + "' AND DATE BETWEEN '" + i + "' AND '" + j + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String name = results.getString("Country_name");
                String Longitude = results.getString("Longitude");
                String Latitude = results.getString("Latitude");
                String Total_Cases = results.getString("sum(no_cases)");
                String Population = results.getString("Population");
                String max_Cases = results.getString("max(no_cases)");
                // String perCapita =
                // results.getString("ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2)");
                String date = results.getString("date");
                countryData.add(name);
                countryData.add(Longitude);
                countryData.add(Latitude);
                countryData.add(Population);
                countryData.add(Total_Cases);
                // countryData.add(perCapita);
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

    public ArrayList<String> printCountryDataStringInput(String i) {
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
            String query = "SELECT *,ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2), MAX(No_Cases) FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.Country_name='"
                    + i + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String name = results.getString("Country_name");
                String Longitude = results.getString("Longitude");
                String Latitude = results.getString("Latitude");
                String Total_Cases = results.getString("Total_Cases");
                String Population = results.getString("Population");
                String max_Cases = results.getString("MAX(No_Cases)");
                // String perCapita =
                // results.getString("ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2)");
                String date = results.getString("Date");
                countryData.add(name);
                countryData.add(Longitude);
                countryData.add(Latitude);
                countryData.add(Population);
                countryData.add(Total_Cases);
                // countryData.add(perCapita);
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

    // FUNCTIONS DEATHS PAGE//
    public ArrayList<String> printCountryDeathsData(int i) {
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
            String query = "SELECT COUNTRY.COUNTRY_NAME, POPULATION, SUM(NO_DEATHS), ROUND(AVG(NO_CASES), 2) AS AVG, MAX(NO_DEATHS) ,DATE FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.ROWID="
                    + i + ";";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String TOTAL_DEATHS = results.getString("SUM(NO_DEATHS)");
                String Avg_Deaths = results.getString("AVG");
                // String Total_Cases = results.getString("AVG(NO_DEATHS)");
                String Population = results.getString("Population");
                String max_Deaths = results.getString("MAX(NO_DEATHS)");
                // String perCapita =
                // results.getString("ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2)");
                String date = results.getString("Date");
                countryData.add(Population);
                countryData.add(TOTAL_DEATHS);
                countryData.add(Avg_Deaths);
                max_Deaths = max_Deaths + " - " + date;
                countryData.add(max_Deaths);
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

    public ArrayList<String> printCountryDeathsDataStringInput(String i) {
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
            String query = "SELECT COUNTRY.COUNTRY_NAME, POPULATION, TOTAL_DEATHS, ROUND(AVG(NO_CASES), 2) AS AVG, MAX(NO_DEATHS) ,DATE FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.COUNTRY_NAME='"
                    + i + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String Country_name = results.getString("COUNTRY.COUNTRY_NAME");
                String TOTAL_DEATHS = results.getString("TOTAL_DEATHS");
                String Avg_Deaths = results.getString("AVG");
                // String Total_Cases = results.getString("AVG(NO_DEATHS)");
                String Population = results.getString("Population");
                String max_Deaths = results.getString("MAX(NO_DEATHS)");
                // String perCapita =
                // results.getString("ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2)");
                String date = results.getString("Date");
                countryData.add(Country_name);
                countryData.add(Population);
                countryData.add(TOTAL_DEATHS);
                countryData.add(Avg_Deaths);
                max_Deaths = max_Deaths + " - " + date;
                countryData.add(max_Deaths);
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

    public ArrayList<String> printCloserLookDeathsData(String i) {
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
            String query = "SELECT COUNTRY.COUNTRY_NAME, SUM(No_Deaths) ,ROUND(((total_deaths*1.0)/(population*1.0))*1000000, 2) AS per_Capita, ROUND((SUM(NO_DEATHS*1.0)/SUM(NO_CASES*1.0))*100,2) AS Mortality_Rate, MAX(NO_DEATHS) ,DATE FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.COUNTRY_NAME='"
                    + i + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String TOTAL_DEATHS = results.getString("SUM(No_Deaths)");
                String DeathsPerCapita = results.getString("per_Capita");
                // String Total_Cases = results.getString("AVG(NO_DEATHS)");
                String Mortality_Rate = results.getString("Mortality_Rate");
                String max_Deaths = results.getString("MAX(NO_DEATHS)");
                // String perCapita =
                // results.getString("ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2)");
                String date = results.getString("Date");

                countryData.add(TOTAL_DEATHS);
                countryData.add(DeathsPerCapita);
                countryData.add(Mortality_Rate);
                max_Deaths = max_Deaths + " - " + date;
                countryData.add(max_Deaths);
            }
            // Left botton Heading Value pair - refer to figma for what data is to be put
            // here
            /*
             * 1.Total Deaths 2.Total Deaths per capita 3.Mortality Rate 4.Deadliest Day
             */
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

    // FUNCTIONS FOR REPORTS PAGE//
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
            String query = "SELECT COUNTRY.FLAG_ID, COUNTRY.COUNTRY_NAME, SUM(NO_CASES), SUM(NO_DEATHS), ROUND(AVG(NO_CASES), 2) AS AVG, ROUND(SUM(NO_DEATHS) * 100.0 / SUM(NO_CASES), 2) AS Percent FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.ROWID = "
                    + i + " GROUP BY COUNTRY.COUNTRY_NAME;";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {

                String Name = results.getString("Country_name");
                String Flag = results.getString("FLAG_ID");
                String Total_Cases = results.getString("SUM(No_Cases)");
                String Total_Deaths = results.getString("SUM(No_Deaths)");
                String max_Cases = results.getString("AVG");
                // String perCapita =
                // results.getString("ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2)");
                String date = results.getString("Percent");
                countryData.add(Flag);
                countryData.add(Name);
                countryData.add(Total_Cases);
                countryData.add(Total_Deaths);
                countryData.add(max_Cases);
                // countryData.add(perCapita);
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
            String query = "SELECT COUNTRY.FLAG_ID, COUNTRY.COUNTRY_NAME, SUM(NO_CASES), SUM(NO_DEATHS), ROUND(AVG(NO_CASES), 2) AS AVG, ROUND(SUM(NO_DEATHS) * 100.0 / SUM(NO_CASES), 2) AS Percent FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.ROWID = "
                    + i + " GROUP BY COUNTRY.COUNTRY_NAME;";
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {

                String Name = results.getString("Country_name");
                String Flag = results.getString("FLAG_ID");
                String Total_Cases = results.getString("SUM(No_Cases)");
                String Total_Deaths = results.getString("SUM(No_Deaths)");
                String max_Cases = results.getString("AVG");
                String date = results.getString("Percent");
                countryData.add(Flag);

                countryData.add(Name);
                countryData.add(Total_Cases);
                countryData.add(Total_Deaths);
                countryData.add(max_Cases);
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

    public ArrayList<ArrayList<String>> modalCountryData(int countryId) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

        ArrayList<String> countryData = new ArrayList<String>();
        ArrayList<String> graphData = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT COUNTRYDATA.COUNTRY_NAME, FLAG_ID, LATITUDE, LONGITUDE, POPULATION, SUM(NO_CASES), SUM(NO_DEATHS), ROUND(((SUM(NO_DEATHS)*1.0)/(SUM(NO_CASES)*1.0))*100, 2) AS FatalityRate, ROUND(((SUM(no_deaths)*1.0)/(population*1.0))*1000000, 2) AS PerCapita FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.ROWID='"
                    + countryId + "'";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String name = results.getString("COUNTRY_NAME");
                String flag = results.getString("FLAG_ID");
                String latitude = results.getString("LATITUDE");
                String longitude = results.getString("LONGITUDE");
                String population = results.getString("POPULATION");
                String totalCases = results.getString("SUM(NO_CASES)");
                String totalDeaths = results.getString("SUM(NO_DEATHS)");
                String fatality = results.getString("FatalityRate");
                String perCapitaDeaths = results.getString("PerCapita");

                countryData.add(name);
                countryData.add(totalDeaths);
                countryData.add(totalCases);
                countryData.add(fatality);
                countryData.add(perCapitaDeaths);
                countryData.add(latitude);
                countryData.add(longitude);
                countryData.add(population);
                countryData.add(flag);
            }

            data.add(countryData);

            String queryGraph = "SELECT * FROM (SELECT COUNTRYDATA.ROWID, DATE, CUMULATIVE_CASES, CUMULATIVE_DEATHS FROM COUNTRYDATA WHERE COUNTRYDATA.COUNTRY_NAME = '"
                    + countryData.get(0) + "') WHERE ROWID % 35=0";

            // Get Result
            ResultSet resultsGraph = statement.executeQuery(queryGraph);

            // Process all of the results
            while (resultsGraph.next()) {
                String date = results.getString("DATE");
                String cases = results.getString("CUMULATIVE_CASES");
                String deaths = results.getString("CUMULATIVE_DEATHS");

                graphData.add(date);
                graphData.add(cases);
                graphData.add(deaths);
            }

            data.add(graphData);

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

        return data;
    }

    public JSONObject searchResults(String searchText) {
        JSONObject data = new JSONObject();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT COUNTRY_NAME, FLAG_ID FROM COUNTRY WHERE COUNTRY_NAME LIKE '%" + searchText + "%'";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String flag = results.getString("FLAG_ID");
                String name = results.getString("COUNTRY_NAME");

                data.put(flag, name);

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

        return data;
    }

    public JSONObject countryCompare(int rowId, String dates) {
        JSONObject data = new JSONObject();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "";
            if (dates == null || dates == "") {
                // The Query
                query = "SELECT SUM(NO_CASES), SUM(NO_DEATHS) ,ROUND(((total_deaths*1.0)/(population*1.0))*1000000, 2) AS per_Capita, ROUND((SUM(NO_DEATHS*1.0)/SUM(NO_CASES*1.0))*100,2) AS Mortality_Rate, MAX(NO_DEATHS), DATE FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.COUNTRY_NAME = COUNTRYDATA.COUNTRY_NAME WHERE COUNTRY.ROWID = "
                        + rowId + ";";
            } else {
                String[] date = dates.split("&");
                query = "SELECT SUM(NO_CASES), SUM(NO_DEATHS) ,ROUND(((total_deaths*1.0)/(population*1.0))*1000000, 2) AS per_Capita, ROUND((SUM(NO_DEATHS*1.0)/SUM(NO_CASES*1.0))*100,2) AS Mortality_Rate, MAX(NO_DEATHS), DATE FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.COUNTRY_NAME = COUNTRYDATA.COUNTRY_NAME WHERE COUNTRY.ROWID = "
                        + rowId + " AND DATE BETWEEN '" + date[0] + "' AND '" + date[1] + "';";
            }

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String cases = results.getString("SUM(NO_CASES)");
                String death = results.getString("SUM(NO_DEATHS)");
                String capita = results.getString("per_Capita");
                String mortality = results.getString("Mortality_Rate");
                String maxDead = results.getString("MAX(NO_DEATHS)");
                String date = results.getString("DATE");

                data.put("cases", cases);
                data.put("death", death);
                data.put("capita", capita);
                data.put("mortality", mortality);
                data.put("maxDead", maxDead);
                data.put("date", date);
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

        return data;
    }

    public ArrayList<String> Loopu(String j) {
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
            String query = "SELECT REGION_NAME, LATITUDE, LONGITUDE, TOTAL_CASES, TOTAL_DEATHS FROM REGION WHERE COUNTRY_NAME = '"+ j + "'";
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {

                String REGION_NAME = results.getString("REGION_NAME");
                String LATITUDE = results.getString("LATITUDE");
                String LONGITUDE = results.getString("LONGITUDE");
                String TOTAL_CASES = results.getString("TOTAL_CASES");
                String TOTAL_DEATHS = results.getString("TOTAL_DEATHS");

                countryData.add(REGION_NAME);
                countryData.add(LATITUDE);
                countryData.add(LONGITUDE);
                countryData.add(TOTAL_CASES);
                countryData.add(TOTAL_DEATHS);
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

    public ArrayList<String> printCloserLookDeathsDataWithRange(String country, String entry_date, String exit_date) {
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
            String query = "SELECT COUNTRY.COUNTRY_NAME, SUM(No_Deaths) ,ROUND(((SUM(No_Deaths)*1.0)/(population*1.0))*1000000, 2) AS per_Capita, ROUND((SUM(NO_DEATHS*1.0)/SUM(NO_CASES*1.0))*100,2) AS Mortality_Rate, MAX(NO_DEATHS) ,DATE FROM COUNTRY JOIN COUNTRYDATA ON COUNTRY.Country_Name = COUNTRYDATA.Country_Name WHERE COUNTRY.COUNTRY_NAME='"
                    + country + "' AND DATE BETWEEN '" + entry_date + "' AND '" + exit_date + "';";
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String TOTAL_DEATHS = results.getString("SUM(No_Deaths)");
                String DeathsPerCapita = results.getString("per_Capita");
                // String Total_Cases = results.getString("AVG(NO_DEATHS)");
                String Mortality_Rate = results.getString("Mortality_Rate");
                String max_Deaths = results.getString("MAX(NO_DEATHS)");
                // String perCapita =
                // results.getString("ROUND(((total_cases*1.0)/(population*1.0))*1000000, 2)");
                String date = results.getString("Date");

                countryData.add(TOTAL_DEATHS);
                countryData.add(DeathsPerCapita);
                countryData.add(Mortality_Rate);
                max_Deaths = max_Deaths + " - " + date;
                countryData.add(max_Deaths);
            }
            // Left botton Heading Value pair - refer to figma for what data is to be put
            // here
            /*
             * 1.Total Deaths 2.Total Deaths per capita 3.Mortality Rate 4.Deadliest Day
             */
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

// ;
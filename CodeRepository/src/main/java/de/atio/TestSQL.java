package de.atio;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.code.RegisterRange;
import com.serotonin.modbus4j.ip.IpParameters;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class TestSQL {


    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static void main(String[] args) {

        readSQLTable();
        //writeToSQLTable("Hello", "World");
        //createSQLTable();
        //deleteFromSQLTable("test", "Hello", "World");
        //deleteSQLTable("test");
    }

    public static void readSQLTable(){
        try {
            // JDBC URL, username, and password of MySQL server
            final String URL = "jdbc:sqlserver://localhost:1433;databaseName=atio;encrypt=true;trustServerCertificate=true";
            final String USER = "sa";
            final String PASSWORD = "1234Abcd";
            // Establish a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepare a statement to execute SQL query
            String sqlQuery = "SELECT * FROM test";
            preparedStatement = connection.prepareStatement(sqlQuery);

            // Execute the query and get the result set
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                String exampleName= resultSet.getString("exampleName");
                String exampleValue = resultSet.getString("exampleValue");

                // Do something with the data (e.g., print it)
                System.out.println("exampleName: " + exampleName + ", exampleValue: " + exampleValue);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources in a finally block to ensure they are always closed
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeToSQLTable(String exampleName, String exampleValue) {

        final String URL = "jdbc:sqlserver://localhost:1433;databaseName=atio;encrypt=true;trustServerCertificate=true";
        final String USER = "sa";
        final String PASSWORD = "1234Abcd";

        try {
            // Establish a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepare a statement to execute SQL query with placeholders
            String sqlStatement = "INSERT INTO test (exampleName, exampleValue) VALUES (?, ?)";

            // Create a PreparedStatement object to execute the SQL statement
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            // Set values for the placeholders
            preparedStatement.setString(1, exampleName);
            preparedStatement.setString(2, exampleValue);

            // Execute the SQL statement and get the number of rows affected
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println(rowsAffected + " row(s) affected");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };


    public static void createSQLTable(){
        // JDBC URL, username, and password of MSSQL server
        final String URL = "jdbc:sqlserver://localhost:1433;databaseName=atio;encrypt=true;trustServerCertificate=true";
        final String USER = "sa";
        final String PASSWORD = "1234Abcd";

        Statement statement = null;

        try {
            // Establish a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Create a Statement object
            statement = connection.createStatement();

            // Define the SQL statement to create a table
            String sqlStatement = "CREATE TABLE employees ("
                    + "id INT IDENTITY," // this defines that id will increase by one for every new entry.
                    + "name NVARCHAR(255),"
                    + "age INT,"
                    + "jobTitle NVARCHAR(255))";

            // Execute the SQL statement to create the table
            statement.executeUpdate(sqlStatement);

            System.out.println("Table created successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the resources in the reverse order of their creation
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    public static void deleteFromSQLTable(String tableName, String conditionColumn, String conditionValue){

        // JDBC URL, username, and password of MSSQL server
        final String URL = "jdbc:sqlserver://localhost:1433;databaseName=atio;encrypt=true;trustServerCertificate=true";
        final String USER = "sa";
        final String PASSWORD = "1234Abcd";

        Statement statement = null;

        try {
            // Establish a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Create a Statement object
            statement = connection.createStatement();

            // Define the SQL statement with a placeholder for the WHERE clause
            String sqlStatement = "DELETE FROM " + tableName + " WHERE " + conditionColumn + " = ?";

            // Set the parameter for the WHERE clause
            preparedStatement.setString(1, conditionValue);

            // Execute the SQL statement to delete rows from the table
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println(rowsAffected + " row(s) deleted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the resources in the reverse order of their creation
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void deleteSQLTable(String tableName){
        // JDBC URL, username, and password of MSSQL server
        final String URL = "jdbc:sqlserver://localhost:1433;databaseName=atio;encrypt=true;trustServerCertificate=true";
        final String USER = "sa";
        final String PASSWORD = "1234Abcd";

        Statement statement = null;

        try {
            // Establish a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Create a Statement object
            statement = connection.createStatement();

            // Define the SQL statement to create a table
            String sqlStatement = "DROP TABLE " + tableName;;

            // Execute the SQL statement to create the table
            statement.executeUpdate(sqlStatement);

            System.out.println("Table successfully deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the resources in the reverse order of their creation
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}

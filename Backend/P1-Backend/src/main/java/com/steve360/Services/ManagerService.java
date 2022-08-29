package com.steve360.Services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ManagerService {

    private static Connection connection;

    private ManagerService(){

    }

    public static Connection getConnection(){

        if(connection == null){
            connect();
        }
        return connection;
    }

    private static void connect(){
        System.out.println("Connecting ManagerService");

        try{
            Properties props = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream inp = loader.getResourceAsStream("jdbc.properties");
            props.load(inp);

            String host = props.getProperty("host");
            String port = props.getProperty("port");
            String dbName = props.getProperty("dbName");
            String userName = props.getProperty("userName");
            String password = props.getProperty("password");
            String schema = props.getProperty("schema");
            String driver = props.getProperty("driver");

            StringBuilder builder = new StringBuilder("jdbc:postgresql://");
            builder.append(host);
            builder.append(":");
            builder.append(port);
            builder.append("/");
            builder.append(dbName);
            builder.append("?user=");
            builder.append(userName);
            builder.append("&password=");
            builder.append(password);
            builder.append("&currentSchema=");
            builder.append(schema);


            Class.forName(driver);
            connection = DriverManager.getConnection(builder.toString());

            System.out.println("Connected");
        }

        catch (IOException | SQLException | ClassNotFoundException e) {

            System.out.println("Exception");
           e.printStackTrace();

        }


    }
}

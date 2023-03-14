package com.teamwork.courseselection.Utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnecter {
    //私有变量
    private static String driver;
    private static String JDBC_URL;
    private static String JDBC_USER;
    private static String JDBC_PASSWORD;

    static{
        try{
            Properties properties = new Properties();
            InputStream input =null;
            try{
                input = Files.newInputStream(Path.of("db.properties"));
            }catch(NoSuchFileException e){
                properties.setProperty("driver","org.mariadb.jdbc.Driver");
                properties.setProperty("JDBC_URL","jdbc:mariadb://localhost:3306/electionDB");
                properties.setProperty("JDBC_USER","root");
                properties.setProperty("JDBC_PASSWORD","27494200");
                properties.store(new FileOutputStream("db.properties"),null);
                input = Files.newInputStream(Path.of("db.properties"));
            }
            properties.load(input);
            driver = properties.getProperty("driver");
            JDBC_URL = properties.getProperty("JDBC_URL");
            JDBC_USER = properties.getProperty("JDBC_USER");
            JDBC_PASSWORD = properties.getProperty("JDBC_PASSWORD");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        try{
            Class.forName(driver);
            return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

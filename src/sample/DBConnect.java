package sample;

import java.sql.*;
public class DBConnect {

    public Connection getConnection() throws ClassNotFoundException{

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
            return conn;
        }
        catch (Exception e)
        {
            System.out.println("Connection failed because of :"+e);

        }


        return null;

    }



}

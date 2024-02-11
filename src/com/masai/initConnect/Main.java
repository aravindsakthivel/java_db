package com.masai.initConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Hi");
            e.printStackTrace();
        }

        String DbUrl = "jdbc:mysql://localhost:3306/learn_jdbc";
        try{
            Connection conn = DriverManager.getConnection(DbUrl, "root", "root");

            if(conn != null){
                System.out.println("Connected");
            }else {
                System.out.println("Not connected");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

}
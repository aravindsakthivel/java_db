package com.masai._29March;

import java.sql.*;
import java.util.Scanner;

public class jdbc_query {
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
                System.out.println(" ");
                getEmpDtl(1, conn);
                getAllEmpDtl(conn);
                giveBonus(500, conn);
                Scanner SC = new Scanner(System.in);
                System.out.println("Insert id ");
                Integer id = SC.nextInt();
                System.out.println("Insert salary ");
                Double salary = SC.nextDouble();
                System.out.println("Insert name ");
                SC.nextLine();
                String name = SC.nextLine();
                insertWithoutAddress(name, id, salary, conn);
                lessSalary(4550, conn);
            }else {
                System.out.println("Not connected");
            }
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static  void getEmpDtl(Integer empId, Connection conn){
        try{
            System.out.println("Get specific emp dtl ======> ");
            String query = "SELECT * FROM employee WHERE eid=?";
            PreparedStatement prpStmt = conn.prepareStatement(query);
            prpStmt.setInt(1, empId);
            ResultSet res = prpStmt.executeQuery();
            while (res.next()){
                String name = res.getString("name");
                String address = res.getString("address");
                Integer salary = res.getInt("salary");
                System.out.println(name+" "+address+" "+salary);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void getAllEmpDtl(Connection conn){
        try{
            System.out.println(" ");
            System.out.println("Get all emp dtl ======> ");
            String query = "SELECT * FROM employee";
            PreparedStatement prpStmt = conn.prepareStatement(query);
            ResultSet res = prpStmt.executeQuery();
            while (res.next()){
                String name = res.getString("name");
                String address = res.getString("address");
                Integer salary = res.getInt("salary");
                System.out.println(name+" "+address+" "+salary);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void giveBonus(Integer money, Connection conn){
        try{
            System.out.println(" ");
            System.out.println("Give bonus ======> ");
            String query = "UPDATE employee SET salary = salary + ?";
            PreparedStatement prpStmt = conn.prepareStatement(query);
            prpStmt.setInt(1, money);
            Integer res = prpStmt.executeUpdate();
            System.out.println("Salary updated for "+res);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void insertWithoutAddress(String name, Integer id, Double salary, Connection conn){
        try{
            System.out.println(" ");
            System.out.println("Insert without address ======> ");
            String query = "INSERT INTO employee(eid,name,salary) VALUES(?, ?, ?)";
            PreparedStatement prpStmt = conn.prepareStatement(query);
            prpStmt.setInt(1, id);
            prpStmt.setDouble(3, salary);
            prpStmt.setString(2, name);
            Integer res = prpStmt.executeUpdate();
            System.out.println("new employees "+res);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void lessSalary(Integer slry, Connection conn){
        try{
            System.out.println(" ");
            System.out.println("Get specific emp dtl ======> ");
            String query = "SELECT * FROM employee WHERE salary < ?";
            PreparedStatement prpStmt = conn.prepareStatement(query);
            prpStmt.setInt(1,slry);
            ResultSet res = prpStmt.executeQuery();
            while (res.next()){
                String name = res.getString("name");
                String address = res.getString("address");
                Integer salary = res.getInt("salary");
                System.out.println(name+" "+address+" "+salary);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A00_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class JDBCConnection {

   public static Connection getJDBCConnection(){
       final String url = "jdbc:mysql://localhost:3306/qlbh";
       final String user = "root";
       final String password = "785236";
       
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           return DriverManager.getConnection(url, user, password);
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch(SQLException e){
           e.printStackTrace();
       }
       return null;
   }
    public static void main(String[] args) {
        Connection connection = JDBCConnection.getJDBCConnection();
        if (connection != null) {
            System.out.println("Thanh cong");
        } else {
            System.out.println("That bai");
        }
    }
}

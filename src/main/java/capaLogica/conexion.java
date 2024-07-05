/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Santiago Vides
 */
public class conexion {
    
    static Connection conn = null;
    
    //coneccion con la DB
    public static Connection conectar(){
        String url = "jdbc:mysql://localhost:3306/ecommerce?zeroDateTimeBehavior=CONVERT_TO_NULL";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");//f
        }catch(ClassNotFoundException e){
            System.out.println("Error SQL " + e.getMessage());
        }
        
        try{
            conn=DriverManager.getConnection(url, "root", "SvsSql-0515"); 
        }catch(SQLException e){
            System.out.println("error de coneccion " + e.getMessage());
        }
        
        return conn;
    }
   
}

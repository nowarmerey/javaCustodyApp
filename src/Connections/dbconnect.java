/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;


import java.sql.Connection;
import java.sql.DriverManager;




/**
 *
 * @author NOWAR
 */
public class dbconnect {

    public Connection connect = null;

    public Connection getConnection() {
        try {

        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        connect = DriverManager.getConnection("jdbc:ucanaccess://CUSTODY.accdb");
        // connect = DriverManager.getConnection("jdbc:derby://localhost:1527/mobile", "nowar", "123");
           
            // Class.forName("org.sqlite.JDBC");

           
            //connect = DriverManager.getConnection("jdbc:sqlite:NGI.sqlite");
        } catch (Exception e) {
            System.out.println("Connection Error");
            e.printStackTrace();
        } finally {
            if (connect != null) {
                System.out.println("Connection seuccess");
            } else {
                System.out.println("Connection failed");
            }

        }

        return connect;
    }

    
}

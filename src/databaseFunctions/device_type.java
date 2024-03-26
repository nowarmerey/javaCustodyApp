/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseFunctions;

import Connections.dbconnect;
import entities.en_deviceType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Smart
 */
public class device_type {

    public void add_device_type(en_deviceType dt) throws SQLException {
        String query = "insert into Device_Type (ty_name) values(?)";

        dbconnect con = new dbconnect();

        Connection Connect = con.getConnection();
        PreparedStatement st = Connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, dt.getTy_name());

        try {
            st.executeUpdate();
            // statement = Connect.createStatement();
            //  statement.execute(query);

        } catch (Exception e) {
            System.out.println(e);

        } finally {
            Connect.close();
            //  statement.close();

        }

    }
    
     public void update_device_type(en_deviceType dt) throws SQLException {
        String query = "update Device_Type set ty_name='" + dt.getTy_name() +"' where ty_num="+dt.getTy_num()+" ";
               

        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        try {
            statement = Connect.createStatement();
            statement.execute(query);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Connect.close();
            statement.close();

        }
    }
     
     public void delete_device_type(int ty_id) throws SQLException {
        String query = "delete from Device_Type where em_num=" + ty_id + " ";

        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        try {
            statement = Connect.createStatement();
            statement.execute(query);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Connect.close();
            statement.close();
        }
 
     }
     
      public ArrayList<en_deviceType> show_devices_type() throws SQLException {
        String query = "select ty_num,ty_name from Device_Type  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_deviceType> dt = new ArrayList<en_deviceType>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_deviceType a = new en_deviceType();

                a.setTy_num(rs.getInt("ty_num"));
                a.setTy_name(rs.getString("ty_name"));
                

                dt.add(a);

            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                Connect.close();
                statement.close();
            } catch (SQLException ex) {
                //    Logger.getLogger(tables.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return dt;
    }
      
       public String get_type_name(int num) throws SQLException {
        String query = "select ty_name from Device_Type where ty_num = " + num + " ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        String name = null;
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                name = rs.getString("ty_name");

            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                Connect.close();
                statement.close();
            } catch (SQLException ex) {
                //    Logger.getLogger(tables.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return name;
    }
       
        public en_deviceType get_type(int num) throws SQLException {
        String query = "select ty_num,ty_name from Device_Type where ty_num = " + num + " ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        en_deviceType a = new en_deviceType();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

               a.setTy_num(rs.getInt("ty_num"));
               a.setTy_name(rs.getString("ty_name"));

            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                Connect.close();
                statement.close();
            } catch (SQLException ex) {
                //    Logger.getLogger(tables.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return a;
    }
}

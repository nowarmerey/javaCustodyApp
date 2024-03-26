/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseFunctions;

import Connections.dbconnect;
import entities.en_devices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Smart
 */
public class devices {

    public void add_device(en_devices de) throws SQLException {
        String query = "insert into Devices (ty_num,de_num) values(?,?)";

        dbconnect con = new dbconnect();

        Connection Connect = con.getConnection();
        PreparedStatement st = Connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1, de.getTy_num());
        st.setInt(2, de.getDe_num());

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

    public void update_devices(en_devices de) throws SQLException {
        String query = "update Devices set ty_num=" + de.getTy_num() + ", de_num=" + de.getDe_num() + " where d_num=" + de.getD_num() + "  ";

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

    public void delete_device(int d_id) throws SQLException {
        String query = "delete from Devices where d_num=" + d_id + " ";

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

    public en_devices get_device_numbers(int id) throws SQLException {
        String query = "select d_num,ty_num,de_num from Devices where d_num=" + id + "  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        en_devices a = new en_devices();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                a.setD_num(id);
                a.setTy_num(rs.getInt("ty_num"));
                a.setDe_num(rs.getInt("de_num"));

            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                System.out.println("from devices query..." + a.getDe_num());
                Connect.close();
                statement.close();
            } catch (SQLException ex) {
                //    Logger.getLogger(tables.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return a;
    }

    public int get_last_device_number() {
        String query = "select MAX(d_num) as max from Devices   ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        int a = 0;
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                a = rs.getInt("max");
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                Connect.close();
                statement.close();

            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }

        return a;
    }

    public int get_device_number(int dID,int type) {
        String query = "select d_num from Devices where de_num = "+dID+" and  ty_num = "+type+"  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        int a = 0;
        try {
            System.out.println("device number query is : " + query);
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                a = rs.getInt("d_num");
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                Connect.close();
                statement.close();

            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }

        return a;
    }

}

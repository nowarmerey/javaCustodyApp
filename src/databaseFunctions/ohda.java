/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseFunctions;

import Connections.dbconnect;
import entities.en_ohda;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 *
 * @author Smart
 */
public class ohda {

    public void add_ohda(en_ohda oh) throws SQLException {
        String query = "insert into Ohda (em_num,d_num,notes,oh_date) values(?,?,?,?)";

        dbconnect con = new dbconnect();

        Connection Connect = con.getConnection();
        PreparedStatement st = Connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1, oh.getEm_num());
        st.setInt(2, oh.getD_num());
        st.setString(3, oh.getNotes());
        java.util.Date date = java.sql.Date.valueOf(oh.getOh_date());
        st.setDate(4, (Date) date);

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

    public void update_ohda(int id, String notes) throws SQLException {
        String query = "update Ohda set  notes='" + notes + "'  where oh_num = " + id + "      ";

        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        try {
            statement = Connect.createStatement();
            statement.execute(query);
            System.out.println("ohda updated");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Connect.close();
            statement.close();

        }
    }
    
 
    public void update_employee(int newNum, int oldNum,int device_num) throws SQLException {
        String query = "update Ohda set  em_num = " + newNum + " where em_num = " + oldNum + "  and d_num = "+device_num+"    ";

        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        try {
            System.out.println("query from database is " + query);
            statement = Connect.createStatement();
            statement.execute(query);
            System.out.println("ohda updated");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Connect.close();
            statement.close();

        }
    }

    public void delete_ohda(int oh_id) throws SQLException {
        String query = "delete from Ohda where oh_num=" + oh_id + " ";

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

    public ArrayList<en_ohda> show_ohdas() throws SQLException {
        String query = "select oh_num,em_num,d_num,notes,oh_date from Ohda  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_ohda> ohd = new ArrayList<en_ohda>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_ohda a = new en_ohda();

                a.setOh_num(rs.getInt("oh_num"));
                a.setEm_num(rs.getInt("em_num"));
                a.setD_num(rs.getInt("d_num"));
                a.setNotes(rs.getString("notes"));
                LocalDate localDate = rs.getDate("oh_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                a.setOh_date(localDate);

                ohd.add(a);

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

        return ohd;
    }

    public ArrayList<en_ohda> get_employee_ohda(int em_id) throws SQLException {
        String query = "select oh_num,em_num,d_num,notes,oh_date from Ohda where em_num=" + em_id + "  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_ohda> ohd = new ArrayList<en_ohda>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_ohda a = new en_ohda();

                a.setOh_num(rs.getInt("oh_num"));
                System.out.println("ohda from get_employee_ohda query... " + rs.getInt("oh_num"));
                a.setEm_num(rs.getInt("em_num"));
                System.out.println("ohda from get_employee_ohda query... " + rs.getInt("em_num"));
                a.setD_num(rs.getInt("d_num"));
                System.out.println("ohda from get_employee_ohda query... " + rs.getInt("d_num"));
                a.setNotes(rs.getString("notes"));
                System.out.println("ohda from get_employee_ohda query... " + rs.getString("notes"));
                LocalDate localDate = rs.getDate("oh_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                a.setOh_date(localDate);
                System.out.println("ohda from get_employee_ohda query... " + rs.getDate("oh_date"));

                ohd.add(a);

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

        return ohd;
    }

    public int get_device_id(int ohda_id) throws SQLException {
        String query = "select d_num  from Ohda where oh_num=" + ohda_id + "  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        int device_id = 0;
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                device_id = rs.getInt("d_num");

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

        return device_id;
    }

}

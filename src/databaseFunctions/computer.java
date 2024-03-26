/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseFunctions;

import Connections.dbconnect;
import entities.en_computer;

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
public class computer {

    public void add_computer(en_computer co) throws SQLException {
        String query = "insert into Computer (motherboard,ram,hdd,cpu,monitor,keyboard,mouse,sup) values(?,?,?,?,?,?,?,?)";

        dbconnect con = new dbconnect();

        Connection Connect = con.getConnection();
        PreparedStatement st = Connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, co.getMotherboard());
        st.setString(2, co.getRam());
        st.setString(3, co.getHdd());
        st.setString(4, co.getCpu());
        st.setString(5, co.getMonitor());
        st.setString(6, co.getKeyboard());
        st.setString(7, co.getMouse());
        st.setString(8, co.getSubwoofer());
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

    public void update_computer(en_computer co) throws SQLException {
        String query = "update Computer set motherboard='" + co.getMotherboard() + "', ram='" + co.getRam() + "', hdd='" + co.getHdd() + "', cpu='" + co.getCpu() + "', monitor='" + co.getMonitor() + "', keyboard='" + co.getKeyboard() + "', mouse='" + co.getMouse() + "', sup='" + co.getSubwoofer() + "' where co_num="+co.getCo_num()+" ";

        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        try {
            statement = Connect.createStatement();
            statement.execute(query);
            System.out.println("computer updated");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Connect.close();
            statement.close();

        }
    }

    public void delete_computer(int co_id) throws SQLException {
        String query = "delete from Computer where co_num=" + co_id + " ";

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

    public ArrayList<en_computer> show_computers() throws SQLException {
        String query = "select co_num,motherboard,ram,hdd,cpu,monitor,keyboard,mouse,sup from Computer  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_computer> com = new ArrayList<en_computer>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_computer a = new en_computer();

                a.setCo_num(rs.getInt("con_num"));
                a.setMotherboard(rs.getString("motherboard"));
                a.setRam(rs.getString("ram"));
                a.setHdd(rs.getString("hdd"));
                a.setCpu(rs.getString("cpu"));
                a.setMonitor(rs.getString("monitor"));
                a.setKeyboard(rs.getString("keyboard"));
                a.setMouse(rs.getString("mouse"));
                a.setSubwoofer(rs.getString("sup"));

                com.add(a);

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

        return com;
    }

    public en_computer show_computer(int num)  {
        String query = "select co_num,motherboard,ram,hdd,cpu,monitor,keyboard,mouse,sup from Computer where co_num=" + num + "  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        en_computer a = new en_computer();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                                System.out.println("computer query motherboard..." + rs.getString("motherboard") );

                a.setCo_num(rs.getInt("co_num"));
                a.setMotherboard(rs.getString("motherboard"));
                a.setRam(rs.getString("ram"));
                a.setHdd(rs.getString("hdd"));
                a.setCpu(rs.getString("cpu"));
                a.setMonitor(rs.getString("monitor"));
                a.setKeyboard(rs.getString("keyboard"));
                a.setMouse(rs.getString("mouse"));
                a.setSubwoofer(rs.getString("sup"));

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
    
     public int get_last_computer_number()  {
        String query = "select MAX(co_num) as max from Computer   ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        int a =0;
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                a= rs.getInt("max");
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
        public ArrayList<String> get_data(String name) throws SQLException {
        String query = "select DISTINCT "+name+" from Computer   ";
            System.out.println(query);
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<String> emp = new ArrayList<String>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                
                emp.add(rs.getString(name));
          
                
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
        
        return emp;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseFunctions;

import Connections.dbconnect;

import entities.en_printer;
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
public class printer {

    public void add_printer(en_printer pr) throws SQLException {
        String query = "insert into Printer (pr_type,pr_name,pr_health) values(?,?,?)";

        dbconnect con = new dbconnect();

        Connection Connect = con.getConnection();
        PreparedStatement st = Connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, pr.getPr_type());
        st.setString(2, pr.getPr_name());
        st.setString(3, pr.getPr_health());

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

    public void update_printer(en_printer pr) throws SQLException {
        String query = "update Printer set pr_type='" + pr.getPr_type() + "', pr_name='" + pr.getPr_name() + "', pr_health='" + pr.getPr_health() + "' where pr_num="+pr.getPr_num()+"  ";

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

    public void delete_printer(int pr_id) throws SQLException {
        String query = "delete from Printer where pr_num=" + pr_id + " ";

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

    public ArrayList<en_printer> show_printers() throws SQLException {
        String query = "select pr_num,pr_type,pr_name,pr_health from Printer  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_printer> pri = new ArrayList<en_printer>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_printer a = new en_printer();

                a.setPr_num(rs.getInt("pr_num"));
                a.setPr_type(rs.getString("pr_type"));
                a.setPr_name(rs.getString("pr_name"));
                a.setPr_health(rs.getString("pr_health"));

                pri.add(a);

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

        return pri;
    }

    public en_printer show_printer(int id) throws SQLException {
        String query = "select pr_num,pr_type,pr_name,pr_health from Printer where pr_num='" + id + "'  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        en_printer a = new en_printer();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                a.setPr_num(rs.getInt("pr_num"));
                a.setPr_type(rs.getString("pr_type"));
                a.setPr_name(rs.getString("pr_name"));
                a.setPr_health(rs.getString("pr_health"));

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
    public int get_last_printer_number()  {
        String query = "select MAX(pr_num) as max from Printer   ";
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
        String query = "select DISTINCT "+name+" from Printer   ";
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

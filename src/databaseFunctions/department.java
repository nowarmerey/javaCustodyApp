/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseFunctions;

import Connections.dbconnect;
import entities.en_department;
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
public class department {

    public void add_Department(en_department dep) throws SQLException {
        String query = "insert into Department (d_name) values(?)";

        dbconnect con = new dbconnect();

        Connection Connect = con.getConnection();
        PreparedStatement st = Connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, dep.getD_name());
        

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

    public void update_Department(en_department dep) throws SQLException {
        String query = "update Department set d_name='" + dep.getD_name() + "' where d_num="+dep.getD_num()+" ";

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

    public void delete_Department(int dep_id) throws SQLException {
        String query = "delete from Department where d_num=" + dep_id + " ";

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

    public ArrayList<en_department> show_Departments() {
        String query = "select d_num,d_name from Department  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_department> deps = new ArrayList<en_department>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_department a = new en_department();

                a.setD_num(rs.getInt("d_num"));
                a.setD_name(rs.getString("d_name"));

                deps.add(a);

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

        return deps;
    }

    public String get_department_name(int id) throws SQLException {
        String query = "select d_name from Department where d_num = " + id + " ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        String name = null;
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                name = rs.getString("d_name");

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
    
        public int get_department_id(String name) throws SQLException {
        String query = "select d_num from Department where d_name = '" + name + "' ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        int id = 0;
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                id = rs.getInt("d_num");

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
        return id;
    }
}

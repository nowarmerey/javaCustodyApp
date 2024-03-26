/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseFunctions;

import Connections.dbconnect;

import entities.en_subDep;
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
public class sup_department {
     public void add_sup_Department(en_subDep dep) throws SQLException {
        String query = "insert into Sub_Dep (d_num,sp_name) values(?,?)";

        dbconnect con = new dbconnect();

        Connection Connect = con.getConnection();
        PreparedStatement st = Connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1, dep.getD_num());
        st.setString(2, dep.getSp_name());

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

    public void update_sup_Department(en_subDep dep) throws SQLException {
        String query = "update Sub_Dep set d_num=" + dep.getD_num() + " , sp_name='"+dep.getSp_name()+"' where sp_num="+dep.getSp_num()+"  ";

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

    public void delete_sup_Department(int dep_id) throws SQLException {
        String query = "delete from Sub_Dep where sp_num=" + dep_id + " ";

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

    public ArrayList<en_subDep> show_sup_Departments() {
        String query = "select sp_num,d_num,sp_name from Sub_Dep  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_subDep> deps = new ArrayList<en_subDep>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_subDep a = new en_subDep();

                a.setSp_num(rs.getInt("sp_num"));
                a.setD_num(rs.getInt("d_num"));
                a.setSp_name(rs.getString("sp_name"));

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

    public String get_sup_department_name(int id) throws SQLException {
        String query = "select sp_name from Sub_Dep where sp_num = " + id + " ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        String name = null;
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                name = rs.getString("sp_name");

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
     public ArrayList<String> get_sup_department_names(int id) throws SQLException {
        String query = "select sp_name from Sub_Dep where d_num= " + id + " ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<String> names = new ArrayList<String>();
        
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                names.add(rs.getString("sp_name")); 

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
        return names;
    }
     
      public int get_sup_department_id(String name) throws SQLException {
        String query = "select sp_num from Sub_Dep where sp_name = '" + name + "' ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        int id = 0;
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                id = rs.getInt("sp_num");

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseFunctions;

import Connections.dbconnect;
import entities.en_scanner;
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
public class scanner {
     public void add_scanner(en_scanner sc) throws SQLException {
        String query = "insert into Scanner (sc_type,sc_name,sc_health) values(?,?,?)";

        dbconnect con = new dbconnect();

        Connection Connect = con.getConnection();
        PreparedStatement st = Connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, sc.getSc_type());
        st.setString(2, sc.getSc_name());
        st.setString(3, sc.getSc_health());

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
     
      public void update_scanner(en_scanner sc) throws SQLException {
        String query = "update Scanner set sc_type='"+sc.getSc_type()+"', sc_name='"+sc.getSc_name()+"', sc_health='"+sc.getSc_health()+"' where sc_num="+sc.getSc_num()+"  ";

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
      
       public void delete_scanner(int sc_id) throws SQLException {
        String query = "delete from Scanner where sc_num=" + sc_id + " ";

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
       
        public ArrayList<en_scanner> show_scanners() throws SQLException {
        String query = "select sc_num,sc_type,sc_name,sc_health from Scanner  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_scanner> scr = new ArrayList<en_scanner>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_scanner a = new en_scanner();

                a.setSc_num(rs.getInt("sc_num"));
                a.setSc_type(rs.getString("sc_type"));
                a.setSc_name(rs.getString("sc_name"));
                a.setSc_health(rs.getString("sc_health"));

                scr.add(a);

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

        return scr;
    }
        
         public en_scanner show_scanner(int id) throws SQLException {
        String query = "select sc_num,sc_type,sc_name,sc_health from Scanner where sc_num="+id+"  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
       en_scanner a = new en_scanner();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                

                a.setSc_num(rs.getInt("sc_num"));
                a.setSc_type(rs.getString("sc_type"));
                a.setSc_name(rs.getString("sc_name"));
                a.setSc_health(rs.getString("sc_health"));

               
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
         
         public int get_last_scanner_number()  {
        String query = "select MAX(sc_num) as max from Scanner   ";
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
        String query = "select DISTINCT "+name+" from Scanner   ";
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

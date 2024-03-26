/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseFunctions;

import Connections.dbconnect;
import entities.en_employee;

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
public class employee {
    
    public void add_employee(en_employee em) throws SQLException {
        String query = "insert into Employee (em_name,em_id,d_num,sp_num,office,room) values(?,?,?,?,?,?)";
        
        dbconnect con = new dbconnect();
        
        Connection Connect = con.getConnection();
        PreparedStatement st = Connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
        st.setString(1, em.getEm_name());
        st.setInt(2, em.getEm_id());
        st.setInt(3, em.getD_num());
        st.setInt(4, em.getSp_num());
        st.setString(5, em.getOffice());
        st.setString(6, em.getRoom());
        
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
    
    public void update_employee(en_employee em) throws SQLException {
        String query = "update Employee set em_name='" + em.getEm_name() + "' , em_id=" + em.getEm_id() + " , d_num=" + em.getD_num() + ", "
                + " sp_num=" + em.getSp_num() + ", office='" + em.getOffice() + "', room='" + em.getRoom() + "' where em_num=" + em.getEm_num() + "  ";
        
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
            
            System.out.println("employee: " + em.getEm_num()+ "..." + em.getEm_id() + "..." + em.getEm_name() + " ... updated");
            
        }
    }
    
    public void delete_employee(int em_id) throws SQLException {
        String query = "delete from Employee where em_num=" + em_id + " ";
        
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
    
    public ArrayList<en_employee> show_employees() throws SQLException {
        String query = "select em_num,em_name,em_id,d_num,sp_num,office,room from Employee  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_employee> emp = new ArrayList<en_employee>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                
                en_employee a = new en_employee();
                
                a.setEm_num(rs.getInt("em_num"));
                a.setEm_name(rs.getString("em_name"));
                a.setEm_id(rs.getInt("em_id"));
                a.setD_num(rs.getInt("d_num"));
                a.setSp_num(rs.getInt("sp_num"));
                a.setOffice(rs.getString("office"));
                a.setRoom(rs.getString("room"));
                
                emp.add(a);
                
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

    public ArrayList<String> get_offices() throws SQLException {
        String query = "select DISTINCT office from employee   ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<String> emp = new ArrayList<String>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                
                emp.add(rs.getString("office"));
                
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
      public ArrayList<String> get_rooms() throws SQLException {
        String query = "select DISTINCT room from employee   ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<String> emp = new ArrayList<String>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                
                emp.add(rs.getString("room"));
                
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
    
    public int get_employee_id(int num) throws SQLException {
        String query = "select em_num from Employee where em_id = " + num + " ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        int id = 0;
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                
                id = rs.getInt("em_num");
                
            }
            System.out.println("employee id from get_employee_id query.... " + id);
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
    
    public en_employee get_employee(int id) throws SQLException {
        String query = "select em_num,em_name,em_id,d_num,sp_num,office,room from Employee where em_num=" + id + "  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        en_employee a = new en_employee();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                
                a.setEm_num(rs.getInt("em_num"));
                a.setEm_name(rs.getString("em_name"));
                a.setEm_id(rs.getInt("em_id"));
                a.setD_num(rs.getInt("d_num"));
                a.setSp_num(rs.getInt("sp_num"));
                a.setOffice(rs.getString("office"));
                a.setRoom(rs.getString("room"));
                
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
    
    public int get_employee_id_from_name(String name) throws SQLException {
        String query = "select em_id from Employee where em_name = '" + name + "' ";
        System.out.println("query is : " + query);
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        int id = 0;
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                
                id = rs.getInt("em_id");
                
            }
            System.out.println("employee id from name query.... " + id);
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
    
    public int get_employee_num_from_name(String name) throws SQLException {
        String query = "select em_num from Employee where em_name = '" + name + "' ";
        System.out.println("query is : " + query);
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        int id = 0;
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                
                id = rs.getInt("em_num");
                
            }
            System.out.println("employee id from name query.... " + id);
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

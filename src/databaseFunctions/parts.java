/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseFunctions;

import Connections.dbconnect;

import entities.en_parts;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

/**
 *
 * @author Smart
 */
public class parts {
    public void add_parts(en_parts pa) throws SQLException {
        String query = "insert into Parts (type,part_name,parts_count,recived_date) values(?,?,?,?)";

        dbconnect con = new dbconnect();

        Connection Connect = con.getConnection();
        PreparedStatement st = Connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        
        st.setString(1, pa.getType());
        st.setString(2, pa.getPart_name());
        st.setInt(3, pa.getParts_count());
       
        st.setDate(4, (Date) pa.getRecived_date());
       
        try {
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);

        } finally {
            Connect.close();
            //  statement.close();

        }
    }
    
    
    public void update_parts(en_parts pa) throws SQLException {
        String query = "update Parts set type='"+pa.getType()+"' , part_name='"+pa.getPart_name()+"', parts_count="+pa.getParts_count()+" where ID="+pa.getID()+" ";

        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        try {
            statement = Connect.createStatement();
            statement.execute(query);
            System.out.println("parts updated");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Connect.close();
            statement.close();

        }
    }
    
    public ArrayList<en_parts> show_parts() throws SQLException {
        String query = "select * from Parts  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_parts> par = new ArrayList<en_parts>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_parts a = new en_parts();

               a.setID(rs.getInt("ID"));
                System.out.println("..." + rs.getInt("ID"));
               a.setType(rs.getString("type"));
                 System.out.println("..." + rs.getString("type"));
               a.setPart_name(rs.getString("part_name"));
                 System.out.println("..." + rs.getString("part_name"));
               a.setParts_count(rs.getInt("parts_count"));
                 System.out.println("..." + rs.getInt("parts_count"));
        
               
               a.setRecived_date(rs.getDate("recived_date"));


                par.add(a);

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
        System.out.println("size of parts :" + par.size());

        return par;
    }
      public void delete_parts(int id) throws SQLException {
        String query = "delete Parts where ID="+id+"";

        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        try {
            statement = Connect.createStatement();
            statement.execute(query);
            System.out.println("parts updated");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Connect.close();
            statement.close();

        }
    }
}

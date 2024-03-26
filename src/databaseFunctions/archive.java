/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseFunctions;

import Connections.dbconnect;
import entities.en_archive;
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
public class archive {
    public void add_archive(en_archive ar) throws SQLException {
        String query = "insert into archive (part_id,parts_count,notes,sent_date) values(?,?,?,?)";

        dbconnect con = new dbconnect();

        Connection Connect = con.getConnection();
        PreparedStatement st = Connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        
        st.setInt(1, ar.getPart_id());
        st.setInt(2, ar.getParts_count());
        st.setString(3, ar.getNotes());
        java.util.Date date = java.sql.Date.valueOf(ar.getSent_date());
        st.setDate(4, (Date) date );
       
       
        try {
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);

        } finally {
            Connect.close();
            //  statement.close();

        }
    }
    
    public void update_archive(en_archive ar) throws SQLException {
        String query = "update archive set part_id="+ar.getPart_id()+", parts_count="+ ar.getParts_count() +" , notes='"+ar.getNotes()+"' where ID="+ar.getID()+" ";

        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        try {
            statement = Connect.createStatement();
            statement.execute(query);
            System.out.println("archive updated");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Connect.close();
            statement.close();

        }
    }
    
    public ArrayList<en_archive> show_parts() throws SQLException {
        String query = "select * from archive  ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_archive> par = new ArrayList<en_archive>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_archive a = new en_archive();

                a.setID(rs.getInt("ID"));
               a.setPart_id(rs.getInt("part_id"));
               a.setParts_count(rs.getInt("parts_count"));
               a.setNotes(rs.getString("notes"));
               LocalDate localDate = rs.getDate("recived_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
               a.setSent_date(localDate);

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

        return par;
    }
    
    public void delete_parts(int id) throws SQLException {
        String query = "delete archive where ID="+id+"";

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



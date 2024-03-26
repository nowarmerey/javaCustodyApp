/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseFunctions;

import Connections.dbconnect;
import entities.en_all_computers;
import entities.en_all_parts;
import entities.en_all_ps;

import entities.en_parts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;


/**
 *
 * @author Smart
 */
public class mixed {

    public en_all_computers get_full_employee_info(int employee_id) throws SQLException {
        employee em = new employee();

        int em_num = em.get_employee_id(employee_id);
        String query = "SELECT E.em_name, E.em_id, E.office, E.room,\n"
                + "DP.d_name,SD.sp_name\n"
                + "FROM Employee AS E,Department as DP,Sub_Dep as SD "
                + "where E.em_num=" + em_num + " and E.d_num=DP.d_num and E.sp_num=SD.sp_num";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        en_all_computers a = new en_all_computers();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                a.setEmployee_name(rs.getString("em_name"));
                a.setEmployee_id(rs.getInt("em_id"));
                a.setOffice(rs.getString("office"));
                a.setRoom(rs.getString("room"));
                a.setDepartment(rs.getString("d_name"));
                a.setSup_department(rs.getString("sp_name"));

            }
            System.out.println("end while");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                Connect.close();
                statement.close();
                System.out.println("finally");
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }
        System.out.println("end function");
        return a;

    }

    public ArrayList<en_all_computers> get_full_ohda_info(int employee_id) throws SQLException {
        employee em = new employee();

        int em_num = em.get_employee_id(employee_id);
        String query = "SELECT E.em_num,E.em_name, E.em_id, E.office, E.room,\n"
                + "DP.d_name,SD.sp_name,\n"
                + "C.co_num,C.motherboard,C.ram,C.hdd,C.cpu,C.monitor,C.keyboard,C.mouse,C.sup,"
                + "T.ty_name,"
                + "O.oh_num,O.oh_date,O.notes\n"
                + "FROM Employee AS E, Computer AS C, Devices AS D, Ohda AS O,Device_Type as T,Department as DP,Sub_Dep as SD "
                + "where E.em_num=" + em_num + "  AND D.ty_num=1  and T.ty_num=1  AND O.em_num=E.em_num AND O.d_num=D.d_num AND D.de_num=C.co_num and  E.d_num=DP.d_num and E.sp_num=SD.sp_num";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_all_computers> ar = new ArrayList<en_all_computers>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_all_computers a = new en_all_computers();
                 a.setEm_num(rs.getInt("em_num"));
                a.setEmployee_name(rs.getString("em_name"));
                System.out.println(rs.getString("em_name"));
                a.setEmployee_id(rs.getInt("em_id"));
                System.out.println(rs.getInt("em_id"));
                a.setOffice(rs.getString("office"));
                System.out.println(rs.getString("office"));
                a.setRoom(rs.getString("room"));
                System.out.println(rs.getString("room"));
                a.setDepartment(rs.getString("d_name"));
                System.out.println(rs.getString("d_name"));
                a.setSup_department(rs.getString("sp_name"));
                System.out.println(rs.getString("sp_name"));
                a.setMotherboard(rs.getString("motherboard"));
                System.out.println(rs.getString("motherboard"));
                a.setRam(rs.getString("ram"));
                a.setComputer_number(rs.getInt("co_num"));
                System.out.println(rs.getString("ram"));
                a.setHdd(rs.getString("hdd"));
                System.out.println(rs.getString("hdd"));
                a.setCpu(rs.getString("cpu"));
                System.out.println(rs.getString("cpu"));
                a.setMonitor(rs.getString("monitor"));
                System.out.println(rs.getString("monitor"));
                a.setKeyboard(rs.getString("keyboard"));
                System.out.println(rs.getString("keyboard"));
                a.setMouse(rs.getString("mouse"));
                System.out.println(rs.getString("mouse"));
                a.setSubwoofer(rs.getString("sup"));
                System.out.println(rs.getString("sup"));
                a.setType(rs.getString("ty_name"));
                System.out.println(rs.getString("ty_name"));
                a.setNotes(rs.getString("notes"));
                System.out.println(rs.getString("notes"));
                a.setDate(rs.getDate("oh_date"));
                a.setOhda_num(rs.getInt("oh_num"));
                System.out.println(rs.getDate("oh_date"));

                ar.add(a);
            }
            System.out.println("end while");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                Connect.close();
                statement.close();
                System.out.println("finally");
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }
        System.out.println("end function");
        return ar;

    }

    public ArrayList<en_all_computers> get_all_ohda() {
        String query = "SELECT E.em_num,E.em_name, E.em_id, E.office, E.room,\n"
                + "DP.d_name,SD.sp_name,\n"
                + "C.co_num,C.motherboard,C.ram,C.hdd,C.cpu,C.monitor,C.keyboard,C.mouse,C.sup,"
                + "T.ty_name,"
                + "O.oh_num,O.oh_date,O.notes\n"
                + "FROM Employee AS E, Computer AS C, Devices AS D, Ohda AS O,Device_Type as T,Department as DP,Sub_Dep as SD "
                + "where O.em_num=E.em_num AND O.d_num=D.d_num AND D.de_num=C.co_num AND D.ty_num=T.ty_num AND T.ty_num=1 and E.d_num=DP.d_num and E.sp_num=SD.sp_num";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_all_computers> ar = new ArrayList<en_all_computers>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_all_computers a = new en_all_computers();
                a.setEm_num(rs.getInt("em_num"));
                a.setEmployee_name(rs.getString("em_name"));

                a.setEmployee_id(rs.getInt("em_id"));

                a.setOffice(rs.getString("office"));

                a.setRoom(rs.getString("room"));

                a.setDepartment(rs.getString("d_name"));

                a.setSup_department(rs.getString("sp_name"));
                a.setComputer_number(rs.getInt("co_num"));

                a.setMotherboard(rs.getString("motherboard"));

                a.setRam(rs.getString("ram"));

                a.setHdd(rs.getString("hdd"));

                a.setCpu(rs.getString("cpu"));

                a.setMonitor(rs.getString("monitor"));

                a.setKeyboard(rs.getString("keyboard"));

                a.setMouse(rs.getString("mouse"));

                a.setSubwoofer(rs.getString("sup"));

                a.setType(rs.getString("ty_name"));

                a.setNotes(rs.getString("notes"));

                a.setOhda_num(rs.getInt("oh_num"));

                a.setDate(rs.getDate("oh_date"));

                ar.add(a);
            }
            System.out.println("end while");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                Connect.close();
                statement.close();
                System.out.println("finally");
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }
        System.out.println("end function");
        return ar;

    }

    public ArrayList<en_all_ps> get_all_ps() {
        String query = "SELECT E.em_name, E.em_id, E.office, E.room,\n"
                + "DP.d_name,SD.sp_name,\n"
                + "P.pr_num,P.pr_type,P.pr_name,P.pr_health,"
                + "T.ty_name,"
                + "O.oh_num,O.oh_date,O.notes\n"
                + "FROM Employee AS E, Printer as P, Devices AS D, Ohda AS O,Device_Type as T,Department as DP,Sub_Dep as SD "
                + "where O.em_num=E.em_num AND O.d_num=D.d_num AND D.de_num=P.pr_num  AND D.ty_num=T.ty_num and T.ty_num=2  and E.d_num=DP.d_num and E.sp_num=SD.sp_num";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_all_ps> ar = new ArrayList<en_all_ps>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_all_ps a = new en_all_ps();
                a.setEmployee_name(rs.getString("em_name"));

                a.setEmployee_id(rs.getInt("em_id"));

                a.setOffice(rs.getString("office"));

                a.setRoom(rs.getString("room"));
                a.setPs_num(rs.getInt("pr_num"));

                a.setDepartment(rs.getString("d_name"));
                a.setSup_department(rs.getString("sp_name"));
                a.setType(rs.getString("ty_name"));

                //a.setSup_department(rs.getString("sp_name"));
                a.setDevice_type(rs.getString("pr_type"));
                a.setDevice_name(rs.getString("pr_name"));
                a.setHealth(rs.getString("pr_health"));
                a.setNotes(rs.getString("notes"));
                a.setDate(rs.getDate("oh_date"));
                a.setOh_num(rs.getInt("oh_num"));

                ar.add(a);
            }
            System.out.println("end while");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                Connect.close();
                statement.close();
                System.out.println("finally");
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }
        System.out.println("end function");
        return ar;

    }

    public ArrayList<en_all_ps> get_all_s() {
        String query = "SELECT E.em_name, E.em_id, E.office, E.room,\n"
                + "DP.d_name,SD.sp_name,\n"
                + "S.sc_num,S.sc_type,S.sc_name,S.sc_health,"
                + "T.ty_name,"
                + "O.oh_num,O.oh_date,O.notes\n"
                + "FROM Employee AS E, Scanner as S, Devices AS D, Ohda AS O,Device_Type as T,Department as DP,Sub_Dep as SD "
                + "where O.em_num=E.em_num AND O.d_num=D.d_num AND D.de_num=S.sc_num  AND D.ty_num=T.ty_num and T.ty_num=3  and E.d_num=DP.d_num and E.sp_num=SD.sp_num";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_all_ps> ar = new ArrayList<en_all_ps>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_all_ps a = new en_all_ps();
                a.setEmployee_name(rs.getString("em_name"));

                a.setEmployee_id(rs.getInt("em_id"));

                a.setOffice(rs.getString("office"));

                a.setRoom(rs.getString("room"));

                a.setPs_num(rs.getInt("sc_num"));
                a.setDepartment(rs.getString("d_name"));
                a.setSup_department(rs.getString("sp_name"));
                a.setType(rs.getString("ty_name"));

                a.setSup_department(rs.getString("sp_name"));

                a.setDevice_type(rs.getString("sc_type"));
                a.setDevice_name(rs.getString("sc_name"));
                a.setHealth(rs.getString("sc_health"));
                a.setNotes(rs.getString("notes"));
                a.setDate(rs.getDate("oh_date"));
                a.setOh_num(rs.getInt("oh_num"));

                ar.add(a);
            }
            System.out.println("end while");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                Connect.close();
                statement.close();
                System.out.println("finally");
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }
        System.out.println("end function");
        return ar;

    }

    public ArrayList<en_all_ps> get_em_ps(int id) throws SQLException {
        employee em = new employee();

        int em_num = em.get_employee_id(id);
        String query = "SELECT E.em_name, E.em_id, E.office, E.room,\n"
                + "DP.d_name,SD.sp_name,\n"
                + "P.pr_num,P.pr_type,P.pr_name,P.pr_health,"
                + "T.ty_name,"
                + "O.oh_num,O.oh_date,O.notes\n"
                + "FROM Employee AS E,Printer as P, Devices AS D, Ohda AS O,Device_Type as T,Department as DP,Sub_Dep as SD "
                + "where E.em_num=" + em_num + "  AND  D.ty_num=2  and T.ty_num=2 and O.em_num=E.em_num AND O.d_num=D.d_num AND  D.de_num=P.pr_num and E.d_num=DP.d_num and E.sp_num=SD.sp_num";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_all_ps> ar = new ArrayList<en_all_ps>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_all_ps a = new en_all_ps();
                a.setEmployee_name(rs.getString("em_name"));

                a.setEmployee_id(rs.getInt("em_id"));
                a.setOffice(rs.getString("office"));
                a.setRoom(rs.getString("room"));
                a.setDepartment(rs.getString("d_name"));
                a.setSup_department(rs.getString("sp_name"));
                a.setType(rs.getString("ty_name"));
                a.setSup_department(rs.getString("sp_name"));
                a.setPs_num(rs.getInt("pr_num"));
                a.setDevice_type(rs.getString("pr_type"));
                a.setDevice_name(rs.getString("pr_name"));
                a.setHealth(rs.getString("pr_health"));
                a.setNotes(rs.getString("notes"));
                a.setDate(rs.getDate("oh_date"));
                a.setOh_num(rs.getInt("oh_num"));

                ar.add(a);
            }
            System.out.println("end while");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                Connect.close();
                statement.close();
                System.out.println("finally");
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }
        System.out.println("end function");
        return ar;

    }

    public ArrayList<en_all_ps> get_em_s(int id) throws SQLException {
        employee em = new employee();

        int em_num = em.get_employee_id(id);
        String query = "SELECT E.em_name, E.em_id, E.office, E.room,\n"
                + "DP.d_name,SD.sp_name,\n"
                + "S.sc_num,S.sc_type,S.sc_name,S.sc_health,"
                + "T.ty_name,"
                + "O.oh_num,O.oh_date,O.notes\n"
                + "FROM Employee AS E,Scanner AS S, Devices AS D, Ohda AS O,Device_Type as T,Department as DP,Sub_Dep as SD "
                + "where E.em_num=" + em_num + "  AND  D.ty_num=3  and T.ty_num=3 and O.em_num=E.em_num AND O.d_num=D.d_num AND  D.de_num=S.sc_num and E.d_num=DP.d_num and E.sp_num=SD.sp_num";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_all_ps> ar = new ArrayList<en_all_ps>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_all_ps a = new en_all_ps();
                a.setEmployee_name(rs.getString("em_name"));

                a.setEmployee_id(rs.getInt("em_id"));

                a.setOffice(rs.getString("office"));

                a.setRoom(rs.getString("room"));

                a.setDepartment(rs.getString("d_name"));
                a.setSup_department(rs.getString("sp_name"));
                a.setType(rs.getString("ty_name"));

                //a.setSup_department(rs.getString("sp_name"));
                a.setPs_num(rs.getInt("sc_num"));
                a.setDevice_type(rs.getString("sc_type"));
                a.setDevice_name(rs.getString("sc_name"));
                a.setHealth(rs.getString("sc_health"));
                a.setNotes(rs.getString("notes"));
                a.setDate(rs.getDate("oh_date"));
                a.setOh_num(rs.getInt("oh_num"));

                ar.add(a);
            }
            System.out.println("end while");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                Connect.close();
                statement.close();
                System.out.println("finally scanner size is: " + ar.size());
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }
        System.out.println("end function");
        return ar;

    }

    public ArrayList<en_all_parts> get_all_archive() throws SQLException {

        String query = "select A.ID,A.parts_count,A.notes,A.sent_date,P.type,P.part_name from archive as A,Parts as P where A.part_id = P.ID ";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_all_parts> ar = new ArrayList<en_all_parts>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_all_parts a = new en_all_parts();
                a.setID(rs.getInt("ID"));
                a.setType(rs.getString("type"));
                a.setName(rs.getString("part_name"));
                a.setCount(rs.getInt("parts_count"));
                a.setNotes(rs.getString("notes"));
                a.setSent_date(rs.getDate("sent_date"));

                ar.add(a);
            }
            System.out.println("end while");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                Connect.close();
                statement.close();
                System.out.println("finally parts size is: " + ar.size());
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }
        System.out.println("end function");
        return ar;

    }

    public ArrayList<en_parts> get_all_parts() throws SQLException {

        String query = "select ID,type,part_name,parts_count,recived_date from Parts";
        dbconnect con = new dbconnect();
        Connection Connect = con.getConnection();
        Statement statement = null;
        ArrayList<en_parts> ar = new ArrayList<en_parts>();
        try {
            statement = Connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                en_parts a = new en_parts();
                a.setID(rs.getInt("ID"));
                a.setType(rs.getString("type"));
                a.setPart_name(rs.getString("part_name"));
                a.setParts_count(rs.getInt("parts_count"));
                
                a.setRecived_date( rs.getDate("recived_date"));

                ar.add(a);
            }
            System.out.println("end while");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                Connect.close();
                statement.close();
                System.out.println("finally parts size is: " + ar.size());
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }
        System.out.println("end function");
        return ar;

    }
}

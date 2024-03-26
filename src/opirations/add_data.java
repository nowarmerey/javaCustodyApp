/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opirations;

import databaseFunctions.computer;
import databaseFunctions.department;
import databaseFunctions.device_type;
import databaseFunctions.devices;
import databaseFunctions.employee;
import databaseFunctions.ohda;
import databaseFunctions.printer;
import databaseFunctions.scanner;
import databaseFunctions.sup_department;
import databaseFunctions.users;
import entities.en_computer;
import entities.en_department;
import entities.en_deviceType;
import entities.en_devices;
import entities.en_employee;
import entities.en_ohda;
import entities.en_printer;
import entities.en_scanner;
import entities.en_subDep;
import entities.en_users;
import java.sql.SQLException;

/**
 *
 * @author Smart
 */
public class add_data {
    
    public void add_department_op(en_department dp) {
        department odp = new department();
        try {
            odp.add_Department(dp);
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
    }
    
    public void add_sup_department_op(en_subDep sdp) {
        sup_department osdp = new sup_department();
        try {
            osdp.add_sup_Department(sdp);
        } catch (Exception ex) {
            System.out.println(ex);
        }        
    }
    
    public void add_device_type_op(en_deviceType dt) {
        device_type odt = new device_type();
        
        try {
            odt.add_device_type(dt);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
    }
    
    public void add_computer_op(en_computer co) {
        computer oco = new computer();
        try {
            oco.add_computer(co);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void add_printer_op(en_printer pr) {
        printer opr = new printer();
        try {
            opr.add_printer(pr);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void add_scanner_op(en_scanner sc){
        scanner osc = new scanner();
        try{
            osc.add_scanner(sc);
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void add_device_op(en_devices de){
        devices ode = new devices();
        try{
         ode.add_device(de);
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void add_employee_op(en_employee em){
        employee oem = new employee();
        try{
         oem.add_employee(em);
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void add_ohda_op(en_ohda oh){
        ohda ooh = new ohda();
        try{
         ooh.add_ohda(oh);
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void add_user_op(en_users us){
        users ous = new users();
        try{
         ous.add_user(us);
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
}

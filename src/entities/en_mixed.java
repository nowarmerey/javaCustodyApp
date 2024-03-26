/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Smart
 */
public class en_mixed {
   private String department;
   private String subDepartment;
   private ArrayList<String> department_list;
   private ArrayList<String> supdep_list;
   private ArrayList<en_deviceType> deviceType;
   private ArrayList<en_devices> Devices;
   private en_employee employee;
   private ArrayList<en_employee> employee_list;
   private ArrayList<en_computer> computer;
   private ArrayList<en_printer> printer;
   private ArrayList<en_scanner> scanner;
   private ArrayList<en_ohda> ohda;
   private HashMap<Integer,Integer> map;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSubDepartment() {
        return subDepartment;
    }

    public void setSubDepartment(String subDepartment) {
        this.subDepartment = subDepartment;
    }

 

   

    public ArrayList<en_deviceType> getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(ArrayList<en_deviceType> deviceType) {
        this.deviceType = deviceType;
    }

    public ArrayList<en_devices> getDevices() {
        return Devices;
    }

    public void setDevices(ArrayList<en_devices> Devices) {
        this.Devices = Devices;
    }



    public en_employee getEmployee() {
        return employee;
    }

    public void setEmployee(en_employee employee) {
        this.employee = employee;
    }

    public ArrayList<en_computer> getComputer() {
        return computer;
    }

    public void setComputer(ArrayList<en_computer> computer) {
        this.computer = computer;
    }

    public ArrayList<en_printer> getPrinter() {
        return printer;
    }

    public void setPrinter(ArrayList<en_printer> printer) {
        this.printer = printer;
    }

    public ArrayList<en_scanner> getScanner() {
        return scanner;
    }

    public void setScanner(ArrayList<en_scanner> scanner) {
        this.scanner = scanner;
    }

    public ArrayList<en_ohda> getOhda() {
        return ohda;
    }

    public void setOhda(ArrayList<en_ohda> ohda) {
        this.ohda = ohda;
    }

    public ArrayList<String> getDepartment_list() {
        return department_list;
    }

    public void setDepartment_list(ArrayList<String> department_list) {
        this.department_list = department_list;
    }

    public ArrayList<String> getSupdep_list() {
        return supdep_list;
    }

    public void setSupdep_list(ArrayList<String> supdep_list) {
        this.supdep_list = supdep_list;
    }

    public ArrayList<en_employee> getEmployee_list() {
        return employee_list;
    }

    public void setEmployee_list(ArrayList<en_employee> employee_list) {
        this.employee_list = employee_list;
    }

    public HashMap<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, Integer> map) {
        this.map = map;
    }
    
    
    
    

 

   
   

   
   
    
    
    
    
}

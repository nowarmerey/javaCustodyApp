/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Smart
 */
public class en_all_ps {
    
    private String employee_name;
    private int employee_id;
    private int ps_num;
    private String office;
    private String room;
    private String department;
    private String sup_department;
    private String type;
    private String device_name;
    private String device_type;
    private String health;
    private Date date;
    private int oh_num;
    private String notes;

    public en_all_ps() {
    }

    public en_all_ps(String employee_name, int employee_id, int ps_num, String office, String room, String department, String sup_department, String type, String device_name, String device_type, String health, Date date,int oh_num, String notes) {
        this.employee_name = employee_name;
        this.employee_id = employee_id;
        this.ps_num = ps_num;
        this.office = office;
        this.room = room;
        this.department = department;
        this.sup_department = sup_department;
        this.type = type;
        this.device_name = device_name;
        this.device_type = device_type;
        this.health = health;
        this.date = date;
        this.oh_num = oh_num;
        this.notes = notes;
    }
    
    

    

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSup_department() {
        return sup_department;
    }

    public void setSup_department(String sup_department) {
        this.sup_department = sup_department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    

    public int getPs_num() {
        return ps_num;
    }

    public void setPs_num(int ps_num) {
        this.ps_num = ps_num;
    }

    public int getOh_num() {
        return oh_num;
    }

    public void setOh_num(int oh_num) {
        this.oh_num = oh_num;
    }
    
    
    
    
}

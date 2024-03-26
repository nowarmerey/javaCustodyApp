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
public class en_all_computers {

    private int em_num;
    private String employee_name;
    private  int employee_id;
    private String office;
    private String room;
    private String department;
    private String sup_department;
    private int computer_number;
    private String motherboard;
    private String ram;
    private String hdd;
    private String cpu;
    private String monitor;
    private String keyboard;
    private String mouse;
    private String subwoofer;
    private String type;
    private Date date;
    private int ohda_num;
    private String notes;

    public en_all_computers() {
    }

    public en_all_computers(int em_num, String employee_name, int employee_id, String office, String room, String department, String sup_department, int computer_number, String motherboard, String ram, String hdd, String cpu, String monitor, String keyboard, String mouse, String subwoofer, String type, Date date, int ohda_num, String notes) {
        this.em_num = em_num;
        this.employee_name = employee_name;
        this.employee_id = employee_id;
        this.office = office;
        this.room = room;
        this.department = department;
        this.sup_department = sup_department;
        this.computer_number = computer_number;
        this.motherboard = motherboard;
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
        this.monitor = monitor;
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.subwoofer = subwoofer;
        this.type = type;
        this.date = date;
        this.ohda_num = ohda_num;
        this.notes = notes;
    }

 

    public int getEm_num() {
        return em_num;
    }

    public void setEm_num(int em_num) {
        this.em_num = em_num;
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

    public String getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public String getSubwoofer() {
        return subwoofer;
    }

    public void setSubwoofer(String subwoofer) {
        this.subwoofer = subwoofer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getComputer_number() {
        return computer_number;
    }

    public void setComputer_number(int computer_number) {
        this.computer_number = computer_number;
    }

    public int getOhda_num() {
        return ohda_num;
    }

    public void setOhda_num(int ohda_num) {
        this.ohda_num = ohda_num;
    }
    
    
    

}

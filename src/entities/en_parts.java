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
public class en_parts {
    private int ID;
    private String type;
    private String part_name;
    private int parts_count;
    private Date recived_date;

    public en_parts(int ID, String type, String part_name, int parts_count, Date recived_date) {
        this.ID = ID;
        this.type = type;
        this.part_name = part_name;
        this.parts_count = parts_count;
        this.recived_date = recived_date;
    }

    public en_parts() {
    }
    
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPart_name() {
        return part_name;
    }

    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }

    public int getParts_count() {
        return parts_count;
    }

    public void setParts_count(int parts_count) {
        this.parts_count = parts_count;
    }

    public Date getRecived_date() {
        return recived_date;
    }

    public void setRecived_date(Date recived_date) {
        this.recived_date = recived_date;
    }
    
    
}

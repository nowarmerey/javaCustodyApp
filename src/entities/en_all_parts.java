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
public class en_all_parts {
    private int ID;
    private String type;
    private String name;
    private int count;
    private String notes;
    private Date sent_date;

    public en_all_parts(int ID, String type, String name, int count, String notes, Date sent_date) {
        this.ID = ID;
        this.type = type;
        this.name = name;
        this.count = count;
        this.notes = notes;
        this.sent_date = sent_date;
    }

    public en_all_parts() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getSent_date() {
        return sent_date;
    }

    public void setSent_date(Date sent_date) {
        this.sent_date = sent_date;
    }
    
    
}

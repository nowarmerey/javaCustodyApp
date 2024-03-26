/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;


/**
 *
 * @author Smart
 */
public class en_archive {
    private int ID;
    private int part_id;
    private int parts_count;
    private String notes;
     private LocalDate sent_date;

    public en_archive(int ID, int part_id,int parts_count ,String notes, LocalDate sent_date) {
        this.ID = ID;
        this.part_id = part_id;
        this.parts_count = parts_count;
        this.notes = notes;
        this.sent_date = sent_date;
    }

    public en_archive() {
    }

    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPart_id() {
        return part_id;
    }

    public void setPart_id(int part_id) {
        this.part_id = part_id;
    }

    public int getParts_count() {
        return parts_count;
    }

    public void setParts_count(int parts_count) {
        this.parts_count = parts_count;
    }

    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getSent_date() {
        return sent_date;
    }

    public void setSent_date(LocalDate sent_date) {
        this.sent_date = sent_date;
    }
    
    
}

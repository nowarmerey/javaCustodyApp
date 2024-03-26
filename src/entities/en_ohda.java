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
public class en_ohda {
    private int oh_num;
    private int em_num;
    private int d_num;
    private LocalDate oh_date;
    private String notes;

    public int getOh_num() {
        return oh_num;
    }

    public void setOh_num(int oh_num) {
        this.oh_num = oh_num;
    }

    public int getEm_num() {
        return em_num;
    }

    public void setEm_num(int em_num) {
        this.em_num = em_num;
    }
    

    public int getD_num() {
        return d_num;
    }

    public void setD_num(int d_num) {
        this.d_num = d_num;
    }

    public LocalDate getOh_date() {
        return oh_date;
    }

    public void setOh_date(LocalDate oh_date) {
        this.oh_date = oh_date;
    }


    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
}

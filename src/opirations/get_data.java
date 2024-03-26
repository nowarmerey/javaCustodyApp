/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opirations;

import databaseFunctions.employee;
import databaseFunctions.mixed;
import entities.en_employee;
import entities.en_mixed;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Smart
 */
public class get_data {

    public ArrayList<en_employee> get_employees_info() {
        ArrayList<en_employee> emp = new ArrayList<en_employee>();
        employee oemp = new employee();
        try {
            emp = oemp.show_employees();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return emp;
    }

    public en_employee get_employee_info(int id) {
        en_employee emp = new en_employee();
        employee oemp = new employee();
        try {
            int num = oemp.get_employee_id(id);
            emp = oemp.get_employee(num);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return emp;
    }
    
    //public ObservableList<en_mixed> search_for_employee(int id){

       
        
    //}
    
    
    

}

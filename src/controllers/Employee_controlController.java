/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseFunctions.department;
import databaseFunctions.employee;
import databaseFunctions.mixed;
import databaseFunctions.sup_department;
import entities.en_all_computers;
import entities.en_department;
import entities.en_employee;
import entities.en_subDep;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Smart
 */
public class Employee_controlController implements Initializable {

    @FXML
    private ComboBox<String> t_floor;
    @FXML
    private ComboBox<String> t_room;
    @FXML
    private ComboBox<String> t_supdep;
    @FXML
    private ComboBox<String> t_dep;
    @FXML
    private ComboBox<String> t_id;
    @FXML
    private ComboBox<String> t_name;
    @FXML
    private Button update_bu;
    @FXML
    private Button add_bu;

    String name, office, room, dep, subdep, id;
    boolean checkpoint;
    int oldID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            get_departments();
            get_sup_deps();
            get_employee_names();
            get_office_floor();

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void get_id_for_selected_employee(ActionEvent event) {
        try {
            String name = t_name.getValue();
            mixed mix = new mixed();
            employee em = new employee();
            int id = em.get_employee_id_from_name(name);
            //int num = em.get_employee_id(id);
            en_all_computers emp = mix.get_full_employee_info(id);
            t_dep.setValue(emp.getDepartment());
            t_supdep.setValue(emp.getSup_department());
            t_floor.setValue(emp.getRoom());
            t_room.setValue(emp.getOffice());
            t_id.setValue(String.valueOf(id));
            oldID = em.get_employee_id(id);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void update_action(ActionEvent event) {
        try {
            department d = new department();
            sup_department sd = new sup_department();
            en_employee em = new en_employee();
            employee e = new employee();
            em.setEm_num(oldID);
            em.setEm_name(t_name.getValue());
            em.setEm_id(Integer.parseInt(t_id.getValue()));
            em.setD_num(d.get_department_id(t_dep.getValue()));
            em.setSp_num(sd.get_sup_department_id(t_supdep.getValue()));
            em.setOffice(t_room.getValue());
            em.setRoom(t_floor.getValue());
            e.update_employee(em);
            reset_employee_fields();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Employee information has been modified", ButtonType.OK);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void get_employee_data_from_textfields() {
        if (t_name.getValue().equals("") || t_id.getValue().equals("") || t_dep.getValue().equals("") || t_supdep.getValue().equals("") || t_room.getValue().equals("") || t_floor.getValue().equals("")) {
            checkpoint = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ensure that employee information is entered...", ButtonType.OK);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                //do stuff
            }
        } else {

            checkpoint = true;
            name = t_name.getValue();
            id = t_id.getValue();
            dep = t_dep.getValue();
            subdep = t_supdep.getValue();
            room = t_room.getValue();
            office = t_floor.getValue();

        }

    }

    @FXML
    private void add_action(ActionEvent event) throws SQLException {
        get_employee_data_from_textfields();
        if (checkpoint) {
            employee em = new employee();
            department depl = new department();
            sup_department supdep = new sup_department();
            int dep_id = depl.get_department_id(t_dep.getValue());
            int sup_id = supdep.get_sup_department_id(t_supdep.getValue());
            int integre_id_for_employee = Integer.parseInt(id);
            try {
                int primary_key = 0;
                primary_key = em.get_employee_id(integre_id_for_employee);

                if (primary_key == 0) {
                    en_employee en = new en_employee();
                    en.setEm_name(name);
                    en.setEm_id(integre_id_for_employee);
                    en.setD_num(dep_id);
                    en.setSp_num(sup_id);
                    en.setOffice(room);
                    en.setRoom(office);
                    em.add_employee(en);
                    reset_employee_fields();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "A new employee has been added", ButtonType.OK);
                    alert.showAndWait();
                    System.out.println("employee added........");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The employee is already there", ButtonType.OK);
                    alert.showAndWait();
                }

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
                alert.showAndWait();
            }

        } else {
            System.out.println("checkpoint is false");

        }
    }

    public void reset_employee_fields() {
        t_name.setValue("");
        t_id.setValue("");
        t_dep.setValue("");
        t_supdep.setValue("");
        t_room.setValue("");
        t_floor.setValue("");

    }

    public void get_departments() {

        department d = new department();
        ArrayList<en_department> ar = d.show_Departments();
        ObservableList<String> deps = FXCollections.observableArrayList();

        for (int i = 0; i < ar.size(); i++) {
            deps.add(ar.get(i).getD_name());
        }
        t_dep.setItems(deps);
    }

    public void get_sup_deps() {
        sup_department sp = new sup_department();
        ArrayList<en_subDep> ar = sp.show_sup_Departments();
        ObservableList<String> supdep = FXCollections.observableArrayList();
        for (int i = 0; i < ar.size(); i++) {
            supdep.add(ar.get(i).getSp_name());
        }
        t_supdep.setItems(supdep);
    }

    public void get_employee_names() throws SQLException {
        employee em = new employee();
        ArrayList<en_employee> ar = em.show_employees();
        ObservableList<String> aremployees = FXCollections.observableArrayList();
        ObservableList<String> arids = FXCollections.observableArrayList();
        for (int i = 0; i < ar.size(); i++) {

            aremployees.add(ar.get(i).getEm_name());
            arids.add(String.valueOf(ar.get(i).getEm_id()));
        }
        t_name.setItems(aremployees);
        t_id.setItems(arids);

    }

    public void get_office_floor() throws SQLException {
        employee em = new employee();

        ArrayList<String> of = em.get_offices();
        ArrayList<String> ro = em.get_rooms();
        ObservableList<String> off = FXCollections.observableArrayList(of);
        ObservableList<String> roo = FXCollections.observableArrayList(ro);

        try {

            t_room.setItems(off);
            t_floor.setItems(roo);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void get_id_for_selected_employee() throws SQLException {
        try {
            String name = t_name.getValue();
            mixed mix = new mixed();
            employee em = new employee();
            int id = em.get_employee_id_from_name(name);
            //int num = em.get_employee_id(id);
            en_all_computers emp = mix.get_full_employee_info(id);
            t_dep.setValue(emp.getDepartment());
            t_supdep.setValue(emp.getSup_department());
            t_floor.setValue(emp.getRoom());
            t_room.setValue(emp.getOffice());
            t_id.setValue(String.valueOf(id));

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
            alert.showAndWait();
        }

    }

}

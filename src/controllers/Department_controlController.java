/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseFunctions.department;
import databaseFunctions.sup_department;
import entities.en_department;
import entities.en_subDep;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Smart
 */
public class Department_controlController implements Initializable {

    @FXML
    private Button add_but;

    @FXML
    private ComboBox<String> primary_dep;
    @FXML
    private TextField secondary_dep;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        get_departments();
    }

    public void get_departments() {

        department d = new department();
        ArrayList<en_department> ar = d.show_Departments();
        ObservableList<String> deps = FXCollections.observableArrayList();

        for (int i = 0; i < ar.size(); i++) {
            if (deps.contains(ar.get(i).getD_name())) {

            } else {
                deps.add(ar.get(i).getD_name());
            }

        }
        primary_dep.setItems(deps);
    }

    public void add_department() throws SQLException {
        department d = new department();
        sup_department sp = new sup_department();
        en_subDep esp = new en_subDep();
        en_department ed = new en_department();

        if (primary_dep.getValue() == null || secondary_dep.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ensure that the data is entered correctly...?", ButtonType.OK);
            alert.showAndWait();
        } else {

            ed.setD_name(primary_dep.getValue());
            int id = 0;
            id = d.get_department_id(primary_dep.getValue());
            if (id != 0) {
                esp.setSp_name(secondary_dep.getText());
                esp.setD_num(id);
                sp.add_sup_Department(esp);
            } else {
                d.add_Department(ed);
                esp.setSp_name(secondary_dep.getText());
                esp.setD_num(id);
                sp.add_sup_Department(esp);
            }

            get_departments();
            primary_dep.setValue("");
            secondary_dep.setText("");

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK);
            alert.showAndWait();
        }

    }
}

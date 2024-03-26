/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseFunctions.devices;
import databaseFunctions.employee;
import databaseFunctions.ohda;
import databaseFunctions.printer;
import databaseFunctions.scanner;
import entities.en_all_ps;
import entities.en_employee;
import entities.en_printer;
import entities.en_scanner;
import functions.navigation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Smart
 */
public class Update_psController implements Initializable {

    @FXML
    private TextField tp_type;

    @FXML
    private TextField tp_name;

    @FXML
    private TextField tp_health;

    @FXML
    private TextArea ps_notes;
    @FXML
    private TextField t_id;

    @FXML
    private Button save_bu;

    @FXML
    private CheckBox check_move;

    @FXML
    private ComboBox<String> employee_list;

    @FXML
    private Button new_bu;

    @FXML
    private Button move_bu;

    int old_id, device_num;
    navigation nav = new navigation();

    en_all_ps c;
    String type, name, health, notes;
    @FXML
    private Button ref_but;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c = new en_all_ps();
        employee_list.setDisable(true);
        new_bu.setDisable(true);
        move_bu.setDisable(true);
        ref_but.setDisable(true);
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
        employee_list.setItems(aremployees);

    }

    public void get_id_for_selected_employee() throws SQLException {
        try {
            String name = employee_list.getValue();

            employee em = new employee();
            int id = em.get_employee_id_from_name(name);

            t_id.setText(String.valueOf(id));

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void check_action() throws SQLException {
        if (check_move.isSelected()) {
            employee_list.setDisable(false);
            new_bu.setDisable(false);
            move_bu.setDisable(false);
            ref_but.setDisable(false);
            get_employee_names();
        } else {
            employee_list.setDisable(true);
            new_bu.setDisable(true);
            move_bu.setDisable(true);
            ref_but.setDisable(true);
        }

    }

    public void refresh() throws SQLException {
        get_id_for_selected_employee();
    }

    public void setup_page(en_all_ps ec) {
        System.out.println("controllers.Update_psController.setup_page()");
        c = ec;
        tp_type.setText(c.getDevice_type());
        tp_name.setText(c.getDevice_name());
        tp_health.setText(c.getHealth());
        ps_notes.setText(c.getNotes());
        old_id = c.getEmployee_id();
        device_num = c.getPs_num();

    }

    public void move_ohda() throws SQLException {
        try {
            ohda o = new ohda();
            employee e = new employee();
            devices d = new devices();
            int number_devices_primary = 0;
            d.get_device_number(device_num, 1);
            if (c.getType().equals("طابعة")) {
                number_devices_primary = d.get_device_number(device_num, 2);
            } else {
                number_devices_primary = d.get_device_number(device_num, 3);
            }
            String name = employee_list.getValue();

            int em_num = e.get_employee_num_from_name(name);
            int old = e.get_employee_id(old_id);
            System.out.println("new: " + em_num + " old: " + old);
            o.update_employee(em_num, old, number_devices_primary);
            System.out.println("ohda move done");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Moved...", ButtonType.OK);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
            alert.showAndWait();
        }

    }

  public void add_action() throws IOException {
//         FXMLLoader loader = new FXMLLoader();
//                  loader.setLocation(getClass().getResource(nav.getAdd()));

        Parent root = null;
        URL url = null;

        try {

            url = getClass().getResource(nav.getEmployees());
            root = FXMLLoader.load(getClass().getResource(nav.getEmployees()));
            System.out.println("  fxmlResource = " + nav.getEmployees());
            Stage stage = new Stage();
           
            stage.setScene(new Scene(root));
            stage.showingProperty().addListener((observable, oldValue, showing) -> {

                if (showing) {

                    stage.setMinHeight(157.0);
                    stage.setMinWidth(980.0);
                    stage.setMaximized(false);
                    stage.setTitle("Employees");
                }
            });
            stage.show();
        } catch (Exception ex) {
            System.out.println("Exception on FXMLLoader.load()");
            System.out.println("  * url: " + url);
            System.out.println("  * path: " + nav.getOhda());
            System.out.println("  * " + ex);
            System.out.println("    ----------------------------------------\n");
            throw ex;
        }

    }

    @FXML
    public void update_com() throws SQLException {
        try {
            printer com = new printer();
            scanner scn = new scanner();
            ohda o = new ohda();
            en_printer ep = new en_printer();
            en_scanner es = new en_scanner();
            type = tp_type.getText();
            name = tp_name.getText();
            health = tp_health.getText();
            notes = ps_notes.getText();
            if (c.getType().equals("طابعة")) {
                ep.setPr_num(c.getPs_num());
                ep.setPr_name(name);
                ep.setPr_type(type);
                ep.setPr_health(health);
                com.update_printer(ep);
            } else {
                es.setSc_num(c.getPs_num());
                es.setSc_type(type);
                es.setSc_name(name);
                es.setSc_health(health);
                scn.update_scanner(es);
            }

            o.update_ohda(c.getOh_num(), notes);
            tp_type.setText("");
            tp_name.setText("");
            tp_health.setText("");
            ps_notes.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update done", ButtonType.OK);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
            alert.showAndWait();
        }

    }

}

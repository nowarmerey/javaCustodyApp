/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseFunctions.computer;
import databaseFunctions.devices;
import databaseFunctions.employee;

import databaseFunctions.ohda;
import entities.en_all_computers;
import entities.en_computer;
import entities.en_employee;
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
public class Update_computerController implements Initializable {

    @FXML
    private TextField t_keyboard;
    @FXML
    private TextField t_em_num;

    @FXML
    private TextField t_subwoofer;

    @FXML
    private TextField t_mouse;

    @FXML
    private TextField t_ram;

    @FXML
    private TextField t_monitor;

    @FXML
    private TextField t_cpu;

    @FXML
    private TextField t_hdd;

    @FXML
    private TextField t_motherboard;
    @FXML
    private TextField t_id;

    @FXML
    private TextArea computer_notes;

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

    @FXML
    private Button ref_but;

    navigation nav = new navigation();

    en_all_computers c;
    String motherboard, cpu, ram, hdd, keyboard, mouse, subwoofer, monitor, notes;
    int old_id, device_num;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c = new en_all_computers();
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

    public void setup_page(en_all_computers ec) {
        System.out.println("controllers.Update_computerController.setup_page()");
        c = ec;
        t_em_num.setText(String.valueOf(c.getEm_num()));
        t_motherboard.setText(c.getMotherboard());
        t_cpu.setText(c.getCpu());
        t_hdd.setText(c.getHdd());
        t_keyboard.setText(c.getKeyboard());
        t_ram.setText(c.getRam());
        t_mouse.setText(c.getMouse());
        t_monitor.setText(c.getMonitor());
        t_subwoofer.setText(c.getSubwoofer());
        computer_notes.setText(c.getNotes());
        old_id = c.getEm_num();
        device_num = c.getComputer_number();
    }

    public void move_ohda() throws SQLException {
        try {
            ohda o = new ohda();
            employee e = new employee();
            devices d = new devices();
            String name = employee_list.getValue();
            int number_devices_primary = d.get_device_number(device_num, 1);
            int em_num = e.get_employee_num_from_name(name);
            System.out.println("new: " + em_num + " old: " + old_id + "device: " + device_num);
            o.update_employee(em_num, Integer.parseInt(t_em_num.getText()), number_devices_primary);
            System.out.println("ohda move done");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Moved... old id for "+ c.getEmployee_name()+"= " + t_em_num.getText() + "  new id for " +name+ " = " + em_num, ButtonType.OK);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.toString(), ButtonType.OK);
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

    public void update_com() throws SQLException {
        try {
            computer com = new computer();
            ohda o = new ohda();
            en_computer ec = new en_computer();

            motherboard = t_motherboard.getText();
            ram = t_ram.getText();
            cpu = t_cpu.getText();
            hdd = t_hdd.getText();
            monitor = t_monitor.getText();
            keyboard = t_keyboard.getText();
            mouse = t_mouse.getText();
            subwoofer = t_subwoofer.getText();
            notes = computer_notes.getText();
            ec.setCo_num(c.getComputer_number());
            ec.setMotherboard(motherboard);
            ec.setCpu(cpu);
            ec.setRam(ram);
            ec.setHdd(hdd);
            ec.setMonitor(monitor);
            ec.setKeyboard(keyboard);
            ec.setMouse(mouse);
            ec.setSubwoofer(subwoofer);

            com.update_computer(ec);
            o.update_ohda(c.getOhda_num(), notes);
            t_motherboard.setText("");
            t_cpu.setText("");
            t_hdd.setText("");
            t_keyboard.setText("");
            t_ram.setText("");
            t_mouse.setText("");
            t_monitor.setText("");
            t_subwoofer.setText("");
            computer_notes.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update done", ButtonType.OK);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
            alert.showAndWait();
        }

    }

}

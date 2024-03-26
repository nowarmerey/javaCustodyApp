/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseFunctions.computer;
import databaseFunctions.employee;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Smart
 */
public class Computer_add_pageController implements Initializable {

    @FXML
    private TextArea tc_notes;
    @FXML
    private ComboBox<String> t_keyboard;
    @FXML
    private ComboBox<String> t_subwoofer;
    @FXML
    private ComboBox<String> t_mouse;
    @FXML
    private ComboBox<String> t_ram;
    @FXML
    private ComboBox<String> t_monitor;
    @FXML
    private ComboBox<String> t_cpu;
    @FXML
    private ComboBox<String> t_hdd;
    @FXML
    private ComboBox<String> t_motherboard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            System.out.println("addddddddd paaaaaaagggggge");
            get_data_fill();
            TextFields.bindAutoCompletion(t_motherboard.getEditor(), t_motherboard.getItems());
            TextFields.bindAutoCompletion(t_ram.getEditor(), t_ram.getItems());
            TextFields.bindAutoCompletion(t_hdd.getEditor(), t_hdd.getItems());
            TextFields.bindAutoCompletion(t_cpu.getEditor(), t_cpu.getItems());
            TextFields.bindAutoCompletion(t_monitor.getEditor(), t_monitor.getItems());
            TextFields.bindAutoCompletion(t_keyboard.getEditor(), t_keyboard.getItems());
            TextFields.bindAutoCompletion(t_mouse.getEditor(), t_mouse.getItems());
            TextFields.bindAutoCompletion(t_subwoofer.getEditor(), t_subwoofer.getItems());
       
            t_motherboard.setValue("");
            t_ram.setValue("");
            t_hdd.setValue("");
            t_cpu.setValue("");
            t_monitor.setValue("");
            t_keyboard.setValue("");
            t_mouse.setValue("");
            t_subwoofer.setValue("");
        } catch (SQLException ex) {
            Logger.getLogger(Computer_add_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void get_data_fill() throws SQLException {
        computer em = new computer();

        ArrayList<String> motherboard = em.get_data("motherboard");
        ArrayList<String> ram = em.get_data("ram");
        ArrayList<String> hdd = em.get_data("hdd");
        ArrayList<String> cpu = em.get_data("cpu");
        ArrayList<String> monitor = em.get_data("monitor");
        ArrayList<String> keyboard = em.get_data("keyboard");
        ArrayList<String> mouse = em.get_data("mouse");
        ArrayList<String> sup = em.get_data("sup");
        ObservableList<String> motherboard_1 = FXCollections.observableArrayList(motherboard);
        ObservableList<String> ram_1 = FXCollections.observableArrayList(ram);
        ObservableList<String> hdd_1 = FXCollections.observableArrayList(hdd);
        ObservableList<String> cpu_1 = FXCollections.observableArrayList(cpu);
        ObservableList<String> monitor_1 = FXCollections.observableArrayList(monitor);
        ObservableList<String> keyboard_1 = FXCollections.observableArrayList(keyboard);
        ObservableList<String> mouse_1 = FXCollections.observableArrayList(mouse);
        ObservableList<String> sup_1 = FXCollections.observableArrayList(sup);

        try {

            t_motherboard.setItems(motherboard_1);
            t_ram.setItems(ram_1);
            t_hdd.setItems(hdd_1);
            t_cpu.setItems(cpu_1);
            t_monitor.setItems(monitor_1);
            t_keyboard.setItems(keyboard_1);
            t_mouse.setItems(mouse_1);
            t_subwoofer.setItems(sup_1);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}

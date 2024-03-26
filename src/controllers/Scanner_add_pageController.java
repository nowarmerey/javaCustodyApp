/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseFunctions.printer;
import databaseFunctions.scanner;
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
public class Scanner_add_pageController implements Initializable {

    @FXML
    private TextArea t_notes_scanner;
    @FXML
    private ComboBox<String> ts_type;
    @FXML
    private ComboBox<String> ts_name;
    @FXML
    private ComboBox<String> ts_health;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            System.out.println("addddddddd paaaaaaagggggge");
            get_data_fill();
            TextFields.bindAutoCompletion(ts_type.getEditor(), ts_type.getItems());
            TextFields.bindAutoCompletion(ts_name.getEditor(), ts_name.getItems());
            TextFields.bindAutoCompletion(ts_health.getEditor(), ts_health.getItems());
            ts_type.setValue("");
            ts_name.setValue("");
            ts_health.setValue("");

        } catch (SQLException ex) {
            Logger.getLogger(Computer_add_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void get_data_fill() throws SQLException {
        scanner em = new scanner();

        ArrayList<String> type = em.get_data("sc_type");
        ArrayList<String> name = em.get_data("sc_name");
        ArrayList<String> health = em.get_data("sc_health");

        ObservableList<String> type_1 = FXCollections.observableArrayList(type);
        ObservableList<String> name_1 = FXCollections.observableArrayList(name);
        ObservableList<String> health_1 = FXCollections.observableArrayList(health);

        try {

            ts_type.setItems(type_1);
            ts_name.setItems(name_1);
            ts_health.setItems(health_1);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}

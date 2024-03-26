/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseFunctions.users;
import entities.en_users;
import functions.navigation;
import java.awt.event.KeyAdapter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Smart
 */
public class Login_pageController implements Initializable {

    @FXML
    private TextField name_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private Button login_but;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
  
        password_field.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    login_action();
                } catch (SQLException ex) {
                    Logger.getLogger(Login_pageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void login_action() throws SQLException {

        users u = new users();
        try {
            en_users eu = u.get_user(name_field.getText());
            System.out.println("username is :... " + name_field.getText());
            System.out.println("correct username is :... " + eu.getUsername());
            System.out.println("pass is :... " + password_field.getText());
            System.out.println("correct pass is :... " + eu.getPassword());
            if (eu.getUsername() != null) {
                if (Integer.parseInt(password_field.getText()) == eu.getPassword()) {
                    start_page_open();
                   // ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                    Stage stage = (Stage) login_but.getScene().getWindow();
                    stage.close();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "wrong password", ButtonType.OK);
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "wrong username", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
            alert.showAndWait();
        }

    }

    public void start_page_open() throws Exception {
        Parent root = null;
        navigation nav = new navigation();

        try {

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(nav.getStart()));

            root = fxmlloader.load();

            Stage stage = new Stage();
            stage.setTitle("Workshop");
            stage.setScene(new Scene(root));
            stage.showingProperty().addListener((observable, oldValue, showing) -> {

                if (showing) {

                    stage.setMinHeight(stage.getHeight());
                    stage.setMinWidth(stage.getWidth());
                    stage.setMaximized(true);
                    stage.setTitle("Main page");
                }
            });
            System.out.println("33...");

            System.out.println("33...");
            stage.show();
        } catch (Exception ex) {
            System.out.println("Exception on FXMLLoader.load()");
            //System.out.println("  * url: " + url);
            System.out.println("  * path: " + nav.getOhda());
            System.out.println("  * " + ex);
            System.out.println(" ----------------------------------------\n");
            throw ex;
        }
    }

}

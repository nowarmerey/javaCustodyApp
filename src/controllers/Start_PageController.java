/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseFunctions.computer;
import databaseFunctions.devices;
import databaseFunctions.employee;
import databaseFunctions.mixed;
import databaseFunctions.ohda;
import databaseFunctions.printer;
import databaseFunctions.scanner;

import entities.en_all_computers;
import entities.en_all_ps;
import entities.en_employee;
import entities.en_parts;
import functions.ExcelExport;

import functions.navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Smart
 */
public class Start_PageController implements Initializable {

    @FXML
    private AnchorPane mainpane;

    @FXML
    private TableView tableview;
    @FXML
    private TableView printer_scanner;
    @FXML
    private ComboBox<String> name_search;

    @FXML
    private TableColumn<en_all_computers, Date> Cdate;

    @FXML
    private TableColumn<en_all_computers, String> notesC;

    @FXML
    private TableColumn<en_all_computers, String> subwofeerC;

    @FXML
    private TableColumn<en_all_computers, String> mouseC;

    @FXML
    private TableColumn<en_all_computers, String> keyboardC;

    @FXML
    private TableColumn<en_all_computers, String> monitorC;

    @FXML
    private TableColumn<en_all_computers, String> hddC;

    @FXML
    private TableColumn<en_all_computers, String> cpuC;

    @FXML
    private TableColumn<en_all_computers, String> ramC;

    @FXML
    private TableColumn<en_all_computers, String> motherboardC;

    @FXML
    private TableColumn<en_all_computers, String> deviceC;

    @FXML
    private TableColumn<en_all_computers, String> roomC;

    @FXML
    private TableColumn<en_all_computers, String> officeC;

    @FXML
    private TableColumn<en_all_computers, String> supdepC;

    @FXML
    private TableColumn<en_all_computers, String> depC;

    @FXML
    private TableColumn<en_all_computers, Integer> numC;

    @FXML
    private TableColumn<en_all_computers, String> nameC;
    @FXML
    private TableColumn<en_all_computers, Integer> numberC;
    @FXML
    private TableColumn<en_all_computers, Integer> com_number;
    @FXML
    private TextArea information_area;
    @FXML
    private TextArea information_area1;
    @FXML
    private Button add_btn;

    @FXML
    private Button search_btn;
    @FXML
    private Button bu_refresh;

    @FXML
    private TextField search_field;
    @FXML
    private TableColumn<en_all_ps, String> deviceD;

    @FXML
    private TableColumn<en_all_ps, String> roomD;

    @FXML
    private TableColumn<en_all_ps, String> officeD;

    @FXML
    private TableColumn<en_all_ps, String> supdepD;

    @FXML
    private TableColumn<en_all_ps, String> depD;

    @FXML
    private TableColumn<en_all_ps, Integer> numD;

    @FXML
    private TableColumn<en_all_ps, String> nameD;

    @FXML
    private TableColumn<en_all_ps, Integer> ps_num;
    @FXML

    private TableColumn<en_all_ps, String> d_nameD;
    @FXML
    private TableColumn<en_all_ps, String> d_typeD;
    @FXML
    private TableColumn<en_all_ps, String> d_healthD;
    @FXML
    private TableColumn<en_all_ps, String> notesD;
    @FXML
    private TableColumn<en_all_ps, Date> dateD;

    ObservableList<en_all_computers> row;
    ObservableList<en_all_ps> row1;
    ObservableList<en_all_ps> row2;
    ObservableList<en_all_ps> row3;
    FilteredList<en_all_computers> filteredData;

    navigation nav = new navigation();

    en_all_computers comp, elc;
    en_all_ps ps;
    en_all_ps ps1;

    en_all_computers en_mix;

    public Start_PageController() {
        this.row3 = FXCollections.observableArrayList();
        this.row2 = FXCollections.observableArrayList();
        this.row1 = FXCollections.observableArrayList();
        this.row = FXCollections.observableArrayList();
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
        name_search.setItems(aremployees);

    }

    public void get_id_for_selected_employee() throws SQLException {
        try {
            String name = name_search.getValue();
            mixed mix = new mixed();
            employee em = new employee();
            int id = em.get_employee_id_from_name(name);
            //int num = em.get_employee_id(id);
            en_all_computers emp = mix.get_full_employee_info(id);

            search_field.setText(String.valueOf(id));

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            get_employee_names();
            TextFields.bindAutoCompletion(name_search.getEditor(), name_search.getItems());
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK);
            alert.showAndWait();
        }
        elc = new en_all_computers();

        search_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) {
                return;
            }
            search_field.setText(newValue.replaceAll("[^\\d]", ""));

        });

        try {
            System.out.println("hi...");
            setup_columns_for_computers();
            System.out.println("obaa ...");
            setup_start_page();
            System.out.println("end...");

        } catch (Exception e) {
            System.out.println(e);
        }

        tableview.setOnMouseClicked(event -> {
            try {
                show_computer_informations();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "There is no selection", ButtonType.OK);
                alert.showAndWait();
            }

            if (event.getClickCount() == 2) {
                try {

                    update_computer();
                } catch (Exception ex) {
                    Logger.getLogger(Start_PageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        printer_scanner.setOnMouseClicked(event -> {
            try {
                show_ps_informations();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "There is no selection", ButtonType.OK);
                alert.showAndWait();
            }

            if (event.getClickCount() == 2) {
                try {

                    update_ps();
                } catch (Exception ex) {
                    Logger.getLogger(Start_PageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void setup_columns_for_computers() {
        System.out.println("start setup 1....");

        numberC.setCellValueFactory(new PropertyValueFactory<>("em_num"));
        ps_num.setCellValueFactory(new PropertyValueFactory<>("ps_num"));
        com_number.setCellValueFactory(new PropertyValueFactory<>("computer_number"));
        nameC.setCellValueFactory(new PropertyValueFactory<>("employee_name"));

        numC.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        depC.setCellValueFactory(new PropertyValueFactory<>("department"));
        supdepC.setCellValueFactory(new PropertyValueFactory<>("sup_department"));
        officeC.setCellValueFactory(new PropertyValueFactory<>("office"));
        roomC.setCellValueFactory(new PropertyValueFactory<>("room"));
        deviceC.setCellValueFactory(new PropertyValueFactory<>("type"));
        motherboardC.setCellValueFactory(new PropertyValueFactory<>("motherboard"));
        ramC.setCellValueFactory(new PropertyValueFactory<>("ram"));
        cpuC.setCellValueFactory(new PropertyValueFactory<>("cpu"));
        hddC.setCellValueFactory(new PropertyValueFactory<>("hdd"));
        monitorC.setCellValueFactory(new PropertyValueFactory<>("monitor"));
        keyboardC.setCellValueFactory(new PropertyValueFactory<>("keyboard"));
        mouseC.setCellValueFactory(new PropertyValueFactory<>("mouse"));
        subwofeerC.setCellValueFactory(new PropertyValueFactory<>("subwoofer"));
        notesC.setCellValueFactory(new PropertyValueFactory<>("notes"));
        Cdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        System.out.println("second setup 2....");
        nameD.setCellValueFactory(new PropertyValueFactory<>("employee_name"));
        numD.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        depD.setCellValueFactory(new PropertyValueFactory<>("department"));
        supdepD.setCellValueFactory(new PropertyValueFactory<>("sup_department"));
        officeD.setCellValueFactory(new PropertyValueFactory<>("office"));
        roomD.setCellValueFactory(new PropertyValueFactory<>("room"));
        deviceD.setCellValueFactory(new PropertyValueFactory<>("type"));
        d_nameD.setCellValueFactory(new PropertyValueFactory<>("device_type"));
        d_typeD.setCellValueFactory(new PropertyValueFactory<>("device_name"));
        d_healthD.setCellValueFactory(new PropertyValueFactory<>("health"));
        notesD.setCellValueFactory(new PropertyValueFactory<>("notes"));

        dateD.setCellValueFactory(new PropertyValueFactory<>("date"));
        System.out.println("end setup 3....");
        printer_scanner.setItems(row1);

        System.out.println("adde row1....");
        tableview.setItems(row);
        System.out.println("added row ....");
    }

    public void setup_start_page() {
        System.out.println("start setup page.... 1 ...");

        mixed mx = new mixed();
        ArrayList<en_all_computers> c = mx.get_all_ohda();
        ArrayList<en_all_ps> p = mx.get_all_ps();
        ArrayList<en_all_ps> s = mx.get_all_s();
        System.out.println("befor for loop...");
        for (int i = 0; i < c.size(); i++) {
            System.out.println("i = " + i);
            comp = new en_all_computers(c.get(i).getEm_num(), c.get(i).getEmployee_name(), c.get(i).getEmployee_id(), c.get(i).getOffice(), c.get(i).getRoom(),
                    c.get(i).getDepartment(), c.get(i).getSup_department(), c.get(i).getComputer_number(), c.get(i).getMotherboard(), c.get(i).getRam(), c.get(i).getHdd(),
                    c.get(i).getCpu(), c.get(i).getMonitor(), c.get(i).getKeyboard(), c.get(i).getMouse(), c.get(i).getSubwoofer(), c.get(i).getType(),
                    c.get(i).getDate(), c.get(i).getOhda_num(), c.get(i).getNotes());

            row.add(comp);
            System.out.println("after add row");

            tableview.setItems(row);

            System.out.println("after end table");
        }
        filteredData = new FilteredList<>(row, b -> true);
        for (int i = 0; i < p.size(); i++) {
            System.out.println(p.get(i).getDevice_name());
            ps = new en_all_ps(p.get(i).getEmployee_name(), p.get(i).getEmployee_id(), p.get(i).getPs_num(), p.get(i).getOffice(), p.get(i).getRoom(),
                    p.get(i).getDepartment(), p.get(i).getSup_department(), p.get(i).getType(), p.get(i).getDevice_name(), p.get(i).getDevice_type(),
                    p.get(i).getHealth(), p.get(i).getDate(), p.get(i).getOh_num(), p.get(i).getNotes());

            row1.add(ps);
            System.out.println("after add row");

            printer_scanner.setItems(row1);

            System.out.println("after end table");
        }
        for (int i = 0; i < s.size(); i++) {
            System.out.println(s.get(i).getDevice_name());
            ps1 = new en_all_ps(s.get(i).getEmployee_name(), s.get(i).getEmployee_id(), s.get(i).getPs_num(), s.get(i).getOffice(), s.get(i).getRoom(),
                    s.get(i).getDepartment(), s.get(i).getSup_department(), s.get(i).getType(), s.get(i).getDevice_name(), s.get(i).getDevice_type(),
                    s.get(i).getHealth(), s.get(i).getDate(), s.get(i).getOh_num(), s.get(i).getNotes());

            row1.add(ps1);
            System.out.println("after add row");

            printer_scanner.setItems(row1);

            System.out.println("after end table");
        }

    }

    public void show_computer_informations() {
        System.out.println("controllers.Start_PageController.show_computer_informations()");

        en_all_computers mxd = (en_all_computers) tableview.getSelectionModel().getSelectedItem();
        information_area.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        information_area1.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        information_area.setText("Name : " + mxd.getEmployee_name() + "\n\n" + "ID : " + mxd.getEmployee_id() + "\n\n" + "Dep : " + mxd.getDepartment() + "\n\n" + "Sub : " + mxd.getSup_department() + "\n\n" + "Office : " + mxd.getOffice() + "\n\n"
                + "Floor : " + mxd.getRoom() + "\n\n Date: " + mxd.getDate());
        information_area1.setText("Motherboard : " + mxd.getMotherboard() + "\n\n" + "Ram : " + mxd.getRam() + "\n\n" + "CPU : " + mxd.getCpu() + "\n\n"
                + "HDD : " + mxd.getHdd() + "\n\n" + "Monitor : " + mxd.getMonitor() + "\n\n" + "Keyboard : " + mxd.getKeyboard() + "\n\n"
                + "Mouse : " + mxd.getMouse() + "\n\n" + "Subwoofer : " + mxd.getSubwoofer() + "\n\n" + "Notes : " + mxd.getNotes());
    }

    public void show_ps_informations() {
        System.out.println("controllers.Start_PageController.show_computer_informations()");

        en_all_ps ceb = (en_all_ps) printer_scanner.getSelectionModel().getSelectedItem();
        information_area.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        information_area1.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        information_area.setText("Name : " + ceb.getEmployee_name() + "\n\n" + "ID : " + ceb.getEmployee_id() + "\n\n" + "Dep : " + ceb.getDepartment() + "\n\n" + "Sub : " + ceb.getSup_department() + "\n\n" + "Office : " + ceb.getOffice() + "\n\n"
                + "Floor : " + ceb.getRoom());
        information_area1.setText("Model : " + ceb.getDevice_type() + "\n\n" + "Type : " + ceb.getDevice_name() + "\n\n" + "Status : " + ceb.getHealth() + "\n\n"
                + "Date : " + ceb.getDate() + "\n\n" + "Notes : " + ceb.getNotes());
    }

    @FXML
    public void search_for_employee() throws SQLException {

        if (!search_field.getText().equals("")) {
            int id = Integer.parseInt(search_field.getText());
            tableview.getItems().clear();
            printer_scanner.getItems().clear();

            mixed mx = new mixed();
            ArrayList<en_all_computers> c = mx.get_full_ohda_info(id);
            ArrayList<en_all_ps> prin = mx.get_em_ps(id);
            ArrayList<en_all_ps> s = mx.get_em_s(id);
            System.out.println("size of p:" + prin.size());
            System.out.println("befor for loop...");
            for (int i = 0; i < c.size(); i++) {
                System.out.println("i = " + i);
                comp = new en_all_computers(c.get(i).getEm_num(), c.get(i).getEmployee_name(), c.get(i).getEmployee_id(), c.get(i).getOffice(), c.get(i).getRoom(),
                        c.get(i).getDepartment(), c.get(i).getSup_department(), c.get(i).getComputer_number(), c.get(i).getMotherboard(), c.get(i).getRam(), c.get(i).getHdd(),
                        c.get(i).getCpu(), c.get(i).getMonitor(), c.get(i).getKeyboard(), c.get(i).getMouse(), c.get(i).getSubwoofer(), c.get(i).getType(),
                        c.get(i).getDate(), c.get(i).getOhda_num(), c.get(i).getNotes());

                row.add(comp);
                System.out.println("after add row");

                tableview.setItems(row);

                System.out.println("after end table");
            }
            for (int j = 0; j < prin.size(); j++) {
                System.out.println(prin.get(j).getEmployee_name());
                ps = new en_all_ps(prin.get(j).getEmployee_name(), prin.get(j).getEmployee_id(), prin.get(j).getPs_num(), prin.get(j).getOffice(), prin.get(j).getRoom(),
                        prin.get(j).getDepartment(), prin.get(j).getSup_department(), prin.get(j).getType(), prin.get(j).getDevice_name(), prin.get(j).getDevice_type(),
                        prin.get(j).getHealth(), prin.get(j).getDate(), prin.get(j).getOh_num(), prin.get(j).getNotes());
                row1.add(ps);

                printer_scanner.setItems(row1);
            }
            for (int i = 0; i < s.size(); i++) {

                ps1 = new en_all_ps(s.get(i).getEmployee_name(), s.get(i).getEmployee_id(), s.get(i).getPs_num(), s.get(i).getOffice(), s.get(i).getRoom(),
                        s.get(i).getDepartment(), s.get(i).getSup_department(), s.get(i).getType(), s.get(i).getDevice_name(), s.get(i).getDevice_type(),
                        s.get(i).getHealth(), s.get(i).getDate(), s.get(i).getOh_num(), s.get(i).getNotes());
                row1.add(ps1);

                printer_scanner.setItems(row1);

            }
            name_search.setValue("");
        } else {
            name_search.setValue("");
            tableview.getItems().clear();
            printer_scanner.getItems().clear();
            setup_start_page();
        }

    }

    @FXML
    public void add_action() throws IOException {

        Parent root = null;
        URL url = null;

        try {

            url = getClass().getResource(nav.getOhda());
            root = FXMLLoader.load(getClass().getResource(nav.getOhda()));
            System.out.println("  fxmlResource = " + nav.getOhda());
            Stage stage = new Stage();
            stage.setTitle("Add Device");
            stage.setScene(new Scene(root));
            stage.showingProperty().addListener((observable, oldValue, showing) -> {

                if (showing) {

                    stage.setMinHeight(stage.getHeight());
                    stage.setMinWidth(stage.getWidth());
                    stage.setMaximized(true);
                    stage.setTitle("Add Device");
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
    public void dep_action() throws IOException {

        Parent root = null;
        URL url = null;

        try {

            url = getClass().getResource(nav.getDepartment());
            root = FXMLLoader.load(getClass().getResource(nav.getDepartment()));
            System.out.println("  fxmlResource = " + nav.getDepartment());
            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.showingProperty().addListener((observable, oldValue, showing) -> {

                if (showing) {

                    stage.setMinHeight(150.0);
                    stage.setMinWidth(600.0);
                    stage.setMaximized(false);
                    stage.setTitle("Department");
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
    public void parts_page() throws Exception {

        Parent root = null;
        URL url = null;

        try {

            url = getClass().getResource(nav.getParts());
            root = FXMLLoader.load(getClass().getResource(nav.getParts()));
            System.out.println("  fxmlResource = " + nav.getParts());
            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.showingProperty().addListener((observable, oldValue, showing) -> {

                if (showing) {

                    stage.setMinHeight(800.0);
                    stage.setMinWidth(1200.0);
                    stage.setMaximized(false);
                    stage.setTitle("Parts");
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

    public void update_computer() throws Exception {

        en_all_computers ceb = (en_all_computers) tableview.getSelectionModel().getSelectedItem();

        Parent root = null;

        try {

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(nav.getUpdate_computer()));

            root = fxmlloader.load();

            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.showingProperty().addListener((observable, oldValue, showing) -> {

                if (showing) {

                    stage.setMinHeight(650.0);
                    stage.setMinWidth(755.0);
                    stage.setMaximized(false);
                    stage.setTitle("Update Computers");
                }
            });
            System.out.println("33...");

            Update_computerController up_co = fxmlloader.getController();
            up_co.setup_page(ceb);
            System.out.println("33...");
            stage.show();
        } catch (Exception ex) {
            System.out.println("Exception on FXMLLoader.load()");
            //System.out.println("  * url: " + url);
            System.out.println("  * path: " + nav.getOhda());
            System.out.println("  * " + ex);
            System.out.println("    ----------------------------------------\n");
            throw ex;
        }

    }

    public void update_ps() throws Exception {

        en_all_ps ceb = (en_all_ps) printer_scanner.getSelectionModel().getSelectedItem();

        Parent root = null;

        try {

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(nav.getUpdate_scanner_printer()));

            root = fxmlloader.load();

            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.showingProperty().addListener((observable, oldValue, showing) -> {

                if (showing) {

                    stage.setMinHeight(410.0);
                    stage.setMinWidth(755.0);
                    stage.setMaximized(false);
                    stage.setTitle("Printer-Scanner Update");
                }
            });
            System.out.println("33...");

            Update_psController up_ps = fxmlloader.getController();
            up_ps.setup_page(ceb);
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

    @FXML
    public void delete_computer_record() throws SQLException {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to permanently delete the field?", ButtonType.YES, ButtonType.CLOSE);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                en_all_computers mxd = (en_all_computers) tableview.getSelectionModel().getSelectedItem();
                ohda o = new ohda();
                devices d = new devices();
                computer c = new computer();
                o.delete_ohda(mxd.getOhda_num());
                int device_id = o.get_device_id(mxd.getOhda_num());
                d.delete_device(device_id);
                c.delete_computer(mxd.getComputer_number());
                tableview.getItems().clear();
                printer_scanner.getItems().clear();
                setup_start_page();
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You did not specify any line to delete", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void delete_ps_record() throws SQLException {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to permanently delete the field?", ButtonType.YES, ButtonType.CLOSE);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                en_all_ps ceb = (en_all_ps) printer_scanner.getSelectionModel().getSelectedItem();
                System.out.println("selected device_name:" + ceb.getDevice_name());
                ohda o = new ohda();
                devices d = new devices();
                printer p = new printer();
                scanner s = new scanner();
                int device_id = o.get_device_id(ceb.getOh_num());
                if (ceb.getType().equals("طابعة")) {
                    System.out.println("selected devic p");
                    o.delete_ohda(ceb.getOh_num());
                    d.delete_device(device_id);
                    p.delete_printer(ceb.getPs_num());
                    System.out.println("حذف طابعة");
                    tableview.getItems().clear();
                    printer_scanner.getItems().clear();
                    setup_start_page();

                } else if (ceb.getType().equals("سكنر")) {
                    System.out.println("selected devic s");
                    o.delete_ohda(ceb.getOh_num());
                    d.delete_device(device_id);
                    s.delete_scanner(ceb.getPs_num());
                    System.out.println("حذف سكنر");
                    tableview.getItems().clear();
                    printer_scanner.getItems().clear();
                    setup_start_page();
                }
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You did not specify any line to delete", ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    private void employee_page(ActionEvent event) throws Exception {
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
    private void setting_page(ActionEvent event) throws Exception {
        Parent root = null;
        URL url = null;

        try {

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(nav.getSettings()));

            root = fxmlloader.load();

            System.out.println("  fxmlResource = " + nav.getSettings());
            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.showingProperty().addListener((observable, oldValue, showing) -> {

                if (showing) {

                    stage.setMinHeight(350.0);
                    stage.setMinWidth(300.0);
                    stage.setMaximized(false);
                    stage.setTitle("Settings");
                }
            });
            SettingsController sc = fxmlloader.getController();
            sc.setup_tables_to_export(tableview, printer_scanner);
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

}

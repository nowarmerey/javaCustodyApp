/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseFunctions.computer;
import databaseFunctions.department;
import databaseFunctions.devices;
import databaseFunctions.employee;
import databaseFunctions.mixed;
import databaseFunctions.ohda;
import databaseFunctions.printer;
import databaseFunctions.scanner;
import databaseFunctions.sup_department;
import entities.en_all_computers;
import entities.en_computer;

import entities.en_department;
import entities.en_devices;
import entities.en_employee;
import entities.en_ohda;
import entities.en_printer;
import entities.en_scanner;
import entities.en_subDep;

import functions.navigation;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ArrayList;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;

import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Smart
 */
public class Ohda_add_PageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button computer_but;
    @FXML
    private Button printer_but;

    @FXML
    private Button scanner_but;
    @FXML
    private BorderPane ap;

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
    private DatePicker t_date;

    @FXML
    private CheckBox check_new;
    @FXML
    private CheckBox check_old;

    navigation nav = new navigation();
    int checkNumber;

    int integre_id_for_employee = 0;
    String name, office, room, dep, subdep, id;
    String motherboard, ram, cpu, hdd, monitor, keyboard, mouse, subwoofer, co_notes;
    String computer_note, printer_note, scanner_note;

    Parent root = null;
    FXMLLoader big_loader;

    LocalDate date;

    boolean checkpoint, check_computer, check_printer, check_scanner;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        checkNumber = 0;
        checkpoint = false;
        check_computer = false;
        check_printer = false;
        check_scanner = false;
        computer_note = null;
        printer_note = null;
        scanner_note = null;
        t_name.setValue("");
        t_id.setValue("");
        t_dep.setValue("");
        t_supdep.setValue("");
        t_room.setValue("");
        t_floor.setValue("");

        try {
            setup();

        } catch (SQLException ex) {
            Logger.getLogger(Ohda_add_PageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // AutoCompleteDecorator.decorate(t_dep);
        TextFields.bindAutoCompletion(t_dep.getEditor(), t_dep.getItems());
        TextFields.bindAutoCompletion(t_supdep.getEditor(), t_supdep.getItems());
        TextFields.bindAutoCompletion(t_floor.getEditor(), t_floor.getItems());
        TextFields.bindAutoCompletion(t_name.getEditor(), t_name.getItems());
        TextFields.bindAutoCompletion(t_id.getEditor(), t_id.getItems());
        TextFields.bindAutoCompletion(t_room.getEditor(), t_room.getItems());
        // TODO
    }

    public void setup() throws SQLException {
        get_departments();
        get_sup_deps();
        get_employee_names();
        get_office_floor();
    }

    public void get_departments() {

        department d = new department();
        ArrayList<en_department> ar = d.show_Departments();
        ObservableList<String> deps = FXCollections.observableArrayList();

        for (int i = 0; i < ar.size(); i++) {
            if(deps.contains(ar.get(i).getD_name())){
                
            }else{
                 deps.add(ar.get(i).getD_name());
            }
           
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
            Alert alert = new Alert(AlertType.ERROR, e.toString(), ButtonType.OK);
            alert.showAndWait();
        }

    }

    public void enable_buttons() {
        computer_but.setDisable(false);
        printer_but.setDisable(false);
        scanner_but.setDisable(false);

    }

    public void disable_buttons() {
        computer_but.setDisable(true);
        printer_but.setDisable(true);
        scanner_but.setDisable(true);
    }

    public void checkbox_old() {
        check_new.setSelected(false);
        enable_buttons();
        if (!check_new.isSelected() && !check_old.isSelected()) {
            disable_buttons();
        }
    }

    public void checkbox_new() {
        check_old.setSelected(false);
        enable_buttons();
        if (!check_new.isSelected() && !check_old.isSelected()) {
            disable_buttons();
        }
    }

    public void computer_page() throws SQLException {
        big_loader = new FXMLLoader();
        checkNumber = 1;
        try {

            root = big_loader.load(getClass().getResource(nav.getComputer()).openStream());
//            Computer_add_pageController controller = big_loader.getController();
//            controller.get_data_fill();
//            System.out.println("addddddddd paaaaaaagggggge123");

        } catch (Exception e) {
            System.out.println(e);
        }

        ap.setCenter(root);

        //  ComboBox computer_motherboard = (ComboBox) big_loader.getNamespace().get("t_motherboard");
        //    TextFields.bindAutoCompletion(computer_motherboard.getEditor(), computer_motherboard.getItems());
        check_computer = true;
    }

    public void printer_page() {
        big_loader = new FXMLLoader();
        checkNumber = 2;
        try {

            root = big_loader.load(getClass().getResource(nav.getPrinter()).openStream());

        } catch (Exception e) {
            System.out.println(e);
        }

        ap.setCenter(root);
        check_printer = true;

    }

    public void scanner_page() {
        big_loader = new FXMLLoader();
        checkNumber = 3;
        try {

            root = big_loader.load(getClass().getResource(nav.getScanner()).openStream());

        } catch (Exception e) {
            System.out.println(e);
        }

        ap.setCenter(root);
        check_scanner = true;
    }

    public void get_employee_data_from_textfields() {
        if (t_name.getValue().equals("") || t_id.getValue().equals("") || t_dep.getValue().equals("") || t_supdep.getValue().equals("") || t_room.getValue().equals("") || t_floor.getValue().equals("") || t_date.getValue().equals("")
                || t_date.getValue() == null) {

            Alert alert = new Alert(AlertType.ERROR, "Ensure that employee information is entered...", ButtonType.OK);
            alert.showAndWait();
            checkpoint = false;
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
            date = t_date.getValue();
            System.out.println("local date: " + date);
        }

    }

//    public int get_department_name() {
//        int dep_num = 0;
//        department dep = new department();
//        return dep_num;
//    }
    public void add_employee() throws SQLException {
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

    public void add_cpomputer() throws SQLException {

        try {

            Computer_add_pageController controller = big_loader.getController();

            ComboBox computer_motherboard = (ComboBox) big_loader.getNamespace().get("t_motherboard");
            ComboBox computer_cpu = (ComboBox) big_loader.getNamespace().get("t_cpu");
            ComboBox computer_ram = (ComboBox) big_loader.getNamespace().get("t_ram");
            ComboBox computer_hdd = (ComboBox) big_loader.getNamespace().get("t_hdd");
            ComboBox computer_keyboard = (ComboBox) big_loader.getNamespace().get("t_keyboard");
            ComboBox computer_mouse = (ComboBox) big_loader.getNamespace().get("t_mouse");
            ComboBox computer_subwoofer = (ComboBox) big_loader.getNamespace().get("t_subwoofer");
            ComboBox computer_monitor = (ComboBox) big_loader.getNamespace().get("t_monitor");
            TextArea computer_notes = (TextArea) big_loader.getNamespace().get("tc_notes");

            if (computer_motherboard.getValue().equals("") || computer_cpu.getValue().equals("") || computer_ram.getValue().equals("") || computer_hdd.getValue().equals("")
                    || computer_keyboard.getValue().equals("") || computer_mouse.getValue().equals("") || computer_subwoofer.getValue().equals("") || computer_monitor.getValue().equals("")) {
                Alert alert = new Alert(AlertType.ERROR, "Make sure to enter your computer data", ButtonType.OK);
                alert.showAndWait();
                checkpoint = false;

                if (alert.getResult() == ButtonType.YES) {
                    //do stuff
                }
            } else {

                checkpoint = true;
                en_computer com = new en_computer();
                computer c = new computer();
                com.setMotherboard(computer_motherboard.getValue().toString());
                com.setCpu(computer_cpu.getValue().toString());
                com.setRam(computer_ram.getValue().toString());
                com.setHdd(computer_hdd.getValue().toString());
                com.setKeyboard(computer_keyboard.getValue().toString());
                com.setMouse(computer_mouse.getValue().toString());
                com.setSubwoofer(computer_subwoofer.getValue().toString());
                com.setMonitor(computer_monitor.getValue().toString());
                computer_note = computer_notes.getText();

                c.add_computer(com);
                System.out.println("computer added....");
                computer_motherboard.setValue("");
                computer_cpu.setValue("");
                computer_ram.setValue("");
                computer_hdd.setValue("");
                computer_keyboard.setValue("");
                computer_mouse.setValue("");
                computer_subwoofer.setValue("");
                computer_monitor.setValue("");
                computer_notes.setText("");
            }

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR, e.toString(), ButtonType.OK);
            alert.showAndWait();
        }

    }

    public void add_printer() throws SQLException {

        try {

            Printer_add_pageController controller = big_loader.getController();

            ComboBox printer_type = (ComboBox) big_loader.getNamespace().get("tp_type");
            System.out.println("tesssssst printer type..... from add printer  .....  " + printer_type.getValue());
            ComboBox printer_name = (ComboBox) big_loader.getNamespace().get("tp_name");
            ComboBox printer_health = (ComboBox) big_loader.getNamespace().get("tp_health");
            TextArea printer_notes = (TextArea) big_loader.getNamespace().get("t_notes_printer");

            if (printer_type.getValue().equals("") || printer_name.getValue().equals("") || printer_health.getValue().equals("")) {
                Alert alert = new Alert(AlertType.ERROR, "Make sure to enter the printer data", ButtonType.OK);
                alert.showAndWait();
                checkpoint = false;

                if (alert.getResult() == ButtonType.YES) {
                    //do stuff
                }
            } else {
                checkpoint = true;

                en_printer pr = new en_printer();
                printer p = new printer();
                pr.setPr_name(printer_name.getValue().toString());
                pr.setPr_type(printer_type.getValue().toString());
                pr.setPr_health(printer_health.getValue().toString());
                printer_note = printer_notes.getText();

                p.add_printer(pr);
                System.out.println("printer added....");
                printer_type.setValue("");
                printer_name.setValue("");
                printer_notes.setText("");
                printer_health.setValue("");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void add_scanner() throws SQLException {

        try {

            Scanner_add_pageController controller = big_loader.getController();

            ComboBox scanner_type = (ComboBox) big_loader.getNamespace().get("ts_type");
            ComboBox scanner = (ComboBox) big_loader.getNamespace().get("ts_name");
            ComboBox scanner_health = (ComboBox) big_loader.getNamespace().get("ts_health");
            TextArea scanner_notes = (TextArea) big_loader.getNamespace().get("t_notes_scanner");

            if (scanner_type.getValue().equals("") || scanner.getValue().equals("") || scanner_health.getValue().equals("")) {
                Alert alert = new Alert(AlertType.ERROR, "Make sure to enter the scanner data", ButtonType.OK);
                alert.showAndWait();
                checkpoint = false;

                if (alert.getResult() == ButtonType.YES) {
                    //do stuff
                }
            } else {
                checkpoint = true;
                en_scanner pr = new en_scanner();
                scanner p = new scanner();
                pr.setSc_name(scanner.getValue().toString());
                pr.setSc_type(scanner_type.getValue().toString());
                pr.setSc_health(scanner_health.getValue().toString());
                scanner_note = scanner_notes.getText();

                p.add_scanner(pr);
                System.out.println("scanner added....");
                scanner_type.setValue("");
                scanner.setValue("");
                scanner_health.setValue("");
                scanner_notes.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void add_devices() throws SQLException {
        int type_number = 0;
        int device_number = 0;
        en_devices ed = new en_devices();
        devices d = new devices();
        computer c = new computer();
        printer p = new printer();
        scanner s = new scanner();
        switch (checkNumber) {
            case 1:

                type_number = 1;
                device_number = c.get_last_computer_number();
                ed.setDe_num(device_number);
                ed.setTy_num(type_number);
                d.add_device(ed);
                break;
            case 2:
                type_number = 2;
                device_number = p.get_last_printer_number();
                ed.setDe_num(device_number);
                ed.setTy_num(type_number);
                d.add_device(ed);
                break;
            case 3:
                type_number = 3;
                device_number = s.get_last_scanner_number();
                ed.setDe_num(device_number);
                ed.setTy_num(type_number);
                d.add_device(ed);
                break;
            default:
                break;
        }
        System.out.println("device added.....");

    }

    public void add_ohda() throws SQLException {

        en_ohda oh = new en_ohda();
        ohda o = new ohda();
        employee e = new employee();
        devices d = new devices();

        date = t_date.getValue();
        id = t_id.getValue();
        integre_id_for_employee = Integer.parseInt(id);
        int employee_number = e.get_employee_id(integre_id_for_employee);

        int device_number = d.get_last_device_number();
        System.out.println("last device number.... is ... " + device_number);
        String note = null;
        switch (checkNumber) {
            case 1:
                note = computer_note;
                break;
            case 2:
                note = printer_note;
                break;
            case 3:
                note = scanner_note;
                break;
            default:
                break;
        }
        System.out.println("last note is ........ " + note);
        oh.setEm_num(employee_number);
        oh.setD_num(device_number);
        oh.setNotes(note);
        oh.setOh_date(date);
        o.add_ohda(oh);
        System.out.println("ohda added.....");

    }

    public void reset_employee_fields() {
        t_name.setValue("");
        t_id.setValue("");
        t_dep.setValue("");
        t_supdep.setValue("");
        t_room.setValue("");
        t_floor.setValue("");
        t_date.setValue(null);
    }

    public void add_info() throws SQLException {

        switch (checkNumber) {
            case 1:
                if (check_new.isSelected()) {
                    System.out.println("add computer ohda for new employee");
                    add_employee();

                    if (check_computer) {
                        add_cpomputer();
                        if (checkpoint) {
                            add_devices();
                            add_ohda();
                            reset_employee_fields();
                            setup();
                            computer_page();
                        } else {
                            Alert alert = new Alert(AlertType.ERROR, "check point is false", ButtonType.OK);
                            alert.showAndWait();
                        }
                    }
                    reset_employee_fields();
                   setup();

                } else {
                    System.out.println("add computer ohda for old employee ...checkbox is not selected");
                    add_cpomputer();
                    if (checkpoint) {
                        add_devices();
                        add_ohda();
                        reset_employee_fields();
                        setup();
                        computer_page();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR, "check point is false", ButtonType.OK);
                        alert.showAndWait();
                    }
                    reset_employee_fields();
                    setup();
                }

                break;
            case 2:

                if (check_new.isSelected()) {
                    System.out.println("add printer ohda for new employee");
                    add_employee();
                    if (check_printer) {
                        add_printer();
                        if (checkpoint) {
                            add_devices();
                            add_ohda();
                            reset_employee_fields();
                            setup();
                            printer_page();
                        } else {
                            Alert alert = new Alert(AlertType.ERROR, "check point is false", ButtonType.OK);
                            alert.showAndWait();
                        }
                    }
                    reset_employee_fields();
                    setup();
                } else {
                    System.out.println("add printer ohda for old employee ...checkbox is not selected");

                    add_printer();
                    if (checkpoint) {
                        add_devices();
                        add_ohda();
                        reset_employee_fields();
                        setup();
                         printer_page();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR, "check point is false", ButtonType.OK);
                        alert.showAndWait();
                    }
                    reset_employee_fields();
                    setup();
                }
                break;
            case 3:
                if (check_new.isSelected()) {
                    System.out.println("add scanner ohda for new employee");
                    add_employee();
                    if (check_scanner) {
                        add_scanner();
                        if (checkpoint) {
                            add_devices();
                            add_ohda();
                            reset_employee_fields();
                            setup();
                            scanner_page();
                        } else {
                            Alert alert = new Alert(AlertType.ERROR, "check point is false", ButtonType.OK);
                            alert.showAndWait();
                        }

                    }
                    reset_employee_fields();
                   setup();
                } else {
                    System.out.println("add scanner ohda for old employee ...checkbox is not selected");
                    add_scanner();
                    if (checkpoint) {
                        add_devices();
                        add_ohda();
                        reset_employee_fields();
                        setup();
                        scanner_page();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR, "check point is false", ButtonType.OK);
                        alert.showAndWait();
                    }
                    reset_employee_fields();
                    setup();
                }
                break;
            default:
                Alert alert = new Alert(AlertType.ERROR, "First, choose the type of device", ButtonType.OK);
                alert.showAndWait();
                break;
        }

    }

}

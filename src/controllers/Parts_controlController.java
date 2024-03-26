/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import databaseFunctions.archive;
import databaseFunctions.mixed;
import databaseFunctions.parts;
import entities.en_all_parts;

import entities.en_archive;
import entities.en_parts;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Smart
 */
public class Parts_controlController implements Initializable {

    @FXML
    private TableView sent_table;

    @FXML
    private TableView recived_table;

    @FXML
    private TableColumn<en_all_parts, Date> cs_date;

    @FXML
    private TableColumn<en_all_parts, String> cs_notes;

    @FXML
    private TableColumn<en_all_parts, Integer> cs_count;

    @FXML
    private TableColumn<en_all_parts, String> cs_name;

    @FXML
    private TableColumn<en_all_parts, String> cs_type;

    @FXML
    private TableColumn<en_all_parts, Integer> cs_num;

    @FXML
    private TableColumn<en_parts, Date> cr_date;

    @FXML
    private TableColumn<en_parts, Integer> cr_count;

    @FXML
    private TableColumn<en_parts, String> cr_name;

    @FXML
    private TableColumn<en_parts, String> cr_type;

    @FXML
    private TableColumn<en_parts, Integer> cr_num;

    @FXML
    private TextField s_type;

    @FXML
    private TextField s_name;

    @FXML
    private TextField s_count;

    @FXML
    private DatePicker s_date;

    @FXML
    private TextArea s_notes;

    @FXML
    private Button s_but;

    @FXML
    private TextArea show_notes;

    @FXML
    private TextField r_type;

    @FXML
    private TextField r_name;

    @FXML
    private TextField r_count;

    @FXML
    private DatePicker r_date;

    @FXML
    private Button r_but;
    @FXML
    private Button bu_ref;

    @FXML
    private TextField s_search;

    @FXML
    private TextField r_search;

    ObservableList<en_all_parts> row1;
    ObservableList<en_parts> row2;

    en_all_parts eap;
    en_parts ep, v_ap;
    int bool;

    public Parts_controlController() {
        this.row2 = FXCollections.observableArrayList();
        this.row1 = FXCollections.observableArrayList();
    }

    public void setup_columns_for_tables() {
        System.out.println("start setup 1....");

        cs_num.setCellValueFactory(new PropertyValueFactory<>("ID"));
        cs_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        cs_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cs_count.setCellValueFactory(new PropertyValueFactory<>("count"));
        cs_notes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        cs_date.setCellValueFactory(new PropertyValueFactory<>("sent_date"));

        cr_num.setCellValueFactory(new PropertyValueFactory<>("ID"));
        cr_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        cr_name.setCellValueFactory(new PropertyValueFactory<>("part_name"));
        cr_count.setCellValueFactory(new PropertyValueFactory<>("parts_count"));
        cr_date.setCellValueFactory(new PropertyValueFactory<>("recived_date"));

        System.out.println("end setup 3....");
        sent_table.setItems(row1);

        System.out.println("adde row1....");
        recived_table.setItems(row2);
        System.out.println("added row ....");
    }

    public void setup_page() throws SQLException {
        parts p = new parts();
        archive a = new archive();
        mixed m = new mixed();

        ArrayList<en_all_parts> allp = m.get_all_archive();
        ArrayList<en_parts> ap = p.show_parts();

        for (int i = 0; i < allp.size(); i++) {
            System.out.println("test....");
            eap = new en_all_parts(allp.get(i).getID(), allp.get(i).getType(),
                    allp.get(i).getName(), allp.get(i).getCount(), allp.get(i).getNotes(), allp.get(i).getSent_date());

            row1.add(eap);
            sent_table.setItems(row1);
        }
        for (int i = 0; i < ap.size(); i++) {
            System.out.println("test....2");
            ep = new en_parts(ap.get(i).getID(), ap.get(i).getType(), ap.get(i).getPart_name(), ap.get(i).getParts_count(), ap.get(i).getRecived_date());

            row2.add(ep);
            recived_table.setItems(row2);
        }

    }

    public void add_part() throws SQLException {
        en_parts part = new en_parts();
        parts pa = new parts();
        if (r_name.getText().equals("") || r_type.getText().equals("") || r_count.getText().equals("") || r_date.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The data entered is incorrect", ButtonType.OK);
            alert.showAndWait();

        } else {
            part.setPart_name(r_name.getText());
            part.setType(r_type.getText());
            part.setParts_count(Integer.parseInt(r_count.getText()));
            java.util.Date date = java.sql.Date.valueOf(r_date.getValue());
            part.setRecived_date(date);

            pa.add_parts(part);
            clear_fields();
            System.out.println("parts added");
            sent_table.getItems().clear();
            recived_table.getItems().clear();
            setup_page();

            r_name.setText("");
            r_type.setText("");
            r_count.setText("");
            r_date.setValue(null);
        }

    }

    public void add_archive() throws SQLException {
        en_archive c = new en_archive();
        archive ar = new archive();
        parts p = new parts();
        if (s_name.getText().equals("") || s_type.getText().equals("") || s_count.getText().equals("") || s_date.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The data entered is incorrect", ButtonType.OK);
            alert.showAndWait();

        } else {
            c.setPart_id(v_ap.getID());
            int counts = Integer.parseInt(s_count.getText());
            int first_counts = v_ap.getParts_count();
            c.setParts_count(counts);
            c.setNotes(s_notes.getText());
            c.setSent_date(s_date.getValue());
            if (counts < v_ap.getParts_count()) {
                v_ap.setParts_count(first_counts - counts);
                ar.add_archive(c);
                p.update_parts(v_ap);
                clear_fields();
                System.out.println("archive added");
                sent_table.getItems().clear();
                recived_table.getItems().clear();
                setup_page();

                s_name.setText("");
                s_type.setText("");
                s_count.setText("");
                s_date.setValue(null);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "There is not enough quantity", ButtonType.OK);
                alert.showAndWait();
            }

        }

    }

    public void show_parts_informations() {
        show_notes.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        if (bool == 1) {
            en_parts mxd = (en_parts) recived_table.getSelectionModel().getSelectedItem();
            show_notes.setText("Type : " + mxd.getType() + "\n\n" + " Name : " + mxd.getPart_name() + "\n\n" + "Quantity : " + mxd.getParts_count() + "\n\n Date : " + mxd.getRecived_date());
        } else if (bool == 2) {
            en_all_parts mxd = (en_all_parts) sent_table.getSelectionModel().getSelectedItem();
            show_notes.setText("Type : " + mxd.getType() + "\n\n" + " Name : " + mxd.getName() + "\n\n" + "Quantity : " + mxd.getCount() + "\n\n" + "Notes : " + mxd.getNotes() + "\n\n Date : " + mxd.getSent_date());
        }

    }

    public void clear_fields() {
        r_name.setText("");
        r_type.setText("");
        r_count.setText("");
        r_date.setValue(null);

        s_name.setText("");
        s_type.setText("");
        s_count.setText("");
        s_date.setValue(null);

        s_notes.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            setup_columns_for_tables();
            setup_page();
        } catch (Exception e) {
        }

        recived_table.setOnMouseClicked(event -> {
            bool = 1;
            if (event.getClickCount() == 2) {
                try {

                } catch (Exception ex) {
                    Logger.getLogger(Start_PageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            show_parts_informations();
            v_ap = (en_parts) recived_table.getSelectionModel().getSelectedItem();
            s_type.setText(v_ap.getType());
            s_name.setText(v_ap.getPart_name());

        });
        sent_table.setOnMouseClicked(event -> {
            bool = 2;
            if (event.getClickCount() == 2) {
                try {

                } catch (Exception ex) {
                    Logger.getLogger(Start_PageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            show_parts_informations();

        });
        FilteredList<en_parts> filteredData = new FilteredList<>(row2, e -> true);
        r_search.setOnKeyReleased(e -> {
            r_search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate(part -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(part.getID()).contains(newValue)) {
                        return true;
                    } else if (part.getPart_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            
                        return true;
                    
                    } else if (part.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (String.valueOf(part.getParts_count()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }

                    return false;
                });
            });
            SortedList<en_parts> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(recived_table.comparatorProperty());
            recived_table.setItems(sortedData);

        });
        FilteredList<en_all_parts> filteredData2 = new FilteredList<>(row1, e -> true);
        s_search.setOnKeyReleased(e -> {
            s_search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData2.setPredicate(part -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(part.getID()).contains(newValue)) {
                        return true;
                    } else if (part.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (part.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (String.valueOf(part.getCount()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (part.getNotes().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }

                    return false;
                });
            });
            SortedList<en_all_parts> sortedData = new SortedList<>(filteredData2);
            sortedData.comparatorProperty().bind(sent_table.comparatorProperty());
            sent_table.setItems(sortedData);

        });
    }
}

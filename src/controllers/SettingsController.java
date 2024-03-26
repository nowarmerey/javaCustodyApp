/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import functions.ExcelExport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Smart
 */
public class SettingsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private TableView table_computer, table_ps;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void backup(ActionEvent event) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            File f1 = new File("C:/backup");
            //Creating a folder using mkdirs() method  
            boolean bool2 = f1.mkdirs();
            if (bool2) {
                System.out.println("Folder is created successfully");
            } else {
                System.out.println("Error Found!");
            }
            is = new FileInputStream("CUSTODY.accdb");
            os = new FileOutputStream("C:/backup/CUSTODY.accdb");
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buf)) > 0) {
                os.write(buf, 0, bytesRead);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            is.close();
            os.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Database backed up", ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    private void restore(ActionEvent event) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            File f1 = new File("C:/backup/1");
            //Creating a folder using mkdirs() method  
            boolean bool2 = f1.mkdirs();
            if (bool2) {
                System.out.println("Folder is created successfully");
            } else {
                System.out.println("Error Found!");
            }

            is = new FileInputStream("CUSTODY.accdb");
            os = new FileOutputStream("C:/backup/1/CUSTODY__.accdb");
            byte[] buf1 = new byte[1024];
            int bytesRead1;
            while ((bytesRead1 = is.read(buf1)) > 0) {
                os.write(buf1, 0, bytesRead1);
            }

            is = new FileInputStream("C:/backup/CUSTODY.accdb");
            os = new FileOutputStream("CUSTODY.accdb");
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buf)) > 0) {
                os.write(buf, 0, bytesRead);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            is.close();
            os.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Backup has been successfully restored", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void setup_tables_to_export(TableView c, TableView p) {
        table_computer = c;
        table_ps = p;
    }

    @FXML
    private void export_computer(ActionEvent event) {
        ExcelExport ex = new ExcelExport();
        ex.export_computers(table_computer);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Done, The excel file is ready", ButtonType.OK);
        alert.showAndWait();

    }

    @FXML
    private void export_printer(ActionEvent event) {
        ExcelExport ex = new ExcelExport();
        ex.export_printers(table_ps);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Done, The excel file is ready", ButtonType.OK);
        alert.showAndWait();
    }

    

}

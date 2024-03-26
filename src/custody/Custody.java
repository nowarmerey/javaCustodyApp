/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custody;


import functions.navigation;
import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Smart
 */
public class Custody extends Application {
     navigation nav= new navigation();
    private final int PREF_MIN_WIDTH = 500;
    private final int PREF_MIN_HEIGHT = 300;
    
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
     try{
            Parent root = FXMLLoader.load(getClass().getResource(nav.getLogin()));
        
        Scene scene = new Scene(root);
       // scene.getStylesheets().add("/views/style_add_file.css");
      //scene.getStylesheets().add("views/style_add_file.css");
        stage.setScene(scene);
        stage.showingProperty().addListener((observable, oldValue, showing) -> {
            
                if(showing) {
                 
//                    stage.setMinHeight(stage.getHeight());
//                    stage.setMinWidth(stage.getWidth());
                    stage.setMaximized(false);
                    stage.setTitle("Login");
                }
            });
      //  stage.setAlwaysOnTop(true);
        stage.show();
     } catch(Exception e){
         System.out.println("aloooo "+ e);
     }
     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }
    
}

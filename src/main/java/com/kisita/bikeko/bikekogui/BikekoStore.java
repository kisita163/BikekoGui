/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kisita.bikeko.bikekogui;

import com.kisita.bikeko.bikekogui.controller.FXMLItemsController;
import com.kisita.bikeko.bikekogui.firebase.Firebase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author HuguesKi
 */
public class BikekoStore extends Application {

    private Firebase firebase;

    FXMLItemsController tableViewController = null;


    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/FXMLItemsView.fxml"));
        AnchorPane root = loader.load();

        tableViewController = loader.getController();
        tableViewController.setStage(stage);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        //Image icon = new Image(getClass().getResourceAsStream("pictures/icon.png"));
        //stage.getIcons().add(icon);
        firebase = Firebase.newInstance(this);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public FXMLItemsController getTableViewController() {
        return tableViewController;
    }
}

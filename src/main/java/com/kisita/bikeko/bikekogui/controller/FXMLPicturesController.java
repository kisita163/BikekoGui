/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kisita.bikeko.bikekogui.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HuguesKi
 */
public class FXMLPicturesController implements Initializable {
  
    @FXML
    private ImageView  imageField;
    
    private ArrayList<String> itemPictures;
    
    private Stage mStage;
    
    private int currentImage = 0;

    public void setStage(Stage stage) {
        this.mStage = stage;
    }
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setImageView();
    }    
    
    public FXMLPicturesController(ArrayList<String> pictures){
        itemPictures = pictures;
    }
    
    private void setImageView(){
        String imageSource = itemPictures.get(currentImage);
        //imageField.setImage(new Image(imageSource));
        final ImageLoader load = new ImageLoader(imageSource);
        load.setOnSucceeded(new EventHandler() {
            @Override
            public void handle(Event event) {
                imageField.setImage(load.getValue());
            }
        });
        Thread th = new Thread(load);
        th.setDaemon(true);
        th.start();
    }
    
    public void addNewImage(){
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showOpenDialog(this.mStage);
        if (file != null) {
            try {
                String path = file.toURI().toString();
                itemPictures.add(path);
                imageField.setImage(new Image(path));
            } catch (Exception ex) {
                Logger.getLogger(FXMLPicturesController.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        currentImage++;
    }

    public void setItemPictures(ArrayList<String> itemPictures) {
        this.itemPictures = itemPictures;
    }
    
    @FXML
    void onBack(ActionEvent event) {
        currentImage--;
        if(currentImage < 0)
            return;
        imageField.setImage(new Image(itemPictures.get(currentImage)));
    }

    @FXML
    void onForward(ActionEvent event) {
        currentImage++;
        if(currentImage > itemPictures.size()-1)
            return;
        imageField.setImage(new Image(itemPictures.get(currentImage)));
    }
}

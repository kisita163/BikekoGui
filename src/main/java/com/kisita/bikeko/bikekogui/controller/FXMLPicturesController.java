/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kisita.bikeko.bikekogui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author HuguesKi
 */
public class FXMLPicturesController implements Initializable {
  
    @FXML
    private ImageView  imageField;
    
    private ArrayList<String> itemPictures;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setImageView("");
    }    
    
    
    private void setImageView(String source){
        String imageSource = itemPictures.get(0);//"https://firebasestorage.googleapis.com/v0/b/glam-afc14.appspot.com/o/items%2Fbaby%2Fbaby_new.png?alt=media&token=c3b5780c-0595-453f-839c-732703c16ffe";
        imageField.setImage(new Image(imageSource));
        
    }
    
    public void addNewImage(){
        
    }

    public void setItemPictures(ArrayList<String> itemPictures) {
        this.itemPictures = itemPictures;
    }
    
    
    
    /*public void centerImage() {

        Image img = imageField.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageField.getFitWidth() / img.getWidth();
            double ratioY = imageField.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if (ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageField.setX((imageField.getFitWidth() - w) / 2);
            imageField.setY((imageField.getFitHeight() - h) / 2);

        }
    }*/
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kisita.bikeko.bikekogui.controller;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

/**
 *
 * @author HuguesKi
 */
public class ImageLoader extends Task<Image>{
    
    private String url;
    
    public ImageLoader(String url){
        this.url = url;
    }

    @Override
    protected Image call() throws Exception {
        return new Image(url);
    }
}

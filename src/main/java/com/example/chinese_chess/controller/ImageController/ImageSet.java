package com.example.chinese_chess.controller.ImageController;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ImageSet {
    public static ImageView createImageView(String image_location, double width, double height) {
        Image image = new Image(image_location);
        ImageView image_view = new ImageView(image);
        image_view.setFitWidth(width);
        image_view.setFitHeight(height);
        image_view.setPreserveRatio(true);
        image_view.setSmooth(true);
        image_view.setCache(true);
        return image_view;
    }

    public static ImageView createImageView(String image_location, boolean background){
        Image image = new Image(image_location);
        ImageView image_view = new ImageView(image);
        image_view.relocate(50,50);
        if(background){
            image_view.setFitWidth(45*9);
            image_view.setFitHeight(45*10);
        }
        else{
            image_view.setFitWidth(45);
            image_view.setFitWidth(45);
            image_view.setX(0);
            image_view.setY(0);
        }
        image_view.setPreserveRatio(true);
        image_view.setSmooth(true);
        image_view.setCache(true);
        return image_view;
    }
}

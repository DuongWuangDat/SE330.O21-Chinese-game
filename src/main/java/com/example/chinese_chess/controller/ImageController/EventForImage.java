package com.example.chinese_chess.controller.ImageController;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EventForImage {

    public static void addDragAndDrop(ImageView image_view, double org_sceneX, double org_sceneY, double org_translateX, double org_translateY){
        image_view.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                org_sceneX = e.getSceneX();
                org_sceneY = e.getSceneY();
                org_translateX = ((ImageView)(e.getSource())).getTranslateX();
                org_translateY = ((ImageView)(e.getSource())).getTranslateY();
            }
        });
        image_view.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                double end_sceneX = e.getSceneX();
                double end_sceneY = e.getSceneY();
                ((ImageView)(e.getSource())).setTranslateX(org_translateX);
                ((ImageView)(e.getSource())).setTranslateY(org_translateY);
            }
        });
        image_view.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                double offsetX = e.getSceneX() - org_sceneX;
                double offsetY = e.getSceneY() - org_sceneY;
                double new_translateX = org_translateX + offsetX;
                double new_translateY = org_translateY + offsetY;
                ((ImageView)(e.getSource())).setTranslateX(new_translateX);
                ((ImageView)(e.getSource())).setTranslateY(new_translateY);
            }
        });
    }
}

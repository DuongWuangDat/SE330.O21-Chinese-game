package com.example.chinese_chess.controller.ImageController;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EventForImage {

    public static void addDragAndDrop(ImageView image_view,ImageProperties imageProperties ){
        image_view.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                imageProperties.org_sceneX = e.getSceneX();
                imageProperties.org_sceneY = e.getSceneY();
                imageProperties.org_translateX = ((ImageView)(e.getSource())).getTranslateX();
                imageProperties.org_translateY = ((ImageView)(e.getSource())).getTranslateY();
            }
        });
        image_view.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                double end_sceneX = e.getSceneX();
                double end_sceneY = e.getSceneY();
                ((ImageView)(e.getSource())).setTranslateX(imageProperties.org_translateX);
                ((ImageView)(e.getSource())).setTranslateY(imageProperties.org_translateY);
            }
        });
        image_view.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                double offsetX = e.getSceneX() - imageProperties.org_sceneX;
                double offsetY = e.getSceneY() - imageProperties.org_sceneY;
                double new_translateX = imageProperties.org_translateX + offsetX;
                double new_translateY = imageProperties.org_translateY + offsetY;
                ((ImageView)(e.getSource())).setTranslateX(new_translateX);
                ((ImageView)(e.getSource())).setTranslateY(new_translateY);
            }
        });
    }
}

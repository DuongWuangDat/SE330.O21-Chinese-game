package com.example.chinese_chess.model.Pieces;
import com.example.chinese_chess.Image_Strings.Image_Locations;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class Advisor extends Piece {
    public Advisor(double x, double y, double alpha, char side, Pane pane) throws MalformedURLException {
        super(x,y,alpha,side,pane);
        if(side == 'R'){
            image_view = createImageView(Image_Locations.red_advisorImage, 45, 45);
            image_view.setFitWidth(45);
            image_view.setFitHeight(45);
        }
        else{
            image_view = createImageView(Image_Locations.black_advisorImage, 45, 45);
            image_view.setFitWidth(45);
            image_view.setFitHeight(45);
        }
        setupImageView();
    }
    public String toString(){
        return "Ad";
    }

    @Override
    public ArrayList<Point2D> getLegalMoves(Piece[][] dataBoard) {
        ArrayList<Point2D> ret = new ArrayList<Point2D>();
        int x = this.getPaneX();
        int y = this.getPaneY();
            if (this.getSide() == 'R') {
                if (x == 3 || x == 5) { // Advisor at corner
                    addToList(4, 8, this, dataBoard, ret);
                } else { // Advisor at center
                    addToList(3, 9, this, dataBoard, ret);
                    addToList(3, 7, this, dataBoard, ret);
                    addToList(5, 9, this, dataBoard, ret);
                    addToList(5, 7, this, dataBoard, ret);
                }
            } else {
                if (x == 3 || x == 5) { // Advisor at corner
                    addToList(4, 1, this, dataBoard, ret);
                } else { // Advisor at center
                    addToList(3, 0, this, dataBoard, ret);
                    addToList(3, 2, this, dataBoard, ret);
                    addToList(5, 0, this, dataBoard, ret);
                    addToList(5, 2, this, dataBoard, ret);
                }
            }
            return ret;
    }

}

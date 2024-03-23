package com.example.chinese_chess.model.Pieces;
import com.example.chinese_chess.Image_Strings.Image_Locations;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Soldier extends  Piece {
    public Soldier(double x, double y, double alpha, char side, Pane pane){
        super(x,y,alpha,side,pane);
        if(side == 'R'){
            image_view = createImageView(Image_Locations.red_soldierImage, 45, 45);
            image_view.setFitWidth(45);
            image_view.setFitHeight(45);
        }
        else{
            image_view = createImageView(Image_Locations.black_soldierImage, 45, 45);
            image_view.setFitWidth(45);
            image_view.setFitHeight(45);
        }
        setupImageView();
    }

    public String toString(){
        return "So";
    }



    @Override
    public ArrayList<Point2D> getLegalMoves(Piece[][] dataBoard){
        ArrayList<Point2D> ret = new ArrayList<Point2D>();
        int x = this.getPaneX();
        int y = this.getPaneY();

        // Soldier Behavior

            if(this.getSide() == 'R'){
                if(y <= 4){ // Crossed river
                    if(x - 1 >= 0){
                        addToList(x - 1, y, this, dataBoard, ret);
                    }
                    if(x + 1 <= 8){
                        addToList(x + 1, y, this, dataBoard, ret);
                    }
                }
                if(y - 1 >= 0){
                    addToList(x, y - 1, this, dataBoard, ret);
                }
            }
            else{ //Black side
                if(y >= 5){ // Crossed river
                    if(x - 1 >= 0){
                        addToList(x - 1, y, this, dataBoard, ret);
                    }
                    if(x + 1 <= 8){
                        addToList(x + 1, y, this, dataBoard, ret);
                    }
                }
                if(y + 1 <= 9){
                    addToList(x, y + 1, this, dataBoard, ret);
                }
            }
            return ret;

    }
}

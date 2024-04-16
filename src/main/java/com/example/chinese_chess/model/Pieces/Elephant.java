package com.example.chinese_chess.model.Pieces;
import com.example.chinese_chess.Image_Strings.Image_Locations;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class Elephant extends Piece {
    public Elephant(double x, double y, double alpha, char side, Pane pane) throws MalformedURLException {
        super(x,y,alpha,side,pane);
        if(side == 'R'){
            image_view = createImageView(Image_Locations.red_elephantImage);
            image_view.setFitWidth(45);
            image_view.setFitHeight(45);
        }
        else{
            image_view = createImageView(Image_Locations.black_elephantImage);
            image_view.setFitWidth(45);
            image_view.setFitHeight(45);
        }
        setupImageView();
    }

    public String toString(){
        return "El";
    }
    @Override
    public ArrayList<Point2D> getLegalMoves( Piece[][] dataBoard){
        ArrayList<Point2D> ret = new ArrayList<Point2D>();
        int x = this.getPaneX();
        int y = this.getPaneY();


            if(this.getSide() == 'R'){
                if(y + 2  <= 9){ // Down
                    if(x + 2 <= 8 && noPiece(x + 1, y + 1, dataBoard)){ // Bottom right
                        addToList(x + 2, y + 2, this, dataBoard, ret);
                    }
                    if(x - 2 >= 0 && noPiece(x - 1, y + 1, dataBoard)){ // Bottom left
                        addToList(x - 2, y + 2, this, dataBoard, ret);
                    }

                }
                if(y - 2 >= 5){ // Up
                    if(x + 2 <= 8 && noPiece(x + 1, y - 1, dataBoard)){ // Upper right
                        addToList(x + 2, y - 2, this, dataBoard, ret);
                    }
                    if(x - 2 >= 0 && noPiece(x - 1, y - 1, dataBoard)){ // Upper left
                        addToList(x - 2, y - 2, this, dataBoard, ret);
                    }
                }
                return ret;
            }
            else{
                if(y + 2  <= 4){ // Down
                    if(x + 2 <= 8 && noPiece(x + 1, y + 1, dataBoard)){ // Bottom right
                        addToList(x + 2, y + 2, this, dataBoard, ret);
                    }
                    if(x - 2 >= 0 && noPiece(x - 1, y + 1, dataBoard)){ // Bottom left
                        addToList(x - 2, y + 2, this, dataBoard, ret);
                    }

                }
                if(y - 2 >= 0){ // Up
                    if(x + 2 <= 8 && noPiece(x + 1, y - 1, dataBoard)){ // Upper right
                        addToList(x + 2, y - 2, this, dataBoard, ret);
                    }
                    if(x - 2 >= 0 && noPiece(x - 1, y - 1, dataBoard)){ // Upper left
                        addToList(x - 2, y - 2, this, dataBoard, ret);
                    }
                }
                return ret;
            }
    }
}

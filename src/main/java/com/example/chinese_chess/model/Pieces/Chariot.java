package com.example.chinese_chess.model.Pieces;


import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Chariot extends Piece {

    public Chariot(double x, double y, double alpha, char side, Pane pane){
        super(x,y,alpha,side,pane);
        if(side == 'R'){
            image_view = createImageView("com/example/chinese_chess/Resource/img/CChessBoard.png", 45, 45);
            image_view.setFitWidth(45);
            image_view.setFitHeight(45);
        }
        else{
            image_view = createImageView("com/example/chinese_chess/Resource/img/CChessBoard.png", 45, 45);
            image_view.setFitWidth(45);
            image_view.setFitHeight(45);
        }
        setupImageView();
    }

    public String toString(){
        return "Ch";
    }
    @Override
    public ArrayList<Point2D> getLegalMoves(Piece[][] dataBoard){
        ArrayList<Point2D> ret = new ArrayList<Point2D>();
        int x = this.getPaneX();
        int y = this.getPaneY();



            int x2 = x + 1;
            while(x2 <= 8){
                addToList(x2, y, this, dataBoard, ret);
                if(!noPiece(x2, y, dataBoard)){
                    break;
                }
                x2++;
            }
            x2 = x - 1;
            while(x2 >= 0){
                addToList(x2, y, this, dataBoard, ret);
                if(!noPiece(x2, y, dataBoard)){
                    break;
                }
                x2--;
            }
            int y2 = y + 1;
            while(y2 + 1 <= 9){
                addToList(x, y2 + 1, this, dataBoard, ret);
                if(!noPiece(x, y2 + 1, dataBoard)){
                    break;
                }
                y2++;
            }
            y2 = y - 1;
            while(y2 >= 0){
                addToList(x, y2, this, dataBoard, ret);
                if(!noPiece(x, y2, dataBoard)){
                    break;
                }
                y2--;
            }
            return ret;

    }
}

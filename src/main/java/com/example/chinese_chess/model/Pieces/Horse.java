package com.example.chinese_chess.model.Pieces;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Horse extends Piece {
    public Horse(double x, double y, double alpha, char side, Pane pane){
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
        return "Ho";
    }
    @Override
    public ArrayList<Point2D> getLegalMoves( Piece[][] dataBoard){
        ArrayList<Point2D> ret = new ArrayList<Point2D>();
        int x = this.getPaneX();
        int y = this.getPaneY();

            if(y + 2  <= 9 && noPiece(x, y + 1, dataBoard)){ // Down
                if(x + 1 <= 8){ // Bottom right
                    addToList(x + 1, y + 2, this, dataBoard, ret);
                }
                if(x - 1 >= 0){ // Bottom left
                    addToList(x - 1, y + 2, this, dataBoard, ret);
                }

            }
            if(y - 2 >= 0 && noPiece(x, y - 1, dataBoard)){ // Up
                if(x + 1 <= 8){ // Upper right
                    addToList(x + 1, y - 2, this, dataBoard, ret);
                }
                if(x - 1 >= 0){ // Upper left
                    addToList(x - 1, y - 2, this, dataBoard, ret);
                }

            }
            if(x + 2 <= 8 && noPiece(x + 1, y, dataBoard)){ // Right
                if(y + 1 <= 9){ // Right down
                    addToList(x + 2, y + 1, this, dataBoard, ret);
                }
                if(y - 1 >= 0){ // Right up
                    addToList(x + 2, y - 1, this, dataBoard, ret);
                }

            }
            if(x - 2 >= 0 && noPiece(x - 1, y, dataBoard)){ // Left
                if(y + 1 <= 9){ // Left down
                    addToList(x - 2, y + 1, this, dataBoard, ret);
                }
                if(y - 1 >= 0){ // Left up
                    addToList(x - 2, y - 1, this, dataBoard, ret);
                }
            }
            return ret;

    }
}

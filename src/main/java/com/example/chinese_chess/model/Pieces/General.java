package com.example.chinese_chess.model.Pieces;
import com.example.chinese_chess.Image_Strings.Image_Locations;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class General extends Piece  {
    public General(double x, double y, double alpha, char side, Pane pane) throws MalformedURLException {
        super(x,y,alpha,side,pane);
        if(side == 'R'){
            image_view = createImageView(Image_Locations.red_generalImage, 45, 45);
            image_view.setFitWidth(45);
            image_view.setFitHeight(45);
        }
        else{
            image_view = createImageView(Image_Locations.black_generalImage, 45, 45);
            image_view.setFitWidth(45);
            image_view.setFitHeight(45);
        }
        setupImageView();
    }

    public String toString(){
        return "Ge";
    }
    @Override
    public ArrayList<Point2D> getLegalMoves(Piece[][] dataBoard) {
        ArrayList<Point2D> ret = new ArrayList<Point2D>();
        int x = this.getPaneX();
        int y = this.getPaneY();


        if (this.getSide() == 'R') {
            if (x - 1 >= 3) {
                addToList(x - 1, y, this, dataBoard, ret);
            }
            if (x + 1 <= 5) {
                addToList(x + 1, y, this, dataBoard, ret);
            }
            if (y - 1 >= 7) {
                addToList(x, y - 1, this, dataBoard, ret);
            }
            if (y + 1 <= 9) {
                addToList(x, y + 1, this, dataBoard, ret);
            }
        } else {
            if (x - 1 >= 3) {
                addToList(x - 1, y, this, dataBoard, ret);
            }
            if (x + 1 <= 5) {
                addToList(x + 1, y, this, dataBoard, ret);
            }
            if (y - 1 >= 0) {
                addToList(x, y - 1, this, dataBoard, ret);
            }
            if (y + 1 <= 2) {
                addToList(x, y + 1, this, dataBoard, ret);
            }
        }

        boolean flyingGeneral = false;
        int x2 = x + 1;
        while (x2 <= 8) {
            if (!noPiece(x2, y, dataBoard)) {
                if (dataBoard[y][x2] instanceof General) {
                    addToList(x2, y, this, dataBoard, ret);
                    flyingGeneral = true;
                }
                break;
            }
            x2++;
        }
        if (!flyingGeneral) {
            x2 = x - 1;
            while (x2 >= 0) {
                if (!noPiece(x2, y, dataBoard)) {
                    if (dataBoard[y][x2] instanceof General) {
                        addToList(x2, y, this, dataBoard, ret);
                        flyingGeneral = true;
                    }
                    break;
                }
                x2--;
            }
        }
        int y2 = y + 1;
        if (!flyingGeneral) {
            while (y2 <= 9) {
                if (!noPiece(x, y2, dataBoard)) {
                    if (dataBoard[y2][x] instanceof General) {
                        addToList(x, y2, this, dataBoard, ret);
                        flyingGeneral = true;
                    }
                    break;
                }
                y2++;
            }
        }
        if (!flyingGeneral) {
            y2 = y - 1;
            while (y2 >= 0) {
                if (!noPiece(x, y2, dataBoard)) {
                    if (dataBoard[y2][x] instanceof General) {
                        addToList(x, y2, this, dataBoard, ret);
                    }
                    break;
                }
                y2--;
            }
        }
        return ret;
    }
}

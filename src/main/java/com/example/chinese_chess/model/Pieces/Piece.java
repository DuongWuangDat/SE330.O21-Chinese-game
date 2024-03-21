package com.example.chinese_chess.model.Pieces;

import com.example.chinese_chess.controller.ImageController.*;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Piece{
    double x;
    double y;
    int pane_x;
    int pane_y;
    double alpha;
    char side; // "R" or "B"
    Pane pane;
    final double RATIO = 0.6;
    ImageView image_view;
    ImageProperties imageProperties;
    double moveX, moveY;

    public Piece(double x, double y, double alpha, char side, Pane pane){
        this.x = x;
        this.y = y;
        this.alpha = alpha;
        this.side = side;
        this.pane = pane;
    }

    // Accessors
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public int getPaneX(){
        return pane_x;
    }
    public int getPaneY(){
        return pane_y;
    }
    public Pane getPane(){
        return pane;
    }
    public double getAlpha(){
        return alpha;
    }
    public ImageView getImageView(){
        return image_view;
    }
    public char getSide(){
        return side;
    }

    // Modifiers
    public void setPaneXY(int x, int y){
        this.pane_x = x;
        this.pane_y = y;
    }
    public void setAlpha(double alpha){
        this.alpha = alpha;
        image_view.setOpacity(alpha);
    }

    // Helper Functions
    public ImageView createImageView(String image_location, double width, double height){
        return ImageSet.createImageView(image_location,width,height);
    }

    public void setupImageView(){
        image_view.setOpacity(alpha);
        image_view.setMouseTransparent(true);
        pane.getChildren().add(this.image_view);
        image_view.relocate(x * RATIO, y * RATIO);
    }

    public void updateImageView(){
        pane.getChildren().remove(this.image_view);
        image_view.setOpacity(1.0);
        pane.getChildren().add(this.image_view);
    }

    public void addDragAndDrop(){
        EventForImage.addDragAndDrop(this.image_view,this.imageProperties);
    }

    // Override toString
    public String toString(){
        return "";
    }
    public ArrayList<Point2D> getLegalMoves( Piece[][] dataBoard){
        return null;
    }

    public  void addToList(int x, int y, Piece piece, Piece[][] dataBoard, ArrayList<Point2D> ret){
        if(dataBoard[y][x] == null || dataBoard[y][x].getSide() != piece.getSide()){
            Point2D newPoint = new Point2D(x, y);
            ret.add(newPoint);
        }
    }

    public boolean noPiece(int x, int y, Piece[][] dataBoard){ // True if no piece exists at dataBoard[x][y], false otherwise
        if(x < 0 || x > 8 || y < 0 || y > 9){
            return false; // Out of bounds is
        }
        return dataBoard[y][x] == null;
    }


}
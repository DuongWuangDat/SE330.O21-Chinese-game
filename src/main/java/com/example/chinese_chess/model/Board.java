package com.example.chinese_chess.model;
import com.example.chinese_chess.controller.ImageController.*;
import com.example.chinese_chess.model.Game_EventAndData.Board_Event;
import com.example.chinese_chess.model.Game_EventAndData.Captured;
import com.example.chinese_chess.model.Game_EventAndData.Side;
import com.example.chinese_chess.model.Pieces.*;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

import java.net.MalformedURLException;
import java.util.ArrayList;
import com.example.chinese_chess.Image_Strings.Image_Locations;
public class Board{
    boolean win = false;
    BoardProperties boardProperties = new BoardProperties();
    Scene win_screen;

    public Board(Pane pane, Stage primaryStage,Scene start_screen) throws MalformedURLException {
        ImageView chess_board = createImageView(Image_Locations.chess_boardImage,true);
        setupCapturedPieces(pane); // Set up the captured pieces
        boardProperties.red_turn = createImageView(Image_Locations.chess_boardImage, false);
        boardProperties.black_turn = createImageView(Image_Locations.chess_boardImage, false);
        boardProperties.player_turn = boardProperties.red_turn;
        pane.getChildren().add(boardProperties.player_turn);
        boardProperties.player_turn.relocate(990 * boardProperties.RATIO, 30 * boardProperties.RATIO);
        boardProperties.board = new StackPane[10][9];
        boardProperties.dataBoard = new Piece[10][9];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                StackPane s_pane = new StackPane();
                Rectangle rect = new Rectangle((boardProperties.x + j * boardProperties.spacing + boardProperties.spacing*0.5), (boardProperties.y + i * boardProperties.spacing + boardProperties.spacing*0.5), boardProperties.spacing, boardProperties.spacing);
                //rect.setFill(Color.rgb(183, 17, 17, 0.4));
                rect.setFill(Color.TRANSPARENT);
                rect.setStroke(Color.TRANSPARENT);
                s_pane.getChildren().add(rect);
                rect.setMouseTransparent(true);

                Board_Event.setOnMouseEntered(s_pane,rect);

                Board_Event.setOnMouseExited(s_pane,rect);

                Board_Event.setOnMouseClicked(pane,s_pane,boardProperties);
                pane.getChildren().add(s_pane);
                s_pane.setManaged(true);
                s_pane.relocate((boardProperties.x + j * boardProperties.spacing) * boardProperties.RATIO, (boardProperties.y + i * boardProperties.spacing) * boardProperties.RATIO);
                boardProperties.board[i][j] = s_pane;
            }
        }
        boardProperties.red_side = new Side('R', this);
        boardProperties.black_side = new Side('B', this);
        //((Pane)start_screen.getRoot()).getChildren().addAll(chess_board,pane);
        Board_Event.printDataBoard(boardProperties);
        primaryStage.setScene(start_screen);
    }




    public StackPane[][] getSPArr(){
        return boardProperties.board;
    }

    public Piece[][] getPArr(){
        return boardProperties.dataBoard;
    }



    public ImageView createImageView(String image_location){
        return ImageSet.createImageView(image_location, true);
    }

    public ImageView createImageView(String image_location, boolean background){
        return ImageSet.createImageView(image_location, background);
    }

    public Button createButton(String image_location){
        return new Button("", createImageView(image_location, false));
    }

    public void setupCapturedPieces(Pane pane) throws MalformedURLException {
        this.boardProperties.captured_red_side = new Captured('R', pane, true); // Setup captured red pieces
        this.boardProperties.captured_black_side = new Captured('B', pane, true); // Setup captured black pieces
    }

    public boolean getWin(){
        return win;
    }
}
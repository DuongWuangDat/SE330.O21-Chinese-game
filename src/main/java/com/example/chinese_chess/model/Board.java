package com.example.chinese_chess.model;

import com.example.chinese_chess.model.Game_EventAndData.Captured;
import com.example.chinese_chess.model.Game_EventAndData.Side;
import com.example.chinese_chess.model.Pieces.*;
import javafx.scene.image.Image;
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

import java.util.ArrayList;

public class Board{
    ImageView red_turn, black_turn, player_turn;
    char turn = 'R';
    boolean win = false;
    StackPane[][] board;
    Piece[][] dataBoard;
    final double RATIO = 0.6;
    int x = 50, y = 50, spacing = 45;
    double org_sceneX, org_sceneY, org_translateX, org_translateY;
    double moveX, moveY;
    int piece_oneX, piece_oneY;
    boolean piece_clicked = false;
    StackPane clicked_pane;
    ArrayList<Point2D> validPoints;
    Captured captured_red_side, captured_black_side;
    Side red_side, black_side;
    Scene win_screen;

    public Board(Pane pane, Stage primaryStage,Scene start_screen) {
        ImageView chess_board = createImageView("Images/CChessBoard.png",true);
        setupCapturedPieces(pane); // Set up the captured pieces
        red_turn = createImageView("Images/CChessBoard.png", false);
        black_turn = createImageView("Images/CChessBoard.png", false);
        player_turn = red_turn;
        pane.getChildren().add(player_turn);
        player_turn.relocate(990 * RATIO, 30 * RATIO);
        board = new StackPane[10][9];
        dataBoard = new Piece[10][9];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                StackPane s_pane = new StackPane();
                Rectangle rect = new Rectangle((x + j * spacing + spacing*0.5), (y + i * spacing + spacing*0.5), spacing, spacing);
                //rect.setFill(Color.rgb(183, 17, 17, 0.4));
                rect.setFill(Color.TRANSPARENT);
                rect.setStroke(Color.TRANSPARENT);
                s_pane.getChildren().add(rect);
                rect.setMouseTransparent(true);

                s_pane.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        rect.setStroke(Color.BLACK);
                    }
                });

                s_pane.setOnMouseExited(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        rect.setStroke(Color.TRANSPARENT);
                    }
                });

                s_pane.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    public void handle(MouseEvent e) {
                        if(piece_clicked){
                            if(e.getTarget() instanceof StackPane){
                                double origX = clicked_pane.getLayoutX();
                                double origY = clicked_pane.getLayoutY();
                                Piece old_piece = dataBoard[piece_oneY][piece_oneX];
                                int old_x = piece_oneX;
                                int old_y = piece_oneY;
                                getArrCoor(s_pane);

                                boolean valid_point = false;
                                for(Point2D point : validPoints){
                                    if(point.getX() == piece_oneX && point.getY() == piece_oneY){
                                        valid_point = true;
                                    }
                                }

                                ((Rectangle)(board[piece_oneY][piece_oneX].getChildren().get(0))).setFill(Color.TRANSPARENT);
                                for(Point2D point : validPoints){
                                    int rectX = (int)point.getX();
                                    int rectY = (int)point.getY(); // Rectangle is always the first node
                                    ((Rectangle)(board[rectY][rectX].getChildren().get(0))).setFill(Color.TRANSPARENT);
                                }

                                if(valid_point){
                                    Piece target_piece = null;
                                    if(s_pane.getChildren().size() > 1
                                            && s_pane.getChildren().get(1) instanceof ImageView){
                                        target_piece = dataBoard[piece_oneY][piece_oneX];
                                        target_piece.setPaneXY(piece_oneX, piece_oneY);
                                        System.out.println("Capturing piece");
                                        dataBoard[piece_oneY][piece_oneX] = null; // Remove the piece from the data board
                                        s_pane.getChildren().remove(1);
                                        if(target_piece.getSide() == 'R'){
                                            captured_red_side.updateCaptured(target_piece);
                                        }
                                        else{
                                            captured_black_side.updateCaptured(target_piece);
                                        }
                                    }

//                                    if(target_piece instanceof General){
//                                        ImageView background = createImageView("/Images/chinese_chess_back.jpg", true);
//                                        ImageView winner;
//                                        if(turn == 'R'){
//                                            winner = createImageView("/Images/RedWinner.png");
//                                        }
//                                        else{
//                                            winner = createImageView("/Images/BlackWinner.png");
//                                        }
//                                        Button play_again = createButton("/Images/PlayAgain.png");
//                                        play_again.setOnMouseClicked(f->{
//                                            primaryStage.setScene(start_screen);
//                                        });
//                                        Pane pane = new Pane();
//                                        pane.getChildren().addAll(background, winner, play_again);
//                                        winner.relocate(310 * RATIO, 280 * RATIO);
//                                        play_again.relocate(670 * RATIO, 580 * RATIO);
//                                        Scene win_screen = new Scene(pane);
//                                        win_screen.getStylesheets().add("/css/start_menu.css");
//                                        primaryStage.setScene(win_screen);
//                                        win = true;
//                                    }
                                    target_piece = dataBoard[piece_oneY][piece_oneX];
                                    old_piece.setPaneXY(piece_oneX, piece_oneY);
                                    dataBoard[piece_oneY][piece_oneX] = old_piece;
                                    dataBoard[old_y][old_x] = target_piece;
                                    StackPane swap = board[piece_oneY][piece_oneX];
                                    board[piece_oneY][piece_oneX] = board[old_y][old_x];
                                    board[old_y][old_x] = swap;
                                    clicked_pane.relocate(s_pane.getLayoutX(), s_pane.getLayoutY());
                                    s_pane.relocate(origX, origY);
                                    if(turn == 'R'){
                                        turn = 'B';
                                        pane.getChildren().remove(player_turn);
                                        player_turn = black_turn;
                                        pane.getChildren().add(player_turn);
                                    }
                                    else{
                                        turn = 'R';
                                        pane.getChildren().remove(player_turn);
                                        player_turn = red_turn;
                                        pane.getChildren().add(player_turn);
                                    }
                                    player_turn.relocate(990 * RATIO, 30 * RATIO);
                                }
                            }
                            clicked_pane.setOpacity(1.0);
                            clicked_pane = null;
                            piece_clicked = false;
                            printDataBoard();
                        }
                        else{
                            if(s_pane.getChildren().size() > 1
                                    && s_pane.getChildren().get(1) instanceof ImageView){
                                Piece piece = getPieceFromPane(s_pane);
                                if(piece.getSide() == turn){
                                    validPoints = new ArrayList<Point2D>();
                                    validPoints = piece.getLegalMoves(dataBoard);
                                    for (Point2D validPoint : validPoints) {
                                        int rectX = (int) (validPoint.getX());
                                        int rectY = (int) (validPoint.getY());
                                        // Rectangle is always the first node
                                        ((Rectangle) (board[rectY][rectX].getChildren().get(0))).setFill(Color.rgb(12, 128, 34, 0.4));
                                    }
                                    getArrCoor(s_pane);
                                    piece_clicked = true;
                                    s_pane.setOpacity(0.5);
                                    clicked_pane = s_pane;
                                }
                            }
                        }
                    }
                });
                pane.getChildren().add(s_pane);
                s_pane.setManaged(true);
                s_pane.relocate((x + j * spacing) * RATIO, (y + i * spacing) * RATIO);
                board[i][j] = s_pane;
            }
        }
        red_side = new Side('R', this);
        black_side = new Side('B', this);
        ((Pane)start_screen.getRoot()).getChildren().addAll(chess_board,pane);
        printDataBoard();
        primaryStage.setScene(start_screen);
    }

    // Helper function for calculating x and y values for the 2D arrays through coordinates
    public void getArrCoor(StackPane s_pane){
        Bounds point = s_pane.localToScene(s_pane.getBoundsInLocal());
        piece_oneX = (int)((point.getMinX()) / (spacing * RATIO));
        piece_oneY = (int)((point.getMinY()) / (spacing * RATIO));
    }

    public Piece getPieceFromPane(StackPane s_pane){
        getArrCoor(s_pane);
        return dataBoard[piece_oneY][piece_oneX];
    };


    public StackPane[][] getSPArr(){
        return board;
    }

    public Piece[][] getPArr(){
        return dataBoard;
    }

    public void printDataBoard(){
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 9; j++){
                if(dataBoard[i][j] == null){
                    ret.append("-- ");
                }
                else{
                    ret.append(dataBoard[i][j].toString()).append(" ");
                }
            }
            ret.append("\n");
        }
        System.out.println(ret);
    }

    public ImageView createImageView(String image_location){
        Image image = new Image(image_location);
        ImageView image_view = new ImageView(image);
        image_view.setFitWidth(image.getWidth() * RATIO);
        image_view.setPreserveRatio(true);
        image_view.setSmooth(true);
        image_view.setCache(true);
        return image_view;
    }

    public ImageView createImageView(String image_location, boolean background){
        Image image = new Image(image_location);
        ImageView image_view = new ImageView(image);
        image_view.relocate(50,50);
        if(background){
            image_view.setFitWidth(45*9);
            image_view.setFitHeight(45*10);
        }
        else{
            image_view.setFitWidth(45);
            image_view.setFitWidth(45);
            image_view.setX(0);
            image_view.setY(0);
        }
        image_view.setPreserveRatio(true);
        image_view.setSmooth(true);
        image_view.setCache(true);
        return image_view;
    }

    public Button createButton(String image_location){
        return new Button("", createImageView(image_location, false));
    }

    public void setupCapturedPieces(Pane pane){
        captured_red_side = new Captured('R', pane, true); // Setup captured red pieces
        captured_black_side = new Captured('B', pane, true); // Setup captured black pieces
    }

    public boolean getWin(){
        return win;
    }
}
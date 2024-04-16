package com.example.chinese_chess.model.Game_EventAndData;

import com.example.chinese_chess.model.Board;
import com.example.chinese_chess.model.BoardProperties;
import com.example.chinese_chess.model.Pieces.Piece;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Board_Event {
    public static void setOnMouseEntered(StackPane s_pane, Rectangle rect){
        s_pane.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) { rect.setStroke(Color.BLACK); }
        });
    }
    public static void  setOnMouseExited(StackPane s_pane, Rectangle rect){
        s_pane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                rect.setStroke(Color.TRANSPARENT);
            }
        });
    }

    public static void setOnMouseClicked(Pane pane,StackPane s_pane,BoardProperties boardProperties){
        s_pane.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e) {
                if(boardProperties.piece_clicked){
                    if(e.getTarget() instanceof StackPane){
                        double origX = boardProperties.clicked_pane.getLayoutX();
                        double origY = boardProperties.clicked_pane.getLayoutY();
                        Piece old_piece = boardProperties.dataBoard[boardProperties.piece_oneY][boardProperties.piece_oneX];
                        int old_x = boardProperties.piece_oneX;
                        int old_y = boardProperties.piece_oneY;
                        getArrCoor(s_pane, boardProperties);

                        boolean valid_point = false;
                        for(Point2D point : boardProperties.validPoints){
                            if(point.getX() == boardProperties.piece_oneX && point.getY() == boardProperties.piece_oneY){
                                valid_point = true;
                            }
                        }

                        ((Rectangle)(boardProperties.board[boardProperties.piece_oneY][boardProperties.piece_oneX].getChildren().get(0))).setFill(Color.TRANSPARENT);
                        for(Point2D point : boardProperties.validPoints){
                            int rectX = (int)point.getX();
                            int rectY = (int)point.getY(); // Rectangle is always the first node
                            ((Rectangle)(boardProperties.board[rectY][rectX].getChildren().get(0))).setFill(Color.TRANSPARENT);
                        }

                        if(valid_point){
                            Piece target_piece = null;
                            if(s_pane.getChildren().size() > 1
                                    && s_pane.getChildren().get(1) instanceof ImageView){
                                target_piece = boardProperties.dataBoard[boardProperties.piece_oneY][boardProperties.piece_oneX];
                                target_piece.setPaneXY(boardProperties.piece_oneX, boardProperties.piece_oneY);
                                System.out.println("Capturing piece");
                                boardProperties.dataBoard[boardProperties.piece_oneY][boardProperties.piece_oneX] = null; // Remove the piece from the data board
                                s_pane.getChildren().remove(1);
                                if(target_piece.getSide() == 'R'){
                                    boardProperties.captured_red_side.updateCaptured(target_piece);
                                }
                                else{
                                    boardProperties.captured_black_side.updateCaptured(target_piece);
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
                            target_piece = boardProperties.dataBoard[boardProperties.piece_oneY][boardProperties.piece_oneX];
                            old_piece.setPaneXY(boardProperties.piece_oneX, boardProperties.piece_oneY);
                            boardProperties.dataBoard[boardProperties.piece_oneY][boardProperties.piece_oneX] = old_piece;
                            boardProperties.dataBoard[old_y][old_x] = target_piece;
                            StackPane swap = boardProperties.board[boardProperties.piece_oneY][boardProperties.piece_oneX];
                            boardProperties.board[boardProperties.piece_oneY][boardProperties.piece_oneX] = boardProperties.board[old_y][old_x];
                            boardProperties.board[old_y][old_x] = swap;
                            boardProperties.clicked_pane.relocate(s_pane.getLayoutX(), s_pane.getLayoutY());
                            s_pane.relocate(origX, origY);
                            if(boardProperties.turn == 'R'){
                                boardProperties.turn = 'B';
                                pane.getChildren().remove(boardProperties.player_turn);
                                boardProperties.player_turn = boardProperties.black_turn;
                                pane.getChildren().add(boardProperties.player_turn);
                            }
                            else{
                                boardProperties.turn = 'R';
                                pane.getChildren().remove(boardProperties.player_turn);
                                boardProperties.player_turn = boardProperties.red_turn;
                                pane.getChildren().add(boardProperties.player_turn);
                            }
                            boardProperties.player_turn.relocate(990 * boardProperties.RATIO, 30 * boardProperties.RATIO);
                        }
                    }
                    boardProperties.clicked_pane.setOpacity(1.0);
                    boardProperties.clicked_pane = null;
                    boardProperties.piece_clicked = false;
                    printDataBoard(boardProperties);
                }
                else{
                    if(s_pane.getChildren().size() > 1
                            && s_pane.getChildren().get(1) instanceof ImageView){
                        Piece piece = getPieceFromPane(s_pane, boardProperties);
                        piece.setPaneXY(boardProperties.piece_oneX, boardProperties.piece_oneY);
                        System.out.println(piece.toString());
                        if(piece.getSide() == boardProperties.turn){
                            boardProperties.validPoints = new ArrayList<Point2D>();
                            boardProperties.validPoints = piece.getLegalMoves(boardProperties.dataBoard);
                            for (Point2D validPoint : boardProperties.validPoints) {
                                int rectX = (int) (validPoint.getX());
                                int rectY = (int) (validPoint.getY());
                                // Rectangle is always the first node
                                ((Rectangle) (boardProperties.board[rectY][rectX].getChildren().get(0))).setFill(Color.rgb(12, 128, 34, 0.4));
                            }
                            getArrCoor(s_pane, boardProperties);
                            boardProperties.piece_clicked = true;
                            s_pane.setOpacity(0.5);
                            boardProperties.clicked_pane = s_pane;
                        }
                    }
                }
            }
        });

    }

    public static void getArrCoor(StackPane s_pane, BoardProperties boardProperties){
        Bounds point = s_pane.localToScene(s_pane.getBoundsInLocal());
        boardProperties.piece_oneX = (int)((point.getMinX()) / (boardProperties.spacing ));
        boardProperties.piece_oneY = (int)((point.getMinY()) / (boardProperties.spacing ));
    }
    public static Piece getPieceFromPane(StackPane s_pane, BoardProperties boardProperties){
        getArrCoor(s_pane, boardProperties);
        return boardProperties.dataBoard[boardProperties.piece_oneY][boardProperties.piece_oneX];
    };
    public static void printDataBoard(BoardProperties boardProperties){
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 9; j++){
                if(boardProperties.dataBoard[i][j] == null){
                    ret.append("-- ");
                }
                else{
                    ret.append(boardProperties.dataBoard[i][j].toString()).append(" ");
                }
            }
            ret.append("\n");
        }
        System.out.println(ret);
    }

    // Checking for Chess
    public static boolean isRedInCheck(BoardProperties boardProperties) {
       Point2D redGenCoor = getRedGenCoordinate(boardProperties);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 9; col++) {
                Piece piece = boardProperties.dataBoard[row][col];
                if(piece.getSide()=='B'){
                    ArrayList<Point2D> move = piece.getLegalMoves(boardProperties.dataBoard);
                    if(move.contains(redGenCoor)){
                        return true;
                    }
                }
            }
        }

       return false;
    }

    public static boolean isBlackInCheck(BoardProperties boardProperties) {
        Point2D blackGenCoor = getBlackGenCoordinate(boardProperties);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 9; col++) {
                Piece piece = boardProperties.dataBoard[row][col];
                if(piece.getSide()=='R'){
                    ArrayList<Point2D> move = piece.getLegalMoves(boardProperties.dataBoard);
                    if(move.contains(blackGenCoor)){
                        return true;
                    }
                }
            }
        }

        return false;
    }
    // Get General coordinate
    public static Point2D getRedGenCoordinate(BoardProperties boardProperties){
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 9; col++) {
                Piece piece = boardProperties.dataBoard[row][col];
                if(piece.toString().equals("Ge") && piece.getSide() =='R'){
                    return new Point2D(piece.getX(),piece.getY());
                }
            }
        }
        return new Point2D(0,0);
    }


    public static Point2D getBlackGenCoordinate(BoardProperties boardProperties){
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 9; col++) {
                Piece piece = boardProperties.dataBoard[row][col];
                if(piece.toString().equals("Ge") && piece.getSide() =='B'){
                    return new Point2D(piece.getX(),piece.getY());
                }
            }
        }
        return new Point2D(0,0);
    }
}

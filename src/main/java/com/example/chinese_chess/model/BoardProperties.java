package com.example.chinese_chess.model;

import com.example.chinese_chess.model.Game_EventAndData.Captured;
import com.example.chinese_chess.model.Game_EventAndData.Side;
import com.example.chinese_chess.model.Pieces.Piece;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class BoardProperties {
    public StackPane[][] board;
    public Piece[][] dataBoard;
    public final double RATIO = 0.6;
    public int x = 60, y = 60, spacing = 60;
    public double org_sceneX, org_sceneY, org_translateX, org_translateY;
    public double moveX, moveY;
    public int piece_oneX;
    public int piece_oneY;
    public boolean piece_clicked = false;
    public StackPane clicked_pane;
    public ArrayList<Point2D> validPoints;
    public Captured captured_red_side, captured_black_side;
    public Side red_side, black_side;
    public ImageView red_turn, black_turn, player_turn;
    public char turn = 'R';
}

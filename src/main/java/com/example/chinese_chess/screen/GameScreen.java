package com.example.chinese_chess.screen;

import com.example.chinese_chess.model.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.MalformedURLException;

public class GameScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1200, 800); // Đặt size của cửa sổ ứng dụng

        // Tạo đối tượng bảng cờ
        Board board = new Board(root, primaryStage, scene);

        primaryStage.setTitle("Chinese Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

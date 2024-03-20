module com.example.chinese_chess {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chinese_chess to javafx.fxml;
    exports com.example.chinese_chess;
}
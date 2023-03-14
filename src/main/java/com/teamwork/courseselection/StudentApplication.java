package com.teamwork.courseselection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class StudentApplication extends Application {
    public static Stage primaryStage=null;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("StudentViewer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        //让新窗口居中出现
        stage.setX((Screen.getPrimary().getVisualBounds().getWidth() - 1000) / 2);
        stage.setY((Screen.getPrimary().getVisualBounds().getHeight() - 800) / 2);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage(){
        return primaryStage;
    }
    public static void main(String[] args) {
        launch();
    }
}
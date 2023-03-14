package com.teamwork.courseselection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class LoginApplication extends Application {
    public static Stage primaryStage=null;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        primaryStage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("LoginViewer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 453, 299);
        //让新窗口居中出现
        stage.setX((Screen.getPrimary().getVisualBounds().getWidth() - 453) / 2);
        stage.setY((Screen.getPrimary().getVisualBounds().getHeight() - 299) / 2);
        if(stage.getStyle()!=StageStyle.UNDECORATED) stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("暨北大学选课系统");
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
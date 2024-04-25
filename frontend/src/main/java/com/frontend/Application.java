package com.frontend;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    static private Stage mainStage;

    @Override
    public void start(Stage stage)  {


        mainStage = stage;

        changeView("view/login.fxml");
//        changeView("view/main.fxml");
        mainStage.setTitle("进销存管理系统");

    }

    public static void main(String[] args) {
        launch();
    }


    static public void changeView(String viewPath)   {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(viewPath));

        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainStage.setScene(scene);
        mainStage.show();

    }
}
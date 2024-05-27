package com.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.frontend.entity.APPConfigEntity;
import com.frontend.entity.UserInfoReceiveData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Application extends javafx.application.Application {
    static public Stage mainStage;

    //应用配置
    static public APPConfigEntity appConfigEntity;

    //全局FXML控制实体
    static public Map<String, Object> shareController;

    static public ArrayList<ToggleButton> shareLabelButton;


    //全局登录信息
    static  public UserInfoReceiveData userInfoReceiveData;


    //用户登录Token
    static  public String userLoginToken;

    public static void main(String[] args) {
        shareController = new HashMap<>();
        shareLabelButton = new ArrayList<>();
        getAppConfig();
        launch();
    }


    @Override
    public void start(Stage stage) {
        mainStage = stage;
        mainStage.setResizable(false);
        changeView("view/LoginView.fxml");
        mainStage.setTitle("进销存管理系统");
        mainStage.getIcons().add(new Image(Application.class.getResource("img/logo.png").toString()));
//        mainStage.getIcons().add(new Image("file:/E:/code/idea/inventory_management_system/frontend/target/classes/com/frontend/img/logo.png"));

//        System.out.println(this.getClass().getResourceAsStream("img/logo.png"));

//        mainStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/img/logo.png")));

    }


    static public void changeView(String viewPath) {

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

    private static void  getAppConfig(){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            appConfigEntity = mapper.readValue(Application.class.getResource("config/application.yaml"), APPConfigEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
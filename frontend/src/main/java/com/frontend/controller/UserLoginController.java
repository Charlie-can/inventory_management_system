package com.frontend.controller;

import com.frontend.Application;
import com.frontend.entity.HTTPStatusEnums;
import com.frontend.entity.LoginReceiveData;
import com.frontend.model.UserLoginModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Objects;


public class UserLoginController {

    @FXML
    private TextField account;

    @FXML
    private PasswordField password;

    @FXML
    public void onLoginClicked(Event event) {


        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("警告");

        if (account.getText().isEmpty() || password.getText().isEmpty()) {


            alert.setHeaderText("您输入的用户名或密码为空");
            alert.setContentText("请输入用户名或密码");
            alert.showAndWait();
            return;
        }

        LoginReceiveData loginReceiveData = UserLoginModel.isLogin(Integer.parseInt(account.getText()), password.getText());



        if (Objects.equals(loginReceiveData.getCode(), HTTPStatusEnums.OK.getCode())) {
            Application.userLoginToken = loginReceiveData.getData().get("token");

//            System.out.println(Application.userLoginToken);
//            System.out.println("登录成功"+loginReceiveData);

            Application.changeView("view/MainView.fxml");

            return;
        }

        alert.setHeaderText(loginReceiveData.getMessage());
        alert.setContentText(loginReceiveData.getMessage());

        if (Objects.equals(loginReceiveData.getCode(), HTTPStatusEnums.Not_Connected_Server.getCode())) {

            alert.setHeaderText("未连接到服务器");
            alert.setContentText("没有连接到服务器，请检查网络链接或者服务器发生错误");

        }
        alert.showAndWait();

    }
}

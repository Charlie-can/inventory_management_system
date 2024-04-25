package com.frontend.controller;

import com.frontend.Application;
import com.frontend.entity.LoginReceiveData;
import com.frontend.view.UserLoginView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class UserLoginController {

    @FXML
    private TextField account;

    @FXML
    private TextField password;

    @FXML
    public void onLogin(Event event) {



        System.out.println(account.getText());
        System.out.println(password.getText());


        if(account.getText().isEmpty()||password.getText().isEmpty()){


            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText("您输入的用户名或密码为空");
            alert.setContentText("请输入用户名或密码");
            alert.showAndWait();
            return;
        }

        LoginReceiveData loginReceiveData = UserLoginView.isLogin(Integer.parseInt(account.getText()), password.getText());



        if(loginReceiveData.getCode()!=200){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText(loginReceiveData.getMessage());
            alert.setContentText(loginReceiveData.getMessage());
            alert.showAndWait();
            return;
        }
        System.out.println(loginReceiveData);

        Application.changeView("view/main.fxml");


    }


}

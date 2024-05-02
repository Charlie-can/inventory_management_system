package com.frontend.controller;

import com.frontend.Application;
import com.frontend.entity.UserInfoReceiveData;
import com.frontend.model.MainDisplayModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class MainController {


    @FXML
    VBox VboxTableInfo;

    @FXML
    private VBox VboxLabel;


    public void initialize() {

        //共享MainControllerFXML控件
        Application.shareController.put(getClass().getSimpleName(), this);

        //放置全局共享用户信息
        Application.userInfoReceiveData = MainDisplayModel.getUserInfo();


        UserInfoReceiveData.Data.UserInfo userInfo = Application.userInfoReceiveData.getData().getUserInfo();


        loadVboxLabel("view/ProductsLabel.fxml");


        if (userInfo.getSalesperson() == 1) {

            loadVboxLabel("view/ProductsSalesLabel.fxml");

        }

        if (userInfo.getInventoryperson() == 1) {
            loadVboxLabel("view/ProductsInventoryLabel.fxml");
        }

        if (userInfo.getStorageperson() == 1) {
            loadVboxLabel("view/ProductsStorageLabel.fxml");
        }

        if (userInfo.getAdmin() == 1) {
            loadVboxLabel("view/UserManagementLabel.fxml");
        }

        loadVboxLabel("view/PersonalInformationLabel.fxml");


    }

    private void loadVboxLabel(String labelViewPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(labelViewPath));

            Parent element = fxmlLoader.load();

            VboxLabel.getChildren().add(element);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


}

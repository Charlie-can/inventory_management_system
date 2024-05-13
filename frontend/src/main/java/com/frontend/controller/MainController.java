package com.frontend.controller;

import com.frontend.Application;
import com.frontend.entity.UserInfoReceiveData;
import com.frontend.model.MainDisplayModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {


    @FXML
    public  VBox VboxTableInfo;

    @FXML
    private VBox VboxLabel;


    public void initialize() {

        //共享MainControllerFXML控件
        Application.shareController.put(getClass().getSimpleName(), this);

        //放置全局共享用户信息
        Application.userInfoReceiveData = MainDisplayModel.getUserInfo();


        UserInfoReceiveData.Data.UserInfo userInfo = Application.userInfoReceiveData.getData().getUserInfo();
//        System.out.println(userInfo);


        if (userInfo.getStockmanager()) {
            loadVboxLabel("view/products/ProductsLabel.fxml");
        }

        if (userInfo.getSalesperson()) {

            loadVboxLabel("view/sales/SalesLabel.fxml");

        }

        if (userInfo.getStorageperson()) {
            loadVboxLabel("view/storage/StorageLabel.fxml");
        }

        if (userInfo.getInventoryperson()) {
            loadVboxLabel("view/inventory/InventoryLabel.fxml");
        }

        if (userInfo.getAdmin()) {
            loadVboxLabel("view/userManagement/UserManagementLabel.fxml");
        }

        loadVboxLabel("view/information/PersonalInformationLabel.fxml");


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

//    static  public void loadVboxTableInfo(String viewPath){
//
//        try {
//        VboxTableInfo.getChildren().clear();
//
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(viewPath));
//
//        Parent element = fxmlLoader.load();
//
//        VboxTableInfo.getChildren().add(element);
//
//        }catch (Exception e){
//
//            e.printStackTrace();
//        }
//
//
//
//    }


}

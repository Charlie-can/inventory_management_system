package com.frontend.controller;

import com.frontend.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ProductsController {

    private MainController mainController;

     private Boolean isProductsShow;
    @FXML
    public void OnProductsLabelClicked(){

        if(!isProductsShow) {

            isProductsShow = true;
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/ProductsViewSum.fxml"));

                Parent element = fxmlLoader.load();

                mainController.VboxTableInfo.getChildren().add(element);


            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("showProducts");
        }
    }

    public void initialize() {
        mainController = (MainController) Application.shareController.get(MainController.class.getSimpleName());
        isProductsShow =false;
    }

}

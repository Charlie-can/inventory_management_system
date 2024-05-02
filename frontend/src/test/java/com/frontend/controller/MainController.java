package com.frontend.controller;

import com.frontend.entity.ProductsList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;


public class MainController {


    @FXML
    private TableView<ProductsList> tableViewListStock;


    @FXML
    private TableColumn<ProductsList, Integer> id;

    @FXML
    private TableColumn<ProductsList, String> name;

    @FXML
    private TableColumn<ProductsList, Integer> reserveNow;

    @FXML
    private TableColumn<ProductsList, Integer> reserveMin;

    @FXML
    private TableColumn<ProductsList, Double> price;

    @FXML
    private TableColumn<ProductsList, String> vendor;

    @FXML
    private TableColumn<ProductsList, String> introduction;



    public void initialize() {


        List<ProductsList> productsLists = new ArrayList<>();
//
//        ProductsList productsList1 = new ProductsList(1001,"测试",202,100,5.0,"test","testTestTest");
//        ProductsList productsList2 = new ProductsList(1002,"测试2",202,100,5.0,"test","testTestTest");
//        productsLists.add(productsList1);
//        productsLists.add(productsList2);



        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        reserveNow.setCellValueFactory(new PropertyValueFactory<>("reserveNow"));
        reserveMin.setCellValueFactory(new PropertyValueFactory<>("reserveMin"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        vendor.setCellValueFactory(new PropertyValueFactory<>("vendor"));
        introduction.setCellValueFactory(new PropertyValueFactory<>("introduction"));


        tableViewListStock.setItems(FXCollections.observableList(productsLists));
    }

}

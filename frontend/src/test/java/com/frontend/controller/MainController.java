package com.frontend.controller;

import com.frontend.entity.ListStock;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;


public class MainController {


    @FXML
    private TableView<ListStock> tableViewListStock;


    @FXML
    private TableColumn<ListStock, Integer> id;

    @FXML
    private TableColumn<ListStock, String> name;

    @FXML
    private TableColumn<ListStock, Integer> reserveNow;

    @FXML
    private TableColumn<ListStock, Integer> reserveMin;

    @FXML
    private TableColumn<ListStock, Double> price;

    @FXML
    private TableColumn<ListStock, String> vendor;

    @FXML
    private TableColumn<ListStock, String> introduction;



    public void initialize() {


        List<ListStock>  listStocks = new ArrayList<>();

        ListStock listStock1 = new ListStock(1001,"测试",202,100,5.0,"test","testTestTest");
        ListStock listStock2 = new ListStock(1002,"测试2",202,100,5.0,"test","testTestTest");
        listStocks.add(listStock1);
        listStocks.add(listStock2);



        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        reserveNow.setCellValueFactory(new PropertyValueFactory<>("reserveNow"));
        reserveMin.setCellValueFactory(new PropertyValueFactory<>("reserveMin"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        vendor.setCellValueFactory(new PropertyValueFactory<>("vendor"));
        introduction.setCellValueFactory(new PropertyValueFactory<>("introduction"));

        tableViewListStock.setItems(FXCollections.observableList(listStocks));
    }

}

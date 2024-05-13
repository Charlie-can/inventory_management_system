package com.frontend.controller.inventory;

import com.frontend.Application;
import com.frontend.controller.MainController;
import com.frontend.entity.*;
import com.frontend.model.InventoryModel;
import com.frontend.utils.BackendResource;
import com.frontend.utils.PopupWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class InventoryController {

    private MainController mainController;

    @FXML
    public ComboBox<String> inventoryComboBox;


    @FXML
    public TableColumn id;
    @FXML
    public TableColumn stockId;

    @FXML
    public TableColumn generateTime;

    @FXML
    public TableColumn stockName;
    @FXML
    public TableColumn storagePrice;
    @FXML
    public TableColumn salePrice;
    @FXML
    public TableColumn profitPrice;
    @FXML
    public TableColumn remainingCount;
    @FXML
    public TableColumn replenishCount;


    @FXML
    private TextField userSearchField;

    @FXML
    public TableView inventoryTableView;


    @FXML
    public void onRestButtonClicked() {
        OnInventoryLabelClicked();
    }


    @FXML
    public void onGenerateButtonClicked() {

        BackendResource<HttpResponseData> httpResponseDataBackendResource = new BackendResource<>();

        HttpResponseData request = httpResponseDataBackendResource.getRequest("/inventory/generateInventory", HttpResponseData.class);
        if (request.getCode() == HTTPStatusEnums.OK.getCode()) {
            PopupWindow.alertWindow("生成成功");
        }



    }


    @FXML
    public void OnInventoryLabelClicked( ) {
        try {

            mainController.VboxTableInfo.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/inventory/TableView.fxml"));

            Parent element = fxmlLoader.load();

            mainController.VboxTableInfo.getChildren().add(element);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void initialize() {
        mainController = (MainController) Application.shareController.get(MainController.class.getSimpleName());


        if (inventoryComboBox != null) {

            BackendResource<InventoryReceiveDateDate> arrayListBackendResource = new BackendResource<>();
            ArrayList<String> inventoryDateList = arrayListBackendResource.getRequest("/inventory/getDate", InventoryReceiveDateDate.class).getData().getInventoryDateList();
            Collections.reverse(inventoryDateList);
            inventoryComboBox.getItems().addAll(inventoryDateList);

            inventoryComboBox.addEventHandler(ActionEvent.ACTION, event -> {
                ArrayList<InventoryStock> inventoryList = InventoryModel.receiveDate(inventoryComboBox.getSelectionModel().getSelectedItem()).getData().getInventoryList();

                ObservableList<InventoryStock> observableArrayList = FXCollections.observableArrayList(inventoryList);

                inventoryTableView.setItems(observableArrayList);
            });

        }


        if (inventoryTableView != null) {


            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            stockId.setCellValueFactory(new PropertyValueFactory<>("stockId"));
            generateTime.setCellValueFactory(new PropertyValueFactory<>("date"));
            stockName.setCellValueFactory(new PropertyValueFactory<>("stockName"));
            salePrice.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
            storagePrice.setCellValueFactory(new PropertyValueFactory<>("storagePrice"));
            profitPrice.setCellValueFactory(new PropertyValueFactory<>("profitPrice"));
            remainingCount.setCellValueFactory(new PropertyValueFactory<>("remainingCount"));
            replenishCount.setCellValueFactory(new PropertyValueFactory<>("replenishCount"));

        }


    }


}

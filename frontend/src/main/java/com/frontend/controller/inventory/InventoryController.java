package com.frontend.controller.inventory;

import com.alibaba.excel.EasyExcel;
import com.frontend.Application;
import com.frontend.controller.MainController;
import com.frontend.entity.HTTPStatusEnums;
import com.frontend.entity.HttpResponseData;
import com.frontend.entity.InventoryReceiveDateDate;
import com.frontend.entity.InventoryStock;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class InventoryController {


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
    public ToggleButton inventoryToggleButton;
    @FXML
    public TableView inventoryTableView;
    private MainController mainController;
    @FXML
    private TextField userSearchField;

    @FXML
    public void onRestButtonClicked() {
        OnInventoryLabelClicked();
        for (ToggleButton toggleButton : Application.shareLabelButton) {
            if (Objects.equals(toggleButton.getText(), "商品盘查"))
                toggleButton.setSelected(true);
        }

    }


    @FXML
    public void onGenerateButtonClicked() {

        BackendResource<HttpResponseData> httpResponseDataBackendResource = new BackendResource<>();

        HttpResponseData request = httpResponseDataBackendResource.getRequest("/inventory/generateInventory", HttpResponseData.class);
        if (Objects.equals(request.getCode(), HTTPStatusEnums.OK.getCode())) {
            PopupWindow.alertWindow("生成成功");
        }


    }


    @FXML
    public void OnInventoryLabelClicked() {
        try {
            for (ToggleButton toggleButton : Application.shareLabelButton) {

                if (toggleButton != null) {
                    toggleButton.setSelected(false);
                }
            }

            if (inventoryToggleButton != null)
                inventoryToggleButton.setSelected(true);


            mainController.VboxTableInfo.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/inventory/TableView.fxml"));

            Parent element = fxmlLoader.load();

            mainController.VboxTableInfo.getChildren().add(element);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onExportButtonClicked() {

        ArrayList<InventoryStock> inventoryList = null;

        if (inventoryComboBox.getSelectionModel().getSelectedItem() == null) {
            inventoryList = InventoryModel.receiveDate(inventoryComboBox.getItems().get(0)).getData().getInventoryList();
        } else
            inventoryList = InventoryModel.receiveDate(inventoryComboBox.getSelectionModel().getSelectedItem()).getData().getInventoryList();

        System.out.println(inventoryList);

        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = new Stage();
        stage.setTitle("选择目录");
        File file = directoryChooser.showDialog(stage);
        EasyExcel.write(file+"/导出信息.xls",InventoryStock.class).sheet("导出信息").doWrite(inventoryList);
        PopupWindow.informationWindow("导出成功","导出成功","请到"+file+"查看");
    }

    public void initialize() {
        mainController = (MainController) Application.shareController.get(MainController.class.getSimpleName());

        if (inventoryToggleButton != null)
            Application.shareLabelButton.add(inventoryToggleButton);


        if (inventoryComboBox != null) {

            BackendResource<InventoryReceiveDateDate> arrayListBackendResource = new BackendResource<>();
            ArrayList<String> inventoryDateList = arrayListBackendResource.getRequest("/inventory/getDate", InventoryReceiveDateDate.class).getData().getInventoryDateList();
            Collections.reverse(inventoryDateList);

            ArrayList<InventoryStock> inventoryList = InventoryModel.receiveDate(inventoryDateList.get(0)).getData().getInventoryList();

            ObservableList<InventoryStock> observableArrayList = FXCollections.observableArrayList(inventoryList);

            inventoryTableView.setItems(observableArrayList);

            inventoryComboBox.getItems().addAll(inventoryDateList);


            inventoryComboBox.addEventHandler(ActionEvent.ACTION, event -> {
                ArrayList<InventoryStock> inventoryList1 = InventoryModel.receiveDate(inventoryComboBox.getSelectionModel().getSelectedItem()).getData().getInventoryList();

                ObservableList<InventoryStock> observableArrayList1 = FXCollections.observableArrayList(inventoryList1);

                inventoryTableView.setItems(observableArrayList1);
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

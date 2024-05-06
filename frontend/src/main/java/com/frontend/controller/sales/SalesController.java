package com.frontend.controller.sales;

import com.frontend.Application;
import com.frontend.controller.MainController;
import com.frontend.controller.products.ProductsController;
import com.frontend.entity.ProductsList;
import com.frontend.entity.ProductsListSelected;
import com.frontend.entity.ProductsReceiveData;
import com.frontend.model.ProductsModel;
import com.frontend.utils.PopupWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Objects;

public class SalesController {

    private MainController mainController;

    private ProductsController productsController;

    private Number beforeToString;


    @FXML
    private TableView<ProductsListSelected> salesTableView;

    @FXML
    private ComboBox<String> salesComboBox;

    @FXML
    private TableColumn<ProductsListSelected, Number> id;

    @FXML
    private TableColumn<ProductsListSelected, String> name;

    @FXML
    private TableColumn<ProductsListSelected, Number> reserveNow;

    @FXML
    private TableColumn<ProductsListSelected, Double> price;

    @FXML
    private TableColumn<ProductsListSelected, Boolean> checkColumn;

    @FXML
    private TextField userSearchField;

    @FXML
    private Integer selectColumn;

    @FXML
    private TextField saleId;

    @FXML
    private TextField saleName;

    @FXML
    private TextField saleNumber;

    @FXML
    private TextField salePrice;

    @FXML
    private DatePicker saleDate;


    @FXML
    public void OnSalesLabelClicked() {
        try {

            mainController.VboxTableInfo.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/sales/TableView.fxml"));

            Parent element = fxmlLoader.load();

            mainController.VboxTableInfo.getChildren().add(element);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onSearchButtonClicked() {


        String inputText = userSearchField.getText();

        String comboBoxValue = salesComboBox.getValue();

        if (inputText.isEmpty()) {
            PopupWindow.alertWindow("未输入查询内容");
            return;
        }
        String queryType;
        if (Objects.equals(comboBoxValue, "编号")) {
            queryType = "id";
        } else if (Objects.equals(comboBoxValue, "商品名")) {
            queryType = "name";
        } else if (Objects.equals(comboBoxValue, "当前库存")) {
            queryType = "reserve_now";
        } else if (Objects.equals(comboBoxValue, "价格")) {
            queryType = "price";
        } else {
            PopupWindow.alertWindow("输入错误");
            return;
        }

        ProductsReceiveData productsReceiveData = ProductsModel.queryProducts(queryType, inputText);

        if (productsReceiveData != null) {
            setTableData(productsReceiveData);
        }
    }

    @FXML
    public void onRestButtonClicked() {
        showAllProducts();

    }

    @FXML
    public void onSaleButtonClicked() {
        int selectCount = 0;

        ObservableList<ProductsListSelected> salesTableViewItems = salesTableView.getItems();
        for (ProductsListSelected salesTableViewItem : salesTableViewItems) {
            if (salesTableViewItem.isSelected()) {
                selectCount++;
            }
        }
        if (selectCount == 0 || selectCount > 1) {
            PopupWindow.alertWindow("只能同时选中一行单元,或者没有选择单元格");
            return;
        }
        saleId.setText(String.valueOf(salesTableViewItems.get(selectColumn).getId()));
        saleName.setText(salesTableViewItems.get(selectColumn).getName());
        saleNumber.setText(String.valueOf(salesTableViewItems.get(selectColumn).getPrice()));

        System.out.println(selectColumn);
        System.out.println(selectCount);
    }

    @FXML
    public void onCheckColumnCheckBoxClicked() {

    }


    public void initialize() {

        mainController = (MainController) Application.shareController.get(MainController.class.getSimpleName());
        productsController = (ProductsController) Application.shareController.get(ProductsController.class.getSimpleName());
        Application.shareController.put(SalesController.class.getSimpleName(), this);


        if (salesTableView != null) {
            showAllProducts();

            Callback<TableColumn<ProductsListSelected, Boolean>, TableCell<ProductsListSelected, Boolean>> checkBoxCallBack = new Callback<>() {
                @Override
                public CheckBoxTableCell<ProductsListSelected, Boolean> call(TableColumn<ProductsListSelected, Boolean> param) {

                    return new CheckBoxTableCell<ProductsListSelected, Boolean>() {

                        @Override
                        public void updateItem(Boolean item, boolean empty) {

                            if (item != null && item) {
                                selectColumn = getIndex();
                            }
                            super.updateItem(item, empty);

                        }

                    };
                }
            };

            checkColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            reserveNow.setCellValueFactory(new PropertyValueFactory<>("reserveNow"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            checkColumn.setCellFactory(checkBoxCallBack);
        }

        if (salesComboBox != null) {
            salesComboBox.getItems().addAll("编号", "商品名", "当前库存", "价格");
            salesComboBox.setValue("编号");
        }

    }

    public void showAllProducts() {

        setTableData(ProductsModel.getAllProducts());
    }

    public void setTableData(ProductsReceiveData productsReceiveData) {


        if (productsReceiveData.getCode() != 200) {
            PopupWindow.alertWindow(productsReceiveData.getMessage());
        }

        ArrayList<ProductsList> productsList = productsReceiveData.getData().getStockList();

        ArrayList<ProductsListSelected> productsListSelectedArrayList = new ArrayList<>();

        for (ProductsList list : productsList) {
            ProductsListSelected productsListSelected = new ProductsListSelected(list);
            productsListSelectedArrayList.add(productsListSelected);

        }
        ObservableList<ProductsListSelected> observableArrayList = FXCollections.observableArrayList(productsListSelectedArrayList);
        salesTableView.setItems(observableArrayList);
    }
}

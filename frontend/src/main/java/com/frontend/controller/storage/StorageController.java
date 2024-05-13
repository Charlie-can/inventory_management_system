package com.frontend.controller.storage;

import com.frontend.Application;
import com.frontend.controller.MainController;
import com.frontend.controller.products.ProductsController;
import com.frontend.entity.*;
import com.frontend.model.ProductsModel;
import com.frontend.utils.BackendResource;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import java.util.jar.JarEntry;

public class StorageController {


    private MainController mainController;


    private Number beforeToString;


    @FXML
    private TableView<ProductsListSelected> storageTableView;

    @FXML
    private ComboBox<String> storageComboBox;

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
    private TextField storageId;

    @FXML
    private TextField storageName;

    @FXML
    private TextField storageNumber;

    @FXML
    private TextField storagePrice;

    @FXML
    private DatePicker storageDate;

    @FXML
    private TextField storageTime;

    @FXML
    public ToggleButton storageToggleButton;


    @FXML
    public void OnStorageLabelClicked() {
        try {
            for (ToggleButton toggleButton : Application.shareLabelButton) {
                if(toggleButton!=null){
                    toggleButton.setSelected(false);
                }
            }
            storageToggleButton.setSelected(true);


            mainController.VboxTableInfo.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/storage/TableView.fxml"));

            Parent element = fxmlLoader.load();

            mainController.VboxTableInfo.getChildren().add(element);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onSearchButtonClicked() {


        String inputText = userSearchField.getText();

        String comboBoxValue = storageComboBox.getValue();

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
    public void onStorageButtonClicked() {

        int selectCount = 0;

        ObservableList<ProductsListSelected> storageTableViewItems = storageTableView.getItems();
        for (ProductsListSelected storageTableViewItem : storageTableViewItems) {
            if (storageTableViewItem.isSelected()) {
                selectCount++;
            }
        }
        if (selectCount == 0 || selectCount > 1) {
            PopupWindow.alertWindow("只能同时选中一行单元,或者没有选择单元格");
            return;
        }
        storageId.setText(String.valueOf(storageTableViewItems.get(selectColumn).getId()));
        storageName.setText(storageTableViewItems.get(selectColumn).getName());
        storagePrice.setText(String.valueOf(storageTableViewItems.get(selectColumn).getPrice()));

        System.out.println(selectColumn);
        System.out.println(selectCount);
    }


    @FXML
    public void OnConfirmButtonClicked() {
        try {

            storageDate.setConverter(new StringConverter<LocalDate>() {
                @Override
                public String toString(LocalDate object) {
                    if (object != null) return object.toString();
                    return null;
                }

                @Override
                public LocalDate fromString(String string) {
                    if (string != null) {

                        LocalDate localDate;
                        try {
                            localDate = LocalDate.parse(string);
                        } catch (Exception e) {
                            PopupWindow.alertWindow("输入的日期有误,请重新输入");
                            return null;

                        }
                        return localDate;
                    }
                    return null;
                }

            });


        } catch (Exception e) {
            e.printStackTrace();


        }
        if (storageId.getText() == null ||
                storageName.getText() == null ||
                storageNumber.getText() == null ||
                storagePrice.getText() == null ||
                storageDate.getValue() == null ||
                storageTime.getText() == null
        ) {
            PopupWindow.alertWindow("请输入数据");
            return;
        }


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date time;
        StorageStock storageStock = new StorageStock();
        try {
            time = simpleDateFormat.parse(storageTime.getText());

            storageStock.setStoragePrice(Double.valueOf(storagePrice.getText()));
            storageStock.setStorageTime(storageDate.getValue() + " " + simpleDateFormat.format(time));
            storageStock.setStockId(Integer.valueOf(storageId.getText()));
            storageStock.setStorageVolume(Integer.valueOf(storageNumber.getText()));

            storageStock.setStorageEmployeeId(Application.userInfoReceiveData.getData().getUserInfo().getId());

            System.out.println(storageStock);

        } catch (ParseException e) {
            PopupWindow.alertWindow("输入的时间格式有误,请重新输入");
            return;
        }


        BackendResource<StorageStock> backendResource = new BackendResource<>();
        HttpResponseData responseData = backendResource.postRequest("/storage/storageStock", storageStock);

        System.out.println(responseData);
        if (!Objects.equals(responseData.getCode(), HTTPStatusEnums.OK.getCode())) {

            PopupWindow.alertWindow("存储失败", responseData.getMessage());
            return;
        }
        PopupWindow.informationWindow("存储成功");
        showAllProducts();
    }

    @FXML
    public void onCheckColumnCheckBoxClicked() {

    }


    public void initialize() {

        mainController = (MainController) Application.shareController.get(MainController.class.getSimpleName());
        Application.shareController.put(StorageController.class.getSimpleName(), this);

        if (storageToggleButton != null)
            Application.shareLabelButton.add(storageToggleButton);



        if (storageTableView != null) {
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

        if (storageComboBox != null) {
            storageComboBox.getItems().addAll("编号", "商品名", "当前库存", "价格");
            storageComboBox.setValue("编号");
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
        storageTableView.setItems(observableArrayList);
    }
}

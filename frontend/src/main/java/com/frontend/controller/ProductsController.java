package com.frontend.controller;

import com.frontend.Application;
import com.frontend.entity.ProductsList;
import com.frontend.entity.ProductsListSelected;
import com.frontend.entity.ProductsReceiveData;
import com.frontend.model.ProductsModel;
import com.frontend.utils.PopupWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ProductsController {

    @FXML
    public TableView<ProductsListSelected> productsTableView;
    @FXML
    public TextField userSearchField;
    @FXML
    public Button searchBut;
    @FXML
    public Button resetBut;
    @FXML
    public Button addBut;
    @FXML
    public Button delBut;

    @FXML
    public Button saveBut;

    @FXML
    private ComboBox<String> productsComboBox;


    @FXML
    private TableColumn<ProductsListSelected, Number> id;

    @FXML
    private TableColumn<ProductsListSelected, String> name;

    @FXML
    private TableColumn<ProductsListSelected, Number> reserveNow;

    @FXML
    private TableColumn<ProductsListSelected, Number> reserveMin;

    @FXML
    private TableColumn<ProductsListSelected, Double> price;

    @FXML
    private TableColumn<ProductsListSelected, String> vendor;

    @FXML
    private TableColumn<ProductsListSelected, String> introduction;

    @FXML
    private TableColumn<ProductsListSelected, Boolean> checkColumn;

    @FXML
    private CheckBox checkColumnCheckBox;


    private MainController mainController;

    //当前正在修改的行
    private Integer editeColumn;


    //在转换类型之前做一个回滚备份
    private Number beforeToString;

    @FXML
    public void OnProductsLabelClicked() {

        try {

            mainController.VboxTableInfo.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/ProductsTableView.fxml"));

            Parent element = fxmlLoader.load();

            mainController.VboxTableInfo.getChildren().add(element);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onRestButtonClicked() {
        showAllProducts();

    }

    @FXML
    public void onSearchButtonClicked() {


        String inputText = userSearchField.getText();

        String comboBoxValue = productsComboBox.getValue();


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
        } else if (Objects.equals(comboBoxValue, "最低库存")) {
            queryType = "reserve_min";
        } else if (Objects.equals(comboBoxValue, "价格")) {
            queryType = "price";
        } else if (Objects.equals(comboBoxValue, "制造商")) {
            queryType = "vendor";
        } else if (Objects.equals(comboBoxValue, "商品描述")) {
            queryType = "introduction";
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
    public void onAddButtonClicked() {
        Stage productsSubWindows = new Stage();

        Scene scene;
        try {
            scene = new Scene(new FXMLLoader(Application.class.getResource("view/ProductsSubWindow.fxml")).load());

            productsSubWindows.setTitle("添加单元格");
            productsSubWindows.initOwner(Application.mainStage);
            productsSubWindows.initModality(Modality.WINDOW_MODAL);

            productsSubWindows.setScene(scene);
            productsSubWindows.setResizable(false);
            productsSubWindows.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onDelButtonClicked() {
        for (int i = 0; i < 10; i++)
            System.out.println(" ");
    }

    @FXML
    public void onSaveButtonClicked() {
        if (editeColumn != null) {
            System.out.println("save");
            editeColumn = null;
        }else{
            PopupWindow.alertWindow("没有未保存的单元");
        }
    }

    @FXML
    private void onCheckColumnCheckBoxClicked() {
        System.out.println("click");
        System.out.println(checkColumnCheckBox);


    }

    @FXML
    private void onUpdateButtonClicked() {


        ObservableList<ProductsListSelected> productsTableViewItems = productsTableView.getItems();

        int selected = 0;

        for (int i = 0; i < productsTableViewItems.size(); i++) {


            if (productsTableViewItems.get(i).isSelected()) {
                selected++;
                System.out.println(productsTableViewItems.get(i));
                editeColumn = i;
            }

        }
        if (selected > 1) {
            PopupWindow.alertWindow("只能同时编辑一行");
            editeColumn = null;

        } else if (selected == 0) {
            PopupWindow.alertWindow("请选择一行进行编辑");
            editeColumn = null;
        }


        System.out.println("editeColumn" + editeColumn);
    }


    public void showAllProducts() {
        setTableData(ProductsModel.getAllProducts());
    }


    public void initialize() {

        mainController = (MainController) Application.shareController.get(MainController.class.getSimpleName());
        Application.shareController.put(ProductsController.class.getSimpleName(),this);


        if (productsComboBox != null) {
            productsComboBox.getItems().addAll("编号", "商品名", "当前库存", "最低库存", "价格", "制造商", "商品描述");
            productsComboBox.setValue("编号");
        }

        if (productsTableView != null) {

            showAllProducts();

            StringConverter<Number> numberToString = new StringConverter<>() {
                @Override
                public String toString(Number number) {
                    beforeToString = number;
                    return number.toString();
                }

                @Override
                public Long fromString(String string) {
                    try {
                        return Long.parseLong(string);

                    } catch (Exception formatException) {
                        PopupWindow.alertWindow("类型输入错误，只能输入数值型");
                        return Long.parseLong(beforeToString.toString());
                    }
                }
            };

            StringConverter<Double> doubleToString = new StringConverter<>() {
                @Override
                public String toString(Double aDouble) {
                    beforeToString = aDouble;
                    return aDouble.toString();
                }

                @Override
                public Double fromString(String string) {
                    try {
                        return Double.parseDouble(string);

                    } catch (Exception formatException) {
                        PopupWindow.alertWindow("类型输入错误，只能输入数值型");
                        return beforeToString.doubleValue();
                    }
                }
            };

            StringConverter<Object> objectToSting = new StringConverter<>() {


                @Override
                public String toString(Object object) {
                    return object.toString();
                }

                @Override
                public Object fromString(String string) {
                    return String.format(string);
                }
            };

            Callback<TableColumn<ProductsListSelected, String>, TableCell<ProductsListSelected, String>> stringTextFieldCallBack = new Callback<>() {
                @Override
                public TextFieldTableCell<ProductsListSelected, String> call(TableColumn<ProductsListSelected, String> param) {

                    return new TextFieldTableCell(objectToSting) {
                        @Override
                        public void startEdit() {

                            System.out.println("startEdit");

                            if (editeColumn != null && getIndex() == editeColumn) {
                                super.startEdit();
                            }
                        }
                    };
                }
            };

            Callback<TableColumn<ProductsListSelected, Number>, TableCell<ProductsListSelected, Number>> numberTextFieldCallBack = new Callback<>() {
                @Override
                public TextFieldTableCell<ProductsListSelected, Number> call(TableColumn<ProductsListSelected, Number> param) {

                    return new TextFieldTableCell(numberToString) {
                        @Override
                        public void startEdit() {

                            System.out.println("startEdit");

                            if (editeColumn != null && getIndex() == editeColumn) {
                                super.startEdit();
                            }
                        }
                    };
                }
            };

            Callback<TableColumn<ProductsListSelected, Double>, TableCell<ProductsListSelected, Double>> doubleTextFieldCallBack = new Callback<>() {
                @Override
                public TextFieldTableCell<ProductsListSelected, Double> call(TableColumn<ProductsListSelected, Double> param) {

                    return new TextFieldTableCell(doubleToString) {
                        @Override
                        public void startEdit() {

                            System.out.println("startEdit");

                            if (editeColumn != null && getIndex() == editeColumn) {
                                super.startEdit();
                            }
                        }
                    };
                }
            };

            Callback<TableColumn<ProductsListSelected, Boolean>, TableCell<ProductsListSelected, Boolean>> checkBoxCallBack = new Callback<>() {
                @Override
                public CheckBoxTableCell<ProductsListSelected, Boolean> call(TableColumn<ProductsListSelected, Boolean> param) {

                    return new CheckBoxTableCell<ProductsListSelected, Boolean>() {

                        @Override
                        public void updateItem(Boolean item, boolean empty) {

                            if (item != null) {
                                if (editeColumn != null) {
                                    PopupWindow.alertWindow("有未保存的编辑，请保存后再选择其他单元格");

                                    setSelect(getIndex(), false);

                                    System.out.println(item + " " + empty);

                                    return;
                                }
                                System.out.println(getIndex());
                                System.out.println(item);
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
            reserveMin.setCellValueFactory(new PropertyValueFactory<>("reserveMin"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            vendor.setCellValueFactory(new PropertyValueFactory<>("vendor"));
            introduction.setCellValueFactory(new PropertyValueFactory<>("introduction"));


            checkColumn.setCellFactory(checkBoxCallBack);


            name.setCellFactory(stringTextFieldCallBack);
            reserveNow.setCellFactory(numberTextFieldCallBack);
            reserveMin.setCellFactory(numberTextFieldCallBack);
            price.setCellFactory(doubleTextFieldCallBack);
            vendor.setCellFactory(stringTextFieldCallBack);
            introduction.setCellFactory(stringTextFieldCallBack);

            name.setEditable(true);
            reserveNow.setEditable(true);
            reserveMin.setEditable(true);
            price.setEditable(true);
            vendor.setEditable(true);
            introduction.setEditable(true);


//            reserveNow.setCellFactory(TextFieldTableCell.forTableColumn(numberToString));
//            reserveMin.setCellFactory(TextFieldTableCell.forTableColumn(numberToString));
//            price.setCellFactory(TextFieldTableCell.forTableColumn(doubleToString));
//            vendor.setCellFactory(TextFieldTableCell.forTableColumn());
//            introduction.setCellFactory(TextFieldTableCell.forTableColumn());


            productsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductsListSelected>() {
                @Override
                public void changed(ObservableValue<? extends ProductsListSelected> observable, ProductsListSelected oldValue, ProductsListSelected newValue) {
                    if (oldValue != null)

                        System.out.println(oldValue.isSelected());
                }
            });

        }

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


//        todo: 测试用例
//        productsListSelectedArrayList.get(0).setSelected(true);

        ObservableList<ProductsListSelected> observableArrayList = FXCollections.observableArrayList(productsListSelectedArrayList);

//        System.out.println(observableArrayList);

        productsTableView.setItems(observableArrayList);
    }

    private void setSelect(Integer index, Boolean value) {
        productsTableView.getItems().get(index).setSelected(value);
    }


}



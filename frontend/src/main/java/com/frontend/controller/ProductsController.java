package com.frontend.controller;

import com.frontend.Application;
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
        ProductsListSelected focusedItem = productsTableView.getFocusModel().getFocusedItem();
        System.out.println("id" + focusedItem.getId());

        System.out.println("name" + focusedItem.getName());

        System.out.println("isSelected" + focusedItem.isSelected());
    }

    @FXML
    public void onDelButtonClicked() {
        for (int i = 0; i < 10; i++)
            System.out.println(" ");
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

            StringConverter<Object> objectToSting  = new StringConverter<>() {


                @Override
                public String toString(Object object) {
                    return object.toString();
                }

                @Override
                public Object fromString(String string) {
                    return String.format(string);
                }
            };

            Callback<TableColumn<ProductsListSelected, String>, TableCell<ProductsListSelected, String>> callBackCellFactory =
                    new Callback<TableColumn<ProductsListSelected, String>, TableCell<ProductsListSelected, String>>() {

                        @Override
                        public TextFieldTableCell<ProductsListSelected, String> call(TableColumn<ProductsListSelected, String> parme) {


                            return new TextFieldTableCell(objectToSting)
                            {
                                private void createEditor() {
//                            System.out.println(getItem());
                                    TextField textField = new TextField(getText());


//                            textField.setOnAction(event -> {
//                                commitEdit(textField.getText());
//
//                            });
                                    textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                                        System.out.println("observable");
                                        System.out.println(observable);

                                        if (!newValue) {
                                            setGraphic(null);

                                            setContentDisplay(ContentDisplay.LEFT);

                                            setText(textField.getText());

                                            System.out.println(productsTableView.getFocusModel().getFocusedCell().getColumn());

                                            System.out.println(getText());
                                        } else {
                                            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                                        }

                                    });

                                    setGraphic(textField);
                                    textField.requestFocus();
                                    System.out.println("setTextField");

//                            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//                            textField.selectAll();
                                }


//                                @Override
//                                public void updateItem(String item, boolean empty) {
//                                    System.out.println("update Item");
//                                    super.updateItem(item, empty);
////                                    if (empty || item == null) {
////                                        setText(null);
////                                    } else {
////                                        setText(item);
////                                    }
//                                }

                                @Override
                                public void  updateItem(Object o,boolean empty){
                                    System.out.println("update Item");
                                    super.updateItem( o, empty);


                                }

                                @Override
                                public void startEdit() {

                                    System.out.println("startEdit");

                                    if(getIndex() == editeColumn){

                                    super.startEdit();


                                    }

//                                    super.startEdit();

//                                    System.out.println("getGraphic" + getGraphic());
////                            setEditable(true);
//                                    setGraphic(null);
//                                    createEditor();
//
////                            if (!isEmpty()) {
////                                // 只允许某一行可编辑，其他行不可编辑
////                                if (getIndex() == 2) {
////                            setEditable(true);
//
////                                    createEditor();
////
////                                } else {
////                                    setEditable(false);
////                                }
////                            }
                                }

                                @Override
                                public void cancelEdit() {
                                    System.out.println("cancel Edit");
                                    super.cancelEdit();
//                            System.out.println("isEditing"+isEditing());

//                            setEditable(false);
//                            if (isEditing()) {
//                                Label label = new Label(getItem());
////
////                                setGraphic(label);
//                                setGraphic(label);
//
//                            }
                                }

//                                @Override
//                                public void commitEdit(String value) {
//                                    super.commitEdit(value);
//                                    System.out.println("commitEdit:" + value);
//                                    System.out.println("isEditing" + isEditing());
//                                    System.out.println(getIndex());
//                                }


                            };


                        }


                    };

//            Callback<TableColumn<ProductsListSelected, Boolean>, TableCell<ProductsListSelected, Boolean>> checkBoxCallBackCellFactory =
//                    new Callback<TableColumn<ProductsListSelected, Boolean>, TableCell<ProductsListSelected, Boolean>>() {
//
//
//                        @Override
//                        public TableCell<ProductsListSelected, Boolean> call(TableColumn<ProductsListSelected, Boolean> productsListBooleanTableColumn) {
//
//
//                            return new CheckBoxTableCell<>() {
//
//                                @Override
//                                public void updateItem(Boolean item, boolean empty) {
//                                    System.out.println("CheckBoxTableCell update");
//                                    setEditable(true);
//                                    super.updateItem(item, empty);
//
////                                    System.out.println("CheckBoxTableCell update");
//
//                                }
//
//                                ;
////
////                            checkBoxTableCell.addEventFilter(MouseEvent.MOUSE_CLICKED,event->{
////                                System.out.println(checkBoxTableCell.getItem());
////                                checkBoxTableCell.setItem(true);
////                                ObservableList<ProductsListSelected> productsTableViewItems = productsTableView.getItems();
////                                System.out.println(productsTableViewItems);
////                                System.out.println("clicked");
////                                System.out.println(checkColumn.getCellData(0));
////                                System.out.println("checkColumn"+checkColumn.getText());
////                            });
////
////                            return checkBoxTableCell;
////                            return new TableCell<>() {
////                                private final CheckBox checkBox = new CheckBox();
////
////                                {
////
////
////                                    checkBox.setOnMouseClicked(event -> {
////
////                                        System.out.println(productsListBooleanTableColumn.getColumns());
////
////
//////                                        System.out.println(checkBox);
////
////
//////                                        System.out.println(productsTableView.getRowFactory());
//////                                        System.out.println(productsTableView.getColumns());
//////                                        ObservableList<TableColumn<ProductsListSelected, ?>> tableColumns = productsTableView.getColumns();
//////                                        System.out.println(tableColumns.get(0).getColumns());
//////                                        System.out.println(tableColumns.get(0).getCellData(0));
////
////
////                                    });
////                                }
////
////                                @Override
////                                protected void updateItem(Boolean item, boolean empty) {
////                                    super.updateItem(item, empty);
////                                    if (!empty) {
////                                        checkBox.setSelected(item);
////                                        setGraphic(checkBox);
////                                    } else {
////                                        setGraphic(null);
////                                    }
////                                }
////                            }; };
//                            };
//                        }
//                    };


//            productsTableView.setEditable();

            checkColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            reserveNow.setCellValueFactory(new PropertyValueFactory<>("reserveNow"));
            reserveMin.setCellValueFactory(new PropertyValueFactory<>("reserveMin"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            vendor.setCellValueFactory(new PropertyValueFactory<>("vendor"));
            introduction.setCellValueFactory(new PropertyValueFactory<>("introduction"));


            checkColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkColumn));


            id.setCellFactory(TextFieldTableCell.forTableColumn(numberToString));


//            name.setCellFactory(TextFieldTableCell.forTableColumn());
            name.setCellFactory(callBackCellFactory);
            name.setEditable(true);



            reserveNow.setCellFactory(TextFieldTableCell.forTableColumn(numberToString));
            reserveMin.setCellFactory(TextFieldTableCell.forTableColumn(numberToString));
            price.setCellFactory(TextFieldTableCell.forTableColumn(doubleToString));
            vendor.setCellFactory(TextFieldTableCell.forTableColumn());
            introduction.setCellFactory(TextFieldTableCell.forTableColumn());


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
        productsListSelectedArrayList.get(0).setSelected(true);

        ObservableList<ProductsListSelected> observableArrayList = FXCollections.observableArrayList(productsListSelectedArrayList);

//        System.out.println(observableArrayList);

        productsTableView.setItems(observableArrayList);
    }


}



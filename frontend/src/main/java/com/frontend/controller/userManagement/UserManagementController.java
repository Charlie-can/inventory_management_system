package com.frontend.controller.userManagement;

import com.frontend.Application;
import com.frontend.controller.MainController;
import com.frontend.entity.*;
import com.frontend.utils.BackendResource;
import com.frontend.utils.PopupWindow;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class UserManagementController {

    @FXML
    public TableColumn employee;
    @FXML
    public TableColumn authority;
    @FXML
    private ComboBox<String> userManagementComboBox;
    @FXML
    private TextField userManagementSearchField;
    @FXML
    private Button searchBut;
    @FXML
    private Button resetBut;
    @FXML
    private Button addBut;
    @FXML
    private Button delBut;
    @FXML
    private Button updateBut;
    @FXML
    private Button saveBut;
    @FXML
    private TableView<UserInfoListSelected> userManagementTableView;
    @FXML
    private CheckBox checkColumnCheckBox;
    @FXML
    private TableColumn<UserInfoListSelected, Boolean> checkColumn;
    @FXML
    private TableColumn<UserInfoListSelected, Integer> id;
    @FXML
    private TableColumn<UserInfoListSelected, String> name;
    @FXML
    private TableColumn<UserInfoListSelected, String> sex;
    @FXML
    public TableColumn<UserInfoListSelected, String> password;
    @FXML
    private TableColumn<UserInfoListSelected, String> phone;
    @FXML
    private TableColumn<UserInfoListSelected, String> birthday;
    @FXML
    private TableColumn<UserInfoListSelected, String> hiredate;
    @FXML
    private TableColumn<UserInfoListSelected, Boolean> admin;
    @FXML
    private TableColumn<UserInfoListSelected, Boolean> stockmanager;
    @FXML
    private TableColumn<UserInfoListSelected, Boolean> salesperson;
    @FXML
    private TableColumn<UserInfoListSelected, Boolean> storageperson;
    @FXML
    private TableColumn<UserInfoListSelected, Boolean> inventoryperson;
    @FXML
    private MainController mainController;
    @FXML
    private ToggleButton userManagementToggleButton;

    private Integer editeColumn;

    private Number beforeToString;

    private Boolean authroityInit;

    @FXML
    public void OnUserManagementLabelClicked(MouseEvent mouseEvent) {
        try {
            for (ToggleButton toggleButton : Application.shareLabelButton) {
                if (toggleButton != null) {
                    toggleButton.setSelected(false);
                }
            }
            userManagementToggleButton.setSelected(true);


            mainController.VboxTableInfo.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/userManagement/TableView.fxml"));

            Parent element = fxmlLoader.load();

            mainController.VboxTableInfo.getChildren().add(element);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML

    public void onSearchButtonClicked(ActionEvent actionEvent) {
        if (editeColumn != null) {
            PopupWindow.alertWindow("请先保存");
            return;
        }
        String inputText = userManagementSearchField.getText();

        String comboBoxValue = userManagementComboBox.getValue();

        if (inputText.isEmpty()) {
            PopupWindow.alertWindow("未输入查询内容");
            return;
        }
        String queryType;
        if (Objects.equals(comboBoxValue, "用户编号")) {
            queryType = "id";
        } else if (Objects.equals(comboBoxValue, "用户名")) {
            queryType = "name";
        } else if (Objects.equals(comboBoxValue, "性别")) {
            queryType = "sex";
            if (inputText.equals("男")) {
                inputText = "true";
            } else if (inputText.equals("女")) {
                inputText = "false";
            } else {
                PopupWindow.alertWindow("性别只能为男或女");
                return;
            }

        } else if (Objects.equals(comboBoxValue, "手机号")) {
            queryType = "phone";
        } else if (Objects.equals(comboBoxValue, "管理员")) {
            queryType = "admin";
            if (inputText.equals("是")) {
                inputText = "true";
            } else if (inputText.equals("否")) {
                inputText = "false";
            } else {
                PopupWindow.alertWindow("输入只能为是或否");
                return;
            }

        } else if (Objects.equals(comboBoxValue, "商品销售")) {
            if (inputText.equals("是")) {
                inputText = "true";
            } else if (inputText.equals("否")) {
                inputText = "false";
            } else {
                PopupWindow.alertWindow("输入只能为是或否");
                return;
            }
            queryType = "salesperson";
        } else if (Objects.equals(comboBoxValue, "商品存储")) {
            if (inputText.equals("是")) {
                inputText = "true";
            } else if (inputText.equals("否")) {
                inputText = "false";
            } else {
                PopupWindow.alertWindow("输入只能为是或否");
                return;
            }
            queryType = "storageperson";
        } else if (Objects.equals(comboBoxValue, "商品盘查")) {
            if (inputText.equals("是")) {
                inputText = "true";
            } else if (inputText.equals("否")) {
                inputText = "false";
            } else {
                PopupWindow.alertWindow("输入只能为是或否");
                return;
            }
            queryType = "inventoryperson";
        } else if (Objects.equals(comboBoxValue, "库存管理")) {
            if (inputText.equals("是")) {
                inputText = "true";
            } else if (inputText.equals("否")) {
                inputText = "false";
            } else {
                PopupWindow.alertWindow("输入只能为是或否");
                return;
            }
            queryType = "stockmanager";

        } else {
            PopupWindow.alertWindow("输入错误");
            return;
        }


        BackendResource<UserManagementReceiveData> userManagementReceiveDataBackendResource = new BackendResource<>();
        UserManagementReceiveData userManagementReceiveData = userManagementReceiveDataBackendResource.queryTableList("/userManagement/queryUserInfo", UserManagementReceiveData.class, queryType, inputText);

        if (userManagementReceiveData != null) {
            showAllList(userManagementReceiveData);
        }


    }

    @FXML

    public void onRestButtonClicked(ActionEvent actionEvent) {
        if (editeColumn != null) {
            PopupWindow.alertWindow("请先保存");
            return;
        }
        showAllList();
    }

    @FXML

    public void onAddButtonClicked(ActionEvent actionEvent) {
        if (editeColumn != null) {
            PopupWindow.alertWindow("请先保存");
            return;
        }
        UserManagementSubWindowController.PopupSubWindow();
    }

    @FXML

    public void onDelButtonClicked(ActionEvent actionEvent) {
        if (editeColumn != null) {
            PopupWindow.alertWindow("请先保存");
            return;
        }

        ArrayList<Integer> idList = new ArrayList<>();
        for (UserInfoListSelected viewItem : userManagementTableView.getItems()) {

            if (viewItem.isSelected()) {
                idList.add(viewItem.getId());
            }

        }

        if (idList.isEmpty()) {
            PopupWindow.alertWindow("请选择一条商品删除");
            return;
        }


        System.out.println(idList);
        HashMap<String, ArrayList<Integer>> idListMap = new HashMap<>();
        idListMap.put("idList", idList);

        BackendResource<HashMap<String, ArrayList<Integer>>> hashMapBackendResource = new BackendResource<>();

        HttpResponseData httpResponseData = hashMapBackendResource.postRequest("/userManagement/deleteUser", idListMap);

        System.out.println(httpResponseData);
        if (!Objects.equals(httpResponseData.getCode(), HTTPStatusEnums.OK.getCode())) {
            PopupWindow.alertWindow("可能有部分删除失败");
            showAllList();
            return;
        }

        PopupWindow.informationWindow("删除成功");
        showAllList();

    }


    @FXML
    public void onUpdateButtonClicked(ActionEvent actionEvent) {

        if (editeColumn != null) {
            PopupWindow.alertWindow("请保存上次编辑");
            return;
        }

        ObservableList<UserInfoListSelected> userManagementTableViewItems = userManagementTableView.getItems();

        int selected = 0;

        for (int i = 0; i < userManagementTableViewItems.size(); i++) {


            if (userManagementTableViewItems.get(i).isSelected()) {
                selected++;
//                System.out.println(productsTableViewItems.get(i));
                editeColumn = i;
            }

        }
        if (selected > 1) {
            PopupWindow.alertWindow("只能同时编辑一行");
            editeColumn = null;
            return;

        } else if (selected == 0) {
            PopupWindow.alertWindow("请选择一行进行编辑");
            editeColumn = null;
            return;
        }

        System.out.println("editeColumn" + editeColumn);


        admin.setEditable(true);
        stockmanager.setEditable(true);
        salesperson.setEditable(true);
        storageperson.setEditable(true);
        inventoryperson.setEditable(true);


        setTableSortable(false);
        setTableReorderable(false);


    }


    @FXML

    public void onSaveButtonClicked(ActionEvent actionEvent) {
        if (editeColumn == null) {
            PopupWindow.alertWindow("当前没有需要保存的行");
            return;
        }
        System.out.println(userManagementTableView.getItems().get(editeColumn));


        admin.setEditable(false);
        stockmanager.setEditable(false);
        salesperson.setEditable(false);
        storageperson.setEditable(false);
        inventoryperson.setEditable(false);

        setTableSortable(true);
        setTableReorderable(true);


        UserInfoListSelected userInfoListSelected = userManagementTableView.getItems().get(editeColumn);
        UserInfoList userInfoList = new UserInfoList(userInfoListSelected);
        BackendResource<UserInfoList> backendResource = new BackendResource<>();
        HttpResponseData responseData = backendResource.postRequest("/userManagement/updateUserInfo", userInfoList);
        if (!Objects.equals(responseData.getCode(), HTTPStatusEnums.OK.getCode())) {
            PopupWindow.alertWindow("更新失败", responseData.getMessage());
        } else {
            showAllList();
            PopupWindow.alertWindow("保存成功");
        }
        editeColumn = null;

    }

    @FXML
    public void onCheckColumnCheckBoxClicked(ActionEvent actionEvent) {
    }


    public void showAllList() {


        BackendResource<UserManagementReceiveData> httpResponseDataBackendResource = new BackendResource<>();

        ArrayList<UserManagementReceiveData.Data.UserInfoList> userInfoList = httpResponseDataBackendResource.getRequest("/userManagement/getUserInfoList", UserManagementReceiveData.class).getData().getUserInfoList();

        ArrayList<UserInfoListSelected> userInfoListSelecteds = new ArrayList<>();

        for (UserManagementReceiveData.Data.UserInfoList infoList : userInfoList) {
            userInfoListSelecteds.add(new UserInfoListSelected(infoList));
        }
        ObservableList<UserInfoListSelected> observableArrayList = FXCollections.observableArrayList(userInfoListSelecteds);

        userManagementTableView.setItems(observableArrayList);

    }

    public void showAllList(UserManagementReceiveData userManagementReceiveData) {

        ArrayList<UserManagementReceiveData.Data.UserInfoList> userInfoList = userManagementReceiveData.getData().getUserInfoList();

        ArrayList<UserInfoListSelected> userInfoListSelecteds = new ArrayList<>();

        for (UserManagementReceiveData.Data.UserInfoList infoList : userInfoList) {
            userInfoListSelecteds.add(new UserInfoListSelected(infoList));
        }
        ObservableList<UserInfoListSelected> observableArrayList = FXCollections.observableArrayList(userInfoListSelecteds);

        userManagementTableView.setItems(observableArrayList);

    }


    public void initialize() {

        Application.shareController.put(UserManagementController.class.getSimpleName(), this);
        mainController = (MainController) Application.shareController.get(MainController.class.getSimpleName());


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

        Callback<TableColumn<UserInfoListSelected, String>, TableCell<UserInfoListSelected, String>> stringTextFieldCallBack = new Callback<>() {
            @Override
            public TextFieldTableCell<UserInfoListSelected, String> call(TableColumn<UserInfoListSelected, String> param) {

                return new TextFieldTableCell(objectToSting) {
                    @Override
                    public void startEdit() {
                        System.out.println("start");
//                            System.out.println("startEdit");

                        if (editeColumn != null && getIndex() == editeColumn) {
                            super.startEdit();
                        }
                    }
                };
            }
        };

        Callback<TableColumn<UserInfoListSelected, String>, TableCell<UserInfoListSelected, String>> stringDateCallBack = new Callback<>() {

            @Override
            public TextFieldTableCell<UserInfoListSelected, String> call(TableColumn<UserInfoListSelected, String> param) {

                return new TextFieldTableCell(objectToSting) {
                    @Override
                    public void startEdit() {
                        if (editeColumn != null && getIndex() == editeColumn) {
                            super.startEdit();
                        }
                    }


                    @Override
                    public void commitEdit(Object newValue) {
                        System.out.println(newValue);
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                        LocalDate parse = null;

                        try {
                            parse = LocalDate.parse(newValue.toString(), dateTimeFormatter);
                        } catch (DateTimeParseException e) {
                            PopupWindow.alertWindow("日期格式输入错误");
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        super.commitEdit(parse.toString());
                    }
                };
            }
        };


        Callback<TableColumn<UserInfoListSelected, Number>, TableCell<UserInfoListSelected, Number>> numberTextFieldCallBack = new Callback<>() {
            @Override
            public TextFieldTableCell<UserInfoListSelected, Number> call(TableColumn<UserInfoListSelected, Number> param) {

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

        Callback<TableColumn<UserInfoListSelected, Double>, TableCell<UserInfoListSelected, Double>> doubleTextFieldCallBack = new Callback<>() {
            @Override
            public TextFieldTableCell<UserInfoListSelected, Double> call(TableColumn<UserInfoListSelected, Double> param) {

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


        Callback<TableColumn<UserInfoListSelected, Boolean>, TableCell<UserInfoListSelected, Boolean>> checkBoxCallBack = new Callback<>() {
            @Override
            public CheckBoxTableCell<UserInfoListSelected, Boolean> call(TableColumn<UserInfoListSelected, Boolean> param) {

                return new CheckBoxTableCell<UserInfoListSelected, Boolean>() {

                    @Override
                    public void updateItem(Boolean item, boolean empty) {
                        if (item != null) {
                            if (editeColumn != null) {
                                PopupWindow.alertWindow("有未保存的编辑，请保存后再选择其他单元格");
                                setSelect(getIndex(), getItem());
                                return;
                            }

//                                System.out.println(getIndex());
//                                System.out.println(item);
                        }
                        super.updateItem(item, empty);

                    }

                };
            }
        };

        Callback<TableColumn<UserInfoListSelected, Boolean>, TableCell<UserInfoListSelected, Boolean>> authoritycheckBoxCallBack = new Callback<>() {
            @Override
            public CheckBoxTableCell<UserInfoListSelected, Boolean> call(TableColumn<UserInfoListSelected, Boolean> param) {

                return new CheckBoxTableCell<UserInfoListSelected, Boolean>() {

                    @Override
                    public void updateItem(Boolean item, boolean empty) {

                        if (item != null && !empty) {
                            if (editeColumn == null) {
                                super.updateItem(item, empty);
                                return;
                            }

                            if (getIndex() != editeColumn) {

                                PopupWindow.alertWindow("有未保存的行");
                                if (getId().equals("admin")) {
                                    userManagementTableView.getItems().get(getIndex()).setAdmin(getItem());
                                } else if (getId().equals("stockmanager")) {
                                    userManagementTableView.getItems().get(getIndex()).setStockmanager(getItem());

                                } else if (getId().equals("salesperson")) {
                                    userManagementTableView.getItems().get(getIndex()).setSalesperson(getItem());

                                } else if (getId().equals("storageperson")) {
                                    userManagementTableView.getItems().get(getIndex()).setStorageperson(getItem());

                                } else if (getId().equals("inventoryperson")) {
                                    userManagementTableView.getItems().get(getIndex()).setInventoryperson(getItem());
                                }
                            }


                        }
                        super.updateItem(item, empty);

                    }

                };
            }

        };

        if (userManagementToggleButton != null) Application.shareLabelButton.add(userManagementToggleButton);


        if (userManagementComboBox != null) {
            userManagementComboBox.getItems().addAll("用户编号", "用户名", "性别", "手机号", "管理员", "商品销售", "商品存储", "商品盘查", "库存管理");
            userManagementComboBox.setValue("用户编号");
        }


        if (userManagementTableView != null) {

            checkColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
            checkColumn.setCellFactory(checkBoxCallBack);


            id.setCellValueFactory(new PropertyValueFactory<>("id"));

            name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserInfoListSelected, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<UserInfoListSelected, String> param) {
                    return param.getValue().getEmployee().nameProperty();
                }
            });
            sex.setCellValueFactory(cellData -> cellData.getValue().getEmployee().sexProperty());
            phone.setCellValueFactory(cellData -> cellData.getValue().getEmployee().phoneProperty());
            birthday.setCellValueFactory(cellData -> cellData.getValue().getEmployee().birthdayProperty());
            hiredate.setCellValueFactory(cellData -> cellData.getValue().getEmployee().hiredateProperty());


            password.setCellValueFactory(new PropertyValueFactory<>("password"));

            admin.setCellValueFactory(new PropertyValueFactory<>("admin"));
            stockmanager.setCellValueFactory(new PropertyValueFactory<>("stockmanager"));
            salesperson.setCellValueFactory(new PropertyValueFactory<>("salesperson"));
            storageperson.setCellValueFactory(new PropertyValueFactory<>("storageperson"));
            inventoryperson.setCellValueFactory(new PropertyValueFactory<>("inventoryperson"));

            admin.setEditable(false);
            stockmanager.setEditable(false);
            salesperson.setEditable(false);
            storageperson.setEditable(false);
            inventoryperson.setEditable(false);


            name.setCellFactory(stringTextFieldCallBack);
            phone.setCellFactory(stringTextFieldCallBack);
            password.setCellFactory(stringTextFieldCallBack);

            sex.setCellFactory(new Callback<>() {
                @Override
                public TextFieldTableCell<UserInfoListSelected, String> call(TableColumn<UserInfoListSelected, String> param) {

                    return new TextFieldTableCell(objectToSting) {
                        @Override
                        public void startEdit() {
                            System.out.println("start");
//                            System.out.println("startEdit");

                            if (editeColumn != null && getIndex() == editeColumn) {
                                super.startEdit();
                            }
                        }


                        @Override
                        public void commitEdit(Object newValue) {
                            if (newValue.equals("男")) {
                                super.commitEdit("1");

                            } else if (newValue.equals("女")) {
                                super.commitEdit("0");

                            } else PopupWindow.alertWindow("只能输入男或女");
                        }

                        @Override
                        public void updateItem(Object item, boolean empty) {

                            if (!empty) {
                                if (item.equals("1")) item = "男";
                                else if (item.equals("0")) item = "女";
                            }
                            super.updateItem(item, empty);
                        }
                    };
                }
            });

            birthday.setCellFactory(stringDateCallBack);
            hiredate.setCellFactory(stringDateCallBack);


            admin.setCellFactory(authoritycheckBoxCallBack);
            stockmanager.setCellFactory(authoritycheckBoxCallBack);
            salesperson.setCellFactory(authoritycheckBoxCallBack);
            storageperson.setCellFactory(authoritycheckBoxCallBack);
            inventoryperson.setCellFactory(authoritycheckBoxCallBack);


            showAllList();


        }
    }

    private void setSelect(Integer index, Boolean value) {
        userManagementTableView.getItems().get(index).setSelected(value);
    }


    private void setTableSortable(Boolean value) {
        employee.setSortable(value);
        authority.setSortable(value);
        checkColumn.setSortable(value);
        id.setSortable(value);
        name.setSortable(value);
        password.setSortable(value);
        sex.setSortable(value);
        phone.setSortable(value);
        birthday.setSortable(value);
        hiredate.setSortable(value);
        admin.setSortable(value);
        stockmanager.setSortable(value);
        salesperson.setSortable(value);
        storageperson.setSortable(value);
        inventoryperson.setSortable(value);
    }

    private void setTableReorderable(Boolean value) {
        employee.setReorderable(value);
        authority.setReorderable(value);
        checkColumn.setReorderable(value);
        id.setReorderable(value);
        name.setReorderable(value);
        password.setReorderable(value);
        sex.setReorderable(value);
        phone.setReorderable(value);
        birthday.setReorderable(value);
        hiredate.setReorderable(value);
        admin.setReorderable(value);
        stockmanager.setReorderable(value);
        salesperson.setReorderable(value);
        storageperson.setReorderable(value);
        inventoryperson.setReorderable(value);
    }


}

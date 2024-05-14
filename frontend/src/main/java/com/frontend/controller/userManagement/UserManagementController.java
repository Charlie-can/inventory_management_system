package com.frontend.controller.userManagement;

import com.frontend.Application;
import com.frontend.controller.MainController;
import com.frontend.controller.storage.StorageController;
import com.frontend.entity.*;
import com.frontend.model.ProductsModel;
import com.frontend.utils.BackendResource;
import com.frontend.utils.PopupWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class UserManagementController {
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
    private TableView userManagementTableView;
    @FXML
    private CheckBox checkColumnCheckBox;
    @FXML
    private TableColumn checkColumn;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn sex;
    @FXML
    private TableColumn phone;
    @FXML
    private TableColumn birthday;
    @FXML
    private TableColumn hiredate;
    @FXML
    private TableColumn admin;
    @FXML
    private TableColumn stockmanager;
    @FXML
    private TableColumn salesperson;
    @FXML
    private TableColumn storageperson;
    @FXML
    private TableColumn inventoryperson;
    @FXML

    private MainController mainController;
    @FXML
    private ToggleButton userManagementToggleButton;


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
    }

    @FXML

    public void onRestButtonClicked(ActionEvent actionEvent) {
        showAllList();
    }

    @FXML

    public void onAddButtonClicked(ActionEvent actionEvent) {
    }

    @FXML

    public void onDelButtonClicked(ActionEvent actionEvent) {
    }

    @FXML

    public void onUpdateButtonClicked(ActionEvent actionEvent) {
    }

    @FXML

    public void onSaveButtonClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void onCheckColumnCheckBoxClicked(ActionEvent actionEvent) {
    }


    public void showAllList() {



        BackendResource<UserManagementReceiveData> httpResponseDataBackendResource = new BackendResource<>();

        UserManagementReceiveData request = httpResponseDataBackendResource.getRequest("/userManagement/getUserInfoList", UserManagementReceiveData.class);
        System.out.println(request);


//        setTableData(ProductsModel.getAllProducts());
    }


    private void setTableData(ProductsReceiveData allProducts) {





    }

    public void initialize() {

        Application.shareController.put(StorageController.class.getSimpleName(), this);
        mainController = (MainController) Application.shareController.get(MainController.class.getSimpleName());

        if (userManagementToggleButton != null) Application.shareLabelButton.add(userManagementToggleButton);


        if (userManagementComboBox != null) {
            userManagementComboBox.getItems().addAll("用户编号", "用户名", "手机号", "性别", "管理员", "库存管理", "商品销售", "商品存储");
            userManagementComboBox.setValue("用户编号");
        }


    }


}

package com.frontend.controller.userManagement;

import com.frontend.Application;
import com.frontend.controller.MainController;
import com.frontend.controller.storage.StorageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class UserManagementController {


    @FXML
    public ComboBox userManagementComboBox;

    @FXML
    public TextField userManagementSearchField;
    public Button searchBut;
    public Button resetBut;
    public Button addBut;
    public Button delBut;
    public Button updateBut;
    public Button saveBut;
    public TableView userManagementTableView;
    public CheckBox checkColumnCheckBox;
    public TableColumn checkColumn;
    public TableColumn id;
    public TableColumn name;
    public TableColumn sex;
    public TableColumn phone;
    public TableColumn birthday;
    public TableColumn hiredate;
    public TableColumn admin;
    public TableColumn stockmanager;
    public TableColumn salesperson;
    public TableColumn storageperson;
    public TableColumn inventoryperson;

    private MainController mainController;
    @FXML
    public ToggleButton userManagementToggleButton;




    @FXML
    public void OnUserManagementLabelClicked(MouseEvent mouseEvent) {
        try {
            for (ToggleButton toggleButton : Application.shareLabelButton) {
                if(toggleButton!=null){
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



    public void initialize() {

        Application.shareController.put(StorageController.class.getSimpleName(), this);
        mainController = (MainController) Application.shareController.get(MainController.class.getSimpleName());

        if (userManagementToggleButton != null)
            Application.shareLabelButton.add(userManagementToggleButton);
    }


    public void onSearchButtonClicked(ActionEvent actionEvent) {
    }

    public void onRestButtonClicked(ActionEvent actionEvent) {
    }

    public void onAddButtonClicked(ActionEvent actionEvent) {
    }

    public void onDelButtonClicked(ActionEvent actionEvent) {
    }

    public void onUpdateButtonClicked(ActionEvent actionEvent) {
    }

    public void onSaveButtonClicked(ActionEvent actionEvent) {
    }

    public void onCheckColumnCheckBoxClicked(ActionEvent actionEvent) {
    }
}

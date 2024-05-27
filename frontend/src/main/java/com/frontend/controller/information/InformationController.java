package com.frontend.controller.information;

import com.frontend.Application;
import com.frontend.controller.MainController;
import com.frontend.entity.HTTPStatusEnums;
import com.frontend.entity.HttpResponseData;
import com.frontend.entity.UserInfoList;
import com.frontend.entity.UserManagementReceiveData;
import com.frontend.utils.BackendResource;
import com.frontend.utils.PopupWindow;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

public class InformationController {

    @FXML
    public ToggleButton informationToggleButton;
    @FXML
    public TextField userName;
    @FXML
    public PasswordField password;
    @FXML
    public TextField phone;
    @FXML
    public ComboBox<String> sex;
    @FXML
    public DatePicker birthday;
    @FXML
    public DatePicker hiredate;
    @FXML
    public CheckBox adminCheckBox;
    @FXML
    public CheckBox stockmanagerCheckBox;
    @FXML
    public CheckBox inventorypersonCheckBox;
    @FXML
    public CheckBox storagepersonCheckBox;
    @FXML
    public CheckBox salespersonCheckBox;
    @FXML
    public Button confirmButton;

    private MainController mainController;


    @FXML
    public void OnInformationLabelClicked() {
        try {
            for (ToggleButton toggleButton : Application.shareLabelButton) {

                if (toggleButton != null) {
                    toggleButton.setSelected(false);
                }
            }

            if (informationToggleButton != null)
                informationToggleButton.setSelected(true);


            mainController.VboxTableInfo.getChildren().clear();

            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/information/TableView.fxml"));

            Parent element = fxmlLoader.load();

            mainController.VboxTableInfo.getChildren().add(element);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void onConfirmButtonClick() {
        {


            UserInfoList userInfoList = null;
            if (!userName.getText().equals("") && !password.getText().equals("") && !phone.getText().equals("") && sex.getValue() != null && birthday.getValue() != null && hiredate.getValue() != null&&phone.getText().length()==11) {


                int sexIn;
                if (sex.getValue().equals("男")) {
                    sexIn = 1;
                } else {
                    sexIn = 0;
                }
                userInfoList = new UserInfoList(password.getText(), adminCheckBox.isSelected(), salespersonCheckBox.isSelected(), storagepersonCheckBox.isSelected(), inventorypersonCheckBox.isSelected(), stockmanagerCheckBox.isSelected(), userName.getText(), sexIn, phone.getText(), hiredate.getValue().toString(), birthday.getValue().toString());
                userInfoList.setId(Application.userInfoReceiveData.getData().getUserInfo().getId());
                userInfoList.getEmployee().setId(userInfoList.getId());

            } else {
                PopupWindow.alertWindow("输入信息有误");
                return;
            }


            BackendResource<UserInfoList> httpResponseDataBackendResource = new BackendResource<>();


            HttpResponseData httpResponseData = httpResponseDataBackendResource.postRequest("/user/updateUserInfo", userInfoList);

            try {
                if (Objects.equals(httpResponseData.getCode(), HTTPStatusEnums.OK.getCode())) {

                    PopupWindow.informationWindow("修改成功");
                    showAll();
                }else{
                    PopupWindow.informationWindow("修改失败",httpResponseData.getMessage());


                }
            } catch (Exception e) {
                PopupWindow.alertWindow("修改失败");
            }

        }

    }

    private void showAll(){

        BackendResource<UserManagementReceiveData> userManagementReceiveDataBackendResource = new BackendResource<>();
        UserManagementReceiveData userManagementReceiveData = userManagementReceiveDataBackendResource.queryTableList("/userManagement/queryUserInfo", UserManagementReceiveData.class, "id", Application.userInfoReceiveData.getData().getUserInfo().getId().toString());
        UserManagementReceiveData.Data.UserInfoList userInfoList = userManagementReceiveData.getData().getUserInfoList().get(0);


        userName.setText(userInfoList.getEmployee().getName());
        password.setText(userInfoList.getPassword());
        phone.setText(userInfoList.getEmployee().getPhone());

        sex.setItems(FXCollections.observableArrayList("男", "女"));

        if (userInfoList.getEmployee().getSex().equals("1"))
            sex.setValue("男");
        else
            sex.setValue("女");


        birthday.setValue(userInfoList.getEmployee().getBirthday());
        hiredate.setValue(userInfoList.getEmployee().getHiredate());

        adminCheckBox.setSelected(userInfoList.getAdmin());
        inventorypersonCheckBox.setSelected(userInfoList.getInventoryperson());
        stockmanagerCheckBox.setSelected(userInfoList.getStockmanager());
        salespersonCheckBox.setSelected(userInfoList.getSalesperson());
        storagepersonCheckBox.setSelected(userInfoList.getStorageperson());

    }

    public void initialize() {
        if (informationToggleButton != null) Application.shareLabelButton.add(informationToggleButton);
        mainController = (MainController) Application.shareController.get(MainController.class.getSimpleName());


        if (userName != null) {
            StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                @Override
                public String toString(LocalDate date) {
                    if (date != null) {
                        return dateFormatter.format(date);
                    } else {
                        return "";
                    }
                }

                @Override
                public LocalDate fromString(String string) {
                    if (string != null && !string.isEmpty()) {

                        LocalDate parse;
                        try {
                            parse = LocalDate.parse(string, dateFormatter);
                        } catch (Exception e) {
                            PopupWindow.alertWindow("输入的时间格式有误", "输入的时间格式有误");

                            return null;

                        }
                        return parse;


                    } else {
                        return null;
                    }
                }


            };
            Callback<DatePicker, DateCell> dayCellFactory = new Callback<>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {

                            super.updateItem(item, empty);

                            if (item.isAfter(LocalDate.now())) {
                                setDisable(true);
                                setStyle("-fx-background-color: #9b9b9b;"); // 设置禁用日期的样式
                            }
                        }


                    };
                }


            };

            showAll();
            phone.setTextFormatter(new TextFormatter<>(change -> {
                if (Pattern.compile("\\d{0,11}").matcher(change.getControlNewText()).matches()) {
                    return change;
                } else {
                    return null;
                }
            }));

            birthday.setDayCellFactory(dayCellFactory);
            birthday.setConverter(converter);

            hiredate.setDayCellFactory(dayCellFactory);
            hiredate.setConverter(converter);
        }


    }
}

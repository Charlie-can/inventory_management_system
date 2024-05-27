package com.frontend.controller.userManagement;

import com.frontend.Application;
import com.frontend.entity.*;
import com.frontend.utils.BackendResource;
import com.frontend.utils.PopupWindow;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

public class UserManagementSubWindowController {

    static public Stage userManagementSubWindowStage;
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
    public Button adminButton;
    @FXML
    public CheckBox adminCheckBox;
    @FXML
    public Button stockmanagerButton;
    @FXML
    public CheckBox stockmanagerCheckBox;
    @FXML
    public Button inventorypersonButton;
    @FXML
    public CheckBox inventorypersonCheckBox;
    @FXML
    public Button storagepersonButton;
    @FXML
    public CheckBox storagepersonCheckBox;
    @FXML
    public Button salespersonButton;
    @FXML
    public CheckBox salespersonCheckBox;

    private UserManagementController userManagementController;


    @FXML
    public void onAdminButtonClick() {
        adminCheckBox.setSelected(!adminCheckBox.isSelected());
    }

    @FXML
    public void onStockmanagerButtonClick() {
        stockmanagerCheckBox.setSelected(!stockmanagerCheckBox.isSelected());


    }

    @FXML
    public void onInventorypersonButtonClick() {
        inventorypersonCheckBox.setSelected(!inventorypersonCheckBox.isSelected());

    }

    @FXML
    public void onStoragepersonButtonClick() {
        storagepersonCheckBox.setSelected(!storagepersonCheckBox.isSelected());
    }

    @FXML
    public void onSalespersonButtonClick() {
        salespersonCheckBox.setSelected(!salespersonCheckBox.isSelected());
    }


    @FXML
    public void onConfirmButtonClick() {


        UserInfoList userInfoList = null;
        if (!userName.getText().equals("") && !password.getText().equals("") && !phone.getText().equals("") && sex.getValue() != null && birthday.getValue() != null && hiredate.getValue() != null&&phone.getText().length()==11) {


            int sexIn;
            if (sex.getValue().equals("男")) {
                sexIn = 1;
            } else {
                sexIn = 0;
            }
            userInfoList = new UserInfoList(password.getText(), adminCheckBox.isSelected(), salespersonCheckBox.isSelected(), storagepersonCheckBox.isSelected(), inventorypersonCheckBox.isSelected(), stockmanagerCheckBox.isSelected(), userName.getText(), sexIn, phone.getText(), hiredate.getValue().toString(), birthday.getValue().toString());
            System.out.println(userInfoList);
        } else {
            PopupWindow.alertWindow("输入信息有误");
            return;
        }


        BackendResource<UserInfoList> httpResponseDataBackendResource = new BackendResource<>();


        HttpResponseData httpResponseData = httpResponseDataBackendResource.postRequest("/userManagement/insertUserInfo", userInfoList);

        try {
            if (Objects.equals(httpResponseData.getCode(), HTTPStatusEnums.OK.getCode())) {

                PopupWindow.informationWindow("成功添加");

                userManagementController.showAllList();
                userManagementSubWindowStage.close();
            }
        } catch (Exception e) {
            PopupWindow.alertWindow("添加失败");
        }

    }

    @FXML
    public void onCancelButtonClick() {
        userManagementSubWindowStage.close();
    }


    static public void PopupSubWindow() {

        userManagementSubWindowStage = new Stage();

        Scene UserManagementSubScene;
        try {
            UserManagementSubScene = new Scene(new FXMLLoader(Application.class.getResource("view/userManagement/SubWindow.fxml")).load());
            userManagementSubWindowStage.getIcons().add(new Image(Application.class.getResource("img/logo.png").toString()));

            userManagementSubWindowStage.setTitle("新增用户");
            userManagementSubWindowStage.initOwner(Application.mainStage);
            userManagementSubWindowStage.initModality(Modality.WINDOW_MODAL);

            userManagementSubWindowStage.setScene(UserManagementSubScene);
            userManagementSubWindowStage.setResizable(false);
            userManagementSubWindowStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public void initialize() {
        userManagementController = (UserManagementController) Application.shareController.get(UserManagementController.class.getSimpleName());
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
                        PopupWindow.alertWindow("输入的时间格式有误","输入的时间格式有误");

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


        if (adminCheckBox != null) adminCheckBox.setOnAction(Event::consume);
        if (stockmanagerCheckBox != null) stockmanagerCheckBox.setOnAction(Event::consume);
        if (inventorypersonCheckBox != null) inventorypersonCheckBox.setOnAction(Event::consume);
        if (storagepersonCheckBox != null) storagepersonCheckBox.setOnAction(Event::consume);
        if (salespersonCheckBox != null) salespersonCheckBox.setOnAction(Event::consume);

        if (birthday != null) {
            birthday.setDayCellFactory(dayCellFactory);
            birthday.setConverter(converter);
            birthday.commitValue();
        }

        if (hiredate != null) {
            hiredate.setDayCellFactory(dayCellFactory);
            hiredate.setConverter(converter);
        }
        if (phone != null) {

            // 创建一个文本过滤器，只允许符合正则表达式的文本通过
            phone.setTextFormatter(new TextFormatter<>(change -> {
                if (Pattern.compile("\\d{0,11}").matcher(change.getControlNewText()).matches()) {
                    return change;
                } else {
                    return null;
                }
            }));
        }
        if (sex != null) {
            sex.setItems(FXCollections.observableArrayList("男", "女"));
        }


    }


}




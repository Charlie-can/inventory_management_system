package com.frontend.controller;

import com.frontend.Application;
import com.frontend.utils.PopupWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;


public class UserLogoutController {

    @FXML
    public ToggleButton logoutToggleButton;

    @FXML
    public void onLogoutLabelClicked() {
//        PopupWindow.alertWindow();



        for (ToggleButton toggleButton : Application.shareLabelButton) {
            if (toggleButton != null) {
                toggleButton.setSelected(false);
            }
        }
        logoutToggleButton.setSelected(true);
        Boolean aBoolean = PopupWindow.ConfirmationWindow("退出登录", "是否要退出登录");
        if (aBoolean)
            Application.changeView("view/LoginView.fxml");
    }




    public void initialize() {
        if (logoutToggleButton != null) Application.shareLabelButton.add(logoutToggleButton);




    }

}

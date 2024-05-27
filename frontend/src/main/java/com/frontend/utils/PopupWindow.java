package com.frontend.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class PopupWindow {


    static public void alertWindow(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    static public Boolean ConfirmationWindow(String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(content);
        alert.setHeaderText(content);
        alert.setContentText(content);
        Optional<ButtonType> buttonType = alert.showAndWait();
        return buttonType.get() == ButtonType.OK;
    }
    static public Boolean ConfirmationWindow(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(content);
        alert.setContentText(content);
        Optional<ButtonType> buttonType = alert.showAndWait();
        return buttonType.get() == ButtonType.OK;
    }

    static public Boolean ConfirmationWindow(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Optional<ButtonType> buttonType = alert.showAndWait();
        return buttonType.get() == ButtonType.OK;
    }


    static public void alertWindow(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(content);
        alert.setContentText(content);
        alert.showAndWait();
    }

    static public void alertWindow(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("警告");
        alert.setHeaderText(content);
        alert.setContentText(content);
        alert.show();
    }


    static public void informationWindow(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("信息");
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.show();
    }
    static public void informationWindow(String title, String head,String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(head);
        alert.setContentText(content);
        alert.show();
    }

    static public void informationWindow(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("信息");
        alert.setHeaderText(content);
        alert.setContentText(content);
        alert.show();
    }
}

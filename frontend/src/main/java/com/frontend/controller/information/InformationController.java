package com.frontend.controller.information;

import com.frontend.Application;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

public class InformationController {

    @FXML
    public ToggleButton informationToggleButton;

    @FXML
    public void OnInformationLabelClicked() {
        System.out.println(Application.userInfoReceiveData.getData().getUserInfo());

        for (ToggleButton toggleButton : Application.shareLabelButton) {
            if(toggleButton!=null){
                toggleButton.setSelected(false);
            }
        }
        informationToggleButton.setSelected(true);

    }

    public void initialize() {
        if (informationToggleButton != null)
            Application.shareLabelButton.add(informationToggleButton);

    }
}

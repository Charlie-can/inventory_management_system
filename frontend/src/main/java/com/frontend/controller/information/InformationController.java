package com.frontend.controller.information;

import com.frontend.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InformationController {

    @FXML
    public void OnInformationLabelClicked(){
        System.out.println(Application.userInfoReceiveData.getData().getUserInfo());


    }

    public void initialize(){




    }
}

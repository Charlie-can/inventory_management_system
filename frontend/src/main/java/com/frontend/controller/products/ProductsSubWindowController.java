package com.frontend.controller.products;

import com.frontend.Application;
import com.frontend.entity.HTTPStatusEnums;
import com.frontend.entity.HttpResponseData;
import com.frontend.entity.ProductsList;
import com.frontend.utils.BackendResource;
import com.frontend.utils.PopupWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ProductsSubWindowController {

    @FXML
    private TextField productsName;


    @FXML
    private TextField productsReserveNow;


    @FXML
    private TextField productsReserveMin;


    @FXML
    private TextField productsPrice;


    @FXML
    private TextField productsVendor;

    @FXML
    private TextArea productsIntroduction;

    static public Stage productsSubWindowStage;

    private ProductsController   productsController;

    @FXML
    public void onConfirmButtonClick() {


        ProductsList productsList = new ProductsList();

        System.out.println(productsName.getText());
        if (productsName.getText().isEmpty() || productsPrice.getText().isEmpty() || productsReserveNow.getText().isEmpty() || productsReserveMin.getText().isEmpty() || productsVendor.getText().isEmpty() || productsIntroduction.getText().isEmpty()) {
            PopupWindow.alertWindow("单元格不能为空");
            return;
        }

        try {
            productsList.setName(productsName.getText());
            productsList.setPrice(Double.parseDouble(productsPrice.getText()));
            productsList.setReserveNow(Integer.parseInt(productsReserveNow.getText()));
            productsList.setReserveMin(Integer.parseInt(productsReserveMin.getText()));
            productsList.setVendor(productsVendor.getText());
            productsList.setIntroduction(productsIntroduction.getText());

        } catch (NumberFormatException numberFormatException) {
            PopupWindow.alertWindow("您输入的不是一个有效的数字", "请检查商品价格、当前库存或最低库存，查看是否为整数或小数");
            return;
        }


        BackendResource<ProductsList> httpResponseDataBackendResource = new BackendResource<>();

        System.out.println(productsList);


        HttpResponseData httpResponseData = httpResponseDataBackendResource.postRequest("/stocks/insertStock", productsList);

        try {
            if(Objects.equals(httpResponseData.getCode(), HTTPStatusEnums.OK.getCode())){

                PopupWindow.informationWindow("成功添加");

                productsController.showAllProducts();

                productsSubWindowStage.close();
            }
        }catch (Exception e){
            PopupWindow.alertWindow("添加失败");
        }


    }

    @FXML
    public void onCancelButtonClick() {
        productsSubWindowStage.close();
    }


    static void PopupSubWindow() {

        productsSubWindowStage = new Stage();

        productsSubWindowStage.getIcons().add(new Image(Application.class.getResource("img/logo.png").toString()));

        Scene productsSubScene;
        try {
            productsSubScene = new Scene(new FXMLLoader(Application.class.getResource("view/products/SubWindow.fxml")).load());

            productsSubWindowStage.setTitle("添加单元格");
            productsSubWindowStage.initOwner(Application.mainStage);
            productsSubWindowStage.initModality(Modality.WINDOW_MODAL);

            productsSubWindowStage.setScene(productsSubScene);
            productsSubWindowStage.setResizable(false);
            productsSubWindowStage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



    public void initialize() {

        productsController = (ProductsController) Application.shareController.get(ProductsController.class.getSimpleName());

    }


}




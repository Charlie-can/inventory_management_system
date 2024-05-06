module com.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires lombok;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires org.jetbrains.annotations;

    opens com.frontend to javafx.fxml;
    exports com.frontend;
    exports com.frontend.controller;
    exports com.frontend.entity;

    opens com.frontend.controller to javafx.fxml;

    opens com.frontend.entity to javafx.base;
    exports com.frontend.controller.products;
    exports com.frontend.controller.sales;
    exports com.frontend.utils;
    exports com.frontend.controller.information;

    opens com.frontend.controller.products to javafx.fxml;
    opens com.frontend.controller.sales to javafx.fxml;


}
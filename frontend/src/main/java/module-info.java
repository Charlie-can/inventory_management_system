module com.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires lombok;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires org.jetbrains.annotations;

    exports com.frontend;
    exports com.frontend.controller;
    exports com.frontend.entity;
    exports com.frontend.controller.products;
    exports com.frontend.controller.storage;
    exports com.frontend.controller.sales;
    exports com.frontend.utils;
    exports com.frontend.controller.information;
    exports com.frontend.controller.userManagement;

    opens com.frontend to javafx.fxml;
    opens com.frontend.controller to javafx.fxml;
    opens com.frontend.entity to javafx.base;
    opens com.frontend.controller.products to javafx.fxml;
    opens com.frontend.controller.sales to javafx.fxml;
    opens com.frontend.controller.storage  to  javafx.fxml;
    exports com.frontend.controller.inventory;
    opens com.frontend.controller.inventory to javafx.fxml;
    opens com.frontend.controller.userManagement  to javafx.fxml;

}
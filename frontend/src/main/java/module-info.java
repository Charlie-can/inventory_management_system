module com.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires static lombok;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires org.jetbrains.annotations;
    requires easyexcel.core; // 确保此模块名正确
    requires org.slf4j;

    exports com.frontend;
    exports com.frontend.entity;
    exports com.frontend.utils;
    exports com.frontend.controller;
    exports com.frontend.controller.products;
    exports com.frontend.controller.storage;
    exports com.frontend.controller.sales;
    exports com.frontend.controller.information;
    exports com.frontend.controller.userManagement;
    exports com.frontend.controller.inventory;

    opens com.frontend to javafx.fxml;
    opens com.frontend.controller to javafx.fxml;
    opens com.frontend.entity to javafx.base, com.fasterxml.jackson.databind, easyexcel.core;
    opens com.frontend.controller.products to javafx.fxml;
    opens com.frontend.controller.sales to javafx.fxml;
    opens com.frontend.controller.storage to javafx.fxml;
    opens com.frontend.controller.inventory to javafx.fxml;
    opens com.frontend.controller.userManagement to javafx.fxml;
}

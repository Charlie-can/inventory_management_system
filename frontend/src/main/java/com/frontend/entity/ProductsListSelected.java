package com.frontend.entity;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import lombok.Data;

public class ProductsListSelected {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();

    private SimpleStringProperty name = new SimpleStringProperty();

    private SimpleIntegerProperty reserveNow = new SimpleIntegerProperty();

    private SimpleIntegerProperty reserveMin = new SimpleIntegerProperty();

    private SimpleDoubleProperty price = new SimpleDoubleProperty();

    private SimpleStringProperty vendor = new SimpleStringProperty();

    private SimpleStringProperty introduction = new SimpleStringProperty();

    private SimpleBooleanProperty selected = new SimpleBooleanProperty();

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getReserveNow() {
        return reserveNow.get();
    }

    public SimpleIntegerProperty reserveNowProperty() {
        return reserveNow;
    }

    public void setReserveNow(int reserveNow) {
        this.reserveNow.set(reserveNow);
    }

    public int getReserveMin() {
        return reserveMin.get();
    }

    public SimpleIntegerProperty reserveMinProperty() {
        return reserveMin;
    }

    public void setReserveMin(int reserveMin) {
        this.reserveMin.set(reserveMin);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getVendor() {
        return vendor.get();
    }

    public SimpleStringProperty vendorProperty() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor.set(vendor);
    }

    public String getIntroduction() {
        return introduction.get();
    }

    public SimpleStringProperty introductionProperty() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction.set(introduction);
    }

    public boolean isSelected() {
        return selected.get();
    }

    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
//    private Integer id;
//
//    private String name;
//
//    private Integer reserveNow;
//
//    private Integer reserveMin;
//
//    private Double price;
//
//    private String vendor;
//
//    private String introduction;
//
//    private Boolean selected;


    public ProductsListSelected(ProductsList productsList) {

        id.set(productsList.getId());
        name.set(productsList.getName());
        reserveNow.set(productsList.getReserveNow());
        reserveMin.set( productsList.getReserveMin());
        price.set(productsList.getPrice());
        vendor.set(productsList.getVendor());
        introduction.set(productsList.getIntroduction());
        selected.set(false);
    }

    @Override
    public String toString() {
        return "ProductsListSelected{" +
                "id=" + id +
                ", name=" + name +
                ", reserveNow=" + reserveNow +
                ", reserveMin=" + reserveMin +
                ", price=" + price +
                ", vendor=" + vendor +
                ", introduction=" + introduction +
                ", selected=" + selected +
                '}';
    }
}

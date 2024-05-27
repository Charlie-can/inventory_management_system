package com.frontend.entity;


import javafx.beans.property.*;


import java.time.LocalDate;

public class UserInfoListSelected {


    private IntegerProperty id;

    private StringProperty password;

    private BooleanProperty admin;

    private BooleanProperty salesperson;

    private BooleanProperty storageperson;

    private BooleanProperty inventoryperson;

    private BooleanProperty stockmanager;

    private Employee employee;

    private BooleanProperty selected = new SimpleBooleanProperty(false);

    public int getId() {
        return id.getValue();

    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public boolean isAdmin() {
        return admin.get();
    }

    public BooleanProperty adminProperty() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin.set(admin);
    }

    public boolean isSalesperson() {
        return salesperson.get();
    }

    public BooleanProperty salespersonProperty() {
        return salesperson;
    }

    public void setSalesperson(boolean salesperson) {
        this.salesperson.set(salesperson);
    }

    public boolean isStorageperson() {
        return storageperson.get();
    }

    public BooleanProperty storagepersonProperty() {
        return storageperson;
    }

    public void setStorageperson(boolean storageperson) {
        this.storageperson.set(storageperson);
    }

    public boolean isInventoryperson() {
        return inventoryperson.get();
    }

    public BooleanProperty inventorypersonProperty() {
        return inventoryperson;
    }

    public void setInventoryperson(boolean inventoryperson) {
        this.inventoryperson.set(inventoryperson);
    }

    public boolean isStockmanager() {
        return stockmanager.get();
    }

    public BooleanProperty stockmanagerProperty() {
        return stockmanager;
    }

    public void setStockmanager(boolean stockmanager) {
        this.stockmanager.set(stockmanager);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(UserManagementReceiveData.Data.UserInfoList.Employee employee) {
        this.employee = new Employee(employee);
    }

    public boolean isSelected() {
        return selected.get();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public static class Employee {

        private IntegerProperty id;

        private StringProperty name;

        private StringProperty sex;

        private StringProperty phone;

        private StringProperty birthday;

        private StringProperty hiredate ;

        public int getId() {
            return id.get();
        }

        public IntegerProperty idProperty() {
            return id;
        }

        public void setId(int id) {
            this.id.set(id);
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getSex() {
            return sex.get();
        }

        public StringProperty sexProperty() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex.set(sex);
        }

        public String getPhone() {
            return phone.get();
        }

        public StringProperty phoneProperty() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone.set(phone);
        }

        public String getBirthday() {
            return birthday.get();
        }

        public StringProperty birthdayProperty() {
            return birthday;
        }

        public void setBirthday(LocalDate birthday) {
            this.birthday.set(birthday.toString());
        }

        public String getHiredate() {
            return hiredate.get();
        }

        public StringProperty hiredateProperty() {
            return hiredate;
        }

        public void setHiredate(LocalDate hiredate) {
            this.hiredate.set(hiredate.toString());
        }

        public Employee(UserManagementReceiveData.Data.UserInfoList.Employee employee) {
           this.id = new SimpleIntegerProperty();
           this.name = new SimpleStringProperty();
           this.sex =  new SimpleStringProperty();
           this.phone = new SimpleStringProperty();
           this.birthday = new SimpleStringProperty();
           this.hiredate = new SimpleStringProperty();

            setId(employee.getId());
            setName(employee.getName());
            setSex(employee.getSex());
            setPhone(employee.getPhone());
            setBirthday(employee.getBirthday());
            setHiredate(employee.getHiredate());
        }


        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name=" + name +
                    ", sex=" + sex +
                    ", phone=" + phone +
                    ", birthday=" + birthday +
                    ", hiredate=" + hiredate +
                    '}';
        }
    }


    public UserInfoListSelected(UserManagementReceiveData.Data.UserInfoList userInfoList){

        this.id = new SimpleIntegerProperty();
        this.password = new SimpleStringProperty();
        this.admin = new SimpleBooleanProperty();
        this.salesperson = new SimpleBooleanProperty();
        this.storageperson = new SimpleBooleanProperty();
        this.inventoryperson = new SimpleBooleanProperty();
        this.stockmanager = new SimpleBooleanProperty();



        setId(userInfoList.getId());
        setPassword(userInfoList.getPassword());
        setAdmin(userInfoList.getAdmin());
        setSalesperson(userInfoList.getSalesperson());
        setStorageperson(userInfoList.getStorageperson());
        setInventoryperson(userInfoList.getInventoryperson());
        setStockmanager(userInfoList.getStockmanager());
        setEmployee(userInfoList.getEmployee());
        setSelected(false);
    }

    @Override
    public String toString() {
        return "UserInfoListSelected{" +
                "id=" + id +
                ", password=" + password +
                ", admin=" + admin +
                ", salesperson=" + salesperson +
                ", storageperson=" + storageperson +
                ", inventoryperson=" + inventoryperson +
                ", stockmanager=" + stockmanager +
                ", employee=" + employee +
                ", selected=" + selected +
                '}';
    }
}

package model;

import javafx.beans.property.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty phoneModel;
    private final StringProperty phone;
    private final StringProperty city;
    private final StringProperty problemModelDef;
    private final Date date = new Date();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy - HH:mm");
    public Person() {
        this(null, null);
    }

    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneModel = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.problemModelDef = new SimpleStringProperty();
    }


    public String getDateFormat() {
        return dateFormat.format(date);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getphoneModel() {
        return phoneModel.get();
    }

    public void setphoneModel(String phoneModel) {
        this.phoneModel.set(phoneModel);
    }

    public StringProperty phoneModelProperty() {
        return phoneModel;
    }


    public String getProblemModelDef() {
        return problemModelDef.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty problemModelDefProperty() {
        return problemModelDef;
    }

    public void setProblemModelDef(String problemModelDef) {
        this.problemModelDef.set(problemModelDef);
    }

    public String getPhoneModel() {
        return phoneModel.get();
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel.set(phoneModel);
    }


    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty()
    {
        return city;
    }


}
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Person;

public class PersonEditDialogController {

    @FXML
    public TextField phoneModelLabel;
    @FXML
    public TextField cityLabel;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneLabel;
    @FXML
    private TextField problemModelField;

    private Person person;
    private Stage dialogStage;
    private boolean okClicked = false;

    public PersonEditDialogController() {
    }

    @FXML
    private void initialize(){

    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage=dialogStage;
    }
    public void setPerson(Person person){
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        phoneLabel.setText(person.getPhone());
        phoneModelLabel.setText(person.getPhoneModel());
        cityLabel.setText(person.getCity());
        problemModelField.setText(person.getProblemModelDef());
        problemModelField.setPromptText("суть проблемы =)");
    }
    public boolean isOnClicked(){
        return okClicked;
    }

    @FXML
    private void handleOk(){
        if(isInputValid()){
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setCity(cityLabel.getText());
            person.setPhone(phoneLabel.getText());
            person.setphoneModel(phoneModelLabel.getText());
            person.setProblemModelDef(problemModelField.getText());
            okClicked = true;
            dialogStage.close();
        }
    }
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    private boolean isInputValid(){
        String errorMessage = "";
        if(firstNameField.getText() == null || firstNameField.getText().length() == 0){
            errorMessage += "Невалидное имя!\n";
        }
        if(lastNameField.getText() == null || lastNameField.getText().length() == 0){
            errorMessage+="Невалидная фамилия!\n";
        }
        if(phoneModelLabel.getText() == null || phoneModelLabel.getText().length() == 0){
            errorMessage+="Невалидня улица!\n";
        }
        if(phoneLabel.getText() == null || phoneLabel.getText().length() == 0){
            errorMessage+="Невалидный телефон!\n";
        }
        if (cityLabel.getText() == null || cityLabel.getText().length() == 0){
                errorMessage+="НЕвалидный город!\n";
        }
        if(problemModelField.getText() == null || problemModelField.getText().length() == 0){
            errorMessage+="Проблема-то в чём? А?!\n";
        }

        if(errorMessage.length() == 0){
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Заполни поля ничтожество!");
            alert.setHeaderText("Пожалуйста!");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}

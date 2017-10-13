package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Person;

@SuppressWarnings("Duplicates")
public class PersonOverview {

        @FXML
        private TableView<Person> personTable;
        @FXML
        private TableColumn<Person, String> firstNameColumn;
        @FXML
        private TableColumn<Person, String> lastNameColumn;

        @FXML
        private Label firstNameLabel;
        @FXML
        private Label lastNameLabel;
        @FXML
        private Label phoneModelLabel;
        @FXML
        private Label cityLabel;
        @FXML
        private Label phoneLabel;
        @FXML
        private Label problemModelLabel;

        private Main mainApp;

        public PersonOverview() {
        }

        @FXML
        private void initialize() {
            firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
            lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
            showPersonDetails(null);

            personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->  showPersonDetails(newValue));

        }
        public void setMainApp(Main mainApp) {
            this.mainApp = mainApp;
            personTable.setItems(mainApp.getPersonData());
        }
        private void showPersonDetails(Person person){
            if(person!=null){
                firstNameLabel.setText(person.getFirstName());
                lastNameLabel.setText(person.getLastName());
                phoneModelLabel.setText(person.getphoneModel());
                cityLabel.setText(person.getCity());
                phoneLabel.setText(person.getPhone());
                problemModelLabel.setWrapText(true);
                problemModelLabel.setText(person.getProblemModelDef()+ "\n" + person.getDateFormat());
            }else {
                firstNameLabel.setText("");
                lastNameLabel.setText("");
                phoneModelLabel.setText("");
                cityLabel.setText("");
                phoneLabel.setText("");
                problemModelLabel.setText("");

            }
        }

        @FXML
        private void handleDeletePerson() {
            int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                personTable.getItems().remove(selectedIndex);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Person Selected");
                alert.setContentText("Please select a person in the table.");
                alert.showAndWait();
            }
        }
        @FXML
        private void handleNewPerson(){
            Person tempPerson = new Person();
            boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
            if(okClicked){
                    mainApp.getPersonData().add(tempPerson);
            }
        }
        @FXML
        private void handleEditPerson(){
            Person selectPerson = personTable.getSelectionModel().getSelectedItem();
            if(selectPerson!=null){
                boolean okClicked = mainApp.showPersonEditDialog(selectPerson);
                if(okClicked){
                    showPersonDetails(selectPerson);
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Person Selected");
                alert.setContentText("Please select a person in the table.");

                alert.showAndWait();
            }
        }
    }


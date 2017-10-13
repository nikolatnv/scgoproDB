package controller;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class RootLayoutController {
private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void fileNew() {
        mainApp.getPersonData().clear();
        mainApp.setPearsonFilePath(null);
    }
    @FXML
    private void openFile(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)",".xml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if(file!=null){
            mainApp.loadPersonDataFromFile(file);
        }
    }
    @FXML
    private void saveFile(){
        File file = mainApp.getPersonFilePath();
        if(file!=null){
            mainApp.savePersonDataToFile(file);
        }
        else saveAS();
    }
    @FXML
    private void saveAS() {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)",".xml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if(file!=null){
            if(!file.getPath().endsWith(".xml")){
                file = new File(file.getPath()+".xml");
            }
            mainApp.savePersonDataToFile(file);
        }


    }
    @FXML
    private void  exitScgopro() {
        System.exit(0);
    }
}

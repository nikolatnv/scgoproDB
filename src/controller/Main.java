package controller;

import com.sun.scenario.effect.impl.prism.PrImage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;
import model.PersonWraper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Person> observableList = FXCollections.observableArrayList();


    /*public Main(){
        observableList.add(new Person("дядя", "Вася"));
        observableList.add(new Person("дядя", "Фёдор"));
        observableList.add(new Person("дядя", "Николай"));

        observableList.add(new Person("дядя","Владимир"));
        observableList.add(new Person("дядя", "Михаил"));

        observableList.add(new Person("дядя", "Алексей"));
        observableList.add(new Person("дядя", "ЛАЛАЛАЛ"));

        observableList.add(new Person("дядя", "ждлждлжд"));
        observableList.add(new Person("дядя", "ывфвфвфвы"));
    }
*/
    public ObservableList<Person> getPersonData() {
            return observableList;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{


            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("scgoPRO");
            this.primaryStage.getIcons().add(new Image("resources/image/icon.png"));
            initRootLayout();
            showPersonOverview();

    }
    public void initRootLayout(){
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

        RootLayoutController rootLayoutController = loader.getController();
        rootLayoutController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = getPersonFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);

        }
    }

    public void showPersonOverview(){
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/PersonOverview.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();
        rootLayout.setCenter(personOverview);
        PersonOverview controller = loader.getController();
        controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean showPersonEditDialog(Person person){
        try {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/PersonEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Добавлениe клиентов");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        PersonEditDialogController personEditDialogController = loader.getController();
        personEditDialogController.setDialogStage(dialogStage);
        personEditDialogController.setPerson(person);

        dialogStage.showAndWait();
        return personEditDialogController.isOnClicked();

    }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public File getPersonFilePath(){
        Preferences pref = Preferences.userNodeForPackage(Main.class);
        String filePath = pref.get("filePath", null);
        if(filePath!=null){
            return new File(filePath);
        }else {
            return null;
        }
    }

    public void setPearsonFilePath(File filePath){
        Preferences pref = Preferences.userNodeForPackage(Main.class);
        if(pref!=null){
            pref.put("filePath", filePath.getPath());
            primaryStage.setTitle("scgoPRO - " + filePath.getName());
        }else {
            pref.remove("filePath");
            primaryStage.setTitle("scgoPRO");

        }

    }

    public void loadPersonDataFromFile(File file){
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(PersonWraper.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            PersonWraper wraper = (PersonWraper) unmarshaller.unmarshal(file);
            observableList.clear();
            observableList.add((Person) wraper.getPersons());
            setPearsonFilePath(file);
        } catch (JAXBException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Жопа какая-то");
            alert.setHeaderText("Не грузится, файла нет или я хз что там(");
            alert.setContentText("не могу загрузить эту сволочь((:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonWraper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            PersonWraper wrapper = new PersonWraper();
            wrapper.setPersons(observableList);

            m.marshal(wrapper, file);

            setPearsonFilePath(file);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ёпт!");
            alert.setHeaderText("Не могу сохранить данные хозяин");
            alert.setContentText("не получется их сюда засунуть(:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

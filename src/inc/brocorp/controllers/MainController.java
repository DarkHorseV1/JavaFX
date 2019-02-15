package inc.brocorp.controllers;

import inc.brocorp.AddressBook;
import inc.brocorp.Persons;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    AddressBook addressBook = new AddressBook();

    boolean choice;

    private Stage mainStage;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnAdd;

    @FXML
    private Label labelCount;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView tableAddressBook;

    @FXML
    private TableColumn<Persons, String> columnFio;

    @FXML
    private TableColumn<Persons, String> columnPhone;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Stage editDialogStage;
    private EditController editController;
    private ResourceBundle resourceBundle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;

        tableAddressBook.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnFio.setCellValueFactory(new PropertyValueFactory<Persons, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Persons, String>("phone"));

        tableAddressBook.setItems(addressBook.getPersons());
        initListeners();
        try {
            fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("inc.brocorp.bundle.Locale", new Locale("ru")));

            fxmlEdit = fxmlLoader.load();
            editController = fxmlLoader.getController();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        addressBook.fillTestData();
    }

    private void initListeners(){
        addressBook.getPersons().addListener((ListChangeListener<Persons>) c -> updateLabelCount());
        tableAddressBook.setOnMouseClicked(event -> {
            if(event.getClickCount() ==2){
                editController.setPerson((Persons)tableAddressBook.getSelectionModel().getSelectedItem());
                showDialog();
            }
        });
    }

    private void updateLabelCount(){
        labelCount.setText(resourceBundle.getString("count")+": "+addressBook.getPersons().size());
    }

    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(!(source instanceof Button)){
            return;
        }
        Button clickedButton = (Button) source;
        Persons selectedPerson = (Persons) tableAddressBook.getSelectionModel().getSelectedItem();
        editController.setPerson(selectedPerson);

        switch(clickedButton.getId()){
            case "btnAdd":
                editController.setPerson(new Persons());
                choice = true;
                showDialog();
                addressBook.add(editController.getPerson());
                break;

            case "btnDelete":
                addressBook.delete((Persons)tableAddressBook.getSelectionModel().getSelectedItem());
                break;

            case "btnEdit":
                choice = false;
                showDialog();
                break;
        }
    }
    private void showDialog(){
        if(editDialogStage == null){
            editDialogStage = new Stage();
            if(choice == false){
                editDialogStage.setTitle(resourceBundle.getString("edit"));
            }
            else{
                editDialogStage.setTitle(resourceBundle.getString("add"));
            }
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }
        if(choice == false){
            editDialogStage.setTitle(resourceBundle.getString("edit"));
        }
        else{
            editDialogStage.setTitle(resourceBundle.getString("add"));
        }
        editDialogStage.showAndWait();
    }
}

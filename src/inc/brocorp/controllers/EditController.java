package inc.brocorp.controllers;

import inc.brocorp.Persons;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {

    private Persons person;

    public Persons getPerson(){
        return person;
    }

    @FXML
    public void actionClose(ActionEvent actionEvent){
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void setPerson(Persons person){
        if(person == null){
            return;
        }
        this.person = person;
        txtFIO.setText(person.getFio());
        txtPhone.setText(person.getPhone());
    }

    @FXML
    public void actionSave(ActionEvent actionEvent) {
        person.setFio(txtFIO.getText());
        person.setPhone(txtPhone.getText());
        actionClose(actionEvent);
    }

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtFIO;


}

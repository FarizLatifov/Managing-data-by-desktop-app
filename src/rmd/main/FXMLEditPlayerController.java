/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmd.main;

import AdditionalMethodsOfControllers.OperationsOnData;
import Interfaces.Cleanable;
import Interfaces.Closeable;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rmd.WorkOverDatabase.SquadData;
import rmd.objects.Squad;

/**
 * FXML Controller class
 *
 * @author Fariz
 */
public class FXMLEditPlayerController implements Initializable, Cleanable, Closeable {

    @FXML
    Button buttonSave;
    @FXML
    TextField textFieldFullName;

    @FXML
    Button buttonReset;
    @FXML
    Label labelMessage;
    @FXML
    Label labelPosition;
    @FXML
    Label labelCountry;
    @FXML
    Label labelShirtNumber;
    @FXML
    Label labelShirtNumberMessage;
    @FXML
    ChoiceBox choiceBoxPosition;
    @FXML
    DatePicker datePickerBirthDate;
    @FXML
    ComboBox comboBoxCountry;
    @FXML
    ComboBox comboBoxShirtNumber;
    @FXML
    Button closeWindowButton;

    OperationsOnData operationsOnData = new OperationsOnData();
    SquadData squadData = new SquadData();
    Squad squad = new Squad();


    @FXML
    private void handleCloseWindowButton(){
        closeWindow();
    }
    
    @FXML
    private void handleSaveButton() {
        cleanValidatorMessages();
        if (isUptatable()) {
            updatePlayer();
            closeWindow();
        }
    }

    @FXML
    private void resetAllFields() {
        cleanValidatorMessages();
        textFieldFullName.clear();
        choiceBoxPosition.getSelectionModel().clearSelection();
        comboBoxCountry.getSelectionModel().clearSelection();
        comboBoxCountry.getEditor().clear();
        comboBoxShirtNumber.getSelectionModel().clearSelection();
        comboBoxShirtNumber.getEditor().clear();
        datePickerBirthDate.getEditor().clear();

    }

    public void setDataToEditWindow(Squad oldPlayer) {
        //Adopting old squad object data to new squad object that has taken from selected table row when Edit button clicked 
        this.squad = oldPlayer;
        //Changing date format to LocalDate 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(squad.getBirthDate(), formatter);
        //Setting data to the fields
        textFieldFullName.setText(squad.getName());
        choiceBoxPosition.setValue(squad.getPosition());
        comboBoxCountry.setValue(squad.getCountry());
        comboBoxShirtNumber.setValue(squad.getNumber());
        datePickerBirthDate.setValue(date);
        choiceBoxPosition.getItems().addAll(operationsOnData.takeAppropriateData(labelPosition.getText()));
        comboBoxCountry.getItems().addAll(operationsOnData.takeAppropriateData(labelCountry.getText()));
        comboBoxShirtNumber.getItems().addAll(operationsOnData.takeAppropriateData(labelShirtNumber.getText()));
    }

    public boolean isUptatable() {
        String playerName = textFieldFullName.getText();
        Object playerPosition = choiceBoxPosition.getValue();
        String playerCountry = comboBoxCountry.getEditor().getText();
        String playerShirtNumber = comboBoxShirtNumber.getEditor().getText();
        LocalDate playerBirthDate = datePickerBirthDate.getValue();
        if ((playerName.equals("")) || (playerPosition == null) || (playerCountry.equals("")) || (playerShirtNumber.equals("")) || (playerBirthDate == null)) {
            labelMessage.setText("Please fill all information.");
            return false;
        } else if (!isAtRange(playerShirtNumber)) {
            labelMessage.setText("Error. Improper format!");
            labelShirtNumberMessage.setText("Enter a number between: 1-99");
            return false;
        }
        return true;
    }

    public void updatePlayer() {
        Boolean countryIsNew = true;
        String playerName = textFieldFullName.getText();
        String playerPosition = choiceBoxPosition.getValue().toString();
        String playerCountry = comboBoxCountry.getEditor().getText();
        int playerShirtNumber = Integer.parseInt(comboBoxShirtNumber.getEditor().getText());
        String playerBirthDate = datePickerBirthDate.getValue().toString();
        playerCountry = playerCountry.substring(0, 1).toUpperCase() + playerCountry.substring(1);
        //Here we set old playerID to the playerID of new player. Thus playerID become same. SO oldPlayerID=newPlayerID.
        Squad editedPlayer = new Squad(squad.getId(),
                playerName,
                playerPosition,
                playerCountry,
                playerShirtNumber,
                playerBirthDate);
        for (int i = 0; i < squadData.getCountryFromDatabase().size(); i++) {
            if (playerCountry.equals(squadData.getCountryFromDatabase().get(i).getName())) {
                countryIsNew = false;
            }
        }
        squadData.updateDataOnDatabase(editedPlayer, countryIsNew);
    }

    public boolean isAtRange(String s) {
        if (!s.matches("^[1-9][0-9]?$|^100$")) {
            return false;
        }
        return true;
    }

    @Override
    public void closeWindow() {
        Stage stage = (Stage) buttonSave.getScene().getWindow();
        stage.close();
    }

    @Override
    public void cleanValidatorMessages() {
        labelShirtNumberMessage.setText("");
        labelMessage.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

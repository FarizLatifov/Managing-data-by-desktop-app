/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmd.main;

import AdditionalMethodsOfControllers.OperationsOnData;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rmd.WorkOverDatabase.SquadData;
import rmd.objects.Squad;
import Interfaces.Cleanable;
import Interfaces.Closeable;
import javafx.scene.control.ComboBox;
import rmd.objects.Country;

/**
 * FXML Controller class
 *
 * @author Fariz
 */
public class FXMLAddPlayerController implements Initializable, Cleanable, Closeable {

    @FXML
    Button buttonAdd;
    @FXML
    TextField textFieldFullName;
    @FXML
    TextField textFieldShirtNumber;
    @FXML
    Button buttonClose;
    @FXML
    Button buttonReset;
    @FXML
    Label labelMessage;
    @FXML
    Label labelCountry;
    @FXML
    Label labelPosition;
    @FXML
    Label labelShirtNumber;
    @FXML
    Label labelShirtNumberMessage;
    @FXML
    ChoiceBox choiceBoxPosition;
    @FXML
    ChoiceBox choiceBoxShirtNumber;
    @FXML
    DatePicker datePickerBirthDate;
    @FXML
    ComboBox comboboxCountry;
    @FXML
    ComboBox comboboxShirtNumber;

    SquadData squadData = new SquadData();
    Country country;
    OperationsOnData operationsOnData = new OperationsOnData();

    //-------------------------------------------------------------------------------------------------------
    @FXML
    private void handleCloseWindowButton(){
        closeWindow();
    }
    @FXML
    private void handleAddButton() {
        cleanValidatorMessages();
        if (isAddable()) {
            addPlayer();
            closeWindow();
            
        }
    }

    public boolean isAddable() {
        String playerName = textFieldFullName.getText();
        String playerPosition = (String) choiceBoxPosition.getValue();
        String playerCountry = comboboxCountry.getEditor().getText();
        String playerShirtNumber = comboboxShirtNumber.getEditor().getText();
        LocalDate playerBirthDate = datePickerBirthDate.getValue();

        if ((playerName.equals("")) || (playerPosition == null) || (playerCountry.equals("")) || (playerShirtNumber.equals("")) || (playerBirthDate == null)) {
            labelMessage.setText("Please fill all fields.");
            return false;
        } else if (!isAtRange(playerShirtNumber)) {
            labelMessage.setText("Error. Improper format!");
            labelShirtNumberMessage.setText("Enter a number between: 1-99");
            System.out.println("false range");

            return false;
        }

        return true;

    }

    public boolean isAtRange(String s) {
        if (!s.matches("^[1-9][0-9]?$")) {
            return false;
        }
        return true;
    }

    public void addPlayer() {
        Boolean countryIsNew = true;
        String playerName = textFieldFullName.getText();
        String playerPosition = choiceBoxPosition.getValue().toString();
        String playerCountry = comboboxCountry.getEditor().getText();
        int playerShirtNumber = Integer.parseInt(comboboxShirtNumber.getEditor().getText());
        String playerBirthDate = datePickerBirthDate.getValue().toString();
        playerCountry=playerCountry.substring(0,1).toUpperCase()+playerCountry.substring(1);
        Squad newPlayer = new Squad(playerName,
                playerPosition,
                playerCountry,
                playerShirtNumber,
                playerBirthDate);
        for (int i = 0; i < squadData.getCountryFromDatabase().size(); i++) {
            if (playerCountry.equals(squadData.getCountryFromDatabase().get(i).getName())) {
                countryIsNew = false;
            }
        }
        squadData.addNewDataToDatabase(newPlayer, countryIsNew);
    }

    //-------------------------------------------------------------------------------------------------------
    @FXML
    private void resetAllFields() {
        cleanValidatorMessages();
        textFieldFullName.clear();
        choiceBoxPosition.getSelectionModel().clearSelection();
        comboboxCountry.getSelectionModel().clearSelection();
        comboboxCountry.getEditor().clear();
        comboboxShirtNumber.getSelectionModel().clearSelection();
        comboboxShirtNumber.getEditor().clear();
        choiceBoxPosition.getSelectionModel().clearSelection();
        datePickerBirthDate.getEditor().clear();

    }

    @Override
    public void closeWindow() {
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        stage.close();
    }

    @Override
    public void cleanValidatorMessages() {
        labelShirtNumberMessage.setText("");
        labelMessage.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Setting data to ChoiceBox and Combobox

        choiceBoxPosition.getItems().addAll(operationsOnData.takeAppropriateData(labelPosition.getText()));
        comboboxCountry.getItems().addAll(operationsOnData.takeAppropriateData(labelCountry.getText()));
        comboboxShirtNumber.getItems().addAll(operationsOnData.takeAppropriateData(labelShirtNumber.getText()));

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmd.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import rmd.WorkOverDatabase.SquadData;
import rmd.objects.Squad;
import Interfaces.Cleanable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.StageStyle;

/**
 *
 * @author Fariz
 */
public class FXMLSquadMenuController implements Initializable, Cleanable {

    @FXML
    TableView<Squad> tableAll;
    @FXML
    TableColumn<Squad, String> colPlayerName = new TableColumn<>();
    @FXML
    TableColumn<Squad, String> colPosition = new TableColumn<>();
    @FXML
    TableColumn<Squad, String> colCountry = new TableColumn<>();
    @FXML
    TableColumn<Squad, Integer> colShirtNumber = new TableColumn<>();
    @FXML
    TableColumn<Squad, String> colBirthDate = new TableColumn<>();
    @FXML
    MenuButton squadMenuButton;
    @FXML
    Button buttonAll;
    @FXML
    Button buttonForward;
    @FXML
    Button buttonMidfielder;
    @FXML
    Button buttonDefense;
    @FXML
    Button buttonGoalKeeper;
    @FXML
    Button buttonAdd;
    @FXML
    Button buttonDelete;
    @FXML
    Button buttonEdit;
    @FXML
    Label labelMessage;
    @FXML
    TextArea textAreaWideInformation;
    @FXML
    TextFlow textFlowInfo;
    @FXML
    Button buttonRefresh;

    String position;
    SquadData squadData = new SquadData();
    Squad squad = null;
    ObservableList<Squad> newList = squadData.getDataFromDatabase();
    private static double xOffset = 0;
    private static double yOffset = 0;

    //----------------------FXML Methods----------------------
    @FXML
    private void handleRefreshButton() {
        refreshTable();
    }

    @FXML
    private void handleEditButton() {
        cleanValidatorMessages();
        try { //Loading Edit Window with loader

            FXMLLoader loader = new FXMLLoader();
            Parent rootFxmlEditPlayer = loader.load(getClass().getResource("FXMLEditPlayer.fxml").openStream());
            //Getting controller of Edit Window for using methods of it
            FXMLEditPlayerController editController = loader.getController();
            //Taking selected row object on the table and adopting it to the squad object
            squad = tableAll.getSelectionModel().getSelectedItem();
            //taking selected row object ID on the table and setting it to the squad object ID
            int i = tableAll.getSelectionModel().getSelectedIndex();
            squad.setId(newList.get(i).getId());
            //Setting data to the opened Edit Window
            editController.setDataToEditWindow(squad);
            Scene scene = new Scene(rootFxmlEditPlayer);
            scene.getStylesheets().add(getClass().getResource("AddPlayerCSS.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            setWindowSwipeable(rootFxmlEditPlayer, stage);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLSquadMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleDeleteButton() {
        cleanValidatorMessages();
        deletePlayer();
        refreshTable();
    }

    @FXML
    private void handleAddNewPlayerButton() {
        cleanValidatorMessages();
        try {
            Parent rootFxmlAddPlayer = FXMLLoader.load(getClass().getResource("FXMLAddPlayer.fxml"));
            Scene scene = new Scene(rootFxmlAddPlayer);
//            scene.getStylesheets().add(getClass().getResource("SquadMenuCSS.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
           
            stage.initStyle(StageStyle.TRANSPARENT);
            setWindowSwipeable(rootFxmlAddPlayer, stage);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLSquadMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshTable();
    }

    @FXML
    private void populateTableAll() {
        tableAll.setItems(newList);
    }

    @FXML
    private void populateTableGK() {
        position = buttonGoalKeeper.getText();
        tableAll.setItems(getPlayersAccordingToPosition());
    }

    @FXML
    private void populateTableDefense() {
        position = buttonDefense.getText();
        tableAll.setItems(getPlayersAccordingToPosition());
    }

    @FXML
    private void populateTableMidfielder() {
        position = buttonMidfielder.getText();
        tableAll.setItems(getPlayersAccordingToPosition());
    }

    @FXML
    private void populateTableForward() {
        position = buttonForward.getText();
        tableAll.setItems(getPlayersAccordingToPosition());

    }

    // -------------------------Other methods-----------------------------
    public void setColumns() {
        colPlayerName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("Position"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("Country"));
        colShirtNumber.setCellValueFactory(new PropertyValueFactory<>("Number"));
        colBirthDate.setCellValueFactory(new PropertyValueFactory<>("BirthDate"));
        tableAll.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public ObservableList getPlayersAccordingToPosition() {
        ObservableList<Squad> playerList = FXCollections.observableArrayList();

        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i).getPosition().equals(position)) {
                squad = new Squad(newList.get(i).getName(), newList.get(i).getPosition(), newList.get(i).getCountry(), newList.get(i).getNumber(), newList.get(i).getBirthDate());
                playerList.add(squad);
            }
        }

        return playerList;
    }

    //---------------------------------------------------------------------------------
    public void deletePlayer() {
        int playerID = tableAll.getSelectionModel().getSelectedItem().getId();
        squadData.deleteDataFormDatabase(playerID);
        labelMessage.setText("Player '" + tableAll.getSelectionModel().getSelectedItem().getName() + "' has been deleted successfully!");
        labelMessage.setTextFill(Paint.valueOf("green"));
    }

    //---------------------------------------------------------------------------------
    public void refreshTable() {
        this.newList.clear();
        this.newList = squadData.getDataFromDatabase();
        setColumns();
        tableAll.getItems().clear();
        tableAll.setItems(newList);
        System.out.println("-------------------");

    }

    public void onTableRowSelected() {
        textAreaWideInformation.setEditable(false);
        tableAll.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                buttonDelete.setDisable(false);
                buttonEdit.setDisable(false);
                //Changing type of String to Date
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH);
                LocalDate dateOfBirth = LocalDate.parse(newSelection.getBirthDate(), formatter);
                //Calculating age from Birth Date
                LocalDate today = LocalDate.now();
                int thisYear = today.getYear();
                int birthYear = dateOfBirth.getYear();
                int age = thisYear - birthYear;

                textAreaWideInformation.setText("Name:             " + newSelection.getName() + "\n\n"
                        + "Shirt number:  " + newSelection.getNumber() + "\n\n"
                        + "Position:         " + newSelection.getPosition() + "\n\n"
                        + "Country:          " + newSelection.getCountry() + "\n\n"
                        + "Age:                " + age + "\n\n");

            } else {
                textAreaWideInformation.clear();
                buttonDelete.setDisable(true);
                buttonEdit.setDisable(true);
            }
        });
    }

    public static void setWindowSwipeable(Parent root, Stage stage) {
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    @Override
    public void cleanValidatorMessages() {
        labelMessage.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        onTableRowSelected();
        refreshTable();

        tableAll.setPlaceholder(new Label("Cədvəldə heç bir məlumat yoxdur."));

    }

}

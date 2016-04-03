/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmd.main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Fariz
 */
public class FXMLMainMenuController implements Initializable {

    @FXML
    Label labelFootNote;
    @FXML
    Button buttonSquad;
    @FXML
    StackPane contentStackPane;
    @FXML
    AnchorPane contentAnchorPane;
    @FXML
    AnchorPane headerPane;
    @FXML
    Label labelHeader;
    @FXML
    Button closeWindowButton;

    @FXML
    private void handleCloseWindowButton(){
        Platform.exit();
    }
    @FXML
    private void loadSquadPage() {
        try {
            StackPane newPane = FXMLLoader.load(FXMLSquadMenuController.class.getResource("FXMLSquadMenu.fxml"));
            this.contentStackPane.getChildren().setAll(newPane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleHomeButton() {
        try {
            StackPane homePane = FXMLLoader.load(FXMLHomePageController.class.getResource("FXMLHomePage.fxml"));
            this.contentStackPane.getChildren().setAll(homePane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleAboutUsButton() {
        try {
            StackPane newPane = FXMLLoader.load(FXMLAboutUsPageController.class.getResource("FXMLAboutUsPage.fxml"));
            this.contentStackPane.getChildren().setAll(newPane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalDate date = LocalDate.now();
        handleHomeButton();
        labelFootNote.setText("Fariz Latifov Inc. © " + date.getYear() + " All rights reserved!");
        labelHeader.setText("Team Maker");
    }
}

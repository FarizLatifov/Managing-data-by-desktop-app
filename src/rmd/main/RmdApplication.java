/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmd.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Fariz
 */
public class RmdApplication extends Application {
      private static double xOffset = 0;
      private static double yOffset = 0;
      
     public static void setWindowSwipeable(Parent root, Stage stage){
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
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));
        Scene scene = new Scene(root);
        setWindowSwipeable(root, stage);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setMinHeight(514);
        stage.setMinWidth(726);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

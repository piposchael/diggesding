/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.StartSteuerung;
import java.io.Serializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Pippn
 */
public class StartView implements Serializable {
    
    /**
     * Hoehe des Fensters.
     */
    private static final int FENSTER_HOEHE = 530;
    /**
     * Breite des Fensters.
     */
    private static final int FENSTER_BREITE = 500;
    
    private static final int HBOX_SPACING = 15;
    
    private static final int HBOX_PADDING = 30;
    
    private VBox hbox;
    
    private StartSteuerung startConroller;
    
    private Stage primaryStage;
    
    private StackPane root;
    
    private Button newGame;
    
    private Button loadGame;
    
    private Scene scene;
    
    public StartView(final Stage stage, final StartSteuerung startsteuerung){
        
        this.primaryStage = stage;
        this.startConroller = startsteuerung;
        
        this.newGame = new Button("Neues Spiel");
        this.loadGame = new Button("Spiel Laden");
        //this.newGame.setLayoutX(300);
        //this.newGame.setLayoutY(400);
        
        hbox = new VBox();
        hbox.setSpacing(HBOX_SPACING);
        hbox.setPadding(new Insets(HBOX_PADDING,HBOX_PADDING,HBOX_PADDING,HBOX_PADDING));
        hbox.getChildren().addAll(this.newGame, this.loadGame);
        
        //this.loadGame.setLayoutX(100);
        //this.loadGame.setLayoutY(200);
        
        //this.loadGame.setOnAction(this.startConroller);
        //this.newGame.setOnAction(this.startConroller);
        
        root = new StackPane();
        root.getChildren().add(hbox);
        //root.getChildren().add(this.loadGame);
        
        scene = new Scene(root,FENSTER_BREITE, FENSTER_HOEHE);
        scene.setOnKeyPressed(this.startConroller);
        
        
        this.primaryStage.setTitle("Spieleinstellungen");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }
    
    /**
     * Zeigt wieder die spielfeldScene an.
     */
    public void show() {
        this.primaryStage.setTitle("Spieleinstellungen");
        this.primaryStage.setScene(this.getScene());
        this.primaryStage.show();
    }

    /**
     * @return the startConroller
     */
    public StartSteuerung getStartConroller() {
        return startConroller;
    }

    /**
     * @return the primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * @return the newGame
     */
    public Button getNewGame() {
        return newGame;
    }

    /**
     * @param newGame the newGame to set
     */
    public void setNewGame(Button newGame) {
        this.newGame = newGame;
    }

    /**
     * @return the loadGame
     */
    public Button getLoadGame() {
        return loadGame;
    }

    /**
     * @param loadGame the loadGame to set
     */
    public void setLoadGame(Button loadGame) {
        this.loadGame = loadGame;
    }

    /**
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @param scene the scene to set
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }
    
    
}

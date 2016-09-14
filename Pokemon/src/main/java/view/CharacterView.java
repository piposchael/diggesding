/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Avatar;

/**
 *
 * @author Pippn
 */
public class CharacterView implements Observer, Serializable {
    
    private static final int POS_X_LABELS = 10;
    
    private static final int POS_X_STATS = 110;
    
    private static final int POS_Y_LEBEN = 30;
    
    private static final int POS_Y_WISSEN = 50;
    
    private static final int POS_Y_GESCHICK = 70;
    
    private static final int POS_Y_SCHNELLIGKEIT = 90;
    
    private static final int POS_Y_KRAFT = 110;
    
    private static final int POS_Y_LEVEL = 10;
    
    private static final int FENSTER_BREITE = 500;
    
    private static final int FENSTER_HOEHE = 530;

    /**
     * Eine Scene.
     */
    private Scene scene;
    /**
     * Die Menüleiste.
     */
    private MenuBar menueBar;
    /**
     * Das Menü datei.
     */
    private Menu datei;
    /**
     * Das Menüitem beenden.
     */
    private MenuItem beenden;
    /**
     * Das Menüitem zurück.
     */
    private MenuItem zurueck;
    /**
     * Eine Stage.
     */
    private Stage stage;
    /**
     * Das Pane, auf dem das Inventar dargestellt wird.
     */
    private Pane pane;
    /**
     * Zeigt die aktuelle Anzahl von kleinen Heiltränken an.
     */
    private Label geschickAnzahl;
    /**
     * Zeigt die aktuelle Anzahl von großen Heiltränken an.
     */
    private Label wissenAnzahl;
    /**
     * Zeigt die aktuelle Anzahl von Pokebällen an.
     */
    private Label kraftAnzahl;
    /**
     * Zeigt die aktuelle Anzahl von Pokebällen an.
     */
    private Label lebenAnzahl;
    /**
     * Zeigt die aktuelle Anzahl von Pokebällen an.
     */
    private Label schnelligkeitAnzahl;
    /**
     * Zeigt die aktuelle Anzahl Levels an.
     */
    private Label levelAnzahl;
    /**
     * Beschreibung des kleinen Heiltranks.
     */
    private Label geschickText;
    /**
     * Beschreibung des großer Heiltranks.
     */
    private Label wissenText;
    /**
     * Beschreibung des Pokeballs.
     */
    private Label kraftText;
    /**
     * Beschreibung des Pokeballs.
     */
    private Label lebenText;
    /**
     * Beschreibung des Pokeballs.
     */
    private Label schnelligkeitText;
    /**
     * Beschreibung des Levels.
     */
    private Label levelText;
    
    
    public CharacterView(){
        this.stage = new Stage();
        
        menueBar = new MenuBar();
        datei = new Menu("Datei");
        beenden = new MenuItem("Beenden");
        zurueck = new MenuItem("Zurück");
        menueBar.getMenus().addAll(datei);
        datei.getItems().addAll(zurueck, beenden);
        
        pane = new Pane();
        
        levelText = new Label("Level:");
        levelText.setLayoutX(POS_X_LABELS);
        levelText.setLayoutY(POS_Y_LEVEL);
        pane.getChildren().add(levelText);
        
        lebenText = new Label("Lebenspunkte:");
        lebenText.setLayoutX(POS_X_LABELS);
        lebenText.setLayoutY(POS_Y_LEBEN);
        pane.getChildren().add(lebenText);
        
        wissenText = new Label("Wissen:");
        wissenText.setLayoutX(POS_X_LABELS);
        wissenText.setLayoutY(POS_Y_WISSEN);
        pane.getChildren().add(wissenText);
        
        geschickText = new Label("Geschick:");
        geschickText.setLayoutX(POS_X_LABELS);
        geschickText.setLayoutY(POS_Y_GESCHICK);
        pane.getChildren().add(geschickText);
        
        kraftText = new Label("Kraft:");
        kraftText.setLayoutX(POS_X_LABELS);
        kraftText.setLayoutY(POS_Y_KRAFT);
        pane.getChildren().add(kraftText);
        
        schnelligkeitText = new Label("Schnelligkeit:");
        schnelligkeitText.setLayoutX(POS_X_LABELS);
        schnelligkeitText.setLayoutY(POS_Y_SCHNELLIGKEIT);
        pane.getChildren().add(schnelligkeitText);
        
        levelAnzahl = new Label("");
        levelAnzahl.setLayoutX(POS_X_STATS);
        levelAnzahl.setLayoutY(POS_Y_LEVEL);
        pane.getChildren().add(levelAnzahl);
        
        lebenAnzahl = new Label("");
        lebenAnzahl.setLayoutX(POS_X_STATS);
        lebenAnzahl.setLayoutY(POS_Y_LEBEN);
        pane.getChildren().add(lebenAnzahl);
        
        wissenAnzahl = new Label("");
        wissenAnzahl.setLayoutX(POS_X_STATS);
        wissenAnzahl.setLayoutY(POS_Y_WISSEN);
        pane.getChildren().add(wissenAnzahl);
        
        geschickAnzahl = new Label("");
        geschickAnzahl.setLayoutX(POS_X_STATS);
        geschickAnzahl.setLayoutY(POS_Y_GESCHICK);
        pane.getChildren().add(geschickAnzahl);
        
        kraftAnzahl = new Label("");
        kraftAnzahl.setLayoutX(POS_X_STATS);
        kraftAnzahl.setLayoutY(POS_Y_KRAFT);
        pane.getChildren().add(kraftAnzahl);
        
        schnelligkeitAnzahl = new Label("");
        schnelligkeitAnzahl.setLayoutX(POS_X_STATS);
        schnelligkeitAnzahl.setLayoutY(POS_Y_SCHNELLIGKEIT);
        pane.getChildren().add(schnelligkeitAnzahl);
        
        BorderPane root = new BorderPane(pane, menueBar, null, null, null);

        this.scene = new Scene(root, FENSTER_BREITE, FENSTER_HOEHE);
    }
    
    
    /**
     * Übergibt eine Stage an this.stage, setzt die Scene ein und ruft die show()-Methode auf.
     * @param primaryStage Die primaryStage.
     */
    public void show(Stage primaryStage){
        this.stage = primaryStage;
        this.stage.setTitle("Pokemon");
        this.stage.setScene(scene);
        this.stage.show();
    }
    
    public final MenuItem getBeenden() {
        return beenden;
    }
    
    public final MenuItem getZurueck(){
        return zurueck;
    }
    
    public Stage getStage(){
        return this.stage;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        Avatar avatar = (Avatar) o;
        
        levelAnzahl.setText("" + avatar.getCharacterStats().getLevel());
        lebenAnzahl.setText("" + avatar.getCharacterStats().getLebenspunkte());
        wissenAnzahl.setText("" + avatar.getCharacterStats().getWissen());
        geschickAnzahl.setText("" + avatar.getCharacterStats().getGeschick());
        kraftAnzahl.setText("" + avatar.getCharacterStats().getKraft());
        schnelligkeitAnzahl.setText("" + avatar.getCharacterStats().getSchnelligkeit());
    }
    
}

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Avatar;

/**
 * Die GUI für das Inventar.
 * @author fabiankaupmann
 */
public class ItemView implements Observer, Serializable {
    
    /**
     * Hoehe des Fensters.
     */
    private static final int FENSTER_HOEHE = 530;
    /**
     * Breite des Fensters.
     */
    private static final int FENSTER_BREITE = 500;
    /**
     * Index des großen Heiltranks.
     */
    private static final int INDEX_GROSSER_HEILTRANK = 2;
    /**
     * Schriftgröße auf den Labels für die Anzahl der Gegenstände.
     */
    private static final double FONT_SIZE_ANZAHL = 30.0;
    /**
     * Schriftgröße auf den Labels für die Beschreibung der Gegenstände.
     */
    private static final double FONT_SIZE_BESCHREIBUNG = 18.0;
    /**
     * Koordinate.
     */
    private static final int POS_X_KLEINER_HEILTRANK_ICON = 0;
    /**
     * Koordinate.
     */
    private static final int POS_Y_KLEINER_HEILTRANK_ICON = 20;
    /**
     * Koordinate.
     */
    private static final int POS_X_GROSSER_HEILTRANK_ICON = 10;
    /**
     * Koordinate.
     */
    private static final int POS_Y_GROSSER_HEILTRANK_ICON = 140;
    /**
     * Koordinate.
     */
    private static final int POS_X_POKEBALL_ICON = 10;
    /**
     * Koordinate.
     */
    private static final int POS_Y_POKEBALL_ICON = 260;
    /**
     * Koordinate.
     */
    private static final int POS_X_KLEINER_HEILTRANK_ANZAHL = 100;
    /**
     * Koordinate.
     */
    private static final int POS_Y_KLEINER_HEILTRANK_ANZAHL = 30;
    /**
     * Koordinate.
     */
    private static final int POS_X_GROSSER_HEILTRANK_ANZAHL = 100;
    /**
     * Koordinate.
     */
    private static final int POS_Y_GROSSER_HEILTRANK_ANZAHL = 150;
    /**
     * Koordinate.
     */
    private static final int POS_X_POKEBALL_ANZAHL = 100;
    /**
     * Koordinate.
     */
    private static final int POS_Y_POKEBALL_ANZAHL = 270;
    /**
     * Koordinate.
     */
    private static final int POS_X_KLEINER_HEILTRANK_TEXT = 150;
    /**
     * Koordinate.
     */
    private static final int POS_Y_KLEINER_HEILTRANK_TEXT = 20;
    /**
     * Koordinate.
     */
    private static final int POS_X_GROSSER_HEILTRANK_TEXT = 150;
    /**
     * Koordinate.
     */
    private static final int POS_Y_GROSSER_HEILTRANK_TEXT = 140;
    /**
     * Koordinate.
     */
    private static final int POS_X_POKEBALL_TEXT = 150;
    /**
     * Koordinate.
     */
    private static final int POS_Y_POKEBALL_TEXT = 260;
    /**
     * String "Serif".
     */
    private static final String SERIF = "Serif";
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
     * Stellt den kleinen Heiltrank dar.
     */
    private ImageView kleinerHeiltrankIcon;
    /**
     * Stellt den großen Heiltrank dar.
     */
    private ImageView grosserHeiltrankIcon;
    /**
     * Stellt den Pokeball dar.
     */
    private ImageView pokeballIcon;
    /**
     * Zeigt die aktuelle Anzahl von kleinen Heiltränken an.
     */
    private Label kleinerHeiltrankAnzahl;
    /**
     * Zeigt die aktuelle Anzahl von großen Heiltränken an.
     */
    private Label grosserHeiltrankAnzahl;
    /**
     * Zeigt die aktuelle Anzahl von Pokebällen an.
     */
    private Label pokeballAnzahl;
    /**
     * Beschreibung des kleinen Heiltranks.
     */
    private Label kleinerHeiltrankText;
    /**
     * Beschreibung des großer Heiltranks.
     */
    private Label grosserHeiltrankText;
    /**
     * Beschreibung des Pokeballs.
     */
    private Label pokeballText;
    
    /**
     * Konstruktor.
     */
    public ItemView(){
        this.stage = new Stage();
        
        menueBar = new MenuBar();
        datei = new Menu("Datei");
        beenden = new MenuItem("Beenden");
        zurueck = new MenuItem("Zurück");
        menueBar.getMenus().addAll(datei);
        datei.getItems().addAll(zurueck, beenden);
        
        pane = new Pane();
        
        kleinerHeiltrankIcon = new ImageView();
        kleinerHeiltrankIcon.setLayoutX(POS_X_KLEINER_HEILTRANK_ICON);
        kleinerHeiltrankIcon.setLayoutY(POS_Y_KLEINER_HEILTRANK_ICON);
        pane.getChildren().add(kleinerHeiltrankIcon);
        grosserHeiltrankIcon = new ImageView();
        grosserHeiltrankIcon.setLayoutX(POS_X_GROSSER_HEILTRANK_ICON);
        grosserHeiltrankIcon.setLayoutY(POS_Y_GROSSER_HEILTRANK_ICON);
        pane.getChildren().add(grosserHeiltrankIcon);
        pokeballIcon = new ImageView();
        pokeballIcon.setLayoutX(POS_X_POKEBALL_ICON);
        pokeballIcon.setLayoutY(POS_Y_POKEBALL_ICON);
        pane.getChildren().add(pokeballIcon);
        kleinerHeiltrankAnzahl = new Label("");
        kleinerHeiltrankAnzahl.setFont(new Font(SERIF, FONT_SIZE_ANZAHL));
        kleinerHeiltrankAnzahl.setLayoutX(POS_X_KLEINER_HEILTRANK_ANZAHL);
        kleinerHeiltrankAnzahl.setLayoutY(POS_Y_KLEINER_HEILTRANK_ANZAHL);
        pane.getChildren().add(kleinerHeiltrankAnzahl);
        grosserHeiltrankAnzahl = new Label("");
        grosserHeiltrankAnzahl.setFont(new Font(SERIF, FONT_SIZE_ANZAHL));
        grosserHeiltrankAnzahl.setLayoutX(POS_X_GROSSER_HEILTRANK_ANZAHL);
        grosserHeiltrankAnzahl.setLayoutY(POS_Y_GROSSER_HEILTRANK_ANZAHL);
        pane.getChildren().add(grosserHeiltrankAnzahl);
        pokeballAnzahl = new Label("");
        pokeballAnzahl.setFont(new Font(SERIF, FONT_SIZE_ANZAHL));
        pokeballAnzahl.setLayoutX(POS_X_POKEBALL_ANZAHL);
        pokeballAnzahl.setLayoutY(POS_Y_POKEBALL_ANZAHL);
        pane.getChildren().add(pokeballAnzahl);
        kleinerHeiltrankText = new Label("Kleiner Heiltrank\n(heilt 30 Lebenspunkte)");
        kleinerHeiltrankText.setFont(new Font(SERIF, FONT_SIZE_BESCHREIBUNG));
        kleinerHeiltrankText.setLayoutX(POS_X_KLEINER_HEILTRANK_TEXT);
        kleinerHeiltrankText.setLayoutY(POS_Y_KLEINER_HEILTRANK_TEXT);
        pane.getChildren().add(kleinerHeiltrankText);
        grosserHeiltrankText = new Label("Großer Heiltrank\n(heilt 60 Lebenspunkte)");
        grosserHeiltrankText.setFont(new Font(SERIF, FONT_SIZE_BESCHREIBUNG));
        grosserHeiltrankText.setLayoutX(POS_X_GROSSER_HEILTRANK_TEXT);
        grosserHeiltrankText.setLayoutY(POS_Y_GROSSER_HEILTRANK_TEXT);
        pane.getChildren().add(grosserHeiltrankText);
        pokeballText = new Label("Pokeball\n(wird zum Fangen von Monstern benutzt)");
        pokeballText.setFont(new Font(SERIF, FONT_SIZE_BESCHREIBUNG));
        pokeballText.setLayoutX(POS_X_POKEBALL_TEXT);
        pokeballText.setLayoutY(POS_Y_POKEBALL_TEXT);
        pane.getChildren().add(pokeballText);
        
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
        if (avatar.getInventar()[0] > 0){
            kleinerHeiltrankIcon.setImage(new Image("file:Bilder/small_potion.png"));
        }
        else {
            kleinerHeiltrankIcon.setImage(
                    new Image("file:Bilder/small_potion_black_and_white.png"));
        }
        kleinerHeiltrankAnzahl.setText("" + avatar.getInventar()[0]);
        if (avatar.getInventar()[INDEX_GROSSER_HEILTRANK] > 0){
            grosserHeiltrankIcon.setImage(new Image("file:Bilder/large_potion.png"));
        }
        else {
            grosserHeiltrankIcon.setImage(
                    new Image("file:Bilder/large_potion_black_and_white.png"));
        }
        grosserHeiltrankAnzahl.setText("" + avatar.getInventar()[INDEX_GROSSER_HEILTRANK]);
        if (avatar.getInventar()[1] > 0){
            pokeballIcon.setImage(new Image("file:Bilder/pokeball.jpg"));
        }
        else {
            pokeballIcon.setImage(new Image("file:Bilder/pokeball_black_and_white.jpg"));
        }
        pokeballAnzahl.setText("" + avatar.getInventar()[1]);
    }
            
}

package view;

import control.SpielSteuerung;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Feld;
import static model.Feld.MONSTERSAMMLER;
import model.Spielfeld;

/**
 * Darstellung des Spielfelds. Die Klasse GUI stellt das im Model in der Klasse
 * "Spielfeld" modellierte Spielfeld auf der ihr übergebenen primaryStage dar.
 * Außerdem wird eine Menüleiste angezeigt, über die das Programm beendet werden
 * kann. Die Klasse GUI observiert die Klasse Spielfeld im Sinne des
 * Observer-Patterns, damit bei Änderungen im Model entsprechend die Anzeige
 * aktualisiert werden kann.
 *
 * @author Agirman
 */
public class GUI implements Observer, Serializable {

    /**
     * Vertikale Lücke im GridPane. Ist 0, da die einzelnen Felder des
     * Spielfelds direkt aneinander liegen.
     */
    private static final int GRID_VERTIKAL_GAP = 0;
    /**
     * Horizontale Lücke im GridPane. Ist 0, da die einzelnen Felder des
     * Spielfelds direkt aneinander liegen.
     */
    private static final int GRID_HORIZONTAL_GAP = 0;
    /**
     * Hoehe des Fensters.
     */
    private static final int FENSTER_HOEHE = 550;
    /**
     * Breite des Fensters.
     */
    private static final int FENSTER_BREITE = 500;
    /**
     * Gibt an, wie viele Felder hoch der angezeigte Ausschnitt des Spielfelds
     * ist. Ist immer ungerade, damit der Avatar genau in der Mitte des
     * Spielfelds angezeigt werden kann.
     */
    private static final int AUSSCHNITT_HOEHE = 10;
    /**
     * Gibt an, wie viele Felder breit der angezeigte Ausschnitt des Spielfelds
     * ist. Ist immer ungerade, damit der Avatar genau in der Mitte des
     * Spielfelds angezeigt werden kann.
     */
    private static final int AUSSCHNITT_BREITE = 10;
    /**
     * Viertel Drehung: 90 Grad.
     */
    private static final int VIERTEL_DREHUNG = 90;
    /**
     * Titel des Fensters.
     */
    private static final String TITLE = "Pokemon";
    /**
     * Magic number, um die Hälfte des Ausschnitts zu erhalten.
     */
    private static final int HAELFTE = 2;
    /**
     * Referenz auf die primaryStage.
     */
    private Stage primaryStage;
    /**
     * Das GridPane, auf dem das Spielfeld dargestellt wird.
     */
    private GridPane grid;
    /**
     * Referenz auf den Controller.
     */
    private final SpielSteuerung controller;
    /**
     * Menüknopf.
     */
    private final Menu menu1;
    /**
     * Menubar.
     */
    private final MenuBar menuBar;
    /**
     * MenuItem zum Beenden des Programms.
     */
    private final MenuItem beenden;
    /**
     * MenuItem zum Anzeigen des Inventars.
     */
    private final MenuItem inventar;
    /**
     * MenuItem zum Anzeigen der Monster Liste.
     */
    private final MenuItem monster;
    /**
     * MenuItem zum Starten eines Kampfes.
     */
    private final MenuItem kampf;
    /**
     * MenuItem zum Speichern des Spiels.
     */
    private final MenuItem speichern;
    /**
     * MenuItem zum Laden eines alten Spielstands.
     */
    private final MenuItem laden;
    /**
     * MenuItem zum Öffnen des CharFensters.
     */
    private final MenuItem charStats;
    /**
     * Scene, auf der das Spielfeld angezeigt wird.
     */
    private final Scene spielfeldScene;
    /**
     * Array für Zugriff auf die ImageView Objekte, die den Hintergrund
     * darstellen.
     */
    private ImageView[][] hintergrundView;
    /**
     * Array für Zugriff auf die ImageView Objekte, die den Vordergrund
     * darstellen.
     */
    private ImageView[][] vordergrundView;
    /**
     * Array für Zugriff auf die ImageView Objekte, die den Avatar darstellen.
     * Zu jedem Zeitpunkt stellt nur eins der Objekte den Avatar dar, alle
     * anderen haben kein Bild gesetzt.
     */
    private ImageView[][] avatarView;
    
    private BorderPane root;
    
    private ProgressBar epBar;
    
    private HBox hbox;
    /**
     * Konstruktor der GUI. Baut die Menüleiste und das GridPane auf und
     * positioniert sie mit Hilfe eines BorderPane.
     *
     * @param primaryStage Referenz auf die primaryStage.
     * @param c Referenz auf den Controller.
     */
    public GUI(final SpielSteuerung c) {

        this.controller = c;

        menuBar = new MenuBar();
        menu1 = new Menu("Datei");

        beenden = new MenuItem("Beenden");
        beenden.setOnAction(c);
        kampf = new MenuItem("kampfTest");
        kampf.setOnAction(c);
        inventar = new MenuItem("Inventar");
        inventar.setOnAction(c);
        charStats = new MenuItem("Charakter");
        charStats.setOnAction(c);
        monster = new MenuItem("Monster");
        monster.setOnAction(c);
        speichern = new MenuItem("Speichern");
        speichern.setOnAction(c);
        laden = new MenuItem("Laden");
        laden.setOnAction(c);

        menuBar.getMenus().addAll(menu1);
        menu1.getItems().addAll(charStats,inventar, monster, speichern, laden, beenden);

        initGrid();
        
        hbox = new HBox();
        hbox.setSpacing(5);
        hbox.setAlignment(Pos.CENTER);
        hbox.setMaxSize(FENSTER_HOEHE, 25);
        
        epBar = new ProgressBar(1.0);
        epBar.prefWidthProperty().bind(hbox.widthProperty().subtract(20));
        hbox.getChildren().add(epBar);
        
        this.root = new BorderPane(grid, menuBar, null, null, null);
        this.root.setBottom(hbox);
        
        

        this.spielfeldScene = new Scene(root, FENSTER_BREITE, FENSTER_HOEHE);
        this.spielfeldScene.setOnKeyPressed(this.controller);

        //spielfeldScene.getChildren().add(hbox);
        //spielfeldScene.setRoot(hbox);
        //this.primaryStage.setTitle(TITLE);
        //this.primaryStage.setScene(spielfeldScene);
        //this.primaryStage.show();
    }
    
    /**
     * Initialisiert das GridPane, auf dem das Spielfeld dargestellt wird.
     */
    private void initGrid() {
        grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(GRID_HORIZONTAL_GAP);
        grid.setVgap(GRID_VERTIKAL_GAP);
        grid.setPadding(new Insets(0, 0, 0, 0));

        hintergrundView = new ImageView[AUSSCHNITT_HOEHE][AUSSCHNITT_BREITE];
        vordergrundView = new ImageView[AUSSCHNITT_HOEHE][AUSSCHNITT_BREITE];
        avatarView = new ImageView[AUSSCHNITT_HOEHE][AUSSCHNITT_BREITE];

        for (int i = 0; i < AUSSCHNITT_HOEHE; i++) {
            for (int j = 0; j < AUSSCHNITT_BREITE; j++) {
                hintergrundView[i][j] = new ImageView();
                grid.add(hintergrundView[i][j], i, j);
            }
        }
        for (int i = 0; i < AUSSCHNITT_HOEHE; i++) {
            for (int j = 0; j < AUSSCHNITT_BREITE; j++) {
                vordergrundView[i][j] = new ImageView();
                grid.add(vordergrundView[i][j], i, j);
            }
        }
        for (int i = 0; i < AUSSCHNITT_HOEHE; i++) {
            for (int j = 0; j < AUSSCHNITT_BREITE; j++) {
                avatarView[i][j] = new ImageView();
                grid.add(avatarView[i][j], i, j);
            }
        }
    }

    /**
     * Zeigt wieder die spielfeldScene an.
     * @param primaryStage
     */
    public void show(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(TITLE);
        this.primaryStage.setScene(this.spielfeldScene);
        this.primaryStage.show();
    }

    public final MenuItem getChar(){
        return charStats;
    }
    public final MenuItem getBeenden() {
        return beenden;
    }

    public final MenuItem getKampfTest() {
        return kampf;
    }

    public final Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public final MenuItem getInventar() {
        return inventar;
    }

    public final MenuItem getMonster() {
        return monster;
    }

    public final MenuItem getSpeichern() {
        return speichern;
    }

    public final MenuItem getLaden() {
        return laden;
    }
    public void blackScreen(){
    }

    /**
     * Wird durch Änderungen im Spielfeld ausgelöst und aktualisiert die
     * Anzeige. Holt die aktuellen Hintergrund und Vordergrund Arrays aus dem
 Model und fügt sie nacheinander in das GridPane ein. Dann wird die
 aktuelle Position des Avatars aus dem Model abgefragt und dieser im
 GridPane platziert. Als letztes wird abgefragt, ob sich der Avatar auf
 einem Kampf-Feld befindet und gegebenenfalls ein entsprechendes ActionEvent
 in der SpielSteuerung ausgelöst.
     *
     * @param o Das Spielfeld
     * @param arg leer
     */
    @Override
    public final void update(final Observable o, final Object arg) {
        Spielfeld spielfeld = (Spielfeld) o;
        Feld[][] hintergrund = spielfeld.getHintergrund();
        Feld[][] vordergrund = spielfeld.getVordergrund();
        int ausschnittAnfangX = ausschnittAnfang(spielfeld.getPosX(),
                AUSSCHNITT_BREITE, Spielfeld.getBREITE());
        int ausschnittAnfangY = ausschnittAnfang(spielfeld.getPosY(),
                AUSSCHNITT_HOEHE, Spielfeld.getHOEHE());
        for (int i = 0; i < AUSSCHNITT_BREITE; i++) {
            for (int j = 0; j < AUSSCHNITT_HOEHE; j++) {
                this.hintergrundView[i][j].setImage(
                        hintergrund[ausschnittAnfangX + i][ausschnittAnfangY + j].getImage());
            }
        }
        for (int i = 0; i < AUSSCHNITT_BREITE; i++) {
            for (int j = 0; j < AUSSCHNITT_HOEHE; j++) {
                this.vordergrundView[i][j].setImage(
                        vordergrund[ausschnittAnfangX + i][ausschnittAnfangY + j].getImage());
            }
        }
        for (int i = 0; i < AUSSCHNITT_BREITE; i++) {
            for (int j = 0; j < AUSSCHNITT_HOEHE; j++) {
                this.avatarView[i][j].setImage(null);
            }
        }
        avatarView[spielfeld.getPosX() - ausschnittAnfangX][spielfeld.getPosY()
                - ausschnittAnfangY].setImage(MONSTERSAMMLER.getImage());
        avatarView[spielfeld.getPosX() - ausschnittAnfangX][spielfeld.getPosY() - ausschnittAnfangY]
                .setRotate(spielfeld.getBlickrichtungAvatar() * VIERTEL_DREHUNG);
        if (spielfeld.isAufKampfFeld()) {
            kampf.fire();
        }
        //EPLeiste
        this.epBar.setProgress((double) controller.getAvatar().getCharacterStats().getErfahrungspunkte()/
                this.controller.getAvatar().getCharacterStats().calcFullLevelEp(this.controller.getAvatar()
                .getCharacterStats().getLevel()));
    }

    /**
     * Berechnet, welcher Ausschnitt des Spielfelds dargestellt werden muss,
     * damit sich der Avatar an der korrekten Position befindet.
     * Wird für die x- und y-Dimension getrennt aufgerufen.
     * @param position Position des Avatars
     * @param groesseAusschnitt Größe des Ausschnitts auf dem Bildschirm in Feldern
     * @param groesseSpielfeld Größe des Spielfelds in Feldern
     * @return Index des ersten darzustellenden Feldes
     */
    private int ausschnittAnfang(int position, int groesseAusschnitt, int groesseSpielfeld) {
        if (position < (groesseAusschnitt / HAELFTE)) {
            return 0;
        } else if (position > (groesseSpielfeld - 1) - (groesseAusschnitt / HAELFTE)) {
            return groesseSpielfeld - groesseAusschnitt;
        } else {
            return position - (groesseAusschnitt / HAELFTE);
        }
    }
}

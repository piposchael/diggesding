package view;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Avatar;
import model.Kampfsystem;

/**
 * Die GUI für den Kampf.
 * @author fabiankaupmann
 */
public class KampfView implements Observer, Serializable {

    /**
     * Hoehe des Fensters.
     */
    private static final int FENSTER_HOEHE = 670;
    /**
     * Breite des Fensters.
     */
    private static final int FENSTER_BREITE = 520;
    /**
     * Spacing in den HBoxen für die Buttons.
     */
    private static final int HBOX_SPACING = 30;
    /**
     * Padding in den HBoxen für die Buttons.
     */
    private static final int HBOX_PADDING = 15;
    /**
     * Y-Koordinate der ImageView für das Gegner-Monster.
     */
    private static final int POS_Y_MONSTER_GEGNER = 0;
    /**
     * X-Koordinate der ImageView für das Gegner-Monster.
     */
    private static final int POS_X_MONSTER_GEGNER = 250;
    /**
     * Y-Koordinate der ImageView für das Spieler-Monster.
     */
    private static final int POS_Y_MONSTER_SPIELER = 250;
    /**
     * X-Koordinate der ImageView für das Spieler-Monster.
     */
    private static final int POS_X_MONSTER_SPIELER = 0;
    /**
     * Y-Koordinate des Labels für den Namen des Spieler-Monsters.
     */
    private static final int POS_Y_NAME_SPIELER_MONSTER = 275;
    /**
     * X-Koordinate des Labels für den Namen des Spieler-Monsters.
     */
    private static final int POS_X_NAME_SPIELER_MONSTER = 280;
    /**
     * Y-Koordinate des Labels für den Namen des Gegner-Monsters.
     */
    private static final int POS_Y_NAME_GEGNER_MONSTER = 25;
    /**
     * X-Koordinate des Labels für den Namen des Gegner-Monsters.
     */
    private static final int POS_X_NAME_GEGNER_MONSTER = 30;
    /**
     * Y-Koordinate des Labels für das Level des Spieler-Monsters.
     */
    private static final int POS_Y_LEVEL_SPIELER_MONSTER = 315;
    /**
     * X-Koordinate des Labels für das Level des Spieler-Monsters.
     */
    private static final int POS_X_LEVEL_SPIELER_MONSTER = 280;
    /**
     * Y-Koordinate des Labels für das Level des Gegner-Monsters.
     */
    private static final int POS_Y_LEVEL_GEGNER_MONSTER = 65;
    /**
     * X-Koordinate des Labels für das Level des Gegner-Monsters.
     */
    private static final int POS_X_LEVEL_GEGNER_MONSTER = 30;
    /**
     * Y-Koordinate der ProgressBar für die Lebenspunkte des Spieler-Monsters.
     */
    private static final int POS_Y_HP_SPIELER_MONSTER = 355;
    /**
     * X-Koordinate der ProgressBar für die Lebenspunkte des Spieler-Monsters.
     */
    private static final int POS_X_HP_SPIELER_MONSTER = 280;
    /**
     * Y-Koordinate der ProgressBar für die Lebenspunkte des Gegner-Monsters.
     */
    private static final int POS_Y_HP_GEGNER_MONSTER = 105;
    /**
     * X-Koordinate der ProgressBar für die Lebenspunkte des Gegner-Monsters.
     */
    private static final int POS_X_HP_GEGNER_MONSTER = 30;
    /**
     * Y-Koordinate des Gift-Icons für das Spieler-Monsters.
     */
    private static final int POS_Y_GIFT_SPIELER_MONSTER = 395;
    /**
     * X-Koordinate des Gift-Icons für das Spieler-Monsters.
     */
    private static final int POS_X_GIFT_SPIELER_MONSTER = 312;
    /**
     * Y-Koordinate des Gift-Icons für das Gegner-Monsters.
     */
    private static final int POS_Y_GIFT_GEGNER_MONSTER = 145;
    /**
     * X-Koordinate des Gift-Icons für das Gegner-Monsters.
     */
    private static final int POS_X_GIFT_GEGNER_MONSTER = 62;
    /**
     * Y-Koordinate des Paralyse-Icons für das Spieler-Monsters.
     */
    private static final int POS_Y_PARALYSE_SPIELER_MONSTER = 395;
    /**
     * X-Koordinate des Paralyse-Icons für das Spieler-Monsters.
     */
    private static final int POS_X_PARALYSE_SPIELER_MONSTER = 344;
    /**
     * Y-Koordinate des Paralyse-Icons für das Gegner-Monsters.
     */
    private static final int POS_Y_PARALYSE_GEGNER_MONSTER = 145;
    /**
     * X-Koordinate des Paralyse-Icons für das Gegner-Monsters.
     */
    private static final int POS_X_PARALYSE_GEGNER_MONSTER = 94;
    /**
     * Y-Koordinate des Paralyse-Icons für das Spieler-Monsters.
     */
    private static final int POS_Y_SCHLAF_SPIELER_MONSTER = 395;
    /**
     * X-Koordinate des Schlaf-Icons für das Spieler-Monsters.
     */
    private static final int POS_X_SCHLAF_SPIELER_MONSTER = 280;
    /**
     * Y-Koordinate des Schlaf-Icons für das Gegner-Monsters.
     */
    private static final int POS_Y_SCHLAF_GEGNER_MONSTER = 145;
    /**
     * X-Koordinate des Schlaf-Icons für das Gegner-Monsters.
     */
    private static final int POS_X_SCHLAF_GEGNER_MONSTER = 30;
    /**
     * Schriftgröße auf den Labels.
     */
    private static final double FONT_SIZE = 18.0;
    /**
     * Index des großen Heiltranks.
     */
    private static final int INDEX_GROSSER_HEILTRANK = 2;
    /**
     * String " : ".
     */
    private static final String DOPPELPUNKT = " : ";
    /**
     * String "Item".
     */
    private static final String ITEM = "Item";
    /**
     * String "Angriff".
     */
    private static final String ANGRIFF = "Angriff";
    /**
     * String "Fliehen".
     */
    private static final String FLIEHEN = "Fliehen";
    /**
     * String ".png".
     */
    private static final String PNG = ".png";
    /**
     * String "file:Bilder/".
     */
    private static final String BILDER_PFAD = "file:Bilder/";
    /**
     * String "file:Bilder/poison.png".
     */
    private static final String GIFT_ICON_PFAD = "file:Bilder/poison.png";
    /**
     * String "file:Bilder/sleep.png".
     */
    private static final String SCHLAF_ICON_PFAD = "file:Bilder/sleep.png";
    /**
     * String "file:Bilder/paralysed.png".
     */
    private static final String PARALYSE_ICON_PFAD = "file:Bilder/paralysed.png";
    /**
     * String "Serif".
     */
    private static final String SERIF = "Serif";
    /**
     * String "LEVEL ".
     */
    private static final String LEVEL = "LEVEL ";
    /**
     * String "file:Bilder/pokeball.jpg".
     */
    private static final String POKEBALL_ICON_PFAD = "file:Bilder/pokeball.jpg";
    /**
     * String "file:Bilder/small_potion.png".
     */
    private static final String KLEINER_HEILTRANK_ICON_PFAD = "file:Bilder/small_potion.png";
    /**
     * String "file:Bilder/large_potion.png".
     */
    private static final String GROSSER_HEILTRANK_ICON_PFAD = "file:Bilder/large_potion.png";
    /**
     * String ")".
     */
    private static final String KLAMMER_ZU = ")";
    /**
     * Referenz auf die primaryStage.
     */
    private Stage stage;
    /**
     * Wurzel des SceneGraph.
     */
    private BorderPane root;
    /**
     * Das GridPane, auf dem die Monster dargestellt werden.
     */
    private Pane pane;
    /**
     * Das Pane, auf dem die Buttons zur Auswahl zwischen Angriff, Item
     * und Fliehen dargestellt werden.
     */
    private HBox angriffItemFliehen;
    /**
     * Das Pane, auf dem die Buttons zur Auswahl einer Attacke dargestellt
     * werden.
     */
    private HBox attacken;
    /**
     * Das Pane, auf dem die Buttons zur Auswahl eines Items dargestellt
     * werden.
     */
    private HBox items;
    /**
     * Menüknopf.
     */
    private final Menu menu1;
    /**
     * Menubar.
     */
    private final MenuBar menuBar;
    /**
     * MenuItem beenden.
     */
    private final MenuItem beenden;
    /**
     * Button angriffButton.
     */
    private final Button angriffButton;
    /**
     * Button itemButton.
     */
    private final Button itemButton;
    /**
     * Button zum Fliehen aus dem Kampf.
     */
    private final Button fliehenButton;
    /**
     * Button attacke1Button.
     */
    private final Button attacke1Button;
    /**
     * Button attacke2Button.
     */
    private final Button attacke2Button;
    /**
     * Button attacke3Button.
     */
    private final Button attacke3Button;
    /**
     * Button attacke4Button.
     */
    private final Button attacke4Button;
    /**
     * ImageView, die das Monster des Spielers darstellt.
     */
    private ImageView monsterSpieler;
    /**
     * ImageView, die das Monster des Gegners darstellt.
     */
    private ImageView monsterGegner;
    /**
     * Label auf dem der Name des Spieler-Monsters angezeigt wird.
     */
    private Label nameSpielerMonster;
    /**
     * Label auf dem der Name des Gegner-Monsters angezeigt wird.
     */
    private Label nameGegnerMonster;
    /**
     * Label auf dem das Level des Spieler-Monsters angezeigt wird.
     */
    private Label levelSpielerMonster;
    /**
     * Label auf dem das Level des Gegner-Monsters angezeigt wird.
     */
    private Label levelGegnerMonster;
    /**
     * ProgressBar, die die Lebenspunkte des Spieler-Monsters darstellt.
     */
    private ProgressBar hpSpielerMonster;
    /**
     * ProgressBar, die die Lebenspunkte des Gegner-Monsters darstellt.
     */
    private ProgressBar hpGegnerMonster;
    /**
     * ImageView für das Gift-Icon des Spieler-Monsters.
     */
    private ImageView giftSpielerMonster;
    /**
     * ImageView für das Gift-Icon des Gegner-Monsters.
     */
    private ImageView giftGegnerMonster;
    /**
     * ImageView für das Schlaf-Icon des Spieler-Monsters.
     */
    private ImageView schlafSpielerMonster;
    /**
     * ImageView für das Schlaf-Icon des Gegner-Monsters.
     */
    private ImageView schlafGegnerMonster;
    /**
     * ImageView für das Paralyse-Icon des Spieler-Monsters.
     */
    private ImageView paralyseSpielerMonster;
    /**
     * ImageView für das Paralyse-Icon des Gegner-Monsters.
     */
    private ImageView paralyseGegnerMonster;
    /**
     * Button für den kleinen Heiltrank.
     */
    private Button kleinerHeiltrank;
    /**
     * Button für den großen Heiltrank.
     */
    private Button grosserHeiltrank;
    /**
     * ImageView für das Kleiner-Heiltrank-Icon.
     */
    private ImageView kleinerHeiltrankIcon;
    /**
     * ImageView für das Großer-Heiltrank-Icon.
     */
    private ImageView grosserHeiltrankIcon;
    /**
     * ImageView für das Pokeball-Icon.
     */
    private ImageView pokeballIcon;
    /**
     * Button für den Pokeball.
     */
    private Button pokeball;
    /**
     * Button um aus der Item-auswahl heraus zu kommen.
     */
    private Button zurueck;
    /**
     * Die Scene.
     */
    private Scene scene;
    /**
     * Referenz auf den Avatar.
     */
    private Avatar avatar;
    
    /**
     * Initialisiert alle nötigen Objekte, setzt das Menü zusammen, fügt dem Grid
     * alle grafischen Objekte hinzu und übergibt der Stage alles als Scene.
     * @param avatar der Avatar
     */
    public KampfView(Avatar avatar) {
        this.avatar = avatar;
        this.stage = new Stage();

        menuBar = new MenuBar();
        menu1 = new Menu("Datei");
        beenden = new MenuItem("Beenden");
        menuBar.getMenus().addAll(menu1);
        menu1.getItems().add(beenden);
        
        
        pane = new Pane();
        monsterSpieler = new ImageView();
        monsterSpieler.setLayoutX(POS_X_MONSTER_SPIELER);
        monsterSpieler.setLayoutY(POS_Y_MONSTER_SPIELER);
        pane.getChildren().add(monsterSpieler);
        monsterGegner = new ImageView();
        monsterGegner.setLayoutX(POS_X_MONSTER_GEGNER);
        monsterGegner.setLayoutY(POS_Y_MONSTER_GEGNER);
        pane.getChildren().add(monsterGegner);
        nameSpielerMonster = new Label();
        nameSpielerMonster.setFont(new Font(SERIF, FONT_SIZE));
        nameSpielerMonster.setLayoutX(POS_X_NAME_SPIELER_MONSTER);
        nameSpielerMonster.setLayoutY(POS_Y_NAME_SPIELER_MONSTER);
        pane.getChildren().add(nameSpielerMonster);
        nameGegnerMonster = new Label();
        nameGegnerMonster.setFont(new Font(SERIF, FONT_SIZE));
        nameGegnerMonster.setLayoutX(POS_X_NAME_GEGNER_MONSTER);
        nameGegnerMonster.setLayoutY(POS_Y_NAME_GEGNER_MONSTER);
        pane.getChildren().add(nameGegnerMonster);
        levelSpielerMonster = new Label();
        levelSpielerMonster.setFont(new Font(SERIF, FONT_SIZE));
        levelSpielerMonster.setLayoutX(POS_X_LEVEL_SPIELER_MONSTER);
        levelSpielerMonster.setLayoutY(POS_Y_LEVEL_SPIELER_MONSTER);
        pane.getChildren().add(levelSpielerMonster);
        levelGegnerMonster = new Label();
        levelGegnerMonster.setFont(new Font(SERIF, FONT_SIZE));
        levelGegnerMonster.setLayoutX(POS_X_LEVEL_GEGNER_MONSTER);
        levelGegnerMonster.setLayoutY(POS_Y_LEVEL_GEGNER_MONSTER);
        pane.getChildren().add(levelGegnerMonster);
        hpSpielerMonster = new ProgressBar(1.0);
        hpSpielerMonster.setLayoutX(POS_X_HP_SPIELER_MONSTER);
        hpSpielerMonster.setLayoutY(POS_Y_HP_SPIELER_MONSTER);
        pane.getChildren().add(hpSpielerMonster);
        hpGegnerMonster = new ProgressBar(1.0);
        hpGegnerMonster.setLayoutX(POS_X_HP_GEGNER_MONSTER);
        hpGegnerMonster.setLayoutY(POS_Y_HP_GEGNER_MONSTER);
        pane.getChildren().add(hpGegnerMonster);
        giftSpielerMonster = new ImageView(GIFT_ICON_PFAD);
        giftSpielerMonster.setVisible(false);
        giftSpielerMonster.setLayoutX(POS_X_GIFT_SPIELER_MONSTER);
        giftSpielerMonster.setLayoutY(POS_Y_GIFT_SPIELER_MONSTER);
        pane.getChildren().add(giftSpielerMonster);
        giftGegnerMonster = new ImageView(GIFT_ICON_PFAD);
        giftGegnerMonster.setVisible(false);
        giftGegnerMonster.setLayoutX(POS_X_GIFT_GEGNER_MONSTER);
        giftGegnerMonster.setLayoutY(POS_Y_GIFT_GEGNER_MONSTER);
        pane.getChildren().add(giftGegnerMonster);
        schlafSpielerMonster = new ImageView(SCHLAF_ICON_PFAD);
        schlafSpielerMonster.setVisible(false);
        schlafSpielerMonster.setLayoutX(POS_X_SCHLAF_SPIELER_MONSTER);
        schlafSpielerMonster.setLayoutY(POS_Y_SCHLAF_SPIELER_MONSTER);
        pane.getChildren().add(schlafSpielerMonster);
        schlafGegnerMonster = new ImageView(SCHLAF_ICON_PFAD);
        schlafGegnerMonster.setVisible(false);
        schlafGegnerMonster.setLayoutX(POS_X_SCHLAF_GEGNER_MONSTER);
        schlafGegnerMonster.setLayoutY(POS_Y_SCHLAF_GEGNER_MONSTER);
        pane.getChildren().add(schlafGegnerMonster);
        paralyseSpielerMonster = new ImageView(PARALYSE_ICON_PFAD);
        paralyseSpielerMonster.setVisible(false);
        paralyseSpielerMonster.setLayoutX(POS_X_PARALYSE_SPIELER_MONSTER);
        paralyseSpielerMonster.setLayoutY(POS_Y_PARALYSE_SPIELER_MONSTER);
        pane.getChildren().add(paralyseSpielerMonster);
        paralyseGegnerMonster = new ImageView(PARALYSE_ICON_PFAD);
        paralyseGegnerMonster.setVisible(false);
        paralyseGegnerMonster.setLayoutX(POS_X_PARALYSE_GEGNER_MONSTER);
        paralyseGegnerMonster.setLayoutY(POS_Y_PARALYSE_GEGNER_MONSTER);
        pane.getChildren().add(paralyseGegnerMonster);
        
        angriffButton = new Button(ANGRIFF);
        itemButton = new Button(ITEM);
        fliehenButton = new Button(FLIEHEN);
        angriffItemFliehen = new HBox();
        angriffItemFliehen.setPadding(
                new Insets(HBOX_PADDING, HBOX_PADDING, HBOX_PADDING, HBOX_PADDING));
        angriffItemFliehen.setSpacing(HBOX_SPACING);
        angriffItemFliehen.getChildren().addAll(angriffButton, itemButton, fliehenButton);
        
        attacke1Button = new Button("Attacke1");
        attacke2Button = new Button("Attacke2");
        attacke3Button = new Button("Attacke3");
        attacke4Button = new Button("Attacke4");
        attacken = new HBox();
        attacken.setPadding(
                new Insets(HBOX_PADDING, HBOX_PADDING, HBOX_PADDING, HBOX_PADDING));
        attacken.setSpacing(HBOX_SPACING);
        attacken.getChildren().addAll(attacke1Button, attacke2Button,
                attacke3Button, attacke4Button);
        
        items = new HBox();
        items.setPadding(
                new Insets(HBOX_PADDING, HBOX_PADDING, HBOX_PADDING, HBOX_PADDING));
        items.setSpacing(HBOX_SPACING);
        kleinerHeiltrankIcon = new ImageView(KLEINER_HEILTRANK_ICON_PFAD);
        grosserHeiltrankIcon = new ImageView(GROSSER_HEILTRANK_ICON_PFAD);
        kleinerHeiltrank = new Button("", kleinerHeiltrankIcon);
        grosserHeiltrank = new Button("", grosserHeiltrankIcon);
        kleinerHeiltrank.setContentDisplay(ContentDisplay.TOP);
        grosserHeiltrank.setContentDisplay(ContentDisplay.TOP);
        pokeballIcon = new ImageView(POKEBALL_ICON_PFAD);
        pokeball = new Button("", pokeballIcon);
        pokeball.setContentDisplay(ContentDisplay.TOP);
        zurueck = new Button("", new ImageView("file:Bilder/back.png"));
        items.getChildren().addAll(kleinerHeiltrank, grosserHeiltrank, pokeball, zurueck);
        
        root = new BorderPane(pane, menuBar, null, angriffItemFliehen, null);

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

    public Button getZurueck() {
        return zurueck;
    }

    public Button getFliehenButton() {
        return fliehenButton;
    }
    
    public final MenuItem getBeenden() {
        return beenden;
    }
    
    public final Button getAngriffButton(){
        return angriffButton;
    }

    public final Button getItemButton(){
        return itemButton;
    }
    
    public final Button getAttacke1Button(){
        return attacke1Button;
    }
    
    public final Button getAttacke2Button(){
        return attacke2Button;
    }
    
    public Button getAttacke3Button() {
        return attacke3Button;
    }

    public Button getAttacke4Button() {
        return attacke4Button;
    }

    public Scene getScene() {
        return scene;
    }

    public Button getKleinerHeiltrank() {
        return kleinerHeiltrank;
    }

    public Button getGrosserHeiltrank() {
        return grosserHeiltrank;
    }

    public Button getPokeball() {
        return pokeball;
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
        Kampfsystem tmp = (Kampfsystem) o;
        if (avatar != null) {
            if (avatar.getInventar()[0] > 0){
                kleinerHeiltrankIcon.setImage(new Image(KLEINER_HEILTRANK_ICON_PFAD));
                kleinerHeiltrank.setText(
                        "Kleiner Heiltrank\n(noch " + avatar.getInventar()[0] + KLAMMER_ZU);
                kleinerHeiltrank.setDisable(false);
            }
            else {
                kleinerHeiltrankIcon.setImage(
                        new Image("file:Bilder/small_potion_black_and_white.png"));
                kleinerHeiltrank.setText("Kleiner Heiltrank\n(leer)");
                kleinerHeiltrank.setDisable(true);
            }
            if (avatar.getInventar()[INDEX_GROSSER_HEILTRANK] > 0){
                grosserHeiltrankIcon.setImage(new Image(GROSSER_HEILTRANK_ICON_PFAD));
                grosserHeiltrank.setText("Großer Heiltrank\n(noch "
                        + avatar.getInventar()[INDEX_GROSSER_HEILTRANK] + KLAMMER_ZU);
                grosserHeiltrank.setDisable(false);
            }
            else {
                grosserHeiltrankIcon.setImage(
                        new Image("file:Bilder/large_potion_black_and_white.png"));
                grosserHeiltrank.setText("Großer Heiltrank\n(leer)");
                grosserHeiltrank.setDisable(true);
            }
            if (avatar.getInventar()[1] > 0) {
                pokeballIcon.setImage(new Image(POKEBALL_ICON_PFAD));
                pokeball.setText("Pokeball\n(noch " + avatar.getInventar()[1] + KLAMMER_ZU);
                pokeball.setDisable(false);
            }
            else {
                pokeballIcon.setImage(new Image("file:Bilder/pokeball_black_and_white.jpg"));
                pokeball.setText("Pokeball\n(leer)");
                pokeball.setDisable(true);
            }
        }
        if(tmp.getSpielerMonster() != null){
          this.attacke1Button.setText(tmp.getSpielerMonster().getNameAttacke1());
          this.attacke2Button.setText(tmp.getSpielerMonster().getNameAttacke2());
          this.attacke3Button.setText(tmp.getSpielerMonster().getNameAttacke3());
          this.attacke4Button.setText(tmp.getSpielerMonster().getNameAttacke4());
          
          monsterSpieler.setImage(new Image(
                    BILDER_PFAD + tmp.getSpielerMonster().getNameMonster() + PNG));
          nameSpielerMonster.setText(tmp.getSpielerMonster().getNameMonster().toUpperCase());
          levelSpielerMonster.setText(LEVEL + tmp.getSpielerMonster().getLevel());
          hpSpielerMonster.setProgress(
                    ((double) tmp.getSpielerMonster().getLebensPunkte())/Kampfsystem.MAX_HP);
          switch (tmp.getStatusSpieler()) {
              case Kampfsystem.KEINE_EFFEKTE:
                  schlafSpielerMonster.setVisible(false);
                  giftSpielerMonster.setVisible(false);
                  paralyseSpielerMonster.setVisible(false);
                  break;
              case Kampfsystem.VERGIFTET:
                  schlafSpielerMonster.setVisible(false);
                  giftSpielerMonster.setVisible(true);
                  paralyseSpielerMonster.setVisible(false);
                  break;
              case Kampfsystem.SCHLAF:
                  schlafSpielerMonster.setVisible(true);
                  giftSpielerMonster.setVisible(false);
                  paralyseSpielerMonster.setVisible(false);
                  break;
              case Kampfsystem.PARALYSE:
                  schlafSpielerMonster.setVisible(false);
                  giftSpielerMonster.setVisible(false);
                  paralyseSpielerMonster.setVisible(true);
                  break;
              case Kampfsystem.VERGIFTET_SCHLAF:
                  schlafSpielerMonster.setVisible(true);
                  giftSpielerMonster.setVisible(true);
                  paralyseSpielerMonster.setVisible(false);
                  break;
              case Kampfsystem.VERGIFTET_PARALYSE:
                  schlafSpielerMonster.setVisible(false);
                  giftSpielerMonster.setVisible(true);
                  paralyseSpielerMonster.setVisible(true);
                  break;
              case Kampfsystem.SCHLAF_PARALYSE:
                  schlafSpielerMonster.setVisible(true);
                  giftSpielerMonster.setVisible(false);
                  paralyseSpielerMonster.setVisible(true);
                  break;
              case Kampfsystem.SCHLAF_PARALYSE_VERGIFTET:
                  schlafSpielerMonster.setVisible(true);
                  giftSpielerMonster.setVisible(true);
                  paralyseSpielerMonster.setVisible(true);
                  break;
              default:
                  break;
          }
        }
        
        if (tmp.getGegnerMonster() != null) {
            monsterGegner.setImage(new Image(
                    BILDER_PFAD + tmp.getGegnerMonster().getNameMonster() + PNG));
            nameGegnerMonster.setText(tmp.getGegnerMonster().getNameMonster().toUpperCase());
            levelGegnerMonster.setText(LEVEL + tmp.getGegnerMonster().getLevel());
            hpGegnerMonster.setProgress(
                    ((double) tmp.getGegnerMonster().getLebensPunkte())/Kampfsystem.MAX_HP);
            
            switch (tmp.getStatusGegner()) {
              case Kampfsystem.KEINE_EFFEKTE:
                  schlafGegnerMonster.setVisible(false);
                  giftGegnerMonster.setVisible(false);
                  paralyseGegnerMonster.setVisible(false);
                  break;
              case Kampfsystem.VERGIFTET:
                  schlafGegnerMonster.setVisible(false);
                  giftGegnerMonster.setVisible(true);
                  paralyseGegnerMonster.setVisible(false);
                  break;
              case Kampfsystem.SCHLAF:
                  schlafGegnerMonster.setVisible(true);
                  giftGegnerMonster.setVisible(false);
                  paralyseGegnerMonster.setVisible(false);
                  break;
              case Kampfsystem.PARALYSE:
                  schlafGegnerMonster.setVisible(false);
                  giftGegnerMonster.setVisible(false);
                  paralyseGegnerMonster.setVisible(true);
                  break;
              case Kampfsystem.VERGIFTET_SCHLAF:
                  schlafGegnerMonster.setVisible(true);
                  giftGegnerMonster.setVisible(true);
                  paralyseGegnerMonster.setVisible(false);
                  break;
              case Kampfsystem.VERGIFTET_PARALYSE:
                  schlafGegnerMonster.setVisible(false);
                  giftGegnerMonster.setVisible(true);
                  paralyseGegnerMonster.setVisible(true);
                  break;
              case Kampfsystem.SCHLAF_PARALYSE:
                  schlafGegnerMonster.setVisible(true);
                  giftGegnerMonster.setVisible(false);
                  paralyseGegnerMonster.setVisible(true);
                  break;
              case Kampfsystem.SCHLAF_PARALYSE_VERGIFTET:
                  schlafGegnerMonster.setVisible(true);
                  giftGegnerMonster.setVisible(true);
                  paralyseGegnerMonster.setVisible(true);
                  break;
              default:
                  break;
          }
        }
        if(null != tmp.getKampfPhase())
        {
            switch (tmp.getKampfPhase()) {
            case "":
                this.angriffButton.setDisable(false);
                this.itemButton.setDisable(false);
                this.fliehenButton.setDisable(false);
                this.attacke1Button.setDisable(true);
                this.attacke2Button.setDisable(true);
                this.attacke3Button.setDisable(true);
                this.attacke4Button.setDisable(true);
                if (tmp.getStatusSpieler() == Kampfsystem.SCHLAF
                        || tmp.getStatusSpieler() == Kampfsystem.VERGIFTET_SCHLAF
                        || tmp.getStatusSpieler() == Kampfsystem.SCHLAF_PARALYSE
                        || tmp.getStatusSpieler() == Kampfsystem.SCHLAF_PARALYSE_VERGIFTET) {
                    angriffButton.setText("Passen");
                }
                else {
                    angriffButton.setText(ANGRIFF);
                }
                root.setBottom(angriffItemFliehen);
                break;
            case "Gegner":
                this.angriffButton.setDisable(true);
                this.itemButton.setDisable(true);
                this.fliehenButton.setDisable(true);
                this.attacke1Button.setDisable(true);
                this.attacke2Button.setDisable(true);
                this.attacke3Button.setDisable(true);
                this.attacke4Button.setDisable(true);
                root.setBottom(angriffItemFliehen);
                break;
            case ANGRIFF:
                this.angriffButton.setDisable(true);
                this.itemButton.setDisable(true);
                this.fliehenButton.setDisable(true);
                this.attacke1Button.setDisable(false);
                this.attacke2Button.setDisable(false);
                this.attacke3Button.setDisable(false);
                this.attacke4Button.setDisable(false);
                root.setBottom(attacken);
                break;
            case ITEM:
                root.setBottom(items);
                break;
            default:
                break;
        }
        }
    }
}

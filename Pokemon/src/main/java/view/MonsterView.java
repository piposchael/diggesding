package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Avatar;
import model.Kampfsystem;

/**
 * Die GUI für den MonsterBeutel.
 * @author fabiankaupmann
 */
public class MonsterView implements Observer, Serializable {
    /**
     * Magic String für den Text "leerer Slot".
     */ 
    private static final String LEERER_SLOT = "Leerer Slot";
    /**
     * Hoehe des Fensters.
     */
    private static final int FENSTER_HOEHE = 500;
    /**
     * Breite des Fensters.
     */
    private static final int FENSTER_BREITE = 500;
    /**
     * Koordinate.
     */
    private static final int POS_X_MONSTER_1 = 0;
    /**
     * Koordinate.
     */
    private static final int POS_Y_MONSTER_1 = 0;
    /**
     * Koordinate.
     */
    private static final int POS_X_MONSTER_2 = 0;
    /**
     * Koordinate.
     */
    private static final int POS_Y_MONSTER_2 = 30;
    /**
     * Koordinate.
     */
    private static final int POS_X_MONSTER_3 = 0;
    /**
     * Koordinate.
     */
    private static final int POS_Y_MONSTER_3 = 60;
    /**
     * Koordinate.
     */
    private static final int POS_X_MONSTER_4 = 0;
    /**
     * Koordinate.
     */
    private static final int POS_Y_MONSTER_4 = 90;
    /**
     * Koordinate.
     */
    private static final int POS_X_MONSTER_5 = 0;
    /**
     * Koordinate.
     */
    private static final int POS_Y_MONSTER_5 = 120;
    /**
     * Koordinate.
     */
    private static final int POS_X_MONSTER_6 = 0;
    /**
     * Koordinate.
     */
    private static final int POS_Y_MONSTER_6 = 150;
    /**
     * Koordinate.
     */
    private static final int POS_X_MONSTER_7 = 0;
    /**
     * Koordinate.
     */
    private static final int POS_Y_MONSTER_7 = 180;
    /**
     * Koordinate.
     */
    private static final int POS_X_MONSTER_8 = 0;
    /**
     * Koordinate.
     */
    private static final int POS_Y_MONSTER_8 = 210;
    /**
     * Koordinate.
     */
    private static final int POS_X_MONSTER_9 = 0;
    /**
     * Koordinate.
     */
    private static final int POS_Y_MONSTER_9 = 240;
    /**
     * Koordinate.
     */
    private static final int POS_X_MONSTER_10 = 0;
    /**
     * Koordinate.
     */
    private static final int POS_Y_MONSTER_10 = 270;
    /**
     * Koordinate.
     */
    private static final int POS_X_MONSTER_BILD = 100;
    /**
     * Koordinate.
     */
    private static final int POS_Y_MONSTER_BILD = 0;
    /**
     * Koordinate.
     */
    private static final int POS_X_MONSTER_NAME = 100;
    /**
     * Koordinate.
     */
    private static final int POS_Y_MONSTER_NAME = 250;
    /**
     * Koordinate.
     */
    private static final int POS_X_MONSTER_LEVEL = 220;
    /**
     * Koordinate.
     */
    private static final int POS_Y_MONSTER_LEVEL = 250;
    /**
     * Koordinate.
     */
    private static final int POS_X_MONSTER_HP = 320;
    /**
     * Koordinate.
     */
    private static final int POS_Y_MONSTER_HP = 250;
    /**
     * Schriftart für die Labels.
     */
    private static final String FONT_NAME = "Serif";
    /**
     * Schriftgröße für die Labels.
     */
    private static final double FONT_SIZE = 18.0;
    /**
     * Eine Scene.
     */
    private Scene scene;
    /**
     * Die Menüleiste.
     */
    private MenuBar menueBar;
    /**
     * MenuItem Datei.
     */
    private Menu datei;
    /**
     * MenuItem Beenden.
     */
    private MenuItem menueItem;
    /**
     * MenuItem zruück.
     */
    private MenuItem zurueck;
    /**
     * Ein GridPane.
     */
    private GridPane grid;
    /**
     * Pane, enthält Buttons, Bild des Monsters und Informationen zu dem Monster.
     */
    private Pane pane;
    /**
     * Eien Stage.
     */
    private Stage stage;  
    /**
     * Ein Button für das jeweilige Monster.
     */
    private Button monster1;
    /**
     * Ein Button für das jeweilige Monster.
     */
    private Button monster2;
    /**
     * Ein Button für das jeweilige Monster.
     */
    private Button monster3;
    /**
     * Ein Button für das jeweilige Monster.
     */
    private Button monster4;
    /**
     * Ein Button für das jeweilige Monster.
     */
    private Button monster5;
    /**
     * Ein Button für das jeweilige Monster.
     */
    private Button monster6;
    /**
     * Ein Button für das jeweilige Monster.
     */
    private Button monster7;
    /**
     * Ein Button für das jeweilige Monster.
     */
    private Button monster8;
    /**
     * Ein Button für das jeweilige Monster.
     */
    private Button monster9;
    /**
     * Ein Button für das jeweilige Monster.
     */
    private Button monster10;
    /**
     * Eine ArrayList mit allen Buttons.
     */
    private ArrayList<Button> buttons;
    /**
     * ImageView, die das Bild des ausgewählten Monsters anzeigt.
     */
    private ImageView monsterBild;
    /**
     * Label für den Namen des Monsters.
     */
    private Label monsterName;
    /**
     * Label für das Level des Monsters.
     */
    private Label monsterLevel;
    /**
     * ProgressBar für die Lebenspunkte des Monsters.
     */
    private ProgressBar monsterHP;
    /**
     * Index des momentan ausgewählten Monsters.
     */
    private int indexMonster;
    
    
    /**
     * Initialisiert alle nötigen Objekte, setzt das Menü zusammen, fügt dem Grid
     * alle grafischen Objekte hinzu und übergibt der Stage alles als Scene.
     */
    public MonsterView(){
        
        this.stage = new Stage();
        
        menueBar = new MenuBar();
        datei = new Menu("Datei");
        menueItem = new MenuItem("Beenden");
        zurueck = new MenuItem("Zurück");
        menueBar.getMenus().addAll(datei);
        datei.getItems().addAll(zurueck, menueItem);
        indexMonster = 0;
        
        pane = new Pane();
        
        this.monster1 = new Button();
        this.monster1.setText(LEERER_SLOT);
        this.monster1.setLayoutX(POS_X_MONSTER_1);
        this.monster1.setLayoutY(POS_Y_MONSTER_1);
        pane.getChildren().add(monster1);
        
        this.monster2 = new Button();
        this.monster2.setText(LEERER_SLOT);
        this.monster2.setLayoutX(POS_X_MONSTER_2);
        this.monster2.setLayoutY(POS_Y_MONSTER_2);
        pane.getChildren().add(monster2);
        
        this.monster3 = new Button();
        this.monster3.setText(LEERER_SLOT);
        this.monster3.setLayoutX(POS_X_MONSTER_3);
        this.monster3.setLayoutY(POS_Y_MONSTER_3);
        pane.getChildren().add(monster3);
        
        this.monster4 = new Button();
        this.monster4.setText(LEERER_SLOT);
        this.monster4.setLayoutX(POS_X_MONSTER_4);
        this.monster4.setLayoutY(POS_Y_MONSTER_4);
        pane.getChildren().add(monster4);
        
        this.monster5 = new Button();
        this.monster5.setText(LEERER_SLOT);
        this.monster5.setLayoutX(POS_X_MONSTER_5);
        this.monster5.setLayoutY(POS_Y_MONSTER_5);
        pane.getChildren().add(monster5);
        
        this.monster6 = new Button();
        this.monster6.setText(LEERER_SLOT);
        this.monster6.setLayoutX(POS_X_MONSTER_6);
        this.monster6.setLayoutY(POS_Y_MONSTER_6);
        pane.getChildren().add(monster6);
        
        this.monster7 = new Button();
        this.monster7.setText(LEERER_SLOT);
        this.monster7.setLayoutX(POS_X_MONSTER_7);
        this.monster7.setLayoutY(POS_Y_MONSTER_7);
        pane.getChildren().add(monster7);
        
        this.monster8 = new Button();
        this.monster8.setText(LEERER_SLOT);
        this.monster8.setLayoutX(POS_X_MONSTER_8);
        this.monster8.setLayoutY(POS_Y_MONSTER_8);
        pane.getChildren().add(monster8);
        
        this.monster9 = new Button();
        this.monster9.setText(LEERER_SLOT);
        this.monster9.setLayoutX(POS_X_MONSTER_9);
        this.monster9.setLayoutY(POS_Y_MONSTER_9);
        pane.getChildren().add(monster9);
        
        this.monster10 = new Button();
        this.monster10.setText(LEERER_SLOT);
        this.monster10.setLayoutX(POS_X_MONSTER_10);
        this.monster10.setLayoutY(POS_Y_MONSTER_10);
        pane.getChildren().add(monster10);
        
        monsterBild = new ImageView();
        monsterBild.setLayoutX(POS_X_MONSTER_BILD);
        monsterBild.setLayoutY(POS_Y_MONSTER_BILD);
        pane.getChildren().add(monsterBild);
        
        monsterName = new Label();
        monsterName.setFont(new Font(FONT_NAME, FONT_SIZE));
        monsterName.setLayoutX(POS_X_MONSTER_NAME);
        monsterName.setLayoutY(POS_Y_MONSTER_NAME);
        pane.getChildren().add(monsterName);
        
        monsterLevel = new Label();
        monsterLevel.setFont(new Font(FONT_NAME, FONT_SIZE));
        monsterLevel.setLayoutX(POS_X_MONSTER_LEVEL);
        monsterLevel.setLayoutY(POS_Y_MONSTER_LEVEL);
        pane.getChildren().add(monsterLevel);
        
        monsterHP = new ProgressBar();
        monsterHP.setLayoutX(POS_X_MONSTER_HP);
        monsterHP.setLayoutY(POS_Y_MONSTER_HP);
        pane.getChildren().add(monsterHP);
        
        this.buttons = new ArrayList<Button>();
        buttons.add(monster1);
        buttons.add(monster2);
        buttons.add(monster3);
        buttons.add(monster4);
        buttons.add(monster5);
        buttons.add(monster6);
        buttons.add(monster7);
        buttons.add(monster8);
        buttons.add(monster9);
        buttons.add(monster10);
        
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
    
    
    public final MenuItem getMenueItem() {
        return menueItem;
    }
    
    public final MenuItem getZurueck() {
        return zurueck;
    }

    public Button getMonster1() {
        return monster1;
    }

    public Button getMonster2() {
        return monster2;
    }

    public Button getMonster3() {
        return monster3;
    }

    public Button getMonster4() {
        return monster4;
    }

    public Button getMonster5() {
        return monster5;
    }

    public Button getMonster6() {
        return monster6;
    }

    public Button getMonster7() {
        return monster7;
    }

    public Button getMonster8() {
        return monster8;
    }

    public Button getMonster9() {
        return monster9;
    }

    public Button getMonster10() {
        return monster10;
    }

    public int getIndexMonster() {
        return indexMonster;
    }

    public void setIndexMonster(int indexMonster) {
        this.indexMonster = indexMonster;
    }
    
    
    
    @Override
    public void update(Observable o, Object arg) {   
        Avatar avatar = (Avatar) o;
        monsterBild.setImage(new Image("file:Bilder/" +
                avatar.getMonsterBeutel().get(indexMonster).getNameMonster() + ".png"));
        monsterName.setText(
                avatar.getMonsterBeutel().get(indexMonster).getNameMonster().toUpperCase());
        monsterLevel.setText("LEVEL " + avatar.getMonsterBeutel().get(indexMonster).getLevel());
        monsterHP.setProgress(((double) avatar.getMonsterBeutel().
                get(indexMonster).getLebensPunkte())/Kampfsystem.MAX_HP);
        for(int i=0; i<avatar.getMonsterBeutel().size(); i++){
            this.buttons.get(i).setText(avatar.getMonsterBeutel().get(i).getNameMonster());
        }   
    }
            
}

package control;

import java.awt.event.KeyListener;
import java.io.Serializable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Avatar;
import model.Monster;
import model.MonsterListe;
import view.GUI;
import model.Spielfeld;
import model.Spielstand;
import view.CharacterView;
import view.ItemView;
import view.MonsterView;
import view.KampfView;

/**
 * Startet die GUI und Game, steuert den EventHandler.
 *
 * @author Agirman
 */
public class SpielSteuerung implements EventHandler, Serializable {

    /**
     * Variable für GUI.
     */
    private GUI gui;
    /**
     * Variable für Avatar.
     */
    private Avatar avatar;
    /**
     * Variable für MonsterListe.
     */
    private MonsterListe monsterliste;
    /**
     * Variable für das Spielfeld.
     */
    private Spielfeld spielfeld;
    /**
     * Variable für ItemViewSteuerung.
     */
    private ItemViewSteuerung itemViewSteuerung;
    /**
     * Variable für ItemView.
     */
    private ItemView itemView;
    /**
     * Variable für MonsterViewSteuerung.
     */
    private MonsterViewSteuerung monsterViewSteuerung;
    /**
     * Variable für MonsterView.
     */
    private MonsterView monsterView;
    /**
     * Variable für KampfView.
     */
    private KampfView kampfView;
    /**
     * Variable für KampfSteuerung.
     */
    private KampfSteuerung kampfSteuerung;
    /**
     * Variable für CharacterView.
     */
    private CharacterView charView;
    /**
     * Variable für KampfSteuerung.
     */
    private CharacterViewSteuerung charViewSteuerung;
    /**
     * Variable für Spielstand.
     */
    private Spielstand spielstand;

    
    

    /**
     * Startet die GUI + startet das Model + fuegt dem Model die Gui als
     * Observer hinzu und initGame fuer den Anfangszustand des Spiels wird
     * aufgerufen.
     *
     * @param primaryStage Stage
     * @throws Exception Wirft allgemeine Exception.
     *
    @Override
    /*public void start(final Stage primaryStage) throws Exception {
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });*/
    /**
     * Konstruktor.
     */
    public SpielSteuerung() {
    
        this.gui = new GUI(this);

        this.monsterliste = new MonsterListe();

        Monster startMonster = this.monsterliste.getZufaelligesMonster();
        this.avatar = new Avatar("Bob", startMonster);
        this.monsterliste.monsterEntfernen(startMonster);

        this.spielfeld = new Spielfeld(this.avatar);
        this.spielfeld.addObserver(this.gui);
        this.spielfeld.notifyObservers();

        this.itemView = new ItemView();
        this.itemViewSteuerung = new ItemViewSteuerung(this.avatar, this.itemView, this.gui);

        this.monsterView = new MonsterView();
        this.monsterViewSteuerung = new MonsterViewSteuerung(
                this.avatar, this.monsterView, this.gui);

        this.kampfView = new KampfView(this.avatar);
        this.kampfSteuerung = new KampfSteuerung(
                this.avatar, this.kampfView, this.gui, this.monsterliste);
        
        this.charView = new CharacterView();
        this.charViewSteuerung = new CharacterViewSteuerung(this.avatar, this.charView, this.gui);

        this.spielstand = new Spielstand(this.avatar, this.monsterliste, this.spielfeld);

    }

    /**
     * Initialisiert die SpielSteuerung mit aus einem Spielstand geladenem Spielfeld,
 Avatar und Monsterliste.
     */
    public void mitGeladenemSpielstandInitialisieren() {
        
        this.spielstand = Spielstand.spielstandLaden();
            if (this.spielstand != null) {
                this.monsterliste = this.spielstand.getMonsterliste();
                this.avatar = this.spielstand.getAvatar();
                this.spielfeld = this.spielstand.getSpielfeld();
            
                this.spielfeld.setAufKampfFeld(false);
                this.spielfeld.addObserver(this.gui);
                this.itemView = new ItemView();
                this.itemViewSteuerung = new ItemViewSteuerung(this.avatar, this.itemView, this.gui);
                this.monsterView = new MonsterView();
                this.monsterViewSteuerung = new MonsterViewSteuerung(
                        this.avatar, this.monsterView, this.gui);
                this.kampfView = new KampfView(this.avatar);
                this.kampfSteuerung = new KampfSteuerung(
                        this.avatar, this.kampfView, this.gui, this.monsterliste);
                this.gui.update(this.spielfeld, null);
        }else{
                System.out.println("Du hast noch keinen Spielstand!");
        }
    }

    @Override
    public void handle(Event event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            KeyEvent ke = (KeyEvent) event;
            if (ke.getCode() == KeyCode.UP) {
                spielfeld.nachOben();
            } else if (ke.getCode() == KeyCode.DOWN) {
                spielfeld.nachUnten();
            } else if (ke.getCode() == KeyCode.RIGHT) {
                spielfeld.nachRechts();
            } else if (ke.getCode() == KeyCode.LEFT) {
                spielfeld.nachLinks();
            }
        }
        if (event.getSource() == gui.getInventar()) {
            this.itemView.show(this.gui.getPrimaryStage());
        }
        if (event.getSource() == gui.getMonster()) {
            this.monsterView.show(this.gui.getPrimaryStage());
        }
        if (event.getSource() == gui.getKampfTest()) {
            this.kampfSteuerung.kampfBeginn();
            this.kampfView.show(this.gui.getPrimaryStage());
        }
        if(event.getSource() == gui.getChar()){
            this.charView.show(this.gui.getPrimaryStage());
        }
        if (event.getSource() == this.gui.getBeenden()) {
            System.exit(0);
        }
        if (event.getSource() == this.gui.getSpeichern()) {
            System.out.println("Speichern!");
            this.spielstand.speichern(spielstand);
        }
        if (event.getSource() == this.gui.getLaden()) {
            
                mitGeladenemSpielstandInitialisieren();
            
        }
    }

    public GUI getGui() {
        return gui;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public MonsterListe getMonsterliste() {
        return monsterliste;
    }

    public void setMonsterliste(MonsterListe monsterliste) {
        this.monsterliste = monsterliste;
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }

    public void setSpielfeld(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }

    public ItemViewSteuerung getItemViewSteuerung() {
        return itemViewSteuerung;
    }

    public void setItemViewSteuerung(ItemViewSteuerung itemViewSteuerung) {
        this.itemViewSteuerung = itemViewSteuerung;
    }

    public ItemView getItemView() {
        return itemView;
    }

    public void setItemView(ItemView itemView) {
        this.itemView = itemView;
    }

    public MonsterViewSteuerung getMonsterViewSteuerung() {
        return monsterViewSteuerung;
    }

    public void setMonsterViewSteuerung(MonsterViewSteuerung monsterViewSteuerung) {
        this.monsterViewSteuerung = monsterViewSteuerung;
    }

    public MonsterView getMonsterView() {
        return monsterView;
    }

    public void setMonsterView(MonsterView monsterView) {
        this.monsterView = monsterView;
    }

    public KampfView getKampfView() {
        return kampfView;
    }

    public void setKampfView(KampfView kampfView) {
        this.kampfView = kampfView;
    }

    public KampfSteuerung getKampfSteuerung() {
        return kampfSteuerung;
    }

    public void setKampfSteuerung(KampfSteuerung kampfSteuerung) {
        this.kampfSteuerung = kampfSteuerung;
    }

    public Spielstand getSpielstand() {
        return spielstand;
    }

    public void setSpielstand(Spielstand spielstand) {
        this.spielstand = spielstand;
    }
    
}
package control;

import java.io.Serializable;
import javafx.event.Event;
import javafx.event.EventHandler;
import model.Avatar;
import model.Kampfsystem;
import model.MonsterListe;
import view.GUI;
import view.KampfView;

/**
 * Koordiniert das Kampfsystem mit der KampfView.
 * @author fabiankaupmann
 */
public class KampfSteuerung implements EventHandler, Serializable {
    
    /**
     * Magic number für die maximalen Lebenspunkte.
     */
    private static final int MAX_LEBENSPUNKTE = 100;
    /**
     * String "Angriff".
     */
    private static final String ANGRIFF = "Angriff";
    /**
     * Die Standard-Gui.
     */
    private GUI gui;
    /**
     * Der Avatar.
     */
    private Avatar avatar;
    /**
     * Die GUI, die für die Kampf-Darstellung zuständig ist.
     */
    private KampfView kampfView;
    /**
     * Das Kampfsystem, dass die Logik hinter dem Kampf berechnet.
     */
    private Kampfsystem kampfsystem;
    /**
     * Die MonsterListe, die alle Monster der Welt enthält.
     */
    private MonsterListe monsterListe;
    
    /**
     * Initialisiert alle für den Kampf nötigen Objekte. 
     * Fügt außerdem den Buttons der KampfView Actions hinzu.
     * @param avatar Der Avatar
     * @param kampfView Die GUI für den Kampf
     * @param gui Die standard-GUI
     * @param monsterListe Die MonsterListe
     */
    public KampfSteuerung(Avatar avatar, KampfView kampfView, GUI gui, MonsterListe monsterListe){
        
        this.avatar = avatar;
        this.monsterListe = monsterListe;
        this.kampfView = kampfView;
        this.kampfView.getBeenden().setOnAction(this);
        this.kampfView.getAngriffButton().setOnAction(this);
        this.kampfView.getItemButton().setOnAction(this);
        this.kampfView.getFliehenButton().setOnAction(this);
        this.kampfView.getAttacke1Button().setOnAction(this);
        this.kampfView.getAttacke2Button().setOnAction(this);
        this.kampfView.getAttacke3Button().setOnAction(this);
        this.kampfView.getAttacke4Button().setOnAction(this);
        this.kampfView.getZurueck().setOnAction(this);
        this.kampfView.getKleinerHeiltrank().setOnAction(this);
        this.kampfView.getGrosserHeiltrank().setOnAction(this);
        this.kampfView.getPokeball().setOnAction(this);
        this.kampfsystem = new Kampfsystem(this, avatar);
        this.kampfsystem.addObserver(this.kampfView);
        this.gui = gui;
        
    }

    /**
     * Startet einen neuen Kampf.
     * Setzt dafür spielerMonster und gegnerMonster des Kampfsystems neu.
     */
    public void kampfBeginn(){
        
            this.kampfsystem.setSpielerMonster(this.avatar.getMonsterBeutel().get(0));
            this.kampfsystem.setGegnerMonster(this.monsterListe.getZufaelligesMonster());
            this.kampfsystem.updateObserver();
            System.out.println("Kampf gestartet!");
            
    }
    /**
     * Beendet einen Kampf.
     * Fügt evtl ein gefangenes Monster dem Monsterbeutel des Avatars hinzu
     * und entfernt dieses dann aus dem Rest der Welt. 
     * Ruft wieder die standard-GUI auf.
     */
    public void kampfEnde(){
        if(this.kampfsystem.isGefangen()){
            this.avatar.monsterHinzufuegen(this.kampfsystem.getGegnerMonster());
            this.monsterListe.monsterEntfernen(this.kampfsystem.getGegnerMonster());
        } 
        else if(!this.kampfsystem.isGefangen()){
            this.kampfsystem.getGegnerMonster().setLebensPunkte(MAX_LEBENSPUNKTE);
        }
        this.gui.show(this.gui.getPrimaryStage());
        this.avatar.informiereObserver();
        this.kampfsystem.updateObserver();
        this.kampfsystem.nachKampfAufrauemen();
        
    }
    
    @Override
    public void handle(Event event) {
        
        if (event.getSource() == this.kampfView.getBeenden()) {
            System.exit(0);
        }
        if((event.getSource() == this.kampfView.getAngriffButton())){
            if("".equals(kampfsystem.getKampfPhase())){
                if (kampfsystem.getStatusSpieler() == Kampfsystem.SCHLAF
                        || kampfsystem.getStatusSpieler() == Kampfsystem.VERGIFTET_SCHLAF
                        || kampfsystem.getStatusSpieler() == Kampfsystem.SCHLAF_PARALYSE
                        || kampfsystem.getStatusSpieler()==Kampfsystem.SCHLAF_PARALYSE_VERGIFTET) {
                    kampfsystem.setKampfPhase(ANGRIFF);
                    this.kampfsystem.setAngriffsstaerke(
                           (int) this.kampfsystem.getSpielerMonster().getWertAttacke1());
                    this.kampfsystem.rundeSpieler();
                    kampfsystem.updateObserver();
                    this.kampfsystem.updateObserver();
                }
                else {
                    kampfsystem.setKampfPhase(ANGRIFF);
                    kampfsystem.updateObserver();
                }
            }
        }
        if(event.getSource() == this.kampfView.getItemButton()){
            if("".equals(kampfsystem.getKampfPhase())){
                this.kampfsystem.setKampfPhase("Item");
                kampfsystem.updateObserver();
//                this.kampfsystem.rundeSpieler(); 
//                if(this.kampfsystem.isGefangen()){
//                    this.kampfsystem.updateObserver();
//                } else {
//                    this.kampfsystem.setKampfPhase("");
//                    this.kampfsystem.updateObserver();
//                }
            }
        }
        
        if(event.getSource() == this.kampfView.getAttacke1Button()){
            this.kampfsystem.setAngriffsstaerke(
                   (int) this.kampfsystem.getSpielerMonster().getWertAttacke1());
            this.kampfsystem.rundeSpieler();
            kampfsystem.updateObserver();
            this.kampfsystem.updateObserver();
        }
        
        if(event.getSource() == this.kampfView.getAttacke2Button()){
            this.kampfsystem.setAngriffsstaerke(
                  (int)  this.kampfsystem.getSpielerMonster().getWertAttacke2());
             
            this.kampfsystem.rundeSpieler();
            this.kampfsystem.updateObserver();
        }
        
        if(event.getSource() == this.kampfView.getAttacke3Button()){
            this.kampfsystem.statusVeraenderung(kampfsystem.getSpielerMonster().getNameAttacke3(),
                    true);
              this.kampfsystem.updateObserver();
        }
        if(event.getSource() == this.kampfView.getAttacke4Button()){
            this.kampfsystem.statusVeraenderung(kampfsystem.getSpielerMonster().getNameAttacke4(),
                    true);
              this.kampfsystem.updateObserver();
        
        }
        if (event.getSource() == this.kampfView.getKleinerHeiltrank()) {
            this.kampfsystem.setGewaehltesItem("Kleiner Heiltrank");
            this.kampfsystem.rundeSpieler();
            this.kampfsystem.updateObserver();
        }
        if (event.getSource() == this.kampfView.getGrosserHeiltrank()) {
            this.kampfsystem.setGewaehltesItem("Großer Heiltrank");
            this.kampfsystem.rundeSpieler();
            this.kampfsystem.updateObserver();
        }
        if (event.getSource() == this.kampfView.getPokeball()) {
            this.kampfsystem.setGewaehltesItem("Pokeball");
            this.kampfsystem.rundeSpieler();
            this.kampfsystem.updateObserver();
//            this.kampfsystem.setKampfPhase("");
//            this.kampfsystem.updateObserver();
        }
        if(event.getSource() == this.kampfView.getFliehenButton()){
            this.kampfEnde();
        }
        if (event.getSource() == this.kampfView.getZurueck()) {
            this.kampfsystem.setKampfPhase("");
            this.kampfsystem.updateObserver();
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

    public KampfView getKampfView() {
        return kampfView;
    }

    public void setKampfView(KampfView kampfView) {
        this.kampfView = kampfView;
    }

    public Kampfsystem getKampfsystem() {
        return kampfsystem;
    }

    public void setKampfsystem(Kampfsystem kampfsystem) {
        this.kampfsystem = kampfsystem;
    }

    public MonsterListe getMonsterListe() {
        return monsterListe;
    }

    public void setMonsterListe(MonsterListe monsterListe) {
        this.monsterListe = monsterListe;
    }



   
}

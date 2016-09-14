package model;

import control.KampfSteuerung;
import java.io.Serializable;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 * Berechnet die Logik hinter einem Kampf.
 * @author fabiankaupmann
 */
public class Kampfsystem extends Observable implements Serializable{
    
    /**
     * Maximale Lebenspunkte eines Monsters.
     */
    public static final int MAX_HP = 100;
    /**
      * Konstante für den Zustand des Pokemon.
      */
     public static final int KEINE_EFFEKTE = 1;
     /**
      * Konstante für den Zustand des Pokemon.
      */
     public static final int VERGIFTET = 2;
     /**
      * Konstante für den Zustand des Pokemon.
      */
     public static final int SCHLAF = 3;
     /**
      * Konstante für den Zustand des Pokemon.
      */
     public static final int PARALYSE = 4;
     /**
      * Konstante für den Zustand des Pokemon.
      */
     public static final int VERGIFTET_SCHLAF=5;
     /**
      * Konstante für den Zustand des Pokemon.
      */
     public static final int VERGIFTET_PARALYSE = 6;
     /**
      * Konstante für den Zustand des Pokemon.
      */
     public static final int SCHLAF_PARALYSE = 7;
     /**
      * Konstante für den Zustand des Pokemon.
      */
     public static final int SCHLAF_PARALYSE_VERGIFTET = 8;
     /**
     * Verzögerung, bevor die Attacke des Gegner-Monsters ausgeführt wird.
     */
    private static final int VERZOEGERUNG = 1500;
    /**
     * Magic Number, die für die zufällige Auswahl der
     * gegnerischen Attacke verwendet wird.
     */
    private static final int ZUFAELLIGE_AUSWAHL_ATTACKE = 3;
    /**
     * Magic number für die Schwelle, aber der ein Monster gefangen wird.
     */
    private static final int FANG_SCHWELLE = 30;
    /**
     * Ein kleiner Heiltrank erhöht die Lebenspunkte des Spieler-Monsters um diesen Wert.
     */
    private static final int WIRKUNG_KLEINER_HEILTRANK = 30;
    /**
     * Ein großer Heiltrank erhöht die Lebenspunkte des Spieler-Monsters um diesen Wert.
     */
    private static final int WIRKUNG_GROSSER_HEILTRANK = 30;
    /**
     * String "Fangen".
     */
    private static final String FANGEN = "Fangen";
    /**
     * Das Monster des Spielers.
     */
    private Monster spielerMonster; 
    /**
     * Das gegnerische Monster.
     */
    private Monster gegnerMonster;
     /**
      * Bei true ist der Kampf vorbei.
      */
     private boolean kampfVorbei;
     /**
      * Boolean der angibt ob ein Pokemon gefangen wurde.
      */
     private boolean gefangen;
     /**
      * Die KampfSteuerung.
      */
     private KampfSteuerung kampfSteuerung;
     /**
      * Ein Random, der zuständig für das Fangen ist.
      */
     private Random random;
     /**
      * String der aussagt ob ein Angriff oder ein Item verwendet wird bzw noch
      * keins von beiden gewählt wurde.
      */
     private String kampfPhase;
     /**
      * int welchen Status das Spieler Monster hat;
      * 1 bedeutet es hat keine negativen Statuseffekte
      * 2 bedeutet es ist vergiftet.
      * 3 bedeutet es schläft
      * 4 bedeutet es ist paralysiert
      * 5 beudetet es ist vergiftet und schläft
      * 6 bedeutet es ist vergiftet und paralysiert
      * 7 bedeutet es ist am schlafen und paralysiert
      * 8 bedeutet es ist am schlafen paralysiert und vergiftet.
      */
     private int statusSpieler;
     /**
      * Int welchen Status das Gegnermonster hat.
      * 1 bedeutet es hat keine negativen Statuseffekte
      * 2 bedeutet es ist vergiftet.
      * 3 bedeutet es schläft
      * 4 bedeutet es ist paralysiert
      * 5 beudetet es ist vergiftet und schläft
      * 6 bedeutet es ist vergiftet und paralysiert
      * 7 bedeutet es ist am schlafen und paralysiert
      * 8 bedeutet es ist am schlafen paralysiert und vergiftet.
      */
     private int statusGegner;
     /**
      * Variable der Zahl zehn.
      */
     private final int zehn =10;
     
     /**
      * Int wie stark Gift ist.
      */
     private final int gift=8;
     /**
      * Int der angibt wielange Gift,Schlaf wirkt.
      */
     private int dauerSpieler;
     /**
      * Int der angibt wielange das Gift,Schlaf noch wirkt.
      */
     private int dauerGegner;
     /**
      * Int der angibt wielange der Schlaf wirkt.
      */
     private int schlafSpieler;
     /**
      * Int der angibt wielange der Schlaf noch wirkt.
      */
     private int schlafGegner;
     /**
      * Int der angibt wielange die paralyse wirkt.
      */
     private int paralyseSpieler;
     /**
      * Int der angibt wielange die Paralyse noch wirkt.
      */
     private int paralyseGegner;
     /**
      * Int wie stark der Angriff ist.
      */
     private int angriffsstaerke;
     /**
      * String, der angibt, welches Item ausgewählt wurde.
      */
     private String gewaehltesItem;
     /**
      * Referenz auf den Avatar.
      */
     private Avatar avatar;
     
     /**
      * Initialisiert alle nötigen Objekte.
      * @param kampfSteuerung Die Kampfsteuerung.
     * @param avatar Der Avatar
      */
    public Kampfsystem(KampfSteuerung kampfSteuerung, Avatar avatar){
        this.avatar = avatar;
        this.kampfSteuerung = kampfSteuerung;
        this.spielerMonster = null;
        this.gegnerMonster = null;
        this.gefangen = false;
        this.random = new Random();
        this.kampfPhase = "";
        this.statusSpieler = 1;
        this.statusGegner =1;
        this.dauerSpieler = VERGIFTET_SCHLAF;
        this.dauerGegner=VERGIFTET_SCHLAF;
        this.schlafSpieler = SCHLAF;
        this.schlafGegner = SCHLAF;
        this.paralyseGegner =PARALYSE;
        this.paralyseSpieler =PARALYSE;
        this.angriffsstaerke=0;
        this.gewaehltesItem = "";
    }
    
    /**
     * Hier wird der Kampf durchgeführt.
     * Wenn angegriffen wird , wird überprüft welche Statuse das Pokemon hat und dementsprechend
     * die Aktion ausgeführt.
     * Falls ein Item zum Einsatz kommt wird der Effekt des items ausgeführt.
     * Falls gefangen wird , wird die Fangchacne ausgerechnet und geschaut ob das fangen erfolgreich
     * war.
     */
    public void rundeSpieler(){
     if(spielerMonster.getLebensPunkte()>0 && gegnerMonster.getLebensPunkte()>0)    
     {
                      
         if("Angriff".equals(kampfPhase)){
             switch(statusSpieler){
                 case KEINE_EFFEKTE:
                     gegnerMonster.setLebensPunkte(gegnerMonster.getLebensPunkte()-angriffsstaerke);
                     hpAbfrage();
                     updateObserver();
                     rundeComputer();                 
                 break;
                 case VERGIFTET:   
                    gift();
                    gegnerMonster.setLebensPunkte(gegnerMonster.getLebensPunkte()-angriffsstaerke);
                    hpAbfrage();
                    updateObserver();
                    rundeComputer();
                    break;
                 case SCHLAF:
                     System.out.println("Das pokemon schläft , Sire!");
                     schlaf();
                     hpAbfrage();
                     updateObserver();
                     rundeComputer();
                     updateObserver();
                    break;
                 case PARALYSE:
                    paralyse();
                    int randomzahl = random.nextInt(zehn);
                    if(randomzahl >VERGIFTET_SCHLAF){
                        gegnerMonster.setLebensPunkte(
                                gegnerMonster.getLebensPunkte()-angriffsstaerke);
                        hpAbfrage();
                        updateObserver();
                        rundeComputer();
                    }
                    else {
                        System.out.println("Du hast gemissed!, Sire ");
                        updateObserver();
                        rundeComputer();   
                    }
                    break;
                 case VERGIFTET_SCHLAF:
                    gift();
                    hpAbfrage();
                    schlaf();
                    System.out.println("Das pokemon schläft, Sire!");
                    break;
                 case VERGIFTET_PARALYSE:
                    gift();
                    hpAbfrage();
                    paralyse();
                    randomzahl = random.nextInt(zehn);
                    if(randomzahl >VERGIFTET_SCHLAF){
                        gegnerMonster.setLebensPunkte(
                                gegnerMonster.getLebensPunkte()-angriffsstaerke);
                        hpAbfrage();
                        updateObserver();
                        rundeComputer();
                    }
                    else {
                        System.out.println("Du hast gemissed !, Sire");
                        updateObserver();
                        rundeComputer();   
                    }
                    break;
                case SCHLAF_PARALYSE:
                    schlaf();
                    paralyse();
                    System.out.println("Du hast gemissed!, Sire");
                    break;
                case SCHLAF_PARALYSE_VERGIFTET:
                    gift();
                    schlaf();
                    paralyse();
                    rundeComputer();
                    break;
                default:
                    break;
             }
         }
         
         if("Item".equals(kampfPhase)) {
             
             if (statusSpieler == VERGIFTET || statusSpieler == VERGIFTET_SCHLAF
                     || statusSpieler == VERGIFTET_PARALYSE
                     || statusSpieler == SCHLAF_PARALYSE_VERGIFTET) {
                 gift();
                 hpAbfrage();
             }
             if (statusSpieler == SCHLAF || statusSpieler == VERGIFTET_SCHLAF
                     || statusSpieler == SCHLAF_PARALYSE
                     || statusSpieler == SCHLAF_PARALYSE_VERGIFTET) {
                 schlaf();
                 hpAbfrage();
             }
             if (statusSpieler == PARALYSE || statusSpieler == VERGIFTET_PARALYSE
                     || statusSpieler == SCHLAF_PARALYSE
                     || statusSpieler == SCHLAF_PARALYSE_VERGIFTET) {
                 paralyse();
                 hpAbfrage();
             }
             
             switch (gewaehltesItem) {
                 case "Kleiner Heiltrank":
                     hpAbfrage();
                     gewaehltesItem = "";
                     avatar.kleinenHeiltrankEntfernen();
                     if (spielerMonster.getLebensPunkte() + WIRKUNG_KLEINER_HEILTRANK <= MAX_HP) {
                         spielerMonster.setLebensPunkte(
                                 spielerMonster.getLebensPunkte() + WIRKUNG_KLEINER_HEILTRANK);
                     }
                     else {
                         spielerMonster.setLebensPunkte(MAX_HP);
                     }
                     hpAbfrage();
                     updateObserver();
                     rundeComputer();
                     break;
                 case "Großer Heiltrank":
                     hpAbfrage();
                     gewaehltesItem = "";
                     avatar.grossenHeiltrankEntfernen();
                     if (spielerMonster.getLebensPunkte() + WIRKUNG_GROSSER_HEILTRANK <= MAX_HP) {
                         spielerMonster.setLebensPunkte(
                                 spielerMonster.getLebensPunkte() + WIRKUNG_GROSSER_HEILTRANK);
                     }
                     else {
                         spielerMonster.setLebensPunkte(MAX_HP);
                     }
                     hpAbfrage();
                     updateObserver();
                     rundeComputer();
                     break;
                 case "Pokeball":
                     hpAbfrage();
                     gewaehltesItem = "";
                     avatar.pokeballEntfernen();
                     kampfPhase = FANGEN;
                     break;
                 default:
                     gewaehltesItem = "";
                     hpAbfrage();
                     updateObserver();
                     rundeComputer();
                     break;
             }
         }
         
         if(FANGEN.equals(kampfPhase)){
             hpAbfrage();
             System.out.println("Fangversuch");
             if(fangErfolg() < FANG_SCHWELLE){
                 System.out.println("Du hast das Pokemon gefangen!");
                 gefangen = true;
                 kampfVorbei = true;
                 updateObserver();
                 kampfSteuerung.kampfEnde();
             }
             else {
                 hpAbfrage();
             System.out.println("Du hast das Pokemon nicht gefangen");
                       updateObserver();
                       rundeComputer(); 
             }
         }
     }
     }
    
    /**
     * Zählt die Anzahl der Runden, die das Spieler-Monster noch paralysiert
     * ist, herunter und resettet diese gegebenenfalls.
     */
    private void paralyse() {
        paralyseSpieler--;
        if(paralyseSpieler==0){
            paralyseReset(true);
        }
    }
    
    /**
     * Zählt die Anzahl der Runden, die das Spieler-Monster noch schläft herunter
     * und resettet diese gegebenenfalls.
     */
    private void schlaf() {
        schlafSpieler--;
        if(schlafSpieler==0){
            schlafReset(true);
        }
    }
    
    /**
     * Berechnet den Giftschaden des Spieler-Monsters, zählt die Anzahl der
     * Runden, die das Monster noch vergiftet ist, herunter und resettet
     * diese gegebenenfalls.
     */
    private void gift() {
        spielerMonster.setLebensPunkte(spielerMonster.getLebensPunkte() -gift);
        dauerSpieler--;
        updateObserver();
        hpAbfrage();
        if(dauerSpieler==0){
            giftReset(true);
            updateObserver();  
        }
    }
    
    /**
     * Ruft nach einer Verzögerung rundeComputer2() auf.
     */
    private void rundeComputer(){
        kampfPhase = "Gegner";
        updateObserver();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        hpAbfrage();
                        rundeComputer2();
                        updateObserver();
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, VERZOEGERUNG);
     }
          
    /**
     * Kampfrunde des Computers.
     * Wird nach einer Verzögerung von rundeComputer aufgerufen.
     */
    public void rundeComputer2() {
        System.out.println("Beginn rundeComputer");
        if(spielerMonster != null &&
                 spielerMonster.getLebensPunkte()>0 && gegnerMonster.getLebensPunkte()>0)    
     {
         switch (statusGegner){
             case KEINE_EFFEKTE:
                 auswahlAttacke();
                 hpAbfrage();
                 System.out.println("Kampf-Phase in rundeComputer: " + kampfPhase);
                 updateObserver();
                 break;
             case VERGIFTET:
                 gegnerMonster.setLebensPunkte(gegnerMonster.getLebensPunkte()-gift);
                 hpAbfrage();
                 dauerGegner--;
                 if(dauerGegner == 0){
                     giftReset(false);
                 }
                 updateObserver();
                 auswahlAttacke();
                 hpAbfrage();
                 updateObserver();
                 break;
             case SCHLAF:
                 schlafGegner--;
                 if(schlafGegner==0){
                     schlafReset(false);
                 }
                 System.out.println("Das Gegnermonster schläft , Sire!");
                 updateObserver();
                 hpAbfrage();
                 break;
             case PARALYSE:
                 paralyseGegner--;
                 if(paralyseGegner==0){
                     paralyseReset(false);
                 }
                 int randomzahl = random.nextInt(zehn);
                 if(randomzahl>VERGIFTET_SCHLAF){
                     hpAbfrage();
                     auswahlAttacke();
                   updateObserver();
                 }
                 else {
                     System.out.println("Das GegnerMonster hat gemissed , Sire!");
                     hpAbfrage();
                 }
                 
             break;
             case VERGIFTET_SCHLAF:
                 gegnerMonster.setLebensPunkte(gegnerMonster.getLebensPunkte()-gift);
                  hpAbfrage();
                 dauerGegner--;
                 if(dauerGegner == 0){
                     giftReset(false);
                 }
                 schlafGegner--;
                 if(schlafGegner==0){
                     schlafReset(false);
                 }
                 updateObserver();
                 System.out.println("Das gegnermonster  schläft , Sire!");
                 break;
             case VERGIFTET_PARALYSE:
                 gegnerMonster.setLebensPunkte(gegnerMonster.getLebensPunkte()-gift);
                  hpAbfrage();
                 dauerGegner--;
                 if(dauerGegner == 0){
                     giftReset(false);
                 }
                 updateObserver();
                  randomzahl = random.nextInt(zehn);
                   if(randomzahl>VERGIFTET_SCHLAF){
                       hpAbfrage();
                       auswahlAttacke();
                   updateObserver();
                 }
                 else {
                       hpAbfrage();
                       System.out.println("Das gegnerMonster hat gemissed , Sire!");
                   }
                 break;
             case SCHLAF_PARALYSE:
                 hpAbfrage();
                 schlafGegner--;
                 if(schlafGegner==0){
                     schlafReset(false);
                 }
                 paralyseGegner--;
                 if(paralyseGegner==0){
                     paralyseReset(false);
                 }
                 System.out.println("Das gegnerMonster hat gemissed und schläft , Sire!");
                 break;
             case SCHLAF_PARALYSE_VERGIFTET:
                  gegnerMonster.setLebensPunkte(gegnerMonster.getLebensPunkte()-gift);
                  hpAbfrage();
                 dauerGegner--;
                 if(dauerGegner == 0){
                     giftReset(false);
                 }
                  schlafGegner--;
                 if(schlafGegner==0){
                     schlafReset(false);
                 }
                 paralyseGegner--;
                 if(paralyseGegner==0){
                     paralyseReset(false);
                 }
             default:
                 break;
         }
     
    }
        hpAbfrage();
     kampfPhase ="";   
    }
    
    /**
     * Hier wird die Giftdauer resettet und gleichzeitig die Statuseffekte der Pokemon geändert.
     * @param runde Sagt aus ob der Spieler oder der Computer dran ist.
     */
    private void giftReset(Boolean runde){
       if(runde) {
           dauerSpieler=VERGIFTET_SCHLAF;
         switch(statusSpieler){
                              case VERGIFTET: 
                                  statusSpieler=1;
                                  break;
                              case VERGIFTET_SCHLAF:
                                  statusSpieler=SCHLAF;
                                  break;
                              case VERGIFTET_PARALYSE :
                                  statusSpieler=PARALYSE;
                                  break;  
                              case SCHLAF_PARALYSE_VERGIFTET:
                                  statusSpieler = SCHLAF_PARALYSE;
                                  break;
                             default:
                              break;
         }  
       }
       else {
           dauerGegner=VERGIFTET_SCHLAF;
         switch(statusGegner){
                              case VERGIFTET: 
                                  statusGegner=1;
                                  break;
                              case VERGIFTET_SCHLAF:
                                  statusGegner=SCHLAF;
                                  break;
                              case VERGIFTET_PARALYSE :
                                  statusGegner=PARALYSE;
                                  break;    
                              case SCHLAF_PARALYSE_VERGIFTET:
                                  statusGegner=SCHLAF_PARALYSE;
                                  break;
               default:
                 break;
         }  
       }
    }
    
   /**
     * Hier wird die Schlafdauer und gleichzeitig die Statuseffekte der Pokemon geändert.
     * Der Schlaf wird entfernt und zu den entsprechenden Statuseffekten zurückgesetzt.
     * @param runde Sagt aus ob der Spieler oder der Computer dran ist.
     */
      private void schlafReset(Boolean runde){
       if(runde) {
           schlafSpieler=SCHLAF;
         switch(statusSpieler){
                              case SCHLAF: 
                                  statusSpieler=1;
                                  break;
                              case VERGIFTET_SCHLAF:
                                  statusSpieler=VERGIFTET;
                                  break;
                              case SCHLAF_PARALYSE :
                                  statusSpieler=PARALYSE;
                                  break;      
                             case SCHLAF_PARALYSE_VERGIFTET:
                                 statusSpieler = VERGIFTET_PARALYSE;
                                 break;
                 default:
                 break;
         }  
       }
       else {
           schlafGegner=SCHLAF;
         switch(statusGegner){
                              case SCHLAF: 
                                  statusGegner=1;
                                  break;
                              case VERGIFTET_SCHLAF:
                                  statusGegner=VERGIFTET;
                                  break;
                              case SCHLAF_PARALYSE:
                                  statusGegner=PARALYSE;
                                  break;  
                              case SCHLAF_PARALYSE_VERGIFTET:
                                  statusGegner =VERGIFTET_PARALYSE;
                                  break;
               default:
                 break;
         }  
       }
    }   
     /**
     * Hier wird die paralysedauer und gleichzeitig die Statuseffekte der Pokemon geändert.
     * Die paralyse wird entfernt und zu den entsprechenden Statuseffekten zurückgesetzt.
     * @param runde Sagt aus ob der Spieler oder der Computer dran ist.
     */
      private void paralyseReset(Boolean runde){
       if(runde) {
           paralyseSpieler=PARALYSE;
         switch(statusSpieler){
                              case PARALYSE: 
                                  statusSpieler=1;
                                  break;
                              case VERGIFTET_PARALYSE:
                                  statusSpieler=VERGIFTET;
                                  break;
                              case SCHLAF_PARALYSE :
                                  statusSpieler=SCHLAF;
                                  break;
                              case SCHLAF_PARALYSE_VERGIFTET:
                                  statusSpieler=VERGIFTET_SCHLAF;
                                  break;
                 default:
                 break;
         }  
       }
       else {
           paralyseGegner=PARALYSE;
         switch(statusGegner){
                              case PARALYSE: 
                                  statusGegner=1;
                                  break;
                              case VERGIFTET_PARALYSE:
                                  statusGegner=VERGIFTET;
                                  break;
                              case SCHLAF_PARALYSE:
                                  statusGegner=SCHLAF;
                                  break;     
                              case SCHLAF_PARALYSE_VERGIFTET:
                                  statusGegner=VERGIFTET_SCHLAF;
               default:
                 break;
         }  
       }
    }   
    
    /**
     * Hp Abfrage , die falls ein pokemon bewusstlos geworden ist dem anderen Ep gibt.
     */
    private void hpAbfrage(){
        if(spielerMonster.getLebensPunkte()<=0){
        gegnerMonster.setErfahrungsPunkte(gegnerMonster.getErfahrungsPunkte() 
                + spielerMonster.getLevel());
           spielerMonster.setLebensPunkte(0);
           kampfVorbei =true;
           updateObserver();
           kampfSteuerung.kampfEnde();
           
        }
        
       if(gegnerMonster != null && gegnerMonster.getLebensPunkte()<=0  ){
           spielerMonster.setErfahrungsPunkte(spielerMonster.getErfahrungsPunkte()
                   +gegnerMonster.getLevel()); 
           gegnerMonster.setLebensPunkte(0);
           kampfVorbei=true;
           updateObserver();
           kampfSteuerung.kampfEnde();
       }
    }
    
    /**
     * Wählt die Attacke für das gegnermonster aus.
     */
    public void auswahlAttacke(){
        if(gegnerMonster!=null){
        int zufall =  random.nextInt(ZUFAELLIGE_AUSWAHL_ATTACKE + 1);
        switch(zufall){
            case 0:
                spielerMonster.setLebensPunkte(spielerMonster.getLebensPunkte()-
                    (int)gegnerMonster.getWertAttacke1());
                break;
            case 1:
                spielerMonster.setLebensPunkte(spielerMonster.getLebensPunkte()-
                    (int)gegnerMonster.getWertAttacke2());
                break;
            case VERGIFTET:
                statusVeraenderung(gegnerMonster.getNameAttacke3(),false);
                break;
            case SCHLAF:
                statusVeraenderung(gegnerMonster.getNameAttacke4(),false);
                break;
            default:
                break;
        
        }
    }
        
    }
    
    /**
     * Berechnet eine Zufallszahl, die bestimmt, ob Fangen erfolgreich ist oder nicht.
     * @return Ein Zufallsint
     */
    private int fangErfolg(){
        
        int chance = this.gegnerMonster.getLebensPunkte() + this.gegnerMonster.getLevel();
        int erfolg = this.random.nextInt(chance);
        return erfolg;
        
    }
    /**
     * Methode wo gesagt wird was passiert wenn Gift/paralyse/schlaf angewendet wird.
     * @param attacke ist der Attacken name
     * @param runde ist ein boolean der angibt ob der Spieler dran ist(true) oder der Gegner (false)
     */
    public void statusVeraenderung(String attacke, Boolean runde){
    switch (attacke)
       {
      
           case "Gift":
             if(runde){
               switch(this.getStatusGegner()){
                   case KEINE_EFFEKTE:
                       setStatusGegner(VERGIFTET);
                       updateObserver();
                       rundeComputer();
                       updateObserver();
                       break;
                   case VERGIFTET:
                       rundeComputer();
                       updateObserver();
                       break;
                   case SCHLAF:
                       setStatusGegner(VERGIFTET_SCHLAF);
                       updateObserver();
                       rundeComputer();
                       updateObserver();
                       break;
                   case PARALYSE:
                       setStatusGegner(VERGIFTET_PARALYSE);
                       updateObserver();
                       rundeComputer();
                       updateObserver();
                       break;
                   case VERGIFTET_SCHLAF:
                       rundeComputer();
                       updateObserver();
                       break;
                   case VERGIFTET_PARALYSE:
                       rundeComputer();
                       updateObserver();
                       break;
                   case SCHLAF_PARALYSE:
                       setStatusGegner(SCHLAF_PARALYSE_VERGIFTET);
                       updateObserver();
                       break;
                   case SCHLAF_PARALYSE_VERGIFTET:
                       rundeComputer();
                       updateObserver();
                       break;     
                   default:
                       break;
                           
               }
             }
             else
             {
             switch(this.getStatusSpieler()){
                   case KEINE_EFFEKTE:
                       setStatusSpieler(VERGIFTET);
                       updateObserver();
                       break;
                   case VERGIFTET:
                       updateObserver();
                       break;
                   case SCHLAF:
                       setStatusSpieler(VERGIFTET_SCHLAF);
                       updateObserver();

                       break;
                   case PARALYSE:
                       setStatusSpieler(VERGIFTET_PARALYSE);
                       updateObserver();
                       break;
                   case VERGIFTET_SCHLAF:
                     
                       updateObserver();
                       break;
                   case VERGIFTET_PARALYSE:
                     
                       updateObserver();
                       break;
                   case SCHLAF_PARALYSE:
                       setStatusSpieler(SCHLAF_PARALYSE_VERGIFTET);
                       updateObserver();
                       break;
                   case SCHLAF_PARALYSE_VERGIFTET:
                       updateObserver();
                       break;     
                   default:
                       break;
                           
               }
             }
           break;
             
           case "Schlaf":
             if(runde){
             switch(getStatusGegner()){
                  case 1:
                       setStatusGegner(SCHLAF);
                       updateObserver();
                       rundeComputer();
                       updateObserver();
                       break;
                   case VERGIFTET:
                        setStatusGegner(VERGIFTET_SCHLAF);
                        updateObserver();
                        rundeComputer();
                       updateObserver();
                       break;
                   case SCHLAF:
                       rundeComputer();
                       updateObserver();
                       break;
                   case PARALYSE:
                       setStatusGegner(SCHLAF_PARALYSE);
                       updateObserver();
                       rundeComputer();
                       updateObserver();
                       break;
                   case VERGIFTET_SCHLAF:
                       rundeComputer();
                       updateObserver();
                       break;
                   case VERGIFTET_PARALYSE:
                       setStatusGegner(SCHLAF_PARALYSE_VERGIFTET);
                       updateObserver();
                       rundeComputer();
                       updateObserver();
                       break;
                   case SCHLAF_PARALYSE:
                       rundeComputer();
                       updateObserver();
                       break;
                   case SCHLAF_PARALYSE_VERGIFTET:
                       rundeComputer();
                       updateObserver();
                       break;     
                   default:
                       break; 
                 
             }
             }
             else {
                  switch(getStatusSpieler()){
                  case 1:
                       setStatusSpieler(SCHLAF);
                       updateObserver();
                       break;
                   case VERGIFTET:
                        setStatusSpieler(VERGIFTET_SCHLAF);
                        updateObserver();
                       break;
                   case SCHLAF:
                       rundeComputer();
                       updateObserver();
                       break;
                   case PARALYSE:
                       setStatusSpieler(SCHLAF_PARALYSE);
                       updateObserver();
                       break;
                   case VERGIFTET_SCHLAF:
                       rundeComputer();
                       updateObserver();
                       break;
                   case VERGIFTET_PARALYSE:
                       setStatusSpieler(SCHLAF_PARALYSE_VERGIFTET);
                       updateObserver();
                       break;
                   case SCHLAF_PARALYSE:
                       updateObserver();
                       break;
                   case SCHLAF_PARALYSE_VERGIFTET:
                       updateObserver();
                       break;     
                   default:
                       break; 
                 
             }
             
             }
           break;
           case "Paralyse": 
               if(runde){
               switch(this.getStatusGegner()){
                  case 1:
                       setStatusGegner(PARALYSE);
                       updateObserver();
                       rundeComputer();
                       updateObserver();
                       break;
                   case VERGIFTET:
                       setStatusGegner(VERGIFTET_PARALYSE);
                       updateObserver();
                       rundeComputer();
                       updateObserver();
                       break;
                   case SCHLAF:
                       setStatusGegner(SCHLAF_PARALYSE);
                       updateObserver();
                       rundeComputer();
                       updateObserver();
                       break;
                   case PARALYSE:
                       rundeComputer();
                       updateObserver();
                       break;
                   case VERGIFTET_SCHLAF:
                       setStatusGegner(SCHLAF_PARALYSE_VERGIFTET);
                       updateObserver();
                       rundeComputer();
                       updateObserver();
                       break;
                   case VERGIFTET_PARALYSE:
                       rundeComputer();
                       updateObserver();
                       break;
                   case SCHLAF_PARALYSE:
                       rundeComputer();
                       updateObserver();
                       break;
                   case SCHLAF_PARALYSE_VERGIFTET:
                       rundeComputer();
                       updateObserver();
                       break;     
                   default:
                       break; 
                 
             }
               }
               else {
               
               switch(this.getStatusSpieler()){
                  case 1:
                       setStatusSpieler(PARALYSE);
                       updateObserver();
                       break;
                   case VERGIFTET:
                       setStatusSpieler(VERGIFTET_PARALYSE);
                       updateObserver();
                       break;
                   case SCHLAF:
                       setStatusSpieler(SCHLAF_PARALYSE);
                       updateObserver();
                       break;
                   case PARALYSE:
                       updateObserver();
                       break;
                   case VERGIFTET_SCHLAF:
                       setStatusSpieler(SCHLAF_PARALYSE_VERGIFTET);
                       updateObserver();
                       break;
                   case VERGIFTET_PARALYSE:
                       updateObserver();
                       break;
                   case SCHLAF_PARALYSE:
                       updateObserver();
                       break;
                   case SCHLAF_PARALYSE_VERGIFTET:
                       updateObserver();
                       break;     
                   default:
                       break; 
                 
             }
               }
               break;
           default:
               break;
                
       
    }
    }
    
    /**
     * Setzt alle Werte zurück, sodass ein neuer Kampf gestartet werden kann.
     */
    public void nachKampfAufrauemen(){
        this.spielerMonster = null;
        this.gegnerMonster = null;
        this.kampfVorbei = false;
        this.gefangen =false;
        this.kampfPhase="";
        this.statusGegner=1;
        this.statusSpieler=1;

    }


    public int getVERGIFTET() {
        return VERGIFTET;
    }

    public int getSCHLAF() {
        return SCHLAF;
    }

    public int getPARALYSE() {
        return PARALYSE;
    }

    public int getZehn() {
        return zehn;
    }

    public int getGift() {
        return gift;
    }

    public int getSchlafSpieler() {
        return schlafSpieler;
    }

    public int getSchlafGegner() {
        return schlafGegner;
    }

    public int getParalyseSpieler() {
        return paralyseSpieler;
    }

    public int getParalyseGegner() {
        return paralyseGegner;
    }
    
    public Monster getSpielerMonster() {
        return spielerMonster;
    }

    public void setSpielerMonster(Monster spielerMonster) {
        this.spielerMonster = spielerMonster;
    }

    public Monster getGegnerMonster() {
        return gegnerMonster;
    }

    public void setGegnerMonster(Monster gegnerMonster) {
        this.gegnerMonster = gegnerMonster;
    }

    public boolean isKampfVorbei() {
        return kampfVorbei;
    }

    public void setKampfVorbei(boolean kampfVorbei) {
        this.kampfVorbei = kampfVorbei;
    }

    public boolean isGefangen() {
        return gefangen;
    }

    public void setGefangen(boolean gefangen) {
        this.gefangen = gefangen;
    }

    public KampfSteuerung getKampfSteuerung() {
        return kampfSteuerung;
    }

    public void setKampfSteuerung(KampfSteuerung kampfSteuerung) {
        this.kampfSteuerung = kampfSteuerung;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public String getKampfPhase() {
        return kampfPhase;
    }

    public void setKampfPhase(String kampfPhase) {
        this.kampfPhase = kampfPhase;
    }

    public int getStatusSpieler() {
        return statusSpieler;
    }

    public void setStatusSpieler(int statusSpieler) {
        this.statusSpieler = statusSpieler;
    }

    public int getStatusGegner() {
        return statusGegner;
    }

    public void setStatusGegner(int statusGegner) {
        this.statusGegner = statusGegner;
    }

    public int getDauerSpieler() {
        return dauerSpieler;
    }

    public void setDauerSpieler(int dauerSpieler) {
        this.dauerSpieler = dauerSpieler;
    }

    public void setGewaehltesItem(String gewaehltesItem) {
        this.gewaehltesItem = gewaehltesItem;
    }

    public int getDauerGegner() {
        return dauerGegner;
    }

    public void setDauerGegner(int dauerGegner) {
        this.dauerGegner = dauerGegner;
    }

    public int getAngriffsstaerke() {
        return angriffsstaerke;
    }

    public void setAngriffsstaerke(int angriffsstaerke) {
        this.angriffsstaerke = angriffsstaerke;
    }

    public static int getMAXHP() {
        return MAX_HP;
    }

    public static int getKEINEEFFEKTE() {
        return KEINE_EFFEKTE;
    }

    public static int getVERGIFTETSCHLAF() {
        return VERGIFTET_SCHLAF;
    }

    public static int getVERGIFTETPARALYSE() {
        return VERGIFTET_PARALYSE;
    }

    public static int getSCHLAFPARALYSE() {
        return SCHLAF_PARALYSE;
    }

    public static int getSCHLAFPARALYSEVERGIFTET() {
        return SCHLAF_PARALYSE_VERGIFTET;
    }

    public static int getVERZOEGERUNG() {
        return VERZOEGERUNG;
    }

    public static int getZUFAELLIGEAUSWAHLATTACKE() {
        return ZUFAELLIGE_AUSWAHL_ATTACKE;
    }

    public static int getFANGSCHWELLE() {
        return FANG_SCHWELLE;
    }

    public static int getWIRKUNGHEILTRANK() {
        return WIRKUNG_KLEINER_HEILTRANK;
    }

    public static String getFANGEN() {
        return FANGEN;
    }

    public String getGewaehltesItem() {
        return gewaehltesItem;
    }

    public Avatar getAvatar() {
        return avatar;
    }
    
   
    /**
     * Vereinfacht das updaten der Observer.
     */
    public void updateObserver(){
        this.setChanged();
        this.notifyObservers();
    }
    
}

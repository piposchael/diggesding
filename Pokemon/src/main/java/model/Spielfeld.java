package model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Random;
import static model.Feld.GRAS;
import static model.Feld.GRAS2;
import static model.Feld.HEILFELD;

/**
 * Modelliert das Spielfeld mit Hilfe der Arrays hintergrund und vordergrund.
 * Außerdem wird die Position des Avatars gespeichert. Die Klasse Spielfeld wird
 * durch die Klasse GUI im Sinne des Observer-Patterns observiert.
 *
 * @author pbraukmann
 */
public class Spielfeld extends Observable implements Serializable {

    /**
     * Konstante für die Breite des Spielfelds in Feldern.
     */
    private static final int BREITE = 14;
    /**
     * Konstante für die Höhe des Spielfelds in Feldern.
     */
    private static final int HOEHE = 14;
    /**
     * Konstante für Startposition X des Avatar.
     */
    private static final int START_POSITIONX = 4;
    /**
     * Konstante für Startposition Y des Avatar.
     */
    private static final int START_POSITIONY = 4;
    /**
     * Wahrscheinlichkeit, dass auf einem Gras-Feld ein Kampf gegen ein wildes
     * Monster gestartet wird.
     */
    private static final double KAMPF_WKEIT = 0.4;
    /**
     * Konstante für Blickrichtung des Avatars.
     */
    private static final int OBEN = 0;
    /**
     * Konstante für Blickrichtung des Avatars.
     */
    private static final int RECHTS = 1;
    /**
     * Konstante für Blickrichtung des Avatars.
     */
    private static final int UNTEN = 2;
    /**
     * Konstante für Blickrichtung des Avatars.
     */
    private static final int LINKS = 3;
    /**
     * Repräsentiert den "Boden" der Spielwelt durch enums vom Typ Feld.
     */
    private Feld[][] hintergrund;
    /**
     * Repräsentiert Objekte, die auf dem Hintergrund der Spielwelt zu sehen
     * sind (Bäume, Pokecenter, etc.) durch enums vom Typ Feld.
     */
    private Feld[][] vordergrund;
    /**
     * Variable für Position X des Avatar.
     */
    private int posX = START_POSITIONX;
    /**
     * Variable für Position Y des Avatar.
     */
    private int posY = START_POSITIONY;
    /**
     * Referenz auf den Avatar, der auf dem Spielfeld platziert ist.
     */
    private Avatar avatar;
    
    private Karte karte;
    /**
     * Ist true, falls der Avatar auf einem Feld mit Kampf-Wkeit steht.
     */
    private boolean aufKampfFeld = false;
    /**
     * Instanz der Klasse Random für die Kampfwahrscheinlichkeit auf
     * Gras-Feldern.
     */
    private Random rand = new Random();
    /**
     * Gibt an, in welche Richtung der Avatar schaut.
     */
    private int blickrichtungAvatar;

    /**
     * Konstruiert ein Spielfeld. Erstellt einen SpielfeldGenerator und
     * initialisiert mit diesem die Arrays hintergrund und vordergrund und
     * markiert das Spielfeld als geändert, damit vom Controller aus
     * notifyObservers() aufgerufen werden kann.
     *
     * @param avatar Referenz auf den Avatar, der auf dem Spielfeld platziert
     * werden soll.
     */
    public Spielfeld(Avatar avatar) {
        this.avatar = avatar;
        do {
        this.karte = new Karte(BREITE, HOEHE);
        //SpielfeldGenerator sg = new SpielfeldGenerator(BREITE, HOEHE);
        //hintergrund = sg.getHintergrund();
        //vordergrund = sg.getVordergrund();
        
        hintergrund = karte.getHintergrund();
        vordergrund = karte.getVordergrund();
        } while (!(hintergrund[posX][posY].isBegehbar() && vordergrund[posX][posY].isBegehbar()));
        setChanged();
        this.blickrichtungAvatar = RECHTS;
    }

    public Feld[][] getHintergrund() {
        return hintergrund;
    }

    public Feld[][] getVordergrund() {
        return vordergrund;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public static int getBREITE() {
        return BREITE;
    }

    public static int getHOEHE() {
        return HOEHE;
    }

    /**
     * Falls das Feld oberhalb vom Avatar begehbar ist, wird dieser um ein Feld
     * nach oben bewegt. Anschließend wird pruefeNeuesFeld() aufgerufen, um zu
     * testen, ob es sich um ein Heil- oder Gras-Feld handelt. Zuletzt werden
     * die Observer über die Änderung benachrichtigt. Falls das Feld oberhalb
     * vom Avatar nicht begehbar ist, geschieht nichts.
     */
    public void nachOben() {
        if( this.blickrichtungAvatar != OBEN){
            this.blickrichtungAvatar = OBEN;
        }else{
            if (posY - 1 >= 0
                    && hintergrund[posX][posY - 1].isBegehbar()
                    && vordergrund[posX][posY - 1].isBegehbar()) {
                this.blickrichtungAvatar = OBEN;
                posY--;
                pruefeNeuesFeld();
            }
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Falls das Feld unterhalb vom Avatar begehbar ist, wird dieser um ein Feld
     * nach unten bewegt. Anschließend wird pruefeNeuesFeld() aufgerufen, um zu
     * testen, ob es sich um ein Heil- oder Gras-Feld handelt. Zuletzt werden
     * die Observer über die Änderung benachrichtigt. Falls das Feld unterhalb
     * vom Avatar nicht begehbar ist, geschieht nichts.
     */
    public void nachUnten() {
        if( this.blickrichtungAvatar != UNTEN){
            this.blickrichtungAvatar = UNTEN;
        }else{
            if (posY + 1 < HOEHE
                    && hintergrund[posX][posY + 1].isBegehbar()
                    && vordergrund[posX][posY + 1].isBegehbar()) {
                this.blickrichtungAvatar = UNTEN;
                posY++;
                pruefeNeuesFeld();
            }
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Falls das Feld rechts vom Avatar begehbar ist, wird dieser um ein Feld
     * nach rechts bewegt. Anschließend wird pruefeNeuesFeld() aufgerufen, um zu
     * testen, ob es sich um ein Heil- oder Gras-Feld handelt. Zuletzt werden
     * die Observer über die Änderung benachrichtigt. Falls das Feld rechts vom
     * Avatar nicht begehbar ist, geschieht nichts.
     */
    public void nachRechts() {
        if( this.blickrichtungAvatar != RECHTS){
            this.blickrichtungAvatar = RECHTS;
        }else{
            if (posX + 1 < BREITE
                    && hintergrund[posX + 1][posY].isBegehbar()
                    && vordergrund[posX + 1][posY].isBegehbar()) {
                this.blickrichtungAvatar = RECHTS;
                posX++;
                pruefeNeuesFeld();
            }
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Falls das Feld links vom Avatar begehbar ist, wird dieser um ein Feld
     * nach links bewegt. Anschließend wird pruefeNeuesFeld() aufgerufen, um zu
     * testen, ob es sich um ein Heil- oder Gras-Feld handelt. Zuletzt werden
     * die Observer über die Änderung benachrichtigt. Falls das Feld links vom
     * Avatar nicht begehbar ist, geschieht nichts.
     */
    public void nachLinks() {
        if( this.blickrichtungAvatar != LINKS){
            this.blickrichtungAvatar = LINKS;
        }else{
            if (posX - 1 >= 0
                    && hintergrund[posX - 1][posY].isBegehbar()
                    && vordergrund[posX - 1][posY].isBegehbar()) {
                this.blickrichtungAvatar = LINKS;
                posX--;
                pruefeNeuesFeld();
            }
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Überprüft ob das Feld, auf dem der Avatar im Moment steht, ein Heilfeld
     * oder ein Gras-Feld ist und führt die entsprechenden Aktionen aus. Im Fall
     * eines Heilfelds wird die Methode heilen() des Avatars aufgerufen. Bei
     * einem Gras-Feld wird mit der Wahrscheinlichkeit KAMPF_WKEIT die Variable
     * aufKampfFeld auf true gesetzt und dadurch ein Kapmpf ausgelöst.
     */
    private void pruefeNeuesFeld() {
        if (vordergrund[posX][posY] == HEILFELD) {
            //this.avatar.heilen();
            
            /*
            * noch statischer Kartenwechsel
            */
            if(this.karte.getKartenName() == "Berlin"){
                this.wechselKarte("Amsterdam");
            }else if(this.karte.getKartenName() == "Amsterdam"){
                this.wechselKarte("Detroit");
            }else{
                this.wechselKarte("Berlin");
            }
        }
        if (hintergrund[posX][posY] == GRAS
                || hintergrund[posX][posY] == GRAS2) {
            if (rand.nextDouble() < KAMPF_WKEIT) {
                //aufKampfFeld = true;
            }
        } else {
            aufKampfFeld = false;
        }
    }
    
    private void wechselKarte(String karte){
        this.karte.setActiveKarte(karte);
        this.vordergrund = this.karte.getVordergrund();
        this.hintergrund = this.karte.getHintergrund();
        
        for(int i = 0; i < BREITE; i++){
            for(int j = 0; j < HOEHE; j++){
                if(this.vordergrund[i][j] == HEILFELD){
                    if(this.vordergrund[i][j+1].isBegehbar()){
                        this.posX = i;
                        this.posY = j+1;
                    }
                }
            }
        }
    }

    public boolean isAufKampfFeld() {
        return aufKampfFeld;
    }

    public void setAufKampfFeld(boolean aufKampfFeld) {
        this.aufKampfFeld = aufKampfFeld;
    }

    public int getBlickrichtungAvatar() {
        return blickrichtungAvatar;
    }
}

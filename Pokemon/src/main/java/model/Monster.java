package model;

import java.io.Serializable;


/**
 * Repräsentation eines Monsters.
 * @author Agirman
 */
public class Monster  implements Serializable{
    
    /**
     * Magic number für die maximalen Lebenspunkte.
     */
    private static final int MAX_LEBENSPUNKTE = 100;
    
    /**
     * Variable fur Name der Monster.
     */
    private final String nameMonster;
    /**
     * Variable fur lebensPunkte.
     */
    private int lebensPunkte;
    /**
     * Variable fur erfahrungsPunkte.
     */
    private int erfahrungsPunkte;
    /**
     * Variable fur Level.
     */
    private int level;
    /**
     * Variable fur Staerke des Monsters.
     */
    private int staerke;
    /**
     * Variable fur Typ des Monsters. 
     */
    private String typMonster;
    /**
     * Variable fur Name der ersten Attacke.
     */    
    private String nameAttacke1;
    /**
     * Variable fur Name der zweiten Attacke.
     */
    private String nameAttacke2;
    /**
     * Variable für den Namen der dritten Attacke.
     */
    private String nameAttacke3;
    /**
     *  Variable für den Namen der vierten Attacke.
     */
    private String nameAttacke4;
    /**
     * Variable fur Wert der ersten Attacke.
     */
    private float wertAttacke1;
    /**
     * Variable fur Wert der zweiten Attacke.
     */
    private float wertAttacke2;   
    /**
     * Variable für die benötigten Erfahrungspunkte bis zum nächsten Level.
     */
    private int schwelleLevelAufstieg;
   
    /**
     * Der Konstruktor initialisiert alle Variablen des Monsters.
     * @param nameMonster String Name des Monsters
     * @param lebensPunkte Int Lebenspunkte des Monsters
     * @param erfahrungsPunkte Int Erfahrungspunkte des Monsters
     * @param level Int Das Level des Monsters
     * @param typMonster String Der Typ des Monsters
     * @param staerkeMonster Int Die Stärke des Monsters
     * @param nameAttacke1 String Name der ersten Attacke
     * @param nameAttacke2 String Name der zweiten Attacke
     * @param nameAttacke3 String Name der dritten Attacke
     * @param nameAttacke4 String Name der vierten Attacke
     * @param multiplikatorAttacke1 Ein Int, der mit der staerke multipliziert
     * wird um den Schaden von Attacke1 zu ermitteln.
     * @param multiplikatorAttacke2 Ein Int, der mit der staerke multipliziert
     * wird um den Schaden von Attacke2 zu ermitteln.
     * @param schwelleLevelAufstieg Int Die benötigten Erfahrungspunkte
     * bis zum Levelaufstieg 
     */
    public Monster(
            String nameMonster, 
            int lebensPunkte, 
            int erfahrungsPunkte, 
            int level, 
            String typMonster, 
            int staerkeMonster, 
            String nameAttacke1, 
            String nameAttacke2, 
            String nameAttacke3, 
            String nameAttacke4,
            float multiplikatorAttacke1, 
            float multiplikatorAttacke2,
            int schwelleLevelAufstieg)
            {
                
        this.nameMonster = nameMonster;
        this.lebensPunkte = lebensPunkte;
        this.erfahrungsPunkte = erfahrungsPunkte;
        this.level= level;
        this.typMonster = typMonster;
        this.staerke = staerkeMonster;
        this.nameAttacke1 = nameAttacke1;
        this.nameAttacke2 = nameAttacke2;
        this.nameAttacke3= nameAttacke3;
        this.nameAttacke4=nameAttacke4;
        this.wertAttacke1 = multiplikatorAttacke1 * this.staerke;
        this.wertAttacke2 = multiplikatorAttacke2 * this.staerke;
        this.schwelleLevelAufstieg = schwelleLevelAufstieg;
  
    }
    /**
     * Setzt die Lebenspunkte des Monsters auf MAX_LEBENSPUNKTE.
     */ 
    public void heilen(){
        this.lebensPunkte = MAX_LEBENSPUNKTE;
    }
    
    public String getNameMonster() {
        return nameMonster;
    }

    public int getLebensPunkte() {
        return lebensPunkte;
    }

    public int getErfahrungsPunkte() {
        return erfahrungsPunkte;
    }
        
    public int getLevel() {
        return level;
    }
    
    public String getTypMonster() {
        return typMonster;
    } 
        
    public int getStaerke() {
        return staerke;
    }

    public String getNameAttacke1() {
        return nameAttacke1;
    }

    public String getNameAttacke2() {
        return nameAttacke2;
    }

    public float getWertAttacke1() {
        return wertAttacke1;
    }

    public float getWertAttacke2() {
        return wertAttacke2;
    }

    public int getSchwelleLevelAufstieg() {
        return schwelleLevelAufstieg;
    }

    public void setSchwelleLevelAufstieg(int schwelleLevelAufstieg) {
        this.schwelleLevelAufstieg = schwelleLevelAufstieg;
    }

    public void setLebensPunkte(int lebensPunkte) {
        this.lebensPunkte = lebensPunkte;
    }

    public void setErfahrungsPunkte(int erfahrungsPunkte) {
        this.erfahrungsPunkte = erfahrungsPunkte;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStaerke(int staerke) {
        this.staerke = staerke;
    }

    public void setTypMonster(String typMonster) {
        this.typMonster = typMonster;
    }

    public void setNameAttacke1(String nameAttacke1) {
        this.nameAttacke1 = nameAttacke1;
    }

    public void setNameAttacke2(String nameAttacke2) {
        this.nameAttacke2 = nameAttacke2;
    }

    public void setWertAttacke1(int wertAttacke1) {
        this.wertAttacke1 = wertAttacke1;
    }

    public void setWertAttacke2(int wertAttacke2) {
        this.wertAttacke2 = wertAttacke2;
    }

    public String getNameAttacke3() {
        return nameAttacke3;
    }

    public void setNameAttacke3(String nameAttacke3) {
        this.nameAttacke3 = nameAttacke3;
    }

    public String getNameAttacke4() {
        return nameAttacke4;
    }

    public void setNameAttacke4(String nameAttacke4) {
        this.nameAttacke4 = nameAttacke4;
    }
    
    
}

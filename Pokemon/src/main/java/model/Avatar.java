package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Repräsentation des Avatars.
 * Hat ein Inventar, einen MonsterBeutel und einen Namen.
 * @author Agirman
 */
public class Avatar extends Observable implements Serializable{
    /**
     * Magic Number für die Grosse des MonsterBeutels.
     */
    private static final int GROESSE_MONSTER_BEUTEL = 10;
    /**
     * Anzahl verschiedener Items.
     */
    private static final int ANZAHL_VERSCHIEDENER_ITEMS = 3;
    /**
     * Anzahl kleiner Heiltränke zu Beginn des Spiels.
     */
    private static final int ANZAHL_KLEINE_HEILTRAENKE = 10;
    /**
     * Anzahl großer Heiltränke zu Beginn des Spiels.
     */
    private static final int ANZAHL_GROSSE_HEILTRAENKE = 10;
    /**
     * Anzahl Pokebälle zu Beginn des Spiels.
     */
    private static final int ANZAHL_POKEBAELLE = 20;
    /**
     * Index des großen Heiltranks im Inventar.
     */
    private static final int INDEX_GROSSER_HEILTRANK = 2;
    /**
     * Variable fur Name des Avatar.
     */
    private final String avatarName;
    /**
     * ArrayList mit allen Monstern des Spielers.
     */
    private ArrayList<Monster> monsterBeutel;
    /**
     * Array, mit dem das Inventar dargestellt wird.
     * An Stelle 0 anzahl der Heiltränke, an stelle 1 Anzahl der Pokebälle.
     */
    private int[] inventar;
    /**
     * Int, der die aktuelle Anzahl an Monstern im MonsterBeutel festhält.
     */
    private int aktuelleGroesseMonsterBeutel;
    
    /**
     * Die Stats des Avatars.
     */
    private CharacterStats stats;
    
    /**
     * Konstruktor fur Avatar.
     * @param avatarName String Name des Avatars
     * @param avatarMonster Monster Das erste Monster des Avatars.
     */
    public Avatar(String avatarName, Monster avatarMonster) {
        this.aktuelleGroesseMonsterBeutel = 0;
        this.avatarName = avatarName;
        this.monsterBeutel = new ArrayList<Monster>(GROESSE_MONSTER_BEUTEL);
        this.monsterHinzufuegen(avatarMonster);
        this.inventar = new int[ANZAHL_VERSCHIEDENER_ITEMS];
        
        this.stats = new CharacterStats(0,0,15,100,0,1);
        
        inventar[0] = ANZAHL_KLEINE_HEILTRAENKE;
        inventar[1] = ANZAHL_POKEBAELLE;
        inventar[INDEX_GROSSER_HEILTRANK] = ANZAHL_GROSSE_HEILTRAENKE;
    }    
    
    /**
     * Setzt die Lebenspunkte aller Monster im Monsterbeutel wieder auf den 
     * Startwert zurück.
     */
    public void heilen(){
        for(int i = 0; i < this.monsterBeutel.size(); i++){
            this.monsterBeutel.get(i).heilen();
        }
        this.informiereObserver();
    }
    
    public void CheckLevel(int ep){
        this.stats.checkLevelUp(ep);
        this.informiereObserver();
    }
    
    /**
     * Fügt dem Inventar einen kleinen Heiltrank hinzu.
     */
    public void kleinenHeiltrankHinzufuegen(){
        inventar[0]++;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Entfernt einen kleinen Heiltrank aus dem Inventar.
     */
    public void kleinenHeiltrankEntfernen(){
        inventar[0]--;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Fügt dem Inventar einen großen Heiltrank hinzu.
     */
    public void grossenHeiltrankHinzufuegen(){
        inventar[INDEX_GROSSER_HEILTRANK]++;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Entfernt einen großen Heiltrank aus dem Inventar.
     */
    public void grossenHeiltrankEntfernen(){
        inventar[INDEX_GROSSER_HEILTRANK]--;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Fügt dem Inventar einen Pokeball hinzu.
     */
    public void pokeballHinzufuegen(){
        inventar[1]++;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Entfernt einen Pokeball aus dem Inventar.
     */
    public void pokeballEntfernen(){
        inventar[1]--;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Fügt dem MonsterBeutel ein neues Monster hinzu, falls dieser nicht 
     * schon voll ist. 
     * @param gefangenesMonster 
     */
    public void monsterHinzufuegen(Monster gefangenesMonster){
        if(this.aktuelleGroesseMonsterBeutel < GROESSE_MONSTER_BEUTEL){
            this.monsterBeutel.add(gefangenesMonster);
            setChanged();
            notifyObservers();
            this.aktuelleGroesseMonsterBeutel++;
        } else {
            System.out.println("Monsterbeutel voll!");
        }
        
    }

    
    public ArrayList<Monster> getMonsterBeutel(){
        return this.monsterBeutel;
    }
    
    public String getAvatarName() {
        return avatarName;
    }
    
    public int[] getInventar() {
        return this.inventar;
    }
    
    public CharacterStats getCharacterStats(){
        return this.stats;
    }
    /**
     * Vereinfacht das updaten der Observer.
     */
    public void informiereObserver(){
        setChanged();
        notifyObservers(this);
    }
    
}

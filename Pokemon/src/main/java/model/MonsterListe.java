package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Eine Klasse, die alle Monster der Welt enthält.
 * @author Agirman
 */
public class MonsterListe implements Serializable{
    
    /**
     * Magic Number für die Lebenspunkte aller Monster.
     */
    
    private static final int LEBENSPUNKTE = 100;
    
    /**
     * Magic number für die Erfahrungspunkte.
     */
    
    private static final int ERFAHRUNGSPUNKTE = 0;
    
    /**
     * Magic Number für das Startlevel.
     */
    
    private static final int LEVEL = 1;
    
    /**
     * Magic Numerb für die Schwelle bis zum nächsten Level.
     */
    
    private static final int LEVEL_SCHWELLE = 10;
    
    /**
     * Magic number für die staerke des jeweiligen Monsters.
     */
    
    private static final int PIKACHU_STAERKE = 10;
    
    /**
     * Magic number für die staerke des jeweiligen Monsters.
     */
    
    private static final int SCHIGGIE_STAERKE = 15;
    
    /**
     * Magic number für die staerke des jeweiligen Monsters.
     */
    
    private static final int GLUMANDA_STAERKE = 12;
    
    /**
     * Magic number für die staerke des jeweiligen Monsters.
     */
    
    private static final int BISASAM_STAERKE = 20;
    
    /**
     * Magic number für die staerke des jeweiligen Monsters.
     */
    
    private static final int PUMMELUFF_STAERKE = 10;
    
    /**
     * Magic number für die staerke des jeweiligen Monsters.
     */
    
    private static final int TAUBSI_STAERKE = 14;
    
    /**
     * Magic number für die staerke des jeweiligen Monsters.
     */
    
    private static final int RATTFATZ_STAERKE = 10;
    
    /**
     * Magic number für die staerke des jeweiligen Monsters.
     */
    
    private static final int ENTON_STAERKE = 18;
    
    /**
     * Magic number für die staerke des jeweiligen Monsters.
     */
    
    private static final int SLEIMA_STAERKE = 20;
    
    /**
     * Magic number für die staerke des jeweiligen Monsters.
     */
    
    private static final int ONIX_STAERKE = 25;
    
    /**
     * Magic number für den Mutiplikator der ersten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float PIKACHU_MULTIPLIKATOR_1 = 3;
    
    /**
     * Magic number für den Mutiplikator der ersten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float SCHIGGIE_MULTIPLIKATOR_1 = 2;
    
    /**
     * Magic number für den Mutiplikator der ersten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float GLUMANDA_MULTIPLIKATOR_1 = 2;
    
    /**
     * Magic number für den Mutiplikator der ersten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float BISASAM_MULTIPLIKATOR_1 = (float) 1.5;
    
    /**
     * Magic number für den Mutiplikator der ersten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float PUMMELUFF_MULTIPLIKATOR_1 = 1;
    
    /**
     * Magic number für den Mutiplikator der ersten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float TAUBSI_MULTIPLIKATOR_1 = (float) 2.5;
    
    /**
     * Magic number für den Mutiplikator der ersten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float RATTFATZ_MULTIPLIKATOR_1 = 2;
    
    /**
     * Magic number für den Mutiplikator der ersten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float ENTON_MULTIPLIKATOR_1 = (float) 2.5;
    
    /**
     * Magic number für den Mutiplikator der ersten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float SLEIMA_MULTIPLIKATOR_1 = (float) 1.5;
    
    /**
     * Magic number für den Mutiplikator der ersten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float ONIX_MULTIPLIKATOR_1 = (float) 0.5;
    
    /**
     * Magic number für den Mutiplikator der zweiten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float PIKACHU_MULTIPLIKATOR_2 = 2;
    
    /**
     * Magic number für den Mutiplikator der zweiten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float SCHIGGIE_MULTIPLIKATOR_2 = 1;
    
    /**
     * Magic number für den Mutiplikator der zweiten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float GLUMANDA_MULTIPLIKATOR_2 = (float) 2.5;
    
    /**
     * Magic number für den Mutiplikator der zweiten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float BISASAM_MULTIPLIKATOR_2 = 2;
    
    /**
     * Magic number für den Mutiplikator der zweiten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float PUMMELUFF_MULTIPLIKATOR_2 = 2;
    
    /**
     * Magic number für den Mutiplikator der zweiten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float TAUBSI_MULTIPLIKATOR_2 = 1;
    
    /**
     * Magic number für den Mutiplikator der zweiten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float RATTFATZ_MULTIPLIKATOR_2 = 3;
    
    /**
     * Magic number für den Mutiplikator der zweiten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float ENTON_MULTIPLIKATOR_2 = 1;
    
    /**
     * Magic number für den Mutiplikator der zweiten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float SLEIMA_MULTIPLIKATOR_2 = (float) 0.5;
    
    /**
     * Magic number für den Mutiplikator der zweiten Attackde 
     * des jeweiligen Monsters.
     */
    
    private static final float ONIX_MULTIPLIKATOR_2 = 2;
    /**
     * String für Schlaf.
     */
    private final  String schlaf = "Schlaf";
    /**
     * String für Gift.
     */
    private final String gift = "Gift";
    /**
     * String für Paralyse.
     */
    private final String paralyse = "Paralyse";
    /**
     * Variable fur Arrayliste der Monster.
     */
    private ArrayList<Monster> monsterListe;    
    /**
     * Hier werden die Monster mit ihren Eigenschaften erstellt.
     */
    public MonsterListe(){
        
        monsterListe = new ArrayList();
        
        monsterlisteFuellen();
    }
    
    /**
     * Füllt die Monsterliste mit Monstern.
     */
    private void monsterlisteFuellen() {
        monsterListe.add(new Monster("Pikachu", LEBENSPUNKTE, ERFAHRUNGSPUNKTE, 
                LEVEL, "Elektro", PIKACHU_STAERKE, "Donnerschock", 
                "Ruckzuckhieb", schlaf,paralyse, PIKACHU_MULTIPLIKATOR_1, 
                PIKACHU_MULTIPLIKATOR_2, LEVEL_SCHWELLE));
        monsterListe.add(new Monster("Schiggie", LEBENSPUNKTE, ERFAHRUNGSPUNKTE,
                LEVEL, "Wasser", SCHIGGIE_STAERKE, "Biss",
                "Aquaknarre",gift,schlaf, SCHIGGIE_MULTIPLIKATOR_1,
                SCHIGGIE_MULTIPLIKATOR_2, LEVEL_SCHWELLE));
        monsterListe.add(new Monster("Glumanda", LEBENSPUNKTE, ERFAHRUNGSPUNKTE,
                LEVEL, "Feuer", GLUMANDA_STAERKE, "Drachenklaue", 
                "Glut",schlaf,gift, GLUMANDA_MULTIPLIKATOR_1, GLUMANDA_MULTIPLIKATOR_2,
                LEVEL_SCHWELLE));
        monsterListe.add(new Monster("Bisisam", LEBENSPUNKTE, ERFAHRUNGSPUNKTE,
                LEVEL, "Pflanze", BISASAM_STAERKE, "Body-Check", 
                "Rankenhieb",schlaf,paralyse, BISASAM_MULTIPLIKATOR_1, BISASAM_MULTIPLIKATOR_2,
                LEVEL_SCHWELLE));
        monsterListe.add(new Monster("Abra", LEBENSPUNKTE, ERFAHRUNGSPUNKTE,
                LEVEL, "Psycho", PUMMELUFF_STAERKE, "Psychokinese", 
                "Hypnose",paralyse,gift, PUMMELUFF_MULTIPLIKATOR_1, PUMMELUFF_MULTIPLIKATOR_2,
                LEVEL_SCHWELLE));
        monsterListe.add(new Monster("Taubsi", LEBENSPUNKTE, ERFAHRUNGSPUNKTE,
                LEVEL, "Flug", TAUBSI_STAERKE, "Sandwirbel", 
                "Windstoß",schlaf,paralyse, TAUBSI_MULTIPLIKATOR_1, TAUBSI_MULTIPLIKATOR_2,
                LEVEL_SCHWELLE));
        monsterListe.add(new Monster("Ratzfatz", LEBENSPUNKTE, ERFAHRUNGSPUNKTE,
                LEVEL, "Normal", RATTFATZ_STAERKE, "Rutenschlag", 
                "Tackle",gift,paralyse, RATTFATZ_MULTIPLIKATOR_1, RATTFATZ_MULTIPLIKATOR_2,
                LEVEL_SCHWELLE));
        monsterListe.add(new Monster("Enton", LEBENSPUNKTE, ERFAHRUNGSPUNKTE,
                LEVEL, "Ente", ENTON_STAERKE, "Kratzer", 
                "Nassmacher",schlaf,gift, RATTFATZ_MULTIPLIKATOR_1, RATTFATZ_MULTIPLIKATOR_2,
                LEVEL_SCHWELLE));
        monsterListe.add(new Monster("Sleima", LEBENSPUNKTE, ERFAHRUNGSPUNKTE,
                LEVEL, "Giftig", SLEIMA_STAERKE, "Giftwolke", 
                "Pfund",schlaf,paralyse, SLEIMA_MULTIPLIKATOR_1, SLEIMA_MULTIPLIKATOR_2,
                LEVEL_SCHWELLE));
        monsterListe.add(new Monster("Onix", LEBENSPUNKTE, ERFAHRUNGSPUNKTE,
                LEVEL, "Gestein", ONIX_STAERKE, "Haertner", 
                "Klammergriff",schlaf,paralyse, ONIX_MULTIPLIKATOR_1, ONIX_MULTIPLIKATOR_1,
                LEVEL_SCHWELLE));
    }
    
    /**
     * 
     * @param monsterNummer int
     * @return Monster.get(MonsterNumber)
     */
    public Monster getMonster(int monsterNummer){
           return monsterListe.get(monsterNummer);
    }
    /**
     * Gibt die ArrayList monsterListe zurück.
     * @return ArrayList monsterListe
     */
    public ArrayList<Monster> getGesamteListe(){
        return monsterListe;
    }
    /**
     * Gibt die Größe der ArrayList monsterListe zurück.
     * @return ArrayList monsterListe
     */
    public int getMonsterlistLaenge(){
        return monsterListe.size();
    }
    /**
     * Gibt ein zufälliges Monster aus der Liste zurück.
     * @return Ein zufälliges Monster
     */
    public Monster getZufaelligesMonster(){
        if (monsterListe.isEmpty()) {
            monsterlisteFuellen();
        }
        int zufall = (int) (Math.random() * monsterListe.size());
        return monsterListe.get(zufall);
    }
    /**
     * Entfernt ein bestimmtes Monster aus der monsterListe.
     * @param monster Das zu entfernende Mosnter
     */ 
    public void monsterEntfernen(Monster monster){
        this.monsterListe.remove(monster);
    }
    
}

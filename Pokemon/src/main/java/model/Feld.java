package model;

import java.io.Serializable;
import javafx.scene.image.Image;

/**
 * Modelliert die Objekte in der Spielwelt sowie die verschiedenen Arten von
 * Boden. Die einzelnen enums haben das Bild, druch das sie dargestellt werden
 * als Attribut.
 * @author pbraukmann
 */
public enum Feld implements Serializable{
    
    /**
     * Linker oberer Teil der Arena.
     *//**
     * Linker oberer Teil der Arena.
     */
    ARENA_LINKS_OBEN(new Image("file:Bilder/ArenaLinksOben.png"), false, 0),
    /**
     * Linker unterer Teil der Arena.
     */
    ARENA_LINKS_UNTEN(new Image("file:Bilder/ArenaLinksUnten.png"), false, 0),
    /**
     * Rechter oberer Teil der Arena.
     */
    ARENA_RECHTS_OBEN(new Image("file:Bilder/ArenaRechtsOben.png"), false, 0),
    /**
     * Rechter unterer Teil der Arena.
     */
    ARENA_RECHTS_UNTEN(new Image("file:Bilder/ArenaRechtsUnten.png"), false, 0),
    /**
     * Baum Variante 1.
     */
    BAUM1(new Image("file:Bilder/Baum1.png"), false, 0),
    /**
     * Baum Variante 2.
     */
    BAUM2(new Image("file:Bilder/Baum2.png"), false, 0),
    /**
     * Felsen.
     */
    FELSEN(new Image("file:Bilder/Felsen.png"), false, 0),
    /**
     * Gras Variante 1.
     */
    GRAS(new Image("file:Bilder/Gras.png"), true, 0.2),
    /**
     * Gras Variante 2.
     */
    GRAS2(new Image("file:Bilder/Gras2.png"), true, 0.3),
    /**
     * Normaler Boden.
     */
    NORMALER_BODEN(new Image("file:Bilder/NormalerBoden.png"), true, 0),
    /**
     * Pfütze.
     */
    PFUETZE(new Image("file:Bilder/Pfuetze.png"), true, 0),
    /**
     * Linker oberer Teil des Pokecenter.
     */
    POKECENTER_LINKS_OBEN(new Image("file:Bilder/PokecenterLinksOben.png"), false, 0),
    /**
     * Linker unterer Teil des Pokecenter.
     */
    POKECENTER_LINKS_UNTEN(new Image("file:Bilder/PokecenterLinksUnten.png"), false, 0),
    /**
     * Rechter oberer Teil des Pokecenter.
     */
    POKECENTER_RECHTS_OBEN(new Image("file:Bilder/PokecenterRechtsOben.png"), false, 0),
    /**
     * Rechter unterer Teil des Pokecenter.
     */
    POKECENTER_RECHTS_UNTEN(new Image("file:Bilder/PokecenterRechtsUnten.png"), false, 0),
    /**
     * Steiniger Boden.
     */
    STEINIGER_BODEN(new Image("file:Bilder/SteinigerBoden.png"), true, 0),
    /**
     * Wasser.
     */
    WASSER(new Image("file:Bilder/Wasser.png"), false, 0),
    /**
     * Baum Variante 3.
     */
    BAUM3(new Image("file:Bilder/baum.png"), false, 0),
    /**
     * Heilfeld.
     */
    HEILFELD(new Image("file:Bilder/heilfeld.png"), true, 0),
    /**
     * Monstersammler links.
     */
    MONSTERSAMMLER_L(new Image("file:Bilder/monstersammler_l.png"), true, 0),
    /**
     * Monstersammler rechts.
     */
    MONSTERSAMMLER_R(new Image("file:Bilder/monstersammler_r.png"), true, 0),
    /**
     * Monstersammler.
     */
    MONSTERSAMMLER(new Image("file:Bilder/monstersammler.png"), true, 0),
    /**
     * Silberner Stein.
     */
    STEIN_SILBER(new Image("file:Bilder/stein-silber.png"), false, 0),
    /**
     * Platzhalter für leeres Feld im Array vordergrund.
     */
    LEER(null, true, 0);
    
    /**
     * Das Bild, durch das das jeweilige Objekt in der GUI dargestellt wird.
     */
    private Image image;
    /**
     * Feld ist für Avatar begehbar.
     */
    private boolean begehbar;
    /**
     * Wahrscheinlichkeit, dass ein Monster angreift.
     */
    private double monsterWk;

    /**
     * Konstruktor.
     * @param image Das Bild, mit dem das Feld dargestellt wird
     * @param begehbar Feld ist für Avatar begehbar
     * @param monsterWk 
     */
    private Feld(Image image, boolean begehbar, double monsterWk) {
        this.image = image;
        this.begehbar = begehbar;
        this.monsterWk = monsterWk;
    }

    public Image getImage() {
        return image;
    }

    public boolean isBegehbar() {
        return begehbar;
    }
}
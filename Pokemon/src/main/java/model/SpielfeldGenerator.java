package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.Random;
import static model.Feld.ARENA_LINKS_OBEN;
import static model.Feld.ARENA_LINKS_UNTEN;
import static model.Feld.ARENA_RECHTS_OBEN;
import static model.Feld.ARENA_RECHTS_UNTEN;
import static model.Feld.BAUM1;
import static model.Feld.BAUM2;
import static model.Feld.FELSEN;
import static model.Feld.LEER;
import static model.Feld.GRAS;
import static model.Feld.HEILFELD;
import static model.Feld.NORMALER_BODEN;
import static model.Feld.PFUETZE;
import static model.Feld.POKECENTER_LINKS_OBEN;
import static model.Feld.POKECENTER_LINKS_UNTEN;
import static model.Feld.POKECENTER_RECHTS_OBEN;
import static model.Feld.POKECENTER_RECHTS_UNTEN;
import static model.Feld.STEINIGER_BODEN;
import static model.Feld.WASSER;

/**
 * Erstellt ein zufälliges Spielfeld in einer gegebenen Breite und Höhe (in Feldern).
 * @author pbraukmann
 */
public class SpielfeldGenerator implements Serializable {

    /**
     * Mindestabstand der Arena und des Pokecenter vom Rand links und oben.
     */
    private static final int ABSTAND_LINKS_OBEN = 2;
    /**
     * Mindesabstand von Arena/Pokecenter/Teich vom Rand rechts und unten.
     * Unterscheidet sich vom Abstand links und oben, da der Punkt die linke
     * obere Ecke der Arena/des Pokecenter bezeichnet.
     */
    private static final int ABSTAND_RECHTS_UNTEN = 4;
    /**
     * Mindestabstand von Arena, Pokecenter und Teich untereinander.
     */
    private static final int ABSTAND_ARENA_PC_TEICH = 6;
    /**
     * Größe von Arena und Pokecenter inklusive des steinigen Bodens,
     * der sie umgibt.
     */
    private static final int GROESSE_PC_ARENA = 4;
    /**
     * Position der Heilfelder (in y-Richtung) relativ zum Pokecenter.
     */
    private static final int POSITION_HEILFELD = 2;
    /**
     * Häufigkeit von Baum1-Feldern.
     */
    private static final double HAEUFIGKEIT_BAUM1 = 0.02;
    /**
     * Häufigkeit von Baum2-Feldern.
     */
    private static final double HAEUFIGKEIT_BAUM2 = 0.02;
    /**
     * Häufigkeit von Felsen-Feldern.
     */
    private static final double HAEUFIGKEIT_FELSEN = 0.01;
    /**
     * Häufigkeit von Pfütze-Feldern.
     */
    private static final double HAEUFIGKEIT_PFUETZE = 0.01;
    /**
     * Anteil von Feldern, die Gras-Felder sind.
     */
    private static final double ANTEIL_GRAS = 0.2;
    /**
     * Instanz der Klasse Random für die zufällige Berechnung von Positionen.
     */
    private static Random rand = new Random();
    /**
     * Punkt auf dem Spielfeld, an dem sich die Arena befindet.
     */
    private Point arena;
    /**
     * Punkt auf dem Spielfeld, an dem sich das Pokecenter befindet.
     */
    private Point center;
    /**
     * Punkt auf dem Spielfeld, an dem sich der Teich befindet.
     */
    private Point teich;
    /**
     * Breite des zu erstellenden Spielfelds in Feldern.
     */
    private int breite;
    /**
     * Höhe des zu erstellenden Spielfelds in Feldern.
     */
    private int hoehe;
    /**
     * Repräsentiert den Boden des zu erstellenden Spielfelds.
     */
    private Feld[][] hintergrund;
    /**
     * Repräsentiert die Objekte auf dem zu erstellenden Spielfeld.
     */
    private Feld[][] vordergrund;

    /**
     * Konstruiert einen SpielfeldGenerator für Spielfeld mit der angegebenen
     * Breite und Höhe. Legt zufällig Punkte fest, an denen die Arena, das
     * Pokecenter und der Teich platziert werden, wobei garantiert ist, dass
     * der Abstand zwischen diesen groß genug ist. Dann wird durch Aufrufen von
     * generiereHintergrund() und generiereVordergrund() die Spielwelt erstellt.
     * @param breite Breite des zu erstellenden Spielfelds
     * @param hoehe Höhe des zu erstellenden Spielfelds
     */
    public SpielfeldGenerator(int breite, int hoehe) {
        this.breite = breite;
        this.hoehe = hoehe;
        arena = new Point(ABSTAND_LINKS_OBEN + rand.nextInt(breite - ABSTAND_RECHTS_UNTEN),
                ABSTAND_LINKS_OBEN + rand.nextInt(hoehe - ABSTAND_RECHTS_UNTEN));
        do {
            center = new Point(ABSTAND_LINKS_OBEN + rand.nextInt(breite - ABSTAND_RECHTS_UNTEN),
                    ABSTAND_LINKS_OBEN + rand.nextInt(hoehe - ABSTAND_RECHTS_UNTEN));
        } while (arena.distance(center) < ABSTAND_ARENA_PC_TEICH);
        do {
            teich = new Point(ABSTAND_LINKS_OBEN + rand.nextInt(breite - ABSTAND_RECHTS_UNTEN),
                    ABSTAND_LINKS_OBEN + rand.nextInt(hoehe - ABSTAND_RECHTS_UNTEN));
        } while (teich.distance(arena) < ABSTAND_ARENA_PC_TEICH
                || teich.distance(center) < ABSTAND_ARENA_PC_TEICH);
        generiereHintergrund();
        generiereVordergrund();
    }

    /**
     * Generiert den Hintergrund des Spielfelds. Dabei werden zunächst alle Felder
     * als normaler Boden gesetzt, dann der Teich und der Hintergrund von
     * Pokecenter und Arena an den oben berechneten Punkten platziert und eine
     * Anzahl von zufälligen Felder in Gras umgewandelt.
     */
    private void generiereHintergrund() {
        hintergrund = new Feld[breite][hoehe];
        for (int i = 0; i < breite; i++) {
            for (int j = 0; j < hoehe; j++) {
                hintergrund[i][j] = NORMALER_BODEN;
            }
        }

        for (int i = arena.x - 1; i < arena.x + (GROESSE_PC_ARENA-1); i++) {
            for (int j = arena.y - 1; j < arena.y + (GROESSE_PC_ARENA-1); j++) {
                hintergrund[i][j] = STEINIGER_BODEN;
            }
        }

        for (int i = center.x - 1; i < center.x + (GROESSE_PC_ARENA-1); i++) {
            for (int j = center.y - 1; j < center.y + (GROESSE_PC_ARENA-1); j++) {
                hintergrund[i][j] = STEINIGER_BODEN;
            }
        }

        hintergrund[teich.x][teich.y] = WASSER;
        hintergrund[teich.x - 1][teich.y - 1] = WASSER;
        hintergrund[teich.x - 1][teich.y] = WASSER;
        hintergrund[teich.x - 1][teich.y + 1] = WASSER;
        hintergrund[teich.x][teich.y - 1] = WASSER;
        hintergrund[teich.x][teich.y + 1] = WASSER;
        hintergrund[teich.x + 1][teich.y - 1] = WASSER;
        hintergrund[teich.x + 1][teich.y] = WASSER;
        hintergrund[teich.x + 1][teich.y + 1] = WASSER;
        int anzahlGrasStellen = (int) (breite * hoehe * ANTEIL_GRAS);
        Point[] grasStellen = new Point[anzahlGrasStellen];
        for (int i = 0; i < anzahlGrasStellen; i++) {
            grasStellen[i] = new Point(rand.nextInt(breite), rand.nextInt(hoehe));
        }
        for (Point p : grasStellen) {
            if (hintergrund[p.x][p.y] == NORMALER_BODEN) {
                hintergrund[p.x][p.y] = GRAS;
            }
        }
    }

    /**
     * Generiert den Vordergrund des Spielfelds. Dabei werden zunächst alle
     * Felder auf LEER gesetzt. Dann werden Arena und Pokecenter an die
     * im Konstruktor berechneten Punkte gesetzt, wobei unterhalb des Pokecenters
     * noch zwei Heilfelder platziert werden. Zuletzt werden noch zufällig
     * mit den oben definierten Wahrscheinlichkeiten Bäume, Felsen und Pfützen
     * über das Feld verteilt.
     */
    private void generiereVordergrund() {
        vordergrund = new Feld[breite][hoehe];
        for (int i = 0; i < breite; i++) {
            for (int j = 0; j < hoehe; j++) {
                vordergrund[i][j] = LEER;
            }
        }

        vordergrund[arena.x][arena.y] = ARENA_LINKS_OBEN;
        vordergrund[arena.x + 1][arena.y] = ARENA_RECHTS_OBEN;
        vordergrund[arena.x][arena.y + 1] = ARENA_LINKS_UNTEN;
        vordergrund[arena.x + 1][arena.y + 1] = ARENA_RECHTS_UNTEN;

        vordergrund[center.x][center.y] = POKECENTER_LINKS_OBEN;
        vordergrund[center.x + 1][center.y] = POKECENTER_RECHTS_OBEN;
        vordergrund[center.x][center.y + 1] = POKECENTER_LINKS_UNTEN;
        vordergrund[center.x + 1][center.y + 1] = POKECENTER_RECHTS_UNTEN;
        vordergrund[center.x][center.y + POSITION_HEILFELD] = HEILFELD;
        vordergrund[center.x + 1][center.y + POSITION_HEILFELD] = HEILFELD;

        double zufallswert;
        for (int i = 0; i < breite; i++) {
            for (int j = 0; j < hoehe; j++) {
                if (hintergrund[i][j] != WASSER && vordergrund[i][j] == LEER) {
                    zufallswert = rand.nextDouble();
                    if (zufallswert < HAEUFIGKEIT_BAUM1) {
                        vordergrund[i][j] = BAUM1;
                    } else if (zufallswert < HAEUFIGKEIT_BAUM1+HAEUFIGKEIT_BAUM2) {
                        vordergrund[i][j] = BAUM2;
                    } else if (zufallswert < HAEUFIGKEIT_BAUM1+HAEUFIGKEIT_BAUM2
                            +HAEUFIGKEIT_FELSEN) {
                        vordergrund[i][j] = FELSEN;
                    } else if (zufallswert < HAEUFIGKEIT_BAUM1+HAEUFIGKEIT_BAUM2
                            +HAEUFIGKEIT_FELSEN+HAEUFIGKEIT_PFUETZE) {
                        vordergrund[i][j] = PFUETZE;
                    }
                }
            }
        }
    }

    public Feld[][] getHintergrund() {
        return hintergrund;
    }

    public Feld[][] getVordergrund() {
        return vordergrund;
    }
}

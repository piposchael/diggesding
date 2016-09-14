package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Agirman
 */
public class Spielstand implements Serializable {

    /**
     * Variable unter dem die Daten gespeichert werden.
     */
    private static final String FILENAME = "Spielstand.ser";
    /**
     * Spielstand laden ist zunächst null.
     */
    private static Spielstand spielstandLaden = null;
    /**
     * Variable fur Monsterliste.
     */
    private MonsterListe monsterliste;
    /**
     * Variable fur Avatar.
     */
    private Avatar avatar;
    /**
     * Variable fur Spielfeld.
     */
    private Spielfeld spielfeld;

    /**
     * Konstruktor für Spielstand.
     *
     * @param avatar Avatar
     * @param monsterliste Monsterliste
     * @param spielfeld Spielfeld
     */
    public Spielstand(Avatar avatar, MonsterListe monsterliste, Spielfeld spielfeld) {
        this.avatar = avatar;
        this.monsterliste = monsterliste;
        this.spielfeld = spielfeld;
    }

    /**
     * Methode zum speichern des Spielstandes.
     *
     * @param spielstand Spielstand
     */
    public void speichern(Spielstand spielstand) {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(FILENAME);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(this);

            System.out.println("Erfolgreich gespeichert unter: " + FILENAME + "!");
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Spielstand laden.
     *
     * @return spielstandLaden
     */
    public static Spielstand spielstandLaden() {

        try {
            FileInputStream fis = new FileInputStream(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            spielstandLaden = (Spielstand) ois.readObject();

            ois.close();
            fis.close();

        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException!");
           // Logger.getLogger(HighscoreSerialisieren.class.getName()).log(Level.SEVERE, null, ex);
            //    highscoreLaden = new HighscoreSerialisieren();
        } catch (IOException ex) {
            Logger.getLogger(Spielstand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Spielstand.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (spielstandLaden == null) {
            System.out.println("Nichts wurde geladen.");
        }

        return spielstandLaden;
    }

    public static String getFILENAME() {
        return FILENAME;
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }

    public void setSpielfeld(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }

    public MonsterListe getMonsterliste() {
        return monsterliste;
    }

    public void setMonsterliste(MonsterListe monsterliste) {
        this.monsterliste = monsterliste;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

}

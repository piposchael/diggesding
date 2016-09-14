/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import control.KampfSteuerung;
import control.Steuerung;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author fabiankaupmann
 */
public class KampfsystemTest {
    /**
     * Magic number für den Wert einer Attacke.
     */
    private static final int TEST_WERT_ATTACKE1 = 30;
    /**
     * Magic number für den Wert einer Attacke.
     */
    private static final int TEST_WERT_ATTACKE2 = 20;
    /**
     * Magic number für einen Zufallstest.
     */
    private static final int TEST_ZUFALL = 1000;
    /**
     * Index für ein Beispielmonster.
     */
    private static final int TEST_INDEX_MONSTER = 0;
      /**
     * Magic number für die maximalen Lebenspunkte.
     */
    private static final int MAX_LEBENSPUNKTE = 100;
    /**
     * Eine Kampfsteuerung.
     */
    private KampfSteuerung kampfSteuerung;
    /**
     * Ein Kampfsystem.
     */
    private Kampfsystem kampfsystem;
    /**
     * Eine Monsterliste.
     */
    private MonsterListe monsterListe;
    /**
     * Der Avatar.
     */
    private Avatar avatar;
    /**
     * Die Steurung.
     */
    private Steuerung steurung;
    /**
     * String Angriff.
     */
    private final String angriff = "Angriff";
    /**
     * String Gift.
     */
    private final String gift = "Gift";
    /**
     * String Paralyse.
     */
    private final String paralyse = "Paralyse";
    /**
     * String Schlaf.
     */
    private final String schlaf = "Schlaf";
    /**
     * Initialisiert Monsterliste und Kampfsteuerung.
     */
    public KampfsystemTest() {
        this.monsterListe = new MonsterListe();
        this.steurung = new Steuerung();
        this.kampfSteuerung = steurung.getKampfSteuerung();
        this.avatar = steurung.getAvatar(); 
        this.kampfsystem = new Kampfsystem(kampfSteuerung, avatar);
        this.kampfsystem.setSpielerMonster(this.monsterListe.getZufaelligesMonster());
        this.kampfsystem.setGegnerMonster(this.monsterListe.getZufaelligesMonster());
             
    }
    /**
     * Vor dem Test.
     */
    @BeforeClass
    public static void setUpClass() {
    }
    /**
     * Nach dem Test.
     */
    @AfterClass
    public static void tearDownClass() {
    }
    /**
     * Vor dem Test.
     */
    @Before
    public void setUp() {
    }
    /**
     * Nach dem Test.
     */
    @After
    public void tearDown() {
        this.kampfsystem.nachKampfAufrauemen();
    }

    /**
     * Test of nachKampfAufrauemen method, of class Kampfsystem.
     * Nach dem Aufräumen sollte alles wieder auf den ursprünglichen Zustand sein ,
     * dies wird hier überprüft.
     */
    @Test
    public void testNachKampfAufrauemen() {
        Kampfsystem instance = kampfsystem;
        instance.nachKampfAufrauemen();
        assertTrue(instance.getSpielerMonster()==null && instance.getGegnerMonster()==null
                    && "".equals(instance.getKampfPhase()) && instance.getStatusSpieler()==1
                    && instance.getStatusGegner()==1 && !instance.isKampfVorbei());
    }

    /**
     * Test of isKampfVorbei method, of class Kampfsystem.
     */
    @Test
    public void testIsKampfVorbei() {
        System.out.println("isKampfVorbei");
        Kampfsystem instance = this.kampfsystem;
        boolean expResult = false;
        boolean result = instance.isKampfVorbei();
        assertEquals(expResult, result);
    }

    /**
     * Test of setKampfVorbei method, of class Kampfsystem.
     */
    @Test
    public void testSetKampfVorbei() {
        System.out.println("setKampfVorbei");
        boolean kampfVorbei = true;
        Kampfsystem instance = this.kampfsystem;
        instance.setKampfVorbei(kampfVorbei);
        assertTrue(instance.isKampfVorbei());
    }


  

 

    /**
     * Test of updateObserver method, of class Kampfsystem.
     */
    @Test
    public void testUpdateObserver() {
        System.out.println("updateObserver");
        Kampfsystem instance = this.kampfsystem;
        instance.updateObserver();
        assertEquals(instance, this.kampfsystem);
    }

    /**
     * Test einer Kampfrunde .
     * Hier wird Testweise eine kampfrunde durchgeführt.
     * Sie enthält immer den Angriff/Item des Spielers und den Zug des Computers.
     * Zuerst wird ein Normaler Angriff getest.
     * Danach werden alle möglichen Statusveränderungen getestet.
     * Zwischen den tests werden die Hp und Statusveränderungen zurückgesetzt.
     */
    @Test
    public void testKampfrunde() {
        System.out.println("rundeSpieler");
        Kampfsystem instance = kampfsystem;   
        instance.setAngriffsstaerke((int)instance.getSpielerMonster().getWertAttacke1());
        instance.setKampfPhase(angriff);
        instance.rundeSpieler();
        instance.rundeComputer2();
       assertTrue(instance.getGegnerMonster().getLebensPunkte()<MAX_LEBENSPUNKTE ||
               instance.getStatusGegner() !=1);
        System.out.println("Nichts");
       assertTrue(instance.getSpielerMonster().getLebensPunkte()<MAX_LEBENSPUNKTE ||
               instance.getStatusSpieler()!=1);     
       instance.getGegnerMonster().setLebensPunkte(MAX_LEBENSPUNKTE);
       instance.getSpielerMonster().setLebensPunkte(MAX_LEBENSPUNKTE);
       instance.setStatusGegner(1);
       instance.setStatusSpieler(1);
       instance.setKampfPhase(angriff);
       instance.statusVeraenderung(gift, true);
       assertTrue(instance.getStatusGegner() == instance.getVERGIFTET());
       assertTrue(instance.getSpielerMonster().getLebensPunkte()<MAX_LEBENSPUNKTE ||
               instance.getStatusGegner()!=1);
       instance.getGegnerMonster().setLebensPunkte(MAX_LEBENSPUNKTE);
       instance.getSpielerMonster().setLebensPunkte(MAX_LEBENSPUNKTE);
       instance.setStatusGegner(1);
       instance.setStatusSpieler(1);
       instance.setKampfPhase(angriff);
       instance.statusVeraenderung(paralyse, true);
       assertTrue(instance.getStatusGegner() == instance.getPARALYSE());
       assertTrue(instance.getSpielerMonster().getLebensPunkte()<MAX_LEBENSPUNKTE ||
               instance.getStatusGegner()!=1);
       instance.getGegnerMonster().setLebensPunkte(MAX_LEBENSPUNKTE);
       instance.getSpielerMonster().setLebensPunkte(MAX_LEBENSPUNKTE);
       instance.setStatusGegner(1);
       instance.setStatusSpieler(1);
       instance.setKampfPhase(angriff);
       instance.statusVeraenderung(schlaf, true);
       assertTrue(instance.getStatusGegner() == instance.getSCHLAF());
       assertTrue(instance.getSpielerMonster().getLebensPunkte()<MAX_LEBENSPUNKTE ||
               instance.getStatusGegner()!=1);
      
      
    }

    /**
     * Test of statusVeraenderung method, of class Kampfsystem.
     * Hier wird getest ob die Statusveränderungen richtig durchgeführt werden.
     * Dazu wird testweise der Status auf keine effekte gesetzt (1) und dann mit den möglichen
     * Statusveränderugnen geändert.
     * Anschließen wird überprüft ob der Status sich auch richtig verändert hat.
     */
    @Test
    public void testStatusVeraenderung() {
        System.out.println("statusVer\u00e4nderung");
        String attacke = gift;
        Kampfsystem instance = kampfsystem;
        instance.setStatusSpieler(1);
        instance.statusVeraenderung(attacke,false);
        assertTrue(instance.getStatusSpieler()==instance.getVERGIFTET());
        instance.setStatusSpieler(1);
        String attacke2 = schlaf;
        instance.statusVeraenderung(attacke2,false);
        assertTrue(instance.getStatusSpieler()==instance.getSCHLAF());
        instance.setStatusSpieler(1);
        String attacke3 = paralyse;
        instance.statusVeraenderung(attacke3,false);
        assertTrue(instance.getStatusSpieler()==instance.getPARALYSE());    
    }
    
}

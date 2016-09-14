/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author fabiankaupmann
 */
public class MonsterListeTest {
    /**
     * Magic number für einen Text-Index.
     */
    private static final int TEST_INDEX = 0;
    /**
     * Magic number für die Länge einer ArrayList.
     */
    private static final int LISTE_LAENGE = 10;
    /**
     * Eine MonsterListe.
     */
    private MonsterListe monsterListe;
    /**
     * Initialisiert die monsterListe.
     */
    public MonsterListeTest() {
        this.monsterListe = new MonsterListe();
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
    }

    /**
     * Test of getMonsterlistLaenge method, of class MonsterListe.
     */
    @Test
    public void testGetMonsterlistLaenge() {
        System.out.println("getMonsterlistLaenge");
        MonsterListe instance = this.monsterListe;
        int expResult = LISTE_LAENGE;
        int result = instance.getMonsterlistLaenge();
        assertEquals(expResult, result);
    }

    /**
     * Test of getZufaelligesMonster method, of class MonsterListe.
     */
    @Test
    public void testGetZufaelligesMonster() {
        System.out.println("getZufaelligesMonster");
        MonsterListe instance = this.monsterListe;
        Monster test = instance.getZufaelligesMonster();
        assertTrue(test != null);
    }

    /**
     * Test of monsterEntfernen method, of class MonsterListe.
     */
    @Test
    public void testMonsterEntfernen() {
        System.out.println("monsterEntfernen");
        Monster monster = this.monsterListe.getZufaelligesMonster();
        MonsterListe instance = this.monsterListe;
        instance.monsterEntfernen(monster);
        assertTrue(!(instance.getGesamteListe().contains(monster)));
        
    }
    
}

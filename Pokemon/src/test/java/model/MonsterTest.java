/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author fabiankaupmann
 */
public class MonsterTest {
    /**
     * Magic number für die maximalen Lebenspunkte.
     */
    private static final int MAX_LEBENSPUNKTE = 100;
    /**
     * Eine MonsterListe.
     */
    private MonsterListe monsterListe;
    /**
     * Ein Monster.
     */
    private Monster monster;
    /**
     * Initialisiert monsterListe und Monster.
     */
    public MonsterTest() {
        this.monsterListe = new MonsterListe();
        this.monster = this.monsterListe.getZufaelligesMonster();
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
     * Test of heilen method, of class Monster.
     */
    @Test
    public void testHeilen() {
        System.out.println("heilen");
        Monster instance = this.monster;
        instance.heilen();
        assertEquals(instance.getLebensPunkte(), MAX_LEBENSPUNKTE);
    }    
}

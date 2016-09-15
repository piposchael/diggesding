/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author fabiankaupmann
 */
public class AvatarTest {
    /**
     * Magic number für Maximale Leben eines Monsters.
     */
    private static final int LEBEN_TEST = 100;
    
    /**
     * Index für ein Beispielmosnter.
     */
    private static final int TEST_INDEX_MONSTER = 0;
    /**
     * Index für ein Beispielmosnter.
     */
    private static final int TEST_INDEX_MONSTER_HINZUFUEGEN = 0;
    /**
     * Index für ein Beispielitem.
     */
    private static final int TEST_INDEX_ITEM = 3;
    /**
     * Größe des Monsterbeutels.
     */
    private static final int TEST_MONSTERBEUTEL_GROESSE = 6;
    /**
     * Ein Beispielname für den Avatar.
     */
    private static final String TEST_AVATAR_NAME = "Pierre";
    /**
     * Ein Avatar.
     */
    private Avatar avatar;
    /**
     * Eine MonsterListe.
     */
    private MonsterListe monsterListe;
    /**
     * Initialisiert monsterListe und avatar.
     */
    public AvatarTest() {
        this.monsterListe = new MonsterListe();
        this.avatar = new Avatar(TEST_AVATAR_NAME, this.monsterListe.getZufaelligesMonster());
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
     * Test of heilen method, of class Avatar.
     */
    @Test
    public void testHeilen() {
        System.out.println("heilen");
        Avatar instance = this.avatar;
        instance.heilen();
        assertEquals(LEBEN_TEST, 
        this.avatar.getMonsterBeutel().get(TEST_INDEX_MONSTER).getLebensPunkte());
    }

    /**
     * Test of getMonsterBeutel method, of class Avatar.
     */
    @Test
    public void testGetMonsterBeutel() {
        System.out.println("getMonsterBeutel");
        Avatar instance = this.avatar;
        ArrayList<Monster> expResult = new ArrayList<Monster>(
                TEST_MONSTERBEUTEL_GROESSE);
        expResult.add(this.avatar.getMonsterBeutel().get(TEST_INDEX_MONSTER));
        ArrayList<Monster> result = instance.getMonsterBeutel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvatarName method, of class Avatar.
     */
    @Test
    public void testGetAvatarName() {
        System.out.println("getAvatarName");
        Avatar instance = this.avatar;
        String expResult = TEST_AVATAR_NAME;
        String result = instance.getAvatarName();
        assertEquals(expResult, result);
    }

    /**
     * Test of kleinenHeiltrankHinzufuegen method, of class Avatar.
     */
    @Test
    public void testItemHinzufuegen() {
        System.out.println("itemHinzufuegen");
        Item neuesItem = new Item("Blob", "Liegt");
        Avatar instance = this.avatar;
        //instance.heiltrankHinzufuegen(neuesItem);
        //assertNotNull(instance.getInventar().get(TEST_INDEX_ITEM));
    }

    /**
     * Test of monsterHinzufuegen method, of class Avatar.
     */
    @Test
    public void testMonsterHinzufuegen() {
        System.out.println("monsterHinzufuegen");
        Monster gefangenesMonster = this.monsterListe.getZufaelligesMonster();
        Avatar instance = this.avatar;
        instance.monsterHinzufuegen(gefangenesMonster);
        assertNotNull(instance.getMonsterBeutel().get(TEST_INDEX_MONSTER_HINZUFUEGEN));
        
    }

    /**
     * Test of getInventar method, of class Avatar.
     */
    @Test
    public void testGetInventar() {
        System.out.println("getInventar");
        Avatar instance = this.avatar;
        //ArrayList<Item> expResult = instance.getInventar();
        //ArrayList<Item> result = instance.getInventar();
        //assertEquals(expResult, result);
    }

    /**
     * Test of informiereObserver method, of class Avatar.
     */
    @Test
    public void testInformiereObserver() {
        System.out.println("informiereObserver");
        Avatar instance = this.avatar;
        instance.informiereObserver();
        assertEquals(this.avatar, instance);
    }
    
}

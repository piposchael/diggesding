/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.Serializable;
import javafx.event.Event;
import javafx.event.EventHandler;
import model.Avatar;
import view.GUI;
import view.MonsterView;
/**
 * Koordiniert den monsterBeutel des Avatars mit der MonsterViewSteuerung.
 * @author fabiankaupmann
 */
public class MonsterViewSteuerung implements EventHandler, Serializable {
    /**
     * Magic number für den Index des jeweiligen Monsters.
     */
    private static final int INDEX_MONSTER_1 = 0;
    /**
     * Magic number für den Index des jeweiligen Monsters.
     */
    private static final int INDEX_MONSTER_2 = 1;
    /**
     * Magic number für den Index des jeweiligen Monsters.
     */
    private static final int INDEX_MONSTER_3 = 2;
    /**
     * Magic number für den Index des jeweiligen Monsters.
     */
    private static final int INDEX_MONSTER_4 = 3;
    /**
     * Magic number für den Index des jeweiligen Monsters.
     */
    private static final int INDEX_MONSTER_5 = 4;
    /**
     * Magic number für den Index des jeweiligen Monsters.
     */
    private static final int INDEX_MONSTER_6 = 5;
    /**
     * Magic number für den Index des jeweiligen Monsters.
     */
    private static final int INDEX_MONSTER_7 = 6;
    /**
     * Magic number für den Index des jeweiligen Monsters.
     */
    private static final int INDEX_MONSTER_8 = 7;
    /**
     * Magic number für den Index des jeweiligen Monsters.
     */
    private static final int INDEX_MONSTER_9 = 8;
    /**
     * Magic number für den Index des jeweiligen Monsters.
     */
    private static final int INDEX_MONSTER_10 = 9;
    
    /**
     * Variable für GUI.
     */
    private MonsterView monsterView;
    /**
     * Variable fur Avatar.
     */
    private Avatar avatar;
    /**
     * Variable fur Monster.
     */
    private GUI gui;
    
    /**
     * Initialisiert alle nötigen Objekte für eine Ansicht der Monster über
     * das Menü.
     * Fügt außerdem den Buttons der MonsterView Actions hinzu.
     * @param avatar Der Avatar
     * @param monsterView Die GUI für die Ansicht der Monster
     * @param gui Die Standard-GUI
     */
    public MonsterViewSteuerung(Avatar avatar, MonsterView monsterView, GUI gui ){
        this.avatar = avatar;
        this.monsterView = monsterView;
        this.monsterView.getMenueItem().setOnAction(this);
        this.monsterView.getZurueck().setOnAction(this);
        this.monsterView.getMonster1().setOnAction(this);
        this.monsterView.getMonster2().setOnAction(this);
        this.monsterView.getMonster3().setOnAction(this);
        this.monsterView.getMonster4().setOnAction(this);
        this.monsterView.getMonster5().setOnAction(this);
        this.monsterView.getMonster6().setOnAction(this);
        this.monsterView.getMonster7().setOnAction(this);
        this.monsterView.getMonster8().setOnAction(this);
        this.monsterView.getMonster9().setOnAction(this);
        this.monsterView.getMonster10().setOnAction(this);
        this.avatar.addObserver(monsterView);
        this.avatar.informiereObserver();
        this.gui = gui;
    }

    @Override
    public void handle(Event event) {
        if (event.getSource() == this.monsterView.getMenueItem()) {
            System.exit(0);
        }
        if (event.getSource() == this.monsterView.getZurueck()) {
            this.gui.show(this.gui.getPrimaryStage());
        }
        if(event.getSource() == this.monsterView.getMonster1()){
            if(this.avatar.getMonsterBeutel().size() > INDEX_MONSTER_1){
                monsterView.setIndexMonster(INDEX_MONSTER_1);
            }
        }
        if(event.getSource() == this.monsterView.getMonster2()){
            if(this.avatar.getMonsterBeutel().size() > INDEX_MONSTER_2){
                monsterView.setIndexMonster(INDEX_MONSTER_2);
            }
        }
        if(event.getSource() == this.monsterView.getMonster3()){
            if(this.avatar.getMonsterBeutel().size() > INDEX_MONSTER_3){
                monsterView.setIndexMonster(INDEX_MONSTER_3);
            }
        }
        if(event.getSource() == this.monsterView.getMonster4()){
            if(this.avatar.getMonsterBeutel().size() > INDEX_MONSTER_4){
                monsterView.setIndexMonster(INDEX_MONSTER_4);
            }
        }
        if(event.getSource() == this.monsterView.getMonster5()){
            if(this.avatar.getMonsterBeutel().size() > INDEX_MONSTER_5){
                monsterView.setIndexMonster(INDEX_MONSTER_5);
            }
        }
        if(event.getSource() == this.monsterView.getMonster6()){
            if(this.avatar.getMonsterBeutel().size() > INDEX_MONSTER_6){
                monsterView.setIndexMonster(INDEX_MONSTER_6);
            }
        }
        if(event.getSource() == this.monsterView.getMonster7()){
            if(this.avatar.getMonsterBeutel().size() > INDEX_MONSTER_7){
                monsterView.setIndexMonster(INDEX_MONSTER_7);
            }
        }
        if(event.getSource() == this.monsterView.getMonster8()){
            if(this.avatar.getMonsterBeutel().size() > INDEX_MONSTER_8){
                monsterView.setIndexMonster(INDEX_MONSTER_8);
            }
        }
        if(event.getSource() == this.monsterView.getMonster9()){
            if(this.avatar.getMonsterBeutel().size() > INDEX_MONSTER_9){
                monsterView.setIndexMonster(INDEX_MONSTER_9);
            }
        }
        if(event.getSource() == this.monsterView.getMonster10()){
            if(this.avatar.getMonsterBeutel().size() > INDEX_MONSTER_10){
                monsterView.setIndexMonster(INDEX_MONSTER_10);
            }
        }
        monsterView.update(avatar, null);
    }
    
    
}

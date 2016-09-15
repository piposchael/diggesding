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
import view.CharacterView;
import view.GUI;

/**
 *
 * @author Pippn
 */
public class CharacterViewSteuerung implements Serializable, EventHandler{
    /**
     * Variable für GUI.
     */
    private CharacterView charView;
    /**
     * Variable fur Avatar.
     */
    private Avatar avatar;
    /**
     * Variable fur Monster.
     */
    private GUI gui;
    
    public CharacterViewSteuerung(Avatar avatar, CharacterView charView, GUI gui){
        this.avatar = avatar;
        this.charView = charView;
        this.charView.getZurueck().setOnAction(this);
        this.charView.getBeenden().setOnAction(this);
        this.avatar.addObserver(charView);
        this.avatar.informiereObserver();
        this.gui = gui;
    }

    @Override
    public void handle(Event event) {
        if(event.getSource() == this.charView.getBeenden()){
            System.exit(0);
        }
        if(event.getSource() == this.charView.getZurueck()){
            this.gui.show(this.gui.getPrimaryStage());
        }
    }
    
}

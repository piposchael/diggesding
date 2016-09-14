/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 * Repräsentation eines Items.
 * Hat einen Namen und eine Beschreibung.
 * @author fabiankaupmann
 */
public class Item  implements Serializable{
    /**
     * Der Name des Items.
     */
    private final String name;
    /**
     * Eine Beschreibung des Items.
     */
    private final String beschreibung;
    /**
     * Initialisiert name und beschreibung des Items.
     * @param name Der Name des Items.
     * @param beschreibung Die Beschreibung des Items.
     */
    public Item(String name, String beschreibung){
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
    
    
}

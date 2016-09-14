/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Pippn
 */
public class Karte {
    
    private static final String BERLIN = "Berlin";
    
    private static final String AMSTERDAM = "Amsterdam";
    
    private static final String DETROIT = "Detroit";
    
    private String activeKarte;
    
    private Feld[][] vordergrundBerlin;
    
    private Feld[][] hintergrundBerlin;
    
    private Feld[][] vordergrundAmster;
    
    private Feld[][] hintergrundAmster;
    
    private Feld[][] vordergrundDetroit;
    
    private Feld[][] hintergrundDetroit;
    
    private Feld[][] vordergrundActive;
    
    private Feld[][] hintergrundActive;
    
    public Karte(int breite, int hoehe){
        
        SpielfeldGenerator berlin = new SpielfeldGenerator(breite, hoehe);
        
        SpielfeldGenerator amsterdam = new SpielfeldGenerator(breite, hoehe);
        
        SpielfeldGenerator detroit = new SpielfeldGenerator(breite, hoehe);
        
        this.vordergrundBerlin = berlin.getVordergrund();
        this.hintergrundBerlin = berlin.getHintergrund();
        
        this.vordergrundAmster = amsterdam.getVordergrund();
        this.hintergrundAmster = amsterdam.getHintergrund();
        
        this.vordergrundDetroit = detroit.getVordergrund();       
        this.hintergrundDetroit = detroit.getHintergrund();
        
        this.hintergrundActive = this.hintergrundBerlin;
        this.vordergrundActive = this.vordergrundBerlin;
        
        this.activeKarte = BERLIN;
    }
    
    public Feld[][] getVordergrund(){
        return this.vordergrundActive;
    }
    
    public Feld[][] getHintergrund(){
        return this.hintergrundActive;
    }
    
    public String getKartenName(){
        return this.activeKarte;
    }
    
    public void setKartenName(String name){
        this.activeKarte = name;
    }
    
    public void setActiveKarte(String name){
        
        if(name == "Berlin"){
            this.hintergrundActive = this.hintergrundBerlin;
            this.vordergrundActive = this.vordergrundBerlin;
        }else if(name == "Amsterdam"){
            this.hintergrundActive = this.hintergrundAmster;
            this.vordergrundActive = this.vordergrundAmster;
        }else if(name == "Detroit"){
            this.hintergrundActive = this.hintergrundDetroit;
            this.vordergrundActive = this.vordergrundDetroit;
        }
        this.setKartenName(name);
    }
}
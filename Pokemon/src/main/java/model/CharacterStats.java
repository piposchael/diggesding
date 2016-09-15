/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Pippn
 */
public class CharacterStats implements Serializable {
    
    private int geschick;
    
    private int wissen;
    
    private int kraft;
    
    private int lebenspunkte;
    
    private int schnelligkeit;
    
    private int level;
    
    private int erfahrungspunkte;
    
    public CharacterStats(int geschick, int wissen, int kraft, int leben, 
            int schnelligkeit, int level){
        
        this.geschick = geschick;
        this.wissen = wissen;
        this.kraft = kraft;
        this.lebenspunkte = leben;
        this.schnelligkeit = schnelligkeit;
        this.level = level;
        this.erfahrungspunkte = 500;
    }
    
    /*
    * Methode zum Berechnen des Levels.
    * Prüft ob Level up, wenn ja, dann setzte Level up und EP-Berechnung.
    */
    public void checkLevelUp(int ep){
        int epToLvlUp = calcFullLevelEp(this.level);
        int gesamtLevelEp = this.erfahrungspunkte + ep;
        
        if(gesamtLevelEp >= epToLvlUp){
            this.level++;
            this.erfahrungspunkte = gesamtLevelEp - epToLvlUp;
        }else{
            this.erfahrungspunkte = gesamtLevelEp;
        }
    }
    
    public int calcFullLevelEp(int level){
        
        int epToLevelUp = level * 1000;
        
        return epToLevelUp;
    }

    /**
     * @return the geschick
     */
    public int getGeschick() {
        return geschick;
    }

    /**
     * @param geschick the geschick to set
     */
    public void setGeschick(int geschick) {
        this.geschick = geschick;
    }

    /**
     * @return the wissen
     */
    public int getWissen() {
        return wissen;
    }

    /**
     * @param wissen the wissen to set
     */
    public void setWissen(int wissen) {
        this.wissen = wissen;
    }

    /**
     * @return the kraft
     */
    public int getKraft() {
        return kraft;
    }

    /**
     * @param kraft the kraft to set
     */
    public void setKraft(int kraft) {
        this.kraft = kraft;
    }
    /**
     * @return the kraft
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the kraft to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the lebenspunkte
     */
    public int getLebenspunkte() {
        return lebenspunkte;
    }

    /**
     * @param lebenspunkte the lebenspunkte to set
     */
    public void setLebenspunkte(int lebenspunkte) {
        this.lebenspunkte = lebenspunkte;
    }

    /**
     * @return the schnelligkeit
     */
    public int getSchnelligkeit() {
        return schnelligkeit;
    }

    /**
     * @param schnelligkeit the schnelligkeit to set
     */
    public void setSchnelligkeit(int schnelligkeit) {
        this.schnelligkeit = schnelligkeit;
    }
    
    /**
     * @return the Ehrfahrungspunkte
     */
    public int getErfahrungspunkte() {
        return erfahrungspunkte;
    }

    /**
     * @param ep the Erfahrungspunkte to set
     */
    public void setErfahrungspunkte(int ep) {
        this.erfahrungspunkte = ep;
    }
}

package control;

import java.io.Serializable;
import javafx.event.Event;
import javafx.event.EventHandler;
import model.Avatar;
import view.GUI;
import view.ItemView;

/**
 * Koordiniert das Inventer das Avatars mit der ItemView.
 * @author fabiankaupmann
 */
public class ItemViewSteuerung implements EventHandler, Serializable {
    /**
     * Magic number für den Index des zweiten Items im Inventar.
     */
    private static final int INDEX_ITEM_2 = 2;
    /**
     * Magic String für einen Zeilenumbruch.
     */
    private static final String ZEILENUMBRUCH = "\n";
    /**
     * Variable für GUI.
     */
    private ItemView itemView;
    /**
     * Variable fur Avatar.
     */
    private Avatar avatar;
    /**
     * Variable fur Monster.
     */
    private GUI gui;
    
    /**
     * Initialisiert alle für die GUI des Inventars nötigen Objekte. 
     * Setzt außerdem die Actions der Buttons in der ItemView.
     * @param avatar Der Avatar des Spielers.
     * @param itemView Das UI des Inventars.
     * @param gui Die standard-GUI.
     */
    public ItemViewSteuerung(Avatar avatar, ItemView itemView, GUI gui){
        this.avatar = avatar;
        this.itemView = itemView;
        this.itemView.getZurueck().setOnAction(this);
        this.itemView.getBeenden().setOnAction(this);
        this.avatar.addObserver(itemView);
        this.avatar.informiereObserver();
        this.gui = gui;
    }

    @Override
    public void handle(Event event) {
        if(event.getSource() == this.itemView.getBeenden()){
            System.exit(0);
        }
        if(event.getSource() == this.itemView.getZurueck()){
            this.gui.show();
        }
    }
}
 
    
    


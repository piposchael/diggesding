/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.Serializable;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.StartView;

/**
 *
 * @author Pippn
 */
public class StartSteuerung extends Application implements EventHandler, Serializable{
    
    private SpielSteuerung game;
    
    private StartView startView;
    
    private Stage primStage;
    
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        this.primStage = primaryStage;
        
        this.startView = new StartView(primaryStage, this);
        
        this.game = new SpielSteuerung();
        
        this.startView.getNewGame().setOnAction(this);
        this.startView.getLoadGame().setOnAction(this);
        
    }

    @Override
    public void handle(Event event) {
        if(event.getSource() == startView.getNewGame()){
            this.game.getGui().show(this.primStage);
        }
        if(event.getSource() == startView.getLoadGame()){
            this.game.mitGeladenemSpielstandInitialisieren();
            this.game.getGui().show(this.primStage);
        }
    }
    
    public Stage getprimStage(){
        return primStage;
    }
    
}

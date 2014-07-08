package gameControl;

import pathFindAI.Playerai;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MapDisplay;
import view.Settings;

public class Main {

    public static void main(String[] args) {
        final Game gameEngine = Game.getInstance();
        // Creates an AI agent and adds it as an observer for the GameEngine
        Playerai aIPlayer = new Playerai();
        gameEngine.addObserver(aIPlayer);
        
        // Runs the Settings UI
        // This thread then creates the Main GUI thread and joins the game
        Game.getInstance().join();
        (new Runnable() {
            public void run() {
                try {
                    Game.getInstance().addObserver(new MapDisplay());
                } catch (IOException ex) {
                    Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).run();
        
        /*
        new Runnable() {
            public void run() {
                Settings dialog = new Settings(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        }.run();
                */
    }
}

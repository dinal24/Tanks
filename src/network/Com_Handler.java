package network;

import gameDATA.GameMap;
import gameControl.Temp;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

//handle incoming and outgiong messages between client and the server
public class Com_Handler implements Observer {

    private static Com_Handler com_Handler = null;
    private Incoming incoming;
    private Outgoing outgoing;
    private GameMap map;
    //define directions
    private String north;
    private String south;
    private String east;
    private String west;
    private String shoot;
    private String join;

    //maintains single object instance
    public static Com_Handler getInstance() {
        if (Com_Handler.com_Handler == null) {
            Com_Handler.com_Handler = new Com_Handler();
        }
        return Com_Handler.com_Handler;
    }

    //constructor
    private Com_Handler() {
        incoming = new Incoming();
        outgoing = new Outgoing();
        incoming.addObserver(this);
        map = GameMap.getInstance();

        north = "UP#";
        south = "DOWN#";
        east = "RIGHT#";
        west = "LEFT#";
        shoot = "SHOOT#";
        join = "JOIN#";
    }

    //move up command
    public void up() {
        outgoing.sendMessage(north);
    }

    //move down command
    public void down() {
        outgoing.sendMessage(south);
    }

    //move left command
    public void left() {
        outgoing.sendMessage(west);
    }

    //move right command
    public void right() {
        outgoing.sendMessage(east);
    }

    //shoot command
    public void shoot() {
        outgoing.sendMessage(shoot);
    }

    //join to the game
    public void join() {
        outgoing.sendMessage(join);
    }


    //observe the receiveing instance and update the map accordingly 
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Incoming) {
            String string = (String) arg;
	    if (string.startsWith("G:")) {
		map.updateMap(string);
	    } else if (string.startsWith("L:")) {
		map.setLifePack(string);
	    } else if (string.startsWith("C:")) {
		map.setCoinPile(string);
	    } else if (string.startsWith("S:")) {
		map.initialisePlayers(string);
	    } else if (string.startsWith("I:")) {
		map.initialiseMap(string);
	    } else {
		Logger.getLogger(Temp.class.getName()).log(
			Level.SEVERE, "Un Identified String: {0}", string);
	    }
        }
        
        

    }
}

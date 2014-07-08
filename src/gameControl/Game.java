package gameControl;

import network.Com_Handler;
import java.util.Observable;
import java.util.Observer;
import mapBuilder.BasicMapBlock;
import gameDATA.GameMap;
import mapBuilder.Player;

public class Game extends Observable implements Observer{

    public static final int SIZE=20;

    public static Game gameEngineAPI = null;
    private Com_Handler messageParser;
    
     
    private GameMap map;

    private Game() {
        this.messageParser = Com_Handler.getInstance();
        //this.mapController = new MapController();
        this.map = GameMap.getInstance();
        map.addObserver(this);
    }

    public static Game getInstance() {
        if (Game.gameEngineAPI == null) {
            Game.gameEngineAPI = new Game();
        }
        return Game.gameEngineAPI;
    }

    public void join() {
        messageParser.join();
    }

    public void moveUp() {
        messageParser.up();
    }

    public void moveDown() {
        messageParser.down();
    }

    public void moveLeft() {
       messageParser.left();
    }

    public void moveRight() {
        messageParser.right();
    }

    public void shoot() {
        messageParser.shoot();
    }

    public BasicMapBlock[][] getMap(){
        return map.getMap();
    }

    public Player[] getPlayers(){
        return map.getPlayers();
    }

    public Player getThisPlayer(){
        return map.thisPlayer();
    }

    public void update(Observable o, Object arg) {
        if(o instanceof GameMap){
            setChanged();
            notifyObservers(arg);
        }
    }

}

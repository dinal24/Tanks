package gameDATA;

import java.util.Observable;
import mapBuilder.BasicMapBlock;
import mapBuilder.Bounty;
import mapBuilder.Brick;
import mapBuilder.Coin;
import mapBuilder.DeadPlayer;
import mapBuilder.LifePack;
import mapBuilder.Player;
import mapBuilder.Stone;
import mapBuilder.Water;

public class GameMap extends Observable {

    private static GameMap map = null;
    private BasicMapBlock[][] board;
    private String myPlayerName;
    private Player[] players;

    private GameMap() {
        board = new BasicMapBlock[gameControl.Game.SIZE][gameControl.Game.SIZE];
        players = new Player[5];
    }

    public static GameMap getInstance() {
        if (GameMap.map == null) {
            GameMap.map = new GameMap();
        }
        return GameMap.map;
    }

    public void initialiseMap(String string) {
        String[] temp = string.split(":");
        myPlayerName = temp[1];
        setBricks(temp[2]);
        setStones(temp[3]);
        setWater(temp[4]);
        setChanged();
        notifyObservers("MAP_INITIALISED");
    }

    private void setWater(String string) {
        String[] waterStr = string.split(";"), waterXY = new String[2];
        Water water;

        for (int i = 0; i < waterStr.length; i++) {
            waterXY = waterStr[i].split(",");
            water = new Water(Integer.parseInt(waterXY[0]),
                    Integer.parseInt(waterXY[1]), "WATER");
            map.addMapObject(water);
        }

    }

    private void setStones(String string) {
        String[] stoneStr = string.split(";"), stoneXY = new String[2];
        Stone stone;
        for (int i = 0; i < stoneStr.length; i++) {
            stoneXY = stoneStr[i].split(",");
            stone = new Stone(Integer.parseInt(stoneXY[0]),
                    Integer.parseInt(stoneXY[1]), "STONE");
            map.addMapObject(stone);
        }
    }

    private void setBricks(String string) {
        String[] bricks = string.split(";"), brickXY;
        Brick brick;
        for (int i = 0; i < bricks.length; i++) {
            brickXY = bricks[i].split(",");
            brick = new Brick(Integer.parseInt(brickXY[0]),
                    Integer.parseInt(brickXY[1]), "BRICK", 0);
            map.addMapObject(brick);
        }
    }

    private void updateBricks(String string) {
        String[] bricks = string.split(";");
        for (int i = 0; i < bricks.length; i++) {
            Brick brick = (Brick) board[Integer.parseInt(bricks[i].split(",")[0])][Integer.parseInt(bricks[i].split(",")[1])];
            brick.setDamage(Integer.parseInt(bricks[i].split(",")[2]));
            if (brick.getDamage() == 100) {
                board[Integer.parseInt(bricks[i].split(",")[0])][Integer.parseInt(bricks[i].split(",")[1])] = null;
            }
        }
    }

    public void updatePlayer(String string) {
        Player player = new Player(Integer.parseInt(string.split(";")[1].split(",")[0]), Integer.parseInt(string.split(";")[1].split(",")[1]), Integer.parseInt(string.split(";")[2]), string.split(";")[0], Integer.parseInt(string.split(";")[4]), Integer.parseInt(string.split(";")[6]), Integer.parseInt(string.split(";")[5]), !string.split(";")[0].equals(myPlayerName), Integer.parseInt(string.split(";")[3]) == 1);
        if(player.getHealth()==0){
            player = new DeadPlayer(player);
        }
        int playerNo = Integer.parseInt(string.split(";")[0].toString().charAt(1) + "");
        board[players[playerNo].getX()][players[playerNo].getY()] = null;
        board[player.getX()][player.getY()] = player;
        players[playerNo] = player;
    }

    public void setLifePack(String string) {
        String[] lpInfo = string.split(":");
        String[] lifePackXY = lpInfo[1].split(",");
        LifePack lifePack = new LifePack(Integer.parseInt(lifePackXY[0]),
                Integer.parseInt(lifePackXY[1]), "LIFEPACK",
                Integer.parseInt(lpInfo[2]) / 1000);
        map.addMapObject(lifePack);
    }

    public void updateMap(String string) {
        String[] temp = string.split(":");
        for (int i = 1; i < (temp.length - 1); i++) {
            updatePlayer(temp[i]);
        }
        updateBricks(temp[temp.length - 1]);
        reduceBountyRemainingTime();
        setChanged();
        notifyObservers("GLOBAL_UPDATE");
    }

    public void setCoinPile(String string) {
        String[] coinInfo = string.split(":");

        String[] coinXY = coinInfo[1].split(",");
        Coin coin = new Coin(Integer.parseInt(coinXY[0]),
                Integer.parseInt(coinXY[1]), "COIN",
                Integer.parseInt(coinInfo[3]),
                Integer.parseInt(coinInfo[2]) / 1000);
        map.addMapObject(coin);
    }

    private void addMapObject(BasicMapBlock obj) {
        board[obj.getX()][obj.getY()] = obj;
    }

    private void reduceBountyRemainingTime() {
        for (int i = 0; i < gameControl.Game.SIZE; i++) {
            for (int j = 0; j < gameControl.Game.SIZE; j++) {
                if (board[i][j] instanceof Bounty) {
                    Bounty bounty = (Bounty) board[i][j];
                    bounty.setTimeToLive(bounty.getTimeToLive() - 1);
                    if (bounty.getTimeToLive() == 1) {
                        board[i][j] = null;
                    } else {
                        board[i][j] = bounty;
                    }
                }
            }
        }
    }

    public BasicMapBlock[][] getMap() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void initialisePlayers(String string) {
        String[] temp = string.split(":");
        for (int i = 1; i < temp.length; i++) {
            Player player = new Player(Integer.parseInt(temp[i].split(";")[1].split(",")[0]), Integer.parseInt(temp[i].split(";")[1].split(",")[1]), Integer.parseInt(temp[i].split(";")[2]), temp[i].split(";")[0], 100, 0, 0, temp[i].split(";")[0].equals(myPlayerName), false);
            int playerNo = Integer.parseInt(temp[i].split(";")[0].toString().charAt(1) + "");
            players[playerNo] = player;
        }
        setChanged();
        notifyObservers("PLAYERS_INITIALISED");
    }

    public Player thisPlayer() {
        for(int i=0;i<5;i++){
            if(players[i]!=null && players[i].getName().equals(myPlayerName)){
                return players[i];
            }
        }
        return null;
    }
}

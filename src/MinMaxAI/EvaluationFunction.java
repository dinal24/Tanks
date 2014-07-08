package MinMaxAI;
/**
 *
 * @author Eranda
 */
import java.util.ArrayList;
import mapBuilder.Coin;
import mapBuilder.BasicMapBlock;
import mapBuilder.Player;

public class EvaluationFunction {
    
    public static int getEvaluationValue(int x, int y){
        int val = 0;
        BasicMapBlock[][] map = gameControl.Game.getInstance().getMap();
        Coin[] coins = getCoinPiles(map);
        Player thisPlayer = gameControl.Game.getInstance().getThisPlayer();
        // Calculates the advantage over coin piles
        for(int i=0;i<coins.length;i++){         
            int dis = Math.abs(coins[i].getX()-thisPlayer.getX()) + Math.abs(coins[i].getY()-thisPlayer.getY());
            val += (int)10000/dis * coins[i].getValue() * (dis < coins[i].getTimeToLive() ? 0.5 : 1);
        }
        
        // Calculates the advantage over health packs when life is 50%
        if(gameControl.Game.getInstance().getThisPlayer().getHealth()<50){
            // has to implement
        }
        
        return val;
    }
    
    
    private static Coin[] getCoinPiles(BasicMapBlock[][] map){
        ArrayList<BasicMapBlock> coins = new ArrayList<BasicMapBlock>();
        for(int i=0;i<gameControl.Game.SIZE;i++){
            for(int j=0;j<gameControl.Game.SIZE;j++){
                if(map[i][j] instanceof Coin){
                    coins.add(map[i][j]);
                }
            }
        }
       return coins.toArray(new Coin[coins.size()]);
    }
}

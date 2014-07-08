package MinMaxAI;

import java.util.ArrayList;
import mapBuilder.BasicMapBlock;
/**
 *
 * @author Eranda
 */
public class MoveGenerator {

    public static Integer[][] getMaxMoves(Integer[] pos) {
        return getMaxMoves(pos);
    }

    public static Integer[][] getMinMoves(Integer[] pos) {
        return getMoves(pos);
    }

    private static Integer[][] getMoves(Integer[] pos) {
        ArrayList<Integer[]> list = new ArrayList<Integer[]>();
        int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < moves.length; i++) {
            int x = pos[0] + moves[i][0];
            int y = pos[1] + moves[i][1];
            //MapObject[][] map = gameengine.GameEngine.getInstance().getMap();
            if (x >= 0 && x < gameControl.Game.SIZE && y >= 0 && y < gameControl.Game.SIZE 
                    //&& !(map[x][y] instanceof models.Obstacle) && !(map[x][y] instanceof models.Player)
             ) {
                Integer[] arr = new Integer[2];
                arr[0] = x;
                arr[1] = y;
                list.add(arr);
            }
        }
        return list.toArray(new Integer[list.size()][2]);
    }
}

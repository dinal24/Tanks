package MinMaxAI;
/**
 *
 * @author Eranda
 */
public class Main {

    public static void main(String[] args) {
        Integer[] minPos = {3,4};
        Integer[] minPrevPos = {2,4};
        // The count number (30) has to be an even number to finish the min max tree at a max node
        MaxNode maxRoot = new MaxNode(minPos, minPrevPos, 2);
        System.out.println(maxRoot.getVal(-999999999, 999999999));
    }
}

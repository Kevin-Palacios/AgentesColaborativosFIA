
package agentes;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author macario
 */
public class PathState implements Comparable<PathState> {
    private Node node;
    private int cost;
    private int heuristic;

    public PathState(Node node, int cost, int heuristic) {
        this.node = node;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    public Node getNode() {
        return node;
    }

    public int getCost() {
        return cost;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public int getTotalCost() {
        return cost + heuristic;
    }

    @Override
    public int compareTo(PathState other) {
        return Integer.compare(getTotalCost(), other.getTotalCost());
    }
}

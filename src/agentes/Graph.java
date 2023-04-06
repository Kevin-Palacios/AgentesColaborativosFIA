
package agentes;
import java.util.*;

public class Graph {
    private List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void printGraph() {
        for (Node node : nodes) {
            System.out.print("Node " + node.getId() + " has neighbors: ");
            for (Node neighbor : node.getNeighbors()) {
                Coordenadas aux = node.getCoordenadas();
                System.out.print(neighbor.getId() + "(" + node.getCost(neighbor) + ") "+" ("+aux.getX()+", "+aux.getY()+") ,");
                //System.out.print(node.getId() + " ("+aux.getX()+", "+aux.getY()+") ,");
            }
            System.out.println();
        }
    }


    public List<Node> findPath(Node start, Node end, Agente agente) {
        Map<Node, Node> cameFrom = new HashMap<>();
        Map<Node, Integer> costSoFar = new HashMap<>();
    
        PriorityQueue<PathState> frontier = new PriorityQueue<>();
        frontier.add(new PathState(start, 0, heuristic(agente, start)));
    
        cameFrom.put(start, null);
        costSoFar.put(start, 0);
    
        while (!frontier.isEmpty()) {
            PathState current = frontier.poll();
    
            if (current.getNode() == end) {
                break;
            }
    
            for (Node neighbor : current.getNode().getNeighbors()) {
                int newCost = costSoFar.get(current.getNode()) + current.getNode().getCost(neighbor);
                if (!costSoFar.containsKey(neighbor) || newCost < costSoFar.get(neighbor)) {
                    costSoFar.put(neighbor, newCost);
                    int priority = newCost + heuristic(agente, neighbor);
                    frontier.add(new PathState(neighbor, newCost, priority));
                    cameFrom.put(neighbor, current.getNode());
                }
            }
        }
    
        List<Node> path = new ArrayList<>();
        Node current = end;
        while (current != start) {
            path.add(current);
            current = cameFrom.get(current);
        }
        path.add(start);
        Collections.reverse(path);
    
        return path;
    }
    
    private int heuristic(Agente agente, Node start) {
        // implement your heuristic function here
        int h;
        h=agente.mapa[start.getCoordenadas().getX()][start.getCoordenadas().getY()];
        return h;
    }

    
    public Node getNodeById(int id) {
        return nodes.get(id);
    }
   
}

package agentes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
    private int id;
    private List<Node> neighbors;
    private Coordenadas coordenadas;
    private Map<Node, Integer> costMap;

    public Node(int id) {
        this.id = id;
        this.neighbors = new ArrayList<>();
        this.costMap = new HashMap<>();
    }

    public Node(int id, int i, int j) {
        this.id = id;
        this.neighbors = new ArrayList<>();
        this.costMap = new HashMap<>();
        this.coordenadas = new Coordenadas(i, j);
    }
    
    
    public int getId() {
        return id;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node neighbor, int cost) {
        neighbors.add(neighbor);
        costMap.put(neighbor, cost);
    }

    public int getCost(Node neighbor) {
        return costMap.get(neighbor);
    }
    
    
    public Coordenadas getCoordenadas(){
        return coordenadas;
    }
    
    
    
}
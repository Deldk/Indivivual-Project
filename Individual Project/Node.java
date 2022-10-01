import javax.sound.sampled.SourceDataLine;
import java.util.*;

public class Node {
    String state;
    Node parent;
    int path_cost;
    Route action;

    public Node(String state, Node parent, int path_cost, Route action){
        this.state = state;
        this.parent = parent;
        this.path_cost = path_cost;
        this.action = action;
    }

    public String getState(Node Node){
        return Node.state;
    }

    public Node getParentState(Node Node){
        return Node.parent;
    }

    public String path_cost(Node Node){
        return Node.path_cost;
    }

// Calculating the distance between two airports.
    public setPath_Cost(Node sourceNode, Node destinationNode){
        double lat1 = Double.parseDouble(getLatitude(dict_airport.get(sourceNode.state)));
        double long1 = Double.parseDouble(getLongitude(dict_airport.get(sourceNode.state)));
        double lat2 = Double.parseDouble(getLatitude(dict_airport.get(destinationNode.state)));
        double long2 = Double.parseDouble(getLongitude(dict_airport.get(destinationNode.state)));
        this.path_cost = HaversineAlgorithm.haversine(lat1, long1, lat2, long2);
    }

    public ArrayList<Route> actions(){
        ArrayList<Route> Neighbors = Route.dict_route.get(this.state);
        return Neighbors;

    }

}

import java.util.Queue;
import java.util.ArrayList;
import java.util.Hashset;
import java.util.LinkedList;
import java.io.*;
import java.util.*;

public class searchAlgorithm {
    
    /**
     * This function takes in an airport and a destination and returns true if the airport is the
     * destination and false otherwise
     * 
     * @param airport the current airport
     * @param destination The destination airport
     * @return The boolean value of the goal test.
     */
    static boolean goal_test(Airport airport, String destination){
        String airport_location = Airport.getCity(airport) + "- " + Airport.getCountry(airport);
        if (airport_location.equals(destination)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This function takes in a destination node and returns an arraylist of routes that lead to the
     * destination node
     * 
     * @param destination_node The node that is the goal state
     * @return The solution path is being returned.
     */
    public static ArrayList<Route> solution_path(Node destination_node){
        ArrayList<Route> successorRoutes = new ArrayList<Route>();
        successorRoutes.add(destination_node.action);
        Node current_node = destination_node.parent;
        int iterator = destination_node.path_cost;

        while (current_node.parent != null){
            successorRoutes.add(current_node.action);
            current_node = current_node.parent;
            iterator -= 1;
        }

        Collections.reverse(successorRoutes);
        return successorRoutes;
    }



    /**
     * This function takes in a list of source airports and a destination city and country, and returns
     * a list of routes that can be taken to get to the destination
     * 
     * @param source_airports ArrayList of Airport objects
     * @param destination_CityAndCountry The destination city and country in the format of "City,
     * Country"
     * @return The method is returning an ArrayList of Route objects.
     */
    public static ArrayList<Route> BFS2(ArrayList<Airport> source_airports, String destination_CityAndCountry){
        

        Queue<Node> frontier = new LinkedList<Node>();
        for (int i = 0; i < source_airports.size(); i++){
            Airport first_sourceAirports = source_airports.get(i);
            String sourceCode = Airport.getIATA(first_sourceAirports);

            Node sourceAirport = new Node(sourceCode, null, 0, null);
            frontier.add(sourceAirport);
        }

        HashSet<String> explored = new HashSet<String>();

        while (frontier.size() > 0){
            Node sourceAirport = frontier.remove();
            explored.add(sourceAirport.state);

            ArrayList<Route> possibleDestinations = sourceAirport.actions();

            for (int i = 0; i < possibleDestinations.size(); i++){
                Route childRoute = possibleDestinations.get(i);
                String child_sourceCode = Route.getdestinationCode(childRoute);
                Node child = new Node(child_sourceCode, sourceAirport, sourceAirport.path_cost += 1, childRoute);

                if (!frontier.contains(child) && !explored.contains(child.state)){
                    if (child.state != "Not available" || child.state != "\\N"){
                        if (Airport.airportID_object.containsKey(child.state)){
                            Airport airport = Airport.airportID_object.get(child.state);
                            if (goal_test(airport, destination_CityAndCountry)){
                                System.out.println("Found a route for you");
                                return solution_path(child);
                            }
                
                            frontier.add(child);
                        }
                        
                }
                }
            }
        }
        return null;
    }

}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Map;

public class Route{
    String airlineCode;
    String AirlineID;
    String Source_AirportCode;
    String Source_AirportID;
    String Destination_AirportCode;
    String Destination_AirportID;
    String CodeShare;
    String Stops;
    String Equipment;

    static Map<String, ArrayList<Route>> dict_route = new HashMap<String, ArrayList<Route>>();

    public Route(String airlineCode, String AirlineID, String Source_AirportCode, String Source_AirportID, String Destination_AirportCode, String Destination_AirportID, String CodeShare, String Stops){
        this.airlineCode = airlineCode;
        this.AirlineID = AirlineID;
        this.Source_AirportCode = Source_AirportCode;
        this.Source_AirportID = Source_AirportID;
        this.Destination_AirportCode = Destination_AirportCode;
        this.Destination_AirportID = Destination_AirportID;
        this.CodeShare = CodeShare;
        this.Stops = Stops;
    }

    public static void read_RouteData(){
        String line = "";
        try{
            FileReader File = new FileReader("C:/Users/dake_h/Desktop/Project Files/routes.csv");
            BufferedReader input = new BufferedReader(File);
           
            while((line = input.readLine()) != null){
                String[] routes = line.split(",");
                if (routes[2] == "\\N" || routes[2] == ""){
                    routes[2] = "Not available";
                }
                if (routes[3] == "" || routes[3] == ""){
                    routes[3] = "Not available";
                }
                if (routes[4] == "" || routes[4] == "\\N"){
                    routes[4] = "Not available";
                }
                if (routes[6] == "" || routes[6] == "\\N"){
                    routes[6] = "Does not have";
                }

                Route route1 = new Route(routes[0], routes[1], routes[2], routes[3], routes[4], routes[5], routes[6], routes[7]);
                if (dict_route.containsKey(routes[2])){
                    dict_route.get(routes[2]).add(route1);
                }else{
                    dict_route.put(routes[2], new ArrayList<Route>());
                    dict_route.get(routes[2]).add(route1);
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public String getDestinationID(Route route){
        return route.Destination_AirportID;
    }

    public static String getsourceCode(Route route){
        return route.Source_AirportCode;
    }

    public static String getdestinationCode(Route route){
        return route.Destination_AirportCode;
    }

    public getRoute(){

    }
    
}
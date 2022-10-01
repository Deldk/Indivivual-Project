import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Map;

public class flightRouting{

    static String destination;
    static String filename;
    public static void main(String[] args){
        Airline.read_AirlineData();
        Airport.read_AirportData();
        Route.read_RouteData();
        
        ArrayList<Route> Result = searchAlgorithm.BFS2(read_inputFile(), destination);
        
        String result = "";
        int num_of_flights = 0;
        int num_of_stops = 0;

        for (int i = 0; i < Result.size(); i++){
            Route fromRoute = Result.get(i);
            result += "\t" + (i+1) + ". " + fromRoute.airlineCode + " from " + fromRoute.Source_AirportCode + " to " + fromRoute.Destination_AirportCode + " " + fromRoute.Stops + " stops\n";
            num_of_flights ++;
            num_of_stops = num_of_stops + Integer.parseInt(fromRoute.Stops);
        }
        result += "Total flights: " + num_of_flights + "\n";
        result += "Total additional stops: " + num_of_stops + "\n";
        result += "Optimality criteria: flights";
        System.out.println(result);

        try{
            FileWriter filewriter = new FileWriter(filename);
            BufferedWriter writer = new BufferedWriter(filewriter);

            writer.write(result);
            System.out.println("Path noted down in file " + filename);
            writer.close();
        }
        catch (IOException e){
            System.out.print(e.getMessage());
        }

        }
    

    public static ArrayList<Airport> read_inputFile(){
        String line = "";
        try{
            FileReader File = new FileReader("C:/Users/dake_h/Desktop/Project Files/Accra-Winnipeg.txt");
            BufferedReader input = new BufferedReader(File);

            //Reading in the source city and country
            line = input.readLine();
            String[] source_and_destination = line.split(",");
            String source_City = source_and_destination[0];
            String source_Country = source_and_destination[1];

            String sourcekey = source_City + "-" + source_Country;
            ArrayList<Airport> sourceAirports = Airport.dict_airport.get(sourcekey);
            System.out.println(sourceAirports);

            System.out.println(" ");

            //Reading in the destination city and country
            line = input.readLine();
            source_and_destination = line.split(",");
            String destination_City = source_and_destination[0];
            String destination_Country = source_and_destination[1];

            String destinationkey = destination_City + "-" + destination_Country;
            ArrayList<Airport> destinationAirports = Airport.dict_airport.get(destinationkey);
            System.out.println(destinationAirports);

            destination = destinationkey;
            filename = source_City + "-" + destination_City + "_output.txt";

            return sourceAirports;
                
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;

}
}
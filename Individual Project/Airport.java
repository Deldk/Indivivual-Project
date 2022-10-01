import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Map;

public class Airport{
    String AirportID;
    String Name;
    String City;
    String Country;
    String IATA;
    String ICAO;
    String Latitude;
    String Longitude;
    String Altitude;
    String Timezone;
    String DST;
    String TzDB_Timezone;
    String Type;
    String dataSource;

    static Map<String, ArrayList<Airport>> dict_airport = new HashMap<String, ArrayList<Airport>>();
    static Map<String, String> airportID_Code = new HashMap<String, String>();
    static Map<String, Airport> airportID_object = new HashMap<String, Airport>();


    public Airport(String AirportID, String Name, String City, String Country, String IATA, String ICAO, String Latitude, String Longitude, String Altitude, String Timezone, String DST, String TzDB_Timezone, String Type, String dataSource){
        this.AirportID = AirportID;
        this.Name = Name;
        this.City = City;
        this.Country = Country;
        this.IATA = IATA;
        this.ICAO = ICAO;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.Altitude = Altitude;
        this.Timezone = Timezone;
        this.DST = DST;
        this.TzDB_Timezone = TzDB_Timezone;
        this.Type = Type;
        this.dataSource = dataSource;
    }

    /**
     * This function reads the airport data from the csv file and stores it in a dictionary
     */
    public static void read_AirportData (){
        String line = "";
        try{
            FileReader File = new FileReader("C:/Users/dake_h/Desktop/Project Files/airports.csv");
            BufferedReader input = new BufferedReader(File);
           
            while((line = input.readLine()) != null){
                String[] airports = line.split(",");
                if (airports[2] == "\\N" || airports[2] == ""){
                    airports[2] = "Not available";
                }
                if (airports[3] == "" || airports[3] == ""){
                    airports[3] = "Not available";
                }
                if (airports[4] == "" || airports[4] == "\\N"){
                    airports[4] = "Not available";
                }
                if (airports[5] == "" || airports[5] == "\\N"){
                    airports[5] = "Not available";
                }

                Airport airport1 = new Airport(airports[0], airports[1], airports[2], airports[3], airports[4], airports[5], airports[6], airports[7], airports[8], airports[9], airports[10], airports[11], airports[12], airports[13]);
                String key = airports[2] + "- " + airports[3];
                airportID_Code.put(airports[4], airports[0]); 
                if (dict_airport.containsKey(key)){
                    dict_airport.get(key).add(airport1);
                }else{
                    dict_airport.put(key, new ArrayList<Airport>());
                    dict_airport.get(key).add(airport1);
                }
                
                airportID_object.put(airports[4], airport1);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public String[] getAirportIDs(String sourceCity, String sourceCountry){
        String sourcekey = sourceCity + "-" + sourceCountry;
        String[] AirportIDs = dict_airport(sourcekey);
        return AirportIDs;
    }

    public String getName(Airport object){
        return object.Name;
    }

    public static String getIATA(Airport object){
        return object.IATA;
    }

    public String getLatitude(Airport object){
        return object.Latitude;
    }

    public String getLongitude(Airport object){
        return object.Longitude;
    }

    public static String getCity(Airport object){
        return object.City;
    }

    public static String getCountry(Airport object){
        return object.Country;
    }

}

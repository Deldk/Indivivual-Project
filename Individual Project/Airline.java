import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Airline{
    String AirlineID;
    String Name;
    String Alias;
    String IATA;
    String ICAO;
    String Callsign;
    String Country;
    String Active;

    public Airline(String airline_ID, String Name, String Alias, String IATA, String ICAO, String Callsign, String Country, String Active){
        this.AirlineID = airline_ID;
        this.Name = Name;
        this.Alias = Alias;
        this.IATA = IATA;
        this.ICAO = ICAO;
        this.Callsign = Callsign;
        this.Country = Country;
        this.Active = Active;
    }

    /**
     * This function reads the data from the airlines.csv file and stores it in a dictionary
     */
    public static void read_AirlineData (){
        String line = "";
        Dictionary dict_airline = new Hashtable();
        try{
            FileReader File = new FileReader("C:/Users/dake_h/Desktop/Project Files/airlines.csv");
            BufferedReader input = new BufferedReader(File);
           
            while((line = input.readLine()) != null){
                String[] airlines = line.split(",");
                if (airlines[2] == "\\N" || airlines[2] == ""){
                    airlines[2] = "Not available";
                }
                if (airlines[3] == "" || airlines[3] == ""){
                    airlines[3] = "Not available";
                }
                if (airlines[4] == "" || airlines[4] == "\\N"){
                    airlines[4] = "Not available";
                }
                if (airlines[5] == "" || airlines[5] == "\\N"){
                    airlines[5] = "Not available";
                }
                Airline airline1 = new Airline(airlines[0], airlines[1], airlines[2], airlines[3], airlines[4], airlines[5], airlines[6], airlines[7]);
                dict_airline.put(airlines[0], airline1);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public String getName(Airline object){
        return object.Name;
    }

    public String getIATA(Airline object){
        return object.IATA;
    }

}



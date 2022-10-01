public class HaversineAlgorithm {
    static double haversine(double latitude1, double longitude1, double latitude2, double longitude2){

        double LatitudeDistance = Math.toRadians(latitude2 - latitude1);
        double LongitudeDistance = Math.toRadians(longitude2 - longitude1);

        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);

        double a = Math.pow(Math.sin(LatitudeDistance/2), 2) + Math.pow(Math.sin(LongitudeDistance/2), 2) * Math.cos(latitude1) * Math.cos(latitude2);

        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }
}

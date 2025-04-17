public class Debris extends SpaceObject {
    public Debris(int recordId, int noradId, String name, String country, String orbitType, String objectType,
                  int launchYear, String launchSite, double longitude, double avgLongitude,
                  String geoHash, int daysOld) {
        super(recordId, noradId, name, country, orbitType, objectType, launchYear, launchSite,
              longitude, avgLongitude, geoHash, daysOld);
    }

    @Override
    public String getObjectType() {
        return "Debris";
    }
}
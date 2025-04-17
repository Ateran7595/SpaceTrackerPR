public class UnknownObject extends SpaceObject {
    public UnknownObject(int recordId, int noradId, String satelliteName, String country, String orbitType,
                         String objectType, int launchYear, String launchSite, double longitude,
                         double avgLongitude, String geoHash, int daysOld) {
        super(recordId, noradId, satelliteName, country, orbitType, objectType,
              launchYear, launchSite, longitude, avgLongitude, geoHash, daysOld);
    }

    @Override
    public String getObjectType() {
        return "Unknown";
    }
}

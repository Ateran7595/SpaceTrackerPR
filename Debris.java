public class Debris extends SpaceObject {
    private int conjunctionCount;

    public Debris(int recordId, int noradId, String name, String country, String orbitType, String objectType,
                  int launchYear, String launchSite, double longitude, double avgLongitude,
                  String geoHash, int daysOld, int conjunctionCount) {
        super(recordId, noradId, name, country, orbitType, objectType, launchYear, launchSite,
              longitude, avgLongitude, geoHash, daysOld);
        this.conjunctionCount = conjunctionCount;
    }

    public int getConjunctionCount() {
        return conjunctionCount;
    }

    public void setConjunctionCount(int conjunctionCount) {
        this.conjunctionCount = conjunctionCount;
    }

    @Override
    public String getObjectType() {
        return "Debris";
    }
}

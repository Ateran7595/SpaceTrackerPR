public abstract class SpaceObject {
    protected int recordId;
    protected int noradId;
    protected String name;
    protected String country;
    protected String orbitType;
    protected String objectType;
    protected int launchYear;
    protected String launchSite;
    protected double longitude;
    protected double avgLongitude;
    protected String geoHash;
    protected int daysOld;

    protected boolean stillInOrbit;
    protected String riskLevel;

    public SpaceObject(int recordId, int noradId, String name, String country, String orbitType, String objectType,
                       int launchYear, String launchSite, double longitude, double avgLongitude,
                       String geoHash, int daysOld) {
        this.recordId = recordId;
        this.noradId = noradId;
        this.name = name;
        this.country = country;
        this.orbitType = orbitType;
        this.objectType = objectType;
        this.launchYear = launchYear;
        this.launchSite = launchSite;
        this.longitude = longitude;
        this.avgLongitude = avgLongitude;
        this.geoHash = geoHash;
        this.daysOld = daysOld;
    }

    public abstract String getObjectType();

    public int getRecordId() { return recordId; }
    public String getSatelliteName() { return name; }
    public String getCountry() { return country; }
    public String getOrbitType() { return orbitType; }
    public int getLaunchYear() { return launchYear; }
    public String getLaunchSite() { return launchSite; }
    public double getLongitude() { return longitude; }
    public double getAvgLongitude() { return avgLongitude; }
    public String getGeoHash() { return geoHash; }
    public int getDaysOld() { return daysOld; }

    public String getSummary() {
        return recordId + ", " + name + ", " + country + ", " + orbitType + ", " +
                launchYear + ", " + launchSite + ", " + longitude + ", " + avgLongitude + ", " +
                geoHash + ", " + daysOld;
    }
       
    public boolean isStillInOrbit() {
        return stillInOrbit;
    }

    public void setStillInOrbit(boolean stillInOrbit) {
        this.stillInOrbit = stillInOrbit;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}
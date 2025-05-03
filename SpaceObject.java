/**
 * The {@code SpaceObject} class serves as an abstract representation of a tracked object in space.
 * It stores shared attributes for objects such as satellites, debris, payloads, or rocket bodies.
 * Subclasses must define the specific type of space object by implementing {@link #getObjectType()}.
 */
public abstract class SpaceObject {

    /** csv identifiers */
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
    private String hrrCategory;
    private boolean nominated;
    private String nominatedAt;
    private boolean hasDossier;
    private String lastUpdatedAt;
    private String justification;
    private String focusedAnalysis;
    private int conjunctionCount;
    private boolean unknownObject;
    private int allManeuvers;
    private int daysSinceOb;
    private int recentManeuvers;
    private double deltaV90Day;
    private boolean hasSisterDebris;

    /**
     * Constructs a new {@code SpaceObject} with the given parameters.
     *
     * @param recordId       Unique record ID
     * @param noradId        NORAD catalog ID
     * @param name           Name of the object
     * @param country        Launching country
     * @param orbitType      Orbit type (LEO, GEO, etc.)
     * @param objectType     Type of object (e.g., Payload)
     * @param launchYear     Launch year
     * @param launchSite     Launch site location
     * @param longitude      Current longitude
     * @param avgLongitude   Average longitude
     * @param geoHash        Geohash location
     * @param daysOld        Number of days since launch
     */
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

    /**
     * Returns the specific type of space object (e.g., Debris, Payload).
     * This method must be implemented by subclasses.
     *
     * @return String representing the object type
     */
    public abstract String getObjectType();

    /** @return Record ID of the space object */
    public int getRecordId() { return recordId; }

    /** @return Name of the satellite or object */
    public String getSatelliteName() { return name; }

    /** @return Country responsible for the object */
    public String getCountry() { return country; }

    /** @return Orbit type (e.g., LEO, GEO) */
    public String getOrbitType() { return orbitType; }

    /** @return Launch year */
    public int getLaunchYear() { return launchYear; }

    /** @return Launch site */
    public String getLaunchSite() { return launchSite; }

    /** @return Current longitude */
    public double getLongitude() { return longitude; }

    /** @return Average longitude */
    public double getAvgLongitude() { return avgLongitude; }

    /** @return Geohash string */
    public String getGeoHash() { return geoHash; }

    /** @return Number of days since launch */
    public int getDaysOld() { return daysOld; }

    public String getHRRCategory() { return hrrCategory; }
    public void setHRRCategory(String hrrCategory) { this.hrrCategory = hrrCategory; }

    public boolean isNominated() { return nominated; }
    public void setNominated(boolean nominated) { this.nominated = nominated; }

    public String getNominatedAt() { return nominatedAt; }
    public void setNominatedAt(String nominatedAt) { this.nominatedAt = nominatedAt; }

    public boolean hasDossier() { return hasDossier; }
    public void setHasDossier(boolean hasDossier) { this.hasDossier = hasDossier; }

    public String getLastUpdatedAt() { return lastUpdatedAt; }
    public void setLastUpdatedAt(String lastUpdatedAt) { this.lastUpdatedAt = lastUpdatedAt; }

    public String getJustification() { return justification; }
    public void setJustification(String justification) { this.justification = justification; }

    public String getFocusedAnalysis() { return focusedAnalysis; }
    public void setFocusedAnalysis(String focusedAnalysis) { this.focusedAnalysis = focusedAnalysis; }

    public int getConjunctionCount() { return conjunctionCount; }
    public void setConjunctionCount(int conjunctionCount) { this.conjunctionCount = conjunctionCount; }

    public boolean isUnknownObject() { return unknownObject; }
    public void setUnknownObject(boolean unknownObject) { this.unknownObject = unknownObject; }

    public int getAllManeuvers() { return allManeuvers; }
    public void setAllManeuvers(int allManeuvers) { this.allManeuvers = allManeuvers; }

    public int getDaysSinceOb() { return daysSinceOb; }
    public void setDaysSinceOb(int daysSinceOb) { this.daysSinceOb = daysSinceOb; }

    public int getRecentManeuvers() { return recentManeuvers; }
    public void setRecentManeuvers(int recentManeuvers) { this.recentManeuvers = recentManeuvers; }

    public double getDeltaV90Day() { return deltaV90Day; }
    public void setDeltaV90Day(double deltaV90Day) { this.deltaV90Day = deltaV90Day; }

    public boolean hasSisterDebris() { return hasSisterDebris; }
    public void setHasSisterDebris(boolean hasSisterDebris) { this.hasSisterDebris = hasSisterDebris; }


    /**
     * Returns a summary of key details about the space object.
     *
     * @return Formatted string containing summary information
     */
    public String getSummary() {
        return recordId + ", " + name + ", " + country + ", " + orbitType + ", " +
                launchYear + ", " + launchSite + ", " + longitude + ", " + avgLongitude + ", " +
                geoHash + ", " + daysOld;
    }

    /**
     * Checks whether the object is still in orbit.
     *
     * @return true if the object is still in orbit; false otherwise
     */
    public boolean isStillInOrbit() {
        return stillInOrbit;
    }

    /**
     * Sets the object's orbit status.
     *
     * @param stillInOrbit true if the object is still in orbit, false otherwise
     */
    public void setStillInOrbit(boolean stillInOrbit) {
        this.stillInOrbit = stillInOrbit;
    }

    /**
     * Gets the risk level associated with this object.
     *
     * @return String representing the risk level
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * Sets the risk level for this object.
     *
     * @param riskLevel A string describing the assessed risk level
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}

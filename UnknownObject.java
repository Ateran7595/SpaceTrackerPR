/**
 * The {@code UnknownObject} class represents an unknown space object. It is a subclass of {@link SpaceObject}
 * and serves as a default or fallback for objects whose type cannot be identified.
 */
public class UnknownObject extends SpaceObject {

    private int conjunctionCount;
    /**
     * Constructs a new {@code UnknownObject} with the specified attributes.
     *
     * @param recordId the unique identifier for the object
     * @param noradId the NORAD catalog number assigned to the object
     * @param satelliteName the name of the object (e.g., satellite)
     * @param country the country of origin for the object
     * @param orbitType the type of orbit the object is in
     * @param objectType the type of object (in this case, "Unknown")
     * @param launchYear the year the object was launched
     * @param launchSite the location from which the object was launched
     * @param longitude the current longitude of the object
     * @param avgLongitude the average longitude of the object
     * @param geoHash the geohash representing the location of the object
     * @param daysOld the number of days since the object was launched
     */
    public UnknownObject(int recordId, int noradId, String satelliteName, String country, String orbitType,
                         String objectType, int launchYear, String launchSite, double longitude,
                         double avgLongitude, String geoHash, int daysOld, int conjunctionCount) {
        super(recordId, noradId, satelliteName, country, orbitType, objectType,
              launchYear, launchSite, longitude, avgLongitude, geoHash, daysOld);
    }


    public int getConjunctionCount() {
        return conjunctionCount;
    }
    
    public void setConjunctionCount(int conjunctionCount) {
        this.conjunctionCount = conjunctionCount;
    }
    /**
     * Returns the type of object as "Unknown".
     *
     * @return the type of object, which is "Unknown"
     */
    @Override
    public String getObjectType() {
        return "Unknown";
    }
}

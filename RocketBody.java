/**
 * The {@code RocketBody} class represents a rocket body in space. It is a subclass of {@link SpaceObject} 
 * and contains information specific to rocket bodies, such as their record ID, NORAD ID, satellite name, 
 * country of origin, orbit type, and other relevant attributes.
 */
public class RocketBody extends SpaceObject {

    /**
     * Constructs a new {@code RocketBody} object with the specified attributes.
     *
     * @param recordId the unique identifier for the rocket body
     * @param noradId the NORAD catalog number assigned to the rocket body
     * @param satelliteName the name of the satellite associated with the rocket body
     * @param country the country of origin for the rocket body
     * @param orbitType the type of orbit the rocket body is in
     * @param objectType the type of object (in this case, "Rocket Body")
     * @param launchYear the year the rocket body was launched
     * @param launchSite the location from which the rocket body was launched
     * @param longitude the current longitude of the rocket body
     * @param avgLongitude the average longitude of the rocket body
     * @param geoHash the geohash representing the location of the rocket body
     * @param daysOld the number of days since the rocket body was launched
     */
    public RocketBody(int recordId, int noradId, String satelliteName, String country, String orbitType,
                      String objectType, int launchYear, String launchSite, double longitude,
                      double avgLongitude, String geoHash, int daysOld) {
        super(recordId, noradId, satelliteName, country, orbitType, objectType,
              launchYear, launchSite, longitude, avgLongitude, geoHash, daysOld);
    }

    /**
     * Returns the type of object as "Rocket Body".
     *
     * @return the type of object, which is "Rocket Body"
     */
    @Override
    public String getObjectType() {
        return "Rocket Body";
    }
}

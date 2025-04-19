/**
 * The {@code Satellite} class represents a satellite in space. It is a subclass of {@link SpaceObject} 
 * and contains information specific to satellites, such as their record ID, NORAD ID, satellite name, 
 * country of origin, orbit type, and other relevant attributes.
 */
public class Satellite extends SpaceObject {

    /**
     * Constructs a new {@code Satellite} object with the specified attributes.
     *
     * @param recordId the unique identifier for the satellite
     * @param noradId the NORAD catalog number assigned to the satellite
     * @param name the name of the satellite
     * @param country the country of origin for the satellite
     * @param orbitType the type of orbit the satellite is in
     * @param objectType the type of object (in this case, "Payload")
     * @param launchYear the year the satellite was launched
     * @param launchSite the location from which the satellite was launched
     * @param longitude the current longitude of the satellite
     * @param avgLongitude the average longitude of the satellite
     * @param geoHash the geohash representing the location of the satellite
     * @param daysOld the number of days since the satellite was launched
     */
    public Satellite(int recordId, int noradId, String name, String country, String orbitType, String objectType,
                     int launchYear, String launchSite, double longitude, double avgLongitude,
                     String geoHash, int daysOld) {
        super(recordId, noradId, name, country, orbitType, objectType, launchYear, launchSite,
              longitude, avgLongitude, geoHash, daysOld);
    }

    /**
     * Returns the type of object as "Payload".
     *
     * @return the type of object, which is "Payload"
     */
    @Override
    public String getObjectType() {
        return "Payload";
    }
}

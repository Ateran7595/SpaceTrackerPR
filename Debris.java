/**
 * The {@code Debris} class represents a space object classified as orbital debris.
 * It extends the {@link SpaceObject} class and implements the {@link Analyzable} interface
 * to support analysis features such as conjunctions and age.
 */
public class Debris extends SpaceObject implements Analyzable {

    /** The number of conjunctions (close approaches) this debris object has had. */
    private int conjunctionCount;

    /**
     * Constructs a new {@code Debris} object with the specified parameters.
     *
     * @param recordId         the unique ID of the record
     * @param noradId          the NORAD catalog ID
     * @param name             the name of the object
     * @param country          the country of origin
     * @param orbitType        the type of orbit (e.g., LEO, GEO)
     * @param objectType       the type of object (should be "DEBRIS")
     * @param launchYear       the year of launch
     * @param launchSite       the launch site name
     * @param longitude        the current longitude of the object
     * @param avgLongitude     the average longitude
     * @param geoHash          the geohash of the object’s location
     * @param daysOld          the number of days since launch
     * @param conjunctionCount the number of recorded conjunctions
     */
    public Debris(int recordId, int noradId, String name, String country, String orbitType, String objectType,
                  int launchYear, String launchSite, double longitude, double avgLongitude,
                  String geoHash, int daysOld, int conjunctionCount) {
        super(recordId, noradId, name, country, orbitType, objectType, launchYear, launchSite,
              longitude, avgLongitude, geoHash, daysOld);
        this.conjunctionCount = conjunctionCount;
    }

    /**
     * Returns the number of conjunctions this debris object has had.
     *
     * @return the conjunction count
     */
    @Override
    public int getConjunctionCount() {
        return conjunctionCount;
    }

    /**
     * Sets the number of conjunctions for this debris object.
     *
     * @param conjunctionCount the new conjunction count
     */
    public void setConjunctionCount(int conjunctionCount) {
        this.conjunctionCount = conjunctionCount;
    }

    /**
     * Returns the object type as a string ("Debris").
     *
     * @return the object type
     */
    @Override
    public String getObjectType() {
        return "Debris";
    }

    /**
     * Returns the number of days since launch.
     */
    @Override
    public int getDaysOld() {
        return super.getDaysOld();
    }

    /**
     * Returns the orbit type.
     */
    @Override
    public String getOrbitType() {
        return super.getOrbitType();
    }

    /**
     * Returns the name of the object (used as satellite name in analysis).
     */
    @Override
    public String getSatelliteName() {
        return super.getSatelliteName(); // assuming SpaceObject has getName()
    }
}

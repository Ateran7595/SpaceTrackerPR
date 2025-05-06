/**
 * Interface representing an object that can be analyzed in terms of its space characteristics.
 * Classes implementing this interface should provide data related to the object's age,
 * conjunction count, orbit type, object type, and satellite name.
 */
public interface Analyzable {

    /**
     * Gets the number of days since the object was launched.
     * 
     * @return the number of days old of the object
     */
    int getDaysOld();

    /**
     * Gets the conjunction count of the object, which represents how many times it has come
     * into close proximity with another object in space.
     * 
     * @return the conjunction count of the object
     */
    int getConjunctionCount();

    /**
     * Gets the type of orbit the object is in.
     * 
     * @return the orbit type as a string (e.g., "LEO", "GEO", etc.)
     */
    String getOrbitType();

    /**
     * Gets the type of the object, such as "Debris", "Satellite", "Rocket Body", etc.
     * 
     * @return the object type as a string
     */
    String getObjectType();

    /**
     * Gets the name of the satellite or space object.
     * 
     * @return the name of the object or satellite
     */
    String getSatelliteName();
}

/**
 * The {@code MissionControl} class represents a mission control center responsible for managing
 * and interacting with the tracking system of space objects.
 * It contains a {@link TrackingSystem} that holds and manages the data of space objects.
 */
public class MissionControl {

    /** The tracking system used to manage space objects. */
    private TrackingSystem trackingSystem = new TrackingSystem();

    /**
     * Retrieves the current tracking system used by the mission control.
     *
     * @return the {@link TrackingSystem} instance associated with the mission control
     */
    public TrackingSystem getTrackingSystem() {
        return trackingSystem;
    }
}

import java.util.*;

public class TrackingSystem {
    private List<SpaceObject> spaceObjects = new ArrayList<>();

    public void addObject(SpaceObject obj) {
        spaceObjects.add(obj);
    }

    public List<SpaceObject> filterByType(String type) {
        List<SpaceObject> result = new ArrayList<>();
        for (SpaceObject obj : spaceObjects) {
            if (obj.getObjectType().equalsIgnoreCase(type)) {
                result.add(obj);
            }
        }
        return result;
    }

    public void displayObjects(String type) {
        List<SpaceObject> objects = filterByType(type);
        if (objects.isEmpty()) {
            System.out.println("No objects found for type: " + type);
            return;
        }
        for (SpaceObject obj : objects) {
            System.out.println(obj.getSummary());
        }
    }

    public void displayObjectsInLEO() {
        List<SpaceObject> leoObjects = new ArrayList<>();
    
        for (SpaceObject obj : getAllObjects()) {
            if (obj.getOrbitType().equalsIgnoreCase("LEO")) {
                leoObjects.add(obj);
            }
        }
    
        if (leoObjects.isEmpty()) {
            System.out.println("No objects found in LEO.");
            return;
        }
    
        for (SpaceObject obj : leoObjects) {
            System.out.println("Record ID: " + obj.getRecordId() +
                    ", Name: " + obj.getSatelliteName() +
                    ", Country: " + obj.getCountry() +
                    ", Orbit Type: " + obj.getOrbitType() +
                    ", Launch Year: " + obj.getLaunchYear() +
                    ", Launch Site: " + obj.getLaunchSite() +
                    ", Longitude: " + obj.getLongitude() +
                    ", Avg Longitude: " + obj.getAvgLongitude() +
                    ", Geohash: " + obj.getGeoHash() +
                    ", Days Old: " + obj.getDaysOld());
        }
    }

    public List<SpaceObject> getAllObjects() {
        return new ArrayList<>(spaceObjects); // return a copy to avoid external modification
    }

}
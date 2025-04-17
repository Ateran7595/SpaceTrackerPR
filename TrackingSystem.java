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

    public void assessOrbitStatusAndExport() {
        int inOrbitCount = 0;
        int exitedCount = 0;
        List<SpaceObject> exitedDebris = new ArrayList<>();
    
        for (SpaceObject obj : spaceObjects) {
            boolean hasOrbitType = obj.getOrbitType() != null && !obj.getOrbitType().equalsIgnoreCase("unknown");
            boolean hasLongitude = obj.getLongitude() != 0.0;
            boolean hasConjunction = obj instanceof Debris && ((Debris) obj).getConjunctionCount() >= 1; // assuming a Debris subclass
    
            boolean stillInOrbit = hasOrbitType && hasLongitude && obj.getDaysOld() < 15000 && hasConjunction;
            obj.setStillInOrbit(stillInOrbit);
    
            double drift = Math.abs(obj.getLongitude() - obj.getAvgLongitude());
            String risk;
            if (drift > 50) risk = "High";
            else if (drift > 10) risk = "Moderate";
            else risk = "Low";
            obj.setRiskLevel(risk);
    
            if (stillInOrbit) inOrbitCount++;
            else {
                exitedCount++;
                exitedDebris.add(obj);
            }
        }
    
        exportToCSV("assessed_debris.csv");
        writeExitedDebrisReport("exited_debris_report.txt", inOrbitCount, exitedCount, exitedDebris);
    }

    private void exportToCSV(String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            // Header
            writer.println("RecordID,Name,Country,OrbitType,ObjectType,LaunchYear,LaunchSite,Longitude,AvgLongitude,GeoHash,DaysOld,StillInOrbit,RiskLevel");
    
            for (SpaceObject obj : spaceObjects) {
                writer.printf("%d,%s,%s,%s,%s,%d,%s,%.2f,%.2f,%s,%d,%b,%s\n",
                        obj.getRecordId(),
                        obj.getSatelliteName(),
                        obj.getCountry(),
                        obj.getOrbitType(),
                        obj.getObjectType(),
                        obj.getLaunchYear(),
                        obj.getLaunchSite(),
                        obj.getLongitude(),
                        obj.getAvgLongitude(),
                        obj.getGeoHash(),
                        obj.getDaysOld(),
                        obj.isStillInOrbit(),
                        obj.getRiskLevel()
                );
            }
    
            System.out.println("CSV export complete.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeExitedDebrisReport(String fileName, int inOrbit, int exited, List<SpaceObject> exitedDebris) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("Orbit Assessment Report");
            writer.println("========================");
            writer.println("In-Orbit Count: " + inOrbit);
            writer.println("Exited Count: " + exited);
            writer.println();
    
            writer.println("Exited Debris Details:");
            for (SpaceObject obj : exitedDebris) {
                writer.printf("Record ID: %d, Name: %s, Country: %s, Orbit Type: %s, Launch Year: %d, Launch Site: %s, Longitude: %.2f, Avg Longitude: %.2f, Geohash: %s, Days Old: %d\n",
                        obj.getRecordId(),
                        obj.getSatelliteName(),
                        obj.getCountry(),
                        obj.getOrbitType(),
                        obj.getLaunchYear(),
                        obj.getLaunchSite(),
                        obj.getLongitude(),
                        obj.getAvgLongitude(),
                        obj.getGeoHash(),
                        obj.getDaysOld()
                );
            }
    
            System.out.println("TXT report written successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
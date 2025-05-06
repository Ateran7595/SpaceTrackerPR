import java.util.*;
import java.io.PrintWriter;

/**
 * The {@code TrackingSystem} class manages a list of tracked space objects.
 * It allows filtering, displaying, and assessing the orbital status of these objects,
 * and can export relevant data to CSV and text files.
 */
public class TrackingSystem {
    private List<SpaceObject> spaceObjects = new ArrayList<>();
    private ImpactAnalysis impactAnalysis = new ImpactAnalysis();

    /**
     * Adds a space object to the tracking system.
     *
     * @param obj the {@link SpaceObject} to add
     */
    public void addObject(SpaceObject obj) {
        spaceObjects.add(obj);
    }

    /**
     * Filters the tracked space objects by their type.
     *
     * @param type the object type to filter by (e.g., "Satellite", "Debris")
     * @return a list of space objects matching the specified type
     */
    public List<SpaceObject> filterByType(String type) {
        List<SpaceObject> result = new ArrayList<>();
        for (SpaceObject obj : spaceObjects) {
            if (obj.getObjectType().equalsIgnoreCase(type)) {
                result.add(obj);
            }
        }
        return result;
    }

    /**
     * Displays all objects of a specified type in the console.
     *
     * @param type the type of object to display
     */
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

    /**
     * Displays all objects currently in Low Earth Orbit (LEO).
     * Prints their full data in a formatted structure.
     */
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

    /**
     * Returns a copy of all tracked space objects.
     *
     * @return a list of all {@link SpaceObject} instances
     */
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
            boolean hasConjunction = true;  // assume true unless Debris
        
            if (obj instanceof Debris) {
                hasConjunction = ((Debris) obj).getConjunctionCount() >= 1;
            }
        
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

    /**
     * Exports all space object data to a CSV file.
     *
     * @param fileName the name of the CSV file to write
     */
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

    /**
     * Writes a report of all objects that have exited orbit to a TXT file.
     *
     * @param fileName      the name of the TXT file
     * @param inOrbit       the count of objects still in orbit
     * @param exited        the count of objects that have exited
     * @param exitedDebris  the list of debris objects that exited orbit
     */
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

    public void generateDensityReport(double minLongitude, double maxLongitude) {
        System.out.printf("\nGenerating density report for longitude range %.2f to %.2f...\n", minLongitude, maxLongitude);
        int count = 0;
    
        System.out.printf("%-10s %-25s %-15s %-12s %-12s %-15s\n", 
            "RecordID", "Satellite Name", "Country", "Orbit Type", 
            "Launch Year", "Object Type");
    
        for (SpaceObject obj : spaceObjects) {
            double lon = obj.getLongitude();
            if (lon >= minLongitude && lon <= maxLongitude) {
                count++;
                System.out.printf("%-10s %-25s %-15s %-12s %-12s %-15s\n", 
                    obj.getRecordId(), obj.getSatelliteName(), obj.getCountry(), 
                    obj.getOrbitType(), obj.getLaunchYear(), obj.getObjectType());
            }
        }
    
        System.out.println("Total objects in range: " + count);
    }

    public void analyzeLongTermImpact() {
        impactAnalysis.analyzeLongTermImpact(spaceObjects); // Delegate the analysis
    }

}

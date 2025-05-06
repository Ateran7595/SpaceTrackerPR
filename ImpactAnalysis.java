import java.util.List;

public class ImpactAnalysis {

    /**
     * Analyzes the long-term impact of objects in Low Earth Orbit (LEO).
     * Specifically looks for objects with Days Old > 200 and Conjunction Count > 0.
     * 
     * @param spaceObjects the list of space objects to analyze
     */
    public void analyzeLongTermImpact(List<SpaceObject> spaceObjects) {
        System.out.println("Analyzing Long-Term Impact for LEO objects...");
        System.out.printf("%-10s %-25s %-12s %-12s %-15s %-10s %-20s%n",
            "RecordID", "Satellite Name", "Country", "Orbit Type", "Object Type", "Days Old", "Conjunction Count");

        int count = 0; // Counter to track matches
        for (SpaceObject obj : spaceObjects) {
            if (obj.getOrbitType().equalsIgnoreCase("LEO") &&
                obj.getDaysOld() > 200 &&
                obj.getConjunctionCount() > 0) {

                count++;
                System.out.printf("%-10s %-25s %-12s %-12s %-15s %-10d %-20d%n",
                    obj.getRecordId(), obj.getSatelliteName(), obj.getCountry(),
                    obj.getOrbitType(), obj.getObjectType(), obj.getDaysOld(), obj.getConjunctionCount());
            }
        }

        if (count == 0) {
            System.out.println("No LEO objects found with Days Old > 200 and Conjunction Count > 0.");
        }
    }
}

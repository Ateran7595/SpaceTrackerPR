import java.util.*;

public class DebrisDensityAnalysis {
    public void generateReport(List<SpaceObject> objects) {
        System.out.println("Generating debris density report...");
        // Group by orbit type
        Map<String, Integer> densityMap = new HashMap<>();
        for (SpaceObject obj : objects) {
            densityMap.put(obj.orbitType, densityMap.getOrDefault(obj.orbitType, 0) + 1);
        }
        densityMap.forEach((k, v) -> System.out.println(k + ": " + v + " objects"));
    }
}

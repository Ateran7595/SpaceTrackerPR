import java.util.*;

/**
 * The {@code DebrisDensityAnalysis} class provides functionality to analyze and report
 * the density of space debris across different orbit types.
 */
public class DebrisDensityAnalysis {

    /**
     * Generates and prints a report of the number of space objects in each orbit type.
     *
     * <p>This method iterates through a list of {@link SpaceObject} instances, groups them
     * by their {@code orbitType}, and prints the count of objects in each orbit category.</p>
     *
     * @param objects the list of space objects to analyze
     */
    public void generateReport(List<SpaceObject> objects) {
        System.out.println("Generating debris density report...");

        // Group by orbit type
        Map<String, Integer> densityMap = new HashMap<>();
        for (SpaceObject obj : objects) {
            densityMap.put(obj.orbitType, densityMap.getOrDefault(obj.orbitType, 0) + 1);
        }

        // Output results
        densityMap.forEach((k, v) -> System.out.println(k + ": " + v + " objects"));
    }
}

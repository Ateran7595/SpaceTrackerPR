import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.*;
/**
 * The {@code CSVReader} class is responsible for reading satellite and debris data
 * from a CSV file and populating a {@link TrackingSystem} with the parsed objects.
 */
public class CSVReader {

    public void loadObjects(String filename, TrackingSystem system) throws IOException {
        // Use FileInputStream and InputStreamReader to ensure proper encoding handling
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String headerLine = br.readLine();
            if (headerLine == null) return;

            // Remove BOM (Byte Order Mark) if it exists at the start of the line
            headerLine = headerLine.replaceAll("^[\\ufeff]", ""); // Removes BOM if present

            // Clean the header line by trimming any leading/trailing spaces
            String[] headers = headerLine.split(",", -1);
            Map<String, Integer> columnIndex = new HashMap<>();

            // Map headers to their indices for easy access
            for (int i = 0; i < headers.length; i++) {
                columnIndex.put(headers[i].trim().toLowerCase(), i); // Case insensitive match
            }

            // Debug: Print column mappings
            //System.out.println("Column Index Map: " + columnIndex);

            String line;
            while ((line = br.readLine()) != null) {
                // Remove BOM from each line (if any)
                line = line.replaceAll("^[\\ufeff]", ""); // Remove BOM if present in the line

                // Parse the line using a regular expression that accounts for commas inside quotes
                String[] parts = parseCSVLine(line);

                // Debug: Print parsed line (check if the parts are split correctly)
                //System.out.println("Parsed Line: " + Arrays.toString(parts));  // Debug output

                try {
                    int recordId = getInt(parts, columnIndex, "record_id");
                    int noradId = getInt(parts, columnIndex, "norad_cat_id");
                    String name = getStr(parts, columnIndex, "satellite_name");
                    String country = getStr(parts, columnIndex, "country");
                    String orbitType = getStr(parts, columnIndex, "approximate_orbit_type");
                    String objectType = getStr(parts, columnIndex, "object_type");
                    int launchYear = getInt(parts, columnIndex, "launch_year");
                    String launchSite = getStr(parts, columnIndex, "launch_site");
                    double longitude = getDouble(parts, columnIndex, "longitude");
                    double avgLongitude = getDouble(parts, columnIndex, "avg_longitude");
                    String geoHash = getStr(parts, columnIndex, "geohash");
                    int daysOld = getInt(parts, columnIndex, "days_old");
                    int conjunctionCount = getInt(parts, columnIndex, "conjunction_count");

                    // Debug: Show what's being parsed
                    // System.out.printf("Parsed: ID=%d, DaysOld=%d, Name=%s, Type=%s%n",
                    //         recordId, daysOld, name, objectType);

                    // Create the SpaceObject based on the object type
                    SpaceObject obj;
                    switch (objectType.toUpperCase()) {
                        case "DEBRIS":
                            obj = new Debris(recordId, noradId, name, country, orbitType, objectType,
                                    launchYear, launchSite, longitude, avgLongitude, geoHash, daysOld, conjunctionCount);
                            break;
                        case "PAYLOAD":
                            obj = new Satellite(recordId, noradId, name, country, orbitType, objectType,
                                    launchYear, launchSite, longitude, avgLongitude, geoHash, daysOld, conjunctionCount);
                            break;
                        case "ROCKET BODY":
                            obj = new RocketBody(recordId, noradId, name, country, orbitType, objectType,
                                    launchYear, launchSite, longitude, avgLongitude, geoHash, daysOld, conjunctionCount);
                            break;
                        default:
                            obj = new UnknownObject(recordId, noradId, name, country, orbitType, objectType,
                                    launchYear, launchSite, longitude, avgLongitude, geoHash, daysOld, conjunctionCount);
                            break;
                    }

                    // Add object to the tracking system
                    system.addObject(obj);
                } catch (Exception e) {
                    System.err.println("Skipping invalid line: " + line);
                    e.printStackTrace(); // Optional: for deeper debugging
                }
            }
        }
    }

    private int getInt(String[] parts, Map<String, Integer> map, String key) {
        String value = getStr(parts, map, key);
        // Debug: Show the value retrieved for the key
        //System.out.println("Retrieved value for " + key + ": " + value); // Debug line
        try {
            return value.isEmpty() ? 0 : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.err.printf("Warning: Could not parse integer for key '%s': '%s'%n", key, value);
            return 0;
        }
    }

    private String getStr(String[] parts, Map<String, Integer> map, String key) {
        Integer idx = map.get(key.toLowerCase());
        return (idx != null && idx < parts.length) ? parts[idx].trim() : "";
    }

    private double getDouble(String[] parts, Map<String, Integer> map, String key) {
        String value = getStr(parts, map, key);
        try {
            return value.isEmpty() ? 0.0 : Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.err.printf("Warning: Could not parse double for key '%s': '%s'%n", key, value);
            return 0.0;
        }
    }

    /**
     * Handles proper parsing of a CSV line to deal with commas inside quotes.
     */
    private String[] parseCSVLine(String line) {
        // Use a regular expression to split by commas, respecting commas inside quotes
        String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        return line.split(regex, -1); // Ensure it handles trailing commas too
    }
}

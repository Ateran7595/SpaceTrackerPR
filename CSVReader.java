import java.io.*;

public class CSVReader {
    public void loadObjects(String filename, TrackingSystem system) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine(); // Skip header

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",", -1); // -1 includes trailing empty values

            try {
                int recordId = Integer.parseInt(parts[0]);
                int noradId = Integer.parseInt(parts[1]);
                String name = parts[2];
                String country = parts[3];
                String orbitType = parts[4];
                String objectType = parts[5];
                int launchYear = Integer.parseInt(parts[6]);
                String launchSite = parts[7];
                double longitude = parts[8].isEmpty() ? 0.0 : Double.parseDouble(parts[8]);
                double avgLongitude = parts[9].isEmpty() ? 0.0 : Double.parseDouble(parts[9]);
                String geoHash = parts[10];
                int daysOld = parts[19].isEmpty() ? 0 : Integer.parseInt(parts[19]);
                int conjunctionCount = parts.length > 20 && !parts[20].isEmpty() ? Integer.parseInt(parts[20]) : 0;

                SpaceObject obj;

                switch (objectType.toUpperCase()) {
                    case "DEBRIS":
                        obj = new Debris(recordId, noradId, name, country, orbitType, objectType,
                                launchYear, launchSite, longitude, avgLongitude, geoHash, daysOld, conjunctionCount);
                        break;
                    case "PAYLOAD":
                        obj = new Satellite(recordId, noradId, name, country, orbitType, objectType,
                                launchYear, launchSite, longitude, avgLongitude, geoHash, daysOld);
                        break;
                    case "ROCKET BODY":
                        obj = new RocketBody(recordId, noradId, name, country, orbitType, objectType,
                                launchYear, launchSite, longitude, avgLongitude, geoHash, daysOld);
                        break;
                    default:
                        obj = new UnknownObject(recordId, noradId, name, country, orbitType, objectType,
                                launchYear, launchSite, longitude, avgLongitude, geoHash, daysOld);
                        break;
                }

                system.addObject(obj);

            } catch (Exception e) {
                System.out.println("Skipping invalid line: " + line);
            }
        }

        br.close();
    }
}

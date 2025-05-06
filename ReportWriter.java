import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportWriter {
    // Returns the updated report to user whenever he exits the program
    public static void writeUpdatedReport(List<SpaceObject> objects, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write CSV header
            writer.append("country,satellite_name,approximate_orbit_type,record_id,object_type,launch_year,launch_site,longitude,avg_longitude,geohash,HRR_Category,is_nominated,nominated_at,norad_cat_id,has_dossier,last_updated_at,justification,focused_analysis,days_old,conjunction_count,is_unk_object,all_maneuvers,days_since_ob,recent_maneuvers,deltaV_90day,has_sister_debris\n");

            // Write each object's data row
            for (SpaceObject obj : objects) {
                writer.append(safe(obj.getCountry())).append(",")
                      .append(safe(obj.getSatelliteName())).append(",")
                      .append(safe(obj.getOrbitType())).append(",")
                      .append(String.valueOf(obj.getRecordId())).append(",")
                      .append(safe(obj.getObjectType())).append(",")
                      .append(String.valueOf(obj.getLaunchYear())).append(",")
                      .append(safe(obj.getLaunchSite())).append(",")
                      .append(String.valueOf(obj.getLongitude())).append(",")
                      .append(String.valueOf(obj.getAvgLongitude())).append(",")
                      .append(safe(obj.getGeoHash())).append(",")

                      // HRR_Category: only default to "Blue" if null or empty
                      
                      .append(safeOrDefault(obj.getHRRCategory(), "Blue")).append(",")

                      // is_nominated
                      .append(obj.isNominated() ? "TRUE" : "FALSE").append(",")

                      // nominated_at
                      .append(safeOrDefault(obj.getNominatedAt(), "2023-01-01T00:00:00.000Z")).append(",")

                      // norad_cat_id (same as recordId)
                      .append(String.valueOf(obj.getRecordId())).append(",")

                      // has_dossier
                      .append(obj.hasDossier() ? "TRUE" : "FALSE").append(",")

                      // last_updated_at
                      .append(safeOrDefault(obj.getLastUpdatedAt(), "2023-01-01T00:00:00.000Z")).append(",")

                      // justification
                      .append(safeOrDefault(obj.getJustification(), "-")).append(",")

                      // focused_analysis
                      .append(safeOrDefault(obj.getFocusedAnalysis(), "-")).append(",")

                      // days_old
                      .append(String.valueOf(obj.getDaysOld())).append(",")

                      // conjunction_count
                      .append(String.valueOf(obj.getConjunctionCount())).append(",")

                      // is_unk_object
                      .append(obj.isUnknownObject() ? "TRUE" : "FALSE").append(",")

                      // all_maneuvers
                      .append(String.valueOf(obj.getAllManeuvers())).append(",")

                      // days_since_ob
                      .append(String.valueOf(obj.getDaysSinceOb())).append(",")

                      // recent_maneuvers
                      .append(String.valueOf(obj.getRecentManeuvers())).append(",")

                      // deltaV_90day
                      .append(String.valueOf(obj.getDeltaV90Day())).append(",")

                      // has_sister_debris
                      .append(obj.hasSisterDebris() ? "TRUE" : "FALSE").append("\n");
            }

            System.out.println("Updated debris tracking report written to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing updated report: " + e.getMessage());
        }
    }

    // Helper method to handle nulls safely
    private static String safe(String value) {
        return value != null ? value : "";
    }

    // Helper method with default fallback
    private static String safeOrDefault(String value, String defaultValue) {
        return (value == null || value.isEmpty()) ? defaultValue : value;
    }
}

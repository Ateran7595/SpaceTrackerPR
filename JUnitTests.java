import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import org.junit.jupiter.api.Test;

class JUnitTests {

    @Test
    void testLoadDataFromCSV() {
        assertDoesNotThrow(() -> {
            RunSimulation.testableLoadDataFromCSV();
        }, "Loading CSV data should not throw any exception");
    }

    @Test
    void testGetInt_ValidData() {
        String[] parts = {"1", "123", "456"};
        Map<String, Integer> columnIndex = Map.of("record_id", 0, "norad_cat_id", 1);

        CSVReader reader = new CSVReader();
        int result = reader.getInt(parts, columnIndex, "record_id");
        assertEquals(1, result, "getInt should correctly parse the integer value.");
    }

    @Test
    void testIsValidUserType() {
        assertTrue(UserManager.isValidUserType("admin"));
        assertTrue(UserManager.isValidUserType("scientist"));
        assertFalse(UserManager.isValidUserType("guest"));
    }

    @Test
    void testGetStr_MissingValue() {
        String[] parts = {"", "123", "456"};
        Map<String, Integer> columnIndex = Map.of("satellite_name", 0);

        CSVReader reader = new CSVReader();
        String result = reader.getStr(parts, columnIndex, "satellite_name");
        assertEquals("", result, "getStr should return an empty string for missing values.");
    }

    @Test
    void testGetDouble_InvalidData() {
        String[] parts = {"a", "123.45", "78.90"};
        Map<String, Integer> columnIndex = Map.of("longitude", 0);

        CSVReader reader = new CSVReader();
        double result = reader.getDouble(parts, columnIndex, "longitude");
        assertEquals(0.0, result, "getDouble should return 0.0 for invalid double data.");
    }
}

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class JUnitTests {

    @Test
    void testLoadDataFromCSV() {
        // This will call the load method and should not throw an exception
        assertDoesNotThrow(() -> {
            RunSimulation.testableLoadDataFromCSV();
        }, "Loading CSV data should not throw any exception");
    }
}

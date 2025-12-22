package iso2.Exe3;

import ISO2.Exe3.Domain.ActivityRecommender;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class ActivityRecommenderTest {

    private final ActivityRecommender recommender = new ActivityRecommender();

    // ===========================================================================
    // Converted ParameterizedTest to Loop
    // Source: Health Check: Healthy={0}, Symptoms={1} -> Should Block
    // ===========================================================================
    @Test
    public void testHealthRejection() {
        // Data: {healthy, symptoms}
        boolean[][] scenarios = {
            {false, false},
            {true, true},
            {false, true}
        };

        for (boolean[] scenario : scenarios) {
            boolean healthy = scenario[0];
            boolean symptoms = scenario[1];

            List<String> result = recommender.getRecommendation(healthy, symptoms, 20.0, 50, false, false, false);

            assertEquals("Failed for healthy=" + healthy + ", symptoms=" + symptoms, 1, result.size());
            assertEquals("Not permitted to participate due to health status", result.get(0));
        }
    }

    @Test
    public void testStayHome() {
        // DisplayName: Stay Home: Extreme weather logic
        List<String> result = recommender.getRecommendation(true, false, -5.0, 10, true, false, false);
        
        assertTrue(result.contains("Stay at home"));
        assertEquals("Should only recommend staying at home", 1, result.size());
    }

    @Test
    public void testSkiingSuccess() {
        // DisplayName: Skiing: Ideal conditions
        List<String> result = recommender.getRecommendation(true, false, -5.0, 10, false, false, false);
        
        assertTrue(result.contains("Skiing"));
    }

    @Test
    public void testSkiingCapacityFull() {
        // DisplayName: Skiing: Blocked by Capacity
        List<String> result = recommender.getRecommendation(true, false, -5.0, 10, false, false, true);
        
        assertFalse(result.contains("Skiing"));
        assertTrue(result.isEmpty());
    }

    // ===========================================================================
    // Converted ParameterizedTest to Loop
    // Source: Hiking Boundary: Temp={0}
    // ===========================================================================
    @Test
    public void testHikingSuccess() {
        double[] temps = { 0.0, 10.0, 15.0 };

        for (double temp : temps) {
            List<String> result = recommender.getRecommendation(true, false, temp, 40, false, false, false);
            assertTrue("Failed for temp: " + temp, result.contains("Hiking or Climbing"));
        }
    }

    @Test
    public void testHikingRain() {
        // DisplayName: Hiking: Blocked by Rain
        List<String> result = recommender.getRecommendation(true, false, 10.0, 40, true, false, false);
        assertFalse(result.contains("Hiking or Climbing"));
    }

    @Test
    public void testSpringBaseCase() {
        // DisplayName: Spring: MC/DC Base Case (All True)
        List<String> result = recommender.getRecommendation(true, false, 20.0, 50, false, false, false);
        assertTrue(result.contains("Spring/Summer/Autumn Catalog Activity"));
    }

    // ===========================================================================
    // Converted ParameterizedTest to Loop
    // Source: Spring MC/DC Failures
    // Data: {temp, precip, cloudy, hum, reason}
    // ===========================================================================
    @Test
    public void testSpringFailures() {
        Object[][] scenarios = {
            {15.0, false, false, 50, "Temp too low (Boundary)"},
            {25.1, false, false, 50, "Temp too high"},
            {20.0, true,  false, 50, "Rain blocking"},
            {20.0, false, true,  50, "Clouds blocking"},
            {20.0, false, false, 61, "Humidity too high"}
        };

        for (Object[] row : scenarios) {
            double temp = (double) row[0];
            boolean precip = (boolean) row[1];
            boolean cloudy = (boolean) row[2];
            int hum = (int) row[3];
            String reason = (String) row[4];

            List<String> result = recommender.getRecommendation(true, false, temp, hum, precip, cloudy, false);
            assertFalse("Failed due to: " + reason, result.contains("Spring/Summer/Autumn Catalog Activity"));
        }
    }

    @Test
    public void testOverlapSuccess() {
        // DisplayName: Overlap: Temp 33 (Cultural + Beach + Pool)
        List<String> result = recommender.getRecommendation(true, false, 33.0, 50, false, false, false);

        // JUnit 4 does not support assertAll. We run asserts sequentially.
        assertTrue("Missing Cultural", result.contains("Cultural or Gastronomic Activities"));
        assertTrue("Missing Beach", result.contains("Go to the Beach"));
        assertTrue("Missing Pool", result.contains("Go to the Swimming Pool"));
        assertEquals(3, result.size());
    }

    @Test
    public void testOverlapCapacityFull() {
        // DisplayName: Overlap: Temp 33 with Capacity Full
        List<String> result = recommender.getRecommendation(true, false, 33.0, 50, false, false, true);

        // Replaced assertAll with sequential asserts
        assertFalse("Cultural should be blocked", result.contains("Cultural or Gastronomic Activities"));
        assertFalse("Pool should be blocked", result.contains("Go to the Swimming Pool"));
        assertTrue("Beach should act as fallback", result.contains("Go to the Beach"));
        
        assertEquals(1, result.size());
    }

    @Test
    public void testHeatWave() {
        List<String> result = recommender.getRecommendation(true, false, 40.0, 50, false, false, false);

        assertFalse(result.contains("Cultural or Gastronomic Activities"));
        assertTrue(result.contains("Go to the Beach"));
        assertTrue(result.contains("Go to the Swimming Pool"));
    }
}
package iso2.Exe3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ISO2.Exe3.Domain.ActivityRecommender;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivityRecommenderTest {

    private final ActivityRecommender recommender = new ActivityRecommender();
    
    @ParameterizedTest(name = "Health Check: Healthy={0}, Symptoms={1} -> Should Block")
    @CsvSource({
        "false, false",
        "true, true",
        "false, true"
    })
    void testHealthRejection(boolean healthy, boolean symptoms) {
        List<String> result = recommender.getRecommendation(healthy, symptoms, 20.0, 50, false, false, false);

        assertEquals(1, result.size());
        assertEquals("Not permitted to participate due to health status", result.get(0));
    }
    @Test
    @DisplayName("Stay Home: Extreme weather logic")
    void testStayHome() {
        List<String> result = recommender.getRecommendation(true, false, -5.0, 10, true, false, false);
        
        assertTrue(result.contains("Stay at home"));
        assertEquals(1, result.size(), "Should only recommend staying at home");
    }

    @Test
    @DisplayName("Skiing: Ideal conditions")
    void testSkiingSuccess() {
        List<String> result = recommender.getRecommendation(true, false, -5.0, 10, false, false, false);
        
        assertTrue(result.contains("Skiing"));
    }

    @Test
    @DisplayName("Skiing: Blocked by Capacity")
    void testSkiingCapacityFull() {
        List<String> result = recommender.getRecommendation(true, false, -5.0, 10, false, false, true);
        
        assertFalse(result.contains("Skiing"));
        assertTrue(result.isEmpty());
    }

    @ParameterizedTest(name = "Hiking Boundary: Temp={0}")
    @CsvSource({
        "0.0",
        "10.0",
        "15.0"
    })
    void testHikingSuccess(double temp) {
        List<String> result = recommender.getRecommendation(true, false, temp, 40, false, false, false);
        assertTrue(result.contains("Hiking or Climbing"));
    }

    @Test
    @DisplayName("Hiking: Blocked by Rain")
    void testHikingRain() {
        List<String> result = recommender.getRecommendation(true, false, 10.0, 40, true, false, false);
        assertFalse(result.contains("Hiking or Climbing"));
    }

    @Test
    @DisplayName("Spring: MC/DC Base Case (All True)")
    void testSpringBaseCase() {
        List<String> result = recommender.getRecommendation(true, false, 20.0, 50, false, false, false);
        assertTrue(result.contains("Spring/Summer/Autumn Catalog Activity"));
    }

    @ParameterizedTest(name = "Spring MC/DC Failures: {4}")
    @CsvSource({
        "15.0, false, false, 50, Temp too low (Boundary)",
        "25.1, false, false, 50, Temp too high",
        "20.0, true,  false, 50, Rain blocking",
        "20.0, false, true,  50, Clouds blocking",
        "20.0, false, false, 61, Humidity too high"
    })
    void testSpringFailures(double temp, boolean precip, boolean cloudy, int hum, String reason) {
        List<String> result = recommender.getRecommendation(true, false, temp, hum, precip, cloudy, false);
        assertFalse(result.contains("Spring/Summer/Autumn Catalog Activity"), "Failed due to: " + reason);
    }

    @Test
    @DisplayName("Overlap: Temp 33 (Cultural + Beach + Pool)")
    void testOverlapSuccess() {
        List<String> result = recommender.getRecommendation(true, false, 33.0, 50, false, false, false);

        assertAll("Overlap Check",
            () -> assertTrue(result.contains("Cultural or Gastronomic Activities"), "Missing Cultural"),
            () -> assertTrue(result.contains("Go to the Beach"), "Missing Beach"),
            () -> assertTrue(result.contains("Go to the Swimming Pool"), "Missing Pool")
        );
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Overlap: Temp 33 with Capacity Full")
    void testOverlapCapacityFull() {
        List<String> result = recommender.getRecommendation(true, false, 33.0, 50, false, false, true);

        assertAll("Capacity Check",
            () -> assertFalse(result.contains("Cultural or Gastronomic Activities"), "Cultural should be blocked"),
            () -> assertFalse(result.contains("Go to the Swimming Pool"), "Pool should be blocked"),
            () -> assertTrue(result.contains("Go to the Beach"), "Beach should act as fallback")
        );
        assertEquals(1, result.size());
    }

    @Test
    void testHeatWave() {
        List<String> result = recommender.getRecommendation(true, false, 40.0, 50, false, false, false);

        assertFalse(result.contains("Cultural or Gastronomic Activities"));
        assertTrue(result.contains("Go to the Beach"));
        assertTrue(result.contains("Go to the Swimming Pool"));
    }
}

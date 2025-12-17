package ISO2.Exe3.Domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ActivityRecommenderTest {

    ActivityRecommender recommender = new ActivityRecommender();

    @Test
    void testHealthRejection() {
        List<String> result = recommender.getRecommendation(false, false, 20.0, 50, false, false, false);
        assertTrue(result.contains("Not permitted to participate due to health status"));
        assertEquals(1, result.size());
    }

    @Test
    void testSymptomsRejection() {
        List<String> result = recommender.getRecommendation(true, true, 20.0, 50, false, false, false);
        assertTrue(result.contains("Not permitted to participate due to health status"));
    }

    @Test
    void testStayHomeConditions() {
        List<String> result = recommender.getRecommendation(true, false, -5.0, 10, true, false, false);
        assertTrue(result.contains("Stay at home"));
    }

    @Test
    void testSkiingSuccess() {
        List<String> result = recommender.getRecommendation(true, false, -5.0, 10, false, false, false);
        assertTrue(result.contains("Skiing"));
    }

    @Test
    void testHikingSuccess() {
        List<String> result = recommender.getRecommendation(true, false, 10.0, 40, false, false, false);
        assertTrue(result.contains("Hiking or Climbing"));
    }

    @Test
    void testSpringSummerSuccess() {
        List<String> result = recommender.getRecommendation(true, false, 20.0, 50, false, false, false);
        assertTrue(result.contains("Spring/Summer/Autumn Catalog Activity"));
    }

    @Test
    void testCulturalSuccess() {
        List<String> result = recommender.getRecommendation(true, false, 28.0, 50, false, false, false);
        assertTrue(result.contains("Cultural or Gastronomic Activities"));
    }

    @Test
    void testBeachAndPool() {
        List<String> result = recommender.getRecommendation(true, false, 33.0, 50, false, false, false);
        assertAll(
            () -> assertTrue(result.contains("Cultural or Gastronomic Activities"), "Should contain Cultural"),
            () -> assertTrue(result.contains("Go to the Beach"), "Should contain Beach"),
            () -> assertTrue(result.contains("Go to the Swimming Pool"), "Should contain Pool")
        );
    }

    @ParameterizedTest
    @CsvSource({
        "10.0, 50, true, false, false, 0",
        "20.0, 50, false, true, false, 0",
        "20.0, 80, false, false, false, 0",
        "33.0, 50, false, false, true, 1"
    })
    void testSpecificConstraints(double temp, int hum, boolean precip, boolean cloud, boolean cap, int expectedCount) {
        List<String> result = recommender.getRecommendation(true, false, temp, hum, precip, cloud, cap);

        if (temp == 33.0 && cap) {
            assertTrue(result.contains("Go to the Beach"));
            assertFalse(result.contains("Go to the Swimming Pool"));
            assertFalse(result.contains("Cultural or Gastronomic Activities"));
            assertEquals(1, result.size());
        } else {
            assertEquals(expectedCount, result.size());
        }
    }
}

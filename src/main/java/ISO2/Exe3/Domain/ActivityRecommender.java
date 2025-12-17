package iso2.exe3.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * ActivityRecommender provides activity suggestions based on health and weather.
 */
public class ActivityRecommender {

    /** Low humidity threshold (percent). */
    private static final int LOW_HUMIDITY = 15;

    /** Maximum temperature considered suitable for hiking (°C). */
    private static final int HIKING_MAX_TEMP = 15;

    /** Maximum temperature considered spring-like activity (°C). */
    private static final int SPRING_MAX_TEMP = 25;

    /** Maximum temperature for cultural activities (°C). */
    private static final int CULTURAL_MAX_TEMP = 35;

    /** Maximum humidity allowed for spring activities (percent). */
    private static final int SPRING_MAX_HUMIDITY = 60;

    /** Minimum temperature considered suitable for beach activities (°C). */
    private static final int BEACH_MIN_TEMP = 30;

    /**
     * Returns a list of recommended activities for the provided health and
     * weather parameters.
     *
     * @param healthy whether the participant is healthy
     * @param symptoms whether the participant has symptoms
     * @param temp current temperature in degrees Celsius
     * @param humidity current humidity as percentage
     * @param precip whether there is precipitation
     * @param cloudy whether it is cloudy
     * @param capacityExceeded whether capacity limits have been exceeded
     * @return a list of recommended activity descriptions (may be empty)
     */
    public List<String> getRecommendation(final boolean healthy,
                                          final boolean symptoms,
                                          final double temp,
                                          final int humidity,
                                          final boolean precip,
                                          final boolean cloudy,
                                          final boolean capacityExceeded) {

        final List<String> recommendations = new ArrayList<>();

        if (!healthy || symptoms) {
            recommendations.add("Not permitted to participate due to health status");
            return recommendations;
        }

        if (temp < 0 && humidity < LOW_HUMIDITY && precip) {
            recommendations.add("Stay at home");
            return recommendations;
        }

        if (temp < 0 && humidity < LOW_HUMIDITY && !precip && !capacityExceeded) {
            recommendations.add("Skiing");
        }

        if (temp >= 0 && temp <= HIKING_MAX_TEMP && !precip && !capacityExceeded) {
            recommendations.add("Hiking or Climbing");
        }

        if (temp > HIKING_MAX_TEMP && temp <= SPRING_MAX_TEMP && !precip && !cloudy && humidity <= SPRING_MAX_HUMIDITY) {
            recommendations.add("Spring/Summer/Autumn Catalog Activity");
        }

        if (temp > SPRING_MAX_TEMP && temp <= CULTURAL_MAX_TEMP && !precip && !capacityExceeded) {
            recommendations.add("Cultural or Gastronomic Activities");
        }

        if (temp > BEACH_MIN_TEMP && !precip) {
            recommendations.add("Go to the Beach");
            if (!capacityExceeded) {
                recommendations.add("Go to the Swimming Pool");
            }
        }

        return recommendations;
    }
}

package ISO2.Exe3.Domain;

import java.util.ArrayList;
import java.util.List;

public class ActivityRecommender {

    public List<String> getRecommendation(boolean healthy, boolean symptoms, double temp,
                                          int humidity, boolean precip, boolean cloudy,
                                          boolean capacityExceeded) {

        List<String> recommendations = new ArrayList<>();

        // 1. Health rule: if not healthy or has symptoms -> reject
        if (!healthy || symptoms) {
            recommendations.add("Not permitted to participate due to health status");
            return recommendations;
        }

        // 2. Stay at home: extreme conditions
        if (temp < 0 && humidity < 15 && precip) {
            recommendations.add("Stay at home");
            return recommendations;
        }

        // 3. Skiing
        if (temp < 0 && humidity < 15 && !precip && !capacityExceeded) {
            recommendations.add("Skiing");
        }

        // 4. Hiking or Climbing
        if (temp >= 0 && temp <= 15 && !precip && !capacityExceeded) {
            recommendations.add("Hiking or Climbing");
        }

        // 5. Spring/Summer/Autumn Catalog Activity
        if (temp > 15 && temp <= 25 && !precip && !cloudy && humidity <= 60) {
            recommendations.add("Spring/Summer/Autumn Catalog Activity");
        }

        // 6. Cultural or Gastronomic Activities
        if (temp > 25 && temp <= 35 && !precip && !capacityExceeded) {
            recommendations.add("Cultural or Gastronomic Activities");
        }

        // 7. Beach and Pool
        if (temp > 30 && !precip) {
            recommendations.add("Go to the Beach");
            if (!capacityExceeded) {
                recommendations.add("Go to the Swimming Pool");
            }
        }

        return recommendations;
    }
}

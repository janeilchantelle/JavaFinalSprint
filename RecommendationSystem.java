import java.util.ArrayList;
import java.util.List;
public class RecommendationSystem {
    private static final int MIN_HEART_RATE = 60;
    private static final int MAX_HEART_RATE = 100;
    private static final int MIN_STEPS = 10000;

    public List<String> generateRecommendations(HealthData healthData) {
        List<String> recommendations = new ArrayList<>();

        // Analyze heart rate
        int heartRate = healthData.getHeartRate();
        if (heartRate < MIN_HEART_RATE) {
            recommendations.add("Your heart rate is lower than the recommended range. " +
                    "Consider increasing your physical activity to improve your cardiovascular health.");
        } else if (heartRate > MAX_HEART_RATE) {
            recommendations.add("Your heart rate is higher than the recommended range. " +
                    "Consider consulting with a healthcare professional.");
        } else {
            recommendations.add("Your heart rate is within the recommended range. Keep it up!");
        }

        // Analyze steps
        int steps = healthData.getSteps();
        if (steps < MIN_STEPS) {
            recommendations.add("You're not reaching the recommended daily step count. " +
                    "Try to incorporate more walking or other physical activities into your daily routine.");
        } else {
            recommendations.add("Great job on meeting the recommended daily step count! Keep moving!");
        }

        // Analyze weight
        double weight = healthData.getWeight();
        if (weight < 50) {
            recommendations.add("Your weight seems to be lower than the recommended range for your height. " +
                    "Consider consulting with a healthcare professional or nutritionist.");
        } else if (weight > 100) {
            recommendations.add("Your weight seems to be higher than the recommended range for your height. " +
                    "Consider consulting with a healthcare professional or nutritionist.");
        } else {
            recommendations.add("Your weight is within the healthy range. Keep it up!");
        }

        // Analyze height
        double height = healthData.getHeight();
        if (height < 100) {
            recommendations.add("Your height seems to be lower than the average. " +
                    "Consult with a healthcare professional for advice.");
        } else if (height > 220) {
            recommendations.add("Your height seems to be higher than the average. " +
                    "Consult with a healthcare professional for advice.");
        } else {
            recommendations.add("Your height is within the normal range. Good job!");
        }


        return recommendations;
    }
}

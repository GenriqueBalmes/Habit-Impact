public class ImpactAnalyzer {
    // Customizable impact multiplier based on habit type
    public int calculateImpactScore(Habit habit) {
        int multiplier = 10; // Default multiplier

        // Customize multiplier based on habit type
        if (habit instanceof ScreentimeHabit) {
            multiplier = 5;  // Screen time might have a different multiplier
        } else if (habit instanceof Food) {
            multiplier = 8;  // Food habit might have a higher multiplier
        }

        return habit.getDailyScore() * multiplier; // Impact score is daily score multiplied by the habit's impact factor
    }
}

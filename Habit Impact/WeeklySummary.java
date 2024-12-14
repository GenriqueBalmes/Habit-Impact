import java.util.ArrayList;

public class WeeklySummary {
    private ArrayList<Integer> dailyScores;

    // Constructor
    public WeeklySummary() {
        dailyScores = new ArrayList<>();
    }

    // Add daily score
    public void addDailyScore(int score) {
        dailyScores.add(score);
    }

    // Get total score
    public int getTotalScore() {
        int total = 0;
        for (int score : dailyScores) {
            total += score;
        }
        return total;
    }

    // Get average score
    public double getAverageScore() {
        if (dailyScores.isEmpty()) return 0;
        return getTotalScore() / (double) dailyScores.size();
    }

    // Display weekly summary
    public void displaySummary() {
        System.out.println("📊 Weekly Summary:");
        if (dailyScores.isEmpty()) {
            System.out.println("No scores logged this week.");
        } else {
            for (int i = 0; i < dailyScores.size(); i++) {
                System.out.println("Day " + (i + 1) + ": " + dailyScores.get(i) + " points");
            }
            int total = getTotalScore();
            double average = getAverageScore();
            System.out.printf("\nTotal score: %d points\n", total);
            System.out.printf("Average score: %.2f points per day\n", average);
        }
    }

    // Reset weekly summary
    public void resetSummary() {
        dailyScores.clear();
        System.out.println("Weekly summary has been reset.");
    }
}


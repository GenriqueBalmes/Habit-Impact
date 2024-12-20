import java.util.Scanner;

public abstract class Habit {
    private String name;
    private int dailyScore;

    public Habit(String name) {
        this.name = name;
        this.dailyScore = 0;
    }

    public String getName() {
        return name;
    }

    public int getDailyScore() {
        return dailyScore;
    }

    public void setDailyScore(int dailyScore) {
        this.dailyScore = dailyScore;
    }

    public abstract void logHabit(Scanner scanner);

    public abstract String provideFeedback();
}

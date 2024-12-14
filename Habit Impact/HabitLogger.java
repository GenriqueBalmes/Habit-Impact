import java.util.ArrayList;

public class HabitLogger {
    private ArrayList<Habit> habits;

    public HabitLogger() {
        habits = new ArrayList<>();
    }

    public void addHabit(Habit habit) {
        habits.add(habit);
    }

    public void displayHabits() {
        for (Habit habit : habits) {
            System.out.println(habit.getName() + ": " + habit.provideFeedback());
        }
    }
}

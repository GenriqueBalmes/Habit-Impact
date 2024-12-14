import java.util.Scanner;

public class ScreentimeHabit extends Habit {
    public ScreentimeHabit() {
        super("Screen Time Habit");
    }

    @Override
    public void logHabit(Scanner scanner) {
        System.out.print("Enter hours of screen time today (e.g., 3.5 for 3 hours 30 minutes): ");
        if (scanner.hasNextDouble()) {
            double hours = scanner.nextDouble();
            if (hours < 0) {
                System.out.println("Invalid input: Screen time cannot be negative.");
                setDailyScore(0);
            } else if (hours <= 2) {
                setDailyScore(10);
                System.out.println("Great job! You kept your screen time low.");
            } else if (hours <= 4) {
                setDailyScore(7);
                System.out.println("Good job! Consider reducing screen time further.");
            } else {
                setDailyScore(5);
                System.out.println("Try to limit your screen time for better well-being.");
            }
        } else {
            System.out.println("Invalid input. Setting default score of 0.");
            setDailyScore(0);
            scanner.nextLine(); // Consume invalid input
        }
    }

    @Override
    public String provideFeedback() {
        return "Consider reducing screen time to improve well-being and save energy.";
    }
}

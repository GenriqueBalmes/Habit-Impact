import java.util.Scanner;

public class HydrationHabit extends Habit {
    public HydrationHabit() {
        super("Hydration Habit");
    }

    @Override
    public void logHabit(Scanner scanner) {
        System.out.print("Enter the number of glasses of water you drank today: ");
        if (scanner.hasNextInt()) {
            int glasses = scanner.nextInt();
            if (glasses >= 8) {
                setDailyScore(10); // Maximum score for sufficient hydration
            } else if (glasses >= 5) {
                setDailyScore(7); // Moderate hydration score
            } else {
                setDailyScore(5); // Low hydration score
            }
            System.out.println("You logged " + glasses + " glasses of water.");
        } else {
            System.out.println("Invalid input. Setting default score of 0.");
            setDailyScore(0);
            scanner.nextLine(); // Consume invalid input
        }
    }

    @Override
    public String provideFeedback() {
        return "Great job staying hydrated! Remember to drink enough water for optimal health.";
    }
}

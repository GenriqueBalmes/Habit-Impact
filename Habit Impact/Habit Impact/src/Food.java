import java.util.Scanner;

public class Food extends Habit {
    public Food() {
        super("Food Habit");
    }

    @Override
    public void logHabit(Scanner scanner) {
        System.out.print("Enter your meal type (1: Vegetarian, 2: Meat-based, 3: Mixed): ");
        int choice = scanner.nextInt();
        int score = 0;
        if (choice == 1) {
            score = 10;
        } else if (choice == 2) {
            score = 5;
        } else if (choice == 3) {
            score = 7;
        } else {
            System.out.println("Invalid choice. Setting default score of 0.");
        }
        setDailyScore(score);
    }

    @Override
    public String provideFeedback() {
        return "Consider incorporating more plant-based meals to reduce your carbon footprint!";
    }
}

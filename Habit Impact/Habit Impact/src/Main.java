import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create instances for habit tracking, goal tracking, and weekly summary
        HabitLogger habitLogger = new HabitLogger();
        WeeklySummary weeklySummary = new WeeklySummary();
        GoalTracker goalTracker = new GoalTracker("");

        // Establish database connection
        Connection connection = DBConnection.getConnection();
        if (connection != null) {
            System.out.println("Connected to the MySQL database successfully!");
        } else {
            System.out.println("Failed to connect to the MySQL database.");
        }

        System.out.println("Welcome to Habit Impact!");

        boolean running = true;
        while (running) {
            // Main menu UI
            displayMainMenu();

            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number between 1 and 6.");
                scanner.nextLine(); // Consume invalid input
                continue;
            }

            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1: // Log a Habit
                    logHabit(scanner, habitLogger, weeklySummary);
                    break;
                case 2: // View Weekly Summary
                    weeklySummary.displaySummary();
                    break;
                case 3: // Set a Goal
                    setGoal(scanner, goalTracker);
                    break;
                case 4: // Check Goal Status
                    checkGoalStatus(goalTracker);
                    break;
                case 5: // Reset Weekly Summary
                    weeklySummary.resetSummary();
                    break;
                case 6: // Exit the Program
                    running = false;
                    System.out.println("Thank you for using Habit Impact! Stay healthy and mindful!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a number between 1 and 6.");
            }
        }

        scanner.close(); // Close the scanner resource
    }

    private static void displayMainMenu() {
        System.out.println("\n=========================");
        System.out.println("Choose an option:");
        System.out.println("1. Log a Habit");
        System.out.println("2. View Weekly Summary");
        System.out.println("3. Set a Goal");
        System.out.println("4. Check Goal Status");
        System.out.println("5. Reset Weekly Summary");
        System.out.println("6. Exit");
        System.out.println("=========================");
    }

    private static void logHabit(Scanner scanner, HabitLogger habitLogger, WeeklySummary weeklySummary) {
        System.out.println("Choose a habit to log:");
        System.out.println("1. Food Habit");
        System.out.println("2. Screen Time");
        System.out.println("3. Hydration");

        int habitChoice = -1;
        try {
            habitChoice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a number between 1 and 3.");
            scanner.nextLine(); // Consume invalid input
            return;
        }

        scanner.nextLine(); // Consume newline character
        Habit habit = createHabit(habitChoice);

        if (habit != null) {
            habit.logHabit(scanner); // Pass scanner instance for habit logging
            habitLogger.addHabit(habit); // Add habit to logger
            weeklySummary.addDailyScore(habit.getDailyScore()); // Update weekly summary
            System.out.println("Feedback: " + habit.provideFeedback());
        }
    }

    private static Habit createHabit(int habitChoice) {
        switch (habitChoice) {
            case 1:
                return new Food();
            case 2:
                return new ScreentimeHabit();
            case 3:
                return new HydrationHabit();
            default:
                System.out.println("Invalid choice. Returning to main menu.");
                return null;
        }
    }

    private static void setGoal(Scanner scanner, GoalTracker goalTracker) {
        System.out.print("Enter your goal (e.g., reach 70 points this week): ");
        String goal = scanner.nextLine();
        goalTracker.setGoal(goal); // Set a new goal
        System.out.println("Goal set: " + goal);
    }

    private static void checkGoalStatus(GoalTracker goalTracker) {
        if (goalTracker.getGoal() != null && !goalTracker.getGoal().isEmpty()) {
            System.out.println("Your current goal is: " + goalTracker.getGoal());
        } else {
            System.out.println("No goal has been set.");
        }
    }
}

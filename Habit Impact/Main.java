import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HabitLogger habitLogger = new HabitLogger();
        WeeklySummary weeklySummary = new WeeklySummary();
        GoalTracker goalTracker = new GoalTracker("Goal");
        ImpactAnalyzer impactAnalyzer = new ImpactAnalyzer();

        System.out.println("Welcome to Habit Impact!");
        boolean running = true;

        while (running) {
            // Main menu UI
            System.out.println("\n=========================");
            System.out.println("Choose an option:");
            System.out.println("1. Log a Habit");
            System.out.println("2. View Weekly Summary");
            System.out.println("3. Set a Goal");
            System.out.println("4. Check Goal Status");
            System.out.println("5. Reset Weekly Summary");
            System.out.println("6. Exit");
            System.out.println("=========================");

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
                case 1:
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
                        continue;
                    }

                    scanner.nextLine(); // Consume newline character

                    Habit habit = null;

                    // Switch case for each habit
                    switch (habitChoice) {
                        case 1:
                            habit = new Food();
                            break;
                        case 2:
                            habit = new ScreentimeHabit();
                            break;
                        case 3:
                            habit = new HydrationHabit();
                            break;
                        default:
                            System.out.println("Invalid choice. Returning to main menu.");
                            continue;
                    }

                    if (habit != null) {
                        habit.logHabit(scanner); // Pass scanner instance for habit logging
                        habitLogger.addHabit(habit); // Add habit to logger
                        weeklySummary.addDailyScore(habit.getDailyScore()); // Update weekly summary
                        System.out.println("Feedback: " + habit.provideFeedback());

                        // Display the impact score for the habit
                        int impactScore = impactAnalyzer.calculateImpactScore(habit);
                        System.out.println("Impact Score for this habit: " + impactScore);
                    }
                    break;

                case 2:
                    weeklySummary.displaySummary(); // Display the summary of logged habits
                    break;

                case 3:
                    System.out.print("Enter your goal (e.g., reach 70 points this week): ");
                    String goal = scanner.nextLine();
                    goalTracker = new GoalTracker(goal); // Set a new goal
                    System.out.println("Goal set: " + goalTracker.getGoal());
                    break;

                case 4:
                    if (goalTracker != null) {
                        System.out.println("Your current goal is: " + goalTracker.getGoal());
                    } else {
                        System.out.println("No goal has been set.");
                    }
                    break;

                case 5:
                    weeklySummary.resetSummary(); // Reset the weekly summary
                    break;

                case 6:
                    running = false; // Exit the program
                    System.out.println("Thank you for using Habit Impact! Stay healthy and mindful!");
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a number between 1 and 6.");
            }
        }

        scanner.close(); // Close the scanner resource
    }
}

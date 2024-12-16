#Habit Impact
##Project Overview
Habit Impact is a Java-based console application designed to encourage users to develop healthier habits. By allowing users to log daily habits, view weekly summaries, and set personal goals, the application promotes accountability and mindfulness. The program aligns with Sustainable Development Goal (SDG) 3: Good Health and Well-being, aiming to foster healthier lifestyles and improve well-being.

#Object-Oriented Programming Principles
This project demonstrates the core principles of Object-Oriented Programming (OOP):

1. Encapsulation
Data fields in the classes, such as dailyScore in the Habit class, are private to restrict direct access.
Public getter and setter methods are used to access and modify private fields safely.
2. Inheritance
The abstract class Habit is the parent class, with subclasses like Food, HydrationHabit, and ScreentimeHabit inheriting shared attributes and behaviors.
This promotes code reuse and establishes a hierarchical relationship between general and specific habit types.
3. Polymorphism
Method overriding is applied in the subclasses to provide specific implementations of the logHabit and provideFeedback methods.
This allows the program to handle different habit types uniformly while enabling specific behavior for each habit.
4. Abstraction
The abstract Habit class defines common properties and behaviors for all habits, while leaving the implementation of certain methods, such as logHabit and provideFeedback, to the subclasses.
This hides implementation details while exposing essential functionalities.

#Sustainable Development Goal (SDG) Integration
SDG 3: Good Health and Well-being
This project contributes to SDG 3 by:

Promoting healthy eating habits through the Food Habit logging feature.
Encouraging hydration by tracking water intake.
Reducing screen time to improve mental and physical well-being.
The program fosters long-term behavioral change by providing users with feedback and motivation to maintain healthier lifestyles.

#Instructions for Running the Program
To run this program, you need to have Java Development Kit (JDK) 8 or higher installed on your system, along with Visual Studio Code and the Java Extension Pack.

First, clone the repository to your local machine using a terminal in Visual Studio Code. Once you have the repository URL, use the command git clone <repository-url> to copy the project to your system.

Navigate to the project folder by using the cd command, such as cd habit-impact. Open the folder in Visual Studio Code by typing code . in the terminal.

Next, make sure the Java files are compiled. You can do this by running the command javac Main.java in the terminal. Alternatively, you can use the "Run and Debug" feature in Visual Studio Code to compile the code automatically.

Once the program is compiled, run it by typing java Main in the terminal. Alternatively, you can use the "Run" button in Visual Studio Code to execute the program directly.

When the program starts, a menu will appear with options to log habits, view summaries, set goals, and reset data. Follow the prompts to interact with the application.

If you encounter any issues running the program, ensure your Java environment is properly configured in Visual Studio Code. For detailed guidance on setting up Java in Visual Studio Code, refer to the official documentation or reach out to your instructor for assistance.

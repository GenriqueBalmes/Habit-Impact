-- Create the habits table
CREATE TABLE habits (
    habit_id INT AUTO_INCREMENT PRIMARY KEY,
    habit_name VARCHAR(255) NOT NULL,
    description TEXT,
    category VARCHAR(100)
);

-- Create the habit_logs table
CREATE TABLE habit_logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    habit_id INT NOT NULL,
    log_date DATE NOT NULL,
    status ENUM('completed', 'incomplete', 'skipped') NOT NULL,
    note TEXT,
    FOREIGN KEY (habit_id) REFERENCES habits(habit_id) ON DELETE CASCADE
);

-- Create the goals table
CREATE TABLE goals (
    goal_id INT AUTO_INCREMENT PRIMARY KEY,
    habit_id INT NOT NULL,
    goal_value INT NOT NULL,
    target_date DATE NOT NULL,
    FOREIGN KEY (habit_id) REFERENCES habits(habit_id) ON DELETE CASCADE
);

-- Insert initial data for habits table
INSERT INTO habits (habit_name, description, category)
VALUES 
    ('Morning Exercise', 'Engage in physical activities like jogging or yoga in the morning.', 'Health'),
    ('Reading', 'Spend time reading books or articles for personal growth.', 'Learning'),
    ('Hydration', 'Drink at least 8 glasses of water every day.', 'Health');

-- Insert initial data for habit_logs table
INSERT INTO habit_logs (habit_id, log_date, status, note)
VALUES
    (1, '2024-12-20', 'completed', 'Completed morning jog for 30 minutes.'),
    (2, '2024-12-20', 'skipped', 'No time for reading today.'),
    (3, '2024-12-20', 'completed', 'Drank 8 glasses of water.');

-- Insert initial data for goals table
INSERT INTO goals (habit_id, goal_value, target_date)
VALUES
    (1, 30, '2024-12-31'),
    (2, 20, '2024-12-31'),
    (3, 8, '2024-12-31');

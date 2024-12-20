-- Drop database if exists and create new one
DROP DATABASE IF EXISTS habit_impact;
CREATE DATABASE habit_impact;
USE habit_impact;

-- Users table
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP,
    CONSTRAINT chk_email CHECK (email REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

-- Habit types table
CREATE TABLE habit_types (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    max_daily_score INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Habits table
CREATE TABLE habits (
    habit_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    type_id INT NOT NULL,
    date DATE NOT NULL,
    score INT NOT NULL,
    value DECIMAL(10,2),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (type_id) REFERENCES habit_types(type_id),
    CONSTRAINT chk_score CHECK (score >= 0 AND score <= 10),
    UNIQUE KEY unique_habit_day (user_id, type_id, date)
);

-- Goals table
CREATE TABLE goals (
    goal_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    type_id INT NOT NULL,
    target_score INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    achieved BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (type_id) REFERENCES habit_types(type_id),
    CONSTRAINT chk_dates CHECK (end_date >= start_date)
);

-- Weekly summaries table
CREATE TABLE weekly_summaries (
    summary_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    week_start_date DATE NOT NULL,
    total_score INT NOT NULL DEFAULT 0,
    average_score DECIMAL(5,2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    UNIQUE KEY unique_user_week (user_id, week_start_date)
);

-- Insert initial habit types
INSERT INTO habit_types (name, description, max_daily_score) VALUES
('Food', 'Tracking dietary habits and their environmental impact', 10),
('Screen Time', 'Monitoring daily screen time usage', 10),
('Hydration', 'Tracking daily water intake', 10);

-- Insert sample user
INSERT INTO users (username, email, password_hash) VALUES
('demo_user', 'demo@example.com', SHA2('demo123', 256));

-- Insert sample goals
INSERT INTO goals (user_id, type_id, target_score, start_date, end_date) VALUES
(1, 1, 70, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY));

-- Create view for weekly progress
CREATE VIEW weekly_progress AS
SELECT 
    u.username,
    ht.name as habit_type,
    h.date,
    WEEK(h.date) as week_number,
    AVG(h.score) as average_score,
    SUM(h.score) as total_score
FROM habits h
JOIN users u ON h.user_id = u.user_id
JOIN habit_types ht ON h.type_id = ht.type_id
GROUP BY u.username, ht.name, WEEK(h.date)
ORDER BY h.date DESC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitDAO {
    private Connection connection;

    public HabitDAO() {
        this.connection = DBConnection.getConnection();
    }

    // Create: Log a new habit
    public boolean logHabit(int userId, int typeId, int score, double value, String notes) {
        String sql = "INSERT INTO habits (user_id, type_id, date, score, value, notes) VALUES (?, ?, CURDATE(), ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, typeId);
            pstmt.setInt(3, score);
            pstmt.setDouble(4, value);
            pstmt.setString(5, notes);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error logging habit: " + e.getMessage());
            return false;
        }
    }

    // Read: Retrieve all habits for a user
    public List<HabitRecord> getAllHabits(int userId) {
        String sql = "SELECT h.habit_id, h.date, h.score, h.value, h.notes, ht.name AS habit_name " +
                "FROM habits h JOIN habit_types ht ON h.type_id = ht.type_id WHERE h.user_id = ? ORDER BY h.date DESC";
        List<HabitRecord> habits = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                habits.add(new HabitRecord(
                        rs.getInt("habit_id"),
                        rs.getString("habit_name"),
                        rs.getDate("date"),
                        rs.getInt("score"),
                        rs.getDouble("value"),
                        rs.getString("notes")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving habits: " + e.getMessage());
        }
        return habits;
    }

    // Update: Modify an existing habit
    public boolean updateHabit(int habitId, int score, double value, String notes) {
        String sql = "UPDATE habits SET score = ?, value = ?, notes = ? WHERE habit_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, score);
            pstmt.setDouble(2, value);
            pstmt.setString(3, notes);
            pstmt.setInt(4, habitId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating habit: " + e.getMessage());
            return false;
        }
    }

    // Delete: Remove a habit
    public boolean deleteHabit(int habitId) {
        String sql = "DELETE FROM habits WHERE habit_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, habitId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting habit: " + e.getMessage());
            return false;
        }
    }

    // Record class for data transfer
    public static class HabitRecord {
        private int id;
        private String habitName;
        private Date date;
        private int score;
        private double value;
        private String notes;

        public HabitRecord(int id, String habitName, Date date, int score, double value, String notes) {
            this.id = id;
            this.habitName = habitName;
            this.date = date;
            this.score = score;
            this.value = value;
            this.notes = notes;
        }

        // Getters
        public int getId() {
            return id;
        }

        public String getHabitName() {
            return habitName;
        }

        public Date getDate() {
            return date;
        }

        public int getScore() {
            return score;
        }

        public double getValue() {
            return value;
        }

        public String getNotes() {
            return notes;
        }
    }
}

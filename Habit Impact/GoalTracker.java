public class GoalTracker {
    private String goal;
    private boolean isGoalMet;

    public GoalTracker(String goal) {
        this.goal = goal;
        this.isGoalMet = false;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoalMet(boolean isGoalMet) {
        this.isGoalMet = isGoalMet;
    }

    public boolean checkGoalStatus() {
        return isGoalMet;
    }
}

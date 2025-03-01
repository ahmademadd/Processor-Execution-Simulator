public class Task implements Comparable<Task> {
    private final String id;
    private final int creationTime;
    private final int executionTime;
    private int assignedTime;
    private final boolean priority;

    Task(String id, int creationTime, int executionTime, boolean priority) {
        this.id = id;
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
        if (priority != other.priority)
            return Boolean.compare(other.priority, priority); // Higher priority first
        if (executionTime != other.executionTime)
            return Integer.compare(other.executionTime, executionTime); // Shorter execution time first
        return this.id.compareTo(other.id); // Tie-breaker based on task name
    }

    public int getCreationTime() {
        return creationTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public boolean isPriority() {
        return priority;
    }

    public String getId() {
        return id;
    }

    public int getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(int assignedTime) {
        this.assignedTime = assignedTime;
    }
}

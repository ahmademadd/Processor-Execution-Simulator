public class Processor extends Thread implements Comparable<Processor> {
    private final String id;
    private Task currentTask;
    private final Clock clock = Clock.getInstance();
    private volatile boolean available = true;
    private int lastCycle = 1;

    Processor(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (clock.isAlive()) {
            if (available && clock.getCurrentCycle() > lastCycle) {
                System.out.println("Processor '" + id + "' is idle at cycle 'C" + lastCycle + "'");
                lastCycle = clock.getCurrentCycle();
            }
            if (!available) {
                System.out.println("Processor '" + id + "' is executing task '" + currentTask.getId() + "' at cycle 'C" + clock.getCurrentCycle() + "'");
                executing();
                System.out.println("Processor '" + id + "' finished executing task '" + currentTask.getId() + "' at cycle 'C" + clock.getCurrentCycle() + "'");
                available = true;
                lastCycle = clock.getCurrentCycle();
            }
        }
    }

    private void executing() {
        while (currentTask.getExecutionTime() != clock.getCurrentCycle() - currentTask.getAssignedTime()) {Thread.yield();}
    }

    public void assign(Task currentTask) {
        if (available) {
            this.currentTask = currentTask;
            currentTask.setAssignedTime(clock.getCurrentCycle());
            available = false;
        }
    }

    @Override
    public int compareTo(Processor other) {
        if (this.available != other.available)
            return Boolean.compare(other.available, this.available); // Prioritize available processors
        return Integer.compare(Integer.parseInt(id.substring(1)), Integer.parseInt(other.id.substring(1))); // If both are available, prioritize lower ID
    }

    public String getProcessorId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public Task getCurrentTask() {
        return currentTask;
    }
}
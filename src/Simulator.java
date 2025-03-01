import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

public class Simulator extends Thread {
    private static Simulator instance;
    private final PriorityBlockingQueue<Task> tasks = new PriorityBlockingQueue<>();
    private final PriorityBlockingQueue<Processor> processor = new PriorityBlockingQueue<>();
    private final Clock clock;
    private final Scheduler scheduler;
    private final String taskInfoPath;

    private Simulator(int numOfProcessors, int numOfCycles, String taskInfoPath) {
        this.taskInfoPath = taskInfoPath;
        clock = Clock.getInstance(numOfCycles);
        scheduler = Scheduler.getInstance(processor, tasks);
        for (int i = 1; i <= numOfProcessors; i++)
            processor.put(new Processor("P" + i));
    }

    @Override
    public void run() {
        clock.start();
        for (Processor processor : processor)
            processor.start();
        scheduler.start();
        assignTasks();
    }

    public void assignTasks() {
        try (Scanner scanner = new Scanner(new File(taskInfoPath))) {
            int taskNumber = 0;
            List<Task> batch = new ArrayList<>(); // Temporary list for batch insertion
            int lastCreationTime = -1;

            while (scanner.hasNextLine()) {
                int creationTime = scanner.nextInt();
                int executionTime = scanner.nextInt();
                boolean priority = scanner.nextInt() == 1;

                if (lastCreationTime != -1 && creationTime != lastCreationTime) {
                    tasks.addAll(batch);
                    batch.clear();
                }
                // Wait until clock matches creation time
                while (clock.getCurrentCycle() != creationTime) {Thread.yield();}

                // Add to batch for simultaneous insertion
                batch.add(new Task("T" + ++taskNumber, creationTime, executionTime, priority));
                System.out.println("Task 'T" + taskNumber + "' is created at cycle 'C" + clock.getCurrentCycle() + "'");

                lastCreationTime = creationTime;
            }
            // Final batch insert
            if (!batch.isEmpty()) {
                tasks.addAll(batch);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    public static Simulator getInstance(int numOfProcessors, int numOfCycles, String taskInfoPath) {
        if (instance == null)
            return instance = new Simulator(numOfProcessors, numOfCycles, taskInfoPath);
        return instance;
    }

    public static Simulator getInstance() {
        return instance;
    }
}
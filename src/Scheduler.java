import java.util.concurrent.PriorityBlockingQueue;

public class Scheduler extends Thread{
    private static Scheduler instance;
    private Clock clock = Clock.getInstance();
    private volatile PriorityBlockingQueue<Processor> processor;
    private volatile PriorityBlockingQueue<Task> tasks;

    private Scheduler(PriorityBlockingQueue<Processor> processors, PriorityBlockingQueue<Task> tasks) {
        this.processor = processors;
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (clock.isAlive()) {
            processor.add(processor.remove());
            if (processor.peek().isAvailable() && !tasks.isEmpty()) {
                processor.peek().assign(tasks.remove());
            }
        }
    }

    public synchronized static Scheduler getInstance(PriorityBlockingQueue<Processor> processors, PriorityBlockingQueue<Task> tasks) {
        if (instance == null)
            return instance = new Scheduler(processors, tasks);
        return instance;
    }

    public static Scheduler getInstance() {
        return instance;
    }
}
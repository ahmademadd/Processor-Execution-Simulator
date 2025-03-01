public class Clock extends Thread {
    private static Clock instance;
    private final int cycles;
    private volatile int currentCycle = 0;

    private Clock(int cycles) {
        this.cycles = cycles;
    }

    public static Clock getInstance() {
        return instance;
    }

    public synchronized static Clock getInstance(int cycles) {
        if (instance == null)
            return instance = new Clock(++cycles);
        return instance;
    }

    @Override
    public void run() {
        while (currentCycle != cycles) {
            try {
                currentCycle++;
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int getCurrentCycle() {
        return currentCycle;
    }

    public int getCycles() {
        return cycles;
    }
}
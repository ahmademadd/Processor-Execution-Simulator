import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                
                Enter the following arguments to run the simulation of a multithreaded processing unit
                The first argument is an integer, which specifies the number of processors.
                The second argument is an integer, which defines the duration of the simulation which represents the total number of clock cycles in the simulation.
                The third argument is a string, which is a path to the text file that contains tasks’ information""");

        Scanner scanner = new Scanner(System.in);
        String arguments = scanner.nextLine();
        String[] argsArray = arguments.split(" ");

        int numOfProcessors = Integer.parseInt(argsArray[0]);
        int numOfCycles = Integer.parseInt(argsArray[1]);
        String taskInfoPath = argsArray[2];

        Simulator simulator = Simulator.getInstance(numOfProcessors, numOfCycles, taskInfoPath);
        simulator.start();
    }
}
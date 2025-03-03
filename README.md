# Processor Execution Simulator

_By Ahmad Emad_  
- **YouTube Video**: [Watch Here](https://youtu.be/2ebH247v2pE)  
- **Documentation**: [Click Here](Processor-Execution-Simulator-Documentation.pdf).
- **Email**: [ahmademad995.ae@gmail.com](mailto:ahmademad995.ae@gmail.com)

## Introduction
The **Processor Execution Simulator** is a multithreaded simulation of processor execution for tasks. It models a synchronized set of processors executing tasks based on priority and execution time. The simulator processes input from a text file describing tasks and outputs a detailed cycle-by-cycle execution report.

## Features
- Fixed number of processors throughout the simulation.
- Synchronized clock shared across all processors.
- Unique identifiers for processors (`P1, P2, P3, ...`), clock cycles (`C1, C2, C3, ...`), and tasks (`T1, T2, T3, ...`).
- Each processor executes only **one task at a time**.
- Tasks are **queued upon creation** and scheduled based on priority followed by execution time.
- **Scheduler prioritization:**
  - **High-priority** tasks (`1`) are assigned before low-priority tasks (`0`).
  - If multiple high-priority tasks exist, the **longest execution time** task is chosen.
  - If still tied, a **random task** is selected.
- **Task execution is non-preemptive** (once started, it must complete before switching).
- **Immediate task assignment** (if a processor is free at task creation cycle, it starts execution immediately).
- **Console-based report** detailing task creation, execution, and completion per cycle.

## Input Format
The simulator requires three command-line arguments:
1. **Number of processors** (integer)
2. **Total number of clock cycles** (integer)
3. **Path to tasks' details file** (string)

### **Task Input File Format**
Each line in the input file represents a task with three space-separated integers:
```
<creation_time> <execution_time> <priority>
```
- `creation_time`: Clock cycle when the task is created.
- `execution_time`: Number of cycles required to complete execution.
- `priority`: `1` (high priority) or `0` (low priority).

#### **Example Input File**
```
1 4 0
1 3 1
1 5 1
3 4 1
4 1 0
5 3 0
```

## Output
The simulator prints a **detailed cycle-by-cycle report** to the console, showing key events:
- **Task Creation:** When a task enters the queue.
- **Task Assignment:** When a task starts execution on a processor.
- **Task Completion:** When a task finishes execution.
- **Processor State:** Idle processors and which tasks are running on which processors.

#### **Example Output**
```
Task 'T1' is created at cycle 'C1'
Task 'T2' is created at cycle 'C1'
Task 'T3' is created at cycle 'C1'
Processor 'P1' is executing task 'T3' at cycle 'C1'
Processor 'P2' is executing task 'T2' at cycle 'C1'
Task 'T4' is created at cycle 'C3'
Task 'T5' is created at cycle 'C4'
Processor 'P2' finished executing task 'T2' at cycle 'C4'
Processor 'P2' is executing task 'T4' at cycle 'C4'
Task 'T6' is created at cycle 'C5'
Processor 'P1' finished executing task 'T3' at cycle 'C6'
Processor 'P1' is executing task 'T1' at cycle 'C6'
Processor 'P2' finished executing task 'T4' at cycle 'C8'
Processor 'P2' is executing task 'T6' at cycle 'C8'
Processor 'P1' finished executing task 'T1' at cycle 'C10'
Processor 'P1' is executing task 'T5' at cycle 'C10'
Processor 'P2' finished executing task 'T6' at cycle 'C11'
Processor 'P1' finished executing task 'T5' at cycle 'C11'
```

## Usage
Compile and run the simulator using:
```sh
javac Main.java
java Main <num_processors> <total_cycles> <task_file>
```
#### **Example Command**
```sh
java Simulator 2 10 tasks.txt
```

## Implementation Details
- The simulator uses a **priority queue** to manage task scheduling.
- **Synchronization mechanisms** ensure tasks with the same creation time are added before processing.
- **Multithreading** is used for simulation and scheduling.

## Notes
- The report format is **optimized for readability**.
- The simulator does **not write reports to a file**, only console output.
- Tasks are processed **in real-time**, adhering to the given scheduling rules.

## Author
- **Ahmad Emad**

## License
This project is open-source and free to use under the MIT License.


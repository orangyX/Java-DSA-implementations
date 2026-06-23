package scheduler;

import java.util.ArrayList;
import java.util.List;
import queue.Queue;

public class Scheduler implements SchedulerAttributes{
    private final Queue<Process> processQueue;
    private final List<Process> completedQueue = new ArrayList<>();
    private int elapsed = 0;

    public Scheduler(Queue<Process> processQueue) {
        this.processQueue = processQueue;
    }

    @Override
    public void run() {
        while (processQueue.size() > 0) {
            Process front = processQueue.front();
            
            if (front.isFinished()) {
                front.setTime(elapsed);
                completedQueue.add(front);
                processQueue.dequeue();
                continue;
            }

            int thisSlice = front.getSlice() < front.getTimeRemaining() ? front.getSlice() : front.getTimeRemaining();
            front.execute(thisSlice);
            
            processQueue.dequeue();
            processQueue.enqueue(front);
            elapsed += thisSlice;
        }
    }

    @Override
    public boolean idleState() {
        return processQueue.size() == 0;
    }

    public List<Process> completed() {
        return new ArrayList<>(completedQueue);
    }

    // TEST FUNCTIONS
    public void printProcesses() {
        for (int i = 0; i < completedQueue.size(); i++) {
            Process process = completedQueue.get(i);
            System.out.println(process.getName() + ": " + process.getFinishTime());
        }
    }
}
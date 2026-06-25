package scheduler_test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import queue.Queue;
import scheduler.Process;
import scheduler.Scheduler;

public class SchedulerTest {
    Scheduler scheduler;
    Random random;

    @BeforeEach
    void setUp() {
        random = new Random();
    }

    @Test
    @DisplayName("Completed is 0 on instantiation")
    void instantiationYieldZeroElapsed() {
        scheduler = new Scheduler(new Queue<>());

        assertEquals(scheduler.completed().size(), 0);
    }

    @Test
    @DisplayName("Scheduler is idle on size 0")
    void schedulerIdlesOnSizeZero() {
        scheduler = new Scheduler(new Queue<>());

        assertTrue(scheduler.idleState());
    }

    @Test
    @DisplayName("All tasks complete")
    void schedulerEmptiesQueue() {
        Queue<Process> processQueue = new Queue<>();
        int size = random.nextInt(1, 100);
        Process[] processList = new Process[size];
        
        for (int i = 0; i < size; i++) {
            processList[i] = new Process("A" + random.nextInt(1, 26), random.nextInt(1, 999), random.nextInt(5, 99));
            processQueue.enqueue(processList[i]);
        }

        scheduler = new Scheduler(processQueue);
        scheduler.run();
        var completedQueue = scheduler.completed();

        assertEquals(processQueue.size(), 0);
        assertTrue(scheduler.idleState());
    }

    @Test
    @DisplayName("Process completed times align")
    void enqueuedProcessesCompleteAtCorrectTime() {
        Queue<Process> processQueue = new Queue<>();
        Process P1 = new Process("A", 5, 2);
        Process P2 = new Process("B", 3, 2);
        Process P3 = new Process("C", 4, 3);

        processQueue.enqueue(P1);
        processQueue.enqueue(P2);
        processQueue.enqueue(P3);

        scheduler = new Scheduler(processQueue);
        scheduler.run();

        assertEquals(P1.getFinishTime(), 12);
        assertEquals(P2.getFinishTime(), 10);
        assertEquals(P3.getFinishTime(), 11);
    }
}
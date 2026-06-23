package scheduler_test;

import java.util.List;
import java.util.ArrayList;

import queue.Queue;
import scheduler.Process;
import scheduler.Scheduler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

public class SchedulerTest {

    private Queue<Process> q;
    private Scheduler s;

    @BeforeEach
    void setUp() {
        q = new Queue<>();
        s = new Scheduler(q);
        q.enqueue(new Process("A", 150, 8));
        q.enqueue(new Process("B", 223, 7));
        q.enqueue(new Process("C", 83, 11));
        q.enqueue(new Process("D", 27, 12));
        q.enqueue(new Process("E", 14, 2));
        q.enqueue(new Process("F", 10, 5));
        q.enqueue(new Process("G", 39, 8));
        q.enqueue(new Process("H", 289, 30));
        q.enqueue(new Process("I", 109, 15));
        q.enqueue(new Process("J", 133, 15));
    }

    @Test
    @DisplayName("Scheduler empties upon completion")
    void schedulerEmptiesUponCompletion() {
        s.run();
        s.printProcesses();
        assertTrue(s.idleState(), "Case 1 failed");
    }

    @Test
    @DisplayName("Zero time elapsed on zero")
    void zeroTimeElapsed() {
        Queue<Process> queueZero = new Queue<>();

        queueZero.enqueue(new Process("A", 0, 999));
        queueZero.enqueue(new Process("B", 0, -99));
        queueZero.enqueue(new Process("C", 0, 0));

        Scheduler s = new Scheduler(queueZero);

        s.run();

        List<Process> processQ = s.completed();
        Process A = processQ.get(0);
        Process B = processQ.get(1);
        Process C = processQ.get(2);

        assertEquals(A.getFinishTime(), 0, "Case 1 failed");
        assertEquals(B.getFinishTime(), 0, "Case 2 failed");
        assertEquals(C.getFinishTime(), 0, "Case 3 failed");
    }

    @Test
    @DisplayName("Scheduler idles on none")
    void idleOnNothing() {
        Queue<Process> processQ = new Queue<>();
        Scheduler s = new Scheduler(processQ);
        
        assertTrue(s.idleState());
    }

    @Test
    @DisplayName("Test outputs match")
    void processTimesMatching() {
        s.run();
        List<Process> completed = s.completed();

        assertEquals(completed.get(0).getFinishTime(), 257, "Case 1 failed");
        assertEquals(completed.get(0).getName(), "F", "Case 1 failed");

        assertEquals(completed.get(1).getFinishTime(), 351, "Case 2 failed");
        assertEquals(completed.get(1).getName(), "D", "Case 2 failed");

        assertEquals(completed.get(2).getFinishTime(), 544, "Case 3 failed");
        assertEquals(completed.get(2).getName(), "G", "Case 3 failed");

        assertEquals(completed.get(3).getFinishTime(), 713, "Case 4 failed");
        assertEquals(completed.get(3).getName(), "E", "Case 4 failed");

        assertEquals(completed.get(4).getFinishTime(), 777, "Case 5 failed");
        assertEquals(completed.get(4).getName(), "C", "Case 5 failed");

        assertEquals(completed.get(5).getFinishTime(), 807, "Case 6 failed");
        assertEquals(completed.get(5).getName(), "I", "Case 6 failed");

        assertEquals(completed.get(6).getFinishTime(), 854, "Case 7 failed");
        assertEquals(completed.get(6).getName(), "J", "Cased 7 failed");

        assertEquals(completed.get(7).getFinishTime(), 869, "Case 8 failed");
        assertEquals(completed.get(7).getName(), "H", "Case 8 failed");

        assertEquals(completed.get(8).getFinishTime(), 987, "Case 9 failed");
        assertEquals(completed.get(8).getName(), "A", "Case 9 failed");

        assertEquals(completed.get(9).getFinishTime(), 1077, "Case 10 failed");
        assertEquals(completed.get(9).getName(), "B", "Case 10 failed");
    }
}
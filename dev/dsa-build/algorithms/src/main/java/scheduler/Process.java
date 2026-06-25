package scheduler;

public class Process {
    private final String name;
    private final int time;
    private final int slice;
    private int timeRemaining;
    private int finishTime;


    public Process(String name, int time, int slice) {
        this.name = name;
        this.time = time;
        this.slice = slice;
        this.timeRemaining = time;
        this.finishTime = 0;
    }

    protected void setTime(int elapsed) {
        finishTime = elapsed;
    }

    protected void execute(int timeSlice) {
        if (isFinished()) {
            throw new ProcessException("Process has already been processed");
        }
        
        timeRemaining -= timeSlice;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public int getSlice() {
        return slice;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public boolean isFinished() {
        return timeRemaining == 0;
    }

    public int getFinishTime() {
        return finishTime;
    }
}
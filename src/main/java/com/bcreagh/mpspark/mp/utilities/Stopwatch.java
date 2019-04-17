package com.bcreagh.mpspark.mp.utilities;

public class Stopwatch {
    private boolean stopwatchHasBeenStarted = false;
    private long startTime = 0;

    public void start() {
        if (this.stopwatchHasBeenStarted) {
            throw new IllegalStateException("The stopwatch has already been started");
        }
        this.stopwatchHasBeenStarted = true;
        this.startTime = this.getCurrentTimeInMicroSecs();
    }

    public long stop() {
        if (!this.stopwatchHasBeenStarted) {
            throw new IllegalStateException("You cannot stop the stopwatch if it has not yet been started!");
        }
        long currentTime = this.getCurrentTimeInMicroSecs();
        long timeElapsed = currentTime - this.startTime;
        this.stopwatchHasBeenStarted = false;
        return timeElapsed;
    }

    private long getCurrentTimeInMicroSecs() {
        long timeInNanoSecs = System.nanoTime();
        return (timeInNanoSecs / 1000);
    }
}

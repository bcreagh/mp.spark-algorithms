package com.bcreagh.mpspark.mp.domain;

import com.bcreagh.mpspark.mp.utilities.logger.Log;

import java.util.ArrayList;

public class ActionResult {
    private String input = "";
    private String output = "";
    private ArrayList<Log> logs = new ArrayList<>();
    private long performance = -1;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<Log> logs) {
        this.logs = logs;
    }

    public long getPerformance() {
        return performance;
    }

    public void setPerformance(long performance) {
        this.performance = performance;
    }
}

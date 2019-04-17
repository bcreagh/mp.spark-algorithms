package com.bcreagh.mpspark.mp.utilities.logger;

public class Log {
    private String level;
    private String message;

    public Log(String level, String message) {
        this.setLevel(level);
        this.setMessage(message);
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.bcreagh.mpspark.mp.utilities.logger;

import com.bcreagh.mpspark.mp.domain.ActionResult;

public class Logger {
    public static void log(String message) {
        System.out.println(message);
    }

    public static void log(String message, ActionResult result) {
        Log log = new Log("log", message);
        result.getLogs().add(log);
        Logger.log(message);
    }

    public static void error(String message) {
        System.err.println(message);
    }

    public static void error(String message, ActionResult result) {
        Log log = new Log("error", message);
        result.getLogs().add(log);
        Logger.error(message);
    }
}

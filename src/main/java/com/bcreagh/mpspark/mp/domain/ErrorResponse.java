package com.bcreagh.mpspark.mp.domain;

import com.bcreagh.mpspark.mp.utilities.logger.Log;

import java.util.ArrayList;

public class ErrorResponse implements ActionResponse {
    private String userFriendlyError = "";
    private String error = "";
    private ArrayList<Log> logs = new ArrayList<>();

    public ErrorResponse(String userFriendlyError) {
        this.userFriendlyError = userFriendlyError;
    }

    public ErrorResponse(String userFriendlyError, String error) {
        this.userFriendlyError = userFriendlyError;
        this.error = error;
    }

    public ErrorResponse(String userFriendlyError, String error, ArrayList<Log> logs) {
        this.userFriendlyError = userFriendlyError;
        this.error = error;
        this.logs = logs;
    }

    public String getUserFriendlyError() {
        return userFriendlyError;
    }

    public void setUserFriendlyError(String userFriendlyError) {
        this.userFriendlyError = userFriendlyError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<Log> logs) {
        this.logs = logs;
    }
}

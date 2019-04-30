package com.bcreagh.mpspark.routes;

import com.bcreagh.mpspark.mp.domain.ActionInputException;
import com.bcreagh.mpspark.mp.domain.ErrorResponse;
import com.bcreagh.mpspark.mp.utilities.logger.Logger;
import com.bcreagh.mpspark.services.ConfigService;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import spark.Response;

public abstract class BaseRoute {
    protected static Gson gson = new Gson();
    protected static final String SERVICE_NAME = ConfigService.getServiceName();

    protected static String jsonResponse(Object result, Response response) {
        response.type("application/json");
        return gson.toJson(result);
    }

    protected static String jsonError(Object result, Response response, int errorCode) {
        response.type("application/json");
        response.status(errorCode);
        return gson.toJson(result);
    }

    protected static String handleActionInputException(ActionInputException ex, Response response) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getMessage());
        Logger.log(errorResponse.getError());
        ex.printStackTrace();
        return jsonError(errorResponse, response, 400);
    }

    protected static String handleUnexpectedException(Exception ex, Response response) {
        ErrorResponse errorResponse = new ErrorResponse("Error: An unexpected error occured!", ex.getMessage());
        Logger.log(errorResponse.getError());
        ex.printStackTrace();
        return jsonError(errorResponse, response, 500);
    }

    protected static <T> T parseJsonInput(String input, Class<T> clazz) {
        try {
            return gson.fromJson(input, clazz);
        } catch (JsonParseException ex) {
            throw new ActionInputException("Error: There was a problem parsing the input!", ex);
        }
    }
}

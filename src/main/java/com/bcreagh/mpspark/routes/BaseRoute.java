package com.bcreagh.mpspark.routes;

import com.bcreagh.mpspark.services.ConfigService;
import com.google.gson.Gson;
import spark.Response;

public abstract class BaseRoute {
    protected static Gson gson = new Gson();
    protected static final String SERVICE_NAME = ConfigService.getServiceName();

    protected static String jsonResponse(Object result, Response response) {
        response.type("application/json");
        return gson.toJson(result);
    }
}

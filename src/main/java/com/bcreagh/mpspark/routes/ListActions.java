package com.bcreagh.mpspark.routes;

import com.bcreagh.mpspark.mp.domain.Action;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;
import com.bcreagh.mpspark.services.ActionService;

import java.util.ArrayList;

import static spark.Spark.*;

public class ListActions extends BaseRoute {

    @MpRoute
    public static void listActions() {
        get(String.format("/%s/actions", SERVICE_NAME), (request, response) -> {
            ArrayList<Action> actions = ActionService.getAllActions();
            return jsonResponse(actions, response);
        });
    }
}

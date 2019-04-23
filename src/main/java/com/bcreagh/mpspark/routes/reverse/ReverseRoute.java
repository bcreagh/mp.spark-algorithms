package com.bcreagh.mpspark.routes.reverse;

import com.bcreagh.mpspark.mp.domain.Action;
import com.bcreagh.mpspark.mp.domain.ActionResult;
import com.bcreagh.mpspark.mp.utilities.Stopwatch;
import com.bcreagh.mpspark.mp.utilities.logger.Logger;
import com.bcreagh.mpspark.routes.BaseRoute;
import com.bcreagh.mpspark.routes.routeutils.BasicInput;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;
import com.bcreagh.mpspark.services.ActionService;
import com.bcreagh.mpspark.services.FileService;
import com.bcreagh.javaalgos.Reverse;

import static spark.Spark.get;
import static spark.Spark.post;

public class ReverseRoute extends BaseRoute {

    @MpRoute
    public static void reverse_get() {
        get(String.format("/%s/reverse", SERVICE_NAME), (request, response) -> {
            Logger.log("GET request for reverse");
            Action action = ActionService.getAction("reverse");
            String readmeAsString = FileService.readFileFromResources("routes/reverse/reverse.md", "UTF-8");
            action.getReadme().setData(readmeAsString);
            return jsonResponse(action, response);
        });
    }

    @MpRoute
    public static void reverse_post() {
        post(String.format("/%s/reverse", SERVICE_NAME), (request, response) -> {
            ActionResult result = new ActionResult();
            Stopwatch stopwatch = new Stopwatch();
            BasicInput input = gson.fromJson(request.body(), BasicInput.class);

            Logger.log("Handling the POST request", result);
            result.setInput(input.getInput());
            stopwatch.start();
            String output = Reverse.reverse(input.getInput());
            result.setOutput(output);
            long performance = stopwatch.stop();
            result.setPerformance(performance);

            return jsonResponse(result, response);
        });
    }
}

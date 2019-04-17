package com.bcreagh.mpspark.routes.helloworld;

import com.bcreagh.mpspark.mp.domain.ActionResult;
import com.bcreagh.mpspark.mp.utilities.Stopwatch;
import com.bcreagh.mpspark.routes.BaseRoute;
import static spark.Spark.*;

import com.bcreagh.mpspark.mp.domain.Action;
import com.bcreagh.mpspark.mp.utilities.logger.Logger;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;
import com.bcreagh.mpspark.services.ActionService;
import com.bcreagh.mpspark.services.FileService;

public class HelloWorld extends BaseRoute {

    @MpRoute
    public static void hello_world_get() {
        get(String.format("/%s/hello-world", SERVICE_NAME), (request, response) -> {
            Logger.log("Handling the GET request");
            Action action = ActionService.getAction("hello-world");
            String readmeAsString = FileService.readFileFromResources("routes/helloworld/helloWorld.md", "UTF-8");
            action.getReadme().setData(readmeAsString);
            return jsonResponse(action, response);
        });
    }

    @MpRoute
    public static void hello_world_post() {
        post(String.format("/%s/hello-world", SERVICE_NAME), (request, response) -> {
            ActionResult result = new ActionResult();
            Stopwatch stopwatch = new Stopwatch();
            HelloWorldInput input = gson.fromJson(request.body(), HelloWorldInput.class);

            Logger.log("Handling the POST request", result);
            stopwatch.start();
            result.setInput(input.getInput());
            result.setOutput("A message from mp_spark-algos: HELLO " + input.getInput());
            long performance = stopwatch.stop();
            result.setPerformance(performance);

            return jsonResponse(result, response);
        });
    }

    @MpRoute
    public static void hello_world_with_route() {
        post(String.format("/%s/hello-world/with-route", SERVICE_NAME), (request, response) -> {
            ActionResult result = new ActionResult();
            Stopwatch stopwatch = new Stopwatch();
            String input = "- this response is from the 'with-route' route in spark base!";

            Logger.log("Handling the POST request", result);
            stopwatch.start();
            result.setInput(input);
            result.setOutput("Hello " + input);
            long performance = stopwatch.stop();
            result.setPerformance(performance);

            return jsonResponse(result, response);
        });
    }
}

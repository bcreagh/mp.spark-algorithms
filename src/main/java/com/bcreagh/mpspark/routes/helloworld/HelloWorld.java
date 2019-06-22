package com.bcreagh.mpspark.routes.helloworld;

import com.bcreagh.mpspark.mp.domain.ActionResult;
import com.bcreagh.mpspark.mp.utilities.Stopwatch;
import com.bcreagh.mpspark.mp.utilities.logger.Logger;
import com.bcreagh.mpspark.routes.AlgorithmRoute;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;
import com.bcreagh.mpspark.routes.routeutils.SimpleRoute;

import static spark.Spark.post;

@MpRoute
public class HelloWorld extends AlgorithmRoute<HelloWorldInput> {
    @Override
    protected String routeName() {
        return "hello-world";
    }

    @Override
    protected Class<?> inputType() {
        return HelloWorldInput.class;
    }

    @Override
    protected Object algorithm() {
        return "A message from mp_spark-algos: HELLO " + input.getInput();
    }

    @SimpleRoute
    public static void hello_world_with_route() {
        post(String.format("/%s/hello-world/with-route", SERVICE_NAME), (request, response) -> {
            ActionResult result = new ActionResult();
            Stopwatch stopwatch = new Stopwatch();
            String exampleInput = "- this response is from the 'with-route' route in spark base!";

            Logger.log("Handling the POST request", result);
            stopwatch.start();
            result.setInput(exampleInput);
            result.setOutput("Hello " + exampleInput);
            long performance = stopwatch.stop();
            result.setPerformance(performance);

            return jsonResponse(result, response);
        });
    }
}

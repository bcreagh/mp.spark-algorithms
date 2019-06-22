package com.bcreagh.mpspark.routes;

import com.bcreagh.mpspark.mp.domain.Action;
import com.bcreagh.mpspark.mp.domain.ActionInputException;
import com.bcreagh.mpspark.mp.domain.ActionResult;
import com.bcreagh.mpspark.mp.utilities.Stopwatch;
import com.bcreagh.mpspark.mp.utilities.logger.Logger;
import com.bcreagh.mpspark.routes.routeutils.TypedRoute;
import com.bcreagh.mpspark.services.ActionService;
import com.bcreagh.mpspark.services.FileService;

import static spark.Spark.get;
import static spark.Spark.post;

public abstract class AlgorithmRoute<Q> extends BaseRoute {

    protected ActionResult result = new ActionResult();
    protected Q input;

    @TypedRoute
    public static <T extends AlgorithmRoute> void routeGet(Class<T> klass) throws Exception {
        AlgorithmRoute route = klass.getConstructor().newInstance();
        String routeName = route.routeName();
        System.out.println(klass.getSimpleName());
        System.out.println(route.routeName());
        System.out.println(route.getRoute());
        System.out.println(route.getReadmePath());
        get(route.getRoute(), (request, response) -> {
            Logger.log(String.format("GET request for %s", routeName));
            Action action = ActionService.getAction(routeName);
            String readmeAsString = FileService.readFileFromResources(route.getReadmePath(), "UTF-8");
            action.getReadme().setData(readmeAsString);
            return jsonResponse(action, response);
        });
    }

    @TypedRoute
    public static <T extends AlgorithmRoute> void routePost(Class<T> klass) throws Exception {
        AlgorithmRoute route = klass.getConstructor().newInstance();
        Class<?> inputType = route.inputType();

        post(route.postRoute(), (request, response) -> {
            try {
                Logger.log(String.format("POST request for %s", route.routeName()));
                Stopwatch stopwatch = new Stopwatch();
                route.input = parseJsonInput(request.body(), inputType);
                ActionResult result = route.result;

                result.setInput(route.input);
                route.before();

                stopwatch.start();
                result.setOutput(route.algorithm());
                result.setPerformance(stopwatch.stop());

                route.after();
            } catch (ActionInputException ex) {
                return handleActionInputException(ex, response);
            } catch (Exception ex) {
                return handleUnexpectedException(ex, response);
            }
            return jsonResponse(route.result, response);
        });

    }

    protected abstract String routeName();
    protected abstract Class<?> inputType();
    protected abstract Object algorithm();

    protected String getReadmePath() {
        return String.format("routes/%s/%s.md", routeName(), routeName());
    }

    protected String getRoute() {
        return String.format("/%s/%s", SERVICE_NAME, routeName());
    }

    protected String postRoute() {
        return String.format("/%s/%s", SERVICE_NAME, routeName());
    }

    protected void before() {
        // Optionally overridable
    }

    protected void after() {
        // Optionally overridable
    }
}

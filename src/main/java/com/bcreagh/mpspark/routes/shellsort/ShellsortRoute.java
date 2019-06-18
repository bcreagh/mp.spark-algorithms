package com.bcreagh.mpspark.routes.shellsort;

import com.bcreagh.javaalgos.sort.ShellSort;
import com.bcreagh.mpspark.mp.domain.Action;
import com.bcreagh.mpspark.mp.domain.ActionInputException;
import com.bcreagh.mpspark.mp.domain.ActionResult;
import com.bcreagh.mpspark.mp.utilities.Stopwatch;
import com.bcreagh.mpspark.mp.utilities.logger.Logger;
import com.bcreagh.mpspark.routes.BaseRoute;
import com.bcreagh.mpspark.routes.routeutils.IntArrayInput;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;
import com.bcreagh.mpspark.services.ActionService;
import com.bcreagh.mpspark.services.FileService;

import java.util.Arrays;

import static spark.Spark.get;
import static spark.Spark.post;

public class ShellsortRoute extends BaseRoute {

    @MpRoute
    public static void mergesortGet() {
        get(String.format("/%s/shellsort", SERVICE_NAME), (request, response) -> {
            Logger.log("Handling the GET request for shellsort");
            Action action = ActionService.getAction("shellsort");
            String readmeAsString = FileService.readFileFromResources("routes/shellsort/shellsort.md", "UTF-8");
            action.getReadme().setData(readmeAsString);
            return jsonResponse(action, response);
        });
    }

    @MpRoute
    @SuppressWarnings("unchecked")
    public static void shellsortPost() {
        post(String.format("/%s/shellsort", SERVICE_NAME), (request, response) -> {
            ActionResult result = new ActionResult();
            try {
                Stopwatch stopwatch = new Stopwatch();
                String inputAsJson = request.body();
                Logger.log(inputAsJson);
                Integer[] input = parseJsonInput(inputAsJson, Integer[].class);
                Integer[] output = Arrays.copyOf(input, input.length);
                ShellSort<Integer> shellSort = new ShellSort<>();
                stopwatch.start();
                shellSort.sort(output);
                long performance = stopwatch.stop();
                result.setPerformance(performance);

                result.setInput(input);
                result.setOutput(output);
            } catch (ActionInputException ex) {
                return handleActionInputException(ex, response);
            } catch (Exception ex) {
                return handleUnexpectedException(ex, response);
            }
            return jsonResponse(result, response);
        });
    }
}

package com.bcreagh.mpspark.routes.mergesort;

import com.bcreagh.javaalgos.sort.MergeSort;
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
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;

import static spark.Spark.get;
import static spark.Spark.post;

public class MergesortRoute extends BaseRoute {

    @MpRoute
    public static void mergesortGet() {
        get(String.format("/%s/mergesort", SERVICE_NAME), (request, response) -> {
            Logger.log("Handling the GET request for mergesort");
            Action action = ActionService.getAction("mergesort");
            String readmeAsString = FileService.readFileFromResources("routes/mergesort/mergesort.md", "UTF-8");
            action.getReadme().setData(readmeAsString);
            return jsonResponse(action, response);
        });
    }

    @MpRoute
    @SuppressWarnings("unchecked")
    public static void mergesortPost() {
        post(String.format("/%s/mergesort", SERVICE_NAME), (request, response) -> {
            ActionResult result = new ActionResult();
            try {
                Stopwatch stopwatch = new Stopwatch();
                String inputAsJson = request.body();
                Logger.log(inputAsJson);
                Integer[] input = parseJsonInput(inputAsJson, Integer[].class);
                Integer[] output = Arrays.copyOf(input, input.length);
                MergeSort<Integer> mergeSort = new MergeSort<>();
                stopwatch.start();
                mergeSort.sort(output);
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

package com.bcreagh.mpspark.routes.mergesort;

import com.bcreagh.javaalgos.sort.MergeSort;
import com.bcreagh.javaalgos.util.InputUtil;
import com.bcreagh.mpspark.mp.domain.Action;
import com.bcreagh.mpspark.mp.domain.ActionInputException;
import com.bcreagh.mpspark.mp.domain.ActionResult;
import com.bcreagh.mpspark.mp.utilities.Stopwatch;
import com.bcreagh.mpspark.mp.utilities.logger.Logger;
import com.bcreagh.mpspark.routes.BaseRoute;
import com.bcreagh.mpspark.routes.routeutils.BasicInput;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;
import com.bcreagh.mpspark.services.ActionService;
import com.bcreagh.mpspark.services.FileService;

import java.util.Arrays;

import static spark.Spark.get;
import static spark.Spark.post;

public class MergesortRandom extends BaseRoute {

    @MpRoute
    public static void mergesortGet() {
        get(String.format("/%s/mergesort-random", SERVICE_NAME), (request, response) -> {
            Logger.log("Handling the GET request for mergesort-random");
            Action action = ActionService.getAction("mergesort-random");
            String readmeAsString = FileService.readFileFromResources("routes/mergesort/mergesort-random.md", "UTF-8");
            action.getReadme().setData(readmeAsString);
            return jsonResponse(action, response);
        });
    }

    @MpRoute
    @SuppressWarnings("unchecked")
    public static void mergesortPost() {
        post(String.format("/%s/mergesort-random", SERVICE_NAME), (request, response) -> {
            ActionResult result = new ActionResult();
            try {
                Stopwatch stopwatch = new Stopwatch();
                String inputAsJson = request.body();
                Logger.log(inputAsJson);
                String inputLengthAsString = parseJsonInput(inputAsJson, BasicInput.class).getInput();
                int inputLength = Integer.valueOf(inputLengthAsString);
                Integer[] input = InputUtil.generatedRandomArrayWithFixedLength(inputLength);
                if (input > 100000000) {
                    throw new ActionInputException("The number you enter must be less than 100,000,000");
                }
                Integer[] output = Arrays.copyOf(input, input.length);
                MergeSort<Integer> mergeSort = new MergeSort<>();
                stopwatch.start();
                mergeSort.sort(output);
                long performance = stopwatch.stop();
                result.setPerformance(performance);

                if (inputLength < 1000000) {
                    result.setInput(input);
                    result.setOutput(output);
                } else {
                    result.setOutput("The output is too large to display");
                }
            } catch (ActionInputException ex) {
                return handleActionInputException(ex, response);
            } catch (Exception ex) {
                return handleUnexpectedException(ex, response);
            }
            return jsonResponse(result, response);
        });
    }

}

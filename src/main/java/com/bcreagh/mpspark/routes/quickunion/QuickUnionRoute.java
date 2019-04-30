package com.bcreagh.mpspark.routes.quickunion;

import com.bcreagh.javaalgos.QuickUnion;
import com.bcreagh.mpspark.mp.domain.*;
import com.bcreagh.mpspark.mp.utilities.Stopwatch;
import com.bcreagh.mpspark.mp.utilities.logger.Log;
import com.bcreagh.mpspark.mp.utilities.logger.Logger;
import com.bcreagh.mpspark.routes.BaseRoute;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;
import com.bcreagh.mpspark.services.ActionService;
import com.bcreagh.mpspark.services.FileService;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import static spark.Spark.*;

public class QuickUnionRoute extends BaseRoute {

    @MpRoute
    public static void quickUnionGet() {
        get(String.format("/%s/quick-union", SERVICE_NAME), (request, response) -> {
            Logger.log("Handling the GET request for ");
            Action action = ActionService.getAction("quick-union");
            String readmeAsString = FileService.readFileFromResources("routes/quickunion/quickUnion.md", "UTF-8");
            action.getReadme().setData(readmeAsString);
            return jsonResponse(action, response);
        });
    }

    @MpRoute
    public static void quickUnionPost() {
        post(String.format("/%s/quick-union", SERVICE_NAME), (request, response) -> {
            ActionResult result = new ActionResult();
            try {
                Stopwatch stopwatch = new Stopwatch();
                String inputAsJson = request.body();
                Logger.log(inputAsJson);
                QuickUnionInput input = parseJsonInput(inputAsJson, QuickUnionInput.class);
                int[][] nodesToConnect = input.getUnion();
                int[][] nodesToCheck = input.getConnected();
                Logger.log(gson.toJson(nodesToCheck));
                Logger.log(gson.toJson(input));
                String[] output = new String[nodesToCheck.length];

                Logger.log("Performing quick union", result);
                stopwatch.start();
                QuickUnion quickUnion = new QuickUnion(input.getTotalNodes());
                for (int i = 0; i < nodesToConnect.length; i++) {
                    validateInputArray(nodesToConnect[i], input.getTotalNodes());
                    quickUnion.union(nodesToConnect[i][0], nodesToConnect[i][1]);
                }
                for (int i = 0; i < nodesToCheck.length; i++) {
                    validateInputArray(nodesToCheck[i], input.getTotalNodes());
                    int node1 = nodesToCheck[i][0];
                    int node2 = nodesToCheck[i][1];
                    boolean nodesAreConnected = quickUnion.connected(node1, node2);
                    String outputItem;
                    if (nodesAreConnected) {
                        outputItem = "Node %d and node %d are connected";
                    } else {
                        outputItem = "Node %d and node %d are NOT connected";
                    }
                    output[i] = String.format(outputItem, node1, node2);
                }
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

    private static void validateInputArray(int[] inputArray, int totalNodes) {
        if (inputArray.length != 2) {
            throw new ActionInputException("Error: You must specify 2 nodes for union and connected!");
        }
        validateNodeExists(inputArray[0], totalNodes);
        validateNodeExists(inputArray[1], totalNodes);
    }

    private static void validateNodeExists(int node, int totalNodes) {
        if (node < 0) {
            throw new ActionInputException("Error: The node cannot be less than 0!");
        }
        if (node >= totalNodes) {
            throw new ActionInputException("Error: A node cannot be greater than the total number of nodes!");
        }
    }
}

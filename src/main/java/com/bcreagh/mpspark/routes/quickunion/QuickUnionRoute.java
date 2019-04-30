package com.bcreagh.mpspark.routes.quickunion;

import com.bcreagh.javaalgos.QuickUnion;
import com.bcreagh.mpspark.mp.domain.Action;
import com.bcreagh.mpspark.mp.domain.ActionResult;
import com.bcreagh.mpspark.mp.utilities.Stopwatch;
import com.bcreagh.mpspark.mp.utilities.logger.Log;
import com.bcreagh.mpspark.mp.utilities.logger.Logger;
import com.bcreagh.mpspark.routes.BaseRoute;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;
import com.bcreagh.mpspark.services.ActionService;
import com.bcreagh.mpspark.services.FileService;

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
            Stopwatch stopwatch = new Stopwatch();
            String inputAsJson = request.body();
            Logger.log(inputAsJson);
            QuickUnionInput input = gson.fromJson(inputAsJson, QuickUnionInput.class);
            int[][] nodesToConnect = input.getUnion();
            int[][] nodesToCheck = input.getConnected();
            Logger.log(gson.toJson(nodesToCheck));
            Logger.log(gson.toJson(input));
            String[] output = new String[nodesToCheck.length];

            Logger.log("Performing quick union", result);
            stopwatch.start();
            QuickUnion quickUnion = new QuickUnion(input.getTotalNodes());
            for(int i = 0; i < nodesToConnect.length; i++) {
                quickUnion.union(nodesToConnect[i][0], nodesToConnect[i][1]);
            }
            for(int i = 0; i < nodesToCheck.length; i++) {
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
            return jsonResponse(result, response);
        });
    }
}

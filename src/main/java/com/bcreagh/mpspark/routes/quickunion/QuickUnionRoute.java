package com.bcreagh.mpspark.routes.quickunion;

import com.bcreagh.javaalgos.QuickUnion;
import com.bcreagh.mpspark.mp.domain.ActionInputException;
import com.bcreagh.mpspark.mp.utilities.logger.Logger;
import com.bcreagh.mpspark.routes.AlgorithmRoute;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;

@MpRoute
public class QuickUnionRoute extends AlgorithmRoute<QuickUnionInput> {

    private final String ROUTE_NAME = "quick-union";

    @Override
    protected String routeName() {
        return ROUTE_NAME;
    }

    @Override
    protected Class<?> inputType() {
        return QuickUnionInput.class;
    }

    @Override
    protected Object algorithm() {
        int[][] nodesToConnect = input.getUnion();
        int[][] nodesToCheck = input.getConnected();
        String[] output = new String[nodesToCheck.length];
        Logger.log("Performing quick union", result);
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
        return output;
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

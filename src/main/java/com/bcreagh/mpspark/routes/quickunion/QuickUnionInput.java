package com.bcreagh.mpspark.routes.quickunion;

public class QuickUnionInput {
    private int totalNodes;
    private int[][] union;
    private int[][] connected;

    public int getTotalNodes() {
        return totalNodes;
    }

    public void setTotalNodes(int totalNodes) {
        this.totalNodes = totalNodes;
    }

    public int[][] getUnion() {
        return union;
    }

    public void setUnion(int[][] union) {
        this.union = union;
    }

    public int[][] getConnected() {
        return connected;
    }

    public void setConnected(int[][] connected) {
        this.connected = connected;
    }
}

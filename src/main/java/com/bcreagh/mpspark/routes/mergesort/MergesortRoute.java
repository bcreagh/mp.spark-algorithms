package com.bcreagh.mpspark.routes.mergesort;

import com.bcreagh.javaalgos.sort.MergeSort;
import com.bcreagh.mpspark.routes.AlgorithmRoute;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;

import java.util.Arrays;

@MpRoute
public class MergesortRoute extends AlgorithmRoute<Integer[]> {

    private final String ROUTE_NAME = "mergesort";


    @Override
    protected String routeName() {
        return ROUTE_NAME;
    }

    @Override
    protected Class<?> inputType() {
        return Integer[].class;
    }

    @Override
    protected void before() {
        result.setInput(input);
        input = Arrays.copyOf(input, input.length);
    }

    @Override
    protected Object algorithm() {
        MergeSort<Integer> mergeSort = new MergeSort<>();
        return mergeSort.sort(input);
    }
}

package com.bcreagh.mpspark.routes.quicksort;

import com.bcreagh.javaalgos.sort.QuickSort;
import com.bcreagh.mpspark.routes.AlgorithmRoute;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;

import java.util.Arrays;

@MpRoute
public class QuicksortRoute extends AlgorithmRoute<Integer[]> {

    private final String ROUTE_NAME = "quicksort";

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
        QuickSort<Integer> quicksort = new QuickSort<>();
        return quicksort.sort(input);
    }
}

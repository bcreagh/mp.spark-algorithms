package com.bcreagh.mpspark.routes.shellsort;

import com.bcreagh.javaalgos.sort.ShellSort;
import com.bcreagh.mpspark.routes.AlgorithmRoute;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;

import java.util.Arrays;

@MpRoute
public class ShellsortRoute extends AlgorithmRoute<Integer[]> {

    private final String ROUTE_NAME = "shellsort";

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
        ShellSort<Integer> shellSort = new ShellSort<>();
        return shellSort.sort(input);
    }
}

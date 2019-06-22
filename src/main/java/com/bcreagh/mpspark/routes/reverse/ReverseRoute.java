package com.bcreagh.mpspark.routes.reverse;

import com.bcreagh.javaalgos.Reverse;
import com.bcreagh.mpspark.routes.AlgorithmRoute;
import com.bcreagh.mpspark.routes.BaseRoute;
import com.bcreagh.mpspark.routes.routeutils.BasicInput;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;

import static spark.Spark.get;
import static spark.Spark.post;

@MpRoute
public class ReverseRoute extends AlgorithmRoute<BasicInput> {

    protected static final String ROUTE_NAME = "reverse";

    @Override
    protected String routeName() {
        return ROUTE_NAME;
    }

    @Override
    protected Class<?> inputType() {
        return BasicInput.class;
    }

    @Override
    protected Object algorithm() {
        return Reverse.reverse(input.getInput());
    }
}

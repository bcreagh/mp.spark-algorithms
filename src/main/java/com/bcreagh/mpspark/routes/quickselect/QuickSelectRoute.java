package com.bcreagh.mpspark.routes.quickselect;

import com.bcreagh.javaalgos.select.QuickSelect;
import com.bcreagh.mpspark.routes.AlgorithmRoute;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;

@MpRoute
public class QuickSelectRoute extends AlgorithmRoute<QuickSelectInput> {

    private final String ROUTE_NAME = "quickselect";

    @Override
    protected String routeName() {
        return ROUTE_NAME;
    }

    @Override
    protected Class<?> inputType() {
        return QuickSelectInput.class;
    }

    @Override
    protected Object algorithm() {
        QuickSelect<Integer> quickSelect = new QuickSelect<>();
        return quickSelect.select(input.getInput(), input.getK());
    }
}

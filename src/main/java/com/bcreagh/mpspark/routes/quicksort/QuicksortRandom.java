package com.bcreagh.mpspark.routes.quicksort;

import com.bcreagh.javaalgos.sort.QuickSort;
import com.bcreagh.javaalgos.util.InputUtil;
import com.bcreagh.mpspark.mp.domain.ActionInputException;
import com.bcreagh.mpspark.routes.AlgorithmRoute;
import com.bcreagh.mpspark.routes.routeutils.BasicInput;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;

import java.util.Arrays;

@MpRoute
public class QuicksortRandom extends AlgorithmRoute<BasicInput> {

    private final int MAX_INPUT_LENGTH = 100000000;
    private final int DISPLAY_INPUT_LIMIT = 500000;
    private Integer[] inputArray;

    @Override
    protected String routeName() {
        return "quicksort-random";
    }

    @Override
    protected String getReadmePath() {
        return "routes/quicksort/quicksort-random.md";
    }

    @Override
    protected Class<?> inputType() {
        return BasicInput.class;
    }

    @Override
    protected void before() {
        String inputLengthAsString = input.getInput();
        int inputLength = Integer.valueOf(inputLengthAsString);
        if (inputLength > MAX_INPUT_LENGTH) {
            throw new ActionInputException("The number you enter must be less than 100,000,000");
        }
        inputArray = InputUtil.generatedRandomArrayWithFixedLength(inputLength);
        if (inputArray.length <= DISPLAY_INPUT_LIMIT) {
            result.setInput(inputArray);
            inputArray = Arrays.copyOf(inputArray, inputArray.length);
        } else {
            result.setInput(null);
        }
    }

    @Override
    protected Object algorithm() {
        QuickSort<Integer> quickSort = new QuickSort<>();
        return quickSort.sort(inputArray);
    }

    @Override
    protected void after() {
        if (inputArray.length > DISPLAY_INPUT_LIMIT) {
            result.setOutput("The output is too large to display");
        }
    }

}

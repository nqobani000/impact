package com.impact.assessment.core;

import com.impact.assessment.core.Summarizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class TestData {

    public static final String EMPTY_INPUT = "";
    public static final String NULL_INPUT = null;

    public static final String GIVEN_TEST_INPUT = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
    public static final String EXPECTED_FOR_GIVEN_TEST_INPUT = "1, 3, 6-8, 12-15, 21-24, 31";

    public static final String INPUT_WITH_DUPLICATES = "1,3,6,6,6,6,7,8,12,13,14,14,14,15,21,21,21,22,23,24,31";
    public static final String EXPECTED_FOR_INPUT_WITH_DUPLICATES = "1, 3, 6-8, 12-15, 21-24, 31";

    public static final String PERFECT_SEQUENCE_INPUT = "1,2,3,4,5,6,7,8,9,10";
    public static final String EXPECTED_FOR_PERFECT_SEQUENCE = "1-10";

    public static final String RANDOMLY_ORDERED_INPUT = "4,5,6,3,7,8,2,1,9,0,21,22,78,51";
    public static final String EXPECTED_FOR_RANDOMLY_ORDERED_INPUT = "0-9, 21-22, 51, 78";


    public static Collection<Integer> GET_NON_TREE_SET_COLLECTION() {
        Integer[] input = {4, 5, 6, 3, 7, 8, 2, 1, 1, 9, 0, 21, 21, 22, 78, 51, 51};
        return new ArrayList<>(Arrays.asList(input));
    }

    public static String EXPECTED_FOR_NON_TREE_SET_COLLECTION = "0-9, 21-22, 51, 78";

    public static Summarizer newInstance() {
        return new Summarizer();
    }
}

package com.impact.assessment.core;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * @attention: Please refer to the unit tests under test for a detailed look at my assumptions.
 */

/**
 * The service provided by this API doesn't require the class to keep a state.
 * (I assumed the numbers won't always be sequential - so I designed the solution to cater for such.)
 *
 * I used a treeSet collection because -:
 * - Elements are sorted using their natural ordering which reduces overhead and complexity when the input is not sorted.
 * - for this problem duplicate values are redundant, "1,1,1,2,3,4" will result in "1-4", so storing them
 * introduces overhead on a big data set.
 */
public class Summarizer implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {
        if (input == null) {
            throw new NullPointerException("A null input is not allowed!");
        }
        if (input.equals("")) {
            throw new RuntimeException("An empty input is not allowed!");
        }
        return arrayToCollection(input.split(","));
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {

        if (input == null) {
            throw new NullPointerException("The provided input is null");
        }

        if (input.size() == 0) {
            return "";
        }

        Integer[] inputArray = toOrderedArray(input);
        StringBuilder summary = new StringBuilder();

        for (int i = 0; i < inputArray.length; i++) {
            boolean isRange = false;
            summary.append(inputArray[i]);

            while (i < inputArray.length - 1 && inputArray[i + 1] - inputArray[i] == 1) {
                isRange = true;
                inputArray[i] = inputArray[(i++) + 1];
            }

            if (isRange) {
                summary.append("-").append(inputArray[i]);
            }

            if (i < inputArray.length - 1) {
                summary.append(", ");
            }
        }
        return summary.toString();
    }

    /**
     * Converts a collection to an array. If the collection is not already a TreeSet, it will be
     * converted to one, so the client doesn't have to worry about that.
     *
     * @return an ordered array (ASC)
     */
    private Integer[] toOrderedArray(Collection<Integer> input) {

        if (input == null) {
            throw new NullPointerException("input is null.");
        }

        if (!input.getClass().getSimpleName().equals(TreeSet.class.getSimpleName())) {
            TreeSet<Integer> tmp = new TreeSet<>(input);
            return tmp.toArray(new Integer[0]);
        }

        return input.toArray(new Integer[0]);
    }

    /**
     * converts an array to a treeSet collection.
     * Propagates: NumberFormatException if the String does not contain a parsable int.
     *
     * @return a treeSet collection
     */
    private Collection<Integer> arrayToCollection(String... values) {
        Set<Integer> collection = new TreeSet<>();

        for (String value : values) {
            collection.add(Integer.parseInt(value));
        }

        return collection;
    }
}

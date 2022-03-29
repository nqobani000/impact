package com.impact.assessment.core;

import com.sun.org.glassfish.gmbal.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.TreeSet;

import static com.impact.assessment.core.TestData.*;

/**
 * @author Nqobani
 *
 * These simple tests were written using Junit v5.8.2
 * Tests execute in random order, the order of writing is redundant.
 * All tests are given a description annotation for a detailed explanation.
 */
class SummarizerTest {

    @Test
    @DisplayName("throw a null pointer exception on null input")
    @Description("If the input is null, an unchecked null pointer exception is thrown.")
    void throwsANullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> newInstance().collect(NULL_INPUT));
    }

    @Test
    @DisplayName("throw a runtime exception on empty input")
    @Description("If an empty string is provided as input, throw a runtime exception.")
    void throwRuntimeExceptionOnEmptyInput() {
        Assertions.assertThrows(RuntimeException.class, () -> newInstance().collect(EMPTY_INPUT));
    }

    @Test
    @DisplayName("collect method returns a TreeSet collection.")
    @Description("Given valid input, the collect method returns a TreeSet collection.")
    void collectMethodReturnsATreeSetCollection() {
        Collection<Integer> collection = newInstance().collect(GIVEN_TEST_INPUT);

        if (!collection.getClass().getSimpleName().equals(TreeSet.class.getSimpleName())) {
            Assertions.fail(collection.getClass().getSimpleName() + " is not a treeSet");
        }
    }

    @Test
    @DisplayName("evaluate correctness if Collection is not a TreeSet.")
    @Description("Given a collection that isn't a TreeSet, check correctness.")
    void evaluateCorrectnessIfCollectionIsNotTreeSet() {
        String summary = newInstance().summarizeCollection(GET_NON_TREE_SET_COLLECTION());
        Assertions.assertEquals(EXPECTED_FOR_NON_TREE_SET_COLLECTION, summary);
    }

    @Test
    @DisplayName("produces a valid range.")
    @Description("Given a valid input representing a sequence, check correctness.")
    void producesValidRange() {
        Summarizer summarizer = newInstance();
        Collection<Integer> collection = summarizer.collect(PERFECT_SEQUENCE_INPUT);
        String summary = summarizer.summarizeCollection(collection);
        Assertions.assertEquals(EXPECTED_FOR_PERFECT_SEQUENCE, summary);
    }

    @Test
    @DisplayName("evaluate valid input with no duplicates.")
    @Description("Given a valid input with no duplicates, check correctness.")
    void evaluateValidInputWithNoDuplicateValues() {
        Summarizer summarizer = newInstance();
        Collection<Integer> collection = summarizer.collect(GIVEN_TEST_INPUT);
        String summary = summarizer.summarizeCollection(collection);
        Assertions.assertEquals(EXPECTED_FOR_GIVEN_TEST_INPUT, summary);
    }

    @Test
    @DisplayName("evaluate valid input with duplicates.")
    @Description("Given a valid input with duplicates, check correctness.")
    void evaluateValidInputWithDuplicateValues() {
        Summarizer summarizer = newInstance();
        Collection<Integer> collection = summarizer.collect(INPUT_WITH_DUPLICATES);
        String summary = summarizer.summarizeCollection(collection);
        Assertions.assertEquals(EXPECTED_FOR_INPUT_WITH_DUPLICATES, summary);
    }

    @Test
    @DisplayName("evaluate valid input with random order values.")
    @Description("Given a valid input with random ordered values, check correctness.")
    void evaluateValidInputWithRandomOrderedValues() {
        Summarizer summarizer = newInstance();
        Collection<Integer> collection = summarizer.collect(RANDOMLY_ORDERED_INPUT);
        String summary = summarizer.summarizeCollection(collection);
        Assertions.assertEquals(EXPECTED_FOR_RANDOMLY_ORDERED_INPUT, summary);
    }
}
package com.github.judoole.monitorino.internal.dto;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class TestSuite {
    public String time;
    public String name;
    public int tests;
    public int skipped;
    public int errors;
    public int failures;
    public Set<TestCase> testCases;
    public Properties healthcheckProperties;

    public void addCase(TestCase testCase) {
        if (testCases == null) testCases = new HashSet<TestCase>();
        testCases.add(testCase);
        if (testCase.hasError()) errors++;
        else if (testCase.hasFailure()) failures++;
        tests++;
    }
}
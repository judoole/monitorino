package com.github.judoole.monitorino.internal.dto;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class HealthcheckSuite {
    public String time;
    public String name;
    public int tests;
    public int skipped;
    public int errors;
    public int failures;
    public Set<HealthcheckCase> healthcheckCases;
    public Properties healthcheckProperties;

    public void addCase(HealthcheckCase healthcheckCase) {
        if (healthcheckCases == null) healthcheckCases = new HashSet<HealthcheckCase>();
        healthcheckCases.add(healthcheckCase);
        if (healthcheckCase.hasError()) errors++;
        else if (healthcheckCase.hasFailure()) failures++;
        tests++;
    }
}
package com.github.judoole.healthcheck.internal.dto;

import com.github.judoole.healthcheck.internal.HealthcheckProperty;
import com.github.judoole.healthcheck.web.xstream.HealthcheckStacktraceConverter;
import com.github.judoole.healthcheck.web.HealthcheckHtmlView;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;

import java.io.StringWriter;
import java.util.*;

public class HealthcheckSuite {
    public String time;
    public String name;
    public int tests;
    public int skipped;
    public int errors;
    public int failures;
    public Set<HealthcheckCase> healthcheckCases;
    public Set<HealthcheckProperty> healthcheckProperties;

    public void addCase(HealthcheckCase healthcheckCase) {
        if (healthcheckCases == null) healthcheckCases = new HashSet<HealthcheckCase>();
        healthcheckCases.add(healthcheckCase);
        if (healthcheckCase.hasError()) errors++;
        else if (healthcheckCase.hasFailure()) failures++;
        tests++;
    }
}
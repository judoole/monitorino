package com.github.judoole.healthcheck.internal.dto;

import com.github.judoole.healthcheck.internal.xstream.HealthcheckStacktraceConverter;
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

    public static void main(String[] args) {
        Properties p = System.getProperties();
        Enumeration e = p.propertyNames();
        while (e.hasMoreElements()) {
            String pro = (String) e.nextElement();
            System.out.println(pro + ":: " + System.getProperty(pro));
        }
    }

    public void addCase(HealthcheckCase healthcheckCase) {
        if (healthcheckCases == null) healthcheckCases = new HashSet<HealthcheckCase>();
        healthcheckCases.add(healthcheckCase);
        if (healthcheckCase.hasError()) errors++;
        else if (healthcheckCase.hasFailure()) failures++;
        tests++;
    }

    public String asXml() {
        return asXml(true);
    }

    String asXml(boolean prettyPrint) {
        XStream xStream = new XStream();

        xStream.alias("testsuite", HealthcheckSuite.class);
        xStream.alias("testcase", HealthcheckCase.class);
        xStream.aliasType("property", HealthcheckProperty.class);

        xStream.useAttributeFor(HealthcheckSuite.class, "name");
        xStream.useAttributeFor(HealthcheckSuite.class, "tests");
        xStream.useAttributeFor(HealthcheckSuite.class, "skipped");
        xStream.useAttributeFor(HealthcheckSuite.class, "time");
        xStream.useAttributeFor(HealthcheckSuite.class, "errors");
        xStream.useAttributeFor(HealthcheckSuite.class, "failures");
        xStream.addImplicitCollection(HealthcheckSuite.class, "healthcheckCases");
        xStream.aliasField("properties", HealthcheckSuite.class, "healthcheckProperties");

        xStream.useAttributeFor(HealthcheckProperty.class, "name");
        xStream.useAttributeFor(HealthcheckProperty.class, "value");

        xStream.useAttributeFor(HealthcheckCase.class, "name");
        xStream.useAttributeFor(HealthcheckCase.class, "time");

        xStream.registerConverter(new HealthcheckStacktraceConverter());

        if (!prettyPrint) {
            StringWriter sw = new StringWriter();
            xStream.marshal(this, new CompactWriter(sw));
            return sw.toString();
        } else {
            return xStream.toXML(this);
        }
    }

    public String asHtml() {
        return new HealthcheckHtmlView().process(this);
    }
}
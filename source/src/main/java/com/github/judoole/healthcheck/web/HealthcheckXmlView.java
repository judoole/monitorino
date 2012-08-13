package com.github.judoole.healthcheck.web;

import com.github.judoole.healthcheck.internal.dto.HealthcheckCase;
import com.github.judoole.healthcheck.internal.dto.HealthcheckSuite;
import com.github.judoole.healthcheck.web.xstream.HealthcheckStacktraceConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;

import java.io.StringWriter;

public class HealthcheckXmlView {
    public String process(HealthcheckSuite suite) {
        return process(suite, true);
    }

    String process(HealthcheckSuite suite, boolean prettyPrint) {
        XStream xStream = new XStream();

        xStream.alias("testsuite", HealthcheckSuite.class);
        xStream.alias("testcase", HealthcheckCase.class);

        xStream.useAttributeFor(HealthcheckSuite.class, "name");
        xStream.useAttributeFor(HealthcheckSuite.class, "tests");
        xStream.useAttributeFor(HealthcheckSuite.class, "skipped");
        xStream.useAttributeFor(HealthcheckSuite.class, "time");
        xStream.useAttributeFor(HealthcheckSuite.class, "errors");
        xStream.useAttributeFor(HealthcheckSuite.class, "failures");
        xStream.addImplicitCollection(HealthcheckSuite.class, "healthcheckCases");
        xStream.aliasField("properties", HealthcheckSuite.class, "healthcheckProperties");

        xStream.useAttributeFor(HealthcheckCase.class, "name");
        xStream.useAttributeFor(HealthcheckCase.class, "time");

        xStream.registerConverter(new HealthcheckStacktraceConverter());

        if (!prettyPrint) {
            StringWriter sw = new StringWriter();
            xStream.marshal(suite, new CompactWriter(sw));
            return sw.toString();
        } else {
            return xStream.toXML(suite);
        }
    }
}

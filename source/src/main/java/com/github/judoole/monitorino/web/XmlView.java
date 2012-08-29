package com.github.judoole.monitorino.web;

import com.github.judoole.monitorino.internal.dto.TestCase;
import com.github.judoole.monitorino.internal.dto.TestSuite;
import com.github.judoole.monitorino.web.xstream.StacktraceConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;

import java.io.StringWriter;

public class XmlView {
    public String process(TestSuite suite) {
        return process(suite, true);
    }

    String process(TestSuite suite, boolean prettyPrint) {
        XStream xStream = new XStream();

        xStream.alias("testsuite", TestSuite.class);
        xStream.alias("testcase", TestCase.class);

        xStream.useAttributeFor(TestSuite.class, "name");
        xStream.useAttributeFor(TestSuite.class, "tests");
        xStream.useAttributeFor(TestSuite.class, "skipped");
        xStream.useAttributeFor(TestSuite.class, "time");
        xStream.useAttributeFor(TestSuite.class, "errors");
        xStream.useAttributeFor(TestSuite.class, "failures");
        xStream.addImplicitCollection(TestSuite.class, "testCases");
        xStream.aliasField("properties", TestSuite.class, "healthcheckProperties");

        xStream.useAttributeFor(TestCase.class, "name");
        xStream.useAttributeFor(TestCase.class, "time");

        xStream.registerConverter(new StacktraceConverter());

        if (!prettyPrint) {
            StringWriter sw = new StringWriter();
            xStream.marshal(suite, new CompactWriter(sw));
            return sw.toString();
        } else {
            return xStream.toXML(suite);
        }
    }
}

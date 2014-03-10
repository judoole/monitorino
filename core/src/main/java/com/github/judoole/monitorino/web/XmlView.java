package com.github.judoole.monitorino.web;

import com.github.judoole.monitorino.internal.dto.Case;
import com.github.judoole.monitorino.internal.dto.MonitorinoSuite;
import com.github.judoole.monitorino.web.xstream.StacktraceConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;

import java.io.StringWriter;

public class XmlView {
    public String process(MonitorinoSuite suite) {
        return process(suite, true);
    }

    String process(MonitorinoSuite suite, boolean prettyPrint) {
        XStream xStream = new XStream();

        xStream.alias("testsuite", MonitorinoSuite.class);
        xStream.alias("testcase", Case.class);

        xStream.useAttributeFor(MonitorinoSuite.class, "name");
        xStream.useAttributeFor(MonitorinoSuite.class, "tests");
        xStream.useAttributeFor(MonitorinoSuite.class, "skipped");
        xStream.useAttributeFor(MonitorinoSuite.class, "time");
        xStream.useAttributeFor(MonitorinoSuite.class, "errors");
        xStream.useAttributeFor(MonitorinoSuite.class, "failures");
        xStream.addImplicitCollection(MonitorinoSuite.class, "testCases");
        xStream.aliasField("properties", MonitorinoSuite.class, "healthcheckProperties");

        xStream.useAttributeFor(Case.class, "name");
        xStream.useAttributeFor(Case.class, "time");

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

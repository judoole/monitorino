package no.inspirado.monitor.internal.dto;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import no.inspirado.monitor.internal.xstream.MonitorStacktraceConverter;
import no.inspirado.monitor.web.MonitorHtmlView;

import java.io.StringWriter;
import java.util.*;

public class MonitorSuite {
    public String time;
    public String name;
    public int tests;
    public int skipped;
    public int errors;
    public int failures;
    public Set<MonitorCase> monitorCases;
    public Set<MonitorProperty> monitorProperties;

    public static void main(String[] args) {
        Properties p = System.getProperties();
        Enumeration e = p.propertyNames();
        while (e.hasMoreElements()) {
            String pro = (String) e.nextElement();
            System.out.println(pro + ":: " + System.getProperty(pro));
        }
    }

    public void addCase(MonitorCase monitorCase) {
        if (monitorCases == null) monitorCases = new HashSet<MonitorCase>();
        monitorCases.add(monitorCase);
        if (monitorCase.hasError()) errors++;
        else if (monitorCase.hasFailure()) failures++;
        tests++;
    }

    public String asXml() {
        return asXml(true);
    }

    String asXml(boolean prettyPrint) {
        XStream xStream = new XStream();

        xStream.alias("testsuite", MonitorSuite.class);
        xStream.alias("testcase", MonitorCase.class);
        xStream.aliasType("property", MonitorProperty.class);

        xStream.useAttributeFor(MonitorSuite.class, "name");
        xStream.useAttributeFor(MonitorSuite.class, "tests");
        xStream.useAttributeFor(MonitorSuite.class, "skipped");
        xStream.useAttributeFor(MonitorSuite.class, "time");
        xStream.useAttributeFor(MonitorSuite.class, "errors");
        xStream.useAttributeFor(MonitorSuite.class, "failures");
        xStream.addImplicitCollection(MonitorSuite.class, "monitorCases");
        xStream.aliasField("properties", MonitorSuite.class, "monitorProperties");

        xStream.useAttributeFor(MonitorProperty.class, "name");
        xStream.useAttributeFor(MonitorProperty.class, "value");

        xStream.useAttributeFor(MonitorCase.class, "name");
        xStream.useAttributeFor(MonitorCase.class, "time");

        xStream.registerConverter(new MonitorStacktraceConverter());

        if (!prettyPrint) {
            StringWriter sw = new StringWriter();
            xStream.marshal(this, new CompactWriter(sw));
            return sw.toString();
        } else {
            return xStream.toXML(this);
        }
    }

    public String asHtml() {
        return new MonitorHtmlView().process(this);
    }
}
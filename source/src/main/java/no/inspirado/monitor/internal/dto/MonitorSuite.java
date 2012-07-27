package no.inspirado.monitor.internal.dto;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import no.inspirado.monitor.internal.xstream.MonitorErrorConverter;
import no.inspirado.monitor.web.MonitorHtmlView;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;

public class MonitorSuite {
    public String timeInSeconds;
    public String name;
    public int tests;
    public int skipped;
    public int errors;
    public int failures;
    public Collection<MonitorCase> monitorCases;

    public MonitorSuite(String name) {
        this.name = name;
    }

    public void addCase(MonitorCase monitorCase) {
        if (monitorCases == null) monitorCases = new ArrayList<MonitorCase>();
        monitorCases.add(monitorCase);
        if (monitorCase.hasError()) errors++;
        else if (monitorCase.hasFailure()) failures++;
        tests++;
    }

    public String asXml() {
        XStream xStream = new XStream();
        xStream.alias("testsuite", MonitorSuite.class);
        xStream.alias("testcase", MonitorCase.class);

        xStream.useAttributeFor(MonitorSuite.class, "name");
        xStream.useAttributeFor(MonitorSuite.class, "tests");
        xStream.useAttributeFor(MonitorSuite.class, "skipped");
        xStream.useAttributeFor(MonitorSuite.class, "errors");
        xStream.useAttributeFor(MonitorSuite.class, "failures");
        xStream.addImplicitCollection(MonitorSuite.class, "monitorCases");

        xStream.useAttributeFor(MonitorCase.class, "name");

        xStream.registerConverter(new MonitorErrorConverter());
        StringWriter sw = new StringWriter();
        xStream.marshal(this, new CompactWriter(sw));
        return sw.toString();
    }

    public String asHtml(){
        return new MonitorHtmlView().process(this);
    }

    //Contructor for XStream
    protected MonitorSuite() {
    }
}
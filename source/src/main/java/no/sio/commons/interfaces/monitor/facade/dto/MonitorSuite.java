package no.sio.commons.interfaces.monitor.facade.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.Collection;

@XStreamAlias("testsuite")
public class MonitorSuite {
    @XStreamAlias("time")
    @XStreamAsAttribute
    public String timeInSeconds;

    @XStreamAsAttribute
    @XStreamAlias("name")
    public String name;

    @XStreamAsAttribute
    @XStreamAlias("tests")
    public int numberOfTests;

    @XStreamAsAttribute
    @XStreamAlias("skipped")
    public int numberOfSkipped;

    @XStreamAsAttribute
    @XStreamAlias("errors")
    public int numberOfErrors;

    @XStreamAsAttribute
    @XStreamAlias("failures")
    public int numberOfFailures;

    @XStreamImplicit
    public Collection<MonitorCase> monitorCases;

    public MonitorSuite(String name) {
        this.name = name;
    }

    public void addCase(MonitorCase monitorCase) {
        if (monitorCases == null) monitorCases = new ArrayList<MonitorCase>();
        monitorCases.add(monitorCase);
        if (monitorCase.hasError()) numberOfErrors++;
        else if (monitorCase.hasFailure()) numberOfFailures++;
        numberOfTests++;
    }

    //Contructor for XStream
    protected MonitorSuite() {
    }
}
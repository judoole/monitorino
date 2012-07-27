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
    public String tidISekunder;

    @XStreamAsAttribute
    @XStreamAlias("name")
    public String navn;

    @XStreamAsAttribute
    @XStreamAlias("tests")
    public int antallTester;

    @XStreamAsAttribute
    @XStreamAlias("skipped")
    public int antallHoppetOver;

    @XStreamAsAttribute
    @XStreamAlias("errors")
    public int antallError;

    @XStreamAsAttribute
    @XStreamAlias("failures")
    public int antallFeil;

    @XStreamImplicit
    public Collection<MonitorCase> monitorCases;

    public MonitorSuite(String navn) {
        this.navn = navn;
    }

    public void leggTilCase(MonitorCase monitorCase) {
        if (monitorCases == null) monitorCases = new ArrayList<MonitorCase>();
        monitorCases.add(monitorCase);
        if (monitorCase.harError()) antallError++;
        else if (monitorCase.harFeil()) antallFeil++;
        antallTester++;
    }

    //Contructor for XStream
    protected MonitorSuite() {
    }
}
package no.sio.commons.interfaces.monitor.facade.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("testcase")
public class MonitorCase {
    @XStreamAlias("time")
    @XStreamAsAttribute
    public String tidISekunder;
    
    @XStreamAsAttribute
    @XStreamAlias("name")
    public String navn;

    public MonitorStacktrace error;
    @XStreamAlias("failure")
    public MonitorStacktrace feil;

    public boolean harError() {
        return error != null;
    }

    public boolean harFeil() {
        return feil != null;
    }
}
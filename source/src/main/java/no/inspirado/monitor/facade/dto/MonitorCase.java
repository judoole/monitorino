package no.inspirado.monitor.facade.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("testcase")
public class MonitorCase {
    @XStreamAlias("time")
    @XStreamAsAttribute
    public String timeInSeconds;
    
    @XStreamAsAttribute
    @XStreamAlias("name")
    public String name;

    public MonitorStacktrace error;
    @XStreamAlias("failure")
    public MonitorStacktrace failure;

    public boolean hasError() {
        return error != null;
    }

    public boolean hasFailure() {
        return failure != null;
    }
}
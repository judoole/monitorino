package no.sio.commons.interfaces.monitor.facade.dto;

import com.thoughtworks.xstream.annotations.XStreamConverter;
import no.sio.commons.interfaces.monitor.internal.xstream.MonitorErrorConverter;

@XStreamConverter(MonitorErrorConverter.class)
public class MonitorStacktrace {
    public String message;
    public String stacktrace;
}
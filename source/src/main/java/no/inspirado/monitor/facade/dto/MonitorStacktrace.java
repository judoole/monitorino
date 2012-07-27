package no.inspirado.monitor.facade.dto;

import com.thoughtworks.xstream.annotations.XStreamConverter;
import no.inspirado.monitor.internal.xstream.MonitorErrorConverter;

@XStreamConverter(MonitorErrorConverter.class)
public class MonitorStacktrace {
    public String message;
    public String stacktrace;
}
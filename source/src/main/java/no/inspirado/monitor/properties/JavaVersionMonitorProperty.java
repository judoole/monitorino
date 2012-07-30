package no.inspirado.monitor.properties;

import no.inspirado.monitor.internal.dto.MonitorProperty;
import org.apache.commons.lang3.JavaVersion;

public class JavaVersionMonitorProperty extends MonitorProperty{
    public JavaVersionMonitorProperty() {
        super();
        setName("java.version");
        setValue(System.getProperty("java.version"));
    }
}

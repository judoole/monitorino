package no.inspirado.healthcheck.properties;

import no.inspirado.healthcheck.internal.dto.MonitorProperty;

public class JavaVersionMonitorProperty extends MonitorProperty{
    public JavaVersionMonitorProperty() {
        super();
        setName("java.version");
        setValue(System.getProperty("java.version"));
    }
}

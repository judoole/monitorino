package no.inspirado.healthcheck.properties;

import no.inspirado.healthcheck.internal.dto.HealthcheckProperty;

public class JavaVersionHealthcheckProperty extends HealthcheckProperty {
    public JavaVersionHealthcheckProperty() {
        super();
        setName("java.version");
        setValue(System.getProperty("java.version"));
    }
}

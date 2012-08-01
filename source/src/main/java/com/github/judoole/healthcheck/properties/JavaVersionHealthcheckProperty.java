package com.github.judoole.healthcheck.properties;

import com.github.judoole.healthcheck.internal.dto.HealthcheckProperty;

public class JavaVersionHealthcheckProperty extends HealthcheckProperty {
    public JavaVersionHealthcheckProperty() {
        super();
        setName("java.version");
        setValue(System.getProperty("java.version"));
    }
}

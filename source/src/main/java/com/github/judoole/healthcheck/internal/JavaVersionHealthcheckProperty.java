package com.github.judoole.healthcheck.internal;

import com.github.judoole.healthcheck.internal.HealthcheckProperty;

public class JavaVersionHealthcheckProperty extends HealthcheckProperty {
    public JavaVersionHealthcheckProperty() {
        super();
        setName("java.version");
        setValue(System.getProperty("java.version"));
    }
}

package com.github.judoole.healthcheck.internal;

public class HealthcheckProperty {
    private String name;
    private String value;

    public HealthcheckProperty() {

    }

    public HealthcheckProperty(String name, String value) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

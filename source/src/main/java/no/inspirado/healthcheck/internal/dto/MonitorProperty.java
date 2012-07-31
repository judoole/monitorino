package no.inspirado.healthcheck.internal.dto;

public class MonitorProperty {
    private String name;
    private String value;

    public MonitorProperty() {

    }

    public MonitorProperty(String name, String value) {
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

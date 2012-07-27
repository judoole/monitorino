package no.inspirado.monitor.internal.dto;

public class MonitorCase {
    public String time;
    public String name;
    public MonitorStacktrace error;
    public MonitorStacktrace failure;

    public boolean hasError() {
        return error != null;
    }

    public boolean hasFailure() {
        return failure != null;
    }
}
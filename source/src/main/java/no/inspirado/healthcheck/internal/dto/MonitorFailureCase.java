package no.inspirado.healthcheck.internal.dto;

public class MonitorFailureCase {
    public final String reason;

    public  MonitorFailureCase(String reason){
        this.reason = reason;
    }
}

package com.github.judoole.healthcheck.internal.dto;

public class HealthcheckFailureCase {
    public final String reason;

    public HealthcheckFailureCase(String reason){
        this.reason = reason;
    }
}

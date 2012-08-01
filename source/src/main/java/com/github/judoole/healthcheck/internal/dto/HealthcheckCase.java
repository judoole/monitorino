package com.github.judoole.healthcheck.internal.dto;

public class HealthcheckCase {
    public String time;
    public String name;
    public HealthcheckStacktrace error;
    public HealthcheckStacktrace failure;

    public boolean hasError() {
        return error != null;
    }

    public boolean hasFailure() {
        return failure != null;
    }
}
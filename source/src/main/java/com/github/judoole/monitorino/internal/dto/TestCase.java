package com.github.judoole.monitorino.internal.dto;

public class TestCase {
    public String time;
    public String name;
    public Stacktrace error;
    public Stacktrace failure;

    public boolean hasError() {
        return error != null;
    }

    public boolean hasFailure() {
        return failure != null;
    }
}
package no.inspirado.healthcheck.internal;

public class StopWatch {
    long startTime;
    long stopTime;
    boolean running = false;

    public StopWatch start(){
        if(running) throw new IllegalStateException("Stopwatch must be reset before being restarted. ");
        this.startTime = System.currentTimeMillis();
        this.running = true;
        return this;
    }

    public StopWatch stop(){
        if(!running) throw new IllegalStateException("Stopwatch must be reset before being restarted. ");
        this.stopTime = System.currentTimeMillis();
        return this;
    }

    public Long timeInMillis(){
        return stopTime - startTime;
    }

    public Double timeInSeconds(){
        return timeInMillis() / 1000d;
    }

}
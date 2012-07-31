package no.inspirado.healthcheck.internal

import org.junit.Test
import static junit.framework.Assert.assertEquals

public class StopWatchTest {
    @Test
    public void should_calculate_time_in_seconds(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.startTime = 0;
        stopWatch.stopTime = 1100;

        assertEquals(1.1d, stopWatch.timeInSeconds());
    }

}
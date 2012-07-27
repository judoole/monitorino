package no.sio.commons.interfaces.monitor.internal

import org.junit.Test
import static junit.framework.Assert.assertEquals

public class StopWatchTest {
    @Test
    public void  skal_regne_ut_tid_i_sekunder(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.startTime = 0;
        stopWatch.stopTime = 1100;

        assertEquals(1.1d, stopWatch.timeInSeconds());
    }

}
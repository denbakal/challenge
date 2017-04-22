package ua.challenge.core.datasource.latency;

import lombok.SneakyThrows;

/**
 * Created by d.bakal on 22.04.2017.
 */
public class ThreadSleepLatencySimulator implements LatencySimulator {
    private final long extraLatency;

    public ThreadSleepLatencySimulator(long extraLatency) {
        this.extraLatency = extraLatency;
    }

    @Override
    @SneakyThrows
    public void simulate() {
        Thread.sleep(extraLatency);
    }
}

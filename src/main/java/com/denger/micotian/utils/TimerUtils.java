package com.denger.micotian.utils;

public class TimerUtils {
    private long previousMS;

    public TimerUtils() {
        this.previousMS = 0L;
    }

    public void reset() {
        this.previousMS = this.getTime();
    }

    public boolean hasReached(final double d) {
        return this.getTime() - this.previousMS >= d;
    }

    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
}

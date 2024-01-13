package com.nikhildp.benchmark;

public class ThreadTimer {
    private static final ThreadLocal<Long> timer = new ThreadLocal<>();

    private ThreadTimer() {
    }

    public static void start() {
        timer.set(System.nanoTime());
    }

    public static long stop() {
        return System.nanoTime() - timer.get();
    }
}


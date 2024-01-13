package com.nikhildp.benchmark;

public abstract class TestMap {
    long delay;

    protected TestMap(long delay) {
        this.delay = Math.max(delay, 1);
    }

    abstract void putId() throws InterruptedException;

    abstract void removeId() throws InterruptedException;
}

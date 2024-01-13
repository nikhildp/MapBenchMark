/*
 * Copyright (c) AppDynamics, Inc., and its affiliates
 * 2024
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY CODE OF APPDYNAMICS, INC.
 * The copyright notice above does not evidence any actual or intended publication of such source code
 */

package com.nikhildp.benchmark;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class ConcMap extends TestMap {
    public final Map<Long, String> map = new ConcurrentHashMap<>();

    protected ConcMap(long delay) {
        super(delay);
    }

    public void putId() throws InterruptedException {
            map.put(Thread.currentThread().getId(), "something");
            Thread.sleep(ThreadLocalRandom.current().nextLong(50));
    }

    public void removeId() throws InterruptedException {
            map.remove(Thread.currentThread().getId());
            Thread.sleep(ThreadLocalRandom.current().nextLong(50));
    }
}

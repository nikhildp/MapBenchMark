/*
 * Copyright (c) AppDynamics, Inc., and its affiliates
 * 2024
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY CODE OF APPDYNAMICS, INC.
 * The copyright notice above does not evidence any actual or intended publication of such source code
 */

package com.nikhildp.benchmark;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class SyncMap extends TestMap{
    public final Map<Long, String> map = new HashMap<>();

    protected SyncMap(long delay) {
        super(delay);
    }

    public void putId() throws InterruptedException {
        synchronized (map){
            map.put(Thread.currentThread().getId(), "something");
            Thread.sleep(ThreadLocalRandom.current().nextLong(50));
        }
    }

    public void removeId() throws InterruptedException {
        synchronized (map){
            map.remove(Thread.currentThread().getId());
            Thread.sleep(ThreadLocalRandom.current().nextLong(50));
        }
    }
}

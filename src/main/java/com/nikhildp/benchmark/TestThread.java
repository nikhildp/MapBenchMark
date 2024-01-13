/*
 * Copyright (c) AppDynamics, Inc., and its affiliates
 * 2024
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY CODE OF APPDYNAMICS, INC.
 * The copyright notice above does not evidence any actual or intended publication of such source code
 */

package com.nikhildp.benchmark;

import java.util.concurrent.Callable;

public class TestThread implements Callable<Long> {
    TestMap testMap;

    public TestThread(TestMap testMap){
        this.testMap = testMap;
    }

    @Override
    public Long call() throws InterruptedException {
        ThreadTimer.start();
        testMap.putId();
        testMap.removeId();
        return ThreadTimer.stop();
    }
}

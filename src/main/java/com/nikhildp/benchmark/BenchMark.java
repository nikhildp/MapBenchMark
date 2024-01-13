/*
 * Copyright (c) AppDynamics, Inc., and its affiliates
 * 2024
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY CODE OF APPDYNAMICS, INC.
 * The copyright notice above does not evidence any actual or intended publication of such source code
 */

package com.nikhildp.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class BenchMark {

    public static class TestParam {
        public final int concurrency;
        public final int operations;

        public TestParam(int concurrency, int operations) {
            this.concurrency = concurrency;
            this.operations = operations;
        }

        @Override
        public String toString() {
            return "TestParam: " +
                    "concurrency=" + concurrency +
                    ", operations=" + operations;
        }
    }


    public void execute(String testName, TestMap testMap, TestParam testParam) {
        ExecutorService executorService = Executors.newFixedThreadPool(testParam.concurrency);
        ArrayList<Future<Long>> fa = new ArrayList<>();


        for (int i = 0; i < testParam.operations; ++i) {
            fa.add(executorService.submit(new TestThread(testMap)));
        }

        System.out.print("Test Summary - " + testName + " : " + testParam);
        System.out.println(", operationDelay: " + testMap.delay);
        summary(fa);
        executorService.shutdown();
    }

    public static List<Long> toLongList(List<Future<Long>> fa) {

        return fa.stream().map(longFuture -> {
            try {
                return longFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public static double avg(List<Long> a) {
        Long sum = a.stream().reduce(Long::sum).get();
        return ((double) sum) / a.size();
    }

    public static double std(List<Long> a, final double mean) {
        return Math.sqrt(a.stream().map(k -> Math.pow(k.floatValue() - mean, 2)).reduce(Double::sum).get() / a.size());
    }

    public static void summary(List<Future<Long>> fa) {
        List<Long> a = toLongList(fa);
        double avg = avg(a);
        System.out.printf("Average exec time: %s ms", String.format("%6.3e",avg));
        System.out.printf("\t\t\t\tStandard Deviation: %s\n" , String.format("%6.3e",std(a, avg)));
        fa.clear();
    }
}

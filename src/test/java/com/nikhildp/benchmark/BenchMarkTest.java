package com.nikhildp.benchmark;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class BenchMarkTest {

    BenchMark benchMark = new BenchMark();

    public static Stream<Arguments> getTestParams() {
        return Stream.of(
                Arguments.of(30, 200, 0),
                Arguments.of(30, 500, 0),
                Arguments.of(30, 1000, 0),
                Arguments.of(60, 1000, 0),
                Arguments.of(90, 1000, 0),
                Arguments.of(90, 1000, 10),
                Arguments.of(90, 1000, 30),
                Arguments.of(90, 1000, 50)
        );
    }


    @ParameterizedTest
    @MethodSource("getTestParams")
    void benchMark(int conc, int operations, long delayInMs){
        benchMark.execute("ConcurrentMap", new ConcMap(delayInMs), new BenchMark.TestParam(conc, operations));
        benchMark.execute("SyncronisedMap", new SyncMap(delayInMs), new BenchMark.TestParam(conc, operations));
    }

}
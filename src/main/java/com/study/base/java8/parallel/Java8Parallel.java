package com.study.base.java8.parallel;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class Java8Parallel {

    public static void main(String[] args) {
        Instant startTime = Instant.now();

        LongStream.rangeClosed(0, 100000000000L)
                .parallel()
                .reduce(0, Long::sum);

        Instant endTime = Instant.now();

        System.out.println("耗费时间为：" + Duration.between(startTime, endTime).toMillis());  // 6819
    }
}

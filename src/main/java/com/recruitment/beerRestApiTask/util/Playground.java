package com.recruitment.beerRestApiTask.util;


import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

public class Playground {
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    public void TestExecutor() throws ExecutionException, InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(10);
        int counter = 0;
        Future<Integer>[] results = new FutureTask[10];
        for (int i = 0; i < 10; i++) {
            results[i] = executors.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return new Random().nextInt();
                }
            });
        }
        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i].get());
        }
        executors.shutdown();
    }

    ConcurrentHashMap<String, LongAdder> wordToCounter = new ConcurrentHashMap<>();
    void add(String word) {
        wordToCounter.computeIfAbsent(word, key -> new LongAdder()).increment();
    }@Test
    public void wordCounter(){
        add("ala");
        add("ma");
        add("ala");
        wordToCounter.forEach(System.out::printf);
    }
}

package com.github.anatolypopov;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class AllureTest {
  @Test
  void tesConcurrentSteps() throws ExecutionException, InterruptedException {
    ForkJoinPool forkJoinPool = new ForkJoinPool(5);
    forkJoinPool.submit(()-> IntStream.range(0,5).boxed().parallel().forEach(this::testStep)).get();
  }

  @Step("Step number {i}")
  private void testStep(Integer i) {
    try {
      Thread.sleep(Math.abs(new Random(5).nextInt())%5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

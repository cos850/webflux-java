package com.hr.webfluxjava.mono.flux;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

public class ColdnHotSequenceExample {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Cold Sequence
         */
        Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("A", "B", "C"))
                .delayElements(Duration.ofSeconds(1))
                .map(String::toLowerCase);

        coldFlux.subscribe(emit -> System.out.println("# Subscriber1 = " + emit));
        Thread.sleep(1500);

        coldFlux.subscribe(emit -> System.out.println("# Subscriber2 = " + emit));
        Thread.sleep(3500);

        //# Subscriber1 = a
        //# Subscriber1 = b
        //# Subscriber2 = a // Subscriber가 a부터 c까지 모든 요소를 받아옴
        //# Subscriber1 = c
        //# Subscriber2 = b
        //# Subscriber2 = c


        /**
         * Hot Sequence
         */
        Flux<String> hotFlux = Flux.fromIterable(Arrays.asList("A", "B", "C"))
                .delayElements(Duration.ofSeconds(1))
                .share();   // shere 가 중요 !! Cold Sequence > Hot Sequence 로 변경 (여러 Subscriber가 해당 Flux를 공유함)

        hotFlux.subscribe(emit -> System.out.println("# Subscriber1 = " + emit));
        Thread.sleep(1500);

        hotFlux.subscribe(emit -> System.out.println("# Subscriber2 = " + emit));
        Thread.sleep(3500);

        //# Subscriber1 = A
        //# Subscriber1 = B
        //# Subscriber2 = B // 2번째 Subscriber가 B부터 요소를 받아옴
        //# Subscriber1 = C
        //# Subscriber2 = C
    }
}

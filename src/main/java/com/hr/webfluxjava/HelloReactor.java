package com.hr.webfluxjava;

import reactor.core.publisher.Mono;

public class HelloReactor {
    public static void main(String[] args) {
        Mono.just("Hello Reactor!") // publisher 가 데이터를 제공
                .subscribe(message -> System.out.println(message)); // subscriber 가 데이터 받음
    }
}

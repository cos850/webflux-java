package com.hr.webfluxjava.mono.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxExample1 {
    public static void main(String[] args) {
        /**
         * concatWith
         * : 앞의 Publisher 와 전달받은 Publisher 를 합쳐서 Flux 로 반환
         *
         * Mono.justOrEmpty
         * : just 오퍼레이터는 null 을 포함할 수 없지만 justOrEmpty 는 사용 가능
         */
        Flux<Object> flux = Mono.justOrEmpty(null)
                .concatWith(Mono.justOrEmpty("Jobs"));

        flux.subscribe(
                data -> System.out.printf("# result: %s\n", data) // # result: Jobs
        );

        /**
         * Flux.concat
         * : 인자로 전달받은 Publisher 의 요소들을 하나로 연결해준다
         *
         * collectList
         * : emit 된 모든 요소들을 모아 Mono<List<T>> 형태로 반환한다
         */
        Flux.concat(
                Flux.just("A", "AA"),
                Flux.just("B"),
                Flux.just("C", "CC"),
                Flux.just("D")
            )
            .collectList()
            .subscribe(
                    list -> System.out.printf("# list: %s\n", list) // # list: [A, AA, B, C, CC, D]
            );
    }
}

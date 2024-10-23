package com.hr.webfluxjava;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class HelloReactor {
    public static void main(String[] args) {
//        helloMono();
//        helloFlux();

        // mono sample
        monoEmitCompleteSignalWithNoData();
    }

    public static void helloMono() {
        Mono.just("Hello Reactor!") // Mono의 just operator를 사용해 한 개의 데이터를 downStream으로 emit한다 (onNext signal 에 실어서 보낸다)
                .subscribe(message -> System.out.println(message)); // emit된 데이터는 람다 표현식의 인자로 들어옴.
    }

    public static void helloFlux() {
        Flux.just("Hello", "Reactor")
                .map(message -> message.toLowerCase())
                .subscribe(message -> System.out.print(message + " "));
    }

    public static void monoEmitCompleteSignalWithNoData(){
        Mono.empty()
            .subscribe(
                    data -> System.out.println("emitted data = " + data),
                    error -> System.out.println("emitted onError signal"),
                    () -> System.out.println("emitted onComplete signal")
            );
    }

}

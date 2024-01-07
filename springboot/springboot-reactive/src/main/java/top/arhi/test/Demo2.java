package top.arhi.test;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public class Demo2 {
    @Test
    public void fluxTest1() {
        Flux.just(1, 2, 3, 5, 6, 7, 0, 6, 8)
                .log()
                .doOnNext(System.out::println)
                .map(i -> 10 / i)
                .doOnNext(System.out::println)
                .doOnError((error) -> System.out.println("流错误:" + error.getMessage()))
                .subscribe(System.out::println);
    }

    @Test
    public void fluxTest2() {
        Flux.range(1, 10)
                .log()
                .doOnNext(System.out::println)
                .map(i -> 10 / i)
                .doOnNext(System.out::println)
                .doOnError((error) -> System.out.println("流错误:" + error.getMessage()))
                .subscribe(System.out::println);
    }

    @Test
    public void monoTest1() {
        Mono.just(2).map(s -> Math.pow(new Double(s),new Double(4))).subscribe(System.out::println);
    }
}

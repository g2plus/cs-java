package top.arhi.reactor;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

public class Demo2 {
    @Test
    public void fluxTest1() {
        Flux.just(1, 2, 3, 5, 6, 7, 0, 6, 8)
                .log()
                .doOnNext(System.out::println)
                .map(i -> 10 / i)
                .doOnNext(System.out::println)
                .doOnError((error) -> System.out.println("流错误:" + error.getMessage()))
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected Subscription upstream() {
                        return super.upstream();
                    }

                    @Override
                    public boolean isDisposed() {
                        return super.isDisposed();
                    }

                    @Override
                    public void dispose() {
                        super.dispose();
                    }

                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        super.hookOnSubscribe(subscription);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("---->" + value);
                    }

                    @Override
                    protected void hookOnComplete() {
                        super.hookOnComplete();
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        super.hookOnError(throwable);
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }

                    @Override
                    protected void hookFinally(SignalType type) {
                        super.hookFinally(type);
                    }

                    @Override
                    public String toString() {
                        return super.toString();
                    }
                });
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
        Mono.just(2).map(s -> Math.pow(new Double(s), new Double(4))).subscribe(System.out::println);
    }
}

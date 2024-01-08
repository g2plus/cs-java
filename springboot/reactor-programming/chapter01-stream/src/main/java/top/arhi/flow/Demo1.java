package top.arhi.flow;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

//背压模式
//reactive-stream
public class Demo1 {

    static class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<String, String> {

        private Flow.Subscription subscription = null;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.println("processor订阅完成" + subscription);
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(String item) {
            System.out.println("processor获取数据" + item);
            item += ":processor";
            submit(item);//提交处理后的数据
            subscription.request(1);//获取新的数据进行处理
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {

        }
    }


    @Test
    public void test1() throws InterruptedException {

        //1.定义发布者
        SubmissionPublisher publisher = new SubmissionPublisher();


        //2.定义处理器(既是发布者也是订阅者)
        MyProcessor myProcessor = new MyProcessor();
        MyProcessor myProcessor2 = new MyProcessor();


        //3.定义订阅者
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription = null;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println(Thread.currentThread() + "" + subscription);
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onNext(String o) {
                System.out.println(Thread.currentThread() + "订阅,接受" + o);
                if ("p-->7".equals(o)) {
                    subscription.cancel();
                }
                subscription.request(1);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(Thread.currentThread() + "订阅,错误" + t);
            }

            @Override
            public void onComplete() {
                System.out.println("接受完成");
            }
        };


        Flow.Subscriber<String> subscriber2 = new Flow.Subscriber<>() {
            Flow.Subscription subscription = null;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println(">>>" + subscription);
                this.subscription = subscription;
                subscription.request(1);
            }

            @SneakyThrows
            @Override
            public void onNext(String o) {
                Thread.sleep(1000);
                System.out.println(">>>订阅,接受" + o);
                if ("p-->7".equals(o)) {
                    subscription.cancel();
                }
                subscription.request(1);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(">>>订阅,错误" + t);
            }

            @Override
            public void onComplete() {
                System.out.println(">>>接受完成");
            }
        };

        //4.关系绑定
        publisher.subscribe(myProcessor);
        myProcessor.subscribe(myProcessor2);
        myProcessor2.subscribe(subscriber);
        myProcessor2.subscribe(subscriber2);

//        //4.关系绑定
//        publisher.subscribe(subscriber);
//        publisher.subscribe(subscriber2);

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());
            if (i == 9) {
                //publisher.close();
            }
            publisher.submit("p-->" + i);
        }

        //关闭发布通道
        publisher.close();

        //延时关闭
        Thread.sleep(20000);

    }
}

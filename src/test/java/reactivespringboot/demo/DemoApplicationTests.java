package reactivespringboot.demo;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactivespringboot.demo.batching.Vaccine;
import reactivespringboot.demo.batching.VaccineProvider;
import reactivespringboot.demo.consumer.OrderConsumer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.function.Consumer;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testMono(){ //single element
        Mono<String> mono = Mono.just("Sowmik Sarker");
        mono.log().map(data-> data.toUpperCase()).subscribe(data-> System.out.println(data)); //same as: System.out::println
    }

    @Test
    void testFlux(){ //0 to n element
        Flux<String> flux  = Flux.just("Sowmik Sarker", "Mac Book Pro", "Iphone 13 pro max");
        flux.log().map(data->data.toUpperCase()).subscribe(data-> System.out.println(data));
    }

    @Test
    void testFluxWithConsumer() throws InterruptedException {
        Flux<String> flux  = Flux.just("Sowmik Sarker", "Mac Book Pro", "Iphone 13 pro max")
                .delayElements(Duration.ofMillis(2)); //2 ms duration

        flux.log().map(data->data.toUpperCase())
                .subscribe(new OrderConsumer());

        //same as previous(Shortened)
        Flux.just("Sowmik Sarker", "Mac Book Pro", "Iphone 13 pro max")
                .delayElements(Duration.ofMillis(2)).log().map(data->data.toUpperCase())
                .subscribe(new OrderConsumer());


        //instead of using just method you can also use arraylist
        //Use of a Subscriber
        Flux.fromIterable(Arrays.asList("Sowmik Sarker", "Mac Book Pro", "Iphone 13 pro max", "lsdjflkdsjf", "kldsjflkdsfj"))
                .delayElements(Duration.ofSeconds(2))
                .log()
                .map(data->data.toUpperCase())
                .subscribe(new Subscriber<String>() {

                    //Configuration of batching
                    private long count = 0;
                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription subscription) {

                        //how many request this subscriber can handle
                        //by default the value will be 0
                        //subscription.request(Long.MAX_VALUE);
                        this.subscription = subscription;
                        subscription.request(2);
                    }

                    @Override
                    public void onNext(String s) {
                        count++;
                        if(count>=2){
                            count = 0;
                            subscription.request(2);
                        }
                        System.out.println(s);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                        throwable.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                        System.out.println("Completed>>>");
                    }
                });

        Thread.sleep(60000); //6s
    }


    //Vaccine Provider
    @Autowired
    private VaccineProvider provider;

    @Test
    void testVaccineProvider(){
        provider.provideVaccines().subscribe(new Consumer<Vaccine>() {
            @Override
            public void accept(Vaccine vaccine) {
                System.out.println(vaccine.getName());
                System.out.println(vaccine.isDelivered());
            }
        });
    }
}

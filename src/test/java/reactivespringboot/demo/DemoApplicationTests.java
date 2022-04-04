package reactivespringboot.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactivespringboot.demo.consumer.OrderConsumer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    void testFluxWithConsumer(){
        Flux<String> flux  = Flux.just("Sowmik Sarker", "Mac Book Pro", "Iphone 13 pro max");
        flux.log().map(data->data.toUpperCase())
                .subscribe(new OrderConsumer());
    }

}

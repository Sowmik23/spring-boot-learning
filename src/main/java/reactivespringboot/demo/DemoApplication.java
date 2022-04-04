package reactivespringboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}


/*
# Note:
1. Spring Reactive Programming
        ## (Async, Non-blocking, Back pressure).

        - Spring reactor is the implementation of reactive stream specification, and it accomplishes it by using a
          publisher and subscriber between which the data  streams will flow and the back pressure will be automatically
          handled for us by the spring framework through the spring reactor.

        - So, the publisher can be any application that produces unbounded number of elements. This can be databases, libraries, or other
          applications.
        - The subscriber is the application that listens to the publisher and it can control how much data it can handle. The back pressure can be
          controlled on the subscriber site.


         ## Spring Reactor API:
         - Spring boot reactor offers two important classes. Flux and Mono.
         - Flux publishes any number of elements(0 to n). and we will use Flux in our application if want to produce multiple elements.
         - Mono can produce 0 to 1 values. We should use mono if we want to product 0 or 1 elements.
         - So, both flux and mono are publisher classes.

         - Spring Web Flux brings in reactive programming to the website of things it internally uses the spring reactor by default. But we can also use
           other libraries like RxJava with spring web flux if we want.

         - So, that is reactive programming simply using asynchronous, non-blocking and back pressure. and we don't have to worry about handling this
           back pressure. As, the framework will automatically subscribe and handle this back pressure for us.

         ## Mono & Flux(key to developing reactive applications):
         - see DemoApplicationTests
         - testMono()
         - testFlux()

         ## Use a consumer
         - testFluxWithConsumer()

 */